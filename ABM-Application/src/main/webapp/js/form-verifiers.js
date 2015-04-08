
function editNodeText(regex, input, helpId, helpMessage){        // See if the visitor entered the right information

	if (!regex.test(input)) {          // If the wrong information was entered, warn them
		if (helpId != null)
			while (helpId.firstChild) // Remove any warnings that may exist
				helpId.removeChild(helpId.firstChild);
		helpId.appendChild(document.createTextNode(helpMessage)); // Add new warning
		return false;
	}
	else {          // If the right information was entered, clear the help message
		if (helpId != null){
			while (helpId.firstChild) // Remove any warnings that may exist
				helpId.removeChild(helpId.firstChild);
		}
		return true;
	}
}

function isTheFieldEmpty(inputField, helpId) {
	return editNodeText(/^[A-Za-z\.\' \-]{2,15}\s?[A-Za-z\.\' \-]{2,15}\s?[A-Za-z\.\' \-]{2,15}/, inputField.value, helpId, "Please enter a valid name.");
}

function isPasswordOk(inputField, helpId) {
	return editNodeText(/^[A-Za-z\.\' \-]{2,15}\s?[A-Za-z\.\' \-]{2,15}\s?[A-Za-z\.\' \-]{2,15}/, inputField.value, helpId, "Please enter at least four characters.");
}

function isAddressOk(inputField, helpId) {

	return editNodeText(/^[A-Za-z0-9\.\' \-]{5,30}$/, inputField.value, helpId, "Enter a Street (Ex.Main St. 123 City)");
}

function isPhoneOk(inputField, helpId) {

	return editNodeText(/^([0-9]( |-)?)?(\(?[0-9]{3}\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})$/, 
			inputField.value, helpId, "Enter a Phone Number - no spaces or letters (Ex.412828300)");
}

function isEmailOk(inputField, helpId) {

	return editNodeText(/^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/, inputField.value, helpId, "Enter a valid Email (Ex. yourname@example.com)");
}