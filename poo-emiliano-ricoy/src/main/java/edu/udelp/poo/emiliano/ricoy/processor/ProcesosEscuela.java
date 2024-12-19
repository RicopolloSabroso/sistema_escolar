package edu.udelp.poo.emiliano.ricoy.processor;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.reflect.TypeToken;

import edu.udelp.poo.emiliano.ricoy.model.Carrera;
import edu.udelp.poo.emiliano.ricoy.model.Estudiante;
import edu.udelp.poo.emiliano.ricoy.model.Materia;
import edu.udelp.poo.emiliano.ricoy.model.MateriaAlumno;
import edu.udelp.poo.emiliano.ricoy.model.Profesor;
//import edu.udelp.poo.util.Archivos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.beans.property.ReadOnlyStringWrapper;


public class ProcesosEscuela {
	private static ProcesosEscuela s = null;
	public Archivos profesorAr=null;
	public Archivos estudianteAr=null;
	public Archivos carreraAr=null;
	public Archivos  materiaAr=null;
	private List<Carrera> carreras=new LinkedList<Carrera>();
	private List<Materia> materias=new LinkedList<Materia>();
	private List<Profesor> profesores=new LinkedList<Profesor>();
	public List<Estudiante> alumnos=new LinkedList<Estudiante>();
	private ProcesosEscuela() {
		profesorAr=new Archivos();
		estudianteAr=new Archivos();
		carreraAr=new Archivos();
		 materiaAr=new Archivos();
		if(carreraAr.existeArchivo(Carrera.class.getName())){
			carreras=carreraAr.leerArchivo(Carrera.class.getName(),new TypeToken<LinkedList<Carrera>>() {}.getType());
		System.out.println(carreras);
		}
		if(materiaAr.existeArchivo(Materia.class.getName())){
			materias=materiaAr.leerArchivo(Materia.class.getName(),new TypeToken<LinkedList<Materia>>() {}.getType());
			System.out.println(materias);
		}
		if(profesorAr.existeArchivo(Profesor.class.getName())){
			 profesores=profesorAr.leerArchivo(Profesor.class.getName(),new TypeToken<LinkedList<Profesor>>() {}.getType());
			 System.out.println(profesores);
		}
		if(estudianteAr.existeArchivo(Estudiante.class.getName())){
			alumnos=estudianteAr.leerArchivo(Estudiante.class.getName(),new TypeToken<LinkedList<Estudiante>>() {}.getType());
			System.out.println(alumnos);
		}
		
	}
	 public static ProcesosEscuela obtenerInstancia() {
	        if (s == null) {
	            s = new ProcesosEscuela();
	        }
	        return s;
	 }
	public ObservableList<Carrera> getNombreCarreras(){
		ObservableList<Carrera> nombres = FXCollections.observableArrayList();
		List<Carrera> carrerasL = new ArrayList<>(getCarreras());
		while(!carrerasL.isEmpty()){
			nombres.add(carrerasL.remove(carrerasL.size()-1));
		}
		return nombres;
	}
	public ObservableList<Materia> getNombreMaterias(){
		ObservableList<Materia> nombres = FXCollections.observableArrayList();
		List<Materia> materiasL = new ArrayList<>(getMaterias());
		while(!materiasL.isEmpty()){
			nombres.add(materiasL.remove(materiasL.size()-1));
		}
		return nombres;
	}
	public ObservableList<Materia> getNombreMateriasSinProfesorDeLaCarrera(Carrera c){
		ObservableList<Materia> nombres = FXCollections.observableArrayList();
		List<Materia> materiasL = new ArrayList<>(getMaterias());
		for(Materia m: materiasL) {
			if(!m.isProfesor()&& c.equals(m.getCarrera())) {
				nombres.add(m);
			}
		}
		return nombres;
	}


	public ObservableList<MateriaAlumno> getMateriasAlumno(Estudiante estudiante){
		ObservableList<MateriaAlumno> nombres = FXCollections.observableArrayList();
		List<MateriaAlumno> materiasL = new ArrayList<>(estudiante.getMaterias());
		while(!materiasL.isEmpty()){
			nombres.add(materiasL.remove(materiasL.size()-1));
		}
		return nombres;
	}
	public ObservableList<Materia> getMateriasProfesor(Profesor profesor){
		ObservableList<Materia> nombres = FXCollections.observableArrayList();
		List<Materia> materiasL = new ArrayList<>(profesor.getMaterias());
		while(!materiasL.isEmpty()){
			nombres.add(materiasL.remove(materiasL.size()-1));
		}
		return nombres;
	}
	public ObservableList<Profesor> getNombreProfesores(){
		ObservableList<Profesor> nombres = FXCollections.observableArrayList();
		List<Profesor> profesoresL = new ArrayList<>(getProfesores());
		while(!profesoresL.isEmpty()){
			nombres.add(profesoresL.remove(profesoresL.size()-1));
		}
		return nombres;
	}
	
