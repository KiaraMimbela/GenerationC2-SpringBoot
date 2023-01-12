package cl.generation.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.generation.web.models.Usuario;
import cl.generation.web.services.UsuarioServiceImpl;

@RestController
public class UsuarioApiRestController {

	@Autowired // inyeccion, tomar todos los metodos de la clase que vamos a inyectar, los hace
				// propios
	private UsuarioServiceImpl usuarioServiceImpl;
	// creando una variable de tipo usuarioServiceImpl
	// vinculando el controller con Service

	// capturar valores de una api

	// requestbody : captura el cuerpo del mensaje
	@RequestMapping("/guardar/usuario")
	public String guardarUsuario(@RequestBody Usuario usuario) {
		/*
		 * { nombre: "Piatun", correo: "azul@gmail.com", passwword: "secret" } todo esta
		 * informacion de tipo Usuario(objeto) se guardara en la var usuario son los
		 * atributos not null es la informacion que se va a capturar en el requestBody
		 **/
		Boolean resultado = usuarioServiceImpl.guardarUsuario(usuario);
		if(resultado) {//si es verdadero
			return "Insertado correctamente"; //enviar a una vista
		}else {
			return "Error la crear usuario";
		}
	}

	// request body: para eliminar necesitamos el id
	@RequestMapping("/eliminar/usuario")
	public String eliminarUsuario(@RequestParam(value = "id", required = false) Long id) {
		/*
		 * String respuesta = usuarioServiceImpl.eliminarUsuario(id); return respuesta;
		 */
		return usuarioServiceImpl.eliminarUsuario(id);
	}

	@RequestMapping("/actualizar/usuario")
	public String actualizarUsuario(@RequestBody Usuario usuario) {
		if(usuario.getId()!=null) {
			return usuarioServiceImpl.actualizarUsuario(usuario);
		}
		return "No se actualizará ningún dato";
	}
	
	@RequestMapping("/obtener/usuario")
	public Usuario obtenerUsuario(@RequestParam(value = "id", required = true) Long id) {
		return usuarioServiceImpl.obtenerUsuario(id);
	}
	
	// listar todos los usuarios
	@GetMapping("/listar/usuarios")
	public List<Usuario> obtenerListaUsuarios(){
		return usuarioServiceImpl.obtenerListaUsuarios();
	}
}
