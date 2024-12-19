package edu.udelp.poo.emiliano.ricoy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import edu.udelp.poo.emiliano.ricoy.model.Carrera;
import edu.udelp.poo.emiliano.ricoy.model.Estudiante;
import edu.udelp.poo.emiliano.ricoy.model.Materia;
import edu.udelp.poo.emiliano.ricoy.model.Profesor;
import edu.udelp.poo.emiliano.ricoy.processor.ProcesosEscuela;


public class MainApp extends Application {
    private static Stage stage;
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
        stage=s;
        setRoot("inicioSesion");
        stage.setOnCloseRequest(this::cerrarAplicacion);
    }
    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
        
    }

    static void setRoot(String fxml, String title) throws IOException {
        Scene scene = new Scene(loadFXML(fxml));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }
    private void cerrarAplicacion(WindowEvent event) {
        if (null != ProcesosEscuela.obtenerInstancia().getAlumnos()) {
        	ProcesosEscuela.obtenerInstancia().estudianteAr.guardaArchivo(Estudiante.class.getName(), ProcesosEscuela.obtenerInstancia().getAlumnos());
        }
        if(null != ProcesosEscuela.obtenerInstancia().getProfesores()){
        	ProcesosEscuela.obtenerInstancia().profesorAr.guardaArchivo(Profesor.class.getName(), ProcesosEscuela.obtenerInstancia().getProfesores());
        }
        if(null != ProcesosEscuela.obtenerInstancia().getCarreras()){
        	ProcesosEscuela.obtenerInstancia().carreraAr.guardaArchivo(Carrera.class.getName(), ProcesosEscuela.obtenerInstancia().getCarreras());
        }
        if(null != ProcesosEscuela.obtenerInstancia().getMaterias()){
        	ProcesosEscuela.obtenerInstancia().materiaAr.guardaArchivo(Materia.class.getName(), ProcesosEscuela.obtenerInstancia().getMaterias());
        }
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
