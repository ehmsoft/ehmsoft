package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Categoria;

public class NuevaCategoria {

	private Categoria _categoria;
	private NuevaCategoriaScreen _screen;

	public NuevaCategoria() {
		_screen = new NuevaCategoriaScreen();
	}

	public NuevaCategoriaScreen getScreen() {
		return _screen;
	}

	public Categoria getCategoria() throws Exception {
		if (_categoria == null) {
			guardarCategoria();
		}
		return _categoria;
	}

	public void guardarCategoria() throws Exception {
		if (_screen.isGuardado()) {
			Persistence guardado = null;
			try {
				guardado = new Persistence();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			_categoria = new Categoria(_screen.getDescripcion());
			try {
				guardado.guardarCategoria(_categoria);
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		} else {
			throw new Exception("No se esta guardando el elemento");
		}
	}

}
