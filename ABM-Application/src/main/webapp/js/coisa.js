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

function isTheFieldEmpty(inputField, helpId) {        // inputField – ID Number for the html text box
// helpId – ID Number for the child node I want to print a warning in
// See if the input value contains any text
	return editNodeText(/^[A-Za-z\.\' \-]{2,15}\s?[A-Za-z\.\' \-]{2,15}\s?[A-Za-z\.\' \-]{2,15}/, inputField.value, helpId, "Please enter a valid name.");
}             // inputField.value – Value typed in the html text box

function isAddressOk(inputField, helpId) {        // See if the input value contains any text

	return editNodeText(/^[A-Za-z0-9\.\' \-]{5,30}$/, inputField.value, helpId, "Enter a Street (Ex.1234 Main St.)");
}

function isStateOk(inputField, helpId) {        // See if the input value contains any text
	return editNodeText(/^A[LKSZRAEP]|C[AOT]|D[EC]|F[LM]|G[AU]|HI|I[ADLN]|K[SY]|LA|M[ADEHINOPST]|N[CDEHJMVY]|O[HKR]|P[ARW]|RI|S[CD]|T[NX]|UT|V[AIT]|W[AIVY]$/, 
			inputField.value, helpId, "Enter a State Code in Uppercase (Ex.NY, PA, CA)");
}

function isPhoneOk(inputField, helpId) {        // See if the input value contains any text

	return editNodeText(/^([0-9]( |-)?)?(\(?[0-9]{3}\)?|[0-9]{3})( |-)?([0-9]{3}( |-)?[0-9]{4}|[a-zA-Z0-9]{7})$/, 
			inputField.value, helpId, "Enter a Phone Number (Ex.412-828-3000)");
}

function isEmailOk(inputField, helpId) {        // See if the input value contains any text

	return editNodeText(/^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/, inputField.value, helpId, "Enter an Email (Ex. derekbanas@newthinktank.com)");
}