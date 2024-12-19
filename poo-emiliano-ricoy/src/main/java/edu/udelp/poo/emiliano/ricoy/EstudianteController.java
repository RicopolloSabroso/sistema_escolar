package edu.udelp.poo.emiliano.ricoy;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

import edu.udelp.poo.emiliano.ricoy.model.Carrera;
import edu.udelp.poo.emiliano.ricoy.model.Estudiante;
import edu.udelp.poo.emiliano.ricoy.model.Materia;
import edu.udelp.poo.emiliano.ricoy.model.MateriaAlumno;
import edu.udelp.poo.emiliano.ricoy.processor.ProcesosEscuela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class EstudianteController {
	private Estudiante estudiante;
	@FXML
	private ComboBox<Integer> semestreOpcionEstudiante;
	@FXML
	private ComboBox<MateriaAlumno> seleccionarMateriaCalificaciones;
	@FXML TextArea estudianteDatos;
	
	public void initData( Estudiante alumno) {
		this.estudiante=alumno;
		semestreOpcionEstudiante.setItems(ProcesosEscuela.obtenerInstancia().semestresAnteriores( estudiante));
    	semestreOpcionEstudiante.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoMateriaProfesor( newValue);});	

	}

    private void alumnoSeleccionadoMateriaProfesor( Integer semestre) { 
	 	if( null!=semestre) {
	 		seleccionarMateriaCalificaciones.setItems(ProcesosEscuela.obtenerInstancia().getMateriasDelSemestreDelAlumno(estudiante, semestre));
	 	}
    }
    @FXML
	public void initialize() {
    	
    }
    @FXML
    public void buscar() {
    	if(null!=semestreOpcionEstudiante.getSelectionModel().getSelectedItem() && null!=seleccionarMateriaCalificaciones.getSelectionModel().getSelectedItem()) {
    		estudianteDatos.setText(seleccionarMateriaCalificaciones.getSelectionModel().getSelectedItem().info());
    	}else if(null!=semestreOpcionEstudiante.getSelectionModel().selectedItemProperty()) {
    		estudianteDatos.setText(getMateriasDelSemestre(semestreOpcionEstudiante.getSelectionModel().getSelectedItem()));
    		
    	}
    	semestreOpcionEstudiante.getSelectionModel().clearSelection();
    	seleccionarMateriaCalificaciones.getSelectionModel().clearSelection();
    	
    }
    public String getMateriasDelSemestre( int semestre){
    	String datos="Materias\n";
		List<MateriaAlumno> materiasL = estudiante.getMaterias();
			for(MateriaAlumno a:materiasL) {
				if(Integer.parseInt(a.getSemestre())==semestre) {
					datos+=a.info();
				}
				
		}
		return datos;
	}


}
