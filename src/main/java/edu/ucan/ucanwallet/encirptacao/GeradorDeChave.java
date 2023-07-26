
package edu.ucan.ucanwallet.encirptacao;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GeradorDeChave {
    public static KeyPair gerarChave(){
        try {
        KeyPairGenerator chave = KeyPairGenerator.getInstance("RSA");
        chave.initialize(2048);
        return chave.generateKeyPair();
        }catch(NoSuchAlgorithmException ex){
            System.err.println(ex);
        }
        return null;
    }
}
