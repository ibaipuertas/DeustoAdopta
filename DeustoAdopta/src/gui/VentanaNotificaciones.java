package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import domain.Animal;
import domain.Principal;
import domain.SolicitudAdopcion;
import domain.Usuario;

public class VentanaNotificaciones extends JFrame {
    
    private DefaultTableModel modeloTablaSolicitudesRecibidas;
    private DefaultTableModel modeloTablaSolicitudesEnviadas;
    private JTable tablaSolicitudesRecibidas;
    private JScrollPane scrollTablaSolicitudesRecibidas;
    private JTable tablaSolicitudesEnviadas;
    private JScrollPane scrollTablaSolicitudesEnviadas;
    private ArrayList<SolicitudAdopcion> listaSolicitudes;
    private ArrayList<SolicitudAdopcion> listaSolicitudesEnviadas = new ArrayList<>();
    private ArrayList<SolicitudAdopcion> listaSolicitudesRecibidas = new ArrayList<>();
    
    public VentanaNotificaciones(Principal principal, Usuario usuario) {
        
        // AJUSTES DEL FRAME
        setBounds(10, 10, 900, 650);        
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // TITULO Y LOGO, EN PANEL SUPERIOR 
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
        
        // PANEL INFERIOR
        JPanel panelInferior = new JPanel();
        JButton bAtras = new JButton("Atrás");
        panelInferior.add(bAtras);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
        
        // TABLA SOLICITUDES DE ADOPCIÓN EN PANEL CENTRAL
        JPanel panelCentral = new JPanel();
        JLabel lcabecera = new JLabel("Solicitudes recibidas:");
        JButton bAnadir = new JButton("Aceptar solicitud");
        String[] titulosRecibidas = {"NOMBRE", "RAZA", "SOLICITANTE", "LOCALIDAD", "ESTADO"};
        
        modeloTablaSolicitudesRecibidas = new DefaultTableModel(titulosRecibidas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Hace que las celdas no sean editables
            }
        };
        
        tablaSolicitudesRecibidas = new JTable(modeloTablaSolicitudesRecibidas);
        scrollTablaSolicitudesRecibidas = new JScrollPane(tablaSolicitudesRecibidas);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelCentral.add(lcabecera, BorderLayout.NORTH);
        panelCentral.add(scrollTablaSolicitudesRecibidas, BorderLayout.CENTER);
        panelCentral.add(bAnadir, BorderLayout.SOUTH);
        getContentPane().add(panelCentral, BorderLayout.CENTER);
        
        // TABLA SOLICITUDES ENVIADAS EN PANEL DERECHO
        JPanel panelDerecho = new JPanel();
        JLabel lCabeceraLista = new JLabel("Solicitudes enviadas");
        String[] titulosEnviadas = {"NOMBRE", "RAZA", "SOLICITADO", "LOCALIDAD", "ESTADO"};
        
