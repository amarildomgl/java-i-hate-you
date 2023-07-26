/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.ucan.ucanwallet.servlets.conta;

import edu.ucan.ucanwallet.conection.FabricaConexaoPostgres;
import edu.ucan.ucanwallet.dao.ContaDao;
import edu.ucan.ucanwallet.dao.MovimentoDao;
import edu.ucan.ucanwallet.model.Cliente;
import edu.ucan.ucanwallet.model.Conta;
import edu.ucan.ucanwallet.model.Movimento;
import edu.ucan.ucanwallet.util.TipoMovimento;
import edu.ucan.ucanwallet.util.TipologiaConta;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.UUID;

/**
 *
 * @author amari
 */
@WebServlet(name = "CriarContaServlet", urlPatterns = {"/cliente/conta/criar"})
public class CriarContaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {

            Cliente cliente = (Cliente) session.getAttribute("cliente");

            try {
                Connection connection = FabricaConexaoPostgres.getInstancia().getConnection();
                ContaDao contaDao = new ContaDao(connection);
                MovimentoDao movimentoDao = new MovimentoDao(connection);

                Conta conta = new Conta(cliente.getPk_cliente(), TipologiaConta.DEBITO);
                conta.criarChaves();
                contaDao.create(conta);

                Movimento movimento = new Movimento(
                        "DEPÓSITO INICIAL, ABERTURA DE CONTA",
                        conta.getNumero(), 3500.0,
                        TipoMovimento.CREDITO);
                movimentoDao.create(movimento);

            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
            }

            session.removeAttribute("contas");
            response.sendRedirect(request.getContextPath() + "/cliente/home");
            return; //

        }

    }
}
