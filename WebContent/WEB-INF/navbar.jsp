<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="flexfluxWebpage"
	value="http://lipm-bioinfo.toulouse.inra.fr/flexflux" scope="page" />

<!-- Docs master nav -->
<header class="navbar navbar-static-top bs-docs-nav" id="top"
	role="banner" style="background-color: #00007C;">
	<div class="container">
		<div class="navbar-header">
			<button class="navbar-toggle collapsed" type="button"
				data-toggle="collapse" data-target="#bs-navbar"
				aria-controls="bs-navbar" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a href="methods" class="navbar-brand"><font
				color="white">FlexFlux</font></a>
		</div>
		<nav id="bs-navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${ flexfluxWebpage }/documentation.html"><font
						color="white">User documentation</font></a></li>
				<li><a href="${ flexfluxWebpage }/installation.html"><font
						color="white">Installation</font></a></li>
				<li><a
					href="https://github.com/lmarmiesse/FlexFlux/wiki/Downloads"><font
						color="white">Download</font></a></li>
				<li><a href="https://github.com/lmarmiesse/FlexFlux"><font
						color="white">Sources</font></a></li>
				<li><a href="mailto:Lucas.Marmiesse@toulouse.inra.fr"><font
						color="white">Contact</font></a></li>
			</ul>
		</nav> 
	</div>
</header>