package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.GestorBD;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Animal;
import domain.Especie;
import domain.Principal;
import domain.Usuario;
import domain.SolicitudAdopcion;

public class VentanaAdoptar extends JFrame {

    private DefaultTableModel modeloDatosAnimales;
    private JTable tablaAnimales;
    private JScrollPane scrollTablaAnimales;
    private List<Animal> listaAnimales;
    private int mouseRowPersonajes = -1;
    private GestorBD gestorBD= new GestorBD();
    
    private static final Map<Especie, Color> especieColores = new HashMap<>();

    static {
        especieColores.put(Especie.PERRO, Color.BLUE);
        especieColores.put(Especie.GATO, Color.RED);
        especieColores.put(Especie.HURON, Color.GREEN);
        especieColores.put(Especie.AVE, Color.ORANGE);
        especieColores.put(Especie.PEZ, Color.CYAN);
        especieColores.put(Especie.REPTIL, Color.MAGENTA);
        especieColores.put(Especie.ROEDOR, Color.PINK);
    }
    
    
    public VentanaAdoptar(Principal principal, Usuario usuario) {
        // AJUSTES DEL FRAME
        //setBounds(10, 10, 900, 650);
    	setExtendedState(JFrame.MAXIMIZED_BOTH);  // Maximiza la ventana
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
        //IAG - Coversacion con ChatGPT para el filtro 
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
        tablaAnimales.setRowHeight(100);
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
        
        JButton bConfirmar = new JButton("Adoptar");
        bCancelar.setFont(new Font("Arial", Font.BOLD, 18)); // Aumenta el tamaño de la fuente
        bCancelar.setPreferredSize(new Dimension(150, 50));  // Ajusta el tamaño del botón

        bConfirmar.setFont(new Font("Arial", Font.BOLD, 18)); // Aumenta el tamaño de la fuente
        bConfirmar.setPreferredSize(new Dimension(150, 50));  // Ajusta el tamaño del botón
        panelInferior.add(bCancelar);
        panelInferior.add(bConfirmar);

        // EVENTOS
        bCancelar.addActionListener((e) -> {
            VentanaPrincipal vp = new VentanaPrincipal(principal, usuario);
            vp.setVisible(true);
            dispose();
        });

        bConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tablaAnimales.getSelectedRow();
                
                if (selectedRow != -1) {  // Comprobar si hay una fila seleccionada
                    // Obtener el animal correspondiente a la fila seleccionada
                    Animal animalSeleccionado = listaAnimales.get(selectedRow);
                    Usuario propietario = animalSeleccionado.getPropietario();
                    if (propietario.getId() == usuario.getId()) {
                        JOptionPane.showMessageDialog(
                            VentanaAdoptar.this,
                            "No puedes adoptar a tu propio animal.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    
                    // Mostrar el mensaje de confirmación
                    int confirmacion = JOptionPane.showConfirmDialog(
                        VentanaAdoptar.this,
                        "¿Confirmar solicitud de adopción para el animal seleccionado?",
                        "Confirmar Adopción",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (confirmacion == JOptionPane.YES_OPTION) {
                    	Usuario propietario2 = animalSeleccionado.getPropietario();
                        SolicitudAdopcion solicitud = new SolicitudAdopcion(-1, usuario, propietario, animalSeleccionado,false);
                        try {
                            gestorBD.insertarSolicitud(solicitud);
                            JOptionPane.showMessageDialog(
                                VentanaAdoptar.this,
                                "Solicitud de adopción creada exitosamente.\nSe ha enviado solicitud a: " + propietario.getCorreoElectronico(),
                                "Éxito",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(
                                VentanaAdoptar.this,
                                "Error al guardar la solicitud en la base de datos: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                } else {
                    // Mostrar mensaje si no se ha seleccionado ningún animal
                    JOptionPane.showMessageDialog(
                        VentanaAdoptar.this,
                        "Por favor, seleccione un animal antes de confirmar la adopción.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        
     // Configuración del renderizador de celdas para centrar texto y aplicar color por especie
     // Configuración del renderizador para mostrar imágenes en la tabla
        TableCellRenderer centeredRenderer = new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = new JLabel(value == null ? "" : value.toString(), JLabel.CENTER); // Centrado de texto
                cell.setOpaque(true);

                // Colores de selección y fondo
                if (isSelected) {
                    cell.setBackground(table.getSelectionBackground());
                    cell.setForeground(table.getSelectionForeground());
                } else {
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.BLACK);
                }

                return cell;
            }
        };

        // Aplicación del renderizador centrado a las columnas de especie, edad, género y propietario
        tablaAnimales.getColumnModel().getColumn(0).setCellRenderer(centeredRenderer); // Especie
        tablaAnimales.getColumnModel().getColumn(1).setCellRenderer(centeredRenderer); // Edad
        tablaAnimales.getColumnModel().getColumn(2).setCellRenderer(centeredRenderer); // Género
        tablaAnimales.getColumnModel().getColumn(3).setCellRenderer(centeredRenderer); // Propietario
        tablaAnimales.getColumnModel().getColumn(4).setCellRenderer(centeredRenderer); // Localidad

        // Configuración del renderizador para mostrar imágenes en la columna de fotos
        tablaAnimales.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setOpaque(true);

                    if (isSelected) {
                        label.setBackground(table.getSelectionBackground());
                    } else {
                        label.setBackground(Color.WHITE);
                    }

                    return label;
                } else {
                    JLabel label = new JLabel(value == null ? "" : value.toString(), JLabel.CENTER);
                    label.setOpaque(true);

                    if (isSelected) {
                        label.setBackground(table.getSelectionBackground());
                        label.setForeground(table.getSelectionForeground());
                    } else {
                        label.setBackground(Color.WHITE);
                        label.setForeground(Color.BLACK);
                    }

                    return label;
                }
            }
        });
    }
    /**
     * Cargar los animales en la tabla, filtrados por especie si se proporciona un filtro
     * @param especieFiltro Especie por la cual filtrar los animales (null para mostrar todos)
     */
    private void loadAnimales(Especie especieFiltro) {
        modeloDatosAnimales.setRowCount(0);
        try {
            listaAnimales = gestorBD.getAnimales();
            for (Animal animal : listaAnimales) {
                if (especieFiltro == null || animal.getEspecie() == especieFiltro) {
                    String fotoNombre = animal.getFotoAnimal();
                    ImageIcon fotoIcon;
                    File fotoFile = new File("imagenes/" + fotoNombre);
                    if (fotoNombre == null || fotoNombre.isEmpty() || !fotoFile.exists()) {
                        Image logoTemp = new ImageIcon("imagenes/logoDeustoAdopta.png").getImage();
                        fotoIcon = new ImageIcon(logoTemp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
                    } else {
                        Image foto = new ImageIcon(fotoFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        fotoIcon = new ImageIcon(foto);
                    }
                    modeloDatosAnimales.addRow(new Object[] {
                        animal.getEspecie(),
                        animal.getEdad(),
                        animal.getGenero(),
                        animal.getPropietario().getCorreoElectronico(),
                        animal.getPropietario().getComunidadAutonoma(),
                        fotoIcon
                    });
                }
            }
            modeloDatosAnimales.fireTableDataChanged();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }
