<%-- 
    Document   : login
    Created on : 16/07/2023, 12:38:52
    Author     : amari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ucanWallet - Entrar</title>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.min.css" />


    </head>
    <body>



        <nav
            class="navbar navbar-expand-md fixed-top navbar-shrink py-3 navbar-light"
            id="mainNav"
            >
            <div class="container">
                <a class="navbar-brand d-flex align-items-center" href="${pageContext.request.contextPath}/index.jsp"
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
                        <li class="nav-item">
                            A sua carteira digital
                        </li>
                    </ul>
                    <a class="btn btn-primary shadow" role="button" href="${pageContext.request.contextPath}/autenticacao/cadastro.jsp"
                       >Cadastre-se</a
                    >
                </div>
            </div>
        </nav>

        <span style="color: green;">
            <% String mensagem = (String) request.getAttribute("mensagem"); %>
            <%= mensagem != null ? mensagem : "" %>
        </span><br>
        <section class="py-4 py-md-5 my-5">
            <div class="container py-md-5">
                <div class="row">
                    <div class="col-md-6 text-center">
                        <img
                            class="img-fluid w-100"
                            src="${pageContext.request.contextPath}/assets/img/illustrations/login.svg"
                            />
                    </div>
                    <div class="col-md-5 col-xl-4 text-center text-md-start">
                        <h2 class="display-6 fw-bold mb-5">
                            <span class="underline pb-1"><strong>Entrar</strong><br /></span>
                        </h2>
                        <form action="${pageContext.request.contextPath}/entrar" method="post" data-bs-theme="light">
                            <div class="mb-3">

                                <!-- Exibir mensagem de erro em cima do campo de login -->
                                <span style="color: red;">
                                    <% String erroCadastro = (String) request.getAttribute("erroCadastro"); %>
                                    <%= erroCadastro != null ? erroCadastro : "" %>
                                </span><br>
                                <input
                                    class="shadow form-control"
                                    type="text"
                                    name="login"
                                    required
                                    placeholder="Login/Nif"
                                    />
                            </div>
                            <div class="mb-3">
                                <input
                                    class="shadow form-control"
                                    type="password"
                                    name="senha"
                                    required
                                    placeholder="Password"
                                    />
                            </div>
                            <div class="mb-5">
                                <button class="btn btn-primary shadow" type="submit">
                                    Entrar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>


        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/script.min.js"></script>


    </body>
</html>
