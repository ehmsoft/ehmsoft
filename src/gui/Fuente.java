package gui;

public class Fuente {
	private int _height;
	private int _color;
	private String _parametro;

	/**
	 * @param height
	 *            Diferencia con respecto a la fuente basica
	 * @param color
	 *            Color de la fuente en formato RGD ej:0x00FFFFFF
	 * @param parametro
	 *            el nombre del parametro a mostrar
	 */
	public Fuente(int height, int color, String parametro) {
		_height = height;
		_color = color;
		_parametro = parametro;
	}

	/**
	 * @return El tamaño de la fuente
	 */
	public int getHeight() {
		return _height;
	}

	/**
	 * @param Cambia
	 *            el tamaño de la fuente
	 */
	public void setHeight(int _height) {
		this._height = _height;
	}

	/**
	 * @return El color de la fuente
	 */
	public int getColor() {
		return _color;
	}

	/**
	 * @param Cambia
	 *            el color de la fuente
	 */
	public void setColor(int _color) {
		this._color = _color;
	}

	public String getParametro() {
		return _parametro;
	}

	public void setParametro(String parametro) {
		_parametro = parametro;
	}
}
