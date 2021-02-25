package br.com.zup.proposal.shared.crypto;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
public class CryptoResolver {

    @Value("${proposal.crypto.algorithm}")
    private String ALGORITHM;

    @Value("${proposal.crypto.key}")
    private String KEY;

    public String encode(String value) {
        try {
            Cipher c = getCipher(Cipher.ENCRYPT_MODE);
            return new String(Base64.encodeBase64(c.doFinal(value.getBytes())), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decode(String value) {
        try {
            Cipher c = getCipher(Cipher.DECRYPT_MODE);
            return new String(c.doFinal(Base64.decodeBase64(value.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cipher getCipher(int mode) {
        try {
            Key key = new SecretKeySpec(KEY.getBytes(), "AES");
            final Cipher c = Cipher.getInstance(ALGORITHM);
            c.init(mode, key);
            return c;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}