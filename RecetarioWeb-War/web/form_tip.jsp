<%-- 
    Document   : form_user
    Created on : 15/04/2015, 10:51:19 PM
    Author     : Juan Sebastian Vega
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                                                    <li class="menuItem active"><a href="home_user.jsp">Home</a></li>
                                                    <li class="menuItem"><a href="form_receta.jsp">Registrar Receta</a></li>
                                                    <li class="menuItem"><a href="form_tip">Registrar Tip</a></li>
                                                    <li class="menuItem"><a href="modificar_user.jsp">Modificar Datos</a></li>
                                                    <li class="menuItem"><a href="cambiar_contrasena.jsp">Cambiar Contraseña</a></li>
                                                    <li class="menuItem"><a href="page_recetas.jsp">Recetas</a></li>
                                                    <li class="menuItem"><a href="page_tips.jsp">Tips</a></li>
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
                        <h2>Registrar Tip</h2>
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h3>Puedes compartir nuevos tips de recetas que serian de gran utilidad para aquellos<br/>que buscan innovar y aprender secretos para aquellas recetas cotidianas ya utilizadas.</h3>
                    </div>
                    <form action="ServletTip" method="post">
                        <table columns="2" style="margin-left: 100px;float: left;margin-top: 0px" class="table table-hover">
                             <div class="form-group">
                                <label class="col-sm-offset-3 col-md-3 control-label">Nombre Tip *:</label>
                                <div class="col-sm-4 col-xs-12">
                                    <input name="nameTip" type="text" maxlength="50" required="true" placeholder="Nombre Tip" title="Nombre Tip" class="form-control"/><br/>
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <label class="col-sm-offset-3 col-md-3 control-label">Autor Tip *:</label>
                                <div class="col-sm-4 col-xs-12">
                                    <input name="autorTip" type="text" maxlength="50" required="true" placeholder="Autor Tip" title="Autor Tip" class="form-control"/><br/>
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <label class="col-sm-offset-3 col-md-3 control-label">Descripcion Tip *:</label>
                                <div class="col-sm-4 col-xs-12">
                                    <textarea name="descripTip" type="text" maxlength="980" rows="5" required="true" placeholder="Descripcion Tip" title="Descripcion Tip" class="form-control"></textarea><br/>
                                </div>
                            </div><br/>
                            <div class="form-group">
                                <div class="col-sm-offset-5 col-sm-3 col-xs-12">
                                    <input type="submit" value="Registrar Tip" title="Registrar Tip" class="btn btn-warning"/>
                                </div>
                            </div>
                        </table>
                    </form>
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