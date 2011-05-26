package gui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoCamposPersonalizados extends MainScreen {

	/**
	 * 
	 */
	private Object _selected;
	private ListaListadoCamposPersonalizados _lista;
	
	public ListadoCamposPersonalizados() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		
		setTitle("Listado de campos personalizados");
		
		_lista = new ListaListadoCamposPersonalizados(){
			protected boolean navigationClick(int status, int time) {
				_selected = get(_lista, getSelectedIndex());
				return true;
			}
		};
		
		_lista.insert(0,"Nuevo campo personalizado");
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
