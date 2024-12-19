package edu.udelp.poo.emiliano.ricoy;

import edu.udelp.poo.emiliano.ricoy.model.Carrera;
import edu.udelp.poo.emiliano.ricoy.model.Estudiante;
import edu.udelp.poo.emiliano.ricoy.model.Materia;
import edu.udelp.poo.emiliano.ricoy.model.MateriaAlumno;
import edu.udelp.poo.emiliano.ricoy.model.Profesor;
import edu.udelp.poo.emiliano.ricoy.model.Rol;
import edu.udelp.poo.emiliano.ricoy.model.Usuario;
import edu.udelp.poo.emiliano.ricoy.processor.ProcesosEscuela;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DirectorController {
	private Profesor profesor;
	@FXML
	private ComboBox<Integer> semestreCerrado,semestreOpcionEstudiante, semestre,semestreOpcionMateria,obtenerAlumnoDatosSemestre;
	 @FXML
	    private TextField nombreCarrera, nombreMateria, horarioMateria;
	@FXML
	private ComboBox<Materia> materiasOpcionProfesor;
	@FXML
	private ComboBox<Profesor> opcionProfesor;
	@FXML
	private ComboBox<MateriaAlumno> seleccionarMateriaCalificaciones,obtenerAlumnoDatosMateria;
	@FXML
    private ComboBox<Estudiante> seleccionarAlumno,obtenerAlumnoDatos;
	@FXML
    private TextField nombreEstudiante, semestreEstudiante, generacionEstudiante,fechaNacimientoEstudiante,contrasena,usuarioEstudiante, correoEstudiante, fechaDeIngresoEstudiante;
	@FXML
	private TextField primerParcial, segundoParcial, proyecto, examenFinal;
	@FXML
	private ComboBox<String> sexoOpcionEstudiante;
	@FXML 
	private TextArea areaDatosAlumno;
	public void initData( Profesor profesor) {
		this.profesor=profesor;
		materiasOpcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreMateriasSinProfesorDeLaCarrera(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor)));
		 sexoOpcionEstudiante.setItems(FXCollections.observableArrayList("F","M"));
	    	semestreOpcionMateria.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	    	semestre.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	    	semestreCerrado.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	    	seleccionarAlumno.setItems(ProcesosEscuela.obtenerInstancia().getEstudiantesCarrera(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor)));
	    	seleccionarAlumno.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoSemestreProfesor(newValue);});
	    	opcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
	    	semestreOpcionEstudiante.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoMateriaProfesor( seleccionarAlumno.getSelectionModel().selectedItemProperty().get(), newValue);});	
	    
	        obtenerAlumnoDatosSemestre.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	        obtenerAlumnoDatos.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{materiasAlumnoSemestre(newValue,obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem());});
	        obtenerAlumnoDatosSemestre.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{materiaSemestre(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor),newValue);});
	        obtenerAlumnoDatosSemestre.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnosSemestre(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor),newValue);});
	        obtenerAlumnoDatosMateria.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnosMateriasDelSemestre(newValue, obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem());});
	            	
	}
	 private void materiaSemestre(Carrera carrera, Integer semestre) { 
		 	if(null!=carrera && null!=semestre) {
	        	obtenerAlumnoDatosMateria.setItems(ProcesosEscuela.obtenerInstancia().getMateriasDelSemestreCarrera(carrera, semestre));
	        }
	    }
	 private void alumnosSemestre(Carrera carrera, Integer semestre) { 
		 	if(null!=carrera && null!=semestre) {
	        	obtenerAlumnoDatos.setItems(ProcesosEscuela.obtenerInstancia().getEstudiantesDelSemestre(carrera, semestre));
	        }
	    }
	 private void materiasAlumnoSemestre(Estudiante alumno, Integer semestre) { 
		 	if(null!=alumno && null!=semestre&& null== obtenerAlumnoDatosMateria.getSelectionModel().selectedItemProperty()) {
		 		obtenerAlumnoDatosMateria.setItems(ProcesosEscuela.obtenerInstancia().getMateriasDelSemestreDelAlumno(alumno, semestre));
	        }
	    }
	 private void alumnosMateriasDelSemestre(MateriaAlumno materia, Integer semestre) { 
		 	if(null!=materia && null!=semestre) {
		 		obtenerAlumnoDatos.setItems(ProcesosEscuela.obtenerInstancia().getAlumnosDelSemestreDeLaMateria(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor),materia, semestre));
	        }
	    }
    @FXML
	public void initialize()  {
   	
    	}
    private void alumnoSeleccionadoSemestreProfesor(Estudiante alumno) { 
        if(null!=alumno) {
        	semestreOpcionEstudiante.setItems(ProcesosEscuela.obtenerInstancia().semestresAnteriores( alumno));

        }
    }
    private void alumnoSeleccionadoMateriaProfesor(Estudiante alumno, Integer semestre) { 
	 	if(null!=alumno && null!=semestre) {
	 		seleccionarMateriaCalificaciones.setItems(ProcesosEscuela.obtenerInstancia().getMateriasDelSemestreDelAlumno(alumno, semestre));
	 	}
    }
    private void actualizarTablas() {
    	materiasOpcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreMateriasSinProfesorDeLaCarrera(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor)));
    	seleccionarAlumno.setItems(ProcesosEscuela.obtenerInstancia().getEstudiantesCarrera(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor)));
    	seleccionarAlumno.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoSemestreProfesor(newValue);});
    	seleccionarAlumno.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoMateriaProfesor(newValue,semestreOpcionEstudiante.getSelectionModel().getSelectedItem());});
    	opcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
    }
    @FXML
    private void agregarEstudiante() throws Exception{
        String nombre = nombreEstudiante.getText();
        String fechaNacimientoStr = fechaNacimientoEstudiante.getText(); 
        String generacion = generacionEstudiante.getText();
        String usuario=usuarioEstudiante.getText();
        String contra= contrasena.getText();
        String correo=correoEstudiante.getText();
        String fecha=fechaDeIngresoEstudiante.getText();
        if(nombre!=""&&generacion!=""&&ProcesosEscuela.obtenerInstancia().validarFechaNacimiento(fechaNacimientoStr)&&contra!=""&&usuario!=""&& ProcesosEscuela.obtenerInstancia().esEmailValido(correo)&&ProcesosEscuela.obtenerInstancia().esAnioValido(fecha)) {
        	if(semestre.getSelectionModel().getSelectedItem()!=null&&sexoOpcionEstudiante.getSelectionModel().getSelectedItem()!=null) {
        		if(ProcesosEscuela.obtenerInstancia().validarUsuario(usuario)&&ProcesosEscuela.obtenerInstancia().contrasena(contra)) {
        			char sexo = sexoOpcionEstudiante.getSelectionModel().getSelectedItem().charAt(0);
	    	        int sem = semestre.getSelectionModel().getSelectedItem(); 
	    	        Estudiante nuevoEstudiante = new Estudiante(nombre, fechaNacimientoStr, sexo, sem, generacion, new Usuario( usuario, contra, new Rol("Estudiante")),correo,fecha); 
	    	        nuevoEstudiante.setClave(ProcesosEscuela.obtenerInstancia().generarClave(nuevoEstudiante));
	    	        ProcesosEscuela.obtenerInstancia().darDeAltaAlumnos(nuevoEstudiante);
	    	        ProcesosEscuela.obtenerInstancia().asignarCarreras(Integer.parseInt(nuevoEstudiante.getId()),ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor));
		    	       
        		}else {
        			Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Datos");
		            alert.setHeaderText("Usuario o contrasena incorrectos");
		            alert.setContentText("Favor de ingresarlos");
		            alert.showAndWait();
		            return;
        		}
        		
        	}else {
        		Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Datos");
	            alert.setHeaderText("Faltan datos");
	            alert.setContentText("Favor de ingresarlos");
	            alert.showAndWait();
	            return; 
        	}
	        
        }else if(ProcesosEscuela.obtenerInstancia().validarFechaNacimiento(fechaNacimientoStr)){
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos");
            alert.setHeaderText("Faltan datos");
            alert.setContentText("Favor de ingresarlos");
            alert.showAndWait();
            return; 
        }else {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fechas");
            alert.setHeaderText("La fecha no es valida");
            alert.setContentText("Favor de ingresar otra");
            alert.showAndWait();
            return; 
        }
        nombreEstudiante.clear();
        fechaDeIngresoEstudiante.clear();
        correoEstudiante.clear();
        fechaNacimientoEstudiante.clear();
        sexoOpcionEstudiante.getSelectionModel().clearSelection();
        semestre.getSelectionModel().clearSelection();
        generacionEstudiante.clear();
        usuarioEstudiante.clear();
        contrasena.clear();
        actualizarTablas();
    }
 @FXML
    private void agregarMateria() throws Exception {
        
        if(nombreMateria!=null&&semestreOpcionMateria.getSelectionModel().getSelectedItem()!=null&&horarioMateria!=null) {
        	
        	String nombre = nombreMateria.getText();
	        Integer semestre = semestreOpcionMateria.getSelectionModel().getSelectedItem();
	        String horario = horarioMateria.getText();
	        Carrera carreraSeleccionada = ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor);
        	Materia nuevaMateria = new Materia(String.valueOf(semestre), horario, nombre, carreraSeleccionada);
        	ProcesosEscuela.obtenerInstancia().darDeAltaMaterias(nuevaMateria);
        	ProcesosEscuela.obtenerInstancia().asignarMaterias(new MateriaAlumno(String.valueOf(semestre), horario, nombre, carreraSeleccionada));
	 
        }else {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos");
            alert.setHeaderText("Faltan datos");
            alert.setContentText("Favor de ingresarlos");
            alert.showAndWait();
            return; 
        }
        nombreMateria.clear();
        semestreOpcionMateria.getSelectionModel().clearSelection();
        horarioMateria.clear();
        actualizarTablas();
    }
	 @FXML
	 private void asignarMateriasProfesor() throws Exception{
	        Profesor profesor=opcionProfesor.getSelectionModel().getSelectedItem();
	        Materia materia = materiasOpcionProfesor.getSelectionModel().getSelectedItem(); 
	        if(materia!=null&&profesor!=null) {
	        	if(!materia.isProfesor()) {
	        		ProcesosEscuela.obtenerInstancia().asignarMateriasProfesor(Integer.parseInt(profesor.getId()), materia);
	        	}else {
	        		Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Datos");
		            alert.setHeaderText("Materia");
		            alert.setContentText("Esta materia ya tiene un profesor");
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
	        opcionProfesor.getSelectionModel().clearSelection();
	        materiasOpcionProfesor.getSelectionModel().clearSelection();

	    }
	 
    @FXML
    private void calificacion() throws Exception{ 
        if(seleccionarAlumno.getSelectionModel().getSelectedItem()!=null&&seleccionarMateriaCalificaciones.getSelectionModel().getSelectedItem()!=null&&null!=primerParcial&&null!=segundoParcial&&null!=proyecto&&null!=examenFinal) {
        	if(ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(primerParcial.getText()))&&ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(segundoParcial.getText()))&&ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(proyecto.getText()))&&ProcesosEscuela.obtenerInstancia().validarCalificacion(Double.parseDouble(examenFinal.getText()))) {
        		ProcesosEscuela.obtenerInstancia().agregarCalificacion(seleccionarAlumno.getSelectionModel().getSelectedItem(), seleccionarMateriaCalificaciones.getSelectionModel().getSelectedItem(), Double.parseDouble(primerParcial.getText()), Double.parseDouble(segundoParcial.getText()), Double.parseDouble(proyecto.getText()), Double.parseDouble(examenFinal.getText()));
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
            seleccionarAlumno.getSelectionModel().clearSelection();
            seleccionarMateriaCalificaciones.getSelectionModel().clearSelection();
            semestreOpcionEstudiante.getSelectionModel().clearSelection();
            primerParcial.clear();
            segundoParcial.clear();
            proyecto.clear();
            examenFinal.clear();
            alert.showAndWait();
            return; 
        }
        seleccionarAlumno.getSelectionModel().clearSelection();
        seleccionarMateriaCalificaciones.getSelectionModel().clearSelection();
        semestreOpcionEstudiante.getSelectionModel().clearSelection();
        primerParcial.clear();
        segundoParcial.clear();
        proyecto.clear();
        examenFinal.clear();
    }
    @FXML
    public void cerrarSemestres() throws Exception{
    	if(null!=semestreCerrado.getSelectionModel().getSelectedItem()) {
    		if(!ProcesosEscuela.obtenerInstancia().getEstudiantesDelSemestreCerrar(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor),semestreCerrado.getSelectionModel().getSelectedItem()).isEmpty()) {
    			Estudiante a=ProcesosEscuela.obtenerInstancia().cerrarSemestre(ProcesosEscuela.obtenerInstancia().getEstudiantesDelSemestreCerrar(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor),semestreCerrado.getSelectionModel().getSelectedItem()));
        		if(null==a) {
        			Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Semestre cerrado");
                    alert.setHeaderText("Completado");
                    alert.setContentText("Se ha cerrado el semestre");
                    alert.showAndWait();
                    
        		}else {
        			Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Datos");
                    alert.setHeaderText("Materias no calificadas");
                    alert.setContentText("El alumno "+ a.getNombre() +" tiene materias sin calificar");
                    alert.showAndWait();
                    semestreCerrado.getSelectionModel().clearSelection();
                    return; 
        		}	
    		}else {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Datos");
                alert.setHeaderText("Alumnos en el semestre");
                alert.setContentText("No hay alumnos en el semestre");
                alert.showAndWait();
                semestreCerrado.getSelectionModel().clearSelection();
                return; 
    		}
    		
    	}else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos");
            alert.setHeaderText("No hay Datos");
            alert.setContentText("Agregue los datos");
            alert.showAndWait();
            semestreCerrado.getSelectionModel().clearSelection();
            return; 
    	}
    	semestreCerrado.getSelectionModel().clearSelection();
    }
    @FXML
    private void datosAlumnoBox() throws Exception{
	 if( null!=obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem()&& null!=obtenerAlumnoDatos.getSelectionModel().getSelectedItem()&&null!=obtenerAlumnoDatosMateria.getSelectionModel().getSelectedItem()) {
		 areaDatosAlumno.setText(obtenerAlumnoDatosMateria.getSelectionModel().getSelectedItem().info());	 		 
        }else if(null!=obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem()&& null!=obtenerAlumnoDatos.getSelectionModel().getSelectedItem()) {
        	areaDatosAlumno.setText(ProcesosEscuela.obtenerInstancia().getAlumnoInfoCompletaMateriasSemestre(obtenerAlumnoDatos.getSelectionModel().getSelectedItem(),obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem()));
        }else if( null!=obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem()&& null!=obtenerAlumnoDatosMateria.getSelectionModel().getSelectedItem()) {
        	areaDatosAlumno.setText(ProcesosEscuela.obtenerInstancia().getAlumnosInfoCompletaMateria(obtenerAlumnoDatosMateria.getSelectionModel().getSelectedItem()));
        }else if(null!=obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem()){
        	areaDatosAlumno.setText(ProcesosEscuela.obtenerInstancia().getAlumnosInfoCompletaMateriasSemestre(ProcesosEscuela.obtenerInstancia().getEstudiantesDelSemestre(ProcesosEscuela.obtenerInstancia().devolverCarreraProfesor(profesor),obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem()),obtenerAlumnoDatosSemestre.getSelectionModel().getSelectedItem()));
        }

        obtenerAlumnoDatosSemestre.getSelectionModel().clearSelection();
        obtenerAlumnoDatosMateria.getSelectionModel().clearSelection();
        obtenerAlumnoDatos.getSelectionModel().clearSelection();
    }
 
}
