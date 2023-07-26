<%-- 
    Document   : home
    Created on : 16/07/2023, 19:28:41
    Author     : amari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="edu.ucan.ucanwallet.model.Utilizador" %>
<%@ page import="edu.ucan.ucanwallet.model.Pessoa" %>
<%@ page import="edu.ucan.ucanwallet.model.Cliente" %>
<%@ page import="edu.ucan.ucanwallet.model.Conta" %>
<%@ page import="edu.ucan.ucanwallet.dao.ContaDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Painel Principal  - UcanWallet</title>        

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.min.css" />


    </head>
    <body>

        <nav
            class="navbar navbar-expand-md fixed-top navbar-shrink py-3 navbar-light"
            id="mainNav"
            >
            <div class="container">
                <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/cliente/home"
                   ><span>UcanWallet</span></a
                ><button
                    data-bs-toggle="collapse"
                    class="navbar-toggler"
                    data-bs-target="#navcol-1"
                    >
                    <span class="visually-hidden">Toggle navigation</span
                    ><span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navcol-1">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item"><a class="nav-link active" href="${pageContext.request.contextPath}/cliente/home">Início</a></li>
                    </ul>
                    <a class="btn btn-primary shadow" role="button" href="${pageContext.request.contextPath}/sair"
                       >Sair</a
                    >
                </div>
            </div>
        </nav>

        <section class="py-4 py-xl-5">
            <div class="container">
                <div class="text-white bg-primary border rounded border-0 border-primary d-flex flex-column justify-content-between flex-lg-row p-4 p-md-5">
                    <div class="pb-2 pb-lg-1">
                        <h2 class="fw-bold text-warning mb-2">


                            <%
                                    Pessoa pessoa = (Pessoa) session.getAttribute("pessoa");

                                  if (pessoa != null) {
                                      out.println(pessoa.getNome()+ " " + pessoa.getSobrenome());

                                  } 
                            %>
                        </h2>
                        <p class="mb-0">
                            Nível de acesso: <%
                                    Utilizador utilizador = (Utilizador) session.getAttribute("utilizador");

                                  if (pessoa != null) {
                                      out.println(utilizador.getNivel_acesso().name());
                                  } 
                            %>
                            <br>
                            Tipo de conta:
                            <%
                                    Cliente cliente = (Cliente) session.getAttribute("cliente");

                                  if (pessoa != null) {
                                      out.println(cliente.getTipo_cliente().name());
                                  } 
                            %>

                        </p>
                    </div>
                    <div class="my-2">
                        <a class="btn btn-light fs-5 py-2 px-4" role="button" href="${pageContext.request.contextPath}/cliente/conta/criar">
                            Criar nova conta
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <section >

            <div class="container">
                <span style="color: green;">
                    <% String mensagem = (String) request.getAttribute("mensagem"); %>
                    <%= mensagem != null ? mensagem : "" %>
                </span><br>

                <div class="text-center">
                    <p class="mb-1 text-primary" style="font-size: 1.6rem;">
                        Suas contas
                    </p>
                </div>

                <div class="row">
                    <% 
                        List<Conta> contas = (List<Conta>) session.getAttribute("contas");
                    %>
                    <div class="col d-md-flex align-items-md-end align-items-lg-center mb-5">
                        <div class="row gy-4 row-cols-1 row-cols-md-2 flex-grow-1">
                            <% 
                                if (contas != null) {
                                   for (Conta conta : contas) {
                            %>
                            <div class="col">
                                <div class="card border-primary border-2 d-flex justify-content-center p-4">
                                    <div class="card-body">
                                        <div>
                                            <h6 class="fw-bold">Conta: <%= conta.getNumero() %> </h6>
                                            <div class="accordion text-muted" role="tablist">
                                                <div class="accordion-item">
                                                    <h2 class="accordion-header" role="tab">
                                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordion-<%= conta.getNumero() %>" aria-expanded="false" aria-controls="accordion-<%= conta.getNumero() %>">
                                                            Ver informações da conta
                                                        </button>
                                                    </h2>
                                                    <div class="accordion-collapse collapse" id="accordion-<%= conta.getNumero() %>" role="tabpanel" data-bs-parent="#accordion-<%= conta.getNumero() %>">
                                                        <div class="accordion-body">
                                                            <p class="mb-0" id="saldo-estado-info">
                                                                Saldo Contablistico: <span class="underline"><%=conta.getSaldo_contablistico() %></span>
                                                                <br>
                                                                Saldo Disponível: <span class="underline"><%= conta.getSaldo_disponivel()  %></span>
                                                                <br>
                                                                Tipologia: <span class="underline"><%= conta.getTipologia_conta() %></span>
                                                                <br>
                                                                Estado: <span class="underline"><%= conta.getEstado_conta() %></span>
                                                                <br>
                                                            </p>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <a class="fw-bold text-warning btn" href="${pageContext.request.contextPath}/operacao/conta?numero=<%= conta.getNumero()%>">
                                                Operações &rarr;
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% 
                                    }
                                } else {
                            %>
                            <li>Não possui nenhuma conta criada.</li>
                                <% } %>
                        </div>
                    </div>
                </div>

            </div>
        </section>

        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/script.min.js"></script>

    </body>
</html>
