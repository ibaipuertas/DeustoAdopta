package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import clases.ComunidadAutonoma;
import clases.Principal;
import clases.Usuario;

public class VentanaRegistro12 extends JFrame{

	private static final String nomfichUsuarios = "/src/data/Usuarios.csv";
	
	public VentanaRegistro12(Principal principal) {
		
		setBounds(10,10,900,650);		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		JLabel lTitulo = new JLabel("DeustoAdopta");
		lTitulo.setFont(new Font("Serif", Font.BOLD, 50));
		JLabel lLogo = new JLabel();
		Image logoTemp= new ImageIcon("imagenes/logoDeustoAdopta.png").getImage();
		ImageIcon logo=new ImageIcon(logoTemp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		lLogo.setIcon(logo);
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new BevelBorder(1));
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.add(lTitulo);
		panelSuperior.add(lLogo);
		setIconImage(logoTemp);

		
		JLabel lCabecera = new JLabel( "Por favor, introduzca los datos de la asociación" );
		lCabecera.setFont(new Font(null, getFont().BOLD, 20));
		JLabel lNombreAso = new JLabel( "Nombre de asociación:" );
		JTextField tfNombreAso = new JTextField( "", 15 );
		JLabel lPassword = new JLabel( "Password:" );
		JPasswordField tfPassword = new JPasswordField( "", 15 );
		JLabel lComunidadAutonoma = new JLabel("Comunidad Autonoma:");
		JComboBox <ComunidadAutonoma> comboComAut = new JComboBox<>();
		for(ComunidadAutonoma c : ComunidadAutonoma.values()) {
			comboComAut.addItem(c);
		}
		JLabel lDireccion = new JLabel("Dirección:");
		JTextField tfDireccion = new JTextField("", 30);
		JLabel lTelefono = new JLabel("Teléfono de contacto:");
		JTextField tfTelefono = new JTextField("",10);
		JLabel lCorreo = new JLabel("Correo electrónico:");
		JTextField tfCorreo = new JTextField("",20);
		
		JButton bCompletarReg = new JButton("Confirmar Registro");
		JButton bCancelar = new JButton("Cancelar");
		
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		JPanel contenido = new JPanel();
		contenido.setLayout(new GridLayout(0,1, 5, 5));
		panelCentral.add(contenido);

		contenido.add(lCabecera);
		contenido.add(lNombreAso);
		contenido.add(tfNombreAso);
		contenido.add(lPassword);
		contenido.add(tfPassword);
		contenido.add(lComunidadAutonoma);
		contenido.add(comboComAut);
		contenido.add(lDireccion);
		contenido.add(tfDireccion);
		contenido.add(lTelefono);
		contenido.add(tfTelefono);
		contenido.add(lCorreo);
		contenido.add(tfCorreo);
		
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior,BorderLayout.SOUTH);
		panelInferior.add(bCancelar);
		panelInferior.add(bCompletarReg);
	
		bCompletarReg.addActionListener((e)->{
			
			String con = tfPassword.getText();
			String correo = tfCorreo.getText();
			Usuario u = new Usuario(correo,con);
			if(Principal.buscarUsuario(correo)!=null) {
				JOptionPane.showConfirmDialog(null, "Ya existe un cliente con este correo", "ERROR", JOptionPane.ERROR_MESSAGE);
			}else {
				Principal.anadirUsuario(u);
				JOptionPane.showMessageDialog(null, "Cliente registrado con éxito","REGISTRADO",JOptionPane.INFORMATION_MESSAGE);
				principal.guardarUsuariosEnFichero(nomfichUsuarios);
			}
			VentanaInicioSesion vis= new VentanaInicioSesion(principal);
			dispose();
			vis.setVisible(true);
		});
		
		bCancelar.addActionListener((e)->{
			principal.guardarUsuariosEnFichero(nomfichUsuarios);
			VentanaInicio vi = new VentanaInicio();
			vi.setVisible(true);
			dispose();
		});
		
	}
	
	
}
