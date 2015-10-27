<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<p>

<c:choose>
  <c:when test="${process.isAlive()}">
    Pas fini
  </c:when>

  <c:otherwise>
	Fini
  </c:otherwise>
</c:choose>

</p>


</body>
</html>



