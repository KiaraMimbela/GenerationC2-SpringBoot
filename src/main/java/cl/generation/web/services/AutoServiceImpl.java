package cl.generation.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.generation.web.models.Auto;
import cl.generation.web.repositories.AutoRepository;

@Service
public class AutoServiceImpl implements AutoService{

	@Autowired
	public AutoRepository autoRepository;

	@Override
	public Auto guardarAuto(Auto auto) {
		return autoRepository.save(auto);
	}

	@Override
	public Auto obtenerAuto(Long id) {
		return autoRepository.findById(id).get();
	}
	
	
}
