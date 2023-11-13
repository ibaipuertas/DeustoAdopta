package clases;

import javax.swing.ImageIcon;

public class Perro extends Animal{
	
	private boolean chip;

	public Perro(String nombre, String tipo, int edad, ImageIcon foto, Genero genero, Usuario propietario,
			boolean chip) {
		super(nombre, tipo, edad, foto, genero, propietario);
		this.chip = chip;
	}
	
}
