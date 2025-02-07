package domain;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

public class Animal implements Comparable<Animal>{
	
	private int id;
	private String nombre;
	private Especie especie;
	private String raza;
	private int edad;
	private Genero genero;
	private Usuario propietario;
	private String fotoAnimal;
	
	public Animal(int id, String nombre,Especie especie, String raza, int edad, Genero genero, String fotoAnimal,  Usuario propietario) {
		super();
		this.id = id;;
		this.nombre = nombre;
		this.especie = especie;
		this.raza = raza;
		this.edad = edad;
		this.genero = genero;
		this.propietario = propietario;
		this.fotoAnimal = fotoAnimal;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public String getFotoAnimal() {
		return fotoAnimal;
	}

	public void setFotoAnimal(String fotoAnimal) {
		this.fotoAnimal = fotoAnimal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String tipo) {
		this.raza = tipo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
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
	
	public String toString() {
		return especie +" "+ raza +" "+ propietario.toString() ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return id == other.id && edad == other.edad && especie == other.especie
				&& Objects.equals(fotoAnimal, other.fotoAnimal) && genero == other.genero
				&& Objects.equals(nombre, other.nombre) && Objects.equals(propietario, other.propietario)
				&& Objects.equals(raza, other.raza);
	}
	
	public int compareTo(Animal a) {
		if (this.id == a.id) {
			return 0;
		}
		else return 1;
	}
	
}
