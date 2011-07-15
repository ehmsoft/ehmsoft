package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Categoria;

public class VerCategoria {
	private VerCategoriaScreen _screen;
	private Categoria _categoria;

	public VerCategoria(Categoria categoria) {
		_screen = new VerCategoriaScreen(categoria);
		_categoria = categoria;
	}

	public VerCategoriaScreen getScreen() {
		return _screen;
	}

	public void actualizarCategoria() throws Exception{
		if (_screen.isGuardado()) {
			try {
				Persistence persistence = new Persistence();
				boolean cambio = false;

				if (!_categoria.getDescripcion().equals(_screen.getDescripcion()))
					cambio = true;

				if (cambio) {
					_categoria = new Categoria(_categoria.getId_categoria(), _screen.getDescripcion());
					persistence.actualizarCategoria(_categoria);
				}
			} catch (Exception e) {
				Dialog.alert("actualizarCategoria -> " + e.toString());
			}
		}
		if(_screen.isEliminado()) {
			Persistence p;
			try {
				p = new Persistence();
				p.borrarCategoria(_categoria);
			} catch(Exception e) {
				Dialog.alert(e.toString());
			}
			throw new Exception("Se está eliminando la categoría con id: "+_categoria.getId_categoria());
		}
	}

	public Categoria getCategoria() {
		return _categoria;
	}
}