package uk.co.rbs.openbanking.servicedesk.jwtclaims;


import io.jsonwebtoken.*;
import uk.co.rbs.openbanking.servicedesk.pojo.Signing;
import uk.co.rbs.openbanking.servicedesk.services.KeyServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.*;
import java.security.Key;
import static uk.co.rbs.openbanking.servicedesk.config.Config.*;


/**
 * Helper class to generate the registration request JWT.
 */
public class Registration {


    public static void main( String[] args ) {
        System.out.println(getRegistrationJwt());
    }

    /**
     * Gets the authorisation JWT.
     * @return authorisation JWT as a string.
     */
    public static String getRegistrationJwt() {
        String jwtString = null;
        try {
            JwtBuilder jwtBuilder = getJwtBuilder();
            jwtString = jwtBuilder.compact();
        } catch (InvalidKeySpecException | IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return jwtString;
    }

    /**
     * Create and get the JWT builder object.
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    private static JwtBuilder getJwtBuilder() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        long currentTimeJava8 = Instant.now().toEpochMilli();
        long expMs = currentTimeJava8 + 3600000;
        Date expiryDate = new Date(expMs);
        Date iatDate = new Date(currentTimeJava8);

        Signing signing  = KeyServices.getSigningKeys(PRIVATE_KEY_PATH);
        Key key = signing.getSigningKey();
        SignatureAlgorithm signatureAlgorithm = signing.getSignatureAlgorithm();
        Claims claims = Jwts.claims();
        claims.put("software_statement", getSoftwareStatement());
        claims.put("token_endpoint_auth_method", "tls_client_auth");
        claims.put("token_endpoint_signing_alg", SIGNING_ALGORITHM);
        claims.put("request_object_signing_alg", SIGNING_ALGORITHM);
        claims.put("id_token_signed_response_alg", SIGNING_ALGORITHM);
        claims.put("application_type", "web");
        claims.put("grant_types", GRANT_TYPES);
        claims.put("redirect_uris", REDIRECT_URIS);
        claims.put("jti", UUID.randomUUID());
        return Jwts.builder()
                .setHeaderParam("typ", JWT)
                .setHeaderParam("alg", SIGNING_ALGORITHM)
                .setHeaderParam("kid", KEY)
                .setClaims(claims)
                .setAudience(RBS_END_POINT_AUDIENCE)
                .setIssuer(SOFTWARE_STATEMENT_ID)
                .setExpiration(expiryDate)
                .setIssuedAt(iatDate)
                .signWith(signatureAlgorithm, key);
    }

    public static String getSoftwareStatement() throws IOException {
        return getFileContents(SOFTWARE_STATEMENT_PATH);
    }

    private static String getFileContents(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}

