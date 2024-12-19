package edu.udelp.poo.emiliano.ricoy.model;

import java.util.Objects;
import java.util.Random;

import lombok.Data;


@Data
public class Carrera {
	
	
	private String id;
	private String nombre;
	private Integer director;
	public Carrera(String nombre, Profesor profesor) {
		this.id=String.valueOf(generarId());
		this.nombre = nombre;
		this.director=Integer.parseInt(profesor.getId());
	}
	private int generarId() {
		Random random = new Random();
		return random.nextInt(99999999)+10000000;
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
		Carrera other = (Carrera) obj;
		return Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Carrera de " + nombre ;
	}
}
