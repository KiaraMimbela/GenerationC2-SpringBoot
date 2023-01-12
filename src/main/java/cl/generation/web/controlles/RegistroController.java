package cl.generation.web.controlles;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.generation.web.models.Usuario;
import cl.generation.web.services.UsuarioServiceImpl;

@Controller
@RequestMapping("/registro")
public class RegistroController {

	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;

	// http://localhost:8080/registro/usuario
	// capturar la url
	@GetMapping("/usuario")
	public String mostrarFormluario() {
		return "registro.jsp";
	}

	// desplegar el jsp (controlador)
	// llenamos el formulario (vista)
	// enviamos el formulario (vista) al controlador
	// capturar la url

	// http://localhost:8080/registro/usuario
	@PostMapping("/usuario")
	// capturar los parametros @RequestParam
	public String guardarFormulario(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
			@RequestParam("nick") String nick, @RequestParam("email") String correo,
			@RequestParam("password") String password, @RequestParam("password2") String password2, Model model) {
		// permite mandar respuestas desde el back al front, model

		// validación
		if (password.equals(password2)) {
			// .equals es para comparar string
			// instanciar usuario
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setNick(nick);
			usuario.setCorreo(correo);
			usuario.setPassword(password);
			usuario.setPassword2(password2);
			// enviar a base de datos
			Boolean resultado = usuarioServiceImpl.guardarUsuario(usuario);
			if (resultado) {//si es verdadero
				model.addAttribute("msgOk", "Registro exitoso");
				return "login.jsp";//enviar a una vista
			} else {
				model.addAttribute("msgError", "Correo ya registrado");
				return "registro.jsp";
			}

		} else {
			model.addAttribute("msgError", "Passwords distintos");
			return "registro.jsp";
		}
	}

	// http://localhost:8080/registro/login
	// desplegar el jsp
	// se pasan los parametros por el url
	@GetMapping("/login")
	public String login() {
		return "login.jsp";
	}

	// se pasan los parametros ocultos
	// capturar el email y password
	@PostMapping("/login")
	public String ingresoUsuario(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model, HttpSession session) {
		// llamando al metodo
		Boolean resultadoLogin = usuarioServiceImpl.ingresoUsuario(email, password);

		if (resultadoLogin) {// resultadoLogin == true, login correcto
			Usuario usuario = usuarioServiceImpl.obtenerUsuario(email);
			// guardar informacion en sesion
			session.setAttribute("usuarioId", usuario.getId());
			session.setAttribute("usuarioEmail", email);
			session.setAttribute("usuarioRol", usuario.getRoles());
			session.setAttribute("usuarioNombre", usuario.getNombre()+" " +usuario.getApellido());
			
			return "redirect:/home";//redirige a otra ruta
		} else {
			model.addAttribute("msgError", "Intentalo de nuevo, verifica tus datos ingresados");
			return "login.jsp";
		}

	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("usuarioId")!=null) {
			session.invalidate();
		}
		return "redirect:/registro/login";
	}
	
}