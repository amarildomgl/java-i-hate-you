/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package edu.ucan.ucanwallet.servlets;

import edu.ucan.ucanwallet.conection.FabricaConexaoPostgres;
import edu.ucan.ucanwallet.dao.ClienteDao;
import edu.ucan.ucanwallet.dao.PessoaDao;
import edu.ucan.ucanwallet.dao.UtilizadorDao;
import edu.ucan.ucanwallet.model.Cliente;
import edu.ucan.ucanwallet.model.Pessoa;
import edu.ucan.ucanwallet.model.Utilizador;
import edu.ucan.ucanwallet.util.TipoCliente;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 *
 * @author amari
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/registrar"})
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String paginaJSP = "/autenticacao/cadastro.jsp";
        request.getRequestDispatcher(paginaJSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nif = request.getParameter("nif");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String tipo_cliente = request.getParameter("tipo_cliente");
        try {
            Connection connection = FabricaConexaoPostgres.getInstancia().getConnection();
            UtilizadorDao utilizadorDao = new UtilizadorDao(connection);
            PessoaDao pessoaDao = new PessoaDao(connection);

            Pessoa existe = pessoaDao.getPorNif(nif);

            if (existe != null) {
                request.setAttribute("erroCadastro",
                        "O nif indicado já está em uso. Escolha outro nif.");
                String paginaJSP = "/autenticacao/cadastro.jsp";
                request.getRequestDispatcher(paginaJSP).forward(request, response);
            }

            Pessoa pessoa = new Pessoa(nome, sobrenome, nif);
            pessoa = pessoaDao.create(pessoa);

            Utilizador utilizador = new Utilizador(nif, senha, pessoa.getPk_pessoa());
            utilizadorDao.create(utilizador);

            utilizador = utilizadorDao.buscarPorLogin(nif);

            Cliente cliente = new Cliente(utilizador.getPk_usuario(), nif, TipoCliente.valueOf(tipo_cliente));

            ClienteDao clienteDao = new ClienteDao(connection);

            clienteDao.create(cliente);

            request.setAttribute("mensagem",
                    "Conta criada com sucesso! use o seu nif como login");

            response.sendRedirect(request.getContextPath() + "/entrar");


        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao acessar o banco de dados.");
        }

    }

}
