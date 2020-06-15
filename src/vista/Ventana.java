package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import modelo.Pregunta;
import modelo.Respuesta;

public class Ventana extends JInternalFrame implements ActionListener{
	/**
	 * Lista de variables globales nesesarias para la fincion del frame
	 */
	private ArrayList<Pregunta> p;//Lista de preguntas
	private ArrayList<Respuesta> r;//Lita de respuestas
	private String pregunta;//donde se guadara la pregunta
	private String nivel;
	private int c = 0;//Variable necesaria para interar las Preguntas en el frame 
	/**
	 * Elementos tipo respuesta necesarios para que el usuario 
	 * pueda escojer su respuesta
	 */
	private Respuesta opcionA;
	private Respuesta opcionB;
	private Respuesta opcionC;
	private Respuesta opcionD;
	private boolean comExterna ;//variable que cambiara de estado al pulsar una respuesta incoreccta
	private boolean bandCo;//Variable para controlar que el 50 50 sea ejecutado una sola vez

	private Jpanel panel;//panel para visualisar las cantidades de dinero
	private JButton comodin;//boton para realzar el 50 50;
	private Jpanel pp;
	private String nombre;
/**
 * inicializacion de variables
 * @param p
 */
	public Ventana(ArrayList<Pregunta> p) {
		nivel=new String();
		nombre=new String();
		comExterna=true;
		bandCo=true;
		panel=new Jpanel();
		pregunta = new String();
		opcionA = new Respuesta();
		opcionB = new Respuesta();
		opcionC = new Respuesta();
		opcionD = new Respuesta();
		comodin=new JButton(new ImageIcon("imagen/5050.JPG"));
		this.p = p;
		/**
		 * Con este metodo habilitamos la conexi�n con el arduino
		 * especificando el puerto, su constante y el evento escucha 
		 */
		try {

			arduino.arduinoRX("COM6", 9600, Listener);

		} catch (SerialPortException | ArduinoException ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}

		inicioComp();
		/**
		 * EL fondo del frame obtendra el color creado
		 */
		Color azul = new Color(28, 17, 47);
		this.setBackground(azul);
		repaint();

	}
	/**
	 * Se inicioalizan los componentes del frame
	 */
	private void inicioComp() {
		
		this.setTitle("pedro");
		this.setSize(900, 650);
		this.setClosable(true);
		this.setMaximizable(false);
		this.setMaximizable(true);
		comodin.addActionListener(this);
		comodin.setBounds(0, 0, 57, 38);
		 pp=new Jpanel();
		 pp.setLayout(null);
		pp.add(comodin);
	
		pp.setBounds(0,0, 50, 50);
		pp.setBackground(Color.red);
		panel.setBounds(750, 0, 200, 305);
		//panel.setBackground(Color.CYAN);
		/**
		 * Esto hara que al iniciar los componentes del frame la 
		 * primerapregunta ya este dibujada en dicho frame
		 */
		pregunta = p.get(c).getPregunta();
		nivel= "" +p.get(c).getNivel();
		r = p.get(c).getListRespuestas();
		opcionA = r.get(0);
		opcionB = r.get(1);
		opcionC = r.get(2);
		opcionD = r.get(3);

		repaint();
	}
	/**
	 *Se crean el objeto para conectar el arduino  
	 */
	private static PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
	/**
	 * Este metodo escucha las pulsaciones que estemos obteniendo del arduino
	 */
	private SerialPortEventListener Listener = new SerialPortEventListener() {

		@Override
		public void serialEvent(SerialPortEvent serialPortEvent) {
			try {
				if (arduino.isMessageAvailable()) {
					String op = arduino.printMessage();
					System.out.println(op);
					/**En el momento que el jugador preciona una respuesta
					 * incorrecta el juego detendra sin aceptar nuevos intentos
					 */
					if (comExterna == true)//BANDERA
						comparacion(op);
						ganador();
					
				}
			} catch (SerialPortException | ArduinoException ex) {
				ex.printStackTrace();
			}
		}
	};
	/**
	 * Como segun las pulsaciones arduino que sean detectadas por el arduino
	 * este retornara un String donde se procedera a realizar la comparacion con la respuesta 
	 * corecta
	 * @param clave
	 */
	private void comparacion(String clave) {
			// TODO Auto-generated method stub
		switch (clave) {
		case "a":
			System.out.println("respu " + opcionA.isBand());
			/**
			 * Si la respuesta es correcta el contador avanza y es dibujada la siguiente
			 * pregunta en el frame, tambien sus respueatas
			 */
			if (opcionA.isBand() == true) {
				c++;
				panel.dibujar(c);
				repaint();
				// break;
			} else {//Caso contrario el juego se termina
				System.out.println("perdio");
				jOption();
				comExterna=false;
			}
			break;
			/**
			 * Sucede el mismo proceso para lo siquientes casos de Switch
			 */
		case "b":
			System.out.println("respu " + opcionB.isBand());

			if (opcionB.isBand() == true) {
				c++;
				panel.dibujar(c);
				repaint();
				// break;

			} else {
				System.out.println("perdio");
				jOption();
				comExterna=false;

			}

			break;
		case "c":
			System.out.println("respu " + opcionC.isBand());

			if (opcionC.isBand() == true) {
				c++;
				panel.dibujar(c);
				repaint();
				// break;

			} else {
				System.out.println("perdio");
				jOption();
				comExterna=false;
			}

			break;
		case "d":
			System.out.println("respu " + opcionD.isBand());

			if (opcionD.isBand() == true) {
				c++;
				panel.dibujar(c);
				repaint();
				// break;
			} else {
				System.out.println("perdio");
				jOption();
				comExterna=false;
			}

			break;

		default:
			break;
		}
		/**
		 * El contador indica que se avanze ala siguiente 
		 * pregunta
		 */
		try {
			pregunta = p.get(c).getPregunta();
			nivel= "" +p.get(c).getNivel();
		r = p.get(c).getListRespuestas();
		opcionA = r.get(0);
		opcionB = r.get(1);
		opcionC = r.get(2);
		opcionD = r.get(3);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		}
	
	 

	private void jOption() {
			/**
			 * Al momento de pulsar una respuesa incorrecta se prosedera a mostrar al
			 * juador la respuesta correcta mas su cantidad de dinero ganada.
			 */
		
			int pos=0;
			Respuesta ress=new Respuesta();
			
			while(pos<4) {//busca la respuesta correcta
				ress=r.get(pos);
				//System.out.println(r.get(pos).getRespuesta());
				if(ress.isBand()==true) {
					break;
				}
				pos++;
			}
		//System.out.println(	panel.fin().substring(0,2) );
	String valS=panel.fin().substring(0,2);	
	valS=valS.replaceAll(" ", "");
		double val=Double.parseDouble(valS);
		val=val-1;
			System.out.println(val);
			double resultado=(val/5);
			String premio="";
			System.out.println(resultado);
			
			
		if (resultado < 3.0) {
			premio = "3,000";
			if (resultado < 2.0) {
				premio = "200";
				if (resultado < 1.0) {
					premio = "0";
				}
			}
		}
	        JOptionPane.showMessageDialog//mensaje en un joption
	        (null, "Incorrecto\n"
	        		+ "Respuesta correcta es: "+ress.getRespuesta()+"\n"
	        		+"Su ganancia es: "+premio);
		
		
	}
	public void paint(Graphics g) {
	//	super.paint(g);
		/**
		 * Se dibujan una serie de figuras la cuales son parecidas alas del
		 * juego original
		 */
		Color azul = new Color(28, 17, 47);
		g.setColor(azul);
		g.fillRect(0, 0, 900, 650);

		Color azulp = new Color(53, 10, 74);
		g.setColor(azulp);
		int Ex[] = { 30, 50, 840, 860, 840, 50 };
		int Ey[] = { 450, 420, 420, 450, 480, 480 };

		g.fillPolygon(Ex, Ey, 6);
		Color azulp1 = new Color(53, 10, 74);
		g.setColor(azulp1);
		int Ax[] = { 10, 30, 400, 420, 400, 30 };
		int Ay[] = { 525, 500, 500, 525, 550, 550 };

		// g.drawPolyline(ddx, ddy, 7);
		g.fillPolygon(Ax, Ay, 6);

		Color azulp2 = new Color(53, 10, 74);
		g.setColor(azulp2);
		int Bx[] = { 10, 30, 400, 420, 400, 30 };
		int By[] = { 595, 570, 570, 595, 620, 620 };

		// g.drawPolyline(ddx, ddy, 7);
		g.fillPolygon(Bx, By, 6);

		///////////////////////////////
		Color azulp3 = new Color(53, 10, 74);
		g.setColor(azulp3);
		int Cx[] = { 450, 470, 860, 880, 860, 470 };
		int Cy[] = { 525, 500, 500, 525, 550, 550 };

		// g.drawPolyline(ddx, ddy, 7);
		g.fillPolygon(Cx, Cy, 6);

		Color azulp4 = new Color(53, 10, 74);
		g.setColor(azulp4);
		int Dx[] = { 450, 470, 860, 880, 860, 470 };
		int Dy[] = { 595, 570, 570, 595, 620, 620 };

		// g.drawPolyline(Dx, Dy, 6);
		g.fillPolygon(Dx, Dy, 6);

		Image img = Toolkit.getDefaultToolkit().getImage("imagen/millonario3.jpg");

		g.drawImage(img, 250, 80, this);
		
		/**
		 * Se determina el tipo de fuente y el tama�o de la letra tambien la hubicaion de cada
		 * una de las preguntas y la respuesta
		 */
		g.setColor(Color.white);
		Font fon = new Font("Comic Sans MS", Font.BOLD, 17);
		g.setFont(fon);
		g.drawString(pregunta, 50, 455);
		g.drawString(opcionA.getRespuesta(), 50, 530);
		g.drawString(opcionB.getRespuesta(), 530, 530);
		g.drawString(opcionC.getRespuesta(), 50, 600);
		g.drawString(opcionD.getRespuesta(), 530, 600);
		g.drawString("Jugador: "+nombre, 50, 100);
		g.drawString("Nivel: "+nivel, 50, 150);
		
				  
		add(panel);
		add(pp);
		//pp.repaint();
		panel.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		/**
		 * Dentro de esta metodo esta programado el 50 50
		 */
		if(bandCo==true) {//condicion que controla que solo sea ejecutado una sola vez
			int cont=0;//variable que contara las dos respuestas borradas las cuales son falsas
			while(cont<2) {//Ciclo donde se eliminaran las opciones falsas
				try {
					System.out.println("entra");
					int numero = (int) (Math.random() * 4);//numero random
					Respuesta res=r.get(numero);//elegimos al azar una opcion 
					System.out.println(res.getRespuesta());
					if(res.isBand() != true) {//filtramos solo preguntas falsas
						res.setRespuesta("");//suprimimos la opcion falsa
						res.setBand(true);//para que no elimine la misma opcion cambiamos su bandera
						cont++;//cuenta las opciones falsas borradas
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
						
		}
		bandCo=false;//con este cambio el 50 50 queda inhabilitado 
		this.repaint();	
	}

	public void  ganador() {
		if (c>14) {
			JOptionPane.showMessageDialog(rootPane, "GANASTE 50,000 ");
		}
	}
	public void setjugador(String string) {
		 nombre = string;
		
	}
	
	
}
