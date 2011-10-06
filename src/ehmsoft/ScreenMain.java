package ehmsoft;

import java.util.Enumeration;
import java.util.Vector;

import persistence.ConnectionManager;
import persistence.Persistence;
import core.Actuacion;
import core.Plantilla;
import core.ActuacionCritica;
import core.Preferencias;
import core.Proceso;
import gui.Llaves;
import gui.PreferenciasGenerales;
import gui.Util;
import gui.About;
import gui.Listados.ListadoActuaciones;
import gui.Listados.ListadoCampos;
import gui.Listados.ListadoCategorias;
import gui.Listados.ListadoJuzgados;
import gui.Listados.ListadoPersonas;
import gui.Listados.ListadoProcesos;
import gui.Listados.ListadoPlantillas;
import gui.Nuevos.NuevaActuacion;
import gui.Nuevos.NuevoProceso;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.TransitionContext;
import net.rim.device.api.ui.Ui;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.UiEngine;
import net.rim.device.api.ui.UiEngineInstance;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

public class ScreenMain extends MainScreen {

	private GridFieldManager _grid;
	private VerticalFieldManager _info;
	private ObjectListField _lista;
	private LabelField _juzgado;
	private LabelField _fecha;
	private LabelField _fechaProxima;
	private LabelField _descripcion;
	private final int column1 = (Display.getWidth() / 2)
			- (int) (Display.getWidth() * (30.3 / 480));
	private final int column2 = (Display.getWidth() / 2)
			+ (int) (Display.getWidth() * (16.3 / 480));
	private final int row = Display.getHeight()
			- (int) (Display.getHeight() * (13.3 / 360));
	private final int row1 = Display.getHeight() / 2
			- (int) (Display.getWidth() * (32.3 / 480));
	private final int row2 = Display.getHeight() / 2;
	private final int column = Display.getWidth() - 7
			- (int) (Display.getWidth() * (32.3 / 360));

	public ScreenMain() {
		super(NO_VERTICAL_SCROLL);

		actuacionesManager();

		getMainManager().setBackground(
				BackgroundFactory.createLinearGradientBackground(Color.BLACK, Color.BLACK, Color.BLACK, Color.GRAY));

		if (Display.getOrientation() == Display.ORIENTATION_PORTRAIT) {
			_grid = new GridFieldManager(2, 1, GridFieldManager.FIXED_SIZE) {
				public Field getField(int index) {
					try {
						return super.getField(index);
					} catch (ArrayIndexOutOfBoundsException e) {
						return super.getField(0);
					}
				}
			};

			VerticalFieldManager top = new VerticalFieldManager(VERTICAL_SCROLL
					| VERTICAL_SCROLLBAR);
			top.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLACK, 100));
			VerticalFieldManager bottom = new VerticalFieldManager();

			top.setBorder(BorderFactory.createRoundedBorder(new XYEdges(5, 5,
					5, 5), Color.WHITE, Border.STYLE_SOLID));
			top.add(_lista);
			bottom.add(_info);

			_grid.add(top);
			_grid.add(bottom);

