package cl.generation.web.controlles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cl.generation.web.models.Rol;

@Controller
@RequestMapping("/usuario") // una ruta para toda la clase
public class UsuarioController {
	// trabajar con la comunicación interna de los componentes
	// son rutas que dirigen a distintas acciones

	// https://localhost:8080/usuario/
	@RequestMapping("/") // : capturaramos las rutas
	public String getUsuario() {
		System.out.println("Obtener usuario");
		return "index.jsp";
	}

	// https://localhost:8080/usuario/home
	@RequestMapping("/home")
	public String home() {
		System.out.println("Estamos en home");
		return "home";
	}

	// https://localhost:8080/usuario/nao
	@RequestMapping("/nao")
	public String nao() {
		System.out.println("Estamos con nao");
		return "me";
	}

	// https://localhost:8080/usuario/nao
	@RequestMapping("/rol")
		public Rol obtenerRol() {
			Rol rol = new Rol();;
			rol.setNombre("Admin");
			rol.setDescripción("Administra el sistema");
			return rol;
		}
}
