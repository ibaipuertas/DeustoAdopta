package gui;

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

import domain.Animal;
import domain.Especie;
//import domain.Marca;
import domain.Principal;
import domain.Usuario;
import main.Main;


public class VentanaAdoptar extends JFrame{
	
	//ATRIBUTOS
	private DefaultTableModel modeloTablaAnimales;
	private JTable tablaAnimales;
	private DefaultTableModel modeloDatosAnimales;
	private JScrollPane scrollTablaAnimales;
	private DefaultListModel<Animal> modeloListaAnimales; 
	private JList<Animal> listaAnimales; 
	private JScrollPane scrollListaAnimales;
	private static final String nomfichUsuarios = "src/data/DataUsuarios.csv";
	private int mouseRowPersonajes = -1;
	//private int value=Main.carrito.size();
	
	

	public VentanaAdoptar(Principal principal, Usuario usuario) {
		
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
	
	private void cargarAnimales() {
		
	}
	
	private void iniciarTabla() {
		Vector<String> cabeceraAnimales = new Vector<String>(Arrays.asList( "especie","edad","genero","propietario","LOCALIDAD"));
		this.modeloDatosAnimales = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraAnimales);
		this.tablaAnimales = new JTable(this.modeloDatosAnimales);
		this.tablaAnimales.setRowHeight(60);
		this.tablaAnimales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			if(value instanceof Especie) {
				ImageDisplayer id = new ImageDisplayer ();
				
				Especie es = (Especie) value;
				
				switch (es) { 
					case AVE:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/SEAT_Logo_from_2017.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case GATO:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/bmw_logo_PNG19712.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case HURON:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Opel-Logo_2017.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case PERRO:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/toyota.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case PEZ:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Ford-Motor-Company-Logo.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case REPTIL:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Logo_Honda_F1.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case ROEDOR:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Volkswagen_Logo_till_1995.svg.png")));
					} catch (IOException e) {
						e.printStackTrace();
					}
						break;
					default:
				}
				
				return id;
			}
			
			JLabel resultado = new JLabel(value.toString());
			
			if (table.equals(tablaAnimales)) {
				if (row % 2 == 0) {
					resultado.setBackground(new Color(250, 249, 249));
				} else {
					resultado.setBackground(new Color(255, 250, 240));
				}
			}	
			
			if (isSelected || (table.equals(tablaAnimales) && row == mouseRowPersonajes)) {
				resultado.setBackground(table.getSelectionBackground());
				resultado.setForeground(table.getSelectionForeground());			
			}		
			
			resultado.setOpaque(true);
			return resultado;
		};
		
		this.tablaAnimales.setDefaultRenderer(Object.class, cellRenderer);
		this.tablaAnimales.setRowHeight(100);	
		}
	}
	

