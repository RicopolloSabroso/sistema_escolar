package edu.udelp.poo.emiliano.ricoy.model;

import java.util.Objects;

import lombok.Data;


@Data
public class Materia {

	
	private String horario;
	private String nombre;
	private Carrera carrera;
	private String semestre;
	private boolean profesor=false;
	public Materia(String semestre, String horario, String nombre, Carrera carrera) {
	    this.nombre = nombre;
	    this.semestre = semestre;
	    this.horario = horario;
	    this.carrera = carrera; 
	}
	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Materia other = (Materia) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", carrera=" + carrera + ", semestre=" + semestre;
	}

}
