package gui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import domain.Animal;
import domain.Principal;
import domain.Usuario;

public class VentanaMisDadosEnAdopcion extends JFrame {
    
    private DefaultListModel<Animal> modeloListaMisDadosEnAdopcion; 
    private JList<Animal> listaMisDadosEnAdopcion; 
    private JScrollPane scrollListaMisDadosEnAdopcion;
    
    public VentanaMisDadosEnAdopcion(Principal principal, Usuario usuario) {
        
        //AJUSTES DEL FRAME
        setBounds(10,10,900,650);        
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //LOGO Y TITULO EN PANEL SUPERIOR
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

        JPanel panelCentral = new JPanel();
        JLabel lCabeceraLista = new JLabel("Registro de mis mascotas dadas en adopción");
        modeloListaMisDadosEnAdopcion = new DefaultListModel<>();
        listaMisDadosEnAdopcion = new JList<>(modeloListaMisDadosEnAdopcion);
        listaMisDadosEnAdopcion.setCellRenderer(new AnimalCellRenderer()); // Setea el renderizador personalizado
        scrollListaMisDadosEnAdopcion = new JScrollPane(listaMisDadosEnAdopcion);
        scrollListaMisDadosEnAdopcion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollListaMisDadosEnAdopcion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        panelCentral.setLayout(new BorderLayout());
        panelCentral.setBorder(new EmptyBorder(0,30,0,30));
        panelCentral.add(lCabeceraLista, BorderLayout.NORTH);
        panelCentral.add(scrollListaMisDadosEnAdopcion, BorderLayout.CENTER);
        getContentPane().add(panelCentral, BorderLayout.CENTER);
        
        //PANEL INFERIOR
        JPanel panelInferior = new JPanel();
        JButton bAtras = new JButton("Atrás");
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
        panelInferior.add(bAtras);
        
        //EVENTOS
        bAtras.addActionListener((e) -> {
            VentanaMiPerfil vmp = new VentanaMiPerfil(principal, usuario);
            vmp.setVisible(true);
            dispose();
        });
        
        cargarAnimalesEnAdopcion(usuario);
    }
    
    private void cargarAnimalesEnAdopcion(Usuario usuario) {
        ArrayList<Animal> listaAnimalesAdoptados = new ArrayList<>();
        
        for (Animal animal : Principal.getAnimales()) {
            if (animal.getPropietario().equals(usuario)) {
                listaAnimalesAdoptados.add(animal);
            }
        }
        
        for (Animal a : listaAnimalesAdoptados) {
            modeloListaMisDadosEnAdopcion.addElement(a);
        }
    }
    
    // Renderer personalizado para alternar colores y agregar un borde
    private class AnimalCellRenderer extends JPanel implements ListCellRenderer<Animal> {
        private JLabel label;

        public AnimalCellRenderer() {
            setLayout(new BorderLayout());
            label = new JLabel();
            label.setBorder(new LineBorder(Color.GRAY, 1)); // Aplica un borde a cada fila
            label.setOpaque(true);
            add(label, BorderLayout.CENTER);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Animal> list, Animal value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            label.setText(value.toString());

            // Alterna el color de fondo
            if (isSelected) {
                label.setBackground(Color.LIGHT_GRAY);
                label.setForeground(Color.BLACK);
            } else {
                label.setBackground(index % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                label.setForeground(Color.BLACK);
            }

            return this;
        }
    }
}
