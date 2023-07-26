/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.ucan.ucanwallet.servlets.cliente;

import edu.ucan.ucanwallet.conection.FabricaConexaoPostgres;
import edu.ucan.ucanwallet.dao.ClienteDao;
import edu.ucan.ucanwallet.dao.ContaDao;
import edu.ucan.ucanwallet.dao.PessoaDao;
import edu.ucan.ucanwallet.model.Cliente;
import edu.ucan.ucanwallet.model.Conta;
import edu.ucan.ucanwallet.model.Pessoa;
import edu.ucan.ucanwallet.model.Utilizador;
import edu.ucan.ucanwallet.util.NivelAcessoEnum;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amari
 */
@WebServlet(name = "HomeClenteServlet", urlPatterns = {"/cliente/home"})
public class HomeClenteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            Utilizador utilizador = (Utilizador) session.getAttribute("utilizador");

            if (utilizador != null) {
                try {
                    Connection connection = FabricaConexaoPostgres.getInstancia().getConnection();
                    PessoaDao pessoaDao = new PessoaDao(connection);

                    Pessoa pessoa = pessoaDao.getPorId(utilizador.getFk_pessoa());
                    session.setAttribute("pessoa", pessoa);

                    ClienteDao clienteDao = new ClienteDao(connection);
                    Cliente cliente = clienteDao.getByNif(pessoa.getNif());

                    session.setAttribute("cliente", cliente);

                    ContaDao contaDao = new ContaDao(connection);

                    List<Conta> contas = new ArrayList<>();
                    contas = contaDao.getByClienteFk(cliente.getPk_cliente());

                    session.setAttribute("contas", contas);

                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");

                }

                String paginaJSP = "/cliente/home.jsp";
                request.getRequestDispatcher(paginaJSP).forward(request, response);
                return;
            }
        }

        String paginaJSP = "/autenticacao/login.jsp";
        request.getRequestDispatcher(paginaJSP).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
