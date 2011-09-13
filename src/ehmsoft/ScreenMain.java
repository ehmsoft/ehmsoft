package ehmsoft;

import java.util.Vector;

import core.Actuacion;

import persistence.Persistence;
import gui.Util;
import gui.Listados.ListadoActuacionesLista;
import gui.Listados.ListadoCampos;
import gui.Listados.ListadoCategorias;
import gui.Listados.ListadoJuzgados;
import gui.Listados.ListadoPersonas;
import gui.Listados.ListadoProcesos;
import gui.Listados.ListadoPlantillas;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BorderFactory;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public class ScreenMain extends MainScreen {
	
	private VerticalFieldManager _vertical;
	private ListadoActuacionesLista _lista;
	private LabelField _juzgado;
	private LabelField _fecha;
	private LabelField _fechaProxima;
	private LabelField _descripcion;
	private GridFieldManager _grid;
	
	public ScreenMain() {
		super();
		
		actuacionesManager(15);
		
		_grid = new GridFieldManager(1, 2,	FIELD_HCENTER);
		_grid.setColumnProperty(0, GridFieldManager.FIXED_SIZE, (Display.getWidth() / 2) - 32);
		_grid.setColumnProperty(1, GridFieldManager.FIXED_SIZE, (Display.getWidth() / 2) + 16);

		_grid.setRowProperty(0, GridFieldManager.FIXED_SIZE, Display.getHeight() - 32);
		_grid.setPadding(16, 0, 0, 0);

		VerticalFieldManager right = new VerticalFieldManager();
		VerticalFieldManager left = new VerticalFieldManager(VERTICAL_SCROLL | VERTICAL_SCROLLBAR | FIELD_VCENTER);
		left.setBorder(BorderFactory.createRoundedBorder(new XYEdges(5, 5, 5, 5)));

		left.add(_lista);
		right.add(_vertical);

		_grid.add(left);
		_grid.add(right);

		add(_grid);
		left.setFocus();
		left.invalidate();
	}
	
	private void actuacionesManager(int cant) {
		_vertical = new VerticalFieldManager();
		_vertical.setFont(_vertical.getFont().derive(
				_vertical.getFont().getStyle(),
				_vertical.getFont().getHeight() - 5));
		_lista = new ListadoActuacionesLista();
		_lista.setFont(_lista.getFont().derive(_lista.getFont().getStyle(),
				_lista.getFont().getHeight() - 8));
		_lista.setRowHeight(_lista.getFont().getHeight() * 2);

		try {
			Vector v = new Persistence().consultarActuacionesCriticas(cant);
			_lista.loadFrom(v);
		} catch (NullPointerException e) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert(e.toString());
		}
		_lista.setFocusListener(listener);
		
		LabelField lblDescripcion = new LabelField("Descripción: ");
		LabelField lblJuzgado = new LabelField("Juzgado: ");
		LabelField lblFecha = new LabelField("Fecha: ");
		LabelField lblFechaProxima = new LabelField("Fecha próxima: ");
		
		Font bold = _vertical.getFont().derive(Font.BOLD);
		
		lblDescripcion.setFont(bold);
		lblJuzgado.setFont(bold);
		lblFecha.setFont(bold);
		lblFechaProxima.setFont(bold);


		_juzgado = new LabelField();
		_fecha = new LabelField();
		_fechaProxima = new LabelField();
		_descripcion = new LabelField();
		
		_vertical.add(lblDescripcion);
		_vertical.add(_descripcion);
		_vertical.add(new SeparatorField());
		_vertical.add(lblJuzgado);
		_vertical.add(_juzgado);
		_vertical.add(new SeparatorField());
		_vertical.add(lblFecha);
		_vertical.add(_fecha);
		_vertical.add(new SeparatorField());
		_vertical.add(lblFechaProxima);
		_vertical.add(_fechaProxima);
	}
	
	private FocusChangeListener listener = new FocusChangeListener() {

		public void focusChanged(Field field, int context) {
			try {
				Actuacion a = (Actuacion) _lista.getSelectedElement();
				if (a == null) {
					a = (Actuacion) _lista.getElementAt(0);
				}
				_descripcion.setText(a.getDescripcion());
				_juzgado.setText(a.getJuzgado().getNombre());
				String fecha = Util.calendarToString(a.getFecha(), true);
				_fecha.setText(fecha);
				fecha = Util.calendarToString(a.getFechaProxima(), true);
				_fechaProxima.setText(fecha);
				_lista.invalidate();
				_grid.invalidate();
				} catch (NullPointerException e) {
			}
		}
	};

	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuListas);
		menu.add(menuNuevos);
		menu.add(menuPreferencias);
	}
	
	private final MenuItem menuListas = new MenuItem("Listado",0, 0) {
		
		public void run() {
			UiApplication.getUiApplication().pushModalScreen(new Listados(Listados.LISTA));
		}
	};
	
	private final MenuItem menuNuevos = new MenuItem("Nuevo", 0, 0) {
		
		public void run() {
			UiApplication.getUiApplication().pushModalScreen(new Listados(Listados.NUEVO));
		}
	};
	
	private final MenuItem menuPreferencias = new MenuItem("Preferencias", 0, 0) {
		
		public void run() {
			Dialog.alert("En desarrollo en la otra rama");
		}
	};
}

class Listados extends PopupScreen {
	
	private ObjectListField _lista;
	private int _style;
	
	public static final short LISTA = 1;
	public static final short NUEVO = 2;
	
	public Listados(int style) {
		super(new VerticalFieldManager());
		_style = style;
		_lista = new ObjectListField();
		if((_style & LISTA) == LISTA) {
			add(new LabelField("Ver listado de:", FIELD_HCENTER));
			Object[] o = {"Demandantes", "Demandados", "Juzgados", "Campos personalizados", 
					"Categorías", "Procesos", "Plantillas"};
			_lista.set(o);
		} else if((_style & NUEVO) == NUEVO) {
			add(new LabelField("Crear:", FIELD_HCENTER));
			Object[] o = {"Demandante", "Demandado", "Juzgado", "Campos personalizado", 
					"Categoría", "Proceso", "Plantillas"};
			_lista.set(o);
		}
		add(new SeparatorField());
		add(_lista);
	}	
	
	protected boolean navigationClick(int arg0, int arg1) {
		int index = _lista.getSelectedIndex();
		UiApplication.getUiApplication().popScreen(this);
		if (index == 0) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoPersonas(1, false, ListadoPersonas.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevaPersona(1);
			}
		} else if (index == 1) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoPersonas(2, false, ListadoPersonas.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevaPersona(2);
			}
		} else if (index == 2) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoJuzgados(false, ListadoJuzgados.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevoJuzgado();
			}
		} else if (index == 3) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoCampos(false, ListadoCampos.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevoCampoPersonalizado();
			}
		} else if (index == 4) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoCategorias(false, ListadoCategorias.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevaCategoria(false);
			}
		} else if (index == 5) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoProcesos(false, ListadoProcesos.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevoProceso();
			}
		} else if (index == 6) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoPlantillas(false, ListadoPlantillas.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevaPlantilla();
			}
		}
		return true;
	}
	
	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(this);
		return true;
	}
}
