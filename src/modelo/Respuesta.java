package modelo;

public class Respuesta {
	/**
	 * El objeto respuesta tiene dos atributos 
	 * un elemento String para la respuesta y otro tipo boolean
	 * para diferenciar la respuesta correcta
	 */
	private String respuesta;
	private boolean band;

	public Respuesta() {
		respuesta = new String();
		band = false;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public boolean isBand() {
		return band;
	}

	public void setBand(boolean band) {
		this.band = band;
	}

	@Override
	public String toString() {
		return "Respuesta [respuesta=" + respuesta + ", band=" + band + "]";
	}

}
