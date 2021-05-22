package br.com.srcsoftware.testservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( "/hello" )
public class HelloServelet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

		String texto = req.getParameter( "texto" );

		PrintWriter html = resp.getWriter();

		html.print( "<!DOCTYPE html>" );
		html.print( "<html>" );
		html.print( "	<head>" );
		html.print( "		<meta charset='ISO-8859-1'/>" );
		html.print( "		<title>Sistema Hello </title>" );
		html.print( "	</head>" );
		html.print( "	<body>" );
		html.print( "		<label>text: </label><span style='font-weight:bold'>" + texto + "</span>" );
		html.print( "	</body>" );
		html.print( "</html>" );

	}

	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

		String texto = req.getParameter( "texto" );

		PrintWriter html = resp.getWriter();

		html.print( "<!DOCTYPE html>" );
		html.print( "<html>" );
		html.print( "	<head>" );
		html.print( "		<meta charset='ISO-8859-1'/>" );
		html.print( "		<title>Sistema Hello </title>" );
		html.print( "	</head>" );
		html.print( "	<body>" );
		html.print( "		<label>texto: </label><span style='font-weight:bold'>" + texto + "</span>" );
		html.print( "	</body>" );
		html.print( "</html>" );

	}

}
