function dateValidation(dateInput) {

  var date = dateInput;
  if(date == '')
    return false;
   
  //Declare Regex 
  var regexDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
  var dateArray = currVal.match(regexDatePattern); // is format OK?
  if (dateArray == null)
     return false;
  //Checks for mm/dd/yyyy format.
  dateMonth = dateArray[1];
  dateDay= dateArray[3];
  dateYear = dateArray[5];
  if (dateMonth < 1 || dateMonth > 12)
      return false;
  else if (dateDay < 1 || dateDay> 31)
      return false;
  else if ((dateMonth==4 || dateMonth==6 || dateMonth==9 || dateMonth==11) && dateDay==31)
      return false;
  else if (dateMonth == 2) {
     var isleap = (dateYear % 4 == 0 && (dateYear % 100 != 0 || dateYear % 400 == 0));
     if (dateDay> 29 || (dateDay ==29 && !isleap))
          return false;
  }
  return true;
}

$(function() {
	$('#createButton').bind('click', function(){
		var computerNameInput = new RegExp("[!@#$%^&*()<>]");
	
		if (computerNameInput.test($("#computerName").val())) {
			alert("Warning: the computer's name must not contain special characters!")
		}
	    var dateInput =  $('#introductionDate').val();
	    var dateInput2 = $('#discontinuationDate').val();
	    if(!dateValidation(dateInput))
	        alert('Invalid Introduction Date');
	    if(!dateValidation(dateInput2))
	        alert('Invalid Discontinuation Date');
	})
});
