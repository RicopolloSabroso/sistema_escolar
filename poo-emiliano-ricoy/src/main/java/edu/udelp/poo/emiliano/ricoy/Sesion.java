package edu.udelp.poo.emiliano.ricoy;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;

import edu.udelp.poo.emiliano.ricoy.model.Carrera;
import edu.udelp.poo.emiliano.ricoy.model.Estudiante;
import edu.udelp.poo.emiliano.ricoy.model.Materia;
import edu.udelp.poo.emiliano.ricoy.model.Profesor;
import edu.udelp.poo.emiliano.ricoy.model.Rol;
import edu.udelp.poo.emiliano.ricoy.model.Usuario;
import edu.udelp.poo.emiliano.ricoy.processor.ProcesosEscuela;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Sesion {
	@FXML
	private TextField usuario;

	@FXML
	private PasswordField contrasena;


	@FXML
	private void iniciarSesion() throws IOException {
		String user = usuario.getText();
		String password = contrasena.getText();
		usuario.clear();
		contrasena.clear();
		if (user.equals("admin") && password.equals("Admin12@")) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Inicio de sesion");
			alert.setContentText("Iniciaste sesion como administrador");
			alert.showAndWait();
			cargarControlEscolar();
			return;
		}else {
			List<Estudiante> alumnos =ProcesosEscuela.obtenerInstancia().getAlumnos();
			for (Estudiante estudiante : alumnos) {
				if (estudiante.getUsuario().getUsuario().equals(user) && estudiante.getUsuario().getPassword().equals(password)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Inicio de sesion");
					alert.setContentText("Iniciaste sesion como "+estudiante.getUsuario().getRol());
					alert.showAndWait();
					FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/"+"Estudiante" + ".fxml"));
					Parent root = loader.load();
					EstudianteController controller = loader.getController();
					controller.initData(estudiante);
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.show();
					return;
				}
				List<Profesor> profesores = ProcesosEscuela.obtenerInstancia().getProfesores();
				for (Profesor profesor : profesores) {
					if (profesor.getUsuario().getUsuario().equals(user) && profesor.getUsuario().getPassword().equals(password)&& profesor.isEsDirector()) {
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Inicio de sesion");
						alert.setContentText("Iniciaste sesion como "+profesor.getUsuario().getRol());
						alert.showAndWait();
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/"+"director" + ".fxml"));
						Parent root = loader.load();
						DirectorController controller = loader.getController();
						controller.initData(profesor);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.show();
						return;
					}else if(profesor.getUsuario().getUsuario().equals(user) && profesor.getUsuario().getPassword().equals(password)){
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Inicio de sesion");
						alert.setContentText("Iniciaste sesion como "+profesor.getUsuario().getRol());
						alert.showAndWait();
						FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/"+"Profesores" + ".fxml"));
						Parent root = loader.load();
						ProfesorController controller = loader.getController();
						controller.initData(profesor);
						Scene scene = new Scene(root);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.show();
						return;
					}
				}
				
			}
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Usuario o contraseña incorrectos");
			alert.showAndWait();
		}


	}	


	private void cargarControlEscolar() {
		try {
			MainApp.setRoot("controlEscolar");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//	private void cargarVistaProfesores() {
//		try {
//			MainApp.setRoot("ControlProfesoresSesion");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
