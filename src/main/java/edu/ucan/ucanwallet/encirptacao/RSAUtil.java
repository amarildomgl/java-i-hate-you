
package edu.ucan.ucanwallet.encirptacao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class RSAUtil {

    public static byte[] encripitar(Object objecto, PublicKey chavePulica){
        try{
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(objecto);
        byte[] objectoByte = bos.toByteArray();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, chavePulica);
        byte[] chaveEmByteEncriptada = cipher.doFinal(objectoByte);
            return chaveEmByteEncriptada;
        }catch(IOException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            System.err.println(ex);
        }
        return null;
    }

    public static Object decriptar(byte[] objectoEmByte, PrivateKey chavePrivada){
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
            byte[] objectoEmByteDecriptado = cipher.doFinal(objectoEmByte);

            ByteArrayInputStream bis = new ByteArrayInputStream(objectoEmByteDecriptado);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
            
        }catch(IOException | ClassNotFoundException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex){
            System.err.println(ex);
        }
        return null;

    }

}
