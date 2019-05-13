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
import uk.co.rbs.openbanking.servicedesk.pojo.accounts.AccountsResponse;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.UUID;

import static uk.co.rbs.openbanking.servicedesk.config.Config.*;

/**
 * Service class to generate and execute an transaction request call.
 */
public class Transactions {

    public static void getAllTransactions(String accountId, String accessToken) throws IOException, CertificateException,
            UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, JSONException {

        // Create JSON object for request.
        String jsonString = new JSONObject()
                .put("fromBookingDateTime", "2019-04-15T13:42:31")
                .put("toBookingDateTime", "2019-05-17T13:42:31").toString();

        Header[] headers = {
                new BasicHeader("content-type", JSON_CONTENT_TYPE)
                ,new BasicHeader("accept", JSON_CONTENT_TYPE)
                ,new BasicHeader("x-fapi-financial-id", RBS_ORGANISATION_ID)
                ,new BasicHeader("x-fapi-interaction-id", UUID.randomUUID().toString())
                ,new BasicHeader("Authorization", "Bearer " + accessToken)
        };
        String transactionURL = RBS_TRANSACTIONS_SERVICE_URL.replace("{AccountId}", accountId);

        StringEntity entity = new StringEntity(jsonString);
        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH,
                        TRUSTSTORE_PATH, CERT_ALIAS, CERT_PASSWORD);
        HttpResponse response = HttpsConnectionHelper.
                callEndPoint(closeableHttpClient, transactionURL,
                        entity, headers, RequestMethod.HTTP_GET);
        if (response.getStatusLine().getStatusCode()==200) {
            ObjectMapper mapper = new ObjectMapper();
            String entityToString = EntityUtils.toString(response.getEntity(), "UTF-8");
            //JSON from String to Object
            System.out.println(entityToString);
        }
    }

    public static void main (String args[]) {
        try {

            getAllTransactions("", "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