        modeloTablaSolicitudesEnviadas = new DefaultTableModel(titulosEnviadas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  // Hace que las celdas no sean editables
            }
        };
        
        tablaSolicitudesEnviadas = new JTable(modeloTablaSolicitudesEnviadas);
        scrollTablaSolicitudesEnviadas = new JScrollPane(tablaSolicitudesEnviadas);
        panelDerecho.setLayout(new BorderLayout());
        panelDerecho.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelDerecho.add(lCabeceraLista, BorderLayout.NORTH);
        panelDerecho.add(scrollTablaSolicitudesEnviadas, BorderLayout.CENTER);
        getContentPane().add(panelDerecho, BorderLayout.EAST);
        
        // Centrar el contenido de las celdas y ajustar la altura de las filas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tablaSolicitudesRecibidas.setDefaultRenderer(Object.class, centrado);
        tablaSolicitudesEnviadas.setDefaultRenderer(Object.class, centrado);
        tablaSolicitudesRecibidas.setRowHeight(tablaSolicitudesRecibidas.getRowHeight() * 3);
        tablaSolicitudesEnviadas.setRowHeight(tablaSolicitudesEnviadas.getRowHeight() * 3);
        
        // Cargar todas las solicitudes a una lista
        listaSolicitudes = principal.getSolicitudesAdopcion();
        
        // Añadir las solicitudes recibidas y enviadas del usuario del login a sus listas correspondientes
        for (SolicitudAdopcion sA : listaSolicitudes) {
            if (sA.getUsuarioSolicitado().equals(usuario)) {
                listaSolicitudesRecibidas.add(sA);
            }
            if (sA.getUsuarioSolicitante().equals(usuario)) {
                listaSolicitudesEnviadas.add(sA);
            }
        }
        
        // Cargar las solicitudes recibidas en la tabla correspondiente
        for (SolicitudAdopcion solicitud : listaSolicitudesRecibidas) {
            modeloTablaSolicitudesRecibidas.addRow(new Object[] {
                solicitud.getAnimal().getNombre(),
                solicitud.getAnimal().getRaza(),
                solicitud.getUsuarioSolicitante().getCorreoElectronico(),
                solicitud.getUsuarioSolicitante().getComunidadAutonoma(),
                solicitud.getEstado() ? "Aceptada" : "No aceptada"
            });
        }
        
        // Cargar las solicitudes enviadas en la tabla correspondiente
        for (SolicitudAdopcion solicitud : listaSolicitudesEnviadas) {
            modeloTablaSolicitudesEnviadas.addRow(new Object[] {
                solicitud.getAnimal().getNombre(),
                solicitud.getAnimal().getRaza(),
                solicitud.getUsuarioSolicitado().getCorreoElectronico(),
                solicitud.getUsuarioSolicitado().getComunidadAutonoma(),
                solicitud.getEstado() ? "Aceptada" : "No aceptada"
            });
        }
        
        // Renderer para colorear las filas basadas en el estado
        DefaultTableCellRenderer rendererEstado = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                boolean estado = table.getValueAt(row, 4).equals("Aceptada");
                
                // Cambiar color de fondo dependiendo del estado
                setBackground(estado ? Color.GREEN.brighter() : Color.RED.brighter());
                setForeground(Color.BLACK);
                
                return this;
            }
        };

        // Aplicar el renderer a la columna "ESTADO" en ambas tablas
        tablaSolicitudesRecibidas.getColumnModel().getColumn(4).setCellRenderer(rendererEstado);
        tablaSolicitudesEnviadas.getColumnModel().getColumn(4).setCellRenderer(rendererEstado);
        
        // EVENTOS
        bAtras.addActionListener((e) -> {
            VentanaPrincipal vp = new VentanaPrincipal(principal, usuario);
            vp.setVisible(true);
            dispose();
        });

        // Acción del botón "Aceptar solicitud"
        bAnadir.addActionListener((e) -> {
            int selectedRow = tablaSolicitudesRecibidas.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una solicitud para aceptar.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Confirmación del usuario
            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de aceptar esta solicitud?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Obtener la solicitud correspondiente
                SolicitudAdopcion solicitud = listaSolicitudesRecibidas.get(selectedRow);

                // Cambiar el estado de la solicitud y actualizar la tabla
                solicitud.aceptarSolicitud();
                actualizarTablaSolicitudesRecibidas();
            }
        });
    }

    private void actualizarTablaSolicitudesRecibidas() {
        // Vaciar la tabla
        modeloTablaSolicitudesRecibidas.setRowCount(0);

        // Volver a cargar las solicitudes con el estado actualizado
        for (SolicitudAdopcion solicitud : listaSolicitudesRecibidas) {
            modeloTablaSolicitudesRecibidas.addRow(new Object[] {
                solicitud.getAnimal().getNombre(),
                solicitud.getAnimal().getRaza(),
                solicitud.getUsuarioSolicitante().getCorreoElectronico(),
                solicitud.getUsuarioSolicitante().getComunidadAutonoma(),
                solicitud.getEstado() ? "Aceptada" : "No aceptada"
            });
        }
    }
}
