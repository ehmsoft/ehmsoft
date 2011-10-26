package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BorderFactory;
import core.Preferencias;

public class PreferenciasGeneralesScreen extends MainScreen {

	/**
	 * 
	 */
	private BasicEditField _txtNombreUsuario;
	private CheckboxField _chkMostrarCampoBusqueda;
	private CheckboxField _chkMostrarTitulosPantalla;
	private ObjectChoiceField _chfPantallaInicial;
	private BasicEditField _txtCantidadActuaciones;
	private CheckboxField _chkRecordarUltimaCategoria;
	private ObjectChoiceField _chfTipoFuente;
	private ObjectChoiceField _chfTamanoFuente;
	private ObjectChoiceField _chfEstiloFuente;
	private RichTextField _txtEjemploFuente;
	private LabelField _lblCopiaSeguridad;
	private ButtonField _btnCopiaSeguridad;
	private LabelField _lblRestaurarPrefs;
	private ButtonField _btnRestaurarPrefs;
	private HorizontalFieldManager _hfmCantidadActuaciones;
	private LabelField _lblLlaves;
	private ButtonField _btnLlaves;

	private int[] _significadoEstilos = { Font.PLAIN, Font.BOLD,
			Font.EXTRA_BOLD, Font.BOLD | Font.ITALIC, Font.ITALIC };
	private String[] _listaEstilos = { "Normal", "Negrita", "Extra negrita",
			"Negrita cursiva", "Cursiva" };
	private int[] _listaPantallas = {
			Preferencias.LISTADO_CAMPOS_MAIN,
			Preferencias.LISTADO_JUZGADOS_MAIN,
			// Preferencias.getLISTADO_PERSONAS_MAIN(),
			Preferencias.LISTADO_PROCESOS_MAIN,
			Preferencias.LISTADO_CATEGORIAS_MAIN,
			Preferencias.LISTADO_DEMANDANTES_MAIN,
			Preferencias.LISTADO_DEMANDADOS_MAIN,
			Preferencias.PANTALLA_INICIAL_MAIN };
	private String[] _nombresPantallas = { "Lista de Campos Personalizados",
			"Lista de Juzgados", "Lista de Procesos", "Lista de Categorías",
			"Lista de Demandantes", "Lista de Demandados", "Eventos próximos" };

