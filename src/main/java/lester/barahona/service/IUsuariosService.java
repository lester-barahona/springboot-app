package lester.barahona.service;

import java.util.List;

import lester.barahona.model.Usuario;

public interface IUsuariosService {

	void guardar(Usuario usuario);
	
	void eliminar(Integer idUsuario);
	
	List<Usuario> buscarTodos();
	
	Usuario buscarPorUserName(String username);
	
}
