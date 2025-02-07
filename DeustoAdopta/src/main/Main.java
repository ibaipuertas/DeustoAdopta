package main;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import db.GestorBD;
import domain.Animal;
import domain.ComunidadAutonoma;
import domain.Especie;
import domain.Genero;

import domain.Principal;
import domain.SolicitudAdopcion;

import gui.VentanaInicio;

public class Main {

	public static void main(String[] args) {
		
		GestorBD gestorBD = new GestorBD();
		
		//Se crea la BBDD
		gestorBD.crearBBDD();
		
		//gestorBD.insertarDatosDePrueba();
		
		
		Principal principal = new Principal();

		      
		VentanaInicio vi=new VentanaInicio(principal);
		vi.setVisible(true);
		
		//principal.imprimirAnimales();
	}
	
	
}
