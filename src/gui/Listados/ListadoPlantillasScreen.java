package gui.Listados;

import gui.ListaScreen;
import gui.Util;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.ChoiceField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;

public class ListadoPlantillasScreen extends ListaScreen implements
		ListadoProcesosInterface {

	private ObjectChoiceField _cfCategorias;

	public ListadoPlantillasScreen() {
		super();

		_cfCategorias = new ObjectChoiceField() {
			public void setSelectedIndex(Object element) {
				fieldChangeNotify(0);
				super.setSelectedIndex(element);
			}
		};
		_cfCategorias.setChangeListener(listener);
		_cfCategorias.setLabel("Procesos");

		setTitle(_cfCategorias);

		_lista = new ListadoPlantillasLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista, false);
		_lista.setFocus();
	}

	protected void makeMenu(Menu menu, int instance) {
		super.makeMenu(menu, instance);
		if (!String.class.isInstance(_lista.getSelectedElement())) {
			menu.add(menuCrearProceso);
		}
	}

	private MenuItem menuCrearProceso = new MenuItem("Crear proceso", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.NEW_PROCESO);
		}
	};

	public void setCategorias(Object[] choices) {
		_cfCategorias.setChoices(choices);
		_lista.updateList();
	}

	public void setSelectedCategoria(Object object) {
		_cfCategorias.setSelectedIndex(object);
		_lista.updateList();
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
					_lista.setText("");
				} else {
					_lista.setText("Cat"+selected.toString());
				}
			}
		}
	};
}