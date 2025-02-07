package domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Principal {
	
	private static ArrayList<Animal> animales = new ArrayList<>();
	private static List<Usuario> usuarios = new ArrayList<>();
	private static ArrayList<SolicitudAdopcion> solicitudAdopcion = new ArrayList<>();
	private static final String nomfichUsuarios = "src/data/DataUsuarios.csv";
	
	// CONSTRUCTOR
	
	public Principal() {
		
		
	}
	
	
	//CONSEGUIR CONTRASEÑA POR SU CORREO
	
	public static String getPassword(String correo) {
		String res = null;
		for(Usuario u : usuarios) {
			if(u.getCorreoElectronico().equals(correo)) {
				res = u.getContrasenia();
			}
		}
		return res;
	}
	
	//GETTERS Y SETTERS

	public static ArrayList<Animal> getAnimales() {
		return animales;
	}

	public static void setAnimales(ArrayList<Animal> animales) {
		Principal.animales = animales;
	}

	public static List<Usuario> getUsuarios() {
		return usuarios;
	}

	public static void setUsuarios(List<Usuario> usuarios) {
		Principal.usuarios = usuarios;
	}

	public static ArrayList<SolicitudAdopcion> getSolicitudesAdopcion() {
		return solicitudAdopcion;
	}

	public static void setSolicitudAdopciones(ArrayList<SolicitudAdopcion> adopciones) {
		Principal.solicitudAdopcion = adopciones;
	}

	
	
	
	//METODOS PARA ANYADIR ELEMENTOS A LAS COLECCIONES
	
	public static void anadirAnimal(Animal a) {
		animales.add(a);
	}
	
	public static void anadirUsuario(Usuario u) {
		usuarios.add(u);
	}
	
	public static void anadirSolicitudAdopcion(SolicitudAdopcion sA) {
		solicitudAdopcion.add(sA);
	}
	
	
	//METODOS PARA IMPRIMIR POR CONSOLA
	
	public static void imprimirUsuarios() {
		for(Usuario u : usuarios) {
			System.out.println(u);
		}
	}
	
	public static void imprimirAnimales() {
		for(Animal a : animales) {
			System.out.println(a);
		}
	}
	
	
	//METODO RECURSIVO PARA BUSCAR USUARIO
	public static Usuario buscarUsuarioRecursivo(String correo, int pos) {
	    if (pos >= usuarios.size()) {
	        return null; // Caso base: si se llega al final de la lista y no se encontró el usuario
	    }

	    Usuario u = usuarios.get(pos);
	    if (u.getCorreoElectronico().equals(correo)) {
	        return u; // Caso base: se encontró el usuario
	    }

	    return buscarUsuarioRecursivo(correo, pos + 1); // Llamada recursiva con la siguiente posición
	}
	
	//METODOS PARA CARGAR Y GUARDAR EN FICHERO
	
	public static void guardarUsuariosEnFichero(String nomfich) {
		try {
			PrintWriter pw = new PrintWriter(nomfich);
			for(Usuario u : usuarios) {
				pw.println(u.getCorreoElectronico()+","+u.getContrasenia());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void cargarUsuariosEnLista(String nomfich) {
		try {
			Scanner sc = new Scanner(new FileReader(nomfich));
			String linea;
			while(sc.hasNext()) {
				linea = sc.nextLine();
				String [] partes = linea.split(",");
				String correo = partes[0];
				String con = partes[1];
				Usuario u = new Usuario(correo, con);
				if(buscarUsuarioRecursivo(correo,0)==null)
					usuarios.add(u);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
		}
		
	}
	
}
