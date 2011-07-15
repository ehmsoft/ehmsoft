package gui;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.MainScreen;
import core.Actuacion;
import core.CampoPersonalizado;
import core.Categoria;
import core.Juzgado;
import core.Persona;
import core.Proceso;

public class VerProcesoScreen extends MainScreen {

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
	Categoria _categoria;
	Vector _camposPersonalizados;
	Vector _txtCampos;
	Vector _actuaciones;
	
	private boolean _guardar = false;

	public VerProcesoScreen(Proceso proceso) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		
		setTitle("Ver proceso");
		_proceso = proceso;
		_demandante = proceso.getDemandante();
		_demandado = proceso.getDemandado();
		_juzgado = proceso.getJuzgado();
		_camposPersonalizados = proceso.getCampos();
		_actuaciones = proceso.getActuaciones();
		_categoria = proceso.getCategoria();

		_txtDemandante = new EditableTextField("Demandante: ",
				_demandante.getNombre());
		add(_txtDemandante);

		_txtDemandado = new EditableTextField("Demandado: ",
				_demandado.getNombre());
		add(_txtDemandado);

		_txtJuzgado = new EditableTextField("Juzgado: ", _juzgado.getNombre());
		add(_txtJuzgado);

		_dfFecha = new DateField("Fecha: ", _proceso.getFecha().getTime()
				.getTime(), DateField.DATE_TIME);
		_dfFecha.setEditable(false);
		add(_dfFecha);

		_txtRadicado = new EditableTextField("Radicado: ",
				_proceso.getRadicado());
		add(_txtRadicado);

		_txtRadicadoUnico = new EditableTextField("Radicado unico: ",
				_proceso.getRadicadoUnico());
		add(_txtRadicadoUnico);

		_ofActuaciones = new ObjectChoiceField("Actuaciones: ",
				transformActuaciones());
		add(_ofActuaciones);

		_txtEstado = new EditableTextField("Estado: ", _proceso.getEstado());
		add(_txtEstado);

		_txtCategoria = new EditableTextField("Categoría: ",
				_proceso.getCategoria().getDescripcion());
		add(_txtCategoria);

		_txtTipo = new EditableTextField("Tipo: ", _proceso.getTipo());
		add(_txtTipo);

		_txtNotas = new EditableTextField("Notas: ", _proceso.getNotas());
		add(_txtNotas);

		_nfPrioridad = new NumericChoiceField("Prioridad: ", 0, 10, 1);
		_nfPrioridad.setSelectedValue(_proceso.getPrioridad());
		_nfPrioridad.setEditable(false);
		add(_nfPrioridad);

		_txtCampos = new Vector();

