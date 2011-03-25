<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"></meta>
<title>EduCloud</title>
<jsp:include page="scripts.jsp" />
</head>
<body>
<div id="container">
<jsp:include page="header.jsp" />
<div id="content">
<form action="newvm.jsp" method="post">
	<fieldset> 
	
		<legend>Create new virtual machine</legend>
		
		<label for="login">Name:</label> 
		<input type="text" id="name" name="name"/> 
		<div class="clear"></div> 
		
		<label for="description">Description:</label>
		<textarea rows="5" cols="60" id="description" name="description"></textarea>
		<div class="clear"></div>
		
		<label for="template">Template:</label> 
		<select id="template" name="template">
			<option value="">Chose a template...</option>
			<!-- put template options here -->
		</select>
		<div class="clear"></div> 
		 
		<br /> 
		
		<input type="submit" style="margin: 10px 0 0 125px;" class="button" name="commit" value="Create"/>	
	</fieldset> 
</form>
</div>
<jsp:include page="footer.jsp" />
</div>
</body>
</html>
