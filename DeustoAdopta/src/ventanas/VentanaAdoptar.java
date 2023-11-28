package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import clases.Animal;
import clases.Especie;
import clases.Principal;
import clases.Usuario;


public class VentanaAdoptar extends JFrame{
	
	//ATRIBUTOS
	private DefaultTableModel modeloTablaAnimales;
	private JTable tablaAnimales;
	private JScrollPane scrollTablaAnimales;
	private DefaultListModel<Animal> modeloListaAnimales; 
	private JList<Animal> listaAnimales; 
	private JScrollPane scrollListaAnimales;
	private static final String nomfichUsuarios = "src/data/DataUsuarios.csv";
	
	public VentanaAdoptar(Principal principal, Usuario usuario) {
		
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
		getContentPane().add(panelSuperior,BorderLayout.NORTH);
		panelSuperior.add(lTitulo);
		panelSuperior.add(lLogo);
		setIconImage(logoTemp);
		
		
	
		//CREACION DE PANELES
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral,BorderLayout.CENTER);
		JPanel panelDerecho = new JPanel();
		getContentPane().add(panelDerecho,BorderLayout.EAST);
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior,BorderLayout.SOUTH);
		JPanel panelIzquierdo = new JPanel();
		getContentPane().add(panelIzquierdo,BorderLayout.WEST);
		
		//PANEL CENTRAL
		JLabel lcabecera = new JLabel("Seleccione una de las mascotas que buscan hogar:");
		lcabecera.setFont(new Font("Comic Sans", Font.BOLD, 20));
		
		String [] titulos = {"ESPECIE","EDAD","GENERO","PROPIETARIO","LOCALIDAD","FOTO"};
		modeloTablaAnimales = new DefaultTableModel();
		modeloTablaAnimales.setColumnIdentifiers(titulos);
		tablaAnimales = new JTable(modeloTablaAnimales);
		scrollTablaAnimales = new JScrollPane(tablaAnimales);
		panelCentral.setLayout(new BorderLayout());
		panelCentral.setBorder(new EmptyBorder(30,30,0,30));
		panelCentral.add(lcabecera,BorderLayout.NORTH);
		panelCentral.add(scrollTablaAnimales, BorderLayout.CENTER);
		
		
		//CREACION DE TABLA
		
		
		//cargarComprasEnTabla();
		
		//PANEL DERECHO 
		JComboBox<String> comboBoxEspecie = new JComboBox<String>();
		comboBoxEspecie.setBounds(265, 296, 94, 22);
		panelDerecho.add(comboBoxEspecie);
		
		comboBoxEspecie.addItem("Todos");
		
		for (Especie e : Especie.values()) {
			comboBoxEspecie.addItem(e.getEspecie());
		}
		panelDerecho.setBorder(new EmptyBorder(50,0,0,20));
	
		//CargarListaAnimales
		
		//PANEL INFERIOR (BOTONES DE CANCELAR Y CONFIRMAR)
		JButton bCancelar = new JButton("Cancelar");
		JButton bConfirmar=new JButton("Confirmar adopción");
		panelInferior.add(bCancelar);
		panelInferior.add(bConfirmar);
		
		//EVENTOS
		bCancelar.addActionListener((e)->{
			VentanaPrincipal vp = new VentanaPrincipal(principal, usuario);
			vp.setVisible(true);
			dispose();
			
		});
		
	}
	
	private void cargarAnimalesEnTabla() {
		
	}
	
}
