package cl.generation.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.generation.web.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	// Aquí se hace la logica de manipulacion de datos, se hace el CRUD y más (operaciones que nos permite trabajar con la data)
	// JpaRepository<Usuario, Long> 
	// <el objeto con el que vamos a trabajar, el tipo de dato de su primary key>
	Usuario findByCorreo(String correo);
	Usuario findByNick(String nick);

}
