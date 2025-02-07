package db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import domain.Animal;
import domain.ComunidadAutonoma;
import domain.Especie;
import domain.Genero;
import domain.Principal;
import domain.SolicitudAdopcion;
import domain.Usuario;




public class GestorBD {
	
	public static String DRIVER_NAME;
	public static String DATABASE_FILE;
	public static String CONNECTION_STRING;
	private final String LOG_FOLDER = "resources/log";
	
	
	private static final Logger logger=Logger.getLogger(Principal.class.getName());
	
	
	public GestorBD() {
		
		 try {
	            System.out.println("Iniciando conexión a la base de datos...");
	            
	            Properties connectionProperties = new Properties();
	            File propertiesFile = new File("resources/properties.txt");

	            // Verificar si el archivo de propiedades existe
	            if (!propertiesFile.exists()) {
	                System.err.println("ERROR: No se encuentra el archivo de configuración en " + propertiesFile.getAbsolutePath());
	                return;
	            }
	            
	            connectionProperties.load(new FileReader(propertiesFile));

	            DRIVER_NAME = connectionProperties.getProperty("DRIVER_NAME");
	            DATABASE_FILE = connectionProperties.getProperty("DATABASE_FILE");
	            CONNECTION_STRING = connectionProperties.getProperty("CONNECTION_STRING") + DATABASE_FILE;
	            
	            System.out.println("Usando DRIVER: " + DRIVER_NAME);
	            System.out.println("Ruta de la base de datos: " + DATABASE_FILE);
	            System.out.println("Cadena de conexión: " + CONNECTION_STRING);

	            // Intentar cargar el driver
	            Class.forName(DRIVER_NAME);
	            System.out.println("Driver cargado correctamente.");

	            // Intentar establecer la conexión
	            try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {
	                System.out.println("Conexión a la base de datos establecida correctamente.");
	            } catch (SQLException e) {
	                System.err.println("ERROR: No se pudo establecer conexión con la base de datos.");
	                e.printStackTrace();
	            }

	        } catch (Exception ex) {
	            logger.warning("No se pudo cargar el driver de la base de datos: " + ex.getMessage());
	            System.err.println("Error crítico en la conexión a la base de datos: " + ex.getMessage());
	            ex.printStackTrace();
	        }
	}
		
