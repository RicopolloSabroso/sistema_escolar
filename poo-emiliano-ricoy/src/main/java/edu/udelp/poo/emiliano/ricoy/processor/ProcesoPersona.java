package edu.udelp.poo.emiliano.ricoy.processor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.time.temporal.ChronoUnit;
import java.util.LinkedList;

public class ProcesoPersona {
	public int calcularIMC(Double peso, Double altura)  throws Exception{
		double imc = (peso/(Math.pow(altura, 2)));
		if(imc < 20) {
			return -1; 
		} else if(imc > 25) {
			return 1;
		}
		return 0; 
	}

//	public long calcularEdad(LocalDate nacimiento) throws Exception {
//		if(null==nacimiento) {
//    		throw new Exception("La fecha de nacimiento no puede ser nulo");
//    	}
//		LocalDate fechaActual = LocalDate.now();
//		return  ChronoUnit.YEARS.between(nacimiento, fechaActual);
//
//	}
//
//	public boolean esMayorDeEdad(LocalDate nacimiento)  throws Exception{
//		if(null==nacimiento) {
//    		throw new Exception("La fecha de nacimiento no puede ser nulo");
//    	}
//		return calcularEdad(nacimiento)>=18;
//	}
	public  String imprimirLista(LinkedList<?> lista) {
		String info="";
        for (Object elemento : lista) {
            info+=elemento.toString()+"\n";
        }
        return info;
    }
	public boolean validarGenero(char genero) {
        return genero == 'H' || genero == 'M';
    }
//	public boolean validarFechaNacimiento(String nacimiento) {
//		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        try {
//            LocalDate.parse(nacimiento, formato);
//            LocalDate fechaActual = LocalDate.now();
//            
//            if (ChronoUnit.YEARS.between(LocalDate.parse(nacimiento, formato), fechaActual)<0) {
//                System.out.println("La fecha de nacimiento no es valida.");
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            System.out.println("Formato incorrecto.\n Utiliza el formato dd/MM/yyyy.");
//            return false;
//        }
//    }
	
	public boolean validarPesoOAltura(Double peso) {
		if(peso != null && peso >= 0) {
    		return true;
    	}
        return false;
    }
//	public boolean buscarPersonaPorID(int id, LinkedList<Persona> lista) {
//        for (Persona persona : lista) {
//            if (persona.getId() == id) {
//                return true;
//            }
//        }
//        return false;
//    }
//	
}
