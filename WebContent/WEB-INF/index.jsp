<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FlexFlux analyses</title>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/webFF.css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.js"></script>

</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="navbar.jsp"%>

	<div class="container" style="background-color: #ffffff;">
	
	
	<h1>Available FlexFlux functions:</h1>

		<ul>
			<c:forEach items="${ analysesNames }" var="analysisName">
				<li><a
					href="<c:url value="${'parameters?method='}${analysisName}"/>"><h4><c:out
							value="${ analysisName }" /></h4></a>

					<pre class="normal_pre"><c:out value="${ analysesDescriptions[analysisName] }" /></pre></li>
			</c:forEach>
		</ul>

	</div>

</body>
</html>



