package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import modelo.Pregunta;
import modelo.Respuesta;
import utilidades.ConexionBD;

public class ControladorBD {
	
	
/**
 * Este metodo recupera de la base de datos las preguntas con sus respectivas 
 * opciones de este modo el metodo retorna una lista de preguntas
 * @return
 */
	public List<Pregunta> listarPreguntas() {
		List<Pregunta> lista_Preguntas = new ArrayList<Pregunta>();

	for (int j = 0; j < 3; j++) {
		
		int numero = (int) (Math.random() * 50)+1;//numero random
		String parOimpar="";
		if(numero%2==0) {
			parOimpar="=0";
		}else {
			parOimpar="=1";
		}
		
	
		String sql = "SELECT * FROM PREGUNTA WHERE ESTADO"+parOimpar+" AND NIVEL ="+(j+1);
		System.out.println(sql);
		Connection con = null;
		try {
			con = ConexionBD.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// System.out.println(rs.getString("CEDULA"));
				Pregunta pregunta = new Pregunta();
				//System.out.println(rs.getInt("codigo"));
				pregunta.setPregunta(rs.getString("PREGUNTA"));
				pregunta.setCodigo(""+(rs.getInt("CODIGO")));//PASO DE INT A STRING
				pregunta.setNivel(rs.getInt("NIVEL"));
				// esp.setCodigo(rs.getInt("CODE_MED"));
				// esp.setNombre(rs.getString("NOMBRE").trim());

				lista_Preguntas.add(pregunta);
			}
		} catch (Exception e) {
			System.out.println("SALIO");
			e.printStackTrace();
		} finally {
			ConexionBD.close(con);
		}

		
		
		
	}
		
		
	/**
 * En este bucle recupero las pociones de cada pregunta 
 * ,cada opcion tiene un atributo bandera  que diferencia
 *  la respuesta correcta de la incorrecta
 */
		for (int i = 0; i < lista_Preguntas.size(); i++) {
			
			 //aqui extraigo todas las respuestas de cada una de las preguntas
			 //ojojojonsdfakputa
			
			String codPregunta=lista_Preguntas.get(i).getCodigo();//codigo de cada pregunta;
			//System.out.println("entra"+codPregunta);
			ArrayList<Respuesta> respuestas = new ArrayList<>();
			String sql2 = "SELECT  * \n"
					+ "FROM respuestas \n"
					+ "where codigopregunta = "+codPregunta;
		//	System.out.println(sql2);
			Connection con2 = null;
			try {
				con2 = ConexionBD.getConnection();
				PreparedStatement ps = con2.prepareStatement(sql2);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// System.out.println(rs.getString("CEDULA"));
					Respuesta r = new Respuesta();
					r.setRespuesta(rs.getString("respuesta"));
					//Se determina la respuesta correcta
					int bandB = rs.getInt("bandera");
					if (bandB == 1) {
						r.setBand(true);
					} else {
						r.setBand(false);
					}
					respuestas.add(r);// a�ado todas las respuestas
					//System.out.println(r.getRespuesta());
					//System.out.println(r.isBand());
				}
				// esp.setCodigo(rs.getInt("CODE_MED"));
				// esp.setNombre(rs.getString("NOMBRE").trim());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConexionBD.close(con2);
			}

			/**
			 * recupero de la base de datos todas las posibles respuestas de cada pregunta
			 * cada respuesta contiene un atributo para diferenciar la respuesta correcta
			 */
			
			lista_Preguntas.get(i).setListRespuestas(respuestas);

			/**Pregunta pp=lista_Preguntas.get(i);
			pp.setListRespuestas(respuestas);
			
			lista_Preguntas.remove(i);
			lista_Preguntas.add(pp);
			*/
		}

		return lista_Preguntas;
	}
/**
 * Guardamos la Pregunta
 * @param string
 * @param string2
 * @param string3
 * @param string4
 * @param string5
 */
	public void guardarPreguta(String string, String string2, String string3, String string4, String string5) {
		System.out.println("pregunta guardada");

		Connection con2 = null;
		String sql2 = "INSERT INTO PREGUNTAS" + 
		"(PREGUNTA, OPCION1,OPCION2,OPCION3,OPCION4)  " + 
				"VALUES (?,?,?,?,?)";

		try {
			con2 = ConexionBD.getConnection();

			PreparedStatement ps = con2.prepareStatement(sql2);
			ps.setString(1, string);
			ps.setString(2, string2);
			ps.setString(3, string3);
			ps.setString(4, string4);
			ps.setString(5, string5);

			ps.executeUpdate();
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			ConexionBD.close(con2);
		}

		JOptionPane.showMessageDialog// mensaje en un joption
		(null, "pregunta guardada\n" + string + "\n" + "");

	}

public void saveJuagador(String text, String text2, String text3) {
	try{
		  FileWriter file = new FileWriter("sources/jugadores.txt", true);
		  BufferedWriter escritura = new BufferedWriter(file);

		  escritura.append(text+";"+text2+";"+text3+"\n");
		

		  escritura.close();
		}catch(Exception e){
		  //e.getStackTrace();
		}
}
/**
 * RECUPERA LOS JUGADORES
 * @return
 */
public ArrayList<String> recuvaJugadores() {
	ArrayList<String> data=new ArrayList<>();
	try{
		  FileReader file = new FileReader("sources/jugadores.txt");
		  BufferedReader lectura = new BufferedReader(file);

		  String linea = "";
		  while(linea!=null){
		     linea = lectura.readLine();
		     String []partes=linea.split(";");
		     data.add(partes[1]);
		  }
		  lectura.close();
		}catch(Exception e){
		  e.printStackTrace();
		}
	
	
	return data;
}

}
