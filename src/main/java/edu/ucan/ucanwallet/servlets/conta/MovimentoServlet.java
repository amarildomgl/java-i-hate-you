/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.ucan.ucanwallet.servlets.conta;

import edu.ucan.ucanwallet.conection.FabricaConexaoPostgres;
import edu.ucan.ucanwallet.dao.ContaDao;
import edu.ucan.ucanwallet.dao.MovimentoDao;
import edu.ucan.ucanwallet.model.Conta;
import edu.ucan.ucanwallet.model.Movimento;
import edu.ucan.ucanwallet.util.TipoMovimento;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.UUID;
import mensageria.TransacaoMensagem;


/**
 *
 * @author amari
 */
@WebServlet(name = "MovimentoServlet", urlPatterns = {"/movimento"})
public class MovimentoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String numero = request.getParameter("numero");

        String conta_destino = request.getParameter("conta_destino");
        Double valor = Double.parseDouble(request.getParameter("valor"));

        Connection connection = FabricaConexaoPostgres.getInstancia().getConnection();

        Movimento movimento = new Movimento("Transferência",
                UUID.fromString(numero),
                valor, TipoMovimento.DEBITO
        );

        try {
            MovimentoDao movimentoDao = new MovimentoDao(connection);
            ContaDao contaDao = new ContaDao(connection);
            Conta conta = contaDao.getByNumero(UUID.fromString(numero));

            conta.actualizarSaldoDisponivel(movimento);
            movimentoDao.create(movimento);
            contaDao.updateSaldo(conta);

            TransacaoMensagem ms = new TransacaoMensagem(
                    conta, movimento, conta_destino
            );
           
            ms.enviarTransacao();
            ms.fecharConexao();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
        }

        response.sendRedirect(request.getContextPath() + "/operacao/conta?numero=" + numero);
        return;
    }

}
