package br.com.valenight.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.valenight.config.BDConfig;
import br.com.valenight.entity.Nota;

public class NotaDAO {
	public List<Nota> listarNotas() throws Exception {
		List<Nota> lista = new ArrayList<>();
		
		Connection c = BDConfig.getConnection();
		
		String sql = "SELECT * FROM TB_NOTA";
		
		PreparedStatement stmt = c.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()){
			Nota nota = new Nota();
			nota.setId(rs.getInt("ID_NOTE"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
			lista.add(nota);
		}
		
		return lista;
	}
	
	public Nota buscarNotaPorId(int id) throws Exception {
		Nota nota = new Nota();
		
		Connection c = BDConfig.getConnection();
		
		String sql = "SELECT * FROM TB_NOTA WHERE id_note = ?";
		
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()){			
			nota.setId(rs.getInt("ID_NOTE"));
			nota.setTitulo(rs.getString("TITULO"));
			nota.setDescricao(rs.getString("DESCRICAO"));
		}
		
		return nota;
	}
	
	public int addNota(Nota nota) throws ClassNotFoundException, SQLException{
		Connection c = BDConfig.getConnection();
		
		String sql = "INSERT INTO TB_NOTA (TITULO, DESCRICAO) VALUES (?,?)";
		
		PreparedStatement stmt = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, nota.getTitulo());
		stmt.setString(2, nota.getDescricao());
		stmt.execute();
		
		ResultSet rs = stmt.getGeneratedKeys();
		int idGerado = 0;
		if(rs.next()){
			idGerado = rs.getInt(1);
		}
		return idGerado;
		
	}
	
	public void editNota(Nota nota) throws ClassNotFoundException, SQLException{
		Connection c = BDConfig.getConnection();
		
		String sql = "update tb_nota set titulo = ? , descricao = ? where id_note = ?";
		
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setString(1, nota.getTitulo());
		stmt.setString(2, nota.getDescricao());
		stmt.setInt(3, nota.getId());
		stmt.execute();
		
	}
	
	public void removerNota(int id) throws ClassNotFoundException, SQLException{
		Connection c = BDConfig.getConnection();
		
		String sql = "delete from tb_nota where id_note = ?";
		
		PreparedStatement stmt = c.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}
	
}
