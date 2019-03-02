package uk.co.rbs.openbanking.servicedesk.services;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Map;


public class MatlsConnectivityHelper {

    /**
     * Create a closeable client with MA-TLS.
     */
    public static CloseableHttpClient createCloseableHttpClient(String[] tlsVersion, String identityKeystorePath,
                                                               String trustStorePath, String certAlias,
                                                               String certPassword)
            throws NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException, KeyManagementException,
            CertificateException, IOException {
        KeyStore identityKeyStore = getJavaKeyStore(identityKeystorePath, certPassword);
        KeyStore trustKeyStore = getJavaKeyStore(trustStorePath, certPassword);
        String certificatePassword = certPassword;

        SSLContext sslContext = SSLContexts.custom()
                // load identity keystore
                .loadKeyMaterial(identityKeyStore, certificatePassword.toCharArray(), new PrivateKeyStrategy() {
                    @Override
                    public String chooseAlias(Map<String, PrivateKeyDetails> aliases, Socket socket) {
                        return certAlias;
                    }
                })
                // load trust keystore
                .loadTrustMaterial(trustKeyStore, null)
                .build();

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
                tlsVersion,
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        CloseableHttpClient client = HttpClients.custom()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .build();
        return client;
    }

    /**
     * Create a Java based key store.
     */
    private static KeyStore getJavaKeyStore(String keyStorePath, String password) throws KeyStoreException,
            NoSuchAlgorithmException, CertificateException, IOException {
        KeyStore identityKeyStore = KeyStore.getInstance("jks");
        FileInputStream identityKeyStoreFile = new FileInputStream(new File(keyStorePath));
        identityKeyStore.load(identityKeyStoreFile, password.toCharArray());
        return identityKeyStore;
    }
}

