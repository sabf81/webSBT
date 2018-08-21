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
