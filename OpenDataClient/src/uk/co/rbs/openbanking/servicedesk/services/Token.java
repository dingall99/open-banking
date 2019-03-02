package uk.co.rbs.openbanking.servicedesk.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import uk.co.rbs.openbanking.servicedesk.pojo.Signing;
import uk.co.rbs.openbanking.servicedesk.services.pojo.TokenResponse;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static uk.co.rbs.openbanking.servicedesk.services.Config.*;
import static uk.co.rbs.openbanking.servicedesk.services.KeyServices.getSigningKeys;


public class Token {

    private final static String OB_END_POINT_AUDIENCE = "https://matls-sso.openbankingtest.org.uk/as/token.oauth2";
    private final static String SIGNING_ALGORITHM = "RS256";
    private final static String KEY = "<KEY>";
    private final static String JWT = "JWT";
    private final static String GRANT_TYPE = "client_credentials";
    private final static String CLIENT_ID = "<CLIENT_ID>";
    private final static String ASSERTION_TYPE = "urn:ietf:params:oauth:client-assertion-type:jwt-bearer";
    private final static String SCOPE = "ASPSPReadAccess AuthoritiesReadAccess TPPReadAccess";
    private final static String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private final static int EXPIRY_IN_MINUTES = 6;


    Token() {}

    public static TokenResponse getTokenResponse() throws CertificateException, UnrecoverableKeyException,
            NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, JSONException, InvalidKeySpecException {
        TokenResponse tokenResponse = null;
        String jwt = getJwtBuilder().compact();
        Header[] headers = {new BasicHeader(
                HttpHeaders.CONTENT_TYPE, CONTENT_TYPE)
        };
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
        postParameters.add(new BasicNameValuePair("grant_type", GRANT_TYPE));
        postParameters.add(new BasicNameValuePair("client_assertion_type", ASSERTION_TYPE));
        postParameters.add(new BasicNameValuePair("client_assertion", jwt));
        postParameters.add(new BasicNameValuePair("scope", SCOPE));
        StringEntity entity = new UrlEncodedFormEntity(postParameters, "UTF-8");
        //callRBSBrandedEndpoint(OB_END_POINT_AUDIENCE, entity, headers);
        CloseableHttpClient closeableHttpClient =
                MatlsConnectivityHelper.
                        createCloseableHttpClient(TLS_VERSIONS, IDENTITY_KEYSTORE_PATH, TRUSTSTORE_PATH,
                                CERT_ALIAS, CERT_PASSWORD);
        HttpResponse response = HttpsConnectionHelper.callEndPoint(closeableHttpClient, OB_END_POINT_AUDIENCE,
                entity, headers, RequestMethod.HTTP_POST);
        if (response.getStatusLine().getStatusCode()==200) {
            ObjectMapper mapper = new ObjectMapper();
            HttpEntity stringEntity = response.getEntity();
            String entityToString = EntityUtils.toString(stringEntity, "UTF-8");
            //JSON from String to Object
            tokenResponse = mapper.readValue(entityToString, TokenResponse.class);
        }
        return tokenResponse;
    }

    private static JwtBuilder getJwtBuilder() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        long currentTimeJava8 = Instant.now().toEpochMilli();
        long expMs = currentTimeJava8 +   (EXPIRY_IN_MINUTES * 600000);
        Date expiryDate = new Date(expMs);

        Signing signing  = getSigningKeys(PRIVATE_KEY_PATH);
        Key key = signing.getSigningKey();
        SignatureAlgorithm signatureAlgorithm = signing.getSignatureAlgorithm();
        Claims claims = Jwts.claims();
        claims.put("scope", SCOPE);
        claims.put("jti", UUID.randomUUID().toString());

        return Jwts.builder()
                .setHeaderParam("typ", JWT)
                .setHeaderParam("alg", SIGNING_ALGORITHM)
                .setHeaderParam("kid", KEY)
                .setClaims(claims)
                .setAudience(OB_END_POINT_AUDIENCE)
                .setIssuer(CLIENT_ID)
                .setSubject(CLIENT_ID)
                .setIssuedAt(new Date(currentTimeJava8))
                .setExpiration(expiryDate)
                .signWith(signatureAlgorithm, key);
    }


    public static void main( String[] args ) {
        try {
            String accessToken = getTokenResponse().getAccessToken();
            System.out.println(accessToken);
        } catch (CertificateException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException |
                KeyManagementException | IOException | JSONException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
