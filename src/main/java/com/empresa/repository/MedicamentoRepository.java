package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.empresa.entity.Medicamento;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer>{

	public List<Medicamento> findByNombreContaining(String nombreMedicamento);
	public List<Medicamento> findByStock(int stockMed);
}
