<!--sdfsddffsadkfjdsaf-->
<%@page import="com.RecetarioWeb.Controller.ControllerReceta"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%ControllerReceta controller = new ControllerReceta();%>
<%controller.cargarDuo();%>
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
                                <img id="site-title" class=" wow fadeInDown" wow-data-delay="0.0s" wow-data-duration="0.9s" src="img/logo.png" alt="logo"/> 
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
                                                    <li class="menuItem active"><a href="index.jsp">Home</a></li>
                                                    <li class="menuItem"><a href="form_user.jsp">Registrar</a></li>
                                                    <li class="menuItem"><a href="page_recetas.jsp">Recetas</a></li>
                                                    <li class="menuItem"><a href="page_tips.jsp">Tips</a></li>
                                                    <li class="menuItem"><a href="page_categories.jsp">Categorias</a></li>
                                                    <li class="menuItem"><a href="page_empresas.jsp">Empresas</a></li>
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

            <!--about us-->
            <section class="aboutus" id="aboutus">
                <div class="container">
                    <div class="heading text-center">
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h2>Amantes de la Buena Cocina</h2>
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h3>Entra, conoce y disfruta de las mejores recetas, de amantes de la cocina como tu y sorprende a tu familia con los platos mas innovadores y unicos en su tipo, atrevete a probar.</h3>
                    </div>			
                    <div class="row">
                        <div class="col-md-6">
                            <div class="papers text-center">
                                <img src="http://wowthemes.net/demo/leroy/img/dummies/18.jpg" alt=""><br/>
                                <a href="#"><b>Ver reseña completa</b></a>
                                <h4 class="notopmarg nobotmarg"><%=controller.getReceta_1().getNombrereceta()%></h4>
                                <p>
                                    <%=controller.getReceta_1().getDescripcionreceta()%>
                                </p>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="papers text-center">
                                <img src="http://wowthemes.net/demo/leroy/img/dummies/19.jpg" alt=""><br/>
                                <a href="#"><b>Ver reseña completa</b></a>
                                <h4 class="notopmarg nobotmarg"><%=controller.getReceta_2().getNombrereceta()%></h4>
                                <p>
                                    <%=controller.getReceta_2().getDescripcionreceta()%>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <!--specialties-->
            <section class="specialties" id="specialties">
                <div class="container">
                    <div class="heading text-center">
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h2>Ingresa a Nuestro Portal</h2>
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h3>Miles de recetas y tips innovadores para llevar a tu hogar.</h3>
                        <form action="SesionController" method="post">
                            <table columns="2" style="margin-left: 100px;float: left;margin-top: 0px" class="table table-hover">
                                <div class="form-group">
                                    <label class="col-sm-offset-3 col-md-3 control-label">Nickname *:</label>
                                    <div class="col-sm-4 col-xs-12">
                                        <input name="nickname" type="text" maxlength="30" required="true" placeholder="Nickname" title="Nickname" class="form-control"/>
                                    </div>
                                </div><br/><br/>
                                <div class="form-group">
                                    <label class="col-sm-offset-3 col-md-3 control-label">Password *:</label>
                                    <div class="col-sm-4 col-xs-12">
                                        <input name="pass" type="password" maxlength="30" required="true" placeholder="Password" title="Password" class="form-control"/>
                                    </div>
                                </div><br/><br/>
                                <div class="form-group">
                                    <div class="col-sm-offset-5 col-sm-3 col-xs-12">
                                        <input value="Ingresar" type="submit" title="Ingresar" class="btn btn-warning"/>
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