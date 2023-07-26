<%-- 
    Document   : cadastro
    Created on : 16/07/2023, 12:38:36
    Author     : amari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UcanWallet - Cadastro</title>

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
                   ><span>ucanWallet</span></a
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
                    <a class="btn btn-primary shadow" role="button" href="${pageContext.request.contextPath}/entrar"
                       >Entrar</a
                    >
                </div>
            </div>
        </nav>
        <section class="py-4 py-md-5 my-5">
            <div class="container py-md-5">
                <div class="row">
                    <div class="col-md-6 text-center">
                        <img
                            class="img-fluid w-100"
                            src="${pageContext.request.contextPath}/assets/img/illustrations/register.svg"
                            />
                    </div>
                    <div class="col-md-5 col-xl-4 text-center text-md-start">
                        <h2 class="display-6 fw-bold mb-5">
                            <span class="underline pb-1"><strong>Cadastro</strong></span>
                        </h2>
                        <form action="${pageContext.request.contextPath}/registrar" method="post" data-bs-theme="light">
                            <span style="color: red;">
                                <% String erroCadastro = (String) request.getAttribute("erroCadastro"); %>
                                <%= erroCadastro != null ? erroCadastro : "" %>
                            </span><br>
                            <div class="mb-3">
                                <input
                                    class="shadow-sm form-control"
                                    type="text"
                                    name="nome"
                                    required
                                    placeholder="Nome"
                                    />
                            </div>
                            <div class="mb-3">
                                <input
                                    class="shadow-sm form-control"
                                    type="text"
                                    required
                                    name="sobrenome"
                                    placeholder="Sobrenome"
                                    />
                            </div>
                            <div class="mb-3">
                                <input
                                    class="shadow-sm form-control"
                                    type="text"
                                    required
                                    name="nif"
                                    placeholder="NIF"
                                    />
                            </div>
                            <div class="mb-3">
                                <input
                                    class="shadow-sm form-control"
                                    type="password"
                                    required
                                    name="senha"
                                    placeholder="Password"
                                    />
                            </div>
                            <div class="mb-3">
                                <select required name="tipo_cliente" required class="shadow-sm form-control">
                            
                                    <option value="PARTICULAR">PARTICULAR</option>                                  
                                    <option value="EMPRESA">EMPRESA</option>
                                </select>
                            </div>
                            <div class="mb-5">
                                <button class="btn btn-primary shadow" type="submit">
                                    Cadastrar
                                </button>
                            </div>
                        </form>
                        <p class="text-muted">
                            já possui uma conta?
                            <a href="${pageContext.request.contextPath}/entrar">Entrar</a>&nbsp;
                        </p>
                    </div>
                </div>
            </div>
        </section>

        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/script.min.js"></script>




    </body>
</html>
