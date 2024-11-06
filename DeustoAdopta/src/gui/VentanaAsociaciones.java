package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domain.Principal;
import domain.Usuario;

public class VentanaAsociaciones extends JFrame{

	private DefaultTableModel modeloTablaAsociaciones;
	private JTable tablaAsociaciones;
	private JScrollPane scrollTablaAsociaciones;
	
	public VentanaAsociaciones(Principal principal, Usuario usuario) {
		
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

		//PANEL CENTRAL
		JLabel lcabecera = new JLabel("Seleccione una de las mascotas que buscan hogar:");
		lcabecera.setFont(new Font("Comic Sans", Font.BOLD, 20));
		JPanel panelCentral = new JPanel();
		String [] titulos = {"ESPECIE","EDAD","GENERO","PROPIETARIO","LOCALIDAD","FOTO"};
		modeloTablaAsociaciones = new DefaultTableModel();
		modeloTablaAsociaciones.setColumnIdentifiers(titulos);
		tablaAsociaciones = new JTable(modeloTablaAsociaciones);
		scrollTablaAsociaciones = new JScrollPane(tablaAsociaciones);
		panelCentral.setLayout(new BorderLayout());
		panelCentral.setBorder(new EmptyBorder(30,30,0,30));
		panelCentral.add(lcabecera,BorderLayout.NORTH);
		panelCentral.add(scrollTablaAsociaciones, BorderLayout.CENTER);
		
		
	}
}
