package com.empresa.service;

import java.util.List;
import java.util.Optional;

import com.empresa.entity.Alumno;

public interface AlumnoService {

	public abstract List<Alumno> listaAlumno();
	public abstract Alumno insertaActualizaAlumno(Alumno obj);
	public Optional<Alumno> buscarPorId(int idAlumno);
	public abstract void eliminaPorId(int idAlumno);
	public abstract List<Alumno> listaPorDni(String dni);
}
