package clases;

public class UsuarioPersona extends Usuario{
	
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int dni;
	
	
	public UsuarioPersona(String correoElectronico, String contrasenia, int telefono,
			ComunidadAutonoma comunidadAutonoma, String direccion, String nombre, String apellido1, String apellido2,
			int dni) {
		super(correoElectronico, contrasenia, telefono, comunidadAutonoma, direccion);
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
	}
	
	
	
}
