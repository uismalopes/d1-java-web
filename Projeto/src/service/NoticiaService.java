package service;

import java.util.ArrayList;

import dao.NoticiaDAO;
import model.Noticia;

public class NoticiaService {
	NoticiaDAO dao = new NoticiaDAO();
	
	public void criar(Noticia noticia) {
		dao.criar(noticia);
	}
	
	public void atualizar(Noticia noticia) {
		dao.atualizar(noticia);
	}
	
	public void excluir(int id) {
		dao.excluir(id);
	}
	
	public Noticia carregar(int id) {
		return dao.carregar(id);
	}
	
	public ArrayList<Noticia> carregarTodas(){
		return dao.carregarTodas();
	}
}
