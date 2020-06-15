package modelo;

import java.util.ArrayList;

public class Pregunta {
/**
 * Cada pregunta tiene su conjunto de opciones por eso
 * hay una variable de tipo String y una lista de Respuestas 
 */
	private ArrayList<Respuesta> listRespuestas;
	private String pregunta;
	private String codigo;
	private int nivel;

	public Pregunta() {

		listRespuestas = new ArrayList<>();
		pregunta = new String();
		codigo=new String();
	}

	public ArrayList<Respuesta> getListRespuestas() {
		return listRespuestas;
	}

	public void setListRespuestas(ArrayList<Respuesta> listRespuestas) {
		this.listRespuestas = listRespuestas;
	}

	public String getPregunta() {
		return pregunta;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Pregunta [listRespuestas=" + listRespuestas + ", pregunta=" + pregunta + ", codigo=" + codigo + "]";
	}

	

	
	
}
