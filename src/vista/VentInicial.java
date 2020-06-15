package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.ControladorBD;

public class VentInicial extends JInternalFrame implements ActionListener {
	private JTextField codigo, nombre, direccion;
	private JButton registro;
	private ControladorBD control;
	//private ArrayList<String> data;
	private JTextField pregunta;
	private JTextField opcion2;
	private JTextField opcion1;
	private JTextField opcion3;
	private JTextField opcion4;
	private JButton savePRe;
	private JComboBox combo;
	private Ventana v;

	public VentInicial( ControladorBD control,Ventana v) {
		this.control=control;
		this.v=v;
		combo = new JComboBox();
	
		codigo = new JTextField(10);
		nombre = new JTextField(10);
		direccion = new JTextField(8);
		pregunta = new JTextField(20);
		opcion1 = new JTextField(10);
		opcion2 = new JTextField(10);
		opcion3 = new JTextField(10);
		opcion4 = new JTextField(10);
		registro = new JButton("Registrar");
		savePRe = new JButton("Guardar");
		actualizarCombo();
		incioComponentes();
	}

	private void incioComponentes() {
		this.setTitle("juego");
		this.setSize(250, 450);
		this.setClosable(true);
		this.setMaximizable(false);
		this.setMaximizable(true);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());

		JPanel norte = new JPanel();
		norte.setLayout(new FlowLayout());
		norte.add(new JLabel("Codigo Jugador"));
		norte.add(codigo);
		norte.add(new JLabel("Nombre Jugador"));
		norte.add(nombre);
		norte.add(new JLabel("Direccion Jugador"));
		norte.add(direccion);

		registro.addActionListener(this);
		registro.setActionCommand("registrarJU");
		norte.add(registro);

		norte.add(new JLabel("------------Pregunta------------"));
		norte.add(pregunta);
		norte.add(new JLabel("***Opcion1***"));
		norte.add(opcion1);
		norte.add(new JLabel("***Opcion2***"));
		norte.add(opcion2);
		norte.add(new JLabel("***Opcion3***"));
		norte.add(opcion3);
		norte.add(new JLabel("***Opcion4***"));
		norte.add(opcion4);
		
		savePRe.addActionListener(this);
		savePRe.setActionCommand("guardarPregunta");
		norte.add(savePRe);
		
		norte.add(new JLabel("------------Escojer Jugador------------"));
		norte.add(combo);
		c.add(norte, BorderLayout.CENTER);

	}

	public void actualizarCombo() {
		ArrayList<String> data=new ArrayList<>();
		data=control.recuvaJugadores();
		
		combo.removeAll();
		
		for (int i = 0; i < data.size(); i++) {
			//String d=(String) combo.getItemAt(i);
			//System.out.println(d);
			combo.addItem(data.get(i));
			//System.out.println(data.get(i));
		}
		
		combo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				v.setjugador(combo.getSelectedItem().toString());
			}
		});
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stu
		System.out.println(e.getActionCommand());
		String op = e.getActionCommand();
		switch (op) {
		case "guardarPregunta":

			control.guardarPreguta(pregunta.getText(),
					opcion1.getText(),
					opcion2.getText(),
					opcion3.getText(),
					opcion4.getText());
			break;
		case "registrarJU":
control.saveJuagador(codigo.getText(),nombre.getText(),direccion.getText());

	//data=control.recuvaJugadores();
addCombo();
			break;

		default:
			break;
		}
	}

	private void addCombo() {
		combo.addItem(nombre.getText());		
	}

}