	public ObservableList<Estudiante> getNombreEstudiantes(){
		ObservableList<Estudiante> estudiantesO = FXCollections.observableArrayList();
		List<Estudiante> estudiantesL = new ArrayList<>(getAlumnos());
		while(!estudiantesL.isEmpty()){
			estudiantesO.add(estudiantesL.remove(estudiantesL.size()-1)); 
		}
		return estudiantesO;
	}
	public ObservableList<Estudiante> getEstudiantesCarrera(Carrera c){
		ObservableList<Estudiante> estudiantesO = FXCollections.observableArrayList();
		List<Estudiante> estudiantesL = new ArrayList<>(getAlumnos());
		for(Estudiante a:estudiantesL) {
			if(null!=a.getCarrera()) {
				if(a.getCarrera().equals(c)) {
					estudiantesO.add(a);
				}
			}
		}
		return estudiantesO;
	}
	public ObservableList<Carrera> getCarreraProfesorM(Profesor c){
		ObservableList<Carrera> carrerasO = FXCollections.observableArrayList();
		List<Carrera> carreras = new ArrayList<>(getCarreras());
		for(Carrera a:carreras) {
			for(Materia m:c.getMaterias()) {
				if(a.equals(m.getCarrera())) {
					carrerasO.add(a);
				}
			}
		}
		return carrerasO;
	}
	public ObservableList<MateriaAlumno> getMateriasProfesorSemestreC(Profesor profesor, Carrera c, int semestre){
		ObservableList<MateriaAlumno> nombres = FXCollections.observableArrayList();
		List<Materia> materiasL = new ArrayList<>(profesor.getMaterias());
		for(Materia m:materiasL) {
			if(m.getCarrera().equals(c)&& Integer.parseInt( m.getSemestre())==semestre) {
				MateriaAlumno a=new MateriaAlumno(m.getSemestre(),m.getHorario(),m.getNombre(), m.getCarrera());
				a.setProfesor(true);
				nombres.add(a);
			}
		}
		return nombres;
	}
		
