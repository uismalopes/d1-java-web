package service;

import java.util.ArrayList;

import dao.ComentarioDAO;
import model.Comentario;

public class ComentarioService {
	ComentarioDAO dao = new ComentarioDAO();
	
	public void criar(Comentario comentario) {
		dao.criar(comentario);
	}
	
	public void atualizar(Comentario comentario) {
		dao.atualizar(comentario);
	}
	
	public void excluir(int id) {
		dao.excluir(id);
	}
	
	public Comentario carregar(int id) {
		return dao.carregar(id);
	}
	
	public ArrayList<Comentario> carregarTodas(int idNoticia){
		return dao.carregarTodas(idNoticia);
	}
}
