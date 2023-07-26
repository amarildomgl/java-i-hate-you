<%-- 
    Document   : index.jsp
    Created on : 16/07/2023, 12:42:37
    Author     : amari
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="edu.ucan.ucanwallet.model.Utilizador" %>
<%@ page import="edu.ucan.ucanwallet.util.NivelAcessoEnum" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UcanWallet - A sua carteira digital</title>
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

        <section class="py-5 mt-5">
            <div class="container py-4 py-xl-5">
                <div class="row gy-4 gy-md-0">
                    <div class="col-md-6 text-center text-md-start d-flex d-sm-flex d-md-flex justify-content-center align-items-center justify-content-md-start align-items-md-center justify-content-xl-center">
                        <div style="max-width: 350px;">
                            <h1 class="display-5 fw-bold mb-4">ucan<span class="underline">Wallet</span>.</h1>
                            <p class="text-muted my-4">Gerencie suas contas com a ucanWallet</p>
                            <a class="btn btn-primary btn-lg me-2" role="button" href="${pageContext.request.contextPath}/entrar">Entrar</a>
                            <a class="btn btn-light btn-lg" role="button" href="${pageContext.request.contextPath}/registrar">Registrar-se</a>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div><img class="rounded img-fluid w-100 fit-cover" style="min-height: 300px;" src="${pageContext.request.contextPath}/assets/img/illustrations/startup.svg"></div>
                    </div>
                </div>
            </div>
        </section>


        <header class="pt-5">
            <div class="container pt-4 pt-xl-5">
                <div class="row pt-5">
                    <div class="col-md-8 text-center text-md-start mx-auto">
                        <div class="text-center">
                            <h1 class="display-4 fw-bold mb-5">
                                <span class="underline">UcanWallet</span>
                            </h1>
                            <p class="fs-5 text-muted mb-5">A sua carteira digital</p>
                        </div>
                    </div>
                </div>
            </div>
        </header> 



        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/script.min.js"></script>



    </body>
</html>
