package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;

/**
 * Servlet implementation class ManterNoticiasController
 */
@WebServlet("/ManterNoticias.do")
public class ManterNoticiasController extends HttpServlet {
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
		NoticiaService ns = new NoticiaService();
		ArrayList<Noticia> noticia = ns.carregarTodas();
		
		out.println("<html><head><title>Lista de Notícias</title><link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css></head><body>");
		out.println("<div class=container><h1 class=my-3>RealNews <a href=\"index.html\" class=\"btn btn-info float-right\">Adicionar notícia</a></h1><div class=row>");
		
		if(noticia.size() <= 0) {
			out.println("<div class=\"col-lg-12\"><h4 class=\"text-center\">Nenhuma notícia</h4></div>");
		}
		
		for(Noticia n: noticia) {
			String query = "?idNoticia=" + n.getId();
			out.println("<div class=\"col-lg-6 my-3\"><div class=card><div class=card-body>");
			out.println("<form action=\"ManterUmaNoticia.do\" method=get>");
			out.println("<h5 class=card-title>" + n.getTitulo() + "</h5>");
			out.println("<p class=blockquote-footer>" + n.getDescricao() + "</p>");
			out.println("<p class=card-text>" + n.getTexto() + "</p>");
			out.println("<input type=\"hidden\" name=\"idNoticia\" value=\"" + n.getId() + "\"/>");
			out.println("<button class=\"btn btn-primary\" type=\"submit\">Ver Detalhes</button>");
			out.println("<a class=\"btn btn-danger float-right\" href=\"ExcluirNoticia.do"+ query +"\">Excluir</a>");
			out.println("<a class=\"btn btn-success float-right mr-3\" href=\"EditarNoticia.do"+ query +"\">Editar</a>");
			out.println("</form>");
			out.println("</div></div></div>");
		}
		
		
		
		out.println("</div></div>");
	    out.println("</body></html>");
		
	}

}
