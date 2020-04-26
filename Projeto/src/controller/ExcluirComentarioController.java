package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ComentarioService;


/**
 * Servlet implementation class ExcluirComentarioController
 */
@WebServlet("/ExcluirComentario.do")
public class ExcluirComentarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=ISO-8859-1");
		int idNoticia = Integer.parseInt( request.getParameter("idNoticia") );
		int idComentario = Integer.parseInt( request.getParameter("idComentario") );
		
		ComentarioService cs = new ComentarioService();
		cs.excluir(idComentario);
		out.println("<html><head><title>Comentário excluído</title><link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css></head><body>");
		out.println("<h3 class=text-center>Comentário excluído com sucesso</h3>");
		out.println("<form action=\"ManterUmaNoticia.do\" class=\"text-center\" action=\"get\">");
		out.println("<input type=hidden name=\"idNoticia\" value=\"" + idNoticia +"\" />");
		out.println("<button type=\"submit\" class=\"btn btn-success\">Voltar</button>");
		out.println("</form>");
		
	}

}
