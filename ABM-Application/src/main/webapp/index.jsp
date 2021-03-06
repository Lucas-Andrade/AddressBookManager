<html>

<head>
	<title>Address Book Manager</title>
	<script src="js/form-verifiers.js"></script>
	<script src="js/login.js"></script>
</head>

<body>
    
    <noscript> <h3> This website requires Java Script. </h3> </noscript>
    
    <h2>Welcome to Address Book Manager</h2>
    <p>Please, login below or
    <a href="user-signup.html"><input type="button" name="signup" id="signup" value="Sign Up"></a>
    </p>
    
    <form id="loginForm" name="loginForm" action="#" method="GET">
		
		<table>
			<tr> 
				<td> Username: </td>
			    <td> <input type="text" name="username" id="username" maxlength="255" onBlur="isTheFieldEmpty(this, document.getElementById('name_help'))"> 
			    <span id="name_help"></span> </td>
		    </tr>
		    
		    <tr> 
				<td> Password: </td>
			    <td> <input type="password" name="password" id="password" maxlength="255" onBlur="isPasswordOk(this, document.getElementById('password_help'))"> 
			    <span id="password_help"></span> </td>
		    </tr>
		    
		    <tr> 
				<td> <button onclick="getParams()">Log In</button> </td>
			</tr>
	    </table>
		
	</form>
    
    <script>
		function getParams() {
		    var x = document.forms[0];
		    var username = x.elements[0].value;
		    var password = x.elements[1].value;
		    logTheUserIn(username, password);
	    }
	</script>

</body>
</html>
