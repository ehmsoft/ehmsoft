package gui.Listados;

import gui.ListaScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class ListadoProcesosScreen extends ListaScreen implements
		ListadoProcesosInterface {

	private ObjectChoiceField _cfCategorias;

	public ListadoProcesosScreen() {
		super();

		_cfCategorias = new ObjectChoiceField();
		_cfCategorias.setFocusListener(listener);

		HorizontalFieldManager title = new HorizontalFieldManager(USE_ALL_WIDTH);
		title.add(new LabelField("Procesos"));
		title.add(_cfCategorias);

		setTitle(title);

		_lista = new ListadoProcesosLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista, false);
		_lista.setFocus();
	}

	public void setCategorias(Object[] choices) {
		_cfCategorias.setChoices(choices);
		invalidate();
	}

	public void setSelectedCategoria(Object object) {
		_cfCategorias.setSelectedIndex(object);
		invalidate();
	}

	public Object getSelectedCategoria() {
		return _cfCategorias.getChoice(_cfCategorias.getSelectedIndex());
	}
	
	private FocusChangeListener listener = new FocusChangeListener() {
		
		public void focusChanged(Field field, int eventType) {
			Object selected = _cfCategorias.getChoice(_cfCategorias
					.getSelectedIndex());
			if (String.class.isInstance(selected)) {
				_lista.setText("");
			} else {
				_lista.setText(selected.toString());
			}
			_lista.updateList();
		}
	};
}