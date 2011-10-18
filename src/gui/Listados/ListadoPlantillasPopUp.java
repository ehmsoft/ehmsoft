package gui.Listados;

import gui.ListaPopUp;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ChoiceField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class ListadoPlantillasPopUp extends ListaPopUp implements
		ListadoProcesosInterface {

	private ObjectChoiceField _cfCategorias;

	public ListadoPlantillasPopUp() {
		super();

		_cfCategorias = new ObjectChoiceField(null, null, 0,
				ChoiceField.FORCE_SINGLE_LINE) {
			public void setSelectedIndex(Object element) {
				fieldChangeNotify(0);
				super.setSelectedIndex(element);
			}
		};
		_cfCategorias.setChangeListener(listener);

		HorizontalFieldManager title = new HorizontalFieldManager(USE_ALL_WIDTH);
		title.add(new LabelField("Plantillas"));
		title.add(_cfCategorias);

		setTitle(title);

		_lista = new ListadoPlantillasLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista);
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

		public void fieldChanged(Field field, int context) {
			Object selected = _cfCategorias.getChoice(_cfCategorias
					.getSelectedIndex());
			if (selected.toString().equalsIgnoreCase("todas")) {
				_lista.setText("");
			} else {
				_lista.setText(selected.toString());
			}
			_lista.updateList();
			_lista.invalidate();
		}
	};
}
