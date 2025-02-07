package domain;

public class SolicitudAdopcion {
	private int id;
	private Usuario usuarioSolicitante;
	private Usuario usuarioSolicitado;
	private Animal animal;
	private Boolean estado;
	
	public SolicitudAdopcion(int id, Usuario usuarioSolicitante, Usuario usuarioSolicitado, Animal animal,Boolean estado) {
		super();
		this.id=id;
		this.usuarioSolicitante = usuarioSolicitante;
		this.usuarioSolicitado = usuarioSolicitado;
		this.animal = animal;
		this.estado = estado;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public Usuario getUsuarioSolicitado() {
		return usuarioSolicitado;
	}

	public void setUsuarioSolicitado(Usuario usuarioSolicitado) {
		this.usuarioSolicitado = usuarioSolicitado;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	@Override
	public String toString() {
		return "SolicitudAdopcion [usuarioSolicitante=" + usuarioSolicitante + ", usuarioSolicitado="
				+ usuarioSolicitado + ", animal=" + animal + "]";
	}
	
	
	public void aceptarSolicitud() {
		this.setEstado(true);
	}
	
	
	
}
