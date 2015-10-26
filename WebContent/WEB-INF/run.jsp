<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ AnalysisParameters.analysisName }" /></title>
</head>
<body>

<p><c:out value="${ AnalysisParameters.analysisName }" /></p>

<p><c:out value="${ AnalysisParameters.description }" /></p>

<form method="post" action="run">

<input type="hidden" name="method" value="<c:out value="${ AnalysisParameters.analysisName }" />">

<c:forEach items="${ AnalysisParameters.requiredArguments }" var="argument">
    <c:out value="${ argument.HTMLstring } ${ argument.descr }" escapeXml="false"/><br/>
</c:forEach>

<hr>

<c:forEach items="${ AnalysisParameters.optionalArguments }" var="argument">
    <c:out value="${ argument.HTMLstring } ${ argument.descr }" escapeXml="false"/><br/>
</c:forEach>

<input type="submit" />


</form>

</body>
</html>



