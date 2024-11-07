package domain;

import java.awt.Image;
import java.util.Objects;

import javax.swing.ImageIcon;

public class Animal implements Comparable<Animal>{
	
	private static int contadorAnimales = 0;
	private int cod;
	private String nombre;
	private Especie especie;
	private String raza;
	private int edad;
	private Genero genero;
	private Usuario propietario;
	private Image fotoAnimal;
	
	public Animal(String nombre,Especie especie, String raza, int edad, Genero genero, Usuario propietario, Image fotoAnimal) {
		super();
		this.cod = contadorAnimales;
		contadorAnimales = contadorAnimales +1;
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

	public Image getFotoAnimal() {
		return fotoAnimal;
	}

	public void setFotoAnimal(Image fotoAnimal) {
		this.fotoAnimal = fotoAnimal;
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
		return Objects.hash(cod, edad, especie, fotoAnimal, genero, nombre, propietario, raza);
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
		return cod == other.cod && edad == other.edad && especie == other.especie
				&& Objects.equals(fotoAnimal, other.fotoAnimal) && genero == other.genero
				&& Objects.equals(nombre, other.nombre) && Objects.equals(propietario, other.propietario)
				&& Objects.equals(raza, other.raza);
	}
	
	public int compareTo(Animal a) {
		if (this.cod == a.cod) {
			return 0;
		}
		else return 1;
	}
	
}
