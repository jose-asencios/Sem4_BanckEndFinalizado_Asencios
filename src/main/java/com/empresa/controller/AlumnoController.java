package com.empresa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

import util.Constantes;

@RestController
@RequestMapping("/rest/alumno")
@CrossOrigin(origins = "http://localhost:4200")
public class AlumnoController {

	@Autowired
	private AlumnoService service;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Alumno>> listaAlumno(){
		List<Alumno> lista = service.listaAlumno();
		return ResponseEntity.ok(lista);
	}

	/*@PostMapping
	@ResponseBody
	public ResponseEntity<Alumno> insertaAlumno(@RequestBody Alumno obj){
		*if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			obj.setIdAlumno(0);
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			return ResponseEntity.ok(objSalida);
		}
	}*/
	@PostMapping
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaAlumno(@RequestBody Alumno obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Alumno objSalida = service.insertaActualizaAlumno(obj);
			if (objSalida == null) {
				salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
			}else {
				salida.put("mensaje", Constantes.MENSAJE_REG_EXITOSO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			salida.put("mensaje", Constantes.MENSAJE_REG_ERROR);
		}
		return ResponseEntity.ok(salida);
	}
	
	
	@PutMapping
	@ResponseBody
	public ResponseEntity<Alumno> actualizaAlumno(@RequestBody Alumno obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			Optional<Alumno> optAlumno = service.buscarPorId(obj.getIdAlumno());
			if (optAlumno.isEmpty()) {
				return ResponseEntity.noContent().build();
			}else {
				Alumno objSalida = service.insertaActualizaAlumno(obj);
				return ResponseEntity.ok(objSalida);	
			}
		}

	}
	
	@DeleteMapping("/{paramId}")
	@ResponseBody
	public ResponseEntity<Alumno> eliminaAlumno(@PathVariable("paramId") int idAlumno){
		Optional<Alumno> optAlumno = service.buscarPorId(idAlumno);
		if (optAlumno.isPresent()) {
			service.eliminaPorId(idAlumno);
			Optional<Alumno> optSalida = service.buscarPorId(idAlumno);
			if (optSalida.isPresent()) {
				return ResponseEntity.badRequest().build();
			}else {
				return ResponseEntity.ok(optAlumno.get());
			}
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/id/{paramId}")
	@ResponseBody
	public ResponseEntity<Alumno> buscaPorId(@PathVariable("paramId") int idAlumno){
		Optional<Alumno> optAlumno = service.buscarPorId(idAlumno);
		if (optAlumno.isPresent()) {
			return ResponseEntity.ok(optAlumno.get());
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/dni/{paramDni}")
	@ResponseBody
	public ResponseEntity<List<Alumno>> buscaPorDni(@PathVariable("paramDni") String dni){
		List<Alumno> lista = service.listaPorDni(dni);
		if (CollectionUtils.isEmpty(lista)) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}
}
