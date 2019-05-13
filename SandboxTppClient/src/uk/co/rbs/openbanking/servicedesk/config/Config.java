package uk.co.rbs.openbanking.servicedesk.config;

import java.util.Arrays;
import java.util.List;

public class Config {
    public final static String CERT_ALIAS = "transport_alias", CERT_PASSWORD = "<pwd>";
    public final static String[] TLS_VERSIONS = new String[] {"TLSv1.2", "TLSv1.1"};
    public final static String IDENTITY_KEYSTORE_PATH = "<ID_PATH>";
    public final static String TRUSTSTORE_PATH = "<TS_PATH>";
    public final static String PRIVATE_KEY_PATH = "<PK_PATH>";
    public final static String SOFTWARE_STATEMENT_PATH = "<SSA PATH>";
    public final static String JSON_CONTENT_TYPE = "application/json";
    public final static String XFORM_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public final static String RBS_ORGANISATION_ID = "<ORG_ID>";
    public final static String RBS_CLIENT_ID = "<CLIENT_ID>";

    public final static String SCOPE = "openid accounts";
    public final static String AUTHORISATION_CODE_GRANT = "authorization_code";
    public final static String CLIENT_CREDENTIALS_GRANT = "client_credentials";
    public final static String REDIRECT_URI = "<REDIRECT_URI>";
    public final static List<String> REDIRECT_URIS = Arrays.asList("<REDIRECT_URI>" );
    public final static List<String> GRANT_TYPES = Arrays.asList( "authorization_code", "refresh_token", "client_credentials");

    public final static String RBS_ACCOUNTS_SERVICE_URL ="https://ob.rbs.useinfinite.io/open-banking/v2.0/accounts/";
    public final static String RBS_ACCOUNT_REQUEST_SERVICE_URL =
            "https://ob.rbs.useinfinite.io/open-banking/v2.0/account-requests";
    public final static String RBS_TOKEN_SERVICE_URL = "https://ob.rbs.useinfinite.io/token";
    public final static String RBS_AUTHORISATION_SERVICE_URL = "https://ob.rbs.useinfinite.io/authorize";
    public final static String RBS_REGISTRATION_URL = "https://ob.rbs.useinfinite.io/register";
    public final static String RBS_TRANSACTIONS_SERVICE_URL = "https://ob.rbs.useinfinite.io/open-banking/v2.0/accounts/{AccountId}/transactions";

    public final static String SOFTWARE_STATEMENT_ID = "<CLIENT_ID>";
    public final static String RESPONSE_TYPES = "code id_token";

    public final static String RBS_END_POINT_AUDIENCE = "https://ob.rbs.useinfinite.io";
    public final static String SIGNING_ALGORITHM = "RS256";
    public final static String KEY = "<KEY_ID>";
    public final static String JWT = "JWT";
    public final static List<String> ACR_VALUES = Arrays.asList("urn:openbanking:psd2:ca") ;
    public final static String STATE = "cvcvcvcv";
    public final static int MAX_AGE = 86400;
}
