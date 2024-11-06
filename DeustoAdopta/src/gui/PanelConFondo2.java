package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelConFondo2 extends JPanel{
	Image imagen;
	
	public PanelConFondo2(Image i) {
		imagen = i;
		setLayout(new BorderLayout());
	}
	
	public void setImagen(Image i) {
		imagen = i;
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D)g;
		if(imagen == null) {
			super.paintComponent(g);
		}else {
			gr.drawImage(imagen, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

}
