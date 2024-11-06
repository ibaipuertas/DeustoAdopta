package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.ComunidadAutonoma;
import domain.Especie;
import domain.Genero;
import domain.Principal;
import domain.Usuario;

public class VentanaDarEnAdopcion extends JFrame{
		
	public VentanaDarEnAdopcion(Principal principal, Usuario usuario) {
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
		
		//
		JLabel lcabecera = new JLabel("Introduce los datos de la mascota");
		lcabecera.setFont(new Font(null, getFont().BOLD, 20));
		JLabel lNombre = new JLabel( "Nombre: (vacío si no tiene)" );
		JTextField tfNombre = new JTextField( "", 15 );
		JLabel lEdad = new JLabel( "Edad: " );
		JTextField tfEdad = new JTextField( "", 15 );
		JLabel lGenero = new JLabel("Genero:");
		JComboBox <Genero> comboGen = new JComboBox<>();
		for(Genero c : Genero.values()) {
			comboGen.addItem(c);
		}
		JLabel lEspecie = new JLabel("Especie:");
		JComboBox <Especie> comboEsp = new JComboBox<>();
		for(Especie c : Especie.values()) {
			comboEsp.addItem(c);
		}
		JLabel lRaza = new JLabel("Raza:");
		JTextField tfRaza = new JTextField("", 30);
		
		
		
		JButton bConfirmar = new JButton("Confirmar");
		JButton bCancelar = new JButton("Cancelar");
		
		
		JPanel panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		JPanel contenido = new JPanel();
		contenido.setLayout(new GridLayout(0,1, 5, 5));
		panelCentral.add(contenido);
		
		contenido.add(lcabecera);
		contenido.add(lNombre);
		contenido.add(tfNombre);
		contenido.add(lEdad);
		contenido.add(tfEdad);
		contenido.add(lGenero);
		contenido.add(comboGen);
		contenido.add(lEspecie);
		contenido.add(comboEsp);
		contenido.add(lRaza);
		contenido.add(tfRaza);
		
		JButton banadirFoto= new JButton("Añadir una foto");
		contenido.add(banadirFoto);
		JFileChooser fileChooser= new JFileChooser(); 
		
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior,BorderLayout.SOUTH);
		panelInferior.add(bCancelar);
		panelInferior.add(bConfirmar);
		
		//EVENTOS
		banadirFoto.addActionListener((e)->{
			int returnVal = fileChooser.showOpenDialog(this);
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File file = fileChooser.getSelectedFile();
	            try {
	              // return the file path 
	            } catch (Exception ex) {
	              System.out.println("problem accessing file"+file.getAbsolutePath());
	            }
	        } 
	        else {
	            System.out.println("File access cancelled by user.");
	        }       
		});
		
		bCancelar.addActionListener((e)->{
			VentanaPrincipal vp= new VentanaPrincipal(principal,usuario);
			vp.setVisible(true);
			dispose();
		});
		 
    }   

		
}

