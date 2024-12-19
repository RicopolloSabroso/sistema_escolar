
package edu.udelp.poo.emiliano.ricoy.model;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Estudiante extends Persona {
	private Integer semestre;
	private List<MateriaAlumno> materias=null;
	private String generacion;
	private Carrera carrera=null;
	private Usuario usuario;
	private boolean titulado=false;
	private String correo;
	private String clave;
	private String fechaIngreso;

	public Estudiante(String nombre, String fechaNacimiento, char sexo,Integer semestre,String generacion,Usuario usuario, String correo, String fechaIngreso) {
	    super(nombre, fechaNacimiento, sexo); 
		this.semestre=semestre;
		this.generacion=generacion;
		this.usuario=usuario;
		this.materias=new LinkedList<MateriaAlumno>();
		this.correo=correo;
		this.fechaIngreso=fechaIngreso;
		
	}
//	public Estudiante(String nombre, String fechaNacimiento, String sexo,String semestre,String generacion,ObservableList<MateriaAlumno> materias,Usuario usuario) {
//	    super(nombre, fechaNacimiento, sexo.charAt(0)); 
//		this.semestre=Integer.parseInt(semestre);
//		this.generacion=generacion;
//		this.usuario=usuario;
//		this.materias=materias;
//	}
	public String getTitulados() {
		String a="No";
		if(titulado) {
			a="Si";
		}
		return a;
	}
	public String getInfo() {
		return getNombre()+" [Id="+getId()+", Semestre Actual=" + semestre + ", generacion=" + generacion
				+ ", carrera=" + carrera.getNombre() +", Clave="+clave +"Titulado= "+ getTitulados()+" ]"+"\n"+ "Materias=" + materias ;
	}
	public String toString() {
		return " Nombre ="+getNombre() + " Id="+getId();
	}
	public String info() {
		return  "[ Semestre Actual=" + semestre + ", Generacion=" + generacion
				+ ", " + carrera + "]"+"\n"+ "Materias=" + materias ;
	}
	public String InfoEstudianteCompleto() {
		return  "Nombre= "+getNombre()+"\nSemestre Actual=" + semestre +"\nGeneracion=" + generacion
				+"\n"+ carrera +"\n"+ "Materias= " + infoCalif();
	}
	public String infoSemestre() {
		return " Nombre ="+getNombre() + " Semestre del Alumno Actual="+getSemestre();
	}
	private String infoCalif() {
		String s="";
		for (MateriaAlumno m : materias) {
				s+=m.info()+"\n";			
		}
		return s;
	}
    
	
	
	

}
