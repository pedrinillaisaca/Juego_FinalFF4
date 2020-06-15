package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Jpanel extends JPanel{
	/**
	 * El vector de String se lo usa para dibujar en el frame las 
	 * catidades de dinero que el jugador aspira a ganar 
	 * El contador determina la coordenada de las cantidades que el 
	 * jugador obtiene 
	 * Con el eje Y se determina en el nivel en el que se encuentra el jugador
	 */
	private String val[]= {
							" 1   50",
							" 2   75",
							" 3   100",
							" 4   150",
							" 5   200",
							" 6   400",
							" 7   600",
							" 8   800",
							" 9   1,200",
							"10   3,000",
							"11   5,000",
							"12   8,000",
							"13   12,000",
							"14   25,000",
							"15   50,000"
							};
	private int c;
	private int ejeY;
	public Jpanel() {
		super();
		ejeY=320;
	}
	/**
	 * Si la respuesta es verdadera las cantidad de dinero sube y es dibujada 
	 * con un color verde
	 * @param c
	 */
	public void dibujar(int c) {
		this.c=c-1;
		ejeY-=20;

	}
	/**
	 * Si el juador pierde retorna la cantidad de dinero ganado antes de responder
	 * de forma incorrecta
	 * @return
	 */
	public String  fin() {	
		return val[c];
	}
	
	public void paint(Graphics g) {
		//super.paint(g);
		Color azul = new Color(28, 17, 47);
		g.setColor(azul);
		g.fillRect(0, 0, 900, 650);
		Font fon = new Font("Comic Sans MS", Font.BOLD, 15);
		g.setFont(fon);
		g.setColor(Color.white);
		/**
		 * La lista de las cantidades de dinero son mostradas en el frame
		 */
		g.drawString(val[14], 20, 20);
		g.drawString(val[13], 20, 40);
		g.drawString(val[12], 20, 60);
		g.drawString(val[11], 20, 80);
		g.drawString(val[10], 20, 100);
		g.drawString(val[9], 20, 120);
		g.drawString(val[8], 20, 140);
		g.drawString(val[7], 20, 160);
		g.drawString(val[6], 20, 180);
		g.drawString(val[5], 20, 200);
		g.drawString(val[4], 20, 220);
		g.drawString(val[3], 20, 240);
		g.drawString(val[2], 20, 260);
		g.drawString(val[1], 20, 280);
		g.drawString(val[0], 20, 300);
		/**
		 * Segun vaya avanzando las respuestas correctas se dibujara cuanto
		 * dinero el jugador tiene actualmente
		 */
		g.setColor(Color.green);
		try {
					g.drawString(val[c], 20, ejeY);
		} catch (Exception e) {
			// TODO: handle exception
		}


		
	}
}
