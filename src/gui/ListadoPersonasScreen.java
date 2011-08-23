package gui;

import persistence.Persistence;

import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import core.Persona;
import core.PhoneManager;

public class ListadoPersonasScreen extends MainScreen {

	public static final int ON_CLICK_VER = 1;
	public static final int ON_CLICK_SELECT = 2;
	public static final int NO_NUEVO = 4;
	public static final int SEARCH = 8;

	private Object _selected;
	private ListadoPersonasLista _lista;
	private int _tipo;
	private long _style;

	public ListadoPersonasScreen(int tipo) {
		this(tipo, 0);
	}

	public ListadoPersonasScreen(int tipo, long style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_style = style;
		_tipo = tipo;
		_lista = new ListadoPersonasLista();
		_lista.setLabel("Buscar: ");

		if ((_style & NO_NUEVO) != NO_NUEVO) {
			if (tipo == 1)
				_lista.insert(0, "Nuevo demandante");
			else
				_lista.insert(0, "Nuevo demandado");
		}
		if ((_style & SEARCH) == SEARCH) {
			add(_lista.getKeywordField());
		}
		
		ListenerKey k = new ListenerKey() {
			public boolean keyDown(int keycode, int time) {
				if (keycode == 1114112) {
					menuLlamar.run();
					return true;
				} else {
					return false;
				}
			}
		};
		add(_lista);
		addKeyListener(k);
	}

	protected boolean navigationClick(int status, int time) {
		if (String.class.isInstance(_lista.getSelectedElement())) {
			onNew();
			return true;
		} else {
			onClick();
			return true;
		}
	}

	private void onNew() {
		NuevaPersona n = new NuevaPersona(_tipo);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Persona persona = n.getPersona();
		if (persona != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_lista.insert(0, persona);
				_lista.setSelectedIndex(0);
			} else {
				_lista.insert(1, persona);
				_lista.setSelectedIndex(1);
			}
		}
		n = null;
	}

	private void onClick() {
		if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			menuVer.run();
		} else {
			_selected = _lista.getSelectedElement();
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}

	protected void makeMenu(Menu menu, int instance) {
		if (!String.class.isInstance(_lista.getSelectedElement())) {
			menu.add(menuVer);
			menu.add(menuDelete);
			menu.add(menuLlamar);
			menu.add(menuEditarLlamar);
		}
	}
	
	private final MenuItem menuEditarLlamar = new MenuItem("Editar y llamar", 0, 0) {
		
		public void run() {
			Persona p = (Persona)_lista.getSelectedElement();
			EditarLlamar screen = new EditarLlamar(p.getTelefono());
			UiApplication.getUiApplication().pushModalScreen(screen);
			if(screen.getNumber() != null) {
				PhoneManager.call(screen.getNumber());
			}
		}
	};
	
	class EditarLlamar extends PopupScreen {
		
		private BasicEditField _txtNumero;
		
		public EditarLlamar(String numero) {
			super(new VerticalFieldManager());
			if(numero != null) {
				_txtNumero = new BasicEditField(BasicEditField.NO_NEWLINE);
				_txtNumero.setText(numero);
			} else {
				_txtNumero = new BasicEditField(BasicEditField.NO_NEWLINE);
				_txtNumero.setText("");
			}
			
			add(_txtNumero);
			ListenerKey k = new ListenerKey() {
				public boolean keyDown(int keycode, int time) {
					if (keycode == 1769472) {
						_txtNumero.setText("");
						UiApplication.getUiApplication().popScreen(getScreen());
						return true;
					} else if (keycode == 1114112) {
						UiApplication.getUiApplication().popScreen(getScreen());
						return true;
					} else {
						return false;
					}
				}
			};
			addKeyListener(k);
		}
		
		public String getNumber() {
			if(_txtNumero.getTextLength() == 0) {
				return null;
			} else {
				return _txtNumero.getText();
			}
		}
		
		protected boolean navigationClick(int status, int time) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		}
	}
	
	private final MenuItem menuLlamar = new MenuItem("Llamar", 0, 0) {
		
		public void run() {
			Persona p = (Persona)_lista.getSelectedElement();
			if(p.getTelefono().length() != 0) {
				PhoneManager.call(p.getTelefono());
			} else {
				Dialog.alert("No tiene número telefónico");
			}
		}
	};

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			Persona old = (Persona) _lista.getSelectedElement();
			VerPersona verPersona = new VerPersona(old);
			UiApplication.getUiApplication().pushModalScreen(
					verPersona.getScreen());
			Persona nw = verPersona.getPersona();
			_lista.update(old, nw);
			if (_lista.getKeywordField().getTextLength() != 0) {
				_lista.setText(nw.getNombre());
			}
			old = null;
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel;
			if (_tipo == 1) {
				sel = Dialog.ask("¿Desdea eliminar el demandante?", ask, 1);
			} else {
				sel = Dialog.ask("¿Desdea eliminar el demandado?", ask, 1);
			}
			if (sel == 0) {
				Persistence persistence = null;
				try {
					persistence = new Persistence();
					persistence.borrarPersona((Persona) _lista
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

	public void addPersona(Object persona) {
		_lista.insert(persona);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
	
	public class ListenerKey implements KeyListener {
		// Implement methods in the KeyListener interface for handling keyboard
		// events:
		public boolean keyChar(char key, int status, int time) {
			return false;
		}

		public boolean keyDown(int keycode, int time) {
			return false;
		}

		public boolean keyRepeat(int keycode, int time) {
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			return false;
		}

		public boolean keyUp(int keycode, int time) {
			return false;
		}
	}
}
