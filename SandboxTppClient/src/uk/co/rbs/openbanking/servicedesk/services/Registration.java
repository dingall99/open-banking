package uk.co.rbs.openbanking.servicedesk.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import static uk.co.rbs.openbanking.servicedesk.config.Config.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.*;
import java.security.cert.CertificateException;


/**
 * Service class to generate and execute a TPP registration request call.
 */
public class Registration {


    Registration() {}

    /**
     * Creates a TPP registration request.
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static void callRegistrationEndPoint() throws CertificateException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException{
        String jwtToken = uk.co.rbs.openbanking.servicedesk.jwtclaims.Registration.getRegistrationJwt();

        Header[] headers = {new BasicHeader(
                HttpHeaders.CONTENT_TYPE, "application/jwt")
        };
        StringEntity entity = new StringEntity(jwtToken);

        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH,
                        TRUSTSTORE_PATH, CERT_ALIAS, CERT_PASSWORD);
        HttpResponse response = HttpsConnectionHelper.
                callEndPoint(closeableHttpClient, RBS_REGISTRATION_URL,
                        entity, headers, RequestMethod.HTTP_POST);
        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode()==201) {
            ObjectMapper mapper = new ObjectMapper();
            String entityToString = EntityUtils.toString(response.getEntity(), "UTF-8");
            //JSON from String to Object
            System.out.println(entityToString);
        }
    }


    public static void main( String[] args ) {
        try {
            callRegistrationEndPoint();
        } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
    }
}
