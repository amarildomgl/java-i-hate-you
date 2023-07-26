/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mensageria;

import edu.ucan.ucanwallet.model.Conta;
import edu.ucan.ucanwallet.model.Movimento;
import edu.ucan.ucanwallet.model.Transacao;
import edu.ucan.ucanwallet.model.TransacaoCriptografada;
import edu.ucan.ucanwallet.util.EstadoTransacao;
import edu.ucan.ucanwallet.util.UUIDUtil;
import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TransacaoMensagem {

    private Session session;
    private MessageProducer producer;
    private Destination destination;
    private Connection connection;

    private final String fila = "transacoes";
    private Transacao transacao;
    private TransacaoCriptografada transacaoCriptografada;

    public TransacaoMensagem(Conta conta, Movimento movimento, String destino) throws JMSException, NamingException, InterruptedException {
        criarTransacao(conta, movimento, destino);
        criarTransacarCriptografada(conta.getChave_publica());

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

    private void criarTransacao(Conta conta, Movimento movimento, String destino) {

        transacao = new Transacao();
        transacao.setConta(conta.getNumero());
        transacao.setConta_destino(UUIDUtil.parseUUID(UUIDUtil.cleanUUIDString(destino)));
        transacao.setData_transacao(LocalDateTime.now());
        transacao.setTipo_movimento(movimento.getTipo_movimento());
        transacao.setEstado_transacao(EstadoTransacao.EM_PROCESSAMENTO);
        transacao.setValor(movimento.getValor());
    }

    private void criarTransacarCriptografada(PublicKey publicKey) {
        transacaoCriptografada = transacao.criptografarTransacao(publicKey);
    }

    public void enviarTransacao() throws JMSException, NamingException, InterruptedException {
        TextMessage message = session.createTextMessage();
        message.setText(transacaoCriptografada.getGson());
        producer.send(message);
        System.out.println("Mensagem Enviada: " + message.getText());
    }

    public void fecharConexao() throws JMSException {
        if (session != null) {
            session.close();
        }
    }

}
