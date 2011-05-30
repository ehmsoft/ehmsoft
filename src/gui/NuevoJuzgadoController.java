package gui;

import persistence.Persistence;
import core.Juzgado;

public class NuevoJuzgadoController {
	private Juzgado _juzgado;
	private NuevoJuzgado _screen;

	public NuevoJuzgadoController() {
		this._screen = new NuevoJuzgado();
	}

	public NuevoJuzgado getScreen() {
		return _screen;
	}

	public Juzgado getJuzgado() {
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