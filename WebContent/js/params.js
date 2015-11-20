function getXMLHttpRequest() {
	var xhr = null;

	if (window.XMLHttpRequest || window.ActiveXObject) {
		if (window.ActiveXObject) {
			try {
				xhr = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				xhr = new ActiveXObject("Microsoft.XMLHTTP");
			}
		} else {
			xhr = new XMLHttpRequest();
		}
	} else {
		alert("Votre navigateur ne supporte pas l'objet XMLHTTPRequest...");
		return null;
	}

	return xhr;
}

function displayAnalysisOutuput(text) {

	

	$("#outputPre").text(text);


}

function outputRequest(callback) {
	var xhr = getXMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
			var response = xhr.responseText;
			var json = jQuery.parseJSON(response);

			callback(json.output);
		}
	};

	nb = $("#formNb").val();

	xhr.open("GET", "getFunctionStdOut?nb=" + nb, true);
	xhr.send(null);
}

function cancelRequest(callback) {
	var xhr = getXMLHttpRequest();

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && (xhr.status == 200 || xhr.status == 0)) {
			
			
			
			
//			callback(xhr.responseText);
		}
	};
	nb = $("#formNb").val();
	key = $("#key").val();

	console.log(key);
	
	xhr.open("GET", "getFunctionStdOut?nb="+nb+"&key=" + key, true);
	xhr.send(null);
}


function cancelAnalysis() {
	cancelRequest();
	location.reload();
}



function getAnalysisOutuput(){
	
	$("#outputDiv").show();
	
	outputRequest(displayAnalysisOutuput);
	
//	update every 200ms
	setTimeout(getAnalysisOutuput, 200);
	
}

