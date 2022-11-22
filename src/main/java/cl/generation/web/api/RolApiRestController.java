package cl.generation.web.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.generation.web.models.Rol;
import cl.generation.web.models.Usuario;
import cl.generation.web.services.RolServiceImpl;

@RestController
public class RolApiRestController {

	@Autowired
	public RolServiceImpl rolServiceImpl;

	@RequestMapping("/obtener/rol")
	public Rol obtenerRol(@RequestParam(value = "id", required = true) Long id) {
		Rol rol = rolServiceImpl.obtenerRol(id);
		// uso de lazy
		// obteniendo la lista del mismo rol
		List<Usuario> usuarios = rol.getUsuarios();
		// obteniendo la lista de usuarios dependiendo del rol
		for (Usuario usuario : usuarios) {
			System.out.println(usuario.getNombre());
		}
		return rol;
	}
}
