package domain;

public class SolicitudAdopcion {
	private static long cod;
	private Usuario usuarioSolicitante;
	private Usuario usuarioSolicitado;
	private Animal animal;
	private Boolean estado;
	
	public SolicitudAdopcion(Usuario usuarioSolicitante, Usuario usuarioSolicitado, Animal animal) {
		super();
		this.usuarioSolicitante = usuarioSolicitante;
		this.usuarioSolicitado = usuarioSolicitado;
		this.animal = animal;
		this.estado = false;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public static long getCod() {
		return cod;
	}

	public static void setCod(long cod) {
		SolicitudAdopcion.cod = cod;
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
