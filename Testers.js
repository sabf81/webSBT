var testerDropdown

function getListOfTesters() {
 return ["None","Sven Finsterwalder", "Testing Robot", "Dummy"];
}

function getTesters() {
  var testerArray = getListOfTesters();
  testerDropdown = document.getElementById("TesterDropdown");
  testerDropdown.options.length = 0;
  var fragment = document.createDocumentFragment();
  testerArray.forEach(function(tester, index) {
    var opt = document.createElement('option');
    opt.innerHTML = tester;
    opt.value = tester;
    fragment.appendChild(opt);
  });

  testerDropdown.appendChild(fragment);
}
