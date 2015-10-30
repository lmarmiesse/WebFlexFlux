$("table").addClass("table");
$("table").addClass("table-striped");
$("th").addClass("col-md-2");

/////////////////////// For 2D plot with plotly
$(".2Dplot").each(function(index) {

	var plotName = $(this).attr("data-plotname");
	var xAxisName = $(this).attr("data-xAxisName");
	var yAxisName = $(this).attr("data-yAxisName");

	var divId = "2Dplot" + index

	$(this).before("<div id='" + divId + "' ></div>");

	var xVals = new Array();
	var yVals = new Array();
	$(this).find('a').each(function(index) {

		vals = $(this).text().split(";");

		xVals.push(vals[0]);
		yVals.push(vals[1]);
	});

	var trace1 = {
		x : xVals,
		y : yVals,
		mode : 'lines'
	};
	var data = [ trace1 ];

	var layout = {
		xaxis : {
			ticks : "outside",
			tickcolor : "#000",
			title : xAxisName
		},
		yaxis : {
			ticks : "outside",
			tickcolor : "#000",
			title : yAxisName
		},
		title : plotName,
		'width' : 900,
		'height' : 500
	};

	Plotly.newPlot(divId, data, layout);

});



// google.load('visualization', '1.1', {packages: ['corechart']});
// google.setOnLoadCallback(drawCharts);
//
// function drawCharts() {
//
// $(".2Dplot").each(function(index) {
//		
//		
//		
// var plotName = $(this).attr("data-plotname");
// var xAxisName = $(this).attr("data-xAxisName");
// var yAxisName = $(this).attr("data-yAxisName");
//		
// var divId = "2Dplot"+index
//		
// $(this).before("<div id='"+divId+"' ></div>");
//		
//		
// var rows = new Array();
// $(this).find('a').each(function(index) {
//			
// vals = $(this).text().split(";");
//			
// rows.push([Number(vals[0]),Number(vals[1])]);
// });
//		
// drawChart(divId,plotName,xAxisName,yAxisName,plotName,rows)
//		
// });
// }
//
// function drawChart(divId,plotName,xAxisName,yAxisName,lineName,rows) {
//
// console.log(plotName);
//	
// data = new google.visualization.arrayToDataTable([]);
//    
// console.log(data);
//    
// data.addColumn('number', xAxisName);
// data.addColumn('number', lineName);
//
// data.addRows(rows);
//
// options = {
// chart: {
// title: plotName
// },
// width: 900,
// height: 500
// };
//
// chart = new google.charts.Line(document.getElementById(divId));
//
// chart.draw(data, options);
// }
