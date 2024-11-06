package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import domain.Animal;
import domain.Principal;
import domain.Usuario;

public class VentanaMisDadosEnAdopcion extends JFrame{
	
	private DefaultListModel<Animal> modeloListaMisDadosEnAdopcion; 
	private JList<Animal> listaMisDadosEnAdopcion; 
	private JScrollPane scrollListaMisDadosEnAdopcion;
	
	public VentanaMisDadosEnAdopcion(Principal principal, Usuario usuario) {
		
		//AJUSTES DEL FRAME
		setBounds(10,10,900,650);		
		setLocationRelativeTo(null);
		setResizable(true);
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
		getContentPane().add(panelSuperior,BorderLayout.NORTH);
		panelSuperior.add(lTitulo);
		panelSuperior.add(lLogo);
		setIconImage(logoTemp);

		JPanel panelCentral = new JPanel();
		JLabel lCabeceraLista = new JLabel("Registro de mis mascotas dadas en adopción");
		modeloListaMisDadosEnAdopcion = new DefaultListModel<>();
		listaMisDadosEnAdopcion = new JList<>(modeloListaMisDadosEnAdopcion);
		scrollListaMisDadosEnAdopcion = new JScrollPane(listaMisDadosEnAdopcion);
		scrollListaMisDadosEnAdopcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollListaMisDadosEnAdopcion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		panelCentral.setLayout(new BorderLayout());
		panelCentral.setBorder(new EmptyBorder(0,30,0,30));
		panelCentral.add(lCabeceraLista,BorderLayout.NORTH);
		panelCentral.add(scrollListaMisDadosEnAdopcion,BorderLayout.CENTER);
		getContentPane().add(panelCentral,BorderLayout.CENTER);
		
		//PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		JButton bAtras = new JButton("Atrás");
		getContentPane().add(panelInferior,BorderLayout.SOUTH);
		panelInferior.add(bAtras);
		
		
		//EVENTOS
		bAtras.addActionListener((e)->{
			VentanaMiPerfil vmp= new VentanaMiPerfil(principal,usuario);
			vmp.setVisible(true);
			dispose();
		});
		
	}
	
	
}