			add(_grid);
			top.setFocus();
			top.invalidate();
		} else if (Display.getOrientation() == Display.ORIENTATION_LANDSCAPE) {
			_grid = new GridFieldManager(1, 2, GridFieldManager.FIXED_SIZE);

			VerticalFieldManager right = new VerticalFieldManager(
					VERTICAL_SCROLL | VERTICAL_SCROLLBAR);
			VerticalFieldManager left = new VerticalFieldManager(
					VERTICAL_SCROLL | VERTICAL_SCROLLBAR);
			left.setBorder(BorderFactory.createRoundedBorder(new XYEdges(5, 5,
					5, 5), Color.WHITE, Border.STYLE_SOLID));
			left.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLACK, 175));

			left.add(_lista);
			right.add(_info);

			_grid.add(left);
			_grid.add(right);

			add(_grid);
			left.setFocus();
			left.invalidate();
		}

		final PopupScreen wait = new PopupScreen(new VerticalFieldManager());
		wait.add(new LabelField("Porfavor espere..."));
		wait.add(new LabelField(
				"Verificando la base de datos y cargando las preferencias, esto puede tomar unos minutos"));
		UiApplication.getUiApplication().pushGlobalScreen(wait, 0,
				UiEngine.GLOBAL_SHOW_LOWER);

		UiApplication.getUiApplication().invokeLater(new Runnable() {

			public void run() {
				try {
					new ConnectionManager().prepararBD();
					new Persistence().consultarPreferencias();
				} catch (NullPointerException e) {
					UiApplication.getUiApplication().popScreen(wait);
					Dialog.alert(Util.noSDString());
					System.exit(0);
				} catch (Exception e) {
					UiApplication.getUiApplication().popScreen(wait);
					Util.alert(e.toString());
				}
				UiApplication.getUiApplication().popScreen(wait);
				Llaves llaves = new Llaves();
				if (!llaves.verificarLlaves()) {
					UiApplication.getUiApplication().pushModalScreen(
							llaves.getScreen());
					if (!llaves.verificarLlaves()) {
						System.exit(0);
					}
				}
				cargarActuaciones();
			}
		});
	}

	private void cargarActuaciones() {
		Util.pushWaitScreen();
		UiApplication.getUiApplication().invokeLater(new Runnable() {

			public void run() {
				try {
					Vector v = new Persistence()
							.consultarActuacionesCriticas(Preferencias
									.getCantidadActuacionesCriticas());
					Enumeration e = v.elements();
					while (_lista.getSize() != 0) {
						_lista.delete(0);
					}
					while (e.hasMoreElements()) {
						_lista.insert(_lista.getSize(), e.nextElement());

					}
				} catch (NullPointerException e) {
					Util.noSd();
				} catch (Exception e) {
					Util.alert(e.toString());
				}
				_lista.focusChangeNotify(0);
				Util.popWaitScreen();
				_grid.invalidate();
			}
		});
	}

	private void actuacionesManager() {
		initLista();
		initInfo();
	}

	private void initLista() {
		_lista = new ObjectListField() {
			public void drawListRow(ListField listField, Graphics graphics,
					int index, int y, int width) {
				Actuacion objeto = (Actuacion) this.get(listField, index);
				graphics.drawText(objeto.toString(), 0, y);
				graphics.drawText(
						Util.calendarToString(objeto.getFechaProxima(), false),
						(int) (Display.getWidth() * 13.3 / 480), y
								+ getFont().getHeight());
			};

			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		if (Display.getOrientation() == Display.ORIENTATION_LANDSCAPE) {
			_lista.setFont(_lista
					.getFont()
					.derive(_lista.getFont().getStyle(),
							_lista.getFont().getHeight()
									- (int) ((float) Display.getHeight() * (8.3 / 360))));
		} else if (Display.getOrientation() == Display.ORIENTATION_PORTRAIT) {
			_lista.setFont(_lista
					.getFont()
					.derive(_lista.getFont().getStyle(),
							_lista.getFont().getHeight()
									- (int) ((float) Display.getHeight() * (5.3 / 480))));
		}
		_lista.setRowHeight(_lista.getFont().getHeight() * 2);

		_lista.setFocusListener(listener);
	}

	private void initInfo() {
		_info = new VerticalFieldManager();
		if (Display.getOrientation() == Display.ORIENTATION_LANDSCAPE) {
			_info.setFont(_info
					.getFont()
					.derive(_info.getFont().getStyle(),
							_info.getFont().getHeight()
									- (int) ((float) Display.getHeight() * (6.3 / 360))));
		} else if (Display.getOrientation() == Display.ORIENTATION_PORTRAIT) {
			_info.setFont(_info
					.getFont()
					.derive(_info.getFont().getStyle(),
							_info.getFont().getHeight()
									- (int) ((float) Display.getHeight() * (4.3 / 480))));
		}

		LabelField lblDescripcion = new LabelField("Descripci�n:", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		LabelField lblJuzgado = new LabelField("Juzgado:", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		LabelField lblFecha = new LabelField("Fecha:", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		LabelField lblFechaProxima = new LabelField("Fecha pr�xima:", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};

		Font bold = _info.getFont().derive(Font.BOLD);

		lblDescripcion.setFont(bold);
		lblJuzgado.setFont(bold);
		lblFecha.setFont(bold);
		lblFechaProxima.setFont(bold);

		_juzgado = new LabelField("", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		_fecha = new LabelField("", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		_fechaProxima = new LabelField("", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};
		_descripcion = new LabelField("", FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(Color.WHITE);
				super.paint(g);
			}
		};

		_info.add(lblDescripcion);
		_info.add(_descripcion);
		_info.add(new SeparatorField());
		_info.add(lblJuzgado);
		_info.add(_juzgado);
		_info.add(new SeparatorField());
		_info.add(lblFecha);
		_info.add(_fecha);
		_info.add(new SeparatorField());
		_info.add(lblFechaProxima);
		_info.add(_fechaProxima);
	}

	private FocusChangeListener listener = new FocusChangeListener() {

		public void focusChanged(Field field, int context) {
			try {
				if (Display.getOrientation() == Display.ORIENTATION_LANDSCAPE) {
					_grid.setColumnProperty(0, GridFieldManager.FIXED_SIZE,
							column1);
					_grid.setColumnProperty(1, GridFieldManager.FIXED_SIZE,
							column2);
					_grid.setRowProperty(0, GridFieldManager.FIXED_SIZE, row);

				} else if (Display.getOrientation() == Display.ORIENTATION_PORTRAIT) {
					_grid.setRowProperty(0, GridFieldManager.FIXED_SIZE, row1);
					_grid.setRowProperty(1, GridFieldManager.FIXED_SIZE, row2);
					_grid.setColumnProperty(0, GridFieldManager.FIXED_SIZE,
							column);
					_grid.setPadding(new XYEdges(
							(int) (Display.getWidth() * (16.3 / 360)), 0, 0,
							(int) (Display.getWidth() * (16.3 / 360))));
				}

				Actuacion a = (Actuacion) _lista.get(_lista,
						_lista.getSelectedIndex());
				if (a == null) {
					a = (Actuacion) _lista.get(_lista, 0);
				}
				_descripcion.setText(a.getDescripcion());
				_juzgado.setText(a.getJuzgado().getNombre());
				String fecha = Util.calendarToString(a.getFecha(), true);
				_fecha.setText(fecha);
				fecha = Util.calendarToString(a.getFechaProxima(), true);
				_fechaProxima.setText(fecha);
			} catch (Exception e) {
			}
		}
	};

	protected void makeMenu(Menu menu, int instance) {
		if (_lista.getSize() != 0) {
			menu.add(menuVerProceso);
			menu.add(menuVerActuacion);
		}
		menu.add(menuListas);
		menu.add(menuNuevos);
		menu.add(menuPreferencias);
		menu.add(menuCerrar);
		menu.add(menuAcerca);
	}

	private final MenuItem menuVerProceso = new MenuItem("Ver proceso", 65537,
			0) {

		public void run() {
			ActuacionCritica a = (ActuacionCritica) _lista.get(_lista,
					_lista.getSelectedIndex());
			Proceso p = null;
			try {
				p = new Persistence().consultarProceso(a.getId_proceso());
			} catch (NullPointerException e) {
				Util.noSd();
			} catch (Exception e) {
				Util.alert(e.toString());
			}
			if (p != null) {
				Util.verProceso(p);
				cargarActuaciones();
			}
		}
	};

	private final MenuItem menuVerActuacion = new MenuItem("Ver actuacion",
			65537, 1) {

		public void run() {
			ActuacionCritica a = (ActuacionCritica) _lista.get(_lista,
					_lista.getSelectedIndex());
			Util.verActuacion(a);
			cargarActuaciones();
		}
	};

	private final MenuItem menuListas = new MenuItem("Listado", 131075, 2) {

		public void run() {
			UiApplication.getUiApplication().pushModalScreen(
					new Listados(Listados.LISTA));
			cargarActuaciones();
		}
	};

	private final MenuItem menuNuevos = new MenuItem("Nuevo", 131075, 3) {

		public void run() {
			UiApplication.getUiApplication().pushModalScreen(
					new Listados(Listados.NUEVO));
			cargarActuaciones();
		}
	};

	private final MenuItem menuAcerca = new MenuItem("Acerca de", 327682, 5) {

		public void run() {
			TransitionContext transition = new TransitionContext(
					TransitionContext.TRANSITION_SLIDE);
			transition.setIntAttribute(TransitionContext.ATTR_DURATION, 500);
			transition.setIntAttribute(TransitionContext.ATTR_DIRECTION,
					TransitionContext.DIRECTION_DOWN);
			transition.setIntAttribute(TransitionContext.ATTR_STYLE,
					TransitionContext.STYLE_OVER);
			About nextScreen = new About();
			UiEngineInstance engine = Ui.getUiEngineInstance();
			engine.setTransition(null, nextScreen,
					UiEngineInstance.TRIGGER_PUSH, transition);
			Util.pushModalScreen(nextScreen);
			cargarActuaciones();
		}
	};

	private final MenuItem menuPreferencias = new MenuItem("Preferencias",
			262147, 4) {

		public void run() {
			PreferenciasGenerales p = new PreferenciasGenerales();
			Util.pushModalScreen(p.getScreen());
			cargarActuaciones();
		}
	};
	private MenuItem menuCerrar = new MenuItem("Salir de Aplicaci�n",
			1000000000, 6) {

		public void run() {
			System.exit(0);
		}
	};

	public boolean onClose() {
		System.exit(0);
		return true;
	}
}

class Listados extends PopupScreen {

	private ObjectListField _lista;
	private int _style;

	public static final short LISTA = 1;
	public static final short NUEVO = 2;

	public Listados(int style) {
		super(new VerticalFieldManager(VERTICAL_SCROLL | VERTICAL_SCROLLBAR));
		_style = style;
		_lista = new ObjectListField();
		if ((_style & LISTA) == LISTA) {
			add(new LabelField("Ver listado de:", FIELD_HCENTER));
			Object[] o = { "Demandantes", "Demandados", "Juzgados",
					"Campos personalizados", "Categor�as", "Procesos",
					"Plantillas", "Actuaciones" };
			_lista.set(o);
		} else if ((_style & NUEVO) == NUEVO) {
			add(new LabelField("Crear:", FIELD_HCENTER));
			Object[] o = { "Demandante", "Demandado", "Juzgado",
					"Campo personalizado", "Categor�a", "Proceso", "Plantilla",
					"Actuaci�n", "Proceso a partir de plantilla" };
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
		} else if (index == 7) {
			ListadoProcesos procesos = new ListadoProcesos(true,
					ListadoProcesos.NO_NUEVO);
			procesos.setTitle("Procesos ");
			Util.pushModalScreen(procesos.getScreen());
			Proceso proceso = procesos.getSelected();
			if (proceso != null) {
				if ((_style & LISTA) == LISTA) {
					ListadoActuaciones actuaciones = new ListadoActuaciones(
							proceso);
					Util.pushModalScreen(actuaciones.getScreen());
				} else if ((_style & NUEVO) == NUEVO) {
					NuevaActuacion actuacion = new NuevaActuacion(proceso);
					Util.pushModalScreen(actuacion.getScreen());
				}
			}
		} else if (index == 8) {
			ListadoPlantillas plantillas = new ListadoPlantillas(true,
					ListadoPlantillas.ON_CLICK_SELECT
							| ListadoPlantillas.NO_NUEVO);
			plantillas.setTitle("Seleccione una plantilla");
			Util.pushModalScreen(plantillas.getScreen());
			Plantilla plantilla = plantillas.getSelected();
			if (plantilla != null) {
				NuevoProceso n = new NuevoProceso(plantilla);
				UiApplication.getUiApplication().pushModalScreen(n.getScreen());
			}
		}
		return true;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(this);
		return true;
	}
}
