<%-- 
    Document   : criar.jsp
    Created on : 17/07/2023, 17:33:58
    Author     : amari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar conta</title>

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

        <section class="py-5 mt-5">
            <div class="container py-5">
                <div class="row">
                    <div class="col-md-8 col-xl-6 text-center mx-auto">
                        <h2 class="display-6 fw-bold mb-4">Pretende criar uma conta na <span class="underline">ucanWallet</span>?</h2>
                        <p class="text-muted">Na ucanWallet você começa com saldo de 1000.00 UCAN</p>
                    </div>
                </div>
                <div class="row d-flex justify-content-center">
                    <div class="col-md-6">
                        <div>
                            <form class="p-3 p-xl-4" method="post" data-bs-theme="light">
                                <div class="mb-3"><input class="shadow form-control" type="text" id="name-1" name="name" placeholder="Name"></div>
                                <div class="mb-3"><input class="shadow form-control" type="email" id="email-1" name="email" placeholder="Email"></div>
                                <div class="mb-3"><textarea class="shadow form-control" id="message-1" name="message" rows="6" placeholder="Message"></textarea></div>
                                <div><button class="btn btn-primary shadow d-block w-100" type="submit">Send </button></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/script.min.js"></script>



    </body>
</html>
