package domain;

import java.util.ArrayList;

public class Usuario implements Comparable<Usuario>{
	
	private static int contadorUsuarios;
	private int cod;
	private String correoElectronico;
	private String contrasenia;
	private int telefono;
	private ComunidadAutonoma comunidadAutonoma;
	private String direccion;
	
	public Usuario(String correoElectronico, String contrasenia, int telefono, ComunidadAutonoma comunidadAutonoma,
			String direccion) {
		
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.comunidadAutonoma = comunidadAutonoma;
		this.direccion = direccion;
		this.cod = contadorUsuarios;
		contadorUsuarios  = contadorUsuarios + 1;
	}
	
	public Usuario(String correoElectronico, String contrasenia) {
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public ComunidadAutonoma getComunidadAutonoma() {
		return comunidadAutonoma;
	}

	public void setComunidadAutonoma(ComunidadAutonoma comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String toString() {
		
		return correoElectronico;
	}
	
	public String toStringAFichero() {
			
			return cod +","+correoElectronico+","+contrasenia;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean r = false;
		if (obj instanceof Usuario) {
			Usuario o = (Usuario) obj;
			if (this.getCorreoElectronico().equals(o.getCorreoElectronico())) {
				r = true;
			}
		}
		return r;
	}
	
	public int compareTo(Usuario u) {
		if(this.cod == u.getCod()) {
			return 0;
		}
		else if (this.cod < u.getCod()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
