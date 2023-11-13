package clases;

import javax.swing.ImageIcon;

public class Huron extends Animal{

	private boolean chip;

	public Huron(String nombre, String tipo, int edad, ImageIcon foto, Genero genero, Usuario propietario,
			boolean chip) {
		super(nombre, tipo, edad, foto, genero, propietario);
		this.chip = chip;
	}
	
	
}
