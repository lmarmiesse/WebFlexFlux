<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FlexFlux analyses</title>
</head>
<body>

<ul>
<c:forEach items="${ analysesNames }" var="analysis">
    <li><a href="<c:url value="${'parameters?method='}${analysis}"/>" ><c:out value="${ analysis }" /></a></li>
</c:forEach>
</ul>



</body>
</html>



