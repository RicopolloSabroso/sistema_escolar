package edu.udelp.poo.emiliano.ricoy.model;

import java.util.Random;

import lombok.Data;

@Data
public class Rol {
	private String id;
	private String nombre;
	public Rol(String nombre) {
		this.nombre=nombre;
		this.id=String.valueOf(generarId());
	}
	private int generarId() {
		Random random = new Random();
		return random.nextInt(99999999)+10000000;
	}
	@Override
	public String toString() {
		return  nombre ;
	}
}
