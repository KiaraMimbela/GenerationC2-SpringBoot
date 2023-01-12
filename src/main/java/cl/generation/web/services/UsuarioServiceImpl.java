package cl.generation.web.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
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
	public Boolean guardarUsuario(Usuario usuario) {
		// validar el usuario(email)
		Usuario retornoUsuario = usuarioRepository.findByCorreo(usuario.getCorreo());

		if (retornoUsuario == null) {
			//1234 -> 1231245321425fas4352
			String passHashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
			//reemplazando el valor por el hash
			usuario.setPassword(passHashed);
			// guarda la entidad usuario, metodo de crud
			usuarioRepository.save(usuario);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String eliminarUsuario(Long id) {
		// existe o no?
		Boolean existe = usuarioRepository.existsById(id);

		if (existe) {
			// elimino el usuario pasando el id (pk)
			usuarioRepository.deleteById(id);
		} else {
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
		if (existe) {
			usuarioRepository.save(usuario);
			return "Usuario actualizado";
		}
		return "Usuario no actualizado";
	}

	// @Override // funciona
	/**
	 * public Usuario obtenerUsuario(Long id) { // buscar en repositorio por el id y
	 * obtener el usuario Usuario user = usuarioRepository.findById(id).get();
	 * return user; //.get = obtenemos el tipo de dato especifico
	 * 
	 * }
	 **/

	// con validación
	@Override
	public Usuario obtenerUsuario(Long id) {
		// Optional<Usuario> user = usuarioRepository.findById(id);
		Boolean existe = usuarioRepository.existsById(id);

		if (existe) {
			Usuario user = usuarioRepository.findById(id).get();
			return user;
		}
		return null;
	}

	@Override
	public List<Usuario> obtenerListaUsuarios() {
		// obtener todos los usuarios
		return usuarioRepository.findAll();
	}

	@Override
	public Boolean ingresoUsuario(String email, String password) {
		System.out.println(email + " " + password);
		// buscamos en la bd el correo
		Usuario usuario = usuarioRepository.findByCorreo(email);
		if(usuario != null ) {//existe el usuario
			// return BCrypt.checkpw(pasword,usuario.getPassword());
			
			//comparar constraseñas
			boolean resultadoPwd = BCrypt.checkpw(password, usuario.getPassword());
			if(resultadoPwd) {// resultadoPwd == true ; son iguales
				return true;
			}else {// el resultadoPwd == false; password distintas
				return false;
			}
		}else {//no existe el email en bd
			return false;
		}
	}

	@Override
	public Usuario obtenerUsuario(String email) {
		return usuarioRepository.findByCorreo(email);
	}

}
