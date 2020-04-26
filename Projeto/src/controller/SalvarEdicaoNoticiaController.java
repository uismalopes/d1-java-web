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

@WebServlet("/SalvarEdicaoNoticia.do")
public class SalvarEdicaoNoticiaController extends HttpServlet {
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
		String titulo = request.getParameter("titulo");
		String descricao = request.getParameter("descricao");
		String texto = request.getParameter("texto");
		
		
		NoticiaService ns = new NoticiaService();
		Noticia noticia = new Noticia(idNoticia, descricao, titulo, texto);
		ns.atualizar(noticia);
		
		out.println("<html><head><title>Notícia atualizada</title><link rel=stylesheet href=https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css></head><body>");
		out.println("<h3 class=text-center>Notícia atualizada com sucesso</h3>");
		out.println("<div class=\"text-center\">");
		out.println("<a href=\"ManterNoticias.do\" class=\"btn btn-success\"> Ver notícias </a>");
		out.println("</div>");
		
	}
}
