package uk.co.rbs.openbanking.servicedesk.pojo;

import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;

public class Signing {
    private Key signingKey;
    private SignatureAlgorithm signatureAlgorithm;

    public Signing() {}

    public Signing(Key signingKey, SignatureAlgorithm signatureAlgorithm) {
        this.signingKey = signingKey;
        this.signatureAlgorithm = signatureAlgorithm;
    }


    public Key getSigningKey() {
        return signingKey;
    }

    public void setSigningKey(Key signingKey) {
        this.signingKey = signingKey;
    }

    public SignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }
}
