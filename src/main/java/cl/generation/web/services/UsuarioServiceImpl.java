package cl.generation.web.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.generation.web.models.Usuario;
import cl.generation.web.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
// la clase servicie: se realiza toda la logica de negocio del sistema web
// exam: existe el usuario?

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		// guarda la entidad usuario, metodo de crud

		return usuarioRepository.save(usuario);
	}

	@Override
	public String eliminarUsuario(Long id) {
		// existe o no?
		Boolean existe = usuarioRepository.existsById(id);

		if (existe) {
			// elimino el usuario pasando el id (pk)
			usuarioRepository.deleteById(id);
		}else {
			return "El usuario no existe en la tabla";
		}
		
		existe = usuarioRepository.existsById(id);
		if (existe) {
			return "El usuario no fue eliminado";
		}
		return "El usuario fue eliminado";
	}

	@Override
	public String actualizarUsuario(Usuario usuario) {
		Boolean existe = usuarioRepository.existsById(usuario.getId());
		if(existe) {
			usuarioRepository.save(usuario);
			return "Usuario actualizado";
		}
		return "Usuario no actualizado";
	}

	//@Override // funciona
	/**public Usuario obtenerUsuario(Long id) {
		// buscar en repositorio por el id y obtener el usuario
		Usuario user = usuarioRepository.findById(id).get();
		return user;
		//.get = obtenemos el tipo de dato especifico
		
	}**/

	// con validación
	@Override
	public Usuario obtenerUsuario(Long id) {
		//Optional<Usuario> user = usuarioRepository.findById(id);
		Boolean existe = usuarioRepository.existsById(id);
		
		if(existe) {
		Usuario user= usuarioRepository.findById(id).get();
			return user;
		}
		return null;
	}

	@Override
	public List<Usuario> obtenerListaUsuarios() {
		// obtener todos los usuarios
		return usuarioRepository.findAll();
	}

	

}
