package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.MainScreen;
import core.Actuacion;
import core.CampoPersonalizado;
import core.Juzgado;
import core.Persona;
import core.Proceso;

public class VerProceso extends MainScreen {

	EditableTextField _txtDemandante;
	EditableTextField _txtDemandado;
	DateField _dfFecha;
	EditableTextField _txtJuzgado;
	EditableTextField _txtRadicado;
	EditableTextField _txtRadicadoUnico;
	ObjectChoiceField _ofActuaciones;
	EditableTextField _txtEstado;
	EditableTextField _txtCategoria;
	EditableTextField _txtTipo;
	EditableTextField _txtNotas;
	NumericChoiceField _nfPrioridad;
	
	Proceso _proceso;
	Persona _demandante;
	Persona _demandado;
	Juzgado _juzgado;
	Vector _camposPersonalizados;
	Vector _txtCampos;
	Vector _actuaciones;
	
	public VerProceso(Proceso proceso) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Ver proceso");
		_proceso = proceso;
		_demandante = proceso.getDemandante();
		_demandado = proceso.getDemandado();
		_juzgado = proceso.getJuzgado();
		_camposPersonalizados = proceso.getCampos();
		_actuaciones = proceso.getActuaciones();

		_txtDemandante = new EditableTextField("Demandante: ", _demandante.getNombre());
		add(_txtDemandante);
		
		_txtDemandado = new EditableTextField("Demandado: ", _demandado.getNombre());
		add(_txtDemandado);
		
		_txtJuzgado = new EditableTextField("Juzgado: ", _juzgado.getNombre());
		add(_txtJuzgado);
		
		_dfFecha = new DateField("Fecha: ", _proceso.getFecha().getTime().getTime(), DateField.DATE);
		_dfFecha.setEditable(false);
		add(_dfFecha);
		
		_txtRadicado = new EditableTextField("Radicado: ", _proceso.getRadicado());
		add(_txtRadicado);
		
		_txtRadicadoUnico = new EditableTextField("Radicado unico: ", _proceso.getRadicadoUnico());
		add(_txtRadicadoUnico);
		
		_ofActuaciones = new ObjectChoiceField("Actuaciones: ", transformActuaciones());	
		add(_ofActuaciones);
		
		_txtEstado = new EditableTextField("Estado: ", _proceso.getEstado());
		add(_txtEstado);
		
		_txtCategoria = new EditableTextField("Categría: ", _proceso.getCategoria());
		add(_txtCategoria);
		
		_txtTipo = new EditableTextField("Tipo: ", _proceso.getTipo());
		add(_txtTipo);
		
		_txtNotas = new EditableTextField("Notas: ", _proceso.getNotas());
		add(_txtNotas);
		
		_nfPrioridad = new NumericChoiceField("Prioridad: ", 0, 10, 1);
		_nfPrioridad.setSelectedValue(_proceso.getPrioridad());
		add(_nfPrioridad);
		
		_txtCampos = new Vector();
		
		addCampos();
		addMenuItem(menuEditar);
	}
	
	private Object[] transformActuaciones() {
		Enumeration e = _actuaciones.elements();
		Object[] elements = new Object[_actuaciones.size()];
		for(int i = 0; i < elements.length; i++) {
			elements[i] = ((Actuacion)e.nextElement());
		}
		return elements;
	}
	
	private void addCampos() {
		Enumeration e = _camposPersonalizados.elements();
		
		while(e.hasMoreElements()) {
			CampoPersonalizado c = (CampoPersonalizado)e.nextElement();
			EditableTextField etf = new EditableTextField(c.getNombre()+": ", c.getValor());
			etf.setCookie(c);
			_txtCampos.addElement(etf);
			add(etf);
		}
	}
	
	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if(f.equals(_txtDemandante)) {
				VerPersonaController verPersona = new VerPersonaController(_demandante);
				UiApplication.getUiApplication().pushModalScreen(verPersona.getScreen());
				verPersona.actualizarPersona();
				_demandante = verPersona.getPersona();
				_txtDemandante.setText(_demandante.getNombre());
				_txtDemandante.setFocus();
			}
			if(f.equals(_txtDemandado)) {
				VerPersonaController verPersona = new VerPersonaController(_demandado);
				UiApplication.getUiApplication().pushModalScreen(verPersona.getScreen());
				verPersona.actualizarPersona();
				_demandado = verPersona.getPersona();
				_txtDemandado.setText(_demandado.getNombre());
				_txtDemandado.setFocus();
			}
			
			if(f.equals(_txtJuzgado)) {
				VerJuzgadoController verJuzgado = new VerJuzgadoController(_juzgado);
				UiApplication.getUiApplication().pushModalScreen(verJuzgado.getScreen());
				verJuzgado.actualizarJuzgado();
				_juzgado = verJuzgado.getJuzgado();
				_txtJuzgado.setText(_juzgado.getNombre());
				_txtJuzgado.setFocus();
			}
			
			if(f.equals(_dfFecha)) {
				_dfFecha.setEditable(true);
				_dfFecha.setFocus();
			}
			
			if(f.equals(_txtRadicado)) {
				_txtRadicado.setEditable();
				_txtRadicado.setFocus();
			}
			
			if(f.equals(_txtRadicadoUnico)) {
				_txtRadicadoUnico.setEditable();
				_txtRadicadoUnico.setFocus();
			}
			
			if(f.equals(_ofActuaciones)) {
				Actuacion actuacion = (Actuacion)_ofActuaciones.getChoice(_ofActuaciones.getSelectedIndex());
				VerActuacionController verAtuacion = new VerActuacionController(actuacion);
				UiApplication.getUiApplication().pushModalScreen(verAtuacion.getScreen());
				verAtuacion.actualizarActuacion();
				_actuaciones.setElementAt(verAtuacion.getActuacion(),_actuaciones.indexOf(actuacion));
				_ofActuaciones.setChoices(transformActuaciones());
				_ofActuaciones.setFocus();
			}
		}
	};
}
