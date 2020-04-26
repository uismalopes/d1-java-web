package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comentario;
import service.ComentarioService;

@WebServlet("/SalvarEdicaoComentario.do")
public class SalvarEdicaoComentarioController extends HttpServlet {
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
		
		int idComentario = Integer.parseInt(request.getParameter("idComentario"));
		int fkNoticiaId = Integer.parseInt(request.getParameter("fkNoticiaId"));
		
		String nome = request.getParameter("nome");
		String texto = request.getParameter("texto");
		
		
		ComentarioService cs = new ComentarioService();
		Comentario comentario = new Comentario(idComentario, fkNoticiaId, nome, texto);
		
		out.println( comentario.getFkNoticiaId() );
		
		cs.atualizar(comentario);
		
		out.println("<html><head><title>Comentário atualizado</title><link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css></head><body>");
		out.println("<h3 class=text-center>Comentário atualizado com sucesso</h3>");
		out.println("<div class=\"text-center\">");
		String linkVoltar = "ManterUmaNoticia.do?idNoticia=" + fkNoticiaId;
		out.println("<a href="+linkVoltar+" class=\"btn btn-success\"> Voltar para a Notícia</a>");
		out.println("</div>");
		
	}
}
