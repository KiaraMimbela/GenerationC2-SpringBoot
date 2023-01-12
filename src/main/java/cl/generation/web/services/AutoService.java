package cl.generation.web.services;

import java.util.List;

import cl.generation.web.models.Auto;

public interface AutoService {

	public Auto guardarAuto(Auto auto);
	public Auto obtenerAuto(Long id);
	public List<Auto> listarAutos();
	public Auto obtenerAutoNombre(String marca);
	List<Auto> findAllByMarca(String marca);
	public Auto editarAuto(Long id,Auto auto);

}
