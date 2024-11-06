package domain;

public enum Especie {
	GATO("Gato"),
	PERRO("Perro"), 
	HURON("Hurón"), 
	AVE("Ave"), 
	PEZ("Pez"), 
	REPTIL("Reptil"), 
	ROEDOR("Roedor");

	private String nombre;
	
	Especie(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEspecie() {
		return this.nombre;
	}


}
