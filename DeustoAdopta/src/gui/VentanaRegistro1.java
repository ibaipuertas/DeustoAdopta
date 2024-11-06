package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import domain.Principal;

public class VentanaRegistro1 extends JFrame{

	public VentanaRegistro1(Principal principal) {
		
		//AJUSTES DEL FRAME
		setBounds(10,10,900,650);		
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//PANEL SUPERIOR
		JLabel lTitulo = new JLabel("DeustoAdopta");
		lTitulo.setFont(new Font("Serif", Font.BOLD, 50));
		JLabel lLogo = new JLabel();
		Image logoTemp= new ImageIcon("imagenes/logoDeustoAdopta.png").getImage();
		ImageIcon logo=new ImageIcon(logoTemp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		lLogo.setIcon(logo);	
		JLabel lSubTitulo = new JLabel("Comencemos con el registro");
		lSubTitulo.setFont(new Font(null, getFont().BOLD, 30));
		JLabel lQueEres = new JLabel("¿Que eres?");
		lQueEres.setFont(new Font(null, getFont().BOLD, 20));
		Image iconoAsociacionTemp= new ImageIcon("imagenes/iconoRefugio.png").getImage();
		ImageIcon iconoAsociacion =new ImageIcon(iconoAsociacionTemp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		Image iconoPersonaTemp= new ImageIcon("imagenes/iconoPersona.png").getImage();
		ImageIcon iconoPersona =new ImageIcon(iconoPersonaTemp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JButton bPersona = new JButton("Soy una Persona");
		bPersona.setIcon(iconoPersona);
		JButton bAsociacion = new JButton("Soy una Asociación");
		bAsociacion.setIcon(iconoAsociacion);
		JButton bCancelar = new JButton("Cancelar");
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBorder(new BevelBorder(1));
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		panelSuperior.add(lTitulo);
		panelSuperior.add(lLogo);
		setIconImage(logoTemp);
		
		
		//PANEL CENTRAL
		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new EmptyBorder(100,100,100,100));
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		getContentPane().add(panelCentral);
		panelCentral.add(lSubTitulo);
		panelCentral.add(Box.createRigidArea(new Dimension(0,20)));
		panelCentral.add(lQueEres);
		panelCentral.add(Box.createRigidArea(new Dimension(0,20)));
		panelCentral.add(bPersona);
		panelCentral.add(Box.createRigidArea(new Dimension(0,20)));
		panelCentral.add(bAsociacion);
		
		lSubTitulo.setAlignmentX(CENTER_ALIGNMENT);
		lQueEres.setAlignmentX(CENTER_ALIGNMENT);
		bPersona.setAlignmentX(CENTER_ALIGNMENT);
		bAsociacion.setAlignmentX(CENTER_ALIGNMENT);
		
		//PANEL INFERIOR
		JPanel panelInferior = new JPanel();
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		panelInferior.add(bCancelar);
		
		
		//EVENTOS
		bAsociacion.addActionListener((e)->{
			VentanaRegistro12 vr12 = new VentanaRegistro12(principal);
			vr12.setVisible(true);
			dispose();
		});
		
		bPersona.addActionListener((e)->{
			VentanaRegistro11 vr11 = new VentanaRegistro11(principal);
			vr11.setVisible(true);
			dispose();
		});
		
		bCancelar.addActionListener((e)->{
			VentanaInicio vi = new VentanaInicio(principal);
			vi.setVisible(true);
			dispose();
		});
		
	}
	
	
	
	
}
