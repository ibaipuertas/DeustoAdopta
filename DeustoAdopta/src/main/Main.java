package main;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Animal;
import domain.Especie;
import domain.Genero;
//import db.bd;
import domain.Principal;
import gui.VentanaInicio;

public class Main {

	public static void main(String[] args) {
		Principal principal = new Principal();
		//Concesionario conc=new Concesionario();
		//bd bdd=new bd();
		//bdd.crearBBDD();
//		try {
//			bdd.cargarCochesBDDConcesionario();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		Animal animal1= new Animal("Rocky",Especie.PERRO,"Pastor belga",1, Genero.HEMBRA, null, null);
		Animal animal2= new Animal("Paco",Especie.AVE,"Loro",2, Genero.HEMBRA, null,null);
		Animal animal3= new Animal("Michi",Especie.GATO,"Gato Persa",1, Genero.HEMBRA, null,null);
		Animal animal4= new Animal("Gozilla",Especie.REPTIL,"Gecko",3, Genero.HEMBRA, null, null);
		
		principal.anadirAnimal(animal1);
		principal.anadirAnimal(animal2);
		principal.anadirAnimal(animal3);
		principal.anadirAnimal(animal4);
		
		
		VentanaInicio vi=new VentanaInicio(principal);
		vi.setVisible(true);
		
		principal.imprimirAnimales();
	}
}
