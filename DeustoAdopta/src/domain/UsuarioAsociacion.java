package domain;

public class UsuarioAsociacion extends Usuario{
	
	private String nombreAsociacion;
	private String descripcion;
	
	
	public UsuarioAsociacion(String correoElectronico, String contrasenia, int telefono,
			ComunidadAutonoma comunidadAutonoma, String direccion, String nombreAsociacion, String descripcion) {
		super(correoElectronico, contrasenia, telefono, comunidadAutonoma, direccion);
		this.nombreAsociacion = nombreAsociacion;
		this.descripcion = descripcion;
	}
	
	
}
