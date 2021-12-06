<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en" class="no-js">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>불IT났어</title>
		<meta name="description" content="Modern effects and styles for off-canvas navigation with CSS transitions and SVG animations using Snap.svg" />
		<meta name="keywords" content="sidebar, off-canvas, menu, navigation, effect, inspiration, css transition, SVG, morphing, animation" />
		<meta name="author" content="Codrops" />
		<link rel="shortcut icon" href="../favicon.ico">
		<link rel="stylesheet" type="text/css" href="/np/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="/np/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="/np/css/font.css" />
		<link rel="stylesheet" type="text/css" href="/np/css/menu_topside.css" />
		<!--[if IE]>
  		<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		
		<style type="text/css">
		h3 {
			margin : 20px;
		}
		#container_tot {
		    height: 525px;
		    border: 2px solid blue;
		    margin : auto 20px;
		}
		#container_gas {
		    height: 230px;
		    border: 2px solid red;
		    margin : auto 20px;
		}
		#container_temp {
		    height: 230px;
		    border: 2px solid purple;
		    margin : auto 20px;
		}

		.ex-layout{ height: 100%;}
		.menu{
			width: 100%;
			height: 30px;
			border:2px solid #09c;
			background: #d7f5ff;
		}
		.main .left-menu{
			float: left;
			width: 50%;
			height: 500px;
		}
		.main .contents {
			float: left;
			width: 50%;
			height: 250px;
		}
		
		.main .contents .arcticle{
			height: 200px;
			
		}
		.main .contents .comment{
			height: 200px;
			
		}
	
		</style>
	</head>
	<body>
		<div class="container">
			<div class="menu-wrap">
				<nav class="menu-top">
					<div class="profile"><img src="img/user1.png" alt="Matthew Greenberg"/><span>Matthew Greenberg</span></div>
					<div class="icon-list">
						<a href="#"><i class="fa fa-fw fa-star-o"></i></a>
						<a href="#"><i class="fa fa-fw fa-bell-o"></i></a>
						<a href="#"><i class="fa fa-fw fa-envelope-o"></i></a>
						<a href="#"><i class="fa fa-fw fa-comment-o"></i></a>
					</div>
				</nav>
				<nav class="menu-side">
					<a href="rchartgaspage.mc">Recent Data</a>
					<a href="#">Accumulated Data</a>
					<a href="#">Iot Coverage</a>
					<a href="#">About Us</a>
				</nav>
			</div>
			<button class="menu-button" id="open-button">Open Menu</button>
			<div class="content-wrap">
				<div class="content">
					<header class="codrops-header">
						<div class="codrops-links">
							<a class="codrops-icon codrops-icon-prev" href="http://tympanus.net/Development/TabStylesInspiration/"><span>Previous Demo</span></a>
							<a class="codrops-icon codrops-icon-drop" href="http://tympanus.net/codrops/?p=20100"><span>Back to the Codrops Article</span></a>
						</div>
						<h1>불IT났어<span>Showing (off-canvas) menus stylishly</span></h1>
						<p>Based on the <a href="https://dribbble.com/shots/1663008-Old-Designspiration-Menu-Concept">Dribble shot by Michael Martinho</a></p>
					</header>
					<!-- Related demos -->
					<!-- <section class="related">
						<p>If you enjoyed this demo you might also like:</p>
						<a href="http://tympanus.net/Development/SidebarTransitions/">
							<img src="img/related/sidebartransitions.png" />
							<h3>Transitions for Off-Canvas Navigations</h3>
						</a>
						<a href="http://tympanus.net/Development/PerspectivePageViewNavigation/">
							<img src="img/related/PerspectiveNavigation.png" />
							<h3>Perspective Page View Navigation</h3>
						</a>
					</section> -->
					
					
						<%-- <c:choose>
							<c:when test="${center == null }">
								<jsp:include page="chart_view/rchartgas.jsp"/>
							</c:when>
							<c:otherwise>
								<jsp:include page="chart_view/rchartgas.jsp"/>
							</c:otherwise>
						</c:choose> --%>
						
						<div class="ex-layout">
							<div class="main">
								<div class="left-menu">
									<jsp:include page="chart_view/rcharttot.jsp"/>	
								</div>
								<div class="contents">
									<div class="article">
										 <jsp:include page="chart_view/rchartgas.jsp"/> 
									</div>
									<div class="comment">
										<jsp:include page="chart_view/rcharttemp.jsp"/>
									</div>
							   </div>
							</div>
						</div>	
				</div>
			</div><!-- /content-wrap -->
		</div><!-- /container -->
		<script src="/np/js/classie.js"></script>
		<script src="/np/js/main.js"></script>
	</body>
</html>