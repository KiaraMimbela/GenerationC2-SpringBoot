package cl.generation.web.services;

import java.util.List;

import cl.generation.web.models.Usuario;

public interface UsuarioService {
	// Definir metodos/funciones para CRUD de usuario
	public Boolean guardarUsuario(Usuario usuario);
	public String eliminarUsuario(Long id);
	public String actualizarUsuario(Usuario usuario);
	public Usuario obtenerUsuario(Long id);
	public List<Usuario> obtenerListaUsuarios();
	//login
	public Boolean ingresoUsuario(String email, String password);
	public Usuario obtenerUsuario(String email);
}