	 public ObservableList<Estudiante> alumnosDelProfesor(Profesor profesor){
		 ObservableList<Estudiante> estudiantesProfesor = FXCollections.observableArrayList();
		 List<Materia> profesorM = new ArrayList<>(profesor.getMaterias());
		 for(Estudiante x:alumnos) {
			 if(null!=alumnosDelProfesorMaterias(profesorM, x)) {
				 estudiantesProfesor.add(alumnosDelProfesorMaterias(profesorM, x));
			 }
		 }

		 return estudiantesProfesor;
	 }
	 public Estudiante alumnosDelProfesorMaterias(List<Materia> profesorM, Estudiante a){
		 List<MateriaAlumno> alumnosM =a.getMaterias();	
		 for(Materia m:profesorM) {
				for(MateriaAlumno x:alumnosM) {
						if(m.getNombre().equalsIgnoreCase(x.getNombre())&&m.getCarrera().equals(x.getCarrera())&&m.getSemestre().equals(x.getSemestre())) {
							return a;
						}
				}
				
			}
			return null ;
		}
	 public ObservableList<Estudiante> materiasCalificarAlumnos(Materia m){
		 ObservableList<Estudiante> estudiantesProfesor = FXCollections.observableArrayList();
		 for(Estudiante a:alumnos) {
			 for(MateriaAlumno materias:a.getMaterias()) {
				 if(materias.getCarrera().equals(m.getCarrera())&&materias.getNombre().equals(m.getNombre())&&m.isProfesor()&&a.getSemestre()==Integer.parseInt(m.getSemestre())) {
					 estudiantesProfesor.add(a);
					}
			 }
		 }

		 return estudiantesProfesor;
	 }
	 public ObservableList<Integer> semestresAnteriores( Estudiante alumno){
		 ObservableList<Integer> semestres = FXCollections.observableArrayList();
		 for(int i=1;i<=alumno.getSemestre();i++) {
			 semestres.add(i);
		 }

		 return semestres;
	 }
	public boolean validarCalificacion(Double a) {
		if(a != null && a >= 0&& a <= 10) {
    		return true;
    	}
        return false;
    }
	public List<Materia> getMaterias() {
		return materias;
	}
	public boolean estaVacioMaterias() {
		return null==materias|| materias.isEmpty();
	}
	public boolean estaVacioCarreras() {
		return null==carreras || carreras.isEmpty();
	}
	public boolean estaVacioAlumnos() {

		return null==alumnos ||alumnos.isEmpty();
	}
	public boolean estaVacioProfesores() {

		return null==profesores || profesores.isEmpty();
	}
	public void darDeAltaCarreras(Carrera carrera)throws Exception{
		if(null==carrera) {
			throw new Exception("La carrera no puede ser nulo");
		}
		carreras.add(carrera);
	
	}
	public void darDeAltaMaterias(Materia materia)throws Exception{
		if(null==materia) {
			throw new Exception("La materia no puede ser nulo");
		}
		materias.add(materia);
		
	}
	public void darDeAltaProfesores(Profesor prof)throws Exception{
		if(null==prof) {
			throw new Exception("El profesor no puede ser nulo");
		}
		profesores.add(prof);

	}
	public void darDeAltaAlumnos(Estudiante alumno)throws Exception{
		if(null==alumno) {
			throw new Exception("El alumno no puede ser nulo");
		}
		alumnos.add(alumno);

	}
	public Object devolverCarrera(String nombre) throws Exception{
//		if( null==carreras ) {
//			throw new Exception("Debe existir la Carrera");
//		}
//		System.out.println(((Carrera)carreras.get(0)).getClass());
		for (Object x : carreras) {
			
			if(x instanceof Carrera ) {
				
				Carrera carrera = (Carrera) x;
				
					if(carrera.getNombre().equalsIgnoreCase(nombre)) {
						return carrera;
					}
			}else {
				AbstractMap<String, Object> map = (AbstractMap) x;
				if(map.get("nombre").toString().equalsIgnoreCase(nombre)) {
					return x;
				}
			}
			
		}
		return null;
	}
	public Carrera devolverCarreraProfesor(Profesor p) {
		for (Carrera x : carreras) {
			if(x.getDirector()==Integer.parseInt(p.getId())) {
					return x;
			}
			
		}
		return null;
	}
	public Materia validarMaterias(String nombre)throws Exception{
		if(null==materias ) {
			throw new Exception("Debe existir la materia");
		}
		for (Materia x : materias) {
			if(x.getNombre().equalsIgnoreCase(nombre)) {
				return x;
			}
		}
		return null;
	}
	public Estudiante buscarAlumnoPorID(int id) {
		for (Estudiante alumno : alumnos) {
			if (Integer.parseInt(alumno.getId()) == id) {
				return alumno;
			}
		}
//		for (Object x : alumnos) {
//			
//			if(x instanceof Estudiante ) {
//				
//				Estudiante alumno = (Estudiante) x;
//				
//				if (Integer.parseInt(alumno.getId()) == id) {
//					return alumno;
//				}
//			}else {
//				AbstractMap<String, Object> map = (AbstractMap) x;
//				if(Integer.parseInt(map.get("nombre").toString())==id) {
//					return x;
//				}
//			}
//			
//		}
		return null;
	}
	public Profesor buscarProfesorPorID(int id) {
		for (Profesor profesor : profesores) {
			if (Integer.parseInt(profesor.getId()) == id) {
				return profesor;
			}
		}
		return null;
	}
	public Materia buscarMateria(String nombreMateria) {
		for (Materia materia :materias) {
			if (materia.getNombre().equalsIgnoreCase(nombreMateria)) {
				return materia;
			}
		}
		return null; 
	}
	public boolean materiasAlumnoVacio(Estudiante alumno) {
		return alumno.getMaterias()==null?true:false; 
	}
	public void asignarMateriasProfesor(Integer id,Materia materia)throws Exception{
		if(null==materia|| null==profesores ) {
			throw new Exception("La materia no puede ser nulo o deben haber profesores enlistados");
		}
		for (Profesor profesor : profesores) {
			if(Integer.parseInt(profesor.getId())==id){
				materia.setProfesor(true);
				profesor.addMateria(materia);
			}
		} 
	}
	public void asignarCarreras(Integer id,Carrera carrera)throws Exception{
//		if(null==carrera|| null==carreras ) {
//			throw new Exception("Deben haber carreras enlistadas o no debe ser nula la carrera");
//		}
		for (Estudiante x : alumnos) {
			if (Integer.parseInt(x.getId())== id) {
				x.setCarrera(carrera);
				x.setMaterias(asignarMateriasCarrera(carrera));
			}
		}
		
	}
	public void asignarMateriasACarrera(Carrera carrera)throws Exception{
//		if(null==carrera|| null==carreras ) {
//			throw new Exception("Deben haber carreras enlistadas o no debe ser nula la carrera");
//		}
		for (Estudiante x : alumnos) {
			if (x.getCarrera().equals(carrera)) {
				x.setMaterias(asignarMateriasCarrera(carrera));

			}
		}
	}
	private LinkedList<MateriaAlumno> asignarMateriasCarrera(Carrera carrera ){
		LinkedList<MateriaAlumno> materiasAlumno=new LinkedList<MateriaAlumno>();
		for (Materia x : materias) {
			if (x.getCarrera().equals(carrera)) {
				materiasAlumno.add(new MateriaAlumno(x.getSemestre(),x.getHorario(),x.getNombre(), x.getCarrera()));

			}
		}
		return materiasAlumno;
	}
	public void asignarMaterias(MateriaAlumno nuevaMateria){
		for (Estudiante x : alumnos) {
			if(null!=x.getCarrera()) {
				if (x.getCarrera().equals(nuevaMateria.getCarrera())&&null!=x.getMaterias()) {
					List<MateriaAlumno> materiasAlumno=x.getMaterias();
					materiasAlumno.add(nuevaMateria);
					x.setMaterias(materiasAlumno);

					}
					
				}
			}
			
		}
//	public String getAlumnosConMaterias() {
//		String alumnosInfo="";
//		for (Estudiante alumno : alumnos) {
//			alumnosInfo=alumno.toString();
//		}
//		return alumnosInfo;
//	}
	public List<Estudiante> getAlumnos() {
		return alumnos;
	}
	public void agregarCalificacion( Estudiante alumno,MateriaAlumno materia, double primerParcial, double segundoParcial, double proyecto, double examenFinal) {
		for (Estudiante x : alumnos) {
			if (x.getId()== alumno.getId()) {
				List<MateriaAlumno>listaMaterias=x.getMaterias();
				for (MateriaAlumno m : listaMaterias) {
					if (m.getNombre().equalsIgnoreCase(materia.getNombre())&&m.getCarrera().equals(materia.getCarrera())&&m.getSemestre().equalsIgnoreCase(materia.getSemestre())) {
						(m).setPrimerParcial(primerParcial);
						(m).setExamenFinal(examenFinal);
						(m).setSegundoParcial(segundoParcial);
						(m).setProyecto(proyecto);
						m.setCalificada(true);
						break;
					}
				}
			}
		}
	}
	public Materia validarMateriasAlumno(Estudiante alumno, String nombre)throws Exception{
		for (Materia x : alumno.getMaterias()) {
			if(x.getNombre().equalsIgnoreCase(nombre)) {
				return x;
			}
		}
		return null;
	}
	public String promedioPorMateriaAlumno(Estudiante alumno,String nombreMateria) {
		for (Estudiante x : alumnos) {
			if (x.getId()== alumno.getId()) {
				List<MateriaAlumno>listaMaterias=x.getMaterias();
				for (Materia m : listaMaterias) {
					//if(((MateriaAlumno)m).getPrimerParcial()!=0) {
						if (m.getNombre().equalsIgnoreCase(nombreMateria)) {
							Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();							
							return "Promedio de "+nombreMateria+" es: "+ result/4;
						}
					//}
					
				}
			}
		}
		return null;
	}
	 public Double calcularPromedio(LinkedList<Double> numeros) {
	        if (numeros == null || numeros.isEmpty()) {
	            return null; 
	        }
	        double suma = 0;
	        for (Double numero : numeros) {
	            suma += numero;
	        }

	        return (double) suma / numeros.size();
	    }
	public String promedioPorSemestreDelAlumno(Estudiante alumno, int semestre) {
		LinkedList<Double> promedios=new LinkedList<>();
		String promedio="";
		for (Estudiante x : alumnos) {
			if (x.getId()== alumno.getId()) {
				promedio=x.getNombre()+" del Semestre "+semestre+"\n";
				List<MateriaAlumno>listaMaterias=x.getMaterias();
				for (Materia m : listaMaterias) {
					if(semestre==Integer.parseInt(m.getSemestre())&&((MateriaAlumno)m).getPrimerParcial()!=0) {
						if(m.getSemestre().equalsIgnoreCase(String.valueOf(semestre))) {
							Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();
							promedios.add(result/4);
							promedio+="Promedio de "+m.getNombre()+" en el semestre " +semestre+": "+result/4+"\n";
						}
					}
					
				}

				break;
			}
		}
		return promedio+"Promedio del semestre: "+calcularPromedio(promedios)+"\n";
		
	}
	public Double promedioPorSemestreDelAlumnoDoble(Estudiante alumno, int semestre) {
		LinkedList<Double> promedios=new LinkedList<>();
		for (Estudiante x : alumnos) {
			if (x.getId()== alumno.getId()) {
				List<MateriaAlumno>listaMaterias=x.getMaterias();
				for (Materia m : listaMaterias) {
					if(((MateriaAlumno)m).getPrimerParcial()!=0) {
						if(m.getSemestre().equalsIgnoreCase(String.valueOf(semestre))) {
							Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();
							promedios.add(result/4);
						}
					}
						
				}
				
				break;
			}
		}
		return calcularPromedio(promedios);
		
	}

	
	public List<Profesor> getProfesores() {
		return profesores;
	}
	public String promedioDeTodosLosAlumnosDelSemestre(Carrera carrera, int semestre) {
		String promedio="";
		for (Estudiante x : alumnos) {
			if (x.getCarrera().equals(carrera) && x.getSemestre()==semestre) {
				promedio+=promedioPorSemestreDelAlumno(x,semestre)+"\n";
			}
		}
		LinkedList<Double> promediosNum=new LinkedList<>();
		for (Estudiante x : alumnos) {
			if (x.getCarrera().equals(carrera) && x.getSemestre()==semestre) {
				promediosNum.add(promedioPorSemestreDelAlumnoDoble(x, semestre));
			}
		}
		return promedio+"Promedio del semestre de todos los alumnos: "+calcularPromedio(promediosNum);
		
	}
	public ObservableList<Materia> getMateriasDeLaCarrera(Carrera carrera) {
		ObservableList<Materia> mat=FXCollections.observableArrayList();
		for (Materia materia :materias) {
			if (materia.getCarrera().equals(carrera)) {
				mat.add(materia);
			}
			
		}
		return mat;
	}
	
