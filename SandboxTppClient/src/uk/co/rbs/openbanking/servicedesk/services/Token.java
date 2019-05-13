package uk.co.rbs.openbanking.servicedesk.services;

import static uk.co.rbs.openbanking.servicedesk.config.Config.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import uk.co.rbs.openbanking.servicedesk.pojo.accesstoken.AccessTokenResponse;
import uk.co.rbs.openbanking.servicedesk.pojo.accesstoken.TokenAuthCodeResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.ArrayList;

/**
 * Token Services for Open Banking.
 */
public class Token {

    Token() {}

    /**
     * Get Consent Code.
     * @return AccessTokenResponse object.
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static AccessTokenResponse getAuthorisationCode() throws CertificateException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        Header[] headers = {
                new BasicHeader(HttpHeaders.CONTENT_TYPE, XFORM_CONTENT_TYPE),
                new BasicHeader(HttpHeaders.ACCEPT, JSON_CONTENT_TYPE)
        };
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("client_id", RBS_CLIENT_ID));
        postParameters.add(new BasicNameValuePair("scope", SCOPE));
        postParameters.add(new BasicNameValuePair("grant_type", CLIENT_CREDENTIALS_GRANT));
        StringEntity entity = new UrlEncodedFormEntity(postParameters, "UTF-8");

        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH,
                        TRUSTSTORE_PATH, CERT_ALIAS, CERT_PASSWORD);
        HttpResponse response = HttpsConnectionHelper.
                callEndPoint(closeableHttpClient, RBS_TOKEN_SERVICE_URL,
                        entity, headers, RequestMethod.HTTP_POST);
        System.out.println(response.getStatusLine().getStatusCode());
        AccessTokenResponse accessTokenResponse = null;
        if (response.getStatusLine().getStatusCode()==200) {
            ObjectMapper mapper = new ObjectMapper();
            String entityToString = EntityUtils.toString(response.getEntity(), "UTF-8");
            accessTokenResponse = mapper.readValue(entityToString, AccessTokenResponse.class);
        }
        return accessTokenResponse;
    }

    /**
     *
     * @param codeToExchange
     * @return TokenAuthCodeResponse object.
     * @throws CertificateException
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     * @throws IOException
     */
    public static TokenAuthCodeResponse getAccessTokenFromAuthorisationCode(String codeToExchange) throws CertificateException,
            UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException,
            KeyManagementException, IOException {
        Header[] headers = {new BasicHeader(
                HttpHeaders.CONTENT_TYPE, XFORM_CONTENT_TYPE)
        };
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("client_id", RBS_CLIENT_ID));
        postParameters.add(new BasicNameValuePair("grant_type", AUTHORISATION_CODE_GRANT));
        postParameters.add(new BasicNameValuePair("code", codeToExchange));
        postParameters.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));

        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH,
                        TRUSTSTORE_PATH, CERT_ALIAS, CERT_PASSWORD);
        StringEntity entity = new UrlEncodedFormEntity(postParameters, "UTF-8");
        HttpResponse response = HttpsConnectionHelper.
                callEndPoint(closeableHttpClient, RBS_TOKEN_SERVICE_URL,
                        entity, headers, RequestMethod.HTTP_POST);
        TokenAuthCodeResponse tokenResponse = null;
        if (response.getStatusLine().getStatusCode()==200) {
            ObjectMapper mapper = new ObjectMapper();
            String entityToString = EntityUtils.toString(response.getEntity(), "UTF-8");
            TokenAuthCodeResponse tokenAuthCodeResponse = mapper.readValue(entityToString, TokenAuthCodeResponse.class);
            tokenResponse = tokenAuthCodeResponse;
        }
        return tokenResponse;
    }

    public static void main( String[] args ) {

        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
        System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.wire", "DEBUG");
        try {
            //System.out.println("TY:" + Token.getAccessTokenFromAuthorisationCode("6e389e83-ccb6-4962-a4bb-2fbbc93d2bc9"));
            String token = getAuthorisationCode().getAccessToken();
            System.out.println(token);
        } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException |
                KeyStoreException | KeyManagementException | IOException e) {
            e.printStackTrace();
        }
    }
}
