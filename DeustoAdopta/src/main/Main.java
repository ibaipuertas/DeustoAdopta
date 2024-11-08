package main;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Animal;
import domain.ComunidadAutonoma;
import domain.Especie;
import domain.Genero;
import domain.UsuarioPersona;
//import db.bd;
import domain.Principal;
import domain.SolicitudAdopcion;
import gui.VentanaInicio;

public class Main {

	public static void main(String[] args) {
		
		Principal principal = new Principal();

		
		UsuarioPersona usuario1 = new UsuarioPersona("juan.perez@gmail.com", "password123", 600123456, ComunidadAutonoma.MADRID, "Calle Mayor, 10", "Juan", "Pérez", "García", 12345678);
		UsuarioPersona usuario2 = new UsuarioPersona("maria.lopez@yahoo.com", "contrasena456", 611654321, ComunidadAutonoma.CATALUNIA, "Av. Diagonal, 22", "María", "López", "Martínez", 23456789);
		UsuarioPersona usuario3 = new UsuarioPersona("carlos.gonzalez@outlook.com", "pass789", 622789456, ComunidadAutonoma.ANDALUCIA, "Calle de la Luz, 3", "Carlos", "González", "Fernández", 34567890);
		UsuarioPersona usuario4 = new UsuarioPersona("ana.ramirez@hotmail.com", "clave101112", 633987654, ComunidadAutonoma.GALICIA, "Rua Nova, 15", "Ana", "Ramírez", "Sánchez", 45678901);
		UsuarioPersona usuario5 = new UsuarioPersona("pedro.diaz@gmail.com", "mipass123", 644213987, ComunidadAutonoma.EUSKADI, "Calle Estrella, 8", "Pedro", "Díaz", "Puertas", 347434523);
		UsuarioPersona usuario6 = new UsuarioPersona("aa@aa", "aaa", 644213987, ComunidadAutonoma.EUSKADI, "Calle Estrella, 8", "Ibai", "Puertas", "Quilchano", 347434523);
		
		Animal animal1 = new Animal("Max", Especie.PERRO, "Labrador", 3, Genero.MACHO, usuario1, "fotoperro1.jpg");
		Animal animal2 = new Animal("Luna", Especie.GATO, "Siames", 2, Genero.HEMBRA, usuario1, null);
		Animal animal3 = new Animal("Kiwi", Especie.AVE, "Periquito", 1, Genero.MACHO, usuario1, null);
		Animal animal4 = new Animal("Coco", Especie.HURON, "Doméstico", 3, Genero.MACHO, usuario1, null);
		Animal animal5 = new Animal("Fluffy", Especie.ROEDOR, "Cobaya", 1, Genero.HEMBRA, usuario1, null);

		Animal animal6 = new Animal("Charlie", Especie.PERRO, "Bulldog Francés", 4, Genero.MACHO, usuario2, "fotoperro2.jpg");
		Animal animal7 = new Animal("Nala", Especie.GATO, "Persa", 5, Genero.HEMBRA, usuario2, null);
		Animal animal8 = new Animal("Goldie", Especie.PEZ, "Goldfish", 1, Genero.MACHO, usuario2, "fotopez1.jpg");
		Animal animal9 = new Animal("Mango", Especie.AVE, "Canario", 2, Genero.MACHO, usuario6, null);
		Animal animal10 = new Animal("Zorro", Especie.REPTIL, "Gecko", 3, Genero.MACHO, usuario2, null);

		Animal animal11 = new Animal("Rocky", Especie.PERRO, "Golden Retriever", 6, Genero.MACHO, usuario3, null);
		Animal animal12 = new Animal("Lola", Especie.GATO, "Maine Coon", 3, Genero.HEMBRA, usuario6, null);
		Animal animal13 = new Animal("Jazmín", Especie.AVE, "Cacatúa", 2, Genero.HEMBRA, usuario3, null);
		Animal animal14 = new Animal("Sasha", Especie.HURON, "Doméstico", 2, Genero.MACHO, usuario3, null);
		Animal animal15 = new Animal("Dory", Especie.PEZ, "Pez Cirujano Azul", 1, Genero.HEMBRA, usuario3, null);

		Animal animal16 = new Animal("Bella", Especie.PERRO, "Pastor Alemán", 4, Genero.HEMBRA, usuario4, null);
		Animal animal17 = new Animal("Maya", Especie.GATO, "Siamés", 2, Genero.HEMBRA, usuario4, null);
		Animal animal18 = new Animal("Rex", Especie.REPTIL, "Iguana", 5, Genero.MACHO, usuario6, null);
		Animal animal19 = new Animal("Pepe", Especie.ROEDOR, "Hamster", 1, Genero.MACHO, usuario6, null);
		Animal animal20 = new Animal("Lynx", Especie.PERRO, "Labrador", 3, Genero.MACHO, usuario4, null);
		
		principal.anadirAnimal(animal1);
		principal.anadirAnimal(animal2);
		principal.anadirAnimal(animal3);
		principal.anadirAnimal(animal4);
		principal.anadirAnimal(animal5);

		principal.anadirAnimal(animal6);
		principal.anadirAnimal(animal7);
		principal.anadirAnimal(animal8);
		principal.anadirAnimal(animal9);
		principal.anadirAnimal(animal10);

		principal.anadirAnimal(animal11);
		principal.anadirAnimal(animal12);
		principal.anadirAnimal(animal13);
		principal.anadirAnimal(animal14);
		principal.anadirAnimal(animal15);

		principal.anadirAnimal(animal16);
		principal.anadirAnimal(animal17);
		principal.anadirAnimal(animal18);
		principal.anadirAnimal(animal19);
		principal.anadirAnimal(animal20);
	
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario1, animal1));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario1, animal2));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario2, animal6));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario2, animal7));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario2, animal8));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario3, animal11));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario3, animal13));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario3, animal14));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario4, animal16));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario4, animal17));

        // Solicitudes hacia usuario6 como propietario (usuario6 es el solicitado)
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario1, usuario6, animal18));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario2, usuario6, animal18));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario3, usuario6, animal18));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario4, usuario6, animal18));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario5, usuario6, animal18));
        
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario1, usuario6, animal19));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario2, usuario6, animal19));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario3, usuario6, animal19));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario4, usuario6, animal19));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario5, usuario6, animal19));
        
        // Solicitudes adicionales para completar las 30
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario1, animal2));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario2, animal7));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario3, animal14));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario4, animal16));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario6, usuario5, animal1));

        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario2, usuario6, animal18));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario3, usuario6, animal18));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario4, usuario6, animal18));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario5, usuario6, animal19));
        principal.anadirSolicitudAdopcion(new SolicitudAdopcion(usuario1, usuario6, animal19));
        
		VentanaInicio vi=new VentanaInicio(principal);
		vi.setVisible(true);
		
		principal.imprimirAnimales();
	}
}
