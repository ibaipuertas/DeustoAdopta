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

public class VentanaPrincipal extends JFrame{
	
	public VentanaPrincipal(Principal principal, Usuario usuario) {
		
		
		// AJUSTES DEL JFRAME
		setBounds(10,10,900,650);		
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//TITULO Y LOGO, EN PANEL SUPERIOR 
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
		
		//BOTONERA DE OPCIONES EN PANEL CENTRAL (ADOPTAR, DAR EN ADOPCION Y VER ASOCIACIONES)
		JButton bAdoptar = new JButton("Adoptar");
		bAdoptar.setFont(new Font(null, ABORT, 30));
		JButton bDarEnAdop = new JButton("Dar en adopción");
		bDarEnAdop.setFont(new Font(null, ABORT, 30));
		JButton bVerAsoci = new JButton("Ver asociaciones");
		bVerAsoci.setFont(new Font(null, ABORT, 30));
		
		JPanel panelCentro = new JPanel();
		panelCentro.setBorder(new EmptyBorder(100,200,100,200));
		getContentPane().add(panelCentro,BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0,1, 40, 40));
		panelCentro.add(bAdoptar);
		panelCentro.add(bDarEnAdop);
		panelCentro.add(bVerAsoci);
		bAdoptar.setAlignmentX(CENTER_ALIGNMENT);
		bDarEnAdop.setAlignmentX(CENTER_ALIGNMENT);
		bVerAsoci.setAlignmentX(CENTER_ALIGNMENT);
		
		
		//BOTONERA DE OPCIONES PANEL DERECHO (MI PERFIL Y NOTIFICACIONES)
		JPanel panelDerecho = new JPanel();
		getContentPane().add(panelDerecho, BorderLayout.EAST);
		panelDerecho.setLayout(new BoxLayout(panelDerecho, BoxLayout.Y_AXIS));
		Image logoTemp2= new ImageIcon("imagenes/notificacion.png").getImage();
		ImageIcon notifi=new ImageIcon(logoTemp2.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		Image logoTemp3= new ImageIcon("imagenes/usuario.png").getImage();
		ImageIcon usua=new ImageIcon(logoTemp3.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		JLabel lMiPerfil = new JLabel("Mi perfil");
		lMiPerfil.setFont(new Font(null, ABORT, 20));
		JButton bNotificaciones = new JButton(notifi);
		JLabel lNotificacioens = new JLabel("Notificaciones");
		lNotificacioens.setFont(new Font(null, ABORT, 20));
		JButton bMiPerfil = new JButton(usua);
		panelDerecho.add(lMiPerfil);
		panelDerecho.add(Box.createRigidArea(new Dimension(0,10)));
		panelDerecho.add(bMiPerfil);
		panelDerecho.add(Box.createRigidArea(new Dimension(0,30)));
		panelDerecho.add(lNotificacioens);
		panelDerecho.add(Box.createRigidArea(new Dimension(0,10)));
		panelDerecho.add(bNotificaciones);
		lMiPerfil.setAlignmentX(CENTER_ALIGNMENT);
		lNotificacioens.setAlignmentX(CENTER_ALIGNMENT);
		bMiPerfil.setAlignmentX(CENTER_ALIGNMENT);
		bNotificaciones.setAlignmentX(CENTER_ALIGNMENT);
		panelDerecho.setBorder(new EmptyBorder(60,0,0,50));
		
		// BOTON DE SALIR PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior,BorderLayout.SOUTH);
		JButton bSalir = new JButton("Salir");
		panelInferior.add(bSalir);
		
		
		//EVENTOS
		bAdoptar.addActionListener((e)->{
			VentanaAdoptar va= new VentanaAdoptar(principal,usuario);
			va.setVisible(true);
			dispose();
		});
		
		bSalir.addActionListener((e)->{
			System.exit(0);
		});
		
		bMiPerfil.addActionListener((e)->{
			VentanaMiPerfil vmp= new VentanaMiPerfil(principal,usuario);
			vmp.setVisible(true);
			dispose();
		});
		
		bDarEnAdop.addActionListener((e)->{
			VentanaDarEnAdopcion vdea= new VentanaDarEnAdopcion(principal,usuario);
			vdea.setVisible(true);
			dispose();
		});
		
		bNotificaciones.addActionListener((e)->{
			VentanaNotificaciones vn= new VentanaNotificaciones(principal,usuario);
			vn.setVisible(true);
			dispose();
		});
		
	}
	
	
	
}
