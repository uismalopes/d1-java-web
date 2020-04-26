package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Comentario;

public class ComentarioDAO {
	public static void criar(Comentario comentario) {
		String sqlInsert = "INSERT INTO comentario(nome,texto,fk_noticia_id) VALUES (?,?,?)";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setString(1, comentario.getNome());
			stm.setString(2, comentario.getTexto());
			stm.setInt(3, comentario.getFkNoticiaId());
			stm.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void atualizar(Comentario comentario) {
		String sqlUpdate = "UPDATE comentario SET nome=?, texto=? WHERE id=?";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);){
			stm.setString(1, comentario.getNome());
			stm.setString(2, comentario.getTexto());
			stm.setInt(3, comentario.getId());
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void excluir(int id) {
		String sqlDelete = "DELETE FROM comentario WHERE id = ?";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Comentario carregar(int id) {
		Comentario comentario = new Comentario();
		comentario.setId(id);
		String sqlSelect = "SELECT * FROM comentario WHERE comentario.id = ?";
		
		try (Connection conn = ConnectionFactory.obtemConexao();
		PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, comentario.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					comentario.setNome(rs.getString("nome"));
					comentario.setTexto(rs.getString("texto"));
					comentario.setFkNoticiaId(rs.getInt("fk_noticia_id"));
				} else {
					comentario.setId(-1);
					comentario.setNome(null);
					comentario.setTexto(null);
					comentario.setFkNoticiaId(-1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comentario;
	}
	
	public static ArrayList<Comentario> carregarTodas(int idNoticia) {
		ArrayList<Comentario> arrayComentarios = new ArrayList<Comentario>();
		String sqlSelect = "SELECT * FROM comentario WHERE fk_noticia_id = ?";
		try(Connection conn = ConnectionFactory.obtemConexao(); 
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				Statement st = conn.createStatement();  ) {
			
			stm.setInt(1, idNoticia);
			try (ResultSet rs = stm.executeQuery();) {
				while(rs.next()) {
					Comentario comentario = new Comentario();
					comentario.setId(rs.getInt("id"));
					comentario.setNome(rs.getString("nome"));
					comentario.setTexto(rs.getString("texto"));
					comentario.setFkNoticiaId(rs.getInt("fk_noticia_id"));
					arrayComentarios.add(comentario);
				}
			}  catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return arrayComentarios;
	} 
}
