
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://www.springframework.org/security/tags' prefix='sec'%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="KHAN">
        <meta name="author" content="SAIF">
        <title>Reflection - Landing Page</title>
        <jsp:include page="/views/layouts/_css.jsp"/>
        <!-- Custom styles for this template -->
        <link href="${pageContext.request.contextPath}/resources/site/amtl-site.css" rel="stylesheet">
        <!-- Animation CSS -->
        <link href="${pageContext.request.contextPath}/resources/frameworks/animate.css/animate.css" rel="stylesheet">
    </head>
    <body id="page-top" class="landing-page">

        <jsp:include page="site/_navbar.jsp"/>

        <jsp:include page="site/_carousel.jsp"/>

        <jsp:include page="site/_services.jsp"/>

        <section  class="container features">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="navy-line"></div>
                    <h1>Over 40+ unique view<br/> <span class="navy"> with many custom components</span> </h1>
                    <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 text-center wow fadeInLeft">
                    <div>
                        <i class="fa fa-mobile features-icon"></i>
                        <h2>Full responsive</h2>
                        <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus.</p>
                    </div>
                    <div class="m-t-lg">
                        <i class="fa fa-bar-chart features-icon"></i>
                        <h2>6 Charts Library</h2>
                        <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus.</p>
                    </div>
                </div>
                <div class="col-md-6 text-center  wow zoomIn">
                    <img src="${pageContext.request.contextPath}/resources/site/img/landing/perspective.png" alt="dashboard" class="img-responsive">
                </div>
                <div class="col-md-3 text-center wow fadeInRight">
                    <div>
                        <i class="fa fa-envelope features-icon"></i>
                        <h2>Mail pages</h2>
                        <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus.</p>
                    </div>
                    <div class="m-t-lg">
                        <i class="fa fa-google features-icon"></i>
                        <h2>AngularJS version</h2>
                        <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus.</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="navy-line"></div>
                    <h1>Discover great feautres</h1>
                    <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
                </div>
            </div>
            <div class="row features-block">
                <div class="col-lg-6 features-text wow fadeInLeft">
                    <small>Reflection</small>
                    <h2>Perfectly designed </h2>
                    <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with latest jQuery plugins.</p>
                    <a href="#" class="btn btn-primary">Learn more</a>
                </div>
                <div class="col-lg-6 text-right wow fadeInRight">
                    <img src="${pageContext.request.contextPath}/resources/site/img/landing/dashboard.png" alt="dashboard" class="img-responsive pull-right">
                </div>
            </div>
        </section>

        <jsp:include page="site/_team.jsp"/>

        <jsp:include page="site/_features.jsp"/>

        <section class="features">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <div class="navy-line"></div>
                        <h1>Even more great feautres</h1>
                        <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. </p>
                    </div>
                </div>
                <div class="row features-block">
                    <div class="col-lg-3 features-text wow fadeInLeft">
                        <small>Reflection</small>
                        <h2>Perfectly designed </h2>
                        <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with latest jQuery plugins.</p>
                        <a href="#" class="btn btn-primary">Learn more</a>
                    </div>
                    <div class="col-lg-6 text-right m-t-n-lg wow zoomIn">
                        <img src="${pageContext.request.contextPath}/resources/site/img/landing/iphone.jpg" class="img-responsive" alt="dashboard">
                    </div>
                    <div class="col-lg-3 features-text text-right wow fadeInRight">
                        <small>Reflection</small>
                        <h2>Perfectly designed </h2>
                        <p>Reflection Admin Theme is a premium admin dashboard template with flat design concept. It is fully responsive admin dashboard template built with Bootstrap 3+ Framework, HTML5 and CSS3, Media query. It has a huge collection of reusable UI components and integrated with latest jQuery plugins.</p>
                        <a href="#" class="btn btn-primary">Learn more</a>
                    </div>
                </div>
            </div>

        </section>
                    
        <jsp:include page="site/_timeline.jsp"/>

        <jsp:include page="site/_testimonials.jsp"/>

        <jsp:include page="site/_comments.jsp"/>

        <jsp:include page="site/_features.jsp"/>

        <jsp:include page="site/_pricing.jsp"/>

        <jsp:include page="site/_contact.jsp"/>

        <jsp:include page="/views/layouts/_js.jsp"/>

        <script src="${pageContext.request.contextPath}/resources/frameworks/metisMenu/jquery.metisMenu.js"></script>
        <script src="${pageContext.request.contextPath}/resources/frameworks/pace/pace.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/frameworks/wow/wow.min.js"></script>

        <!-- Custom and plugin javascript -->
        <script src="${pageContext.request.contextPath}/resources/site/inspinia.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {


                $('body').scrollspy({
                    target: '.navbar-fixed-top',
                    offset: 80
                });

                // Page scrolling feature
                $('a.page-scroll').bind('click', function (event) {
                    var link = $(this);
                    $('html, body').stop().animate({
                        scrollTop: $(link.attr('href')).offset().top - 50
                    }, 500);
                    event.preventDefault();
                    $("#navbar").collapse('hide');
                });
            });

            var cbpAnimatedHeader = (function () {
                var docElem = document.documentElement,
                        header = document.querySelector('.navbar-default'),
                        didScroll = false,
                        changeHeaderOn = 200;
                function init() {
                    window.addEventListener('scroll', function (event) {
                        if (!didScroll) {
                            didScroll = true;
                            setTimeout(scrollPage, 250);
                        }
                    }, false);
                }
                function scrollPage() {
                    var sy = scrollY();
                    if (sy >= changeHeaderOn) {
                        $(header).addClass('navbar-scroll')
                    } else {
                        $(header).removeClass('navbar-scroll')
                    }
                    didScroll = false;
                }
                function scrollY() {
                    return window.pageYOffset || docElem.scrollTop;
                }
                init();


            })();

            // Activate WOW.js plugin for animation on scrol
            new WOW().init();


        </script>


    </body>

    <!-- Mirrored from webapplayers.com/inspinia_admin-v2.4/landing.html by HTTrack Website Copier/3.x [XR&CO'2014], Sun, 31 Jan 2016 18:56:35 GMT -->
</html>
