<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<html>
<head>
<title>Spitter</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/style.css" />">
</head>
<body>
	<h1>Register</h1>
	<sf:form method="POST" commandName="spitter">
First Name: <sf:input path="firstName" />
<div class="error">
					<sf:errors path="firstName"></sf:errors>
				</div>
		<br />
Last Name: <sf:input path="lastName" />
<div class="error">
					<sf:errors path="lastName"></sf:errors>
				</div>
		<br />
Email: <sf:input path="email" />
<div class="error">
					<sf:errors path="email"></sf:errors>
				</div>
		<br />
Username: <sf:input path="username" />
<div class="error">
					<sf:errors path="username"></sf:errors>
				</div>
		<br />
Password: <sf:password path="password" />
<div class="error">
					<sf:errors path="password"></sf:errors>
				</div>
		<br />
		<input type="submit" value="Register" />
	</sf:form>
</body>
</html>
