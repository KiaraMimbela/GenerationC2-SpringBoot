package cl.generation.web.services;

import java.util.List;

import cl.generation.web.models.Usuario;

public interface UsuarioService {
	// Definir metodos/funciones para CRUD de usuario
	public Usuario guardarUsuario(Usuario usuario);
	public String eliminarUsuario(Long id);
	public String actualizarUsuario(Usuario usuario);
	public Usuario obtenerUsuario(Long id);
	public List<Usuario> obtenerListaUsuarios();
}
