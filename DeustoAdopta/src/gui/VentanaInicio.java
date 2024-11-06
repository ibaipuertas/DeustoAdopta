package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import domain.Principal;

import javax.swing.*;
public class VentanaInicio extends JFrame{
	
	private static final String nomfichUsuarios = "src/data/DataUsuarios.csv";
	
	public VentanaInicio(Principal principal) {
		
		
		//CARGAR LOS USUARIOS EN INSTANCIA DE PRINCIPAL DESDE CSV
		principal.cargarUsuariosEnLista(nomfichUsuarios);
		
		
		//AJUSTES DEL FRAME
		setBounds(10,10,900,650);		
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//LOGO Y TITULO
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
		
		//PANEL CENTRAL
		JPanel panelCentral = new JPanel();
		Image fotoPerros = new ImageIcon("imagenes/pets.jpg").getImage();
		PanelConFondo fondo = new PanelConFondo(fotoPerros);
		fondo.setBorder(new EmptyBorder(150,300,150,300));
		fondo.setLayout(new GridLayout(0, 1, 30,30));
		getContentPane().add(fondo, BorderLayout.CENTER);
		JButton bRegistro = new JButton("Registrarse");
		bRegistro.setFont(new Font(null, ABORT, 20));
		JButton bIniciarSesion = new JButton("Iniciar sesión");
		bIniciarSesion.setFont(new Font(null, ABORT, 20));
		JButton bsalir = new JButton("Salir");
		bsalir.setFont(new Font(null, ABORT, 20));
		fondo.add(bIniciarSesion);
		fondo.add(bRegistro);	
		fondo.add(bsalir);
		
		
		//EVENTOS
		bRegistro.addActionListener((e)->{
			principal.guardarUsuariosEnFichero(nomfichUsuarios);
			VentanaRegistro1 vr1 = new VentanaRegistro1(principal);
			dispose();
			vr1.setVisible(true);
		});
		
		bIniciarSesion.addActionListener((e)->{
			principal.guardarUsuariosEnFichero(nomfichUsuarios);
			VentanaInicioSesion vis = new VentanaInicioSesion(principal); //meter el principal
			dispose();
			vis.setVisible(true);
		});
		
		bsalir.addActionListener((e)->{
			principal.guardarUsuariosEnFichero(nomfichUsuarios);
			System.exit(0);
			
		});
		
		
	}
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaInicio frame = new VentanaInicio();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
}
