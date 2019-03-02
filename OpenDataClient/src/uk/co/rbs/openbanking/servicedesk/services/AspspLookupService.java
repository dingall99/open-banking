package uk.co.rbs.openbanking.servicedesk.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import uk.co.rbs.openbanking.servicedesk.services.pojo.AspspResultSet;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import static uk.co.rbs.openbanking.servicedesk.services.Config.*;


public class AspspLookupService {
    public static String OB_OD_URL = "https://matls-api.openbankingtest.org.uk/scim/v2/OBAccountPaymentServiceProviders/";


    public AspspLookupService() {}

    public static AspspResultSet callAspspService(String accessToken) throws CertificateException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, JSONException, InvalidKeySpecException {

        AspspResultSet resultSet = null;
        Header[] headers = {
                new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                , new BasicHeader(HttpHeaders.ACCEPT, "application/json")
                , new BasicHeader("Authorization", "Bearer " + accessToken)
        };


        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        StringEntity entity = new UrlEncodedFormEntity(postParameters, "UTF-8");

        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.
                        createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH, TRUSTSTORE_PATH,
                                CERT_ALIAS, CERT_PASSWORD);
        HttpResponse response = HttpsConnectionHelper.callEndPoint(closeableHttpClient, OB_OD_URL,
                entity, headers, RequestMethod.HTTP_GET);
        HttpEntity stringEntity = response.getEntity();
        if (response.getStatusLine().getStatusCode()==200) {
            ObjectMapper mapper = new ObjectMapper();
            String entityToString = EntityUtils.toString(stringEntity, "UTF-8");
            //JSON from String to Object
            resultSet = mapper.readValue(entityToString, AspspResultSet.class);
        }
        return resultSet;
    }

    public static void main( String[] args ) {
        try {
            String accessToken = Token.getTokenResponse().getAccessToken();
            System.out.println(accessToken);
            AspspResultSet ass = callAspspService(accessToken);
            ass.getResources().forEach(item->item.getAuthorisationServers().forEach(server->System.out.println(server.getCustomerFriendlyName())));

        } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException |
                KeyManagementException | IOException | JSONException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
