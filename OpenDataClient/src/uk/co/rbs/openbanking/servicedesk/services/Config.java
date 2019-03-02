package uk.co.rbs.openbanking.servicedesk.services;

public class Config {
    static String CERT_ALIAS = "alias", CERT_PASSWORD = "secret";
    static String[] TLS_VERSIONS = new String[] {"TLSv1.2", "TLSv1.1"};
    static String IDENTITY_KEYSTORE_PATH = "C:\\devtools\\OpenSSL-Win64\\certs\\transport.jks";
    static String TRUSTSTORE_PATH = "C:\\devtools\\OpenSSL-Win64\\certs\\ob_truststore.jks";
    static String PRIVATE_KEY_PATH = "C:\\devtools\\OpenSSL-Win64\\certs\\" +
            "private.key";
}
