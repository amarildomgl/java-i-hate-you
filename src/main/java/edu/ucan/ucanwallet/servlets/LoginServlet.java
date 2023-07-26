/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.ucan.ucanwallet.servlets;

import edu.ucan.ucanwallet.conection.FabricaConexaoPostgres;
import edu.ucan.ucanwallet.dao.UtilizadorDao;
import edu.ucan.ucanwallet.model.Utilizador;
import edu.ucan.ucanwallet.util.NivelAcessoEnum;
import edu.ucan.ucanwallet.util.SenhaHasher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;

/**
 *
 * @author amari
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/entrar"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaJSP = "/autenticacao/login.jsp";
        request.getRequestDispatcher(paginaJSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        try {
            Connection connection = FabricaConexaoPostgres.getInstancia().getConnection();
            UtilizadorDao utilizadorDao = new UtilizadorDao(connection);
            Utilizador utilizador = utilizadorDao.buscarPorLogin(login);

            if (utilizador == null) {
                request.setAttribute("erroCadastro",
                        "Erro no login");
                String paginaJSP = "/autenticacao/login.jsp";
                request.getRequestDispatcher(paginaJSP).forward(request, response);
            }

            if (!SenhaHasher.verificarSenha(senha, utilizador.getSenha())) {
                request.setAttribute("erroCadastro",
                        "Erro na senha");
                String paginaJSP = "/autenticacao/login.jsp";
                request.getRequestDispatcher(paginaJSP).forward(request, response);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("utilizador", utilizador);

            response.sendRedirect(request.getContextPath() + "/home");


        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
        }
    }

}
