package gui.Listados;

import gui.ListaScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ChoiceField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class ListadoProcesosScreen extends ListaScreen implements
		ListadoProcesosInterface {

	private ObjectChoiceField _cfCategorias;

	public ListadoProcesosScreen() {
		super();

		_cfCategorias = new ObjectChoiceField() {
			public void setSelectedIndex(Object element) {
				fieldChangeNotify(0);
				super.setSelectedIndex(element);
			}
		};
		_cfCategorias.setChangeListener(listener);

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
	
	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int eventType) {
			if (eventType == ChoiceField.CONTEXT_CHANGE_OPTION) {
				Object selected = _cfCategorias.getChoice(_cfCategorias
						.getSelectedIndex());
				if (String.class.isInstance(selected)) {
					_lista.setKeyword("");
				} else {
					_lista.setKeyword(selected.toString());
				}
			}
		}
	};
}