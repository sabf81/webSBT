var charterDropdown

function getListOfCharters() {
 return ["Teste die Slotession im Playtech EGB",
          "Teste den RealityCheck im EGB Playtech System",
          "Teste den stdl. Warnmeldung im EGB Playtech System",
          "Teste die Fehlermeldungen im EGB Playtech System"];
}

function getCharters() {
  var charterArray = getListOfCharters();
  charterDropdown = document.getElementById("CharterDropdown");
  charterArray.forEach(createDropdownElement);
}

function createDropdownElement(value) {
  console.log(value)
  var option = document.createElement("option");
  option.text = value;
  charterDropdown.add(option);
}

function getSizeOfCharter(){
  var charterArray = getListOfCharters();
  document.getElementById("CharterHeader").innerHTML ="Please select one of "+ charterArray.length +" Charters:";
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
			console.log('Success:', response)
			loadCharterWithStatusInTableRow('TODO')
		});
}

function removeCharter(){
  var Ids = getIdsToRemoveCharter();
  deleteData(Ids, 'rester/charter/remove');
  deleteTableRow();
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

function deleteData(item, url) {
  return fetch(url + '/' + item, {
    method: 'delete'
  })
  .then(function(response) {
    return response.text();
  }).then(function(text) {
  	console.log(text);
  });
};

function deleteTableRow(){
  var allRows = document.getElementById('CharterTableBody').getElementsByTagName('tr');
  var root = allRows[0].parentNode;
  var allInp = root.getElementsByTagName('input');
  for(var i=allInp.length-1;i>=0;i--){
  	if((allInp[i].getAttribute('type')=='checkbox')&&(allInp[i].checked)){
  		root.removeChild(allInp[i].parentNode.parentNode)
  	}
  }
}

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
