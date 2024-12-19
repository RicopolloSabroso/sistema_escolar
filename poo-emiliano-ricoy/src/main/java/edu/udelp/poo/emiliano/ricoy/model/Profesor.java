package edu.udelp.poo.emiliano.ricoy.model;


import java.util.LinkedList;
import java.util.List;

import lombok.Data;

@Data
public class Profesor extends Persona {
	private List<Materia> materias;
	private Usuario usuario;
	private String clave;
	private String fechaIngreso;
	private boolean esDirector=false;
    public Profesor(String nombre, String fechaNacimiento, char sexo, Usuario usuario, String fechaIngreso) {
        super(nombre, fechaNacimiento, sexo);
        this.materias = new LinkedList<Materia>(); 
        this.usuario=usuario;
		this.fechaIngreso=fechaIngreso;
    }

	public void setMaterias(LinkedList<Materia> materias) {
		this.materias = materias;
		
	}
	public void addMateria(Materia materia) {
		this.materias.add(materia);
	}
	
	
    public void imprimirMateriasAsignadas() {
        System.out.println("Materias encargadas del profesor:");
        for (Materia materia : materias) {
            System.out.println("Materia: "+ materia.getNombre());
        }
    }

	@Override
	public String toString() {
		return "Profesor " + getNombre()  ;
	}
	
	
	

}