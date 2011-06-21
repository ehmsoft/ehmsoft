package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import core.Juzgado;

public class ListadoJuzgados extends MainScreen {

	private Object _selected;
	private ListadoJuzgadosLista _lista;

	public ListadoJuzgados() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Listado de juzgados");

		_lista = new ListadoJuzgadosLista() {
			protected boolean navigationClick(int status, int time) {
				_selected = get(_lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		_lista.insert(0, "Nuevo juzgado");
		add(_lista);
		addMenuItem(menuVer);
	}

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			int index = _lista.getSelectedIndex();
			VerJuzgadoController verJuzgado = new VerJuzgadoController(
					(Juzgado) _lista.get(_lista, index));
			UiApplication.getUiApplication().pushModalScreen(
					verJuzgado.getScreen());
			verJuzgado.actualizarJuzgado();
			_lista.delete(index);
			_lista.insert(index, verJuzgado.getJuzgado());
			_lista.setSelectedIndex(index);
		}
	};

	public void addJuzgado(Object juzgado) {
		_lista.insert(_lista.getSize(), juzgado);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
