package lester.barahona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import lester.barahona.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario,Integer> {
	
	Usuario findByUsername(String username);
	
}
