package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Noticia;

public class NoticiaDAO {
	
	public static void criar(Noticia noticia) {
		String sqlInsert = "INSERT INTO noticia(descricao,titulo,texto) VALUES (?,?,?)";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setString(1, noticia.getDescricao());
			stm.setString(2, noticia.getTitulo());
			stm.setString(3, noticia.getTexto());
			stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void atualizar(Noticia noticia) {
		String sqlUpdate = "UPDATE noticia SET descricao=?, titulo=?, texto=? WHERE id=?";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setString(1, noticia.getDescricao());
			stm.setString(2, noticia.getTitulo());
			stm.setString(3, noticia.getTexto());
			stm.setInt(4, noticia.getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void excluir(int id) {
		String sqlDelete = "DELETE FROM noticia WHERE id = ?";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Noticia carregar(int id) {
		Noticia noticia = new Noticia();
		noticia.setId(id);
		String sqlSelect = "SELECT * FROM noticia WHERE noticia.id = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, noticia.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					noticia.setDescricao(rs.getString("descricao"));
					noticia.setTitulo(rs.getString("titulo"));
					noticia.setTexto(rs.getString("texto"));
				} else {
					noticia.setId(-1);
					noticia.setDescricao(null);
					noticia.setTitulo(null);
					noticia.setTexto(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticia;
	}
	
	public static ArrayList<Noticia> carregarTodas() {
		ArrayList<Noticia> arrayNoticias = new ArrayList<Noticia>();
		String sqlSelect = "SELECT * FROM noticia";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				Statement st = conn.createStatement(); ) {
			ResultSet rs = st.executeQuery(sqlSelect);
			while(rs.next()) {
				Noticia noticia = new Noticia();
				noticia.setId(rs.getInt("id"));
				noticia.setDescricao(rs.getString("descricao"));
				noticia.setTitulo(rs.getString("titulo"));
				noticia.setTexto(rs.getString("texto"));
				arrayNoticias.add(noticia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrayNoticias;
	} 
	
}
