package cl.generation.web.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.generation.web.dto.AutoDTO;
import cl.generation.web.models.Auto;
import cl.generation.web.repositories.AutoRepository;

@Service
public class AutoServiceImpl implements AutoService{

	@Autowired
	public AutoRepository autoRepository;

	@Override
	public Auto guardarAuto(Auto auto) {
		/*if(auto.getId() != null) {// edicion
			// logica de traspasar informacion de AutoDTO a Auto
		}*/
		
		return autoRepository.save(auto);
	}

	@Override
	public Auto obtenerAuto(Long id) {
		return autoRepository.findById(id).get();
	}

	@Override
	public List<Auto> listarAutos() {
		return autoRepository.findAll();
	}

	@Override
	public Auto obtenerAutoNombre(String marca) {
		return autoRepository.findByMarca(marca);
	}

	@Override
	public List<Auto> findAllByMarca(String marca) {
		return autoRepository.findAllByMarca(marca);
	}

	public void eliminarAuto(Long id) {
		autoRepository.deleteById(id);
	}
	
	public List<AutoDTO> listaAutos() {
		List<Auto> autos = autoRepository.findAll();
		List<AutoDTO> autosDTO = new ArrayList<AutoDTO>();
		for (Auto auto : autos) {
			AutoDTO autoDTO = new AutoDTO();
			autoDTO.setId(auto.getId());
			autoDTO.setColor(auto.getColor());
			autoDTO.setMarca(auto.getMarca());
			autoDTO.setNombreUser(auto.getUsuario().getNombre());
			autoDTO.setApellidoUser(auto.getUsuario().getApellido());
			autosDTO.add(autoDTO);

		}
		return autosDTO;
	}
	
	@Override
	public Auto editarAuto(Long id, Auto auto) {
		Optional<Auto> autoParaEditar = autoRepository.findById(id);
		Auto autoEditado = autoParaEditar.get();
		autoEditado.setMarca(auto.getMarca());
		autoEditado.setColor(auto.getColor());
		autoRepository.save(autoEditado);
		return autoEditado;
	}

}
