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
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author amari
 */
@WebServlet(name = "OperacoesContaServlet", urlPatterns = {"/operacao/conta"})
public class OperacoesContaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String numero = request.getParameter("numero");

        try {
            Connection connection = FabricaConexaoPostgres.getInstancia().getConnection();
            ContaDao contaDao = new ContaDao(connection);
            MovimentoDao movimentoDao = new MovimentoDao(connection);
            Conta conta = contaDao.getByNumero(UUID.fromString(numero));

            session.setAttribute("conta", conta);

            List<Movimento> movimentos = new ArrayList<>();
            movimentos = movimentoDao.get(conta.getNumero());
            session.setAttribute("movimentos", movimentos);

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");

        }
        String paginaJSP = "/cliente/conta/conta.jsp";
        request.getRequestDispatcher(paginaJSP).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
