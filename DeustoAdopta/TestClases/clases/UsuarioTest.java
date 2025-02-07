//package clases;
//
//import static org.junit.Assert.*;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import domain.ComunidadAutonoma;
//import domain.Usuario;
//
//
//public class UsuarioTest {
//	
//		private Usuario usuario;
//		private String correoElectronico = "correo";
//		private String contrasenia = "contrasenia";
//		private int telefono = 0;
//		private ComunidadAutonoma comunidadAutonoma = ComunidadAutonoma.MADRID;
//		private String direccion = "direccion";
//	
//		private String correoElectronico2 = "correo2";
//		private String contrasenia2 = "contrasenia2";
//		private int telefono2 = 1;
//		private ComunidadAutonoma comunidadAutonoma2 = ComunidadAutonoma.ANDALUCIA;
//		private String direccion2 = "direccion2";
//
//		@Before
//		public void setUp() throws Exception {
//			usuario = new Usuario(correoElectronico, contrasenia, telefono, comunidadAutonoma,direccion);
//		}
//		
//
//		@After
//		public void tearDown() throws Exception {
//		}
//		
//		@Test
//		public void testProducto() {
//			assertNotNull(usuario);
//			assertEquals(correoElectronico, usuario.getCorreoElectronico());
//			assertEquals(contrasenia, usuario.getContrasenia());
//			assertEquals(telefono, usuario.getTelefono());
//			assertEquals(comunidadAutonoma, usuario.getComunidadAutonoma());
//			assertEquals(direccion, usuario.getDireccion());
//		}
//		
//		
//		
//		
//}
