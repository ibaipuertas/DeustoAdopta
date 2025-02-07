package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import db.GestorBD;
import domain.Principal;
import domain.Usuario;

public class VentanaInicioSesion extends JFrame {

    private GestorBD gestorBD; // Añadir gestor de base de datos

    public VentanaInicioSesion(Principal principal) {
        gestorBD = new GestorBD(); // Inicializar conexión con la BD

        // AJUSTES DEL FRAME
        setBounds(10, 10, 900, 650);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // LOGO Y TITULO EN PANEL SUPERIOR
        JLabel lTitulo = new JLabel("DeustoAdopta");
        lTitulo.setFont(new Font("Serif", Font.BOLD, 50));
        JLabel lLogo = new JLabel();
        Image logoTemp = new ImageIcon("imagenes/logoDeustoAdopta.png").getImage();
        ImageIcon logo = new ImageIcon(logoTemp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        lLogo.setIcon(logo);
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBorder(new BevelBorder(1));
        getContentPane().add(panelSuperior, BorderLayout.NORTH);
        panelSuperior.add(lTitulo);
        panelSuperior.add(lLogo);
        setIconImage(logoTemp);

        // LABELS Y TEXTFIELDS EN PANEL CENTRAL
        JLabel lCabecera = new JLabel("Por favor, introduzca sus datos");
        lCabecera.setFont(new Font(null, Font.BOLD, 20));
        JLabel lcorreo = new JLabel("Correo Electrónico:");
        JTextField tfCorreo = new JTextField("", 30);
        JLabel lcontrasenia = new JLabel("Contraseña:");
        JPasswordField tfcontrasenia = new JPasswordField("", 10);

        JButton bIniciarSesion = new JButton("Iniciar sesión");
        JButton bCancelar = new JButton("Cancelar");

        JPanel panelCentral = new JPanel();
        panelCentral.setBorder(new EmptyBorder(120, 10, 10, 10));
        getContentPane().add(panelCentral, BorderLayout.CENTER);
        JPanel contenido = new JPanel();
        contenido.setLayout(new GridLayout(0, 1, 5, 5));
        panelCentral.add(contenido);
        JLabel lVacio = new JLabel("");
        contenido.add(lCabecera);
        contenido.add(lVacio);
        contenido.add(lVacio);
        contenido.add(lcorreo);
        contenido.add(tfCorreo);
        contenido.add(lcontrasenia);
        contenido.add(tfcontrasenia);

        // BOTONES DE CANCELAR E INICIAR SESION EN PANEL INFERIOR
        JPanel panelInferior = new JPanel();
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
        panelInferior.add(bCancelar);
        panelInferior.add(bIniciarSesion);

        // EVENTOS
        bIniciarSesion.addActionListener((e) -> {
            String correo = tfCorreo.getText();
            String con = new String(tfcontrasenia.getPassword()); // Convertir a String seguro

            try {
                Usuario u = gestorBD.getUsuarioPorCorreo(correo); // Buscar en la BD
                if (u == null) {
                    JOptionPane.showMessageDialog(null, "Para poder iniciar sesión tienes que estar registrado", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (u.getContrasenia().equals(con)) {
                        JOptionPane.showMessageDialog(null, "Bienvenido!", "SESIÓN INICIADA", JOptionPane.INFORMATION_MESSAGE);
                        VentanaPrincipal vp = new VentanaPrincipal(principal, u);
                        dispose();
                        vp.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        });

        bCancelar.addActionListener((e) -> {
            VentanaInicio vi = new VentanaInicio(principal);
            vi.setVisible(true);
            dispose();
        });
    }
}