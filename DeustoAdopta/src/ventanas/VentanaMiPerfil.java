package ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import clases.Principal;
import clases.Usuario;

public class VentanaMiPerfil extends JFrame{

	public VentanaMiPerfil(Principal principal, Usuario usuario) {
		
		//AJUSTES DEL FRAME
		setBounds(10,10,900,650);		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//LOGO Y TITULO EN PANEL SUPERIOR
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
		
		//DATOS DEL PERFIL Y BOTONES PARA CAMBIAR EN PANEL IZQUIERDO 
		
		JPanel panelIzquierdo = new JPanel();
		panelIzquierdo.setBorder(new EmptyBorder(70,100,100,100));
		getContentPane().add(panelIzquierdo, BorderLayout.WEST);
		JPanel contenido = new JPanel();
		contenido.setLayout(new BorderLayout());
		JPanel contenidoMedio = new JPanel(); 
		contenidoMedio.setLayout(new GridLayout(0,1, 10, 10));
		panelIzquierdo.add(contenido);
		
		contenidoMedio.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel lLogoUsu = new JLabel();
		Image logoTemp1= new ImageIcon("imagenes/usuario.png").getImage();
		ImageIcon logoUs=new ImageIcon(logoTemp1.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		lLogoUsu.setIcon(logoUs);
		lLogoUsu.setAlignmentX(CENTER_ALIGNMENT);
		contenido.add(lLogoUsu, BorderLayout.NORTH);
		
		
		
		JLabel lCorreo = new JLabel("Correo Electronico: ");
		JTextField tfCorreo = new JTextField(usuario.getCorreoElectronico(),15);
		JLabel lContra = new JLabel("Contraseña: ");
		JTextField tfContrasena = new JTextField(usuario.getContrasenia(),15);
		JButton bActualizarCorreo= new JButton("Actualizar correo");
		//bMisAdopciones.setFont(new Font(null, ABORT, 20));
		JButton bActualizarContra= new JButton("Actualizar contraseña");
		//bMascotasSolicitadas.setFont(new Font(null, ABORT, 20));
		
		
		contenidoMedio.add(lCorreo);
		contenidoMedio.add(tfCorreo);
		contenidoMedio.add(bActualizarCorreo);
		contenidoMedio.add(lContra);
		contenidoMedio.add(tfContrasena);
		contenidoMedio.add(bActualizarContra);
		contenido.add(contenidoMedio,BorderLayout.CENTER);
		
		JPanel panelCentral = new JPanel();
		JSeparator separador = new JSeparator();
		panelCentral.setBorder(new EmptyBorder(30,0,30,0));
		separador.setOrientation(SwingConstants.VERTICAL);
		panelCentral.add(separador);
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		
		
		//PANEL DERECHO
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(new GridLayout(0,1,10,20));
		JButton bMisAdopciones = new JButton("Mascotas que he adoptado");
		JButton bMisAnimalesEnAdopcion = new JButton("Mis mascotas en adopcion");
		bMisAdopciones.setFont(new Font(null, ABORT, 20));
		bMisAnimalesEnAdopcion.setFont(new Font(null, ABORT, 20));
		JLabel lMisAnimales = new JLabel("Mis mascotas");
		lMisAnimales.setAlignmentX(CENTER_ALIGNMENT);
		lMisAnimales.setFont(new Font(null, ABORT, 30));
		panelDerecho.add(lMisAnimales);
		panelDerecho.add(bMisAdopciones);
		panelDerecho.add(bMisAnimalesEnAdopcion);
		getContentPane().add(panelDerecho,BorderLayout.EAST);
		panelDerecho.setBorder(new EmptyBorder(70,100,100,100));
		
		//PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior,BorderLayout.SOUTH);
		JButton bAtras = new JButton("Atrás");
		panelInferior.add(bAtras);
		
		//EVENTOS
		bAtras.addActionListener((e)->{
			VentanaPrincipal vp= new VentanaPrincipal(principal,usuario);
			vp.setVisible(true);
			dispose();
		});
		
	}
	
	
}
