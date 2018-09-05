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
