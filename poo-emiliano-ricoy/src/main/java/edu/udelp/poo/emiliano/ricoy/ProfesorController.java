package edu.udelp.poo.emiliano.ricoy;

import edu.udelp.poo.emiliano.ricoy.model.Carrera;
import edu.udelp.poo.emiliano.ricoy.model.Estudiante;
import edu.udelp.poo.emiliano.ricoy.model.Materia;
import edu.udelp.poo.emiliano.ricoy.model.MateriaAlumno;
import edu.udelp.poo.emiliano.ricoy.model.Profesor;
import edu.udelp.poo.emiliano.ricoy.processor.ProcesosEscuela;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.WindowEvent;

public class ProfesorController {
	private Profesor profesor;
	@FXML
	private ComboBox<Integer>obtenerDatosSemestre;
	@FXML
	private ComboBox<Carrera>obtenerDatosCarrera;
	@FXML
	private ComboBox<MateriaAlumno>obtenerDatosMateria;
	@FXML
	private ComboBox<Materia> seleccionarMateriaCalificaciones;
	@FXML
    private ComboBox<Estudiante> seleccionarAlumno,obtenerDatosAlumno;
	@FXML
	private TextField primerParcial, segundoParcial, proyecto, examenFinal;
	@FXML
	private TableView<Materia> tablaMaterias;
	@FXML
	private TableColumn<Materia, String> nombreMateria, carreraMateria,semestreMateria,horarioMateria;
	@FXML 
	private TextArea areaDatosAlumno;
	public void initData( Profesor p) {
		this.profesor=p;
		 
	    obtenerDatosCarrera.setItems(ProcesosEscuela.obtenerInstancia().getCarreraProfesorM(profesor));
	    obtenerDatosSemestre.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	    obtenerDatosCarrera.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{carreraSeleccionado(newValue,obtenerDatosSemestre.getSelectionModel().getSelectedItem());});
	    obtenerDatosAlumno.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoCarrera(newValue);});
	    obtenerDatosSemestre.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{carreraSeleccionado(obtenerDatosCarrera.getSelectionModel().selectedItemProperty().get(),newValue);});
	    obtenerDatosCarrera.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{materiaSeleccionadoCarrera(newValue,obtenerDatosSemestre.getSelectionModel().getSelectedItem());});
	    obtenerDatosSemestre.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{materiaSeleccionadoCarrera(obtenerDatosCarrera.getSelectionModel().selectedItemProperty().get(),newValue);});
		 seleccionarMateriaCalificaciones.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoMateriaProfesor(newValue);});
		 tablaMaterias.setItems(ProcesosEscuela.obtenerInstancia().getMateriasProfesor(profesor));
		 seleccionarMateriaCalificaciones.setItems(ProcesosEscuela.obtenerInstancia().getMateriasProfesor(profesor));
	}
	 private void carreraSeleccionado(Carrera carrera, Integer semestre) { 
	        if(null!=carrera && null!=semestre) {
	        	obtenerDatosAlumno.setItems(ProcesosEscuela.obtenerInstancia().getEstudiantesDelSemestreDelProfesor(carrera, profesor.getMaterias(),semestre));
	        }
	    }
	 private void alumnoSeleccionadoCarrera(Estudiante alumno) { 
	        if(null!=alumno ) {
	        	obtenerDatosMateria.setItems(ProcesosEscuela.obtenerInstancia().getMateriasAlumnoDelProfesor(profesor.getMaterias(), alumno.getMaterias()));
	        }
	    }
	 private void materiaSeleccionadoCarrera(Carrera carrera, Integer semestre) { 
		 	if(null!=carrera && null!=semestre) {
	        	obtenerDatosMateria.setItems(ProcesosEscuela.obtenerInstancia().getMateriasProfesorSemestreC(profesor,carrera, semestre));
	        }
	    }
	private void alumnoSeleccionadoMateriaProfesor(Materia m) { 
        if(null!=m) {
        	seleccionarAlumno.setItems(ProcesosEscuela.obtenerInstancia().materiasCalificarAlumnos(m));
        }
    }
	 @FXML 
	public void initialize() {
		 seleccionarMateriaCalificaciones.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoMateriaProfesor(newValue);});
		 nombreMateria.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
		 carreraMateria.setCellValueFactory(new PropertyValueFactory<>("Carrera"));
		 semestreMateria.setCellValueFactory(new PropertyValueFactory<>("Semestre"));
		 horarioMateria.setCellValueFactory(new PropertyValueFactory<>("Horario"));
	 }
	 @FXML
	 private void calificacion() throws Exception{ 
	        if(seleccionarAlumno.getSelectionModel().getSelectedItem()!=null&&seleccionarMateriaCalificaciones.getSelectionModel().getSelectedItem()!=null&&null!=primerParcial&&null!=segundoParcial&&null!=proyecto&&null!=examenFinal) {
	        	if(ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(primerParcial.getText()))&&ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(segundoParcial.getText()))&&ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(proyecto.getText()))&&ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(examenFinal.getText()))) {
	        		Materia s=seleccionarMateriaCalificaciones.getSelectionModel().getSelectedItem();
	        		MateriaAlumno a=materia(s,seleccionarAlumno.getSelectionModel().getSelectedItem());
	        		ProcesosEscuela.obtenerInstancia().agregarCalificacion(seleccionarAlumno.getSelectionModel().getSelectedItem(),a , Double.parseDouble(primerParcial.getText()), Double.parseDouble(segundoParcial.getText()), Double.parseDouble(proyecto.getText()), Double.parseDouble(examenFinal.getText()));
	        	}else {
	        		Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Datos");
		            alert.setHeaderText("Las calificaciones deben ser mayor igual a 0 y menor igual a 10");
		            alert.setContentText("Favor de ingresarlos");
		            alert.showAndWait();
		            return; 
	        	}
	        	
	        }else{
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Datos");
	            alert.setHeaderText("Faltan datos");
	            alert.setContentText("Favor de ingresarlos");
	            alert.showAndWait();
	            return; 
	        }
	        seleccionarAlumno.getSelectionModel().clearSelection();
	        seleccionarMateriaCalificaciones.getSelectionModel().clearSelection();
	        primerParcial.clear();
	        segundoParcial.clear();
	        proyecto.clear();
	        examenFinal.clear();
	    }
	 public MateriaAlumno materia(Materia m,Estudiante alumno) {
		 for(MateriaAlumno materias:alumno.getMaterias()) {
			 	if(materias.getCarrera().equals(m.getCarrera())&&materias.getNombre().equals(m.getNombre())&&m.isProfesor()) {
			 		return materias;
			 	}
		 }
		 return null;
	 }
	 public MateriaAlumno materia(MateriaAlumno m,Estudiante alumno) {
		 for(MateriaAlumno materias:alumno.getMaterias()) {
			 	if(materias.getCarrera().equals(m.getCarrera())&&materias.getNombre().equals(m.getNombre())) {
			 		return materias;
			 	}
		 }
		 return null;
	 }
	 @FXML
	    private void datosAlumnoBox() throws Exception{
		 if(null!=obtenerDatosCarrera.getSelectionModel().getSelectedItem()&& null!=obtenerDatosSemestre.getSelectionModel().getSelectedItem()&& null!=obtenerDatosAlumno.getSelectionModel().getSelectedItem()&&null!=obtenerDatosMateria.getSelectionModel().getSelectedItem()) {
			 areaDatosAlumno.setText(materia(obtenerDatosMateria.getSelectionModel().getSelectedItem(),obtenerDatosAlumno.getSelectionModel().getSelectedItem()).info());	 		 
	        }else if(null!=obtenerDatosCarrera.getSelectionModel().getSelectedItem()&& null!=obtenerDatosSemestre.getSelectionModel().getSelectedItem()&& null!=obtenerDatosAlumno.getSelectionModel().getSelectedItem()) {
	        	areaDatosAlumno.setText(ProcesosEscuela.obtenerInstancia().getAlumnoInfoCompletaMateriasSemestre(obtenerDatosAlumno.getSelectionModel().getSelectedItem(),obtenerDatosSemestre.getSelectionModel().getSelectedItem()));
	        }else if(null!=obtenerDatosCarrera.getSelectionModel().getSelectedItem()&& null!=obtenerDatosSemestre.getSelectionModel().getSelectedItem()&& null!=obtenerDatosMateria.getSelectionModel().getSelectedItem()) {
	        	areaDatosAlumno.setText(ProcesosEscuela.obtenerInstancia().getAlumnosInfoCompletaMateria(obtenerDatosMateria.getSelectionModel().getSelectedItem()));
	        }else if(null!=obtenerDatosCarrera.getSelectionModel().getSelectedItem()&& null!=obtenerDatosSemestre.getSelectionModel().getSelectedItem()){
	        	areaDatosAlumno.setText(ProcesosEscuela.obtenerInstancia().getAlumnosInfoCompletaProfesor(profesor.getMaterias(),ProcesosEscuela.obtenerInstancia().getEstudiantesDelSemestreDelProfesor(obtenerDatosCarrera.getSelectionModel().getSelectedItem(),profesor.getMaterias(),obtenerDatosSemestre.getSelectionModel().getSelectedItem())));
	        }
	        obtenerDatosCarrera.getSelectionModel().clearSelection();
	        obtenerDatosSemestre.getSelectionModel().clearSelection();
	        obtenerDatosMateria.getSelectionModel().clearSelection();
	        obtenerDatosAlumno.getSelectionModel().clearSelection();
	    }
}
