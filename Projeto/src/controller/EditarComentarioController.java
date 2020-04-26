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


/**
 * Servlet implementation class EditarComentarioController
 */
@WebServlet("/EditarComentario.do")
public class EditarComentarioController extends HttpServlet {
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
		
		int idComentario = Integer.parseInt( request.getParameter("idComentario") );
		int idNoticia = Integer.parseInt( request.getParameter("idNoticia") );
		
		ComentarioService cs = new ComentarioService();
		Comentario comentario = cs.carregar(idComentario);
		
		out.println("<html><head><title>Editar Comentário</title><link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css></head><body>");
		out.println("<div class=container><h1 class=my-3>RealNews</h1>");
		
		out.println("<form action=\"SalvarEdicaoComentario.do\" accept-charset=\"UTF-8\">");
		// NOME
		out.println("<div class=\"form-group\">");
		out.println("<label>Nome</label>");
		out.println("<input type=\"text\" name=\"nome\" class=\"form-control\" maxlength=\"126\" required value=\""+ comentario.getNome() +"\" />");
		out.println("</div>");
		// TEXTO
		out.println("<div class=\"form-group\">");
		out.println("<label>Comentário</label>");
		out.println("<textarea class=\"form-control\" maxlength=\"512\" name=\"texto\" rows=\"3\" required>"+ comentario.getTexto() +"</textarea>");
		out.println("</div>");
		
		out.println("<button type=\"submit\" class=\"btn btn-success\">Salvar</button>");
		String linkVoltar = "ManterUmaNoticia.do?idNoticia=" + idNoticia;
		out.println("<a href="+linkVoltar+" class=\"btn btn-info float-right\">Voltar</a>");
		out.println("<input type=\"hidden\" name=\"idComentario\" value=\"" + comentario.getId() + "\" />");
		out.println("<input type=\"hidden\" name=\"fkNoticiaId\" value=\"" + comentario.getFkNoticiaId() + "\" />");
		out.println("</form></div>");

	    out.println("</body></html>");
		
	}

}
