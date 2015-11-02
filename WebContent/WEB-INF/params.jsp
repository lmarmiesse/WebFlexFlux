<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><c:out value="${ AnalysisParameters.analysisName }" /></title>


<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/webFF.css" rel="stylesheet">
<script src="js/jquery-1.11.3.min.js"></script>
<script src="js/bootstrap.js"></script>

</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="navbar.jsp"%>

	<div class="container" style="background-color: #ffffff;">

		<h1>
			<c:out value="${ AnalysisParameters.analysisName }" />
		</h1>

		<pre class="normal_pre"><c:out value="${ AnalysisParameters.description }" /></pre>

		<form method="post" action="result" enctype="multipart/form-data">

			<input type="hidden" name="method"
				value="<c:out value="${ AnalysisParameters.analysisName }" />">

			<input type="hidden" name="outputFiles"
				value="<c:out value="${ AnalysisParameters.outputFilesArguments }"/>" />

			<c:if
				test="${fn:length(AnalysisParameters.requiredArguments) gt 0 || fn:length(AnalysisParameters.optionalArguments) gt 0}">

				<h2>Arguments</h2>

				<c:if test="${fn:length(AnalysisParameters.requiredArguments) gt 0}">

					<h3>Required arguments</h3>

					<c:forEach items="${ AnalysisParameters.requiredArguments }"
						var="argument">

						<div class="row">
							<div class="col-md-2">
								<c:out value="${ argument.name }" />
							</div>
							<div class="col-md-3">
								<c:out value="${ argument.HTMLstring }" escapeXml="false" />
							</div>
							<div class="col-md-6">
								<c:out value="${ argument.descr }" />
							</div>
						</div>


					</c:forEach>
				</c:if>



				<c:if test="${fn:length(AnalysisParameters.optionalArguments) gt 0}">
					<hr>
					<h3>Optinal arguments</h3>

					<c:forEach items="${ AnalysisParameters.optionalArguments }"
						var="argument">
						<div class="row">
							<div class="col-md-2">
								<c:out value="${ argument.name }" />
							</div>
							<div class="col-md-3">
								<c:out value="${ argument.HTMLstring }" escapeXml="false" />
							</div>
							<div class="col-md-6">
								<c:out value="${ argument.descr }" />
							</div>
						</div>
					</c:forEach>
				</c:if>
			</c:if>

			<input type="submit" />


		</form>

	</div>

</body>
</html>



