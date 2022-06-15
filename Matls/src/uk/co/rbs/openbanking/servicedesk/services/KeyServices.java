package uk.co.rbs.openbanking.servicedesk.services;

import uk.co.rbs.openbanking.servicedesk.pojo.Signing;


import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import static io.jsonwebtoken.SignatureAlgorithm.PS256;
import static java.security.KeyFactory.getInstance;

public class KeyServices {


    public static Signing getSigningKeys(String privateKeyFileName) throws InvalidKeySpecException,
            NoSuchAlgorithmException, IOException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        Signing signing = new Signing();
        signing.setSignatureAlgorithm(PS256);
        signing.setSigningKey(getRsaPrivateKey(privateKeyFileName));
        return signing;
    }

    private static Key getRsaPrivateKey(String privateKeyFileName) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String contents = getFileContents(privateKeyFileName);
        String trimmedContents = contents.replaceAll("\\n", "")
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        byte[] bytes = Base64.getDecoder().decode(trimmedContents);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        System.out.println(privateKey);
        return privateKey;
    }


    private static Key getEncryptedPrivateKey(String privateKeyFileName) throws IOException, InvalidKeySpecException,
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        String contents = getFileContents(privateKeyFileName);
        String trimmedContents = contents.replaceAll("\\n", "")
                .replace("-----BEGIN ENCRYPTED PRIVATE KEY-----", "")
                .replace("-----END ENCRYPTED PRIVATE KEY-----", "");
        byte[] bytes = Base64.getDecoder().decode(trimmedContents);
        EncryptedPrivateKeyInfo encryptPKInfo = new EncryptedPrivateKeyInfo(bytes);
        Cipher cipher = Cipher.getInstance(encryptPKInfo.getAlgName());
        PBEKeySpec pbeKeySpec = new PBEKeySpec("Gr8FFF96!".toCharArray());
        SecretKeyFactory secFac = SecretKeyFactory.getInstance(encryptPKInfo.getAlgName());
        Key pbeKey = secFac.generateSecret(pbeKeySpec);
        AlgorithmParameters algParams = encryptPKInfo.getAlgParameters();
        cipher.init(Cipher.DECRYPT_MODE, pbeKey, algParams);
        KeySpec pkcs8KeySpec = encryptPKInfo.getKeySpec(cipher);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(pkcs8KeySpec);
    }

    private static String getFileContents(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
