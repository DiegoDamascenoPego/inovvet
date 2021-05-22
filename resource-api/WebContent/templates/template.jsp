<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<!-- Scriptlet - permite acesso a linguagem java -->
<%
	request.getSession().setAttribute( "rootweb", request.getContextPath() );
%>

<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="Curso" content="">
<meta name="Diego P�go" content="">

<title>Turma 34</title>

<jsp:include page="imports/imports_css.jsp" />



</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
			<!-- INICIO CABE�ALHO -->
			<tiles:insert name="header"/>
			<!-- FIM CABE�ALHO -->

			<!-- INICIO MENU -->
			<tiles:insert name="menu"/>
			<!-- FIM MENU -->

		</nav>

		<!-- OS JSP DAS TELAS DEVEM SER CRIADAS DENTRO DE DIVS row -->
		<div id="page-wrapper">
		<tiles:insert name="body"/>
			
		</div>
		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<jsp:include page="imports/imports_js.jsp" />

</body>

</html>
