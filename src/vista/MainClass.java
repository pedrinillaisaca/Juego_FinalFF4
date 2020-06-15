package vista;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.Buffer;
import java.util.ArrayList;


import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;

import controlador.ControladorBD;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import modelo.Pregunta;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ControladorBD c=new ControladorBD();
		Escritorio es=new Escritorio(c);
		es.setVisible(true);
		
		/**
		ControladorBD c=new ControladorBD();
		ArrayList<Pregunta>p=(ArrayList<Pregunta>) c.li
		starPreguntas(); 
		Ventana v=new Ventana(p);
		v.setVisible(true);


		/**
		 * Metodo para verificar que las preguntas se esten recuperando de forma 
		 * correcta de la base de datos
		 */

		/**for (int i = 0; i < p.size(); i++) {
			//System.out.println(p.get(i).toString());
			System.out.println(p.get(i).getPregunta());
			
			for (int j = 0; j < 4; j++) {
				//System.out.println(i);
				//System.out.println(p.get(i).getListRespuestas().get(j).getRespuesta());
				//System.out.println(p.get(i).getListRespuestas().get(j).isBand());
				
				//System.out.println("Pregunta: " + p.get(i).getPregunta() );
				System.out.print( p.get(i).getListRespuestas().get(j).getRespuesta());
				System.out.println( p.get(i).getListRespuestas().get(j).isBand());

			}
		}	*/

		try{
		    FileInputStream archivo;
		    Player player;
		    
		    archivo = new FileInputStream("musica/millonario.mp3");
		    BufferedInputStream sonido = new BufferedInputStream(archivo);
		    
		    player = new Player(sonido);
		    player.play();
		   
		}catch(JavaLayerException e){
		    e.printStackTrace();
		}catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}



		
		
			}
	}

	
	


