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
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author amari
 */
public class ConsumidorMensagem {

    Session session;
    MessageConsumer consumer;
    Destination destination;
    Connection connection;

    public ConsumidorMensagem(String fila) throws JMSException, NamingException {

        Properties properties = new Properties();
        properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        destination = session.createQueue(fila);
        consumer = session.createConsumer(destination);
    }

    public String lerMensagem() throws JMSException {
        Message message = consumer.receive();
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            String msg = textMessage.getText();
            System.out.println("Leu mensagem " + msg);
            return msg;
        } else {
            System.out.println("Mensagem recebida não é uma TextMessage.");
            return null;
        }
    }

    public boolean verificarMensagensNaFila() throws JMSException {
        Message message = consumer.receiveNoWait();
        return (message != null);
    }

    public void fecharConexao() throws JMSException {
        if (session != null) {
            session.close();
        }
    }

    public void testLeituraMensagens() throws JMSException, InterruptedException {
        try {
            while (true) {
                String mensagem = lerMensagem();
                if (mensagem != null) {
                    System.out.println("Mensagem recebida: " + mensagem);
                } else {
                    System.out.println("Nenhuma mensagem disponível na fila.");
                }
                Thread.sleep(2000);
            }
        } finally {
            fecharConexao();
        }
    }
}
