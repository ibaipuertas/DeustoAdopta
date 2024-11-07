package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import domain.Animal;
import domain.Especie;
import domain.Principal;
import domain.Usuario;

public class VentanaAdoptar extends JFrame {

    private DefaultTableModel modeloDatosAnimales;
    private JTable tablaAnimales;
    private JScrollPane scrollTablaAnimales;
    private List<Animal> listaAnimales;
    private int mouseRowPersonajes = -1;

    public VentanaAdoptar(Principal principal, Usuario usuario) {

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

        // CREACION DE PANELES
        JPanel panelCentral = new JPanel();
        getContentPane().add(panelCentral, BorderLayout.CENTER);
        JPanel panelInferior = new JPanel();
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
        JPanel panelIzquierdo = new JPanel();
        getContentPane().add(panelIzquierdo, BorderLayout.WEST);

        // PANEL CENTRAL
        JComboBox<String> comboBoxEspecie = new JComboBox<String>();
        comboBoxEspecie.setBounds(265, 296, 94, 22);

        comboBoxEspecie.addItem("Todos");
        for (Especie e : Especie.values()) {
            comboBoxEspecie.addItem(e.name());  // Usar el nombre de la especie para el comboBox
        }

        // Agregar ActionListener al comboBox para filtrar los animales por especie
        comboBoxEspecie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String especieSeleccionada = (String) comboBoxEspecie.getSelectedItem();
                if ("Todos".equals(especieSeleccionada)) {
                    loadAnimales(null); // Mostrar todos los animales
                } else {
                    loadAnimales(Especie.valueOf(especieSeleccionada)); // Filtrar por especie seleccionada
                }
            }
        });

        String[] titulos = { "ESPECIE", "EDAD", "GENERO", "PROPIETARIO", "LOCALIDAD", "FOTO" };
        modeloDatosAnimales = new DefaultTableModel();
        modeloDatosAnimales.setColumnIdentifiers(titulos);
        tablaAnimales = new JTable(modeloDatosAnimales) {
            // Hacer que las celdas no sean editables
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Ninguna celda será editable
            }
        };
        tablaAnimales.setRowHeight(60);
        scrollTablaAnimales = new JScrollPane(tablaAnimales);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(30, 30, 0, 30));
        panelCentral.add(comboBoxEspecie, BorderLayout.NORTH);
        panelCentral.add(scrollTablaAnimales, BorderLayout.CENTER);

        // Obtener la lista de animales del objeto principal
        listaAnimales = principal.getAnimales();

        // Cargar todos los animales inicialmente
        loadAnimales(null);

        // PANEL INFERIOR (BOTONES DE CANCELAR Y CONFIRMAR)
        JButton bCancelar = new JButton("Cancelar");
        JButton bConfirmar = new JButton("Confirmar adopción");
        panelInferior.add(bCancelar);
        panelInferior.add(bConfirmar);

        // EVENTOS
        bCancelar.addActionListener((e) -> {
            VentanaPrincipal vp = new VentanaPrincipal(principal, usuario);
            vp.setVisible(true);
            dispose();
        });

        // Configuración del renderizador de celdas para centrar texto y aplicar color por especie
        tablaAnimales.setDefaultRenderer(Object.class, new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value == null ? "" : value.toString(), JLabel.CENTER); // Centrado
                cell.setOpaque(true);
                
                // Colorear la fila según la especie del animal
                Especie especie = (Especie) modeloDatosAnimales.getValueAt(row, 0); // Obtener especie en primera columna
                if (especie != null) {
                    switch (especie) {
                        case PERRO:
                            cell.setBackground(new Color(200, 230, 255)); // Color para perros
                            break;
                        case GATO:
                            cell.setBackground(new Color(255, 230, 200)); // Color para gatos
                            break;
                        case AVE:
                            cell.setBackground(new Color(230, 255, 200)); // Color para aves
                            break;
                        case HURON:
                            cell.setBackground(new Color(108, 49, 42)); // Color para huron
                            break;
                        case REPTIL:
                            cell.setBackground(new Color(255, 105, 97)); // Color para reptiles
                            break;
                        default:
                            cell.setBackground(Color.WHITE); // Color por defecto
                            break;
                    }
                }

                // Si la fila está seleccionada, aplicar colores de selección
                if (isSelected) {
                    cell.setBackground(table.getSelectionBackground());
                    cell.setForeground(table.getSelectionForeground());
                } else {
                    cell.setForeground(Color.BLACK);
                }

                return cell;
            }
        });
    }

    /**
     * Cargar los animales en la tabla, filtrados por especie si se proporciona un filtro
     * @param especieFiltro Especie por la cual filtrar los animales (null para mostrar todos)
     */
    private void loadAnimales(Especie especieFiltro) {
        modeloDatosAnimales.setRowCount(0); // Limpiar los datos de la tabla

        // Se añaden los animales filtrados al modelo de datos
        for (Animal animal : listaAnimales) {
            if (especieFiltro == null || especieFiltro == animal.getEspecie()) {
                // Aquí se añade la ruta de la foto del animal
                ImageIcon fotoIcon = new ImageIcon(getClass().getClassLoader().getResource("data/" + animal.getFotoAnimal()));
                Image foto = fotoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH); // Ajustamos la imagen al tamaño de la celda
                modeloDatosAnimales.addRow(new Object[] {
                    animal.getEspecie(),
                    animal.getEdad(),
                    animal.getGenero(),
                    animal.getPropietario(),
                    animal.getPropietario().getComunidadAutonoma(),
                    new ImageIcon(foto) // La foto del animal como imagen en la columna correspondiente
                });
            }
        }

        // Notificar a la tabla que el modelo de datos ha cambiado
        modeloDatosAnimales.fireTableDataChanged();
    }
}
