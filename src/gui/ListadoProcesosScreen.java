package gui;

import java.util.Vector;

import persistence.Persistence;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.MainScreen;
import core.Categoria;
import core.Proceso;

public class ListadoProcesosScreen extends MainScreen {

	public static final int ON_CLICK_VER = 1;
	public static final int ON_CLICK_SELECT = 2;
	public static final int NO_NUEVO = 4;
	public static final int SEARCH = 8;
	
	private Proceso _selected;
	private ListadoProcesosLista _lista;
	private long _style;
	private ObjectChoiceField _banner;
	
	public ListadoProcesosScreen() {
		this(0);
	}

	public ListadoProcesosScreen(long style ) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		
		_style = style;
		_banner = new ObjectChoiceField();
		_banner.setLabel("Procesos");
		Object[] o;
		try {
			Vector v = new Persistence().consultarCategorias();
			o = new Object[v.size() + 1];
			v.copyInto(o);
			o[v.size()] = "Todas";
			_banner.setChoices(o);
			_banner.setSelectedIndex("Todas");
			v = null;
		} catch(Exception e) {
			Dialog.alert(e.toString());
		}
		setTitle(_banner);
		
		_banner.setChangeListener(listenerBanner);

		_lista = new ListadoProcesosLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(_lista.getSelectedElement())) {
					onNew();
					return true;
				} else {
					onClick();
					return true;
				}
			}
		};
		_lista.setLabel("Buscar: ");
		
		if((_style & NO_NUEVO) != NO_NUEVO) {
			_lista.insert(0, "Nuevo proceso");
		}
		if ((_style & SEARCH) == SEARCH) {
			add(_lista.getKeywordField());
		}
		add(_lista);
	}
	
	FieldChangeListener listenerBanner = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			try {
				Object choice = _banner.getChoice(_banner
						.getSelectedIndex());
				if (String.class.isInstance(choice)) {
					_lista.setText("");
					_lista.updateList();
					_lista.setFocus();
				} else {
					_lista.setText(((Categoria) choice).getDescripcion()
							+ " ");
					_lista.updateList();
					_lista.setFocus();
				}
			} catch (Exception e) {
			}
		}
	};

	private void onNew() {
		NuevoProceso n = new NuevoProceso();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		try {
			if((_style & NO_NUEVO) == NO_NUEVO) {
				_lista.insert(0, n.getProceso());
				_lista.setSelectedIndex(0);
			} else {
				_lista.insert(1, n.getProceso());
				_lista.setSelectedIndex(1);
			}
		} catch (Exception e) {

		} finally {
			n = null;
		}
	}

	private void onClick() {
		if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			menuVer.run();
		} else {
			_selected = (Proceso)_lista.getSelectedElement();
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}

	protected void makeMenu(Menu menu, int instance) {
		if (!String.class.isInstance(_lista.getSelectedElement())) {
			menu.add(menuVer);
			menu.add(menuDelete);
		}
	}

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			Proceso old = (Proceso) _lista.getSelectedElement();
			VerProceso v = new VerProceso(old);
			UiApplication.getUiApplication().pushModalScreen(
					v.getScreen());
			v.actualizarProceso();
			Proceso nw = v.getProceso();
			_lista.update(old, nw);
			if (_lista.getKeywordField().getTextLength() != 0) {
				_lista.setText(nw.getId_proceso());
			}
			old = null;
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desdea eliminar el proceso?", ask, 1);

			if (sel == 0) {
				try {
					new Persistence().borrarProceso((Proceso) _lista
							.getSelectedElement());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}

				int index = _lista.getIndex(_lista
						.getSelectedElement());
				_lista.remove(index);
				_lista.setSelectedIndex(index);
			}
		}
	};

	public void addProceso(Proceso proceso) {
		_lista.insert(proceso);
	}

	public Proceso getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}