package cl.generation.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.generation.web.models.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long>{

	Auto findByMarca(String marca);
	
	//JPQL
	
	//obtener una lista de autos por marca
	//establecer la query que vamos a utilizar, entre(la consulta atraves de jpql)
	@Query("SELECT a FROM Auto a WHERE a.marca = ?1") //la "a" es el alias de auto, se usa AUTO porque se trabaja como objeto
	List<Auto> findAllByMarca(String marca);
	
	//query comun
	@Query(value="select * from autos a where a.marca = ?1", nativeQuery = true)
	List<Auto> findAllByMarcaComun(String marca);
	
	// JPQL lista de todos los autos de un usuario
	@Query("SELECT a FROM Auto a WHERE a.usuario.id = ?1")
	List<Auto> findAllByUsuario(Long id);
	
	// obtener lista de autos por marca y color
	@Query("SELECT a FROM Auto a WHERE a.marca = ?1 and a.color = ?2") 
	List<Auto> findAllByMarcaAndColor(String marca, String color);
	

}
