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
import uk.co.rbs.openbanking.servicedesk.pojo.accesstoken.TokenAuthCodeResponse;
import uk.co.rbs.openbanking.servicedesk.pojo.accounts.Account;
import uk.co.rbs.openbanking.servicedesk.pojo.accounts.AccountsResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.UUID;

import static uk.co.rbs.openbanking.servicedesk.config.Config.*;
import static uk.co.rbs.openbanking.servicedesk.config.Config.CERT_PASSWORD;
import static uk.co.rbs.openbanking.servicedesk.services.Transactions.getAllTransactions;

/**
 * Service class to generate and execute an accounts call.
 */
public class Accounts {

    /**
     * Get account meta data.
     * @param accessToken
     * @return
     * @throws JSONException
     * @throws IOException
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     */
    public static AccountsResponse getAllAccounts(String accessToken) throws IOException, CertificateException,
            UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {

        String jsonString = new JSONObject().toString();

        Header[] headers = {
                new BasicHeader("content-type", JSON_CONTENT_TYPE)
                , new BasicHeader("accept", JSON_CONTENT_TYPE)
                , new BasicHeader("x-fapi-financial-id", RBS_ORGANISATION_ID)
                , new BasicHeader("x-fapi-interaction-id", UUID.randomUUID().toString())
                , new BasicHeader("Authorization", "Bearer " + accessToken)
        };

        StringEntity entity = new StringEntity(jsonString);
        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH,
                        TRUSTSTORE_PATH, CERT_ALIAS, CERT_PASSWORD);
        HttpResponse response = HttpsConnectionHelper.
                callEndPoint(closeableHttpClient, RBS_ACCOUNTS_SERVICE_URL,
                        entity, headers, RequestMethod.HTTP_GET);
        AccountsResponse accountsResponse = null;
        if (response.getStatusLine().getStatusCode()==200) {
            ObjectMapper mapper = new ObjectMapper();
            String entityToString = EntityUtils.toString(response.getEntity(), "UTF-8");
            //JSON from String to Object
            accountsResponse = mapper.readValue(entityToString, AccountsResponse.class);
        }
        return accountsResponse;
    }

    public static void main(String args[]) {
        try {
            TokenAuthCodeResponse tokenAuthCodeResponse = Token.getAccessTokenFromAuthorisationCode("d6355953-ad80-4827-9bdb-049315dcde55");
            String accessToken = tokenAuthCodeResponse.getAccessToken();
            AccountsResponse getAccounts = Accounts.getAllAccounts(accessToken);
            for (Account account : getAccounts.getData().getAccount()) {
                String accountId = account.getAccountId();
                System.out.println(accountId);
                getAllTransactions(accountId, accessToken);
            }
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
