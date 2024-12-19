package edu.udelp.poo.emiliano.ricoy.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import lombok.Data;
@Data
public class Persona {
	private String nombre;
	private String nacimiento;
	private String id;
	public char sexo;
	private double peso;
	private double altura;

	public Persona() {

	}
	public Persona(String nombre, String nacimiento, char sexo) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.nombre = nombre;
//		this.nacimiento=LocalDate.parse(nacimiento, formato);
		this.nacimiento=nacimiento;
		this.sexo = sexo;
		this.id=generarId();
	}

	public Persona(String nombre, String nacimiento, char sexo, Double peso, Double altura) { 
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		this.nombre = nombre;
//		this.nacimiento = LocalDate.parse(nacimiento, formato);
		this.nacimiento=nacimiento;
		this.sexo = sexo;
		this.peso = peso;
		this.altura = altura;
		this.id=generarId();
		
	}
	
	private String generarId() {
		Random random = new Random();
		int numero = random.nextInt(100000000);
        String numeroComoCadena = String.format("%08d", numero);

        return numeroComoCadena;
	}
	
	public String toString() {
		return "Persona ["+ "nombre=" + nombre + ", nacimiento=" + nacimiento+", ID=" + id
				+ ", sexo=" + sexo + ", peso=" + peso + ", altura=" + altura + "]";
	}

	
}
