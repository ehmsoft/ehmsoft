package gui;

import java.util.Vector;

import core.Persona;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoPersonas extends MainScreen {

	private Object _selected;
	private ListadoPersonasLista _lista;

	public ListadoPersonas(int tipo, Vector fuentes) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		if (tipo == 1)
			setTitle("Listado de demandantes");
		else
			setTitle("Listado de demandados");

		_lista = new ListadoPersonasLista(fuentes) {
			protected boolean navigationClick(int status, int time) {
				_selected = get(_lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		if (tipo == 1)
			_lista.insert(0, "Nuevo demandante");
		else
			_lista.insert(0, "Nuevo demandado");
		add(_lista);
		addMenuItem(menuVer);
	}
	
	public ListadoPersonas(int tipo) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		if (tipo == 1)
			setTitle("Listado de demandantes");
		else
			setTitle("Listado de demandados");

		_lista = new ListadoPersonasLista() {
			protected boolean navigationClick(int status, int time) {
				_selected = get(_lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		if (tipo == 1)
			_lista.insert(0, "Nuevo demandante");
		else
			_lista.insert(0, "Nuevo demandado");
		add(_lista);
		addMenuItem(menuVer);
	}
	
	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			int index = _lista.getSelectedIndex();
			VerPersonaController verPersona = new VerPersonaController((Persona) _lista.get(_lista, index));
			UiApplication.getUiApplication().pushModalScreen(verPersona.getScreen());
			verPersona.actualizarPersona();
			_lista.delete(index);
			_lista.insert(index,verPersona.getPersona());
			_lista.setSelectedIndex(index);
		}
	};

	public void addPersona(Object persona) {
		_lista.insert(_lista.getSize(), persona);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
