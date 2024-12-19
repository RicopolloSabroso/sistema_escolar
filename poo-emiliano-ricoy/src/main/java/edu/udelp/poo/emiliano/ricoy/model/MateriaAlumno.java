package edu.udelp.poo.emiliano.ricoy.model;

import java.util.Objects;

import edu.udelp.poo.emiliano.ricoy.processor.ProcesosEscuela;
import lombok.Data;

@Data
public class MateriaAlumno extends Materia {

	private Double primerParcial;
	private Double segundoParcial;
	private Double proyecto;
	private Double examenFinal;
	private boolean calificada=false;
	public MateriaAlumno(String semestre, String horario, String nombre, Carrera carrera) {
		super(semestre,horario,nombre,carrera);
		this.primerParcial=0.0;
		this.segundoParcial=0.0;
		this.examenFinal=0.0;
		this.proyecto=0.0;
	}
	@Override
	public String toString() {
		return  getNombre();
	}
	public String info() {
		return  "\n"+getNombre()+"\nSemestre:"+ getSemestre()+" [ primerParcial=" + primerParcial + ", segundoParcial="
				+ segundoParcial + ", proyecto=" + proyecto + ", examenFinal=" + examenFinal +", Promedio Final="+(primerParcial+segundoParcial+proyecto+examenFinal)/4+"]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MateriaAlumno other = (MateriaAlumno) obj;
		return calificada == other.calificada && Objects.equals(examenFinal, other.examenFinal)
				&& Objects.equals(primerParcial, other.primerParcial) && Objects.equals(proyecto, other.proyecto)
				&& Objects.equals(segundoParcial, other.segundoParcial);
	}
	
	

}