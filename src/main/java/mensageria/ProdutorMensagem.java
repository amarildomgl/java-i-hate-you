/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensageria;

import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author amari
 */
public class ProdutorMensagem {

    private Session session;
    private MessageProducer producer;
    private Destination destination;
    private Connection connection;

    public ProdutorMensagem(String fila) throws JMSException, NamingException {

        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(fila);
        producer = session.createProducer(destination);

    }

    public void enviarMensagem(String objecto) throws JMSException {
        TextMessage message = session.createTextMessage();
        message.setText(objecto);
        producer.send(message);
        System.out.println("Mensagem Enviada: " + message.getText());

    }

    public void fecharConexao() throws JMSException {
        if (session != null) {
            session.close();
        }
    }

    
    public void testEnvioLeituraMensagens() throws JMSException, InterruptedException {
        try {
            for (int i = 1; i <= 10; i++) {
                enviarMensagem("Mensagem " + i);
                System.out.println("Enviou mensagem " + i);
                Thread.sleep(1000); 
            }
        } finally {
            fecharConexao();
        }
    }

}
