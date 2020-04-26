package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Noticia;
import service.NoticiaService;


/**
 * Servlet implementation class EditarNoticiaController
 */
@WebServlet("/EditarNoticia.do")
public class EditarNoticiaController extends HttpServlet {
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
		
		NoticiaService ns = new NoticiaService();
		Noticia noticia = ns.carregar(idNoticia);
		out.println("<html><head><title>Editar Notícia</title><link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css></head><body>");
		out.println("<div class=container><h1 class=my-3>RealNews</h1>");
		
		out.println("<form action=\"SalvarEdicaoNoticia.do\" accept-charset=\"UTF-8\">");
		// TITULO
		out.println("<div class=\"form-group\">");
		out.println("<label>Título</label>");
		out.println("<input type=\"text\" name=\"titulo\" maxlength=\"126\" class=\"form-control\" required value=\""+ noticia.getTitulo() +"\" />");
		out.println("</div>");
		// DESCRICAO
		out.println("<div class=\"form-group\">");
		out.println("<label>Descrição</label>");
		out.println("<textarea class=\"form-control\" name=\"descricao\" maxlength=\"512\" rows=\"3\" required>"+ noticia.getDescricao() +"</textarea>");
		out.println("</div>");
		// TEXTO
		out.println("<div class=\"form-group\">");
		out.println("<label>Descrição</label>");
		out.println("<textarea class=\"form-control\" name=\"texto\" rows=\"3\" required>" + noticia.getTexto() + "</textarea>");
		out.println("</div>");
		out.println("<input type=\"hidden\" name=\"idNoticia\" value=\"" + noticia.getId() + "\" />");
		out.println("<button type=\"submit\" class=\"btn btn-success\">Salvar</button>");
		out.println("<a href=\"ManterNoticias.do\" class=\"btn btn-info float-right\">Voltar</button>");
		out.println("</form>");

	    out.println("</body></html>");
		
	}

}
