package uk.co.rbs.openbanking.servicedesk.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import uk.co.rbs.openbanking.servicedesk.pojo.accountrequest.AccountRequestResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

import static uk.co.rbs.openbanking.servicedesk.config.Config.*;

// DI on Token call please use Dep inj


/**
 * Service class to generate and execute an account request call.
 */
public class AccountRequest {
    //System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
    //System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
    //System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");

    AccountRequest() {}

    /**
     * Create and return account request object.
     * @return AccountRequestResponse object containing account request id.
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws IOException
     * @throws JSONException
     */
    public static AccountRequestResponse getAccountRequest(String accessToken) throws CertificateException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, JSONException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        String transactionFromTime = ZonedDateTime.now().format(formatter);
        String transactionToTime = ZonedDateTime.now().plusDays(30).format(formatter);
        String expirationDateTime = ZonedDateTime.now().plusDays(30).format(formatter);

        // Add permissions
        ArrayList<String> permissions = new ArrayList<String>();
        permissions.add("ReadAccountsDetail");
        permissions.add("ReadBalances");
        permissions.add("ReadTransactionsBasic");
        permissions.add("ReadTransactionsCredits");
        permissions.add("ReadTransactionsDebits");


        // Create JSON object for request.
        String jsonString = new JSONObject()
                .put("Data", new JSONObject()
                .put("TransactionFromDateTime", transactionFromTime)
                .put("TransactionToDateTime", transactionToTime)
                .put("ExpirationDateTime", expirationDateTime)
                .put("Permissions", permissions))
                .put("Risk", new JSONObject()).toString();

        Header[] headers = {
                new BasicHeader("content-type", JSON_CONTENT_TYPE)
                ,new BasicHeader("accept", JSON_CONTENT_TYPE)
                ,new BasicHeader("x-fapi-financial-id", RBS_ORGANISATION_ID)
                ,new BasicHeader("x-fapi-interaction-id", UUID.randomUUID().toString())
                ,new BasicHeader("Authorization", "Bearer " + accessToken)
        };

        StringEntity entity = new StringEntity(jsonString);
        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH,
                        TRUSTSTORE_PATH, CERT_ALIAS, CERT_PASSWORD);
        HttpResponse response = HttpsConnectionHelper.
                callEndPoint(closeableHttpClient, RBS_ACCOUNT_REQUEST_SERVICE_URL,
                        entity, headers, RequestMethod.HTTP_POST);
        AccountRequestResponse accountRequestResponse = null;
        if (response.getStatusLine().getStatusCode()==201) {
            ObjectMapper mapper = new ObjectMapper();
            String entityToString = EntityUtils.toString(response.getEntity(), "UTF-8");
            //JSON from String to Object
            accountRequestResponse = mapper.readValue(entityToString, AccountRequestResponse.class);
        }
        return accountRequestResponse;
    }

    public static void main(String[] args) {
        try {
            String accessToken = Token.getAuthorisationCode().getAccessToken();
            getAccountRequest(accessToken);
        } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException |
                KeyStoreException | KeyManagementException | IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
