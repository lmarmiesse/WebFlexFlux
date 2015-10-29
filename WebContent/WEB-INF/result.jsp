<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link href="css/bootstrap.css" rel="stylesheet">
<script src="js/bootstrap.js"></script>
<script src="js/jquery-1.11.3.min.js"></script>

</head>
<body>
	<%@ include file="navbar.jsp"%>

	<div class="container" style="background-color: #ffffff;">

		<p>Analysis over</p>


		<c:forEach items="${ fileNames }" var="name">

			<div class="row">

				<a
					href="<c:url value="${'files?method='}${analysisName}${'&nb='}${uniqueNumer}${'&file='}${name}"/>"><c:out
						value="${ name }" /></a>

			</div>
		</c:forEach>


	</div>
</body>
</html>



