package gui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoCamposScreen extends MainScreen {

	private Object _selected;
	private ListadoCamposLista _lista;

	public ListadoCamposScreen() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Listado de campos personalizados");

		_lista = new ListadoCamposLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(get(_lista, getSelectedIndex()))) {
					NuevoCampo n = new NuevoCampo();
					UiApplication.getUiApplication().pushModalScreen(
							n.getScreen());
					try {
						_lista.insert(1, n.getCampo());
					} catch (Exception e) {
						return true;
					}
					return true;
				} else {
					_selected = get(_lista, getSelectedIndex());
					UiApplication.getUiApplication().popScreen(getScreen());
					return true;
				}
			}
		};

		_lista.insert(0, "crear nuevo campo personalizado");
		add(_lista);
	}

	public void addCampo(Object campo) {
		_lista.insert(_lista.getSize(), campo);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}

}
