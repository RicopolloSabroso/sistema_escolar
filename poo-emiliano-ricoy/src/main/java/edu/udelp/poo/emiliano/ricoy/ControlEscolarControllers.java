package edu.udelp.poo.emiliano.ricoy;
import java.util.AbstractMap;
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
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.Alert;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class ControlEscolarControllers  {
	@FXML
	private ComboBox<Materia> materiasOpcionProfesor,boxPromedioMateria;
	@FXML
	private Label promedioPorMateriaLabel,LabelPromedioPorAlumno,LabelPromedioSemestre,labelPromedioPorMateria,labelCarrera;
	@FXML
	private TextArea areaDatosAlumno;
	@FXML
	private ComboBox<MateriaAlumno> promedioPorMateria;
	@FXML
	private ComboBox<Profesor> opcionProfesor, escogerDirectorDeCarrera;
	@FXML
	private ComboBox<String> sexoOpcionEstudiante;
	@FXML
	private ComboBox<String> sexoOpcionProfesor;
	@FXML
	private ComboBox<Integer> semestreOpcionEstudiante,semestreOpcionMateria,semestrePromedioPorAlumno,semestrePromedioDelSemestre;
	@FXML
    private TextField nombreEstudiante, semestreEstudiante, generacionEstudiante,fechaNacimientoEstudiante,contrasena,usuarioEstudiante, contrasenaProfesor,usuarioProfesor, correoEstudiante, fechaDeIngresoEstudiante, fechaDeIngresoProfesor;
    @FXML
    private ComboBox<Carrera> carreraEstudiante, carreraMateria,boxPromedioDelSemestre,boxCarreraPorMateria,boxCarreraPromedio;
    @FXML
    private ComboBox<Estudiante> boxEstudiante,promedioPorMateriaAlumno,boxPromedioPorAlumno;
    @FXML
    private TableColumn<Profesor, String> columnaIdProfesor, columnaNombreProfesor,columnaIngresoProfesor,columnaClaveProfesor;
    @FXML
    private TableView<Carrera> tablaCarrera;
    @FXML
    private TableView<Estudiante> tablaEstudiantes;
    @FXML
    private TableView<Profesor> tablaProfesor;
    @FXML
    private TableColumn<Carrera, String> columnaId, columnaNombre;
    @FXML
    private TableColumn<Estudiante, String> columnaIdAlumno, columnaNombreAlumno,  columnaSemestreAlumno,columnaIngresoAlumno, columnaCarreraAlumno;
    @FXML
    private TextField idProfesor, nombreProfesor, fechaNacimientoProfesor;
    @FXML
    private TextField nombreCarrera, nombreMateria, horarioMateria;

 
    @FXML
	 
	public void initialize() {
    	boxCarreraPorMateria.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
   	escogerDirectorDeCarrera.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
    carreraMateria.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());

    
    
    carreraEstudiante.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    boxCarreraPorMateria.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{carreraSeleccionadoPromedioMateria(newValue);});
    boxPromedioDelSemestre.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    boxCarreraPromedio.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    carreraMateria.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    boxEstudiante.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());
    
    
    promedioPorMateriaAlumno.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());
    boxPromedioPorAlumno.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());
    promedioPorMateriaAlumno.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoPromedioMateria(newValue);});
    opcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
    materiasOpcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreMaterias());
    tablaCarrera.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
     columnaId.setCellValueFactory(param -> {

		Object row = param.getValue();
		
		if(row instanceof Carrera ) {
			
			Carrera carrera = (Carrera) row;
			return new ReadOnlyStringWrapper(carrera.getId().toString());
		}
		
		AbstractMap<String, Object> map = (AbstractMap) row;
		return new ReadOnlyStringWrapper(map.get("id").toString());
	});
     columnaNombre.setCellValueFactory(param -> {

 		Object row = param.getValue();
 		
 		if(row instanceof Carrera ) {
 		
 			Carrera carrera = (Carrera) row;
 			return new ReadOnlyStringWrapper(carrera.getNombre().toString());
 		}
 		
 		AbstractMap<String, Object> map = (AbstractMap) row;
 		return new ReadOnlyStringWrapper(map.get("nombre").toString());
 	});
     tablaEstudiantes.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());

     columnaIdAlumno.setCellValueFactory(new PropertyValueFactory<>("id"));
     columnaNombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombre"));
     columnaSemestreAlumno.setCellValueFactory(new PropertyValueFactory<>("semestre"));
     columnaIngresoAlumno.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));
     columnaCarreraAlumno.setCellValueFactory(new PropertyValueFactory<>("carrera"));
	 sexoOpcionProfesor.setItems(FXCollections.observableArrayList("F","M"));
	 sexoOpcionEstudiante.setItems(FXCollections.observableArrayList("F","M"));
	 semestreOpcionEstudiante.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	 semestreOpcionMateria.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	 semestrePromedioPorAlumno.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	 semestrePromedioDelSemestre.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8));
	 
	 tablaProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
	 columnaIdProfesor.setCellValueFactory(new PropertyValueFactory<>("id"));
     columnaNombreProfesor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
     columnaClaveProfesor.setCellValueFactory(new PropertyValueFactory<>("clave"));
     columnaIngresoProfesor.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));

	}
    private void actualizarTablas() {
    	escogerDirectorDeCarrera.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
    	 boxCarreraPorMateria.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    	 carreraMateria.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    	    carreraEstudiante.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    	    boxPromedioDelSemestre.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    	    boxCarreraPromedio.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    	    carreraMateria.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    	    boxEstudiante.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());
    	    promedioPorMateriaAlumno.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());
    	    boxPromedioPorAlumno.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());
    	    promedioPorMateriaAlumno.getSelectionModel().selectedItemProperty().addListener((options,oldValue,newValue)->{alumnoSeleccionadoPromedioMateria(newValue);});
    	    opcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
    	    materiasOpcionProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreMaterias());
    	     tablaCarrera.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
    	     tablaEstudiantes.setItems(ProcesosEscuela.obtenerInstancia().getNombreEstudiantes());
    		 tablaProfesor.setItems(ProcesosEscuela.obtenerInstancia().getNombreProfesores());
    			
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
	        	if(semestreOpcionEstudiante.getSelectionModel().getSelectedItem()!=null&&sexoOpcionEstudiante.getSelectionModel().getSelectedItem()!=null) {
	        		if(ProcesosEscuela.obtenerInstancia().validarUsuario(usuario)&&ProcesosEscuela.obtenerInstancia().contrasena(contra)) {
	        			char sexo = sexoOpcionEstudiante.getSelectionModel().getSelectedItem().charAt(0);
		    	        int semestre = semestreOpcionEstudiante.getSelectionModel().getSelectedItem(); 
		    	        Estudiante nuevoEstudiante = new Estudiante(nombre, fechaNacimientoStr, sexo, semestre, generacion, new Usuario( usuario, contra, new Rol("Estudiante")),correo,fecha); 
		    	        nuevoEstudiante.setClave(ProcesosEscuela.obtenerInstancia().generarClave(nuevoEstudiante));
		    	        ProcesosEscuela.obtenerInstancia().darDeAltaAlumnos(nuevoEstudiante);
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
	        semestreOpcionEstudiante.getSelectionModel().clearSelection();
	        generacionEstudiante.clear();
	        carreraEstudiante.getSelectionModel().clearSelection();
	        usuarioEstudiante.clear();
	        contrasena.clear();
	        actualizarTablas();
	    }
	 @FXML
	    private void agregarMateria() throws Exception {
	        
	        if(nombreMateria!=null&&semestreOpcionMateria.getSelectionModel().getSelectedItem()!=null&&horarioMateria!=null&&carreraMateria.getSelectionModel().getSelectedItem()!=null) {
	        	
	        	String nombre = nombreMateria.getText();
		        Integer semestre = semestreOpcionMateria.getSelectionModel().getSelectedItem();
		        String horario = horarioMateria.getText();
		        Carrera carreraSeleccionada = carreraMateria.getSelectionModel().getSelectedItem();
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
	        carreraMateria.getSelectionModel().clearSelection();
	        actualizarTablas();
	    }
	 @FXML
	    private void agregarCarrera() throws Exception{
		
	        String nombre = nombreCarrera.getText();
	        if(nombre==""||null==escogerDirectorDeCarrera.getSelectionModel().getSelectedItem()) {
	        	 Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Datos");
		            alert.setHeaderText("Ingrese los datos");
		            alert.setContentText("Favor de ingresarlos");
		            alert.showAndWait();
		            return; 
	        }
	        if(ProcesosEscuela.obtenerInstancia().devolverCarrera(nombre)==null) {
	        	Profesor p=escogerDirectorDeCarrera.getSelectionModel().getSelectedItem();
	        	p.setEsDirector(true);
	        	Usuario u=p.getUsuario();
	        	Rol r= u.getRol();
	        	r.setNombre("Director");
	        	u.setRol(r);
	        	p.setUsuario(u);
	        	ProcesosEscuela.obtenerInstancia().darDeAltaCarreras(new Carrera(nombre,p));
	        	tablaCarrera.setItems(ProcesosEscuela.obtenerInstancia().getNombreCarreras());
	        }else {
	        	 Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Carreras");
		            alert.setHeaderText("Carrera ya existente");
		            alert.setContentText("Favor de ingresar otra");
		            alert.showAndWait();
		            return; 
	        }
	        nombreCarrera.clear();
	        escogerDirectorDeCarrera.getSelectionModel().clearSelection();
	        actualizarTablas();
	    }
	 @FXML
	    private void agregarProfesor()throws Exception {
		 String fechaNacimiento = fechaNacimientoProfesor.getText();   
		 String nombre = nombreProfesor.getText();
		 String usuario=usuarioProfesor.getText();
		  String fecha=fechaDeIngresoProfesor.getText();
	        String contra= contrasenaProfesor.getText();
	        if(nombreProfesor!=null&&sexoOpcionProfesor.getSelectionModel().getSelectedItem()!=null&&ProcesosEscuela.obtenerInstancia().validarFechaNacimientoProfesor(fechaNacimiento)&&contra!=""&&usuario!=""&&ProcesosEscuela.obtenerInstancia().esAnioValido(fecha)) {
	        	if(ProcesosEscuela.obtenerInstancia().validarUsuario(usuario)&&ProcesosEscuela.obtenerInstancia().contrasena(contra)) {
	        		char genero =sexoOpcionProfesor.getSelectionModel().getSelectedItem().charAt(0);
		        	Profesor profesor = new Profesor(nombre, fechaNacimiento, genero,new Usuario( usuario, contra, new Rol("Profesor")),fecha);
		        	profesor.setClave(ProcesosEscuela.obtenerInstancia().generarClaveProfesor(profesor));
		        	ProcesosEscuela.obtenerInstancia().darDeAltaProfesores(profesor);
	        	}else {
	        		Alert alert = new Alert(Alert.AlertType.ERROR);
		            alert.setTitle("Datos");
		            alert.setHeaderText("Usuario o contrasena incorrectos");
		            alert.setContentText("Favor de ingresarlos");
		            alert.showAndWait();
		            return;
	        	}
	        	
	        }else if(ProcesosEscuela.obtenerInstancia().validarFechaNacimientoProfesor(fechaNacimiento)){
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
	        fechaDeIngresoProfesor.clear();
	        nombreProfesor.clear();
	        fechaNacimientoProfesor.clear();
	        sexoOpcionProfesor.getSelectionModel().clearSelection();
	        contrasenaProfesor.clear();
	        usuarioProfesor.clear();
	        actualizarTablas();
	    }
	 @FXML
	 private void asignarCarrera() throws Exception{
	        Carrera carrera=carreraEstudiante.getSelectionModel().getSelectedItem();
	        Estudiante alumno = boxEstudiante.getSelectionModel().getSelectedItem(); 
	        if(carrera!=null&&alumno!=null) {
	        	if(!carrera.equals(alumno.getCarrera())) {
	        		ProcesosEscuela.obtenerInstancia().asignarCarreras(Integer.parseInt(alumno.getId()), carrera);
	        	}
		        
	        }else{
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Datos");
	            alert.setHeaderText("Faltan datos");
	            alert.setContentText("Favor de ingresarlos");
	            alert.showAndWait();
	            return; 
	        }
	        carreraEstudiante.getSelectionModel().clearSelection();
	        boxEstudiante.getSelectionModel().clearSelection();
	       tablaEstudiantes.refresh();
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
	        actualizarTablas();
	    }
	 
	
	
//	 @FXML
//	 private void promedioPorMateriaFuncion() throws Exception{
//	        if(null!=promedioPorMateria.getSelectionModel().getSelectedItem()&&null!=promedioPorMateriaAlumno.getSelectionModel().getSelectedItem()) {
//	        	promedioPorMateriaLabel.setText(ProcesosEscuela.obtenerInstancia().promedioPorMateriaAlumno(promedioPorMateriaAlumno.getSelectionModel().getSelectedItem(),promedioPorMateria.getSelectionModel().getSelectedItem().getNombre() ));
//	        }else{
//	        	Alert alert = new Alert(Alert.AlertType.ERROR);
//	            alert.setTitle("Datos");
//	            alert.setHeaderText("Faltan datos");
//	            alert.setContentText("Favor de ingresarlos");
//	            alert.showAndWait();
//	            return; 
//	        }
//	        promedioPorMateria.getSelectionModel().clearSelection();
//	        promedioPorMateriaAlumno.getSelectionModel().clearSelection();
//	    }
//	 @FXML
//	 private void promedioPorAlumnoFuncion() throws Exception{
//	        if(null!=boxPromedioPorAlumno.getSelectionModel().getSelectedItem()&&null!=semestrePromedioPorAlumno.getSelectionModel().getSelectedItem()) {
//	        	LabelPromedioPorAlumno.setText(ProcesosEscuela.obtenerInstancia().promedioPorSemestreDelAlumno(boxPromedioPorAlumno.getSelectionModel().getSelectedItem(), semestrePromedioPorAlumno.getSelectionModel().getSelectedItem()));
//	        }else{
//	        	Alert alert = new Alert(Alert.AlertType.ERROR);
//	            alert.setTitle("Datos");
//	            alert.setHeaderText("Faltan datos");
//	            alert.setContentText("Favor de ingresarlos");
//	            alert.showAndWait();
//	            return; 
//	        }
//	        boxPromedioPorAlumno.getSelectionModel().clearSelection();
//	        semestrePromedioPorAlumno.getSelectionModel().clearSelection();
//	    }
//	 @FXML
//	 private void promedioPorSemestreDelAlumnoFuncion() throws Exception{
//	        if(null!=boxPromedioDelSemestre.getSelectionModel().getSelectedItem()&&null!=semestrePromedioDelSemestre.getSelectionModel().getSelectedItem()) {
//	        	LabelPromedioSemestre.setText(ProcesosEscuela.obtenerInstancia().promedioDeTodosLosAlumnosDelSemestre(boxPromedioDelSemestre.getSelectionModel().getSelectedItem(),(int)semestrePromedioDelSemestre.getSelectionModel().getSelectedItem()));
//	        }else{
//	        	Alert alert = new Alert(Alert.AlertType.ERROR);
//	            alert.setTitle("Datos");
//	            alert.setHeaderText("Faltan datos");
//	            alert.setContentText("Favor de ingresarlos");
//	            alert.showAndWait();
//	            return; 
//	        }
//	        boxPromedioDelSemestre.getSelectionModel().clearSelection();
//	        semestrePromedioDelSemestre.getSelectionModel().clearSelection();
//	    }
//	 @FXML
//	 private void promediodeLaCarreraPorMateria() throws Exception{
//	        if(null!=boxCarreraPorMateria.getSelectionModel().getSelectedItem()&&null!=boxPromedioMateria.getSelectionModel().getSelectedItem()) {
//	        	labelPromedioPorMateria.setText(ProcesosEscuela.obtenerInstancia().promedioDeLaMateriaDeTodosLosAlumnosDelSemestre(boxCarreraPorMateria.getSelectionModel().getSelectedItem(),Integer.parseInt(boxPromedioMateria.getSelectionModel().getSelectedItem().getSemestre()),boxPromedioMateria.getSelectionModel().getSelectedItem().getNombre()));
//	        }else{
//	        	Alert alert = new Alert(Alert.AlertType.ERROR);
//	            alert.setTitle("Datos");
//	            alert.setHeaderText("Faltan datos");
//	            alert.setContentText("Favor de ingresarlos");
//	            alert.showAndWait();
//	            return; 
//	        }
//	        boxCarreraPorMateria.getSelectionModel().clearSelection();
//	        boxPromedioMateria.getSelectionModel().clearSelection();
//	    }
//	 @FXML
//	 private void promediodeLaCarrera() throws Exception{
//	        if(null!=boxCarreraPromedio.getSelectionModel().getSelectedItem()) {
//	        	labelCarrera.setText(ProcesosEscuela.obtenerInstancia().promedioDeLaCarrera(boxCarreraPromedio.getSelectionModel().getSelectedItem()));
//	        }else{
//	        	Alert alert = new Alert(Alert.AlertType.ERROR);
//	            alert.setTitle("Datos");
//	            alert.setHeaderText("Faltan datos");
//	            alert.setContentText("Favor de ingresarlos");
//	            alert.showAndWait();
//	            return; 
//	        }
//	        boxCarreraPromedio.getSelectionModel().clearSelection();
//	    }
	 
	 private void alumnoSeleccionadoPromedioMateria(Estudiante alumno) { 
	        if(null!=alumno) {
	        	promedioPorMateria.setItems(ProcesosEscuela.obtenerInstancia().getMateriasAlumno(alumno));
	        }
	    }
	 private void carreraSeleccionadoPromedioMateria(Carrera carrera) { 
	        if(null!=carrera) {
	        	boxPromedioMateria.setItems(ProcesosEscuela.obtenerInstancia().getMateriasDeLaCarrera(carrera));
	        }
	    }
	
	 
}
