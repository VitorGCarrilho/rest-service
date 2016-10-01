package br.com.valenight.rest;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.valenight.dao.NotaDAO;
import br.com.valenight.entity.Nota;

@Path("/notas")
public class NotasService {
	
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	private NotaDAO notaDAO;
	
	
	@PostConstruct
	private void init(){
		notaDAO = new NotaDAO();
	}
	
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Nota> listarNotas(){
		List<Nota> lista = null;
		try {
			lista = notaDAO.listarNotas();
		} catch (Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	
	@PUT
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String addNota (Nota nota){
		String msg = "";
		
		try {
			int idGerado = notaDAO.addNota(nota);
			msg = String.valueOf(idGerado);
		} catch (ClassNotFoundException | SQLException e) {
			msg = "Erro ao adicionada";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Nota buscarPorId(@PathParam("id") int id){
		Nota nota = null;
		try {
			nota = notaDAO.buscarNotaPorId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nota;
	}
	
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarNota(Nota nota) {
		String msg = "";
		
		try {
			notaDAO.editNota(nota);
			msg = "Nota editada";
		} catch (ClassNotFoundException | SQLException e) {
			msg = "Erro ao editar nota";
			e.printStackTrace();
		}
		return msg;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletarNota(@PathParam("id") int id) {
		String msg = "";
		
		try {
			notaDAO.removerNota(id);
			msg = "Nota removida";
		} catch (ClassNotFoundException | SQLException e) {
			msg = "Erro ao remover";
			e.printStackTrace();
		}
		return msg;
	}
}
