package uk.co.rbs.openbanking.servicedesk.jwtclaims;


import io.jsonwebtoken.*;
import org.json.JSONException;
import uk.co.rbs.openbanking.servicedesk.pojo.Signing;
import uk.co.rbs.openbanking.servicedesk.services.AccountRequest;
import uk.co.rbs.openbanking.servicedesk.services.KeyServices;
import uk.co.rbs.openbanking.servicedesk.services.Token;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.*;
import static uk.co.rbs.openbanking.servicedesk.config.Config.*;


/**
 * Helper class to generate the consent request JWT.
 */
public class Consent {

    public static void main(String[] args) throws CertificateException, UnrecoverableKeyException,
            NoSuchAlgorithmException, IOException, JSONException, KeyStoreException, KeyManagementException,
                InvalidKeySpecException, URISyntaxException {
        // Call the account request service, to get account request id
        String accessToken = Token.getAuthorisationCode().getAccessToken();
        String accountRequestId = AccountRequest.getAccountRequest(accessToken).getData().getAccountRequestId();
        System.out.println(getConsentJwt(accountRequestId));
    }

    /**
     * Gets the authorisation JWT.
     * @return authorisation JWT as a string.
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    public static String getConsentJwt(String accountRequestId) throws NoSuchAlgorithmException, IOException,
                InvalidKeySpecException {
        JwtBuilder jwtBuilder = getJwtBuilder(accountRequestId);
        String jwtString = jwtBuilder.compact();
        return jwtString;
    }

    /**
     * Create and get the JWT builder object.
     * @return Jwt Object
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private static JwtBuilder getJwtBuilder(String accountRequestId) throws InvalidKeySpecException,
            NoSuchAlgorithmException, IOException {
        long currentTimeJava8 = Instant.now().toEpochMilli();
        long expMs = currentTimeJava8 + 3600000;
        Date expiryDate = new Date(expMs);

        Signing signing = KeyServices.getSigningKeys(PRIVATE_KEY_PATH);
        Key key = signing.getSigningKey();
        SignatureAlgorithm signatureAlgorithm = signing.getSignatureAlgorithm();

        // Get the JWT object with header and claim data.
        return Jwts.builder()
                .setHeaderParam("typ", JWT)
                .setHeaderParam("alg", SIGNING_ALGORITHM)
                .setHeaderParam("kid", KEY)
                .setClaims(getClaims(accountRequestId))
                .setAudience(RBS_END_POINT_AUDIENCE)
                .setExpiration(expiryDate)
                .setIssuer(SOFTWARE_STATEMENT_ID)
                .signWith(signatureAlgorithm, key);
    }

    /**
     * Get claims data
     * @param accountRequestId, account request id for the claims data
     * @return Claims object.
     */
    private static Claims getClaims(String accountRequestId) {
        Map<String, Object> claimsJson = new HashMap<String, Object>();

        Map<String,Object> intentId = new HashMap<String, Object>();
        intentId.put("value", accountRequestId);
        intentId.put("essential","true");

        Map<String,Object> acr = new HashMap<String, Object>();
        acr.put("essential","true");
        acr.put("values", ACR_VALUES);

        Map<String,Object> idToken = new HashMap<String, Object>();
        idToken.put("acr", acr);
        idToken.put("openbanking_intent_id", intentId);

        Map<String,Object> userInfo = new HashMap<String, Object>();
        userInfo.put("acr", acr);
        userInfo.put("openbanking_intent_id", intentId);

        claimsJson.put("id_token", idToken);
        claimsJson.put("userinfo", userInfo);

        Claims claims = Jwts.claims();
        claims.put("redirect_uri", REDIRECT_URI);
        claims.put("scope", SCOPE);
        claims.put("state", STATE);
        claims.put("nonce", UUID.randomUUID());
        claims.put("max_age", MAX_AGE);
        claims.put("response_type", RESPONSE_TYPES);
        claims.put("client_id", SOFTWARE_STATEMENT_ID);
        claims.put("claims", claimsJson);
        return claims;
    }
}

