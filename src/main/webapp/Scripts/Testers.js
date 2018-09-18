var testerDropdown

function loadTesterDropdown(){
  fetch('/rest/tester/all')
  .then(function(response) {
    if(response.ok){
      return response.json()
    }
    throw new Error('Network response was not ok.');
  })
  .then(function(myJson) {
    testerDropdown = document.getElementById("TesterDropdown");
    testerDropdown.options.length = 0;
    var fragment = document.createDocumentFragment();
    var opt = document.createElement('option');
    opt.innerHTML = "None"
    opt.value = ""
    fragment.appendChild(opt);
    for (entry in myJson) {
      var opt = document.createElement('option');
      console.log(myJson[entry]['firstname'].concat(' ',myJson[entry]['lastname']));
      opt.innerHTML = myJson[entry]['firstname'].concat(' ',myJson[entry]['lastname']);
      opt.value = myJson[entry]['id'];
      fragment.appendChild(opt);
    }
    testerDropdown.appendChild(fragment);
  })
  .catch(function(error) {
  console.log('Testers.js: There has been a problem with your fetch operation: ', error.message);
  });
}

function loadTesterTableRow(){
  fetch('/rest/tester/all')
  .then(function(response) {
    if(response.ok){
      return response.json()
    }
    throw new Error('Network response was not ok.');
  })
  .then(function(myJson) {
    var table = document.getElementById("TesterTabeleBody");
    while (table.firstChild) table.removeChild(table.firstChild);
    for(i in myJson){
      var row = table.insertRow(0);
      var idCell = row.insertCell(0)
      var firstnameCell = row.insertCell(1);
      var lastnameCell = row.insertCell(2);
      var emailCell = row.insertCell(3);
      var chkbox = document.createElement('input');
      chkbox.type = "checkbox";
      chkbox.id = myJson[i]['id'] ;
      chkbox.name = myJson[i]['id'] ;
      idCell.appendChild(chkbox);
      firstnameCell.innerHTML = myJson[i]['firstname'];
      lastnameCell.innerHTML = myJson[i]['lastname'];
      emailCell.innerHTML = myJson[i]['email'];
    }
  })
  .catch(function(error) {
	  console.log('Testers.js: There has been a problem with your fetch operation: ', error.message);
  });
}

function removeTester(){
  var Ids = getIdsToRemoveTester();
  deleteTesterData(Ids,'rest/tester/remove')
}

function getIdsToRemoveTester(){
  var selected = [];
  $("table td :checkbox").each(function() {
    if($(this).is(':checked')){
      selected.push(this.id);
      console.log(this.id);
    }
  });
  return selected;
}

function deleteTesterData(item, url) {
  return fetch(url + '/' + item, {
    method: 'delete'
  })
  .then(function(response) {
    return response.text();
  })
  .catch(error => console.error('Error:', error))
  .then(function(text) {
  	console.log(text);
  	loadTesterTableRow();
  });
};
