package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Medicamento;

public interface MedicamentoService {
	public abstract List<Medicamento> listaMedicamento();
	public abstract Medicamento insertaActualizaMedicamento(Medicamento obj);
	
	public Optional<Medicamento> buscarPorIdMed(int idMedicamento);
	public abstract void eliminaPorIdMed(int idMedicamento);
	
	public abstract List<Medicamento> listaPorNombreMed(String nombreMedicamento);
	public abstract List<Medicamento> listaPorStockMed(int stockMed);
}
