package uk.co.rbs.openbanking.servicedesk.services;


import org.json.JSONException;

import javax.print.URIException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;
import static uk.co.rbs.openbanking.servicedesk.config.Config.*;


/**
 * Service class to generate and execute a consent URL.
 */
public class Consent {

    /**
     * Get consent URL as a string.
     * @return consent URL as a string.
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws InvalidKeySpecException
     */
    public static String getAuthenticationUrl(String accountRequestId) throws  NoSuchAlgorithmException,
            IOException, InvalidKeySpecException {
        String rbsAuthorisationURL = RBS_AUTHORISATION_SERVICE_URL +
                "?client_id=" + SOFTWARE_STATEMENT_ID +
                "&scope=" + encodeSpaces(SCOPE) +
                "&response_type=" + encodeSpaces(RESPONSE_TYPES) +
                "&redirect_uri=" + encode(REDIRECT_URI) +
                "&nonce=" + UUID.randomUUID() +
                "&request=" + uk.co.rbs.openbanking.servicedesk.jwtclaims.Consent.getConsentJwt(accountRequestId);
        return rbsAuthorisationURL;
    }

    /**
     * Encode URL characters.
     * @param stuffToEncode
     * @return
     */
    private static String encode(String stuffToEncode) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(stuffToEncode, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encoded;
    }

    /**
     * Encode spaces
     * @param stringToEncode
     * @return
     */
    private static String encodeSpaces(String stringToEncode) {
        return stringToEncode.replace(" ", "%20");
    }

    public static void main( String[] args ) {
        try {
            String accessToken = Token.getAuthorisationCode().getAccessToken();
            String accountRequestId = AccountRequest.getAccountRequest(accessToken).getData().getAccountRequestId();
            System.out.println(getAuthenticationUrl(accountRequestId));
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}