package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Juzgado;

public class VerJuzgadoController {
	private VerJuzgado _screen;
	private Juzgado _juzgado;

	public VerJuzgadoController(Juzgado juzgado) {
		_screen = new VerJuzgado(juzgado);
		_juzgado = juzgado;
	}

	public VerJuzgado getScreen() {
		return _screen;
	}

	public void actualizarJuzgado() {
		try {
			Persistence persistence = new Persistence();
			boolean cambio = false;
			Juzgado juzgado = _screen.getJuzgado();

			if (juzgado.getNombre() != _screen.getNombre())
				cambio = true;
			if (juzgado.getCiudad() != _screen.getCiudad())
				cambio = true;
			if (juzgado.getTelefono() != _screen.getTelefono())
				cambio = true;
			if (juzgado.getDireccion() != _screen.getDireccion())
				cambio = true;
			if (juzgado.getTipo() != _screen.getTipo())
				cambio = true;

			if (cambio) {
				_juzgado = new Juzgado(_screen.getNombre(), _screen.getCiudad(), _screen.getDireccion(), _screen.getTelefono(), _screen.getTipo());
				persistence.actualizarJuzgado(_juzgado);
			}
		} catch (Exception e) {
			Dialog.alert("actualizarJuzgado -> " + e.toString());
		}
	}
	
	public Juzgado getJuzgado() {
		return _juzgado;
	}
}
