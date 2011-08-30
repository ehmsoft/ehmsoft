package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;
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
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.decor.BorderFactory;

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
	private int [] _significadoEstilos = {Font.PLAIN, Font.BOLD, Font.EXTRA_BOLD, Font.BOLD | Font.ITALIC, Font.ITALIC};
	String [] _listaEstilos = {"Normal", "Negrita", "Extra negrita", "Negrita cursiva", "Cursiva"};
	public PreferenciasGeneralesScreen() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_txtNombreUsuario = new BasicEditField("Nombre de Usuario: ", "");
		_chkMostrarCampoBusqueda = new CheckboxField("Mostrar Búsqueda en todas las pantallas", false);
		_chkMostrarTitulosPantalla = new CheckboxField("Mostrar los títulos de cada pantalla", false);
		//TODO inicializar el campo chfPantallaInicial, de él depende txtCantidadActuaciones
		_chkRecordarUltimaCategoria = new CheckboxField("Recordar última categoría", false);
		
		//Fuentes
		FontFamily[] listaFuentes = FontFamily.getFontFamilies();
		_chfTipoFuente = new ObjectChoiceField("Tipo de fuente: ", listaFuentes, getFont().getFontFamily());
		int[] _listaTamanos = listaFuentes[_chfTipoFuente.getSelectedIndex()].getHeights();
		Integer[] _listaTamanosInteger = new Integer[_listaTamanos.length];
		for(int i=0;i<_listaTamanos.length;i++){
			_listaTamanosInteger[i] = new Integer(_listaTamanos[i]);
		}
		_chfTamanoFuente = new ObjectChoiceField("Tamaño fuente: ", _listaTamanosInteger, new Integer(getFont().getHeight()));
		_chfEstiloFuente = new ObjectChoiceField("Estilo de fuente:", _listaEstilos);
		_txtEjemploFuente = new RichTextField("El veloz murciélago hindú comía feliz cardillo y kiwi. La cigüeña tocaba el saxofón detrás del palenque de paja.", Field.NON_FOCUSABLE | Field.USE_ALL_WIDTH);
		_txtEjemploFuente.setFont(listaFuentes[_chfTipoFuente.getSelectedIndex()].getFont(_significadoEstilos[_chfEstiloFuente.getSelectedIndex()], _listaTamanos[_chfTamanoFuente.getSelectedIndex()]));
		_txtEjemploFuente.setBorder(BorderFactory.createRoundedBorder(new XYEdges(5, 5, 5, 5)));
		
		_chfTipoFuente.setChangeListener(listenerTipoFuente);
		_chfTamanoFuente.setChangeListener(listenerActualizarFuente);
		_chfEstiloFuente.setChangeListener(listenerActualizarFuente);
		//Botones
		
		_lblCopiaSeguridad = new LabelField("Realizar copia de seguridad de base de datos:", LabelField.USE_ALL_WIDTH);
		_btnCopiaSeguridad = new ButtonField("Copia de seguridad", ButtonField.FIELD_RIGHT);
		_lblRestaurarPrefs = new LabelField("Restaurar preferencias predeterminadas", LabelField.USE_ALL_WIDTH);
		_btnRestaurarPrefs = new ButtonField("Restaurar Preferencias", ButtonField.FIELD_RIGHT);
		_btnCopiaSeguridad.setChangeListener(listenerCopiaSeguridad);
		_btnRestaurarPrefs.setChangeListener(listenerRestaurarPrefs);
		
		//Agregar componentes
		add(_txtNombreUsuario);
		add(_chkMostrarCampoBusqueda);
		add(_chkMostrarTitulosPantalla);
		add(_chkRecordarUltimaCategoria);
		add(new SeparatorField());
		add(_chfTipoFuente);
		add(_chfTamanoFuente);
		add(_chfEstiloFuente);
		add(_txtEjemploFuente);
		add(new SeparatorField());
		add(_lblCopiaSeguridad);
		add(_btnCopiaSeguridad);
		add(_lblRestaurarPrefs);
		add(_btnRestaurarPrefs);
	
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
	
	public void setRecordarUltimaCategoria(boolean value) {
		_chkRecordarUltimaCategoria.setChecked(value);
	}
	
	public void setFuente(Font font) {
		_chfTipoFuente.setSelectedIndex(font.getFontFamily());
		_chfEstiloFuente.setSelectedIndex(_significadoEstilos[font.getStyle()]);
		_chfTamanoFuente.setSelectedIndex(new Integer(font.getHeight()));
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
	
	public boolean isRecordarUltimaCategoria() {
		return _chkRecordarUltimaCategoria.getChecked();
	}
	
	public Font getFuente() {
		FontFamily fontFamily = (FontFamily) _chfTipoFuente.getChoice(_chfTipoFuente.getSelectedIndex());
		int style = _significadoEstilos[_chfEstiloFuente.getSelectedIndex()];
		int height = ((Integer) _chfTamanoFuente.getChoice(_chfTamanoFuente.getSelectedIndex())).intValue();
		Font font = fontFamily.getFont(style, height);
		return font;
	}
	
	private FieldChangeListener listenerPantallaInicial = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			// Listener Pantalla Inicial
			
		}
	};
	private FieldChangeListener listenerTipoFuente = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			FontFamily fontFamily = (FontFamily) _chfTipoFuente.getChoice(_chfTipoFuente.getSelectedIndex());
			int[] _listaTamanos = fontFamily.getHeights();
			Integer[] _listaTamanosInteger = new Integer[_listaTamanos.length];
			for(int i=0;i<_listaTamanos.length;i++){
				_listaTamanosInteger[i] = new Integer(_listaTamanos[i]);
			}
			int style = _significadoEstilos[_chfEstiloFuente.getSelectedIndex()];
			int height = ((Integer) _chfTamanoFuente.getChoice(_chfTamanoFuente.getSelectedIndex())).intValue();
			Font font = fontFamily.getFont(style, height);
			_chfTamanoFuente.setChoices(_listaTamanosInteger);
			_chfTamanoFuente.setSelectedIndex(new Integer(getFont().getHeight()));
			_txtEjemploFuente.setFont(font);
		}
	};
	private FieldChangeListener listenerActualizarFuente = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			FontFamily fontFamily = (FontFamily) _chfTipoFuente.getChoice(_chfTipoFuente.getSelectedIndex());
			int style = _significadoEstilos[_chfEstiloFuente.getSelectedIndex()];
			int height = ((Integer) _chfTamanoFuente.getChoice(_chfTamanoFuente.getSelectedIndex())).intValue();
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
			String[] choices = {"Sí", "No"};
			int eleccion = Dialog.ask("Está seguro que desea restaurar las preferencias por defecto?", choices, 1);
			if(eleccion == 0){
				// TODO Listener Preferencias por defecto
			}
			fieldChangeNotify(Util.RESTAURAR_PREFERENCIAS);
		}
	};
	
	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuGuardar);
	}
	
	private MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {
		
		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	protected boolean onSave(){
		fieldChangeNotify(Util.GUARDAR);
		return false;
	}
}
