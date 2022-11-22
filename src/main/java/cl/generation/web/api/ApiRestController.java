package cl.generation.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // responde a peticiones externas, de otro sistemas y le entrega información
@RequestMapping("/api")
public class ApiRestController {

	// http://localhost:8080/api/hola
	@RequestMapping("/hola")
	public String hola() {
		return "Hola Api";
	}

	// ruta dinamica, que me sirve cuando un dato va a cambiar en el tiempo
	// http://localhost:8080/api/edad/42
	@RequestMapping("/edad/{edad}") // informacion dinamica{}
	public String rutaDinamica(@PathVariable("edad") int edad) {
		// @PathVariable para capturar (lo que vamos a capturar) tipo de dato
		return "Capturando edad: " + edad;
	}

	// http://localhost:8080/api/nombre/...
	@RequestMapping("/nombre/{nombre}")
	public String capturarNombre(@PathVariable("nombre") String nombre) {
		return "El nombre capturado es: " + nombre;
	}

	// http://localhost:8080/api/14/noviembre/2022
	// pathvariable, capturar desde la ruta 
	@RequestMapping("/{dia}/{mes}/{año}")
	public String fecha(@PathVariable("dia") int dia, @PathVariable("mes") String mes, @PathVariable("año") int año) {
		return "La fecha es " + dia + " " + mes + " " + año;
	}

	// http://localhost:8080/api?id=--1 // peticion de tipo get, ruta con parametros
	// siempre van a ser string
	// request param para capturar parametros
	@RequestMapping("/usuario")
	public String parametro(@RequestParam(value = "usuarioId", required = false) Integer id) {
		// required true es que el parametro es obligatorio
		if (id == null) {
			return "El parametro no existe";
		} else {
			return "parametro por get " + id;
		}

	}

	@RequestMapping("/usuario2")
	public String parametro2(@RequestParam(value = "usuarioId", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre) {

		if (id == null) {
			return "El parametro no existe en parametro 2";
		} else {
			return "parametro por get " + id + " nombre " + nombre;

		}
	}
}