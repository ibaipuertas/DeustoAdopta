package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import db.GestorBD;
import domain.Principal;
import domain.SolicitudAdopcion;
import domain.Usuario;

public class VentanaNotificaciones extends JFrame {
    private DefaultTableModel modeloTablaSolicitudesRecibidas;
    private DefaultTableModel modeloTablaSolicitudesEnviadas;
    private JTable tablaSolicitudesRecibidas;
    private JTable tablaSolicitudesEnviadas;
    private GestorBD gestorBD = new GestorBD();
    private Usuario usuario;

    public VentanaNotificaciones(Principal principal, Usuario usuario) {
        this.usuario = usuario;

        setBounds(10, 10, 900, 650);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lTitulo = new JLabel("DeustoAdopta");
        lTitulo.setFont(new Font("Serif", Font.BOLD, 50));
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBorder(new BevelBorder(1));
        getContentPane().add(panelSuperior, BorderLayout.NORTH);
        panelSuperior.add(lTitulo);

        JPanel panelInferior = new JPanel();
        JButton bAtras = new JButton("Atrás");
        panelInferior.add(bAtras);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);

        JPanel panelCentral = new JPanel();
        JLabel lcabecera = new JLabel("Solicitudes recibidas pendientes:");
        JButton bAceptar = new JButton("Aceptar solicitud");
        String[] titulosRecibidas = {"NOMBRE", "RAZA", "SOLICITANTE", "LOCALIDAD", "ESTADO"};

        modeloTablaSolicitudesRecibidas = new DefaultTableModel(titulosRecibidas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaSolicitudesRecibidas = new JTable(modeloTablaSolicitudesRecibidas);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelCentral.add(lcabecera, BorderLayout.NORTH);
        panelCentral.add(new JScrollPane(tablaSolicitudesRecibidas), BorderLayout.CENTER);
        panelCentral.add(bAceptar, BorderLayout.SOUTH);
        getContentPane().add(panelCentral, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel();
        JLabel lCabeceraLista = new JLabel("Solicitudes enviadas pendientes:");
        String[] titulosEnviadas = {"NOMBRE", "RAZA", "SOLICITADO", "LOCALIDAD", "ESTADO"};

        modeloTablaSolicitudesEnviadas = new DefaultTableModel(titulosEnviadas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaSolicitudesEnviadas = new JTable(modeloTablaSolicitudesEnviadas);
        panelDerecho.setLayout(new BorderLayout());
        panelDerecho.setBorder(new EmptyBorder(0, 10, 0, 10));
        panelDerecho.add(lCabeceraLista, BorderLayout.NORTH);
        panelDerecho.add(new JScrollPane(tablaSolicitudesEnviadas), BorderLayout.CENTER);
        getContentPane().add(panelDerecho, BorderLayout.EAST);

        cargarSolicitudes();

        bAtras.addActionListener(e -> {VentanaPrincipal ven = new VentanaPrincipal(principal, usuario);
        dispose();
		ven.setVisible(true);
        		});

        bAceptar.addActionListener(e -> {
            int selectedRow = tablaSolicitudesRecibidas.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona una solicitud para aceptar.", "Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de aceptar esta solicitud?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int solicitudId = (int) tablaSolicitudesRecibidas.getValueAt(selectedRow, 0);
                gestorBD.aceptarSolicitud(solicitudId);
                cargarSolicitudes();
            }
        });
    }

    private void cargarSolicitudes() {
        modeloTablaSolicitudesRecibidas.setRowCount(0);
        modeloTablaSolicitudesEnviadas.setRowCount(0);

        List<SolicitudAdopcion> recibidas = gestorBD.getSolicitudesRecibidas(usuario.getId(), false);
        for (SolicitudAdopcion solicitud : recibidas) {
            modeloTablaSolicitudesRecibidas.addRow(new Object[]{
                solicitud.getId(),
                solicitud.getAnimal().getNombre(),
                solicitud.getAnimal().getRaza(),
                solicitud.getUsuarioSolicitante().getCorreoElectronico(),
                solicitud.getUsuarioSolicitante().getComunidadAutonoma(),
                "Pendiente"
            });
        }

        List<SolicitudAdopcion> enviadas = gestorBD.getSolicitudesEnviadas(usuario.getId(), false);
        for (SolicitudAdopcion solicitud : enviadas) {
            modeloTablaSolicitudesEnviadas.addRow(new Object[]{
                solicitud.getAnimal().getNombre(),
                solicitud.getAnimal().getRaza(),
                solicitud.getUsuarioSolicitado().getCorreoElectronico(),
                solicitud.getUsuarioSolicitado().getComunidadAutonoma(),
                "Pendiente"
            });
        }
    }
}
