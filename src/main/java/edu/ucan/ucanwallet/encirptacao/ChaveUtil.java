
package edu.ucan.ucanwallet.encirptacao;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ChaveUtil {
    public static String chavePublicaBase64(byte[] publicKeyBytes) {
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    public static String chavePrivadaBase64(byte[] privateKeyBytes) {
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

    public static byte[] chavePublicaParaBase64(String publicKeyBase64) {
        return Base64.getDecoder().decode(publicKeyBase64);
    }

    public static byte[] chavePrivadaParaBase64(String privateKeyBase64) {
        return Base64.getDecoder().decode(privateKeyBase64);
    }
    
    public static PublicKey bytesParaChavePublica(byte[] publicKeyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey butesParaChavePrivada(byte[] privateKeyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(keySpec);
    }
}
