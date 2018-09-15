var charterDropdown

function loadCharterDropdown(){
	  fetch('/rest/charter/all/TODO')
	  .then(function(response) {
	    if(response.ok){
	      return response.json()
	    }
	    throw new Error('Network response was not ok.');
	  })
	  .then(function(myJson) {
		  charterDropdown = document.getElementById("CharterDropdown");
		  charterDropdown.options.length = 0;
		    var fragment = document.createDocumentFragment();
		    var opt = document.createElement('option');
		    opt.innerHTML = "None"
		    opt.value = ""
		    fragment.appendChild(opt);
		    for (entry in myJson) {
		      var opt = document.createElement('option');
		      console.log(myJson[entry]['chartername']);
		      opt.innerHTML = myJson[entry]['chartername'];
		      opt.value = myJson[entry]['id'];
		      fragment.appendChild(opt);
		    }
		    charterDropdown.appendChild(fragment);
		    var size = charterDropdown.length -1;
		    document.getElementById("CharterHeader").innerHTML ="Please select one of "+ size +" Charters:";
	  })
	  .catch(function(error) {
	  console.log('There has been a problem with your fetch operation: ', error.message);
	  });
	}

function addNewCharter(){
	var charterData = document.getElementById("inputNewCharter").value
	fetch('rest/charter/add/new', {
		  method: 'POST',
		  body: "charterName="+charterData,
		  headers: {
	             "Content-Type": "application/x-www-form-urlencoded",
	        },
		})
		.then(response => response.text())
		.catch(error => console.error('Error:', error))
		.then(function(response) {
			console.log('Success:', response);
			loadCharterWithStatusInTableRow('TODO');
		});
}

function removeCharter(){
  var Ids = getIdsToRemoveCharter();
  deleteCharterData(Ids, 'rest/charter/remove');
}

function getIdsToRemoveCharter(){
  var selected = [];
  $("table td :checkbox").each(function() {
    if($(this).is(':checked')){
      selected.push(this.id);
      console.log(this.id);
    }

  });
  return selected;
}

function deleteCharterData(item, url) {
  return fetch(url + '/' + item, {
    method: 'delete'
  })
  .then(function(response) {
    return response.text();
  })
  .catch(error => console.error('Error:', error))
  .then(function(text) {
  	console.log(text);
  	loadCharterWithStatusInTableRow('TODO');
  });
};

function loadCharterWithStatusInTableRow(status){
	  fetch('/rest/charter/all/'+status)
	  .then(function(response) {
	    if(response.ok){
	      return response.json()
	    }
	    throw new Error('Network response was not ok.');
	  })
	  .then(function(myJson) {
	    var table = document.getElementById("CharterTableBody");
      while (table.firstChild) table.removeChild(table.firstChild);
	    for(i in myJson){
	      var row = table.insertRow(0);
	      var idCell = row.insertCell(0)
	      var charterName = row.insertCell(1);
	      var charterStatus = row.insertCell(2);
	      var chkbox = document.createElement('input');
	      chkbox.type = "checkbox";
	      chkbox.id = myJson[i]['id'] ;
	      chkbox.name = myJson[i]['id'] ;
	      idCell.appendChild(chkbox);
	      charterName.innerHTML = myJson[i]['chartername'];
	      charterStatus.innerHTML = myJson[i]['status'];
	    }
	  })
	  .catch(function(error) {
		  console.log('There has been a problem with your fetch operation: ', error.message);
	  });
	}
