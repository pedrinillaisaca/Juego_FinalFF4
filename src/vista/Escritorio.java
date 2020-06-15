package vista;

import java.awt.BorderLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controlador.ControladorBD;
import modelo.Pregunta;

/**
 * 
 * @2018-05-13
 * 
 * @author Pedro Illaisaca @
 */
public class Escritorio extends JFrame implements ActionListener {

	/**
	 * A las cajas de texto se las declara como privadas para que sean visibles para
	 * los demas metodos
	 */

	private JMenu ingresarEmp;
	private JMenuItem regJu;
	private JMenuItem juego;
	private JMenuItem mnuSalir;

	private ControladorBD control;
	private Ventana v;
	private VentInicial vIn;

	private JDesktopPane escritorio;

	public Escritorio(ControladorBD control) {
		this.control = control;

		ArrayList<Pregunta> p = (ArrayList<Pregunta>) control.listarPreguntas();
		v = new Ventana(p);
		vIn = new VentInicial(control,v);
		/**
		 * Metodo para verificar que las preguntas se esten recuperando de forma
		 * correcta de la base de datos
		 */

		for (int i = 0; i < p.size(); i++) {
			// System.out.println(p.get(i).toString());
			System.out.println(p.get(i).getPregunta());

			for (int j = 0; j < 4; j++) {
				// System.out.println(i);
				// System.out.println(p.get(i).getListRespuestas().get(j).getRespuesta());
				// System.out.println(p.get(i).getListRespuestas().get(j).isBand());

				// System.out.println("Pregunta: " + p.get(i).getPregunta() );
				System.out.print(p.get(i).getListRespuestas().get(j).getRespuesta());
				System.out.println(p.get(i).getListRespuestas().get(j).isBand());

			}
		}
		init();
	}

	public void init() {

		setTitle("Pedro Illaisaca");
		setSize(905, 700);
		

		getContentPane().setLayout(new BorderLayout());
		escritorio = new JDesktopPane();
		getContentPane().add(escritorio, BorderLayout.CENTER);
		/**
		 * Este menu barra tendra la posiblidad de ingresar opciones que se mostrarar en
		 * el inicio de la ventana
		 */
		JMenuBar barra = new JMenuBar();

		ingresarEmp = new JMenu("Opciones");

		regJu = new JMenuItem("Registrar Jugador");
		regJu.addActionListener(this);
		regJu.setActionCommand("agreJugador");
		ingresarEmp.add(regJu);
		
		juego = new JMenuItem("Iniciar Juego");
		juego.addActionListener(this);
		juego.setActionCommand("play");
		ingresarEmp.add(juego);
		

		barra.add(ingresarEmp);
		/**
		 * Se crean los manu item los cuales iran adicionados en el menubar
		 */

		// JMenu option=new JMenu("Opciones");
		mnuSalir = new JMenuItem("Salir");
		//
		mnuSalir.addActionListener(this);
		mnuSalir.setActionCommand("mnuSalir");

		ingresarEmp.add(mnuSalir);

		setJMenuBar(barra);

		// getContentPane().add(barra); error
		// setJMenuBar(barra);

	}

	/**
	 * Este metodo se lo crea obligatoriamente al implementar el ActionListener
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String comando = e.getActionCommand();
		System.out.println("Eventos menu " + comando);

		switch (comando) {

		case "agreJugador":

			llamarVentanaIni();

			break;
		case "play":

			llamarVentana();

			break;

		case "mnuSalir":

			salir();

			break;

		default:
			break;
		}

	}
	

	private void llamarVentanaIni() {
		vIn.setVisible(true);

		if (vIn.isClosable()) {
			escritorio.remove(vIn);
			escritorio.add(vIn);
		}
		try {
			vIn.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Segun los switch del ActionListener y los botones que se pulsaron se
	 * determina a que metodo llamar
	 */

	private void llamarVentana() {
		// TODO Auto-generated method stub

		v.setVisible(true);

		if (v.isClosable()) {
			escritorio.remove(v);
			escritorio.add(v);
		}
		try {
			v.setSelected(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void salir() {
		System.exit(0);
	}

}