		addCampos();
		addMenuItem(menuGuardar);
		addMenuItem(menuEditar);
		addMenuItem(menuEditarTodo);
		addMenuItem(menuAddActuacion);
	}

	private Object[] transformActuaciones() {
		Enumeration e = _actuaciones.elements();
		Object[] elements = new Object[_actuaciones.size()];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = ((Actuacion) e.nextElement());
		}
		return elements;
	}

	private void addCampos() {
		Enumeration e = _camposPersonalizados.elements();

		while (e.hasMoreElements()) {
			CampoPersonalizado c = (CampoPersonalizado) e.nextElement();
			EditableTextField etf = new EditableTextField(c.getNombre() + ": ",
					c.getValor());
			etf.setCookie(c);
			_txtCampos.addElement(etf);
			add(etf);
		}
	}
	
	private final MenuItem menuGuardar = new MenuItem("Guardar",0,0) {
		
		public void run() {
			_guardar = true;
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtDemandante)) {
				VerPersona verPersona = new VerPersona(
						_demandante);
				UiApplication.getUiApplication().pushModalScreen(
						verPersona.getScreen());
				verPersona.actualizarPersona();
				_demandante = verPersona.getPersona();
				_txtDemandante.setText(_demandante.getNombre());
				_txtDemandante.setFocus();
			}
			if (f.equals(_txtDemandado)) {
				VerPersona verPersona = new VerPersona(
						_demandado);
				UiApplication.getUiApplication().pushModalScreen(
						verPersona.getScreen());
				verPersona.actualizarPersona();
				_demandado = verPersona.getPersona();
				_txtDemandado.setText(_demandado.getNombre());
				_txtDemandado.setFocus();
			}

			if (f.equals(_txtJuzgado)) {
				VerJuzgado verJuzgado = new VerJuzgado(
						_juzgado);
				UiApplication.getUiApplication().pushModalScreen(
						verJuzgado.getScreen());
				verJuzgado.actualizarJuzgado();
				_juzgado = verJuzgado.getJuzgado();
				_txtJuzgado.setText(_juzgado.getNombre());
				_txtJuzgado.setFocus();
			}

			if (f.equals(_dfFecha)) {
				_dfFecha.setEditable(true);
				_dfFecha.setFocus();
			}

			if (f.equals(_txtRadicado)) {
				_txtRadicado.setEditable();
				_txtRadicado.setFocus();
			}

			if (f.equals(_txtRadicadoUnico)) {
				_txtRadicadoUnico.setEditable();
				_txtRadicadoUnico.setFocus();
			}

			if (f.equals(_ofActuaciones)) {
				Actuacion actuacion = (Actuacion) _ofActuaciones
						.getChoice(_ofActuaciones.getSelectedIndex());
				VerActuacion verAtuacion = new VerActuacion(
						actuacion);
				UiApplication.getUiApplication().pushModalScreen(
						verAtuacion.getScreen());
				try {
					verAtuacion.actualizarActuacion();
				} catch (Exception e) {
					_actuaciones.removeElement(actuacion);
					_ofActuaciones.setChoices(transformActuaciones());
				}
				_actuaciones.setElementAt(verAtuacion.getActuacion(),
						_actuaciones.indexOf(actuacion));
				_ofActuaciones.setChoices(transformActuaciones());
				_ofActuaciones.setFocus();
			}

			if (f.equals(_txtEstado)) {
				_txtEstado.setEditable();
				_txtEstado.setFocus();
			}

			if (f.equals(_txtCategoria)) {
				_txtCategoria.setEditable();
				_txtCategoria.setFocus();
			}

			if (f.equals(_txtTipo)) {
				_txtTipo.setEditable();
				_txtTipo.setFocus();
			}

			if (f.equals(_txtNotas)) {
				_txtNotas.setEditable();
				_txtNotas.setFocus();
			}

			if (f.equals(_nfPrioridad)) {
				_nfPrioridad.setEditable(true);
				_nfPrioridad.setFocus();
			}
			try {
				if (CampoPersonalizado.class.isInstance(f.getCookie())) {
					VerCampoPersonalizado verCampo = new VerCampoPersonalizado(
							(CampoPersonalizado) f.getCookie());
					UiApplication.getUiApplication().pushModalScreen(
							verCampo.getScreen());
					verCampo.actualizarCampoPersonalizado();
					((EditableTextField) f).setText(verCampo.getCampo()
							.getValor());
					((EditableTextField) f).setLabel(verCampo.getCampo()
							.getNombre() + ": ");
					((EditableTextField) f).setCookie(verCampo.getCampo());
				}
			} catch (Exception e) {
				Dialog.alert("Edit Campo -> " + e.toString());
			}
		}
	};

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 0, 0) {

		public void run() {
			_txtDemandante.setEditable();
			_txtDemandado.setEditable();
			_dfFecha.setEditable(true);
			_txtJuzgado.setEditable();
			_txtRadicado.setEditable();
			_txtRadicadoUnico.setEditable();
			_ofActuaciones.setEditable(true);
			_txtEstado.setEditable();
			_txtCategoria.setEditable();
			_txtTipo.setEditable();
			_txtNotas.setEditable();
			_nfPrioridad.setEditable(true);
		}
	};
	
	private final MenuItem menuAddActuacion = new MenuItem("Agregar actuación", 0, 0) {

		public void run() {
			NuevaActuacion n = new NuevaActuacion();
			UiApplication.getUiApplication().pushModalScreen(n.getScreen());
			try {
				_actuaciones.addElement(n.getActuacion());
			} catch (Exception e) {
			}
			_ofActuaciones.setChoices(transformActuaciones());
		}
	};
	
	public Persona getDemandante() {
		return _demandante;
	}

	public Persona getDemandado() {
		return _demandado;
	}

	public Calendar getFecha() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(_dfFecha.getDate()));
		return calendar;
	}

	public Juzgado getJuzgado() {
		return _juzgado;
	}

	public String getRadicado() {
		return _txtRadicado.getText();
	}

	public String getRadicadoUnico() {
		return _txtRadicadoUnico.getText();
	}

	public Vector getActuaciones() {
		return _actuaciones;
	}

	public String getEstado() {
		return _txtEstado.getText();
	}

	public Categoria getCategoria() {
		return _categoria;
	}

	public String getTipo() {
		return _txtTipo.getText();
	}

	public String getNotas() {
		return _txtNotas.getText();
	}

	public int getPrioridad() {
		return _nfPrioridad.getSelectedValue();
	}

	public Proceso getProceso() {
		return _proceso;
	}

	public Vector getCampos() {
		return _camposPersonalizados;
	}
	
	public boolean isGuardado() {
		return _guardar;
	}
	
	public boolean onClose() {
		boolean cambio = false;
		
		Calendar f1 = _proceso.getFecha();
		Calendar f2 = this.getFecha();

		if (!_proceso.getDemandante().getId_persona()
				.equals(this.getDemandante().getId_persona()))
			cambio = true;
		if (!_proceso.getDemandado().getId_persona()
				.equals(this.getDemandado().getId_persona()))
			cambio = true;
		if (!_proceso.getJuzgado().getId_juzgado()
				.equals(this.getJuzgado().getId_juzgado()))
			cambio = true;
		if ((f1.get(Calendar.YEAR) != f2.get(Calendar.YEAR))
				|| (f1.get(Calendar.MONTH) != f2.get(Calendar.MONTH))
				|| (f1.get(Calendar.DAY_OF_MONTH) != f2
						.get(Calendar.DAY_OF_MONTH)))
			cambio = true;
		if (!_proceso.getRadicado().equals(this.getRadicado()))
			cambio = true;
		if (!_proceso.getRadicadoUnico().equals(this.getRadicadoUnico()))
			cambio = true;
		if (!_proceso.getActuaciones().equals(this.getActuaciones()))
			cambio = true;
		if (!_proceso.getEstado().equals(this.getEstado()))
			cambio = true;
		if (!_proceso.getCategoria().equals(this.getCategoria()))
			cambio = true;
		if (!_proceso.getTipo().equals(this.getTipo()))
			cambio = true;
		if (!_proceso.getNotas().equals(this.getNotas()))
			cambio = true;
		if (_proceso.getPrioridad() != this.getPrioridad())
			cambio = true;
		if (!_proceso.getCampos().equals(this.getCampos()))
			cambio = true;
		
		if(!cambio) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		} else {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = Dialog.ask("Se han detectado cambios", ask, 1);
			if (sel == 0) {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else {
				return false;
			}
		}
	}
}
