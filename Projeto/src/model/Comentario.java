package model;

public class Comentario {
	private int id;
	private int fkNoticiaId;
	private String nome;
	private String texto;
	
	public Comentario() {}
	
	public Comentario(int id, int fkNoticiaId, String nome, String texto) {
		this.id = id;
		this.fkNoticiaId = fkNoticiaId;
		this.nome = nome;
		this.texto = texto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFkNoticiaId() {
		return fkNoticiaId;
	}

	public void setFkNoticiaId(int fkNoticiaId) {
		this.fkNoticiaId = fkNoticiaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
