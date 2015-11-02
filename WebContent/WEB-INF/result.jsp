<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/webFF.css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.js"></script>

<!-- <script type="text/javascript" src="https://www.google.com/jsapi"></script> -->

<!-- D3.js -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js"></script>

<!-- Plotly.js -->
  <script src="https://d14fo0winaifog.cloudfront.net/plotly-basic.js"></script>

</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="navbar.jsp"%>

	<div class="container" style="background-color: #ffffff;">

		<h1>${ analysisName } result</h1>

		
		<c:if test="${fn:length(fileNames) gt 0}">
		<h2>Output files:</h2>
		<p>
			<c:forEach items="${ fileNames }" var="name">
				<a
					href="<c:url value="${'files?method='}${analysisName}${'&nb='}${uniqueNumer}${'&file='}${name}"/>"><c:out
						value="${ name }" /></a>
			</c:forEach>
		</p>
		</c:if>

		<c:if test="${htmlData != ''}">
			<h2>Results:</h2>
			<c:out value="${htmlData}" escapeXml="false" />
		</c:if>
		
		<c:if test="${log != ''}">
			<h2>Function output:</h2>
			<pre id="log"><c:out value="${log}" escapeXml="false" /></pre>
		</c:if>


	</div>

	<script src="js/result.js"></script>
</body>
</html>



