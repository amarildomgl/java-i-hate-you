/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensageria;

import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author amari
 */
public class Teste {

    public static void main(String[] args) throws JMSException, NamingException, InterruptedException {
        String fila = "teste";
        ProdutorMensagem cliente = new ProdutorMensagem(fila);
        ConsumidorMensagem validador = new ConsumidorMensagem(fila);
        
       
        Thread produtorThread = new Thread(() -> {
            try {
                cliente.testEnvioLeituraMensagens();
            } catch (JMSException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumidorThread = new Thread(() -> {
            try {
                validador.testLeituraMensagens();
            } catch (JMSException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        
        produtorThread.start();
        consumidorThread.start();

        produtorThread.join();
        consumidorThread.join();

        cliente.fecharConexao();
        validador.fecharConexao();

    }
}
