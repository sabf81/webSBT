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
		.then(function(response) {
		  if(response.ok){
		      return response.text();
		    }
		  throw new Error('Network response was not ok. '+response.text());
		})
		.catch(error => console.error('Areas.js: Error:', error))
		.then(function(response) {
			console.log('Success:', response);
			
		});
}

function updateArea(){
	var areaId = document.getElementById("AreaDropdown").value
	var subAreaData = document.getElementById("inputSubAreaEdit").value
	
	return fetch(url + '/' + areaId, {
	    method: 'PUT',
	    body: "subAreas="+subAreaData,
	    headers: {
            "Content-Type": "application/x-www-form-urlencoded",
	    },
	  })
	  .then(function(response) {
		  if(response.ok){
		      return response.text();
		    }
		  throw new Error('Network response was not ok. '+response.text());
	  })
	  .catch(error => console.error('Areas.js: Error:', error))
	  .then(function(text) {
	  	console.log(text);
	  	
	  });
}

function getAreas(){
	fetch('/rest/areas/all')
	  .then(function(response) {
	    if(response.ok){
	      return response.json()
	    }
	    throw new Error('Network response was not ok.');
	  })
	  .then(function(myJson) {
		  areaDropdown = document.getElementById("AreaDropdown");
		  areaDropdown.options.length = 0;
		    var fragment = document.createDocumentFragment();
		    var opt = document.createElement('option');
		    opt.innerHTML = "None"
		    opt.value = ""
		    fragment.appendChild(opt);
		    for (entry in myJson) {
		      var opt = document.createElement('option');		      
		      opt.innerHTML = myJson[entry]['area'];
		      opt.value = myJson[entry]['id'];
		      fragment.appendChild(opt);
		    }
		    areaDropdown.appendChild(fragment);
	  })
	  .catch(function(error) {
		  console.log('Areas.js: There has been a problem with your fetch operation: ', error.message);
	  });
}

function loadSubareas(){
	areaValue = document.getElementById("AreaDropdown").value;
	alert(areaValue);
	fetch('/rest/areas/'+areaValue+'/subareas')
	  .then(function(response) {
	    if(response.ok){
	      return response.json()
	    }
	    throw new Error('Network response was not ok.');
	  })
	  .then(function(myJson) {
		  inputSubAreaEdit = document.getElementById("inputSubAreaEdit");
		  inputSubAreaEdit.value ="";
		  inputSubAreaEdit.value = myJson

	  })
	  .catch(function(error) {
		  console.log('Areas.js: There has been a problem with your fetch operation: ', error.message);
	  });
}