	public void crearBBDD() {
	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         Statement stmt = con.createStatement()) {

	        // CREAR TABLA USUARIO PRIMERO
	        String sql1 = "CREATE TABLE IF NOT EXISTS Usuario ("
	                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
	                + " email TEXT NOT NULL UNIQUE,"
	                + " contrasena TEXT NOT NULL,"
	                + " telefono TEXT NOT NULL,"
	                + " comunidadAutonoma TEXT NOT NULL,"
	                + " direccion TEXT NOT NULL"
	                + ");";
	        stmt.execute(sql1);

	        // CREAR TABLA ANIMAL SEGUNDO (AHORA CON id_usuario BIEN DEFINIDO)
	        String sql2 = "CREATE TABLE IF NOT EXISTS Animal ("
	                + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
	                + " nombre TEXT NOT NULL,"
	                + " especie TEXT NOT NULL,"
	                + " raza TEXT NOT NULL,"
	                + " edad INTEGER NOT NULL,"
	                + " genero TEXT NOT NULL,"
	                + " rutaFoto TEXT NOT NULL,"
	                + " id_usuario INTEGER NOT NULL,"
	                + " FOREIGN KEY(id_usuario) REFERENCES Usuario(id) ON DELETE CASCADE"
	                + ");";
	        stmt.execute(sql2);

	        // CREAR TABLA SOLICITUD TERCERO
	        String sql3 = "CREATE TABLE IF NOT EXISTS Solicitud ("
                    + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " id_usuarioAdoptante INTEGER NOT NULL,"
                    + " id_usuarioPropietario INTEGER NOT NULL,"
                    + " id_animal INTEGER NOT NULL,"
                    + " estado BOOLEAN DEFAULT 0,"
                    + " FOREIGN KEY(id_usuarioAdoptante) REFERENCES Usuario(id) ON DELETE CASCADE,"
                    + " FOREIGN KEY(id_usuarioPropietario) REFERENCES Usuario(id) ON DELETE CASCADE,"
                    + " FOREIGN KEY(id_animal) REFERENCES Animal(id) ON DELETE CASCADE"
                    + ");";
	        stmt.execute(sql3);

	        logger.info("Se han creado las tablas correctamente.");
	    } catch (SQLException ex) {
	        logger.warning(String.format("Error al crear las tablas: %s", ex.getMessage()));
	        ex.printStackTrace();
	    }
	}
	
	public void insertarUsuario(Usuario usuario) throws SQLException {
	    Connection conn = DriverManager.getConnection(CONNECTION_STRING);

	    // Verificar si el usuario ya existe
	    String checkSql = "SELECT COUNT(*) FROM Usuario WHERE email = ?";
	    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
	        checkStmt.setString(1, usuario.getCorreoElectronico());
	        ResultSet rs = checkStmt.executeQuery();
	        if (rs.next() && rs.getInt(1) > 0) {
	            System.out.println("El usuario con email " + usuario.getCorreoElectronico() + " ya existe. No se insertará nuevamente.");
	            return; // No se inserta el usuario duplicado
	        }
	    }

	    String sql = "INSERT INTO Usuario (email, contrasena, telefono, comunidadAutonoma, direccion) VALUES (?, ?, ?, ?, ?)";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, usuario.getCorreoElectronico());
	        pstmt.setString(2, usuario.getContrasenia());
	        pstmt.setInt(3, usuario.getTelefono());
	        pstmt.setString(4, usuario.getComunidadAutonoma().name());
	        pstmt.setString(5, usuario.getDireccion());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        logger.warning("No se pudo insertar el usuario en la base de datos: " + DATABASE_FILE);
	    }
	}

	public void insertarAnimal(Animal animal) throws SQLException {
	    Connection con = DriverManager.getConnection(CONNECTION_STRING);
	    String sql = "INSERT INTO Animal (nombre, especie, raza, edad, genero, rutaFoto, id_usuario) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    // Validar que la ruta de la foto no sea nula
	    if (animal.getFotoAnimal() == null || animal.getFotoAnimal().trim().isEmpty()) {
	        System.out.println("ERROR: El animal " + animal.getNombre() + " no tiene una ruta de foto válida.");
	        return;
	    }

	    try (PreparedStatement statement = con.prepareStatement(sql)) {
	        statement.setString(1, animal.getNombre());
	        statement.setString(2, animal.getEspecie().name());
	        statement.setString(3, animal.getRaza());
	        statement.setInt(4, animal.getEdad());
	        statement.setString(5, animal.getGenero().name());
	        statement.setString(6, animal.getFotoAnimal());
	        statement.setInt(7, animal.getPropietario().getId());
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        logger.warning("No se pudo insertar el Animal en la base de datos: " + DATABASE_FILE);
	    }
	}
	
	public void insertarSolicitud(SolicitudAdopcion solicitud) throws SQLException {
	    Connection con = DriverManager.getConnection(CONNECTION_STRING);
	    String sql = "INSERT INTO Solicitud (id_usuarioAdoptante, id_usuarioPropietario, id_animal ) VALUES (?, ?, ?)";

	    try (PreparedStatement statement = con.prepareStatement(sql)) {
	        statement.setInt(1, solicitud.getUsuarioSolicitante().getId());
	        statement.setInt(2, solicitud.getUsuarioSolicitado().getId());
	        statement.setInt(3, solicitud.getAnimal().getId());
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        logger.warning("No se pudo insertar el Animal en la siguiente bdd: "+DATABASE_FILE);
	    }
	}
	
	
	public List<Animal> getAnimales() {
		List<Animal> animales = new ArrayList<>();
		String sql = "SELECT * FROM Animal";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = pStmt.executeQuery();			
			Animal animal;
			
			//Se recorre el ResultSet y se crean los Comics
			while (rs.next()) {
				animal = new Animal(rs.getInt("id"), 
							rs.getString("nombre"),
							Especie.valueOf(rs.getString("especie")),
							rs.getString("raza"),
							rs.getInt("edad"),
							Genero.valueOf(rs.getString("genero")),
							rs.getString("rutafoto"),
							getUsuarioPorId(rs.getInt("id_usuario")));
				

				animales.add(animal);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se han recuperado %d animales", animales.size()));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los animales: %s", ex.getMessage()));						
		}		
		
		return animales;
	}

	public List<Usuario> getUsuarios(){
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "SELECT * FROM Usuario";
		
		//Se abre la conexión y se crea el PreparedStatement con la sentencia SQL
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
		     PreparedStatement pStmt = con.prepareStatement(sql)) {			
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = pStmt.executeQuery();			
			Usuario usuario;
			
			//Se recorre el ResultSet y se crean los Comics
			while (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), 
							rs.getString("email"),
							rs.getString("contrasena"),
							rs.getInt("telefono"),
							ComunidadAutonoma.valueOf(rs.getString("comunidadAutonoma")),
							rs.getString("direccion"));
			
				usuarios.add(usuario);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			logger.info(String.format("Se han recuperado %d animales", usuarios.size()));			
		} catch (Exception ex) {
			logger.warning(String.format("Error recuperar los animales: %s", ex.getMessage()));						
		}		
		
		return usuarios;
	}
	
	//IAG GENERACION DE METODOS PARTIENDO DEL ANTERIOR CODIGO
	public Usuario getUsuarioPorId(int id) {
	    String sql = "SELECT * FROM Usuario WHERE id = ?";
	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement pStmt = con.prepareStatement(sql)) {
	        pStmt.setInt(1, id);
	        ResultSet rs = pStmt.executeQuery();
	        if (rs.next()) {
	            return new Usuario(
	                rs.getInt("id"),
	                rs.getString("email"),
	                rs.getString("contrasena"),
	                rs.getInt("telefono"),
	                ComunidadAutonoma.valueOf(rs.getString("comunidadAutonoma")),
	                rs.getString("direccion")
	            );
	        }
	    } catch (SQLException ex) {
	        logger.warning("Error al recuperar el usuario: " + ex.getMessage());
	    }
	    return null;
	}

	public Animal getAnimalPorId(int id) {
	    String sql = "SELECT * FROM Animal WHERE id = ?";
	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement pStmt = con.prepareStatement(sql)) {
	        pStmt.setInt(1, id);
	        ResultSet rs = pStmt.executeQuery();
	        if (rs.next()) {
	            return new Animal(
	                rs.getInt("id"),
	                rs.getString("nombre"),
	                Especie.valueOf(rs.getString("especie")),
	                rs.getString("raza"),
	                rs.getInt("edad"),
	                Genero.valueOf(rs.getString("genero")),
	                rs.getString("rutaFoto"),
	                null // Necesitarás recuperar y asignar el propietario
	            );
	        }
	    } catch (SQLException ex) {
	        logger.warning("Error al recuperar el animal: " + ex.getMessage());
	    }
	    return null;
	}

	public List<SolicitudAdopcion> getSolicitudes() {
	    List<SolicitudAdopcion> solicitudes = new ArrayList<>();
	    String sql = "SELECT * FROM Solicitud";
	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement pStmt = con.prepareStatement(sql);
	         ResultSet rs = pStmt.executeQuery()) {
	        while (rs.next()) {
	            solicitudes.add(new SolicitudAdopcion(
	                rs.getInt("id"),
	                getUsuarioPorId(rs.getInt("id_usuarioAdoptante")),
	                getUsuarioPorId(rs.getInt("id_usuarioPropietario")),
	                getAnimalPorId(rs.getInt("id_animal")),
	                rs.getBoolean("estado")
	            ));
	        }
	    } catch (SQLException ex) {
	        logger.warning("Error al recuperar las solicitudes: " + ex.getMessage());
	    }
	    return solicitudes;
	}

	public void insertarDatosDePrueba() {
	    try {
	        // Crear dos usuarios
	        Usuario usuario1 = new Usuario(-1, "usuario1@email.com", "password123", 123456789, ComunidadAutonoma.MADRID, "Calle 1, Madrid");
	        Usuario usuario2 = new Usuario(-1, "usuario2@email.com", "password456", 987654321, ComunidadAutonoma.ANDALUCIA, "Calle 2, Barcelona");

	        insertarUsuario(usuario1);
	        insertarUsuario(usuario2);

	        // Recuperar los usuarios de la base de datos para obtener sus IDs generados
	        List<Usuario> usuarios = getUsuarios();
	        if (usuarios.size() < 2) {
	            System.out.println("Error: No se pudieron insertar correctamente los usuarios.");
	            return;
	        }

	        usuario1 = usuarios.get(0);
	        usuario2 = usuarios.get(1);

	        // Crear y agregar animales
	        String[] nombres = {"Luna", "Max", "Rocky", "Bella", "Coco", "Toby", "Leo", "Simba", "Nemo", "Dory", "Rex", "Spike", "Pikachu", "Jerry"};
	        int nombreIndex = 0;

	        for (Especie especie : Especie.values()) {
	            for (int i = 1; i <= 2; i++) {
	                String foto = "foto" + especie.name().toLowerCase() + i + ".jpg";
	                Animal animal = new Animal(
	                    -1,
	                    nombres[nombreIndex++ % nombres.length],
	                    especie,
	                    "Raza_" + especie.name(),
	                    (int) (Math.random() * 10 + 1),  // Edad aleatoria entre 1 y 10
	                    i % 2 == 0 ? Genero.HEMBRA : Genero.MACHO,
	                    foto,
	                    (i % 2 == 0) ? usuario1 : usuario2  // Alternar propietario
	                );
	                insertarAnimal(animal);
	            }
	        }

	        System.out.println("Datos de prueba insertados correctamente.");

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Error al insertar los datos de prueba: " + e.getMessage());
	    }
	}
	
	//IAG metodo generado por GPT4o
	public Usuario getUsuarioPorCorreo(String email) throws SQLException {
	    String sql = "SELECT * FROM Usuario WHERE email = ?";
	    try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
	         PreparedStatement pStmt = con.prepareStatement(sql)) {
	        pStmt.setString(1, email);
	        ResultSet rs = pStmt.executeQuery();

	        if (rs.next()) {
	            return new Usuario(
	                rs.getInt("id"),
	                rs.getString("email"),
	                rs.getString("contrasena"),
	                rs.getInt("telefono"),
	                ComunidadAutonoma.valueOf(rs.getString("comunidadAutonoma")),
	                rs.getString("direccion")
	            );
	        }
	    } catch (SQLException ex) {
	        logger.warning("Error al recuperar el usuario: " + ex.getMessage());
	        throw ex;
	    }
	    return null; // Si no se encuentra el usuario
	}
	
	
	public void aceptarSolicitud(int idSolicitud) {
        String sql = "UPDATE Solicitud SET estado = 1 WHERE id = ?";
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idSolicitud);
            stmt.executeUpdate();
            logger.info("Solicitud " + idSolicitud + " aceptada correctamente.");
        } catch (SQLException ex) {
            logger.warning("Error al aceptar la solicitud: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

	
	public List<SolicitudAdopcion> getSolicitudesRecibidas(int idUsuario, boolean estado) {
        List<SolicitudAdopcion> solicitudes = new ArrayList<>();
        String sql = "SELECT * FROM Solicitud WHERE id_usuarioPropietario = ? AND estado = ?";
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setBoolean(2, estado);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                solicitudes.add(new SolicitudAdopcion(
                        rs.getInt("id"),
                        getUsuarioPorId(rs.getInt("id_usuarioAdoptante")),
                        getUsuarioPorId(rs.getInt("id_usuarioPropietario")),
                        getAnimalPorId(rs.getInt("id_animal")),
                        rs.getBoolean("estado")
                ));
            }
        } catch (SQLException ex) {
            logger.warning("Error al recuperar solicitudes recibidas: " + ex.getMessage());
        }
        return solicitudes;
    }

	public List<SolicitudAdopcion> getSolicitudesEnviadas(int idUsuario, boolean estado) {
        List<SolicitudAdopcion> solicitudes = new ArrayList<>();
        String sql = "SELECT * FROM Solicitud WHERE id_usuarioAdoptante = ? AND estado = ?";
        try (Connection con = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setBoolean(2, estado);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                solicitudes.add(new SolicitudAdopcion(
                        rs.getInt("id"),
                        getUsuarioPorId(rs.getInt("id_usuarioAdoptante")),
                        getUsuarioPorId(rs.getInt("id_usuarioPropietario")),
                        getAnimalPorId(rs.getInt("id_animal")),
                        rs.getBoolean("estado")
                ));
            }
        } catch (SQLException ex) {
            logger.warning("Error al recuperar solicitudes enviadas: " + ex.getMessage());
        }
        return solicitudes;
    }





	
}
