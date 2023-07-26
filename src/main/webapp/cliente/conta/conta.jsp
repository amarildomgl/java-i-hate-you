<%-- 
    Document   : conta
    Created on : 17/07/2023, 22:54:00
    Author     : amari
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="edu.ucan.ucanwallet.model.Conta" %>
<%@ page import="edu.ucan.ucanwallet.model.Movimento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conta</title>

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

        <section class="py-4 mt-5">

        </section>

        <%             
             Conta conta = (Conta) session.getAttribute("conta");          
        %>

        <section class="py-5">
            <div class="container py-5">
                <div class="row">
                    <div class="col-md-8 col-xl-6 text-center mx-auto">
                        <h2 class="display-6 fw-bold mb-4"><span class="underline">Transferências</span></h2>
                    </div>
                </div>

                <div class="container">
                    <div class="row ">
                        <div class="col-md-12 mx-auto">
                            <div class="accordion text-muted" role="tablist" id="accordion-1">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" role="tab"><button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordion-1 .item-2" aria-expanded="true" aria-controls="accordion-1 .item-2">Ver informações da conta</button></h2>
                                    <div class="accordion-collapse collapse item-2" role="tabpanel" data-bs-parent="#accordion-1">
                                        <div class="accordion-body">
                                            <p class="mb-0" id="saldo-estado-info">
                                                Saldo Contablistico:
                                                <span class="">
                                                    <%= conta != null ? conta.getSaldo_contablistico() : "Conta não selecionada" %>
                                                </span>

                                                <br>
                                                Saldo Disponível:    
                                                <span class=" ">
                                                    <%= conta != null ? conta.getSaldo_disponivel() : "Conta não selecionada" %>
                                                </span>
                                                <br>
                                                Tipologia:    <span class="">
                                                    <%= conta != null ? conta.getTipologia_conta() : "Conta não selecionada" %>
                                                </span>
                                                <br>

                                                Estado:         <span class="">
                                                    <%= conta != null ? conta.getEstado_conta() : "Conta não selecionada" %>
                                                </span>
                                                <br>

                                            </p>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="accordion text-muted" role="tablist" id="accordion-2">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" role="tab"><button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordion-2 .item-2" aria-expanded="true" aria-controls="accordion-2 .item-2">Efectuar transferência</button></h2>
                                    <div class="accordion-collapse collapse item-2" role="tabpanel" data-bs-parent="#accordion-1">
                                        <div class="accordion-body">

                                            <form class="p-3 p-xl-4" method="post" data-bs-theme="light" action="${pageContext.request.contextPath}/movimento?numero=<%= conta.getNumero()%>" >

                                                <div class="mb-3"><input class="shadow form-control" type="text" readonly=""  value=" <%= conta != null ? conta.getNumero() : "Conta não selecionada" %>" name="conta"  placeholder="conta"></div>

                                                <div class="mb-3"><input class="shadow form-control" type="text" required name="conta_destino" placeholder="Conta destino" ></div>
                                                <div class="mb-3"><input class="shadow form-control" type="number" required  name="valor" placeholder="montante"></div>
                                                <div><button class="btn btn-primary shadow d-block w-100" type="submit">Transferir </button></div>
                                            </form>

                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="accordion text-muted" role="tablist" id="accordion-3">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" role="tab"><button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordion-3 .item-2" aria-expanded="true" aria-controls="accordion-3 .item-2">Listar movimentos da conta</button></h2>
                                    <div class="accordion-collapse collapse item-2" role="tabpanel" data-bs-parent="#accordion-3">
                                        <div class="accordion-body">

                                              <ul>
                            <% 
                                List<Movimento> movimentos = (List<Movimento>) session.getAttribute("movimentos");
                            %>               

                            <% 
                                if (movimentos != null) {
                                   for (Movimento movimento : movimentos) {
                            %>
                            <li> <span class="underline"><%= movimento.getTipo_movimento().name() %> </span>  :  <%= movimento.getDescricao() %> | Valor:  
                                
                            <%= movimento.getValor() %> 
                            </li>
                                <% 
                                        }
                                    } else {
                                %>
                            <li>Não possui nenhum movimento nesta conta.</li>
                                <% } %>
                        </ul>

                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="row d-flex justify-content-center">
                    <div class="col-md-6">
                     
                    </div>
                </div>
            </div>

        </section>

        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/script.min.js"></script>

    </body>
</html>
