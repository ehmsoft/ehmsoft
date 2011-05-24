package ehmsoft;

import core.Juzgado;
import gui.NuevoJuzgado;
import persistence.Persistence;

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
		Persistence guardado = new Persistence();
		_juzgado = new Juzgado(_screen.getNombre(),_screen.getCiudad(),_screen.getDireccion(),_screen.getTelefono(),_screen.getTelefono());
		guardado.guardarJuzgado(_juzgado);		
	}
}