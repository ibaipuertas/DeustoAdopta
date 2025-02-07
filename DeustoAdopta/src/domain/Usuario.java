  package domain;

import java.util.ArrayList;
import java.util.Objects;



public class Usuario implements Comparable<Usuario>{
	

	private int id;
	private String correoElectronico;
	private String contrasenia;
	private int telefono;
	private ComunidadAutonoma comunidadAutonoma;
	private String direccion;
	
	public Usuario(int id, String correoElectronico, String contrasenia, int telefono, ComunidadAutonoma comunidadAutonoma,
			String direccion) {
		
		this.id=id;
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
		this.telefono = telefono;
		this.comunidadAutonoma = comunidadAutonoma;
		this.direccion = direccion;
	}
	
	public Usuario(String correoElectronico, String contrasenia) {
		this.correoElectronico = correoElectronico;
		this.contrasenia = contrasenia;
	}

	public int getId() {
		return id;
	}

	public void setCod(int id) {
		this.id = id;
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
	
	public static Usuario parseCSV(String csvLine) {
		Usuario u = null;
		
		if (csvLine != null) {
			String[] fragmentos = csvLine.split(";");
			//El ID es -1 por defecto
			u = new Usuario(-1, fragmentos[1], fragmentos[2],Integer.parseInt(fragmentos[3]), ComunidadAutonoma.valueOf(fragmentos[4]),fragmentos[5]);
		}
		
		return u;
	}
	
	//MODIFICACIÓN 4: Convierte un Comic en una línea de texto separada por ";"
	public static String toCSV(Usuario usuario) {		
		if (usuario != null) {
			return String.format("%s;%s;%d;%s;%s;%s", usuario.getCorreoElectronico(), usuario.getContrasenia(), usuario.getTelefono(), usuario.getComunidadAutonoma().name(), usuario.getDireccion());
		}

		return "";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	public int compareTo(Usuario u) {
		if(this.id == u.getId()) {
			return 0;
		}
		else if (this.id < u.getId()) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	
	
	
}
