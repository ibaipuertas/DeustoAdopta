package clases;

import javax.swing.ImageIcon;

public class Animal {
	
	private static int contadorAnimales = 0;
	private int cod;
	private String nombre;
	private String tipo;
	private int edad;
	private ImageIcon foto;
	private Genero genero;
	private Usuario propietario;
	
	public Animal(String nombre, String tipo, int edad, ImageIcon foto, Genero genero, Usuario propietario) {
		super();
		this.cod = contadorAnimales;
		contadorAnimales = contadorAnimales +1;
		this.nombre = nombre;
		this.tipo = tipo;
		this.edad = edad;
		this.foto = foto;
		this.genero = genero;
		this.propietario = propietario;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public ImageIcon getFoto() {
		return foto;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	
	
	
	
}
