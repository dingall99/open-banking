package uk.co.rbs.openbanking.servicedesk.services;

import uk.co.rbs.openbanking.servicedesk.pojo.Signing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.RS256;
import static java.security.KeyFactory.getInstance;

public class KeyServices {


    public static Signing getSigningKeys(String privateKeyFileName) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        Signing signing = new Signing();
        signing.setSignatureAlgorithm(RS256);
        signing.setSigningKey(getRsaPrivateKey(privateKeyFileName));
        return signing;
    }

    private static Key getRsaPrivateKey(String privateKeyFileName) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String contents = getFileContents(privateKeyFileName);
        String trimmedContents = contents.replaceAll("\\n", "")
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "");
        byte[] bytes = Base64.getDecoder().decode(trimmedContents);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        System.out.println(privateKey);
        return privateKey;
    }

    private static String getFileContents(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
