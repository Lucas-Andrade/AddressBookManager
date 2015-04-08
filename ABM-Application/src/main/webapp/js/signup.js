
	function signTheUserIn(username, password, email) {
		
		var xmlHttp = new XMLHttpRequest();
		var path = "v1/users/login/" + username + "/" + password + "/" + email;
		xmlHttp.open("POST", path, true);
		
//		xmlHttp.onreadystatechange = function() {
//			var data = JSON.parse(xmlHttp.responseText);
//			window.alert(xmlHttp.responseText);
//		}
		
	    xmlHttp.send();
	}
	