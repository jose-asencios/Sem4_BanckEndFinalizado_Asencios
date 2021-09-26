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

import com.empresa.entity.Medicamento;
import com.empresa.service.MedicamentoService;

import util.Constantes;

@RestController
@RequestMapping("/rest/medicamento")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicamentoController {
	
	@Autowired
	private MedicamentoService serviceMed;

	@GetMapping
	@ResponseBody
	public ResponseEntity<List<Medicamento>> listaMedicamento(){
		List<Medicamento> listaMed = serviceMed.listaMedicamento();
		return ResponseEntity.ok(listaMed);
	}

	/*@PostMapping
	@ResponseBody
	public ResponseEntity<Medicamento> insertaMedicamento(@RequestBody Medicamento obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			obj.setIdmedicamento(0);
			Medicamento objSalidaMed = serviceMed.insertaActualizaMedicamento(obj);
			return ResponseEntity.ok(objSalidaMed);
		}
	}*/
	
	@PostMapping
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> insertaMedicamento(@RequestBody Medicamento obj){
		Map<String, Object> salida = new HashMap<>();
		try {
			Medicamento objSalida = serviceMed.insertaActualizaMedicamento(obj);
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
	public ResponseEntity<Medicamento> actualizaMedicamento(@RequestBody Medicamento obj){
		if (obj == null) {
			return ResponseEntity.noContent().build();	
		}else {
			Optional<Medicamento> optMedicamento = serviceMed.buscarPorIdMed(obj.getIdmedicamento());
			if (optMedicamento.isEmpty()) {
				return ResponseEntity.noContent().build();
			}else {
				Medicamento objSalida = serviceMed.insertaActualizaMedicamento(obj);
				return ResponseEntity.ok(objSalida);	
			}
		}
	}
	
	@DeleteMapping("/{paramId}")
	@ResponseBody
	public ResponseEntity<Medicamento> eliminaMedicamento(@PathVariable("paramId") int idMedicamento){
		Optional<Medicamento> optMedicamento = serviceMed.buscarPorIdMed(idMedicamento);
		if (optMedicamento.isPresent()) {
			serviceMed.eliminaPorIdMed(idMedicamento);
			Optional<Medicamento> optSalida = serviceMed.buscarPorIdMed(idMedicamento);
			if (optSalida.isPresent()) {
				return ResponseEntity.badRequest().build();
			}else {
				return ResponseEntity.ok(optMedicamento.get());
			}
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/id/{paramId}")
	@ResponseBody
	public ResponseEntity<Medicamento> buscaPorIdMed(@PathVariable("paramId") int idMedicamento){
		Optional<Medicamento> optMedicamento = serviceMed.buscarPorIdMed(idMedicamento);
		if (optMedicamento.isPresent()) {
			return ResponseEntity.ok(optMedicamento.get());
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/nombre/{paramNombre}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> buscaPorNombreMed(@PathVariable("paramNombre") String nombre){
		List<Medicamento> lista = serviceMed.listaPorNombreMed(nombre);
		if (CollectionUtils.isEmpty(lista)) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}
	
	@GetMapping("/stock/{paramStock}")
	@ResponseBody
	public ResponseEntity<List<Medicamento>> buscaPorStockMed(@PathVariable("paramStock") int stock){
		List<Medicamento> lista = serviceMed.listaPorStockMed(stock);
		if (CollectionUtils.isEmpty(lista)) {
			return ResponseEntity.badRequest().build();
		}else {
			return ResponseEntity.ok(lista);
		}
	}
	
}
