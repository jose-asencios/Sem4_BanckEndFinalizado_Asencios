package com.empresa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Medicamento;
import com.empresa.repository.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{

	@Autowired
	private MedicamentoRepository repositoryMed;
	
	@Override
	public List<Medicamento> listaMedicamento() {
		return repositoryMed.findAll();
	}

	@Override
	public Medicamento insertaActualizaMedicamento(Medicamento obj) {
		return repositoryMed.save(obj);
	}

	@Override
	public Optional<Medicamento> buscarPorIdMed(int idMedicamento) {
		return repositoryMed.findById(idMedicamento);
	}

	@Override
	public void eliminaPorIdMed(int idMedicamento) {
		repositoryMed.deleteById(idMedicamento);
	}


	@Override
	public List<Medicamento> listaPorNombreMed(String nombreMedicamento) {
		return repositoryMed.findByNombreContaining(nombreMedicamento);
	}


	@Override
	public List<Medicamento> listaPorStockMed(int stockMed) {
		return repositoryMed.findByStock(stockMed);
	}

}
