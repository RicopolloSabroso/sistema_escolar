package edu.udelp.poo.emiliano.ricoy.model;

import java.util.Random;

import lombok.Data;

@Data
public class Usuario {
	private String id;
	private String usuario;
	private String password;
	private Rol rol;
	public Usuario( String usuario, String password, Rol rol) {
		this.id = String.valueOf(generarId());
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}
	private int generarId() {
		Random random = new Random();
		return random.nextInt(99999999)+10000000;
	}
	@Override
	public String toString() {
		return  usuario ;
	}
}	