	public List<Carrera> getCarreras() {
		return carreras;
	}
	public String promedioDeLaMateriaDeTodosLosAlumnosDelSemestre(Carrera carrera, int semestre,String nombreMateria) {
		LinkedList<Double> promedios=new LinkedList<>();
		for (Estudiante x : alumnos) {
			if (x.getCarrera().equals(carrera)) {
				List<MateriaAlumno>listaMaterias=x.getMaterias();
				for (Materia m : listaMaterias) {
					if (m.getNombre().equalsIgnoreCase(nombreMateria)&& Integer.parseInt( m.getSemestre())==semestre) {
							Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();
							promedios.add(result/4) ;
						
					}
					
					
				}
			}
		}
		return "Promedio de la materia "+nombreMateria+" de todos los alumnos: "+calcularPromedio(promedios);
		
	}
	public String promedioDeLaMateriaDeTodosLosAlumnos(Carrera carrera,Materia nombreMateria) {
		LinkedList<Double> promedios=new LinkedList<>();
		for (Estudiante x : alumnos) {
			if (x.getCarrera().equals(carrera)) {
				List<MateriaAlumno>listaMaterias=x.getMaterias();
				for (Materia m : listaMaterias) {
					if (m.equals(nombreMateria)) {
							Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();
							promedios.add(result/4) ;
						
					}
					
					
				}
			}
		}
		return "Promedio de la materia "+nombreMateria+" de todos los alumnos: "+calcularPromedio(promedios);
		
	}
	public String promedioDeLaCarrera(Carrera carrera) {
		LinkedList<Double> promediosAlumnos=new LinkedList<Double>();
		for (Estudiante x : alumnos) {
			if (x.getCarrera().equals(carrera)) {
				LinkedList<Double> promediosMaterias=new LinkedList<>();
				List<MateriaAlumno>listaMaterias=x.getMaterias();
				for (Materia m : listaMaterias) {
					if(((MateriaAlumno)m).getPrimerParcial()!=0) {
						Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();
						promediosMaterias.add(result/4);
					}
				}
				promediosAlumnos.add(calcularPromedio(promediosMaterias));
			}
		}
		return "Promedio de la Carrera de todos los alumnos: "+calcularPromedio(promediosAlumnos);
		
	}
	public boolean contrasena(String contrasena) {
	    if (contrasena.length() < 8) {
	        return false;
	    }
	    int contadorNumero = 0;
	    int contadorMayuscula = 0;
	    int contadorCaracter = 0;
	    for (char x : contrasena.toCharArray()) {
	        if (Character.isDigit(x)) {
	            contadorNumero++;
	        } else if (Character.isUpperCase(x)) {
	            contadorMayuscula++;
	        } else if (String.valueOf(x).matches("[!@#$%^&*]")) {  
	            contadorCaracter++;
	        }
	    }
	    
	    return contadorNumero >= 2 && contadorMayuscula >= 1 && contadorCaracter >= 1;
	}
	  public boolean validarUsuario(String cadena) {
		  for(Estudiante a:alumnos) {
			  if( !cadena.matches("[a-zA-Z0-9]+")|| cadena.equalsIgnoreCase(a.getUsuario().getUsuario())) {
				  return false;
			  }
		  }
		  for(Profesor a:profesores) {
			  if( !cadena.matches("[a-zA-Z0-9]+")|| cadena.equalsIgnoreCase(a.getUsuario().getUsuario())) {
				  return false;
			  }
		  }
	        return true;
	    }
	  public boolean validarFechaNacimiento(String fecha) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
	        try {
	            LocalDate fechaNacimiento = LocalDate.parse(fecha, formatter);
	            return esMayorDe17(fechaNacimiento);
	        } catch (DateTimeParseException e) {
	            return false;
	        }
	    }
		public boolean validarFechaNacimientoProfesor(String fecha) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
	        try {
	            LocalDate fechaNacimiento = LocalDate.parse(fecha, formatter);
	            return esMayorDe30(fechaNacimiento);
	        } catch (DateTimeParseException e) {
	            return false;
	        }
	    }
	    
	    private boolean esMayorDe17(LocalDate fechaNacimiento) {
	        LocalDate fechaActual = LocalDate.now();
	        Period edad = Period.between(fechaNacimiento, fechaActual);
	        return edad.getYears() >= 17;
	    }
	    private boolean esMayorDe30(LocalDate fechaNacimiento) {
	        LocalDate fechaActual = LocalDate.now();
	        Period edad = Period.between(fechaNacimiento, fechaActual);
	        return edad.getYears() >= 30;
	    }
	    public boolean esAnioValido(String anio) {
	        if (anio == null || anio.length() != 4) {
	            return false;
	        }
	        
	        try {  
	            int anioInt = Integer.parseInt(anio);
	            return anioInt > 0;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
	    public String generarClave(Estudiante a) {
	    	int numero= alumnos.size()+1;
	        String numeroAlumno = String.format("%05d", numero);
	        String clave = a.getFechaIngreso()+"-" + numeroAlumno;
	        return clave;
	    }
	    public String generarClaveProfesor(Profesor a) {
	    	int numero= profesores.size()+1;
	        String numeroAlumno = String.format("%05d", numero);
	        String clave = a.getFechaIngreso()+"-" + numeroAlumno;
	        return clave;
	    }
	    public boolean esEmailValido(String email) {
	    	String emailRegex= "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	        Pattern pattern = Pattern.compile(emailRegex);
	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	    public ObservableList<Estudiante> getEstudiantesDelSemestre(Carrera carrera, int semestre){
			ObservableList<Estudiante> estudiantesO = FXCollections.observableArrayList();
			List<Estudiante> estudiantesL = new ArrayList<>(getAlumnos());
			while(!estudiantesL.isEmpty()){
				Estudiante est=estudiantesL.remove(estudiantesL.size()-1);
				if(null!=est.getCarrera()) {
					if(carrera.equals(est.getCarrera()) && semestre<=est.getSemestre()) {
						estudiantesO.add(est); 
					}
				}
				
			}
			return estudiantesO;
		}
	    public ObservableList<Estudiante> getEstudiantesDelSemestreProfe(Carrera carrera, int semestre){
			ObservableList<Estudiante> estudiantesO = FXCollections.observableArrayList();
			List<Estudiante> estudiantesL = new ArrayList<>(getAlumnos());
			while(!estudiantesL.isEmpty()){
				Estudiante est=estudiantesL.remove(estudiantesL.size()-1);
				if(carrera.equals(est.getCarrera()) && semestre==est.getSemestre()) {
					estudiantesO.add(est); 
				}
			}
			return estudiantesO;
		}
	    public ObservableList<Estudiante> getEstudiantesDelSemestreDelProfesor(Carrera carrera,List<Materia> p, int semestre){
			ObservableList<Estudiante> estudiantesO = FXCollections.observableArrayList();
			for(Estudiante a:getEstudiantesDelSemestreProfe(carrera, semestre)){
				if(null!=materiasAlumnoProfesor(p,a.getMaterias())) {
					estudiantesO.add(a);
				}
			}
			return estudiantesO;
		}
	    public MateriaAlumno materiasAlumnoProfesor(List<Materia> p, List<MateriaAlumno>a){
			for(Materia materiaP:p){
				for(MateriaAlumno materiaA:a) {
					if(materiaP.getCarrera().equals(materiaA.getCarrera())&& materiaP.getNombre().equals(materiaA.getNombre())&&materiaP.getSemestre().equals(materiaA.getSemestre())) {
						return materiaA;
					}
				}
			}
			return null;
		}
	    public ObservableList<MateriaAlumno> getMateriasAlumnoDelProfesor(List<Materia> p, List<MateriaAlumno>a){
			ObservableList<MateriaAlumno> nombres = FXCollections.observableArrayList();
			for(Materia materiaP:p){
				for(MateriaAlumno materiaA:a) {
					if(materiaP.getCarrera().equals(materiaA.getCarrera())&& materiaP.getNombre().equals(materiaA.getNombre())&&materiaP.getSemestre().equals(materiaA.getSemestre())) {
						nombres.add(materiaA);
					}
				}
			}
			return nombres;
		}
	    public ObservableList<Estudiante> getEstudiantesDelSemestreCerrar(Carrera carrera, int semestre){
			ObservableList<Estudiante> estudiantesO = FXCollections.observableArrayList();
			List<Estudiante> estudiantesL = new ArrayList<>(getAlumnos());
			while(!estudiantesL.isEmpty()){
				Estudiante est=estudiantesL.remove(estudiantesL.size()-1);
				if(carrera.equals(est.getCarrera()) && semestre==est.getSemestre()&& est.getTitulados().equals("No")) {
					estudiantesO.add(est); 
				}
			}
			return estudiantesO;
		}
	    public ObservableList<MateriaAlumno> getMateriasDelSemestreCarrera(Carrera carrera, int semestre){
			ObservableList<MateriaAlumno> materiaO = FXCollections.observableArrayList();
			List<Materia> materiasL = new ArrayList<>(getMaterias());
			while(!materiasL.isEmpty()){
				Materia est=materiasL.remove(materiasL.size()-1);
				if(carrera.equals(est.getCarrera()) && semestre==Integer.parseInt(est.getSemestre())) {
					MateriaAlumno a=new MateriaAlumno(est.getSemestre(),est.getHorario(),est.getNombre(), est.getCarrera());
					a.setProfesor(true);
					materiaO.add(a); 
				}
			}
			return materiaO;
		}
	    
	    public ObservableList<MateriaAlumno> getMateriasDelSemestreDelAlumno(Estudiante a, int semestre){
			ObservableList<MateriaAlumno> materiaO = FXCollections.observableArrayList();
			List<MateriaAlumno> materiasL = new ArrayList<>(a.getMaterias());
			for (MateriaAlumno m : materiasL) {
				if(Integer.parseInt(m.getSemestre())==semestre) {
					materiaO.add(m);
				}
			}
			return materiaO;
		}
	    public ObservableList<Estudiante> getAlumnosDelSemestreDeLaMateria(Carrera c,MateriaAlumno a, int semestre){
			ObservableList<Estudiante> est = FXCollections.observableArrayList();
			for(Estudiante alumno:getEstudiantesCarrera(c)) {
				List<MateriaAlumno> materiasL = new ArrayList<>(alumno.getMaterias());
				for (MateriaAlumno m : materiasL) {
					if(m.getNombre().equals(a.getNombre())&&m.getHorario().equals(a.getHorario())&&alumno.getSemestre()>=semestre) {
						est.add(alumno);
						break;
					}
				}
			}
			
			return est;
		}
	    
		public String getAlumnosInfoCompleta(List<Estudiante> a) {
		String alumnosInfo="";
		for (Estudiante alumno : a) {
			alumnosInfo+=alumno.InfoEstudianteCompleto()+"\n";
		}
		return alumnosInfo;
	}
		public String getAlumnosInfoCompletaMateriasSemestre(List<Estudiante> a, int semestre) {
			String alumnosInfo="";
			for (Estudiante alumno : a) {
				alumnosInfo+="\n"+alumno.infoSemestre();
				for(MateriaAlumno m:alumno.getMaterias()) {
					if(Integer.parseInt(m.getSemestre())==semestre) {
						alumnosInfo+="\n"+m.info();
					}
				}
			}
			return alumnosInfo;
		}
		public String getAlumnoInfoCompletaMateriasSemestre(Estudiante a, int semestre) {
			String alumnosInfo=a.infoSemestre();
				for(MateriaAlumno m:a.getMaterias()) {
					if(Integer.parseInt(m.getSemestre())==semestre) {
						alumnosInfo+="\n"+m.info();
					}
				}
			
			return alumnosInfo;
		}
		public String getAlumnosInfoCompletaProfesor(List<Materia>p,List<Estudiante> a) {
			String alumnosInfo="";
			for (Estudiante alumno : a) {
				for(MateriaAlumno m:getMateriasAlumnoDelProfesor(p,alumno.getMaterias()))
				alumnosInfo+=alumno+"\n"+m.info()+"\n";
			}
			return alumnosInfo;
		}
		public String getAlumnosInfoCompletaMateria(MateriaAlumno a) {
			String alumnosInfo="";
			for (Estudiante alumno : alumnos) {
				List<MateriaAlumno> mat=alumno.getMaterias();
				for(MateriaAlumno materia:mat) {
					if(materia.getCarrera().equals(a.getCarrera())&&materia.getNombre().equals(a.getNombre())&&materia.getSemestre().equals(a.getSemestre())&&alumno.getSemestre()>=Integer.parseInt(a.getSemestre())) {
						alumnosInfo+=alumno.infoSemestre()+ materia.info()+"\n";
					}
				}
			}
			return alumnosInfo;
		}
		
		public boolean validarMateriasCalificadas(Estudiante a) {
			List<MateriaAlumno> materias=a.getMaterias();
			if (materias.isEmpty()) {
				return false;
			}

			for (MateriaAlumno materia : materias) {
				if(Integer.parseInt(materia.getSemestre())<=a.getSemestre()) {
					if (!materia.isCalificada()) {
						return false;
					}
				}
			}


			return true;
		}
		public boolean promedioSemestreDelAlumno(Estudiante alumno, int semestre) {
			LinkedList<Double> promedios=new LinkedList<>();
					List<MateriaAlumno>listaMaterias=alumno.getMaterias();
					for (Materia m : listaMaterias) {
							if(m.getSemestre().equalsIgnoreCase(String.valueOf(semestre))) {
								Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();
								promedios.add(result/4);
							}
					}
			return calcularPromedio(promedios)>=6;
			
		}
		 public boolean validarMateriasAprobadasDelSemestre(Estudiante a) {
		            if (!promedioSemestreDelAlumno(a,a.getSemestre())) {
		                return false;
		            }

		        return true;
		    }
		 public Double promedioDeTodasLasMaterias(Estudiante alumno) {
			 LinkedList<Double> promedios=new LinkedList<>();
			 List<MateriaAlumno>listaMaterias=alumno.getMaterias();
			 for (Materia m : listaMaterias) {
						 Double result=((MateriaAlumno)m).getPrimerParcial()+((MateriaAlumno)m).getSegundoParcial()+((MateriaAlumno)m).getProyecto()+((MateriaAlumno)m).getExamenFinal();
						 promedios.add(result/4);
			 }
			 return calcularPromedio(promedios);
		}
			
		 public Estudiante cerrarSemestre(List<Estudiante> a) {
			 
				for (Estudiante alumno : a) {
					if(!validarMateriasCalificadas(alumno)) {
						return alumno;
					}
				}
				for (Estudiante alumno : a) {
					if(validarMateriasAprobadasDelSemestre(alumno)) {
						if(alumno.getSemestre()!=8) {
							alumno.setSemestre(alumno.getSemestre()+1);
						}else if(promedioDeTodasLasMaterias(alumno)>=6){
							alumno.setTitulado(true);
						}
					}
				}
				
				return null;
				
			}
}
	