function addNewAreas(){
	var areaData = document.getElementById("inputArea").value
	var subAreaData = document.getElementById("inputSubArea").value
	
	fetch('rest/areas/add/new', {
		  method: 'POST',
		  body: "area="+areaData+"&subAreas="+subAreaData,
		  headers: {
	             "Content-Type": "application/x-www-form-urlencoded",
	        },
		})
		.then(response => response.text())
		.catch(error => console.error('Error:', error))
		.then(function(response) {
			console.log('Success:', response);
			
		});
}