	public PreferenciasGeneralesScreen() {
		super(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		setFont(Preferencias.getTipoFuente());
		_txtNombreUsuario = new BasicEditField("Nombre de Usuario: ", "");
		_chkMostrarCampoBusqueda = new CheckboxField(
				"Mostrar Búsqueda en todas las pantallas", false);
		_chkMostrarTitulosPantalla = new CheckboxField(
				"Mostrar los títulos de cada pantalla", false);
		// Pantalla Inicial
		_chfPantallaInicial = new ObjectChoiceField("Pantalla Inicial: ",
				_nombresPantallas);
		_chfPantallaInicial.setChangeListener(listenerPantallaInicial);
		_hfmCantidadActuaciones = new HorizontalFieldManager();
		_txtCantidadActuaciones = new BasicEditField("Cantidad eventos: ",
				"10", 3, BasicEditField.FILTER_INTEGER);
		_chkRecordarUltimaCategoria = new CheckboxField(
				"Recordar última categoría", false);

		// Fuentes
		FontFamily[] listaFuentes = FontFamily.getFontFamilies();
		_chfTipoFuente = new ObjectChoiceField("Tipo de fuente: ",
				listaFuentes, getFont().getFontFamily());
		int[] _listaTamanos = listaFuentes[_chfTipoFuente.getSelectedIndex()]
				.getHeights();
		Integer[] _listaTamanosInteger = new Integer[_listaTamanos.length];
		for (int i = 0; i < _listaTamanos.length; i++) {
			_listaTamanosInteger[i] = new Integer(_listaTamanos[i]);
		}
		_chfTamanoFuente = new ObjectChoiceField("Tamaño fuente: ",
				_listaTamanosInteger, new Integer(getFont().getHeight()));
		_chfEstiloFuente = new ObjectChoiceField("Estilo de fuente:",
				_listaEstilos);
		_txtEjemploFuente = new RichTextField(
				"El veloz murciélago hindú comía feliz cardillo y kiwi. La cigüeña tocaba el saxofón detrás del palenque de paja.",
				Field.NON_FOCUSABLE | Field.USE_ALL_WIDTH);
		_txtEjemploFuente.setFont(listaFuentes[_chfTipoFuente
				.getSelectedIndex()].getFont(
				_significadoEstilos[_chfEstiloFuente.getSelectedIndex()],
				_listaTamanos[_chfTamanoFuente.getSelectedIndex()]));
		_txtEjemploFuente.setBorder(BorderFactory
				.createRoundedBorder(new XYEdges(5, 5, 5, 5)));

		_chfTipoFuente.setChangeListener(listenerTipoFuente);
		_chfTamanoFuente.setChangeListener(listenerActualizarFuente);
		_chfEstiloFuente.setChangeListener(listenerActualizarFuente);
		// Copia de Seguridad

		_lblCopiaSeguridad = new LabelField(
				"Realizar copia de seguridad de base de datos:",
				Field.USE_ALL_WIDTH);
		_btnCopiaSeguridad = new ButtonField("Copia de seguridad",
				ButtonField.CONSUME_CLICK | Field.FIELD_RIGHT);
		// Restaurar Preferencias
		_lblRestaurarPrefs = new LabelField(
				"Restaurar preferencias predeterminadas", Field.USE_ALL_WIDTH);
		_btnRestaurarPrefs = new ButtonField("Restaurar Preferencias",
				ButtonField.CONSUME_CLICK | Field.FIELD_RIGHT);
		_btnCopiaSeguridad.setChangeListener(listenerCopiaSeguridad);
		_btnRestaurarPrefs.setChangeListener(listenerRestaurarPrefs);
		// Llaves
		_lblLlaves = new LabelField("Activación", Field.USE_ALL_WIDTH);
		_btnLlaves = new ButtonField("Cambiar Llave de activación",
				ButtonField.CONSUME_CLICK | Field.FIELD_RIGHT);
		_btnLlaves.setChangeListener(listenerLlaves);
		// Agregar componentes
		add(_txtNombreUsuario);
		add(_chkMostrarCampoBusqueda);
		add(_chkMostrarTitulosPantalla);
		add(_chkRecordarUltimaCategoria);
		// add(new SeparatorField());
		// add(_chfPantallaInicial);
		add(_hfmCantidadActuaciones);
		add(new SeparatorField());
		add(_chfTipoFuente);
		add(_chfTamanoFuente);
		add(_chfEstiloFuente);
		add(_txtEjemploFuente);

		add(_lblCopiaSeguridad);
		add(_btnCopiaSeguridad);
		add(new SeparatorField());
		add(_lblRestaurarPrefs);
		add(_btnRestaurarPrefs);
		add(new SeparatorField());
		add(_lblLlaves);
		add(_btnLlaves);
	}

	public void setNombreUsuario(String text) {
		_txtNombreUsuario.setText(text);
	}

	public void setMostrarBusqueda(boolean value) {
		_chkMostrarCampoBusqueda.setChecked(value);
	}

	public void setMostrarTitulos(boolean value) {
		_chkMostrarTitulosPantalla.setChecked(value);
	}

	public void setPantallaInicial(int pantallaInicial) {
		int index = 0;
		for (int i = 0; i < _listaPantallas.length; i++) {
			if (pantallaInicial == _listaPantallas[i]) {
				index = i;
			}
		}
		_chfPantallaInicial.setSelectedIndex(index);
	}

	public void setRecordarUltimaCategoria(boolean value) {
		_chkRecordarUltimaCategoria.setChecked(value);
	}

	public void setFuente(Font font) {
		_chfTipoFuente.setSelectedIndex(font.getFontFamily());
		_chfEstiloFuente.setSelectedIndex(_significadoEstilos[font.getStyle()]);
		_chfTamanoFuente.setSelectedIndex(new Integer(font.getHeight()));
	}

	public void setCantidadActuacionesCriticas(int cant) {
		_txtCantidadActuaciones.setText(Integer.toString(cant));
	}

	public String getNombreUsuario() {
		return _txtNombreUsuario.getText();
	}

	public boolean isMostrarBusqueda() {
		return _chkMostrarCampoBusqueda.getChecked();
	}

	public boolean isMostrarTitulos() {
		return _chkMostrarTitulosPantalla.getChecked();
	}

	public int getPantallaInicial() {
		return _listaPantallas[_chfPantallaInicial.getSelectedIndex()];
	}

	public boolean isRecordarUltimaCategoria() {
		return _chkRecordarUltimaCategoria.getChecked();
	}

	public Font getFuente() {
		FontFamily fontFamily = (FontFamily) _chfTipoFuente
				.getChoice(_chfTipoFuente.getSelectedIndex());
		int style = _significadoEstilos[_chfEstiloFuente.getSelectedIndex()];
		int height = ((Integer) _chfTamanoFuente.getChoice(_chfTamanoFuente
				.getSelectedIndex())).intValue();
		Font font = fontFamily.getFont(style, height);
		return font;
	}

	public int getCantidadActuacionesCriticas() {
		return Integer.parseInt(_txtCantidadActuaciones.getText());
	}

	private FieldChangeListener listenerPantallaInicial = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			if (_listaPantallas[_chfPantallaInicial.getSelectedIndex()] == Preferencias
					.getPANTALLA_INICIAL_MAIN()) {
				_hfmCantidadActuaciones.add(_txtCantidadActuaciones);
			} else {
				try {
					_hfmCantidadActuaciones.delete(_txtCantidadActuaciones);
				} catch (IllegalArgumentException e) {

				}
			}
		}
	};
	private FieldChangeListener listenerTipoFuente = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			FontFamily fontFamily = (FontFamily) _chfTipoFuente
					.getChoice(_chfTipoFuente.getSelectedIndex());
			int[] _listaTamanos = fontFamily.getHeights();
			Integer[] _listaTamanosInteger = new Integer[_listaTamanos.length];
			for (int i = 0; i < _listaTamanos.length; i++) {
				_listaTamanosInteger[i] = new Integer(_listaTamanos[i]);
			}
			int style = _significadoEstilos[_chfEstiloFuente.getSelectedIndex()];
			int height = ((Integer) _chfTamanoFuente.getChoice(_chfTamanoFuente
					.getSelectedIndex())).intValue();
			Font font = fontFamily.getFont(style, height);
			_chfTamanoFuente.setChoices(_listaTamanosInteger);
			_chfTamanoFuente
					.setSelectedIndex(new Integer(getFont().getHeight()));
			_txtEjemploFuente.setFont(font);
		}
	};
	private FieldChangeListener listenerActualizarFuente = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			FontFamily fontFamily = (FontFamily) _chfTipoFuente
					.getChoice(_chfTipoFuente.getSelectedIndex());
			int style = _significadoEstilos[_chfEstiloFuente.getSelectedIndex()];
			int height = ((Integer) _chfTamanoFuente.getChoice(_chfTamanoFuente
					.getSelectedIndex())).intValue();
			Font font = fontFamily.getFont(style, height);
			_txtEjemploFuente.setFont(font);
		}
	};
	private FieldChangeListener listenerCopiaSeguridad = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(Util.COPIA_SEGURIDAD);
		}
	};
	private FieldChangeListener listenerRestaurarPrefs = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			String[] choices = { "Sí", "No" };
			int eleccion = Dialog
					.ask("Está seguro que desea restaurar las preferencias por defecto?",
							choices, 1);
			if (eleccion == 0) {
				fieldChangeNotify(Util.RESTAURAR_PREFERENCIAS);
			}

		}
	};

	private FieldChangeListener listenerLlaves = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(Util.LLAVES);
		}
	};

	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuGuardar);
		menu.add(menuCancelar);
		menu.add(menuCerrar);
	}

	private MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};
	private MenuItem menuCancelar = new MenuItem("Cancelar", 1, 1) {

		public void run() {
			if (Dialog.ask(Dialog.D_YES_NO,
					"Seguro que desea salir y descartar cambios?") == Dialog.YES) {
				Util.popScreen(getScreen());
			}
		}
	};
	private MenuItem menuCerrar = new MenuItem("Salir de Aplicación",
			1000000000, 3) {

		public void run() {
			if (onSavePrompt()) {
				System.exit(0);
			}
		}
	};

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	protected boolean onSave() {
		fieldChangeNotify(Util.GUARDAR);
		return true;
	}
}
