<%-- 
    Document   : form_recetaAd
    Created on : 15/04/2015, 10:51:19 PM
    Author     : Juan Sebastian Vega
--%>

<%@page import="com.RecetarioWeb.Entitys.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.RecetarioWeb.Controller.ControllerReceta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ControllerReceta controller = new ControllerReceta();%>
<%controller.categorias();%>
<!DOCTYPE html>
<html class="no-js" lang="en">
    <!--<![endif]-->
    <head>

        <meta charset="UTF-8"/>

        <title>Amantes de la Buena Cocina</title>

        <meta name="description" content="Onepage Multipurpose Bootstrap HTML Template">

        <meta name="author" content="">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/font-awesome.css">
        <link rel="stylesheet" href="css/animate.css">
        <link rel="stylesheet" href="css/theme.css">

        <link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Playball' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <!--wrapper start-->
        <div class="wrapper" id="wrapper">

            <!--header-->
            <header>
                <div class="banner row" id="banner">		
                    <div class="parallax text-center" style="background-image: url(http://wowthemes.net/demo/leroy/img/dummy1.jpg);">
                        <div class="parallax-pattern-overlay">
                            <div class="container text-center" style="height:580px;padding-top:170px;">
                                <a href="#"><img id="site-title" class=" wow fadeInDown" wow-data-delay="0.0s" wow-data-duration="0.9s" src="img/logo.png" alt="logo"/></a>
                                <h2 class="intro wow zoomIn" wow-data-delay="0.4s" wow-data-duration="0.9s">Amantes de la Buena Cocina</h2>
                            </div>
                        </div>
                    </div>
                </div>	
                <div class="menu">
                    <div class="navbar-wrapper">
                        <div class="container">
                            <div class="navwrapper">
                                <div class="navbar navbar-inverse navbar-static-top">
                                    <div class="container">
                                        <div class="navArea">
                                            <div class="navbar-collapse collapse">
                                                <ul class="nav navbar-nav">
                                                    <li class="menuItem active"><a href="home_admin.jsp">Home</a></li>
                                                    <li class="menuItem"><a href="form_recetaAd.jsp">Crear Receta</a></li>
                                                    <li class="menuItem"><a href="form_tipAd.jsp">Crear Tip</a></li>
                                                    <li class="menuItem"><a href="form_categoria.jsp">Crear Categoria</a></li>
                                                    <li class="menuItem"><a href="form_empresa.jsp">Crear Empresa</a></li>
                                                    <li class="menuItem"><a href="modificar_admin.jsp">Modificar Datos</a></li>
                                                    <li class="menuItem"><a href="cambiar_contrasenaAd.jsp">Modificar Contraseña</a></li>
                                                    <li class="menuItem"><form action="LogoutServlet" method="post">
                                                            <input type="submit" value="SALIR" class="btn btn-link"/>
                                                        </form></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>	
            </header>

            <!--feedback-->
            <section class="contact" id="contact">
                <div class="container">
                    <div class="heading">
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h2>Registrar Receta</h2>
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h3>Puedes registrar nuevas recetas unicas y deliciosas, con el toque personal para cada momento.</h3>
                        <form action="Receta">
                            <table columns="2" style="margin-left: 100px;float: left;margin-top: 0px" class="table table-hover">
                                <div class="form-group">
                                    <label class="col-sm-offset-3 col-md-3 control-label">Nombre Receta *: </label>
                                    <div class="col-sm-4 col-xs-12">
                                        <input name="nameRec" type="text" maxlength="30" required="true" placeholder="Nombre Receta" title="Nombre Receta" class="form-control"/><br/>
                                    </div>
                                </div><br/>
                                <div class="form-group">
                                    <label class="col-sm-offset-3 col-md-3 control-label">Autor Receta *: </label>
                                    <div class="col-sm-4 col-xs-12">
                                        <input name="autorRec" type="text" maxlength="30" required="true" placeholder="Autor Receta" title="Autor Receta" class="form-control"/><br/>
                                    </div>
                                </div><br/>
                                <%ArrayList<Categoria> categorias = controller.getCategorias();%>
                                <div class="form-group">
                                    <label class="col-sm-offset-3 col-md-3 control-label">Categoria *: </label>
                                    <div class="col-sm-4 col-xs-12">
                                        <select name="idCategoria" title="Categoria" class="form-control" required="true" >
                                            <%for (int i = 0; i < categorias.size(); i++) {%>
                                            <option value="<%=categorias.get(i).getIdcat()%>" ><%=categorias.get(i).getNombrecat()%></option>
                                            <%}%>
                                        </select><br/>
                                    </div>
                                </div><br/>
                                <div class="form-group">
                                    <label class="col-sm-offset-3 col-md-3 control-label">Ingredientes Receta *: </label>
                                    <div class="col-sm-4 col-xs-12">
                                        <textarea name="ingreRec" type="text" maxlength="990" rows="6" required="true" placeholder="Ingredientes Receta" title="Ingredientes Receta" class="form-control"></textarea><br/>
                                    </div>
                                </div><br/>
                                <div class="form-group">
                                    <label class="col-sm-offset-3 col-md-3 control-label">Descripcion Receta *: </label>
                                    <div class="col-sm-4 col-xs-12">
                                        <textarea name="descripRec" type="text" maxlength="990" rows="6" required="true" placeholder="Descripcion Receta" title="Descripcion Receta" class="form-control"></textarea><br/>
                                    </div>
                                </div><br/>
                                <div class="form-group">
                                    <div class="col-sm-offset-5 col-sm-3 col-xs-12">
                                        <input type="submit" value="Registrar Receta" title="Registrar Receta" class="btn btn-warning"/>
                                    </div>
                                </div>
                            </table>
                        </form>
                    </div>
                </div>
            </section>

            <!--footer-->
            <section class="footer" id="footer">
                <p class="text-center">
                    <a href="#wrapper" class="gototop"><i class="fa fa-angle-double-up fa-2x"></i></a>
                </p>
                <div class="container">
                    <ul>
                        <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                        <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                        <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                        <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                        <li><a href="#"><i class="fa fa-flickr"></i></a></li>
                    </ul>
                    <p>
                        &copy; 2015 Copyright<br/>
                        Universidad Central<br/>
                        Ingenieria Web
                    </p>
                </div>
            </section>

        </div><!--wrapper end-->

        <!--Javascripts-->
        <script src="js/jquery.js"></script>
        <script src="js/modernizr.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/menustick.js"></script>
        <script src="js/parallax.js"></script>
        <script src="js/easing.js"></script>
        <script src="js/wow.js"></script>
        <script src="js/smoothscroll.js"></script>
        <script src="js/masonry.js"></script>
        <script src="js/imgloaded.js"></script>
        <script src="js/classie.js"></script>
        <script src="js/colorfinder.js"></script>
        <script src="js/gridscroll.js"></script>
        <script src="js/contact.js"></script>
        <script src="js/common.js"></script>

        <script type="text/javascript">
            jQuery(function ($) {
                $(document).ready(function () {
                    //enabling stickUp on the '.navbar-wrapper' class
                    $('.navbar-wrapper').stickUp({
                        parts: {
                            0: 'banner',
                            1: 'aboutus',
                            2: 'specialties',
                            3: 'gallery',
                            4: 'feedback',
                            5: 'contact'
                        },
                        itemClass: 'menuItem',
                        itemHover: 'active',
                        topMargin: 'auto'
                    });
                });
            });
        </script>
    </body>
</html>