package gui;

import persistence.Persistence;
import core.Juzgado;

public class NuevoJuzgado {
	private Juzgado _juzgado;
	private NuevoJuzgadoScreen _screen;

	public NuevoJuzgado() {
		this._screen = new NuevoJuzgadoScreen();
	}

	public NuevoJuzgadoScreen getScreen() {
		return _screen;
	}

	public Juzgado getJuzgado() {
		if(_juzgado == null) {
			guardarJuzgado();
		}
		return _juzgado;
	}

	public void guardarJuzgado() {
		Persistence guardado = null;
		try {
			guardado = new Persistence();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_juzgado = new Juzgado(_screen.getNombre(), _screen.getCiudad(),
				_screen.getDireccion(), _screen.getTelefono(),
				_screen.getTelefono());
		try {
			guardado.guardarJuzgado(_juzgado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}