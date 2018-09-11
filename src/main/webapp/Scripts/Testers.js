var testerDropdown

function getTesters(){
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
  console.log('There has been a problem with your fetch operation: ', error.message);
  });
}

function getAllTesterWithEmail(){
  fetch('/rest/tester/all')
  .then(function(response) {
    if(response.ok){
      return response.json()
    }
    throw new Error('Network response was not ok.');
  })
  .then(function(myJson) {
    var table = document.getElementById("TesterTabeleBody");
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
	  console.log('There has been a problem with your fetch operation: ', error.message);
  });
}

function removeTester(){
  var Ids = getIdsToRemoveTester();
  deleteData(Ids,'rest/tester/remove')
  deleteTableRow();

}

function deleteTableRow(){
  var allRows = document.getElementById('TesterTabeleBody').getElementsByTagName('tr');
  var root = allRows[0].parentNode;
  var allInp = root.getElementsByTagName('input');
  for(var i=allInp.length-1;i>=0;i--){
  	if((allInp[i].getAttribute('type')=='checkbox')&&(allInp[i].checked)){
  		root.removeChild(allInp[i].parentNode.parentNode)
  	}
  }
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
