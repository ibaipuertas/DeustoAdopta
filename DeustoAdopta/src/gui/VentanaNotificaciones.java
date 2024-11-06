package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domain.Animal;
import domain.Principal;
import domain.Usuario;

public class VentanaNotificaciones extends JFrame{
	
	private DefaultTableModel modeloTablaSolicitudes;
	private JTable tablaSolicitudes;
	private JScrollPane scrollTablaSolicitudes;
	private DefaultListModel<Animal> modeloListaSoliAceptadas; 
	private JList<Animal> listaSoliAceptadas; 
	private JScrollPane scrollListaSoliAceptadas;
	
	
	
	public VentanaNotificaciones(Principal principal, Usuario usuario) {
		
		//AJUSTES DEL FRAME	
		setBounds(10,10,900,650);		
		setLocationRelativeTo(null);
		setResizable(true);
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
		
		
		//PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		JButton bAtras = new JButton("Atrás");
		panelInferior.add(bAtras);
		getContentPane().add(panelInferior,BorderLayout.SOUTH);
		
		//TABLA SOLICITUDES DE ADOPCIÓN EN PANEL CENTRAL
		JPanel panelCentral = new JPanel();
		JLabel lcabecera = new JLabel("Solicitudes de adopción de tus mascotas:");
		JButton bAnadir = new JButton("Aceptar solicitud");
		String [] titulos = {"NOMBRE","RAZA","EDAD","GENERO","SOLICITANTE","LOCALIDAD"};
		modeloTablaSolicitudes = new DefaultTableModel();
		modeloTablaSolicitudes.setColumnIdentifiers(titulos);
		tablaSolicitudes = new JTable(modeloTablaSolicitudes);
		scrollTablaSolicitudes = new JScrollPane(tablaSolicitudes);
		panelCentral.setLayout(new BorderLayout());
		panelCentral.setBorder(new EmptyBorder(0,30,0,30));
		panelCentral.add(lcabecera,BorderLayout.NORTH);
		panelCentral.add(scrollTablaSolicitudes, BorderLayout.CENTER);
		panelCentral.add(bAnadir,BorderLayout.SOUTH);
		getContentPane().add(panelCentral,BorderLayout.CENTER);
		
		//LISTA SOLICITUDES ACEPTADAS EN PANEL DERECHO
		JPanel panelDerecho = new JPanel();
		JLabel lCabeceraLista = new JLabel("Solicitudes aceptadas");
		modeloListaSoliAceptadas = new DefaultListModel<>();
		listaSoliAceptadas = new JList<>(modeloListaSoliAceptadas);
		scrollListaSoliAceptadas = new JScrollPane(listaSoliAceptadas);
		scrollListaSoliAceptadas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollListaSoliAceptadas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelDerecho.setLayout(new BorderLayout());
		panelDerecho.setBorder(new EmptyBorder(0,30,0,30));
		panelDerecho.add(lCabeceraLista,BorderLayout.NORTH);
		panelDerecho.add(scrollListaSoliAceptadas,BorderLayout.CENTER);
		getContentPane().add(panelDerecho,BorderLayout.EAST);
		
		
		
		
		//EVENTOS
		bAtras.addActionListener((e)->{
			VentanaPrincipal vp= new VentanaPrincipal(principal,usuario);
			vp.setVisible(true);
			dispose();
		});
	}
	
}
