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
<script src="js/params.js"></script>

</head>
<body style="background-color: #f5f5f5;">
	<%@ include file="navbar.jsp"%>

	<div class="container" style="background-color: #ffffff;">

		<h1>
			<c:out value="${ AnalysisParameters.analysisName }" />
		</h1>

		<pre class="normal_pre"><c:out value="${ AnalysisParameters.description }" /></pre>


		<div class="row" id="outputDiv" hidden>

			<h1>The analysis is running ... </h1>
			<p>When it is over, you will be redirected to the results page.</p>

			<h2>Output:</h2>

			<button type="submit" class="btn btn-danger" id="cancel-btn"
				onclick="cancelAnalysis()">Cancel analysis</button>

			<br/>

			<pre id="outputPre" class="function-output"></pre>

		</div>

		<form method="post" action="result" enctype="multipart/form-data">

			<input type="hidden" name="method"
				value="<c:out value="${ AnalysisParameters.analysisName }" />">
				
			<input type="hidden" name="key"
				value="<c:out value="${ key }" />">

			<input type="hidden" name="nb" id="formNb"
				value="<c:out value="${ uniqueNumber }" />"> <input
				type="hidden" name="outputFiles"
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
						<br />
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
						<br />
					</c:forEach>
				</c:if>
			</c:if>

			<button type="submit" class="btn btn-primary btn-lg" id="submit-btn"
				onclick="getAnalysisOutuput()">Submit</button>

		</form>

	</div>

	<script src="js/webFF.js"></script>
</body>
</html>
