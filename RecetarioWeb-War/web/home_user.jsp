<%-- 
    Document   : home_user
    Created on : 15/04/2015, 10:51:19 PM
    Author     : Juan Sebastian Vega
--%>

<%@page import="com.RecetarioWeb.Controller.Client"%>
<%@page import="com.RecetarioWeb.Controller.ControllerReceta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%Client client = Client.getInstace(); %>
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
                                                    <li class="menuItem"><a href="form_tip.jsp">Registrar Tip</a></li>
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

            <!--specialties-->
            <section class="specialties" id="specialties">
                <div class="container">
                    <div class="heading text-center">
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h2>Te Damos la Bienvenida</h2>
                        <h2><%=client.getNickname()%></h2>
                        <img class="dividerline" src="img/sep.png" alt="">
                        <h3>Amantes de la buena cocina es un portal de recetas de cocina sencillas, pensadas para que todos los que no quieran complicarse la vida en la cocina obtengan platos resultones y gustosos. En cada receta encontrara la lista de ingredientes, instrucciones para elaborarla y un foro donde podra compartir nuevas recetas.</h3>
                    </div>
                </div>
            </section>

            <!--gallery-->
            <section class="gallery" id="gallery">
                <div class="container">
                    <div id="grid-gallery" class="grid-gallery">
                        <section class="grid-wrap">
                            <ul class="grid">
                                <li class="grid-sizer"></li><!-- for Masonry column width -->				
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/10.jpg" alt=""/>
                                        <figcaption><h3>Arroces</h3><p>Semilla de la planta Oryza sativa. Se trata de un cereal considerado alimento basico en muchas culturas culinarias, asi como en algunas partes de America Latina. El arroz es el segundo cereal mas producido en el mundo, tras el maiz.</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/11.jpg" alt=""/>
                                        <figcaption><h3>Pie de Frutas</h3><p>Mezcla de deliciosas frutas colocadas sobre pasta quebrada y azucar despolvoreado.</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/12.jpg" alt=""/>
                                        <figcaption><h3>Pizza</h3><p>Pan plano horneado, cuya base es elaborada con harina de trigo, sal, agua y levadura. Generalmente es cubierto por salsa de tomate u otros ingredientes como salami, champiñones, cebolla, jamon, aceitunas, entre otros</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/13.jpg" alt=""/>
                                        <figcaption><h3>Torta de Mani</h3><p>Delicioso ponque bañado en vino blanco con cobertura de crema y mani. </p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/14.jpg" alt=""/>
                                        <figcaption><h3>Copas de Chocolate</h3><p>Chocolate en forma de copa para decorar y obsequiar en cualquier ocasion.</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/15.jpg" alt=""/>
                                        <figcaption><h3>Merengue</h3><p>Suspiro o meringue es un tipo de postre, hecho con clara de huevo batida y azucar, preferiblemente glas, a los que se le puede añadir aromatizantes, como vainilla, coco, almendras. Son muy ligeros y dulces</p></figcaption>
                                    </figure>
                                </li>

                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/16.jpg" alt=""/>
                                        <figcaption><h3>Cupcakes</h3><p>Pequeña porcion de tarta para una persona. Se hornean en un molde igual que el de magdalenas y muffins. En el molde se colocan unos papeles llamados capsulas</p></figcaption>
                                    </figure>
                                </li>

                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/17.jpg" alt=""/>
                                        <figcaption><h3>Vino</h3><p>"Que mejor compañia que una copa de vino para una deliciosa cena"</p></figcaption>
                                    </figure>
                                </li>

                            </ul>
                        </section><!-- // end small images -->

                        <section class="slideshow">
                            <ul>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/10.jpg" alt=""/>
                                        <figcaption><h3>Arroces</h3><p>Semilla de la planta Oryza sativa. Se trata de un cereal considerado alimento basico en muchas culturas culinarias, asi como en algunas partes de America Latina. El arroz es el segundo cereal mas producido en el mundo, tras el maiz.</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/11.jpg" alt=""/>
                                        <figcaption><h3>Pie de Frutas</h3><p>Mezcla de deliciosas frutas colocadas sobre pasta quebrada y azucar despolvoreado.</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/12.jpg" alt=""/>
                                        <figcaption><h3>Pizza</h3><p>Pan plano horneado, cuya base es elaborada con harina de trigo, sal, agua y levadura. Generalmente es cubierto por salsa de tomate u otros ingredientes como salami, champiñones, cebolla, jamon, aceitunas, entre otros</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/13.jpg" alt=""/>
                                        <figcaption><h3>Torta de Mani</h3><p>Delicioso ponque bañado en vino blanco con cobertura de crema y mani. </p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/14.jpg" alt=""/>
                                        <figcaption><h3>Copas de Chocolate</h3><p>Chocolate en forma de copa para decorar y obsequiar en cualquier ocasion.</p></figcaption>
                                    </figure>
                                </li>
                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/15.jpg" alt=""/>
                                        <figcaption><h3>Merengue</h3><p>Suspiro o meringue es un tipo de postre, hecho con clara de huevo batida y azucar, preferiblemente glas, a los que se le puede añadir aromatizantes, como vainilla, coco, almendras. Son muy ligeros y dulces</p></figcaption>
                                    </figure>
                                </li>

                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/16.jpg" alt=""/>
                                        <figcaption><h3>Cupcakes</h3><p>Pequeña porcion de tarta para una persona. Se hornean en un molde igual que el de magdalenas y muffins. En el molde se colocan unos papeles llamados capsulas</p></figcaption>
                                    </figure>
                                </li>

                                <li>
                                    <figure>
                                        <img src="http://wowthemes.net/demo/leroy/img/dummies/17.jpg" alt=""/>
                                        <figcaption><h3>Vino</h3><p>"Que mejor compañia que una copa de vino para una deliciosa cena"</p></figcaption>
                                    </figure>
                                </li>
                            </ul>
                            <nav>
                                <span class="icon nav-prev"></span>
                                <span class="icon nav-next"></span>
                                <span class="icon nav-close"></span>
                            </nav>
                            <div class="info-keys icon">Navigate with arrow keys</div>
                            <center><form action="LogoutServlet" method="post">
                                    <input type="submit" value="SALIR" title="SALIR" class="btn btn-warning"/>
                                </form></center>
                        </section><!-- // end slideshow -->
                    </div><!-- // grid-gallery -->
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