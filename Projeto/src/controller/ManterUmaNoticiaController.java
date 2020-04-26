package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Comentario;
import model.Noticia;
import service.ComentarioService;
import service.NoticiaService;

/**
 * Servlet implementation class ManterUmaNoticiaController
 */
@WebServlet("/ManterUmaNoticia.do")
public class ManterUmaNoticiaController extends HttpServlet {
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
		
		int idNoticia = Integer.parseInt(request.getParameter("idNoticia"));
		
		NoticiaService ns = new NoticiaService();
		Noticia noticia = ns.carregar(idNoticia);
		
		ComentarioService cd = new ComentarioService();
		ArrayList<Comentario> comentarios = cd.carregarTodas(idNoticia);
		
		out.println("<html><head><title>Notícia</title><link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css></head><body>");
		out.println("<div class=container><h1 class=my-3>RealNews <a href=\"ManterNoticias.do\" class=\"btn btn-info float-right\">Voltar</a></h1><div class=\"row justify-content-center\">");
		out.println("<div class=\"col-lg-8 my-3\"><div class=card><div class=card-body>");
		out.println("<h5 class=card-title>" + noticia.getTitulo() + "</h5>");
		out.println("<p class=blockquote-footer>" + noticia.getDescricao() + "</p>");
		out.println("<p class=card-text>" + noticia.getTexto() + "</p>");
		out.println("<input type=\"hidden\" name=\"idNoticia\" value=\"" + noticia.getId() + "\"/>");
		
		out.println("<div class=container>");
		out.println("<h5 class=\"mb-3\">Comentários: </h5>");
		
		if(comentarios.size() <= 0) {
			out.println("<p class=\"text-center\">Nenhum comentário, seja o primeiro.</p>");
		}
		
		for(Comentario cm: comentarios) {
			out.println( "<div class=\"card mb-3\">" );
			out.println("<div class=\"card-header\">" + cm.getNome());
			
			String query = "?idNoticia=" + noticia.getId() + "&idComentario=" + cm.getId();
			out.println("<a href="+("EditarComentario.do" + query)+" class=\"btn btn-warning btn-sm float-right ml-2\">Editar</a>");
			out.println("<a href="+("ExcluirComentario.do" +query )+" class=\"btn btn-danger btn-sm float-right\">Excluir</a>");
			
			out.println("</div>");
			out.println("<div class=\"card-body\"><p>" + cm.getTexto() + "</p></div>");
			out.println( "</div>" );
		}
		
		out.println("<form class=\"mb-3\" action=\"AdicionarComentario.do\" method=\"get\" accept-charset=\"UTF-8\">");
		
		// INPUT NOME
		out.println("<div class=\"form-group\">");
		out.println("<label>Nome</label>");
		out.println("<input type=\"text\" name=\"nome\" maxlength=\"126\" class=\"form-control\" required>");
		out.println("</div>");
		// TEXTO COMENTARIO
		out.println("<div class=\"form-group\">");
		out.println("<label>Comentar</label>");
		out.println("<textarea class=\"form-control\" name=\"texto\" maxlength=\"512\" rows=\"3\" required></textarea>");
		out.println("</div>");
		

		out.println("<input type=hidden name=\"idNoticia\" value=\"" + idNoticia +"\" />");
		
		out.println("<button type=\"submit\" class=\"btn btn-success\">Comentar</button>");
		out.println("</form>");
		
		out.println("</div>");
		
		out.println("</div></div></div>");
		
		out.println("</div></div>");
	    out.println("</body></html>");
	}
}
