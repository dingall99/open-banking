package uk.co.rbs.openbanking.servicedesk.config;

import java.util.Arrays;
import java.util.List;

public class Config {
    public final static String CERT_ALIAS = "transport_alias", CERT_PASSWORD = "changeit";
    public final static String[] TLS_VERSIONS = new String[] {"TLSv1.2", "TLSv1.1"};
    public final static String IDENTITY_KEYSTORE_PATH = "C:\\devtools\\OpenSSL-Win64\\certs\\ob_rbs_transport_is.jks";
    public final static String TRUSTSTORE_PATH = "C:\\devtools\\OpenSSL-Win64\\certs\\ob_truststore.jks";
    public final static String PRIVATE_KEY_PATH = "C:\\devtools\\OpenSSL-Win64\\certs\\" +
            "tpp1_sig_rbs_nft_0015800000jfwB4AAI_2LO9x5ZbvYosDN5KrxUcnh.key";
    public final static String SOFTWARE_STATEMENT_PATH = "C:\\devtools\\OpenSSL-Win64\\certs\\ssa_rbs.txt";
    public final static String JSON_CONTENT_TYPE = "application/json";
    public final static String XFORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public final static String RBS_ORGANISATION_ID = "0015800000jfwB4AAI";
    public final static String RBS_CLIENT_ID = "2LO9x5ZbvYosDN5KrxUcnh";

    public final static String SCOPE = "openid accounts";
    public final static String AUTHORISATION_CODE_GRANT = "authorization_code";
    public final static String CLIENT_CREDENTIALS_GRANT = "client_credentials";
    public final static String REDIRECT_URI = "https://willowbrook-consulting.com/ob/redirect/";
    public final static List<String> REDIRECT_URIS = Arrays.asList("https://willowbrook-consulting.com/ob/redirect/" );
    public final static List<String> GRANT_TYPES = Arrays.asList( "authorization_code", "refresh_token", "client_credentials");

    public final static String RBS_ACCOUNTS_SERVICE_URL ="https://ob.rbs.useinfinite.io/open-banking/v2.0/accounts/";
    public final static String RBS_ACCOUNT_REQUEST_SERVICE_URL =
            "https://ob.rbs.useinfinite.io/open-banking/v2.0/account-requests";
    public final static String RBS_TOKEN_SERVICE_URL = "https://ob.rbs.useinfinite.io/token";
    public final static String RBS_AUTHORISATION_SERVICE_URL = "https://ob.rbs.useinfinite.io/authorize";
    public final static String RBS_REGISTRATION_URL = "https://ob.rbs.useinfinite.io/register";
    public final static String RBS_TRANSACTIONS_SERVICE_URL = "https://ob.rbs.useinfinite.io/open-banking/v2.0/accounts/{AccountId}/transactions";

    public final static String SOFTWARE_STATEMENT_ID = "2LO9x5ZbvYosDN5KrxUcnh";
    public final static String RESPONSE_TYPES = "code id_token";

    public final static String RBS_END_POINT_AUDIENCE = "https://ob.rbs.useinfinite.io";
    public final static String SIGNING_ALGORITHM = "RS256";
    public final static String KEY = "9Ic4a2TUY2SrisCVgcEEeftiV8I";
    public final static String JWT = "JWT";
    public final static List<String> ACR_VALUES = Arrays.asList("urn:openbanking:psd2:ca") ;
    public final static String STATE = "cvcvcvcv";
    public final static int MAX_AGE = 86400;
}
