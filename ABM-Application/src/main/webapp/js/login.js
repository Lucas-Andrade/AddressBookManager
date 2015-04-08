
	function logTheUserIn(username, password) {
		
		window.alert("coisas");
		var xmlHttp = new XMLHttpRequest();
		//var path = "v1/users/login/" + username + "/" + password;
		var path = "https://www.google.pt";
		xmlHttp.open("GET", path, true);
		
//		xmlHttp.onreadystatechange = function() {
//			var data = JSON.parse(xmlHttp.responseText);
//			window.alert(xmlHttp.responseText);
//		}
		
	    xmlHttp.send();
	}

//	function logToUserIn(username, password) {
//		
//		var xmlHttp = new XMLHttpRequest();
//		var path = "v1/users/utilizador/persons";
//		xmlHttp.open("GET", path, false);
//	}