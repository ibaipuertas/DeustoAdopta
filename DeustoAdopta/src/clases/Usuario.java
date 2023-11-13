package clases;

import java.util.ArrayList;

public class Usuario {
	
	private ArrayList<Animal> animalesAdoptados;
	private ArrayList<Animal> animalesEnAdopción;
	private static int contadorUsuarios;
	private int cod;
	private String correoElectronico;
	private String contrasenia;
	private int telefono;
	private ComunidadAutonoma comunidadAutonoma;
	private String direccion;
	
	public Usuario(String correoElectronico, String contrasenia, int telefono, ComunidadAutonoma comunidadAutonoma,
			String direccion) {
		
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.comunidadAutonoma = comunidadAutonoma;
		this.direccion = direccion;
		this.cod = contadorUsuarios;
		contadorUsuarios  = contadorUsuarios + 1;
	}
	
	
}
