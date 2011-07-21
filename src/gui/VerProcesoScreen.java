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
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import core.Actuacion;
import core.CampoPersonalizado;
import core.Categoria;
import core.Juzgado;
import core.Persona;
import core.Proceso;

public class VerProcesoScreen extends FondoNormal {

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
	Vector _campos;
	Vector _valoresCamposViejos;
	Vector _valoresCamposNuevos;
	Vector _actuaciones;

	private boolean _guardar = false;
	private boolean _actCampo = false;

	public VerProcesoScreen(Proceso proceso) {

		setTitle("Ver proceso");
		_proceso = proceso;
		_demandante = proceso.getDemandante();
		_demandado = proceso.getDemandado();
		_juzgado = proceso.getJuzgado();
		_actuaciones = proceso.getActuaciones();
		_categoria = proceso.getCategoria();
		_campos = proceso.getCampos();

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

		_txtCategoria = new EditableTextField("Categoría: ", _proceso
				.getCategoria().getDescripcion());
		add(_txtCategoria);

		_txtTipo = new EditableTextField("Tipo: ", _proceso.getTipo());
		add(_txtTipo);

		_txtNotas = new EditableTextField("Notas: ", _proceso.getNotas());
		add(_txtNotas);

		_nfPrioridad = new NumericChoiceField("Prioridad: ", 0, 10, 1);
		_nfPrioridad.setSelectedValue(_proceso.getPrioridad());
		_nfPrioridad.setEditable(false);
		add(_nfPrioridad);

		_valoresCamposViejos = new Vector();
		_valoresCamposNuevos = new Vector();

		addCampos(proceso.getCampos());
	}

	public void addCampo(CampoPersonalizado campo)
			throws NullPointerException {
		Enumeration e = _valoresCamposViejos.elements();
		CampoPersonalizado temp;
		boolean is = false;
		while (e.hasMoreElements()) {
			temp = (CampoPersonalizado) (((EditableTextField) e.nextElement())
					.getCookie());
			if (temp.getId_atributo().equals(campo.getId_atributo())) {
				is = true;
			}
		}
		if (!is) {
			e = _valoresCamposNuevos.elements();
			while (e.hasMoreElements()) {
				temp = (CampoPersonalizado) (((EditableTextField) e
						.nextElement()).getCookie());
				if (temp.getId_atributo().equals(campo.getId_atributo())) {
					is = true;
				}
			}
		}
		if (!is) {
			EditableTextField campoP = new EditableTextField();
			campoP.setLabel(campo.getNombre() + ": ");
			campoP.setCookie(campo);
			if (campo.getLongitudMax() != 0)
				campoP.setMaxSize(campo.getLongitudMax());
			add(campoP);
			_valoresCamposNuevos.addElement(campoP);
			campoP.setFocus();
		} else {
			Dialog.alert("El campo ya existe en este proceso");
		}
	}

	private void addCampos(Vector campos) {
		Enumeration e = campos.elements();

		while (e.hasMoreElements()) {
			CampoPersonalizado c = (CampoPersonalizado) e.nextElement();
			EditableTextField etf = new EditableTextField(c.getNombre() + ": ",
					c.getValor());
			etf.setCookie(c);
			_valoresCamposViejos.addElement(etf);
			add(etf);
		}
	}

	public Vector getActuaciones() {
		return _actuaciones;
	}

	public Categoria getCategoria() {
		return _categoria;
	}

	public Persona getDemandado() {
		return _demandado;
	}

	public Persona getDemandante() {
		return _demandante;
	}

	public String getEstado() {
		return _txtEstado.getText();
	}

	public Calendar getFecha() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(_dfFecha.getDate()));
		return calendar;
	}

	public Juzgado getJuzgado() {
		return _juzgado;
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

	public String getRadicado() {
		return _txtRadicado.getText();
	}

	public String getRadicadoUnico() {
		return _txtRadicadoUnico.getText();
	}

	public String getTipo() {
		return _txtTipo.getText();
	}

	public Vector getValoresViejos() {
		return _valoresCamposViejos;
	}
	
	public Vector getValoresNuevos() {
		return _valoresCamposNuevos;
	}

	private boolean isCambiado() {
		boolean cambio = false;

		Calendar f1 = _proceso.getFecha();
		Calendar f2 = this.getFecha();

		if (_demandante != null) {
			if (!_proceso.getDemandante().getId_persona()
					.equals(this.getDemandante().getId_persona())) {
				cambio = true;
			}
		} else if (_demandante == null && _proceso.getDemandante() == null)
			;
		else if (_demandante == null) {
			cambio = true;
		}

		if (_demandado != null) {
			if (!_proceso.getDemandado().getId_persona()
					.equals(this.getDemandado().getId_persona())) {
				cambio = true;
			}
		} else if (_demandado == null && _proceso.getDemandado() == null)
			;
		else if (_demandado == null) {
			cambio = true;
		}

		if (_juzgado != null) {
			if (!_proceso.getJuzgado().getId_juzgado()
					.equals(this.getJuzgado().getId_juzgado())) {
				cambio = true;
			}
		} else if (_juzgado == null && _proceso.getJuzgado() == null)
			;
		else if (_juzgado == null) {
			cambio = true;
		}

		if ((f1.get(Calendar.YEAR) != f2.get(Calendar.YEAR))
				|| (f1.get(Calendar.MONTH) != f2.get(Calendar.MONTH))
				|| (f1.get(Calendar.DAY_OF_MONTH) != f2
						.get(Calendar.DAY_OF_MONTH))) {
			cambio = true;
		}
		if (!_proceso.getRadicado().equals(this.getRadicado())) {
			cambio = true;
		}
		if (!_proceso.getRadicadoUnico().equals(this.getRadicadoUnico())) {
			cambio = true;
		}
		if (!_proceso.getActuaciones().equals(this.getActuaciones())) {
			cambio = true;
		}
		if (!_proceso.getEstado().equals(this.getEstado())) {
			cambio = true;
		}
		if (!_proceso.getCategoria().equals(this.getCategoria())) {
			cambio = true;
		}
		if (!_proceso.getTipo().equals(this.getTipo())) {
			cambio = true;
		}
		if (!_proceso.getNotas().equals(this.getNotas())) {
			cambio = true;
		}
		if (_proceso.getPrioridad() != this.getPrioridad()) {
			cambio = true;
		}
		if(!_valoresCamposNuevos.isEmpty()) {
			cambio = true;
		}
		
		return cambio;
	}

	public boolean isGuardado() {
		return _guardar;
	}
	
	public boolean isCampoEditado() {
		return _actCampo;
	}

	protected void makeMenu(Menu menu, int instance) {
		Field focus = UiApplication.getUiApplication().getActiveScreen()
				.getFieldWithFocus();
		if(focus.equals(_txtCategoria)) {
			menu.add(menuCambiarCategoria);
			menu.addSeparator();
		}
		
		menu.add(menuAddCampo);
		
		if (focus.equals(_ofActuaciones)) {
			menu.add(menuAddActuacion);
			menu.addSeparator();
		}
		
		if (focus.equals(_txtDemandante)) {
			if (_demandante != null) {
				if (!_demandante.getId_persona().equals("1")) {
					menu.add(menuEditar);
				}
			}
		}

		else if (focus.equals(_txtDemandado)) {
			if (_demandado != null) {
				if (!_demandado.getId_persona().equals("1")) {
					menu.add(menuEditar);
				}
			}
		}

		else if (focus.equals(_txtDemandado)) {
			if (_juzgado != null) {
				if (!_juzgado.getId_juzgado().equals("1")) {
					menu.add(menuEditar);
				}
			}
		}
		
		else {
			menu.add(menuEditar);
		}

		menu.add(menuEditarTodo);
		if (!focus.equals(_ofActuaciones)) {
			menu.add(menuAddActuacion);
		}
		menu.addSeparator();
		if (focus.equals(_txtDemandante) || focus.equals(_txtDemandado)
				|| focus.equals(_txtJuzgado)) {
			menu.add(menuCambiar);
			menu.add(menuEliminar);
		} else if (focus.equals(_txtCategoria)) {
			menu.add(menuCambiarCategoria);
		}
		menu.add(menuGuardar);
	}
	
	public boolean onClose() {
		if (!isCambiado()) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		} else {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = Dialog.ask("Se han detectado cambios", ask, 2);
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

	private Object[] transformActuaciones() {
		Enumeration e = _actuaciones.elements();
		Object[] elements = new Object[_actuaciones.size()];
		for (int i = 0; i < elements.length; i++) {
			elements[i] = ((Actuacion) e.nextElement());
		}
		return elements;
	}
	
	private final MenuItem menuCambiarCategoria = new MenuItem("Cambiar", 0, 0) {

		public void run() {
			ListadoCategorias l = new ListadoCategorias();
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
			try {
				_categoria = l.getSelected();
				_txtCategoria.setText(_categoria.getDescripcion());
			} catch (NullPointerException e) {

			} catch (Exception e) {
				Dialog.alert(e.toString());
			} finally {
				l = null;
			}
		}
	};

	private final MenuItem menuAddCampo = new MenuItem(
			"Agregar campo personalizado", 0, 0) {

		public void run() {
			ListadoCampos l = new ListadoCampos(true);
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
			try {
				addCampo(l.getSelected());
			} catch (NullPointerException e) {
			} finally {
				l = null;
			}
		}
	};

	private final MenuItem menuEliminar = new MenuItem("Eliminar del proceso",
			0, 0) {

		public void run() {
			Object[] ask = { "Confirmar", "Cancelar" };

			Field f = UiApplication.getUiApplication().getActiveScreen()
					.getFieldWithFocus();
			if (f.equals(_txtDemandante)) {
				int sel = Dialog.ask("Se eliminará el demandante del proceso",
						ask, 1);
				if (sel == 0) {
					_demandante = null;
					_txtDemandante.setText("vacio");
				}
			} else if (f.equals(_txtDemandado)) {
				int sel = Dialog.ask("Se eliminará el demandado del proceso",
						ask, 1);
				if (sel == 0) {
					_demandado = null;
					_txtDemandado.setText("vacio");
				}
			} else if (f.equals(_txtJuzgado)) {
				int sel = Dialog.ask("Se eliminará el juzgado del proceso",
						ask, 1);
				if (sel == 0) {
					_juzgado = null;
					_txtJuzgado.setText("vacio");
				}
			}
		}
	};
	
	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			if (isCambiado() || isCampoEditado()) {
				Object[] ask = { "Guardar", "Descartar", "Cancelar" };
				int sel = Dialog.ask("¿Desea guardar los cambios realizados?",
						ask, 0);
				if (sel == 0) {
					_guardar = true;
					UiApplication.getUiApplication().popScreen(getScreen());
				} else if (sel == 1) {
					UiApplication.getUiApplication().popScreen(getScreen());
				}
			} else {
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		}
	};
	
	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtDemandante)) {
				VerPersona verPersona = new VerPersona(_demandante);
				UiApplication.getUiApplication().pushModalScreen(
						verPersona.getScreen());
				verPersona.actualizarPersona();
				_demandante = verPersona.getPersona();
				_txtDemandante.setText(_demandante.getNombre());
				_txtDemandante.setFocus();
			}
			else if (f.equals(_txtDemandado)) {
				VerPersona verPersona = new VerPersona(_demandado);
				UiApplication.getUiApplication().pushModalScreen(
						verPersona.getScreen());
				verPersona.actualizarPersona();
				_demandado = verPersona.getPersona();
				_txtDemandado.setText(_demandado.getNombre());
				_txtDemandado.setFocus();
			}

			else if (f.equals(_txtJuzgado)) {
				VerJuzgado verJuzgado = new VerJuzgado(_juzgado);
				UiApplication.getUiApplication().pushModalScreen(
						verJuzgado.getScreen());
				try {
					verJuzgado.actualizarJuzgado();
					_juzgado = verJuzgado.getJuzgado();
					_txtJuzgado.setText(_juzgado.getNombre());
				} catch (NullPointerException e) {
					_juzgado = null;
					_txtJuzgado.setText("vacio");
					_txtJuzgado.setFocus();
				}
			}

			else if (f.equals(_dfFecha)) {
				_dfFecha.setEditable(true);
				_dfFecha.setFocus();
			}

			else if (f.equals(_txtRadicado)) {
				_txtRadicado.setEditable();
				_txtRadicado.setFocus();
			}

			else if (f.equals(_txtRadicadoUnico)) {
				_txtRadicadoUnico.setEditable();
				_txtRadicadoUnico.setFocus();
			}

			else if (f.equals(_ofActuaciones)) {
				Actuacion actuacion = (Actuacion) _ofActuaciones
						.getChoice(_ofActuaciones.getSelectedIndex());
				VerActuacion verAtuacion = new VerActuacion(actuacion);
				UiApplication.getUiApplication().pushModalScreen(
						verAtuacion.getScreen());
				try {
					verAtuacion.actualizarActuacion();
					_actuaciones.setElementAt(verAtuacion.getActuacion(),
							_actuaciones.indexOf(actuacion));
					_ofActuaciones.setChoices(transformActuaciones());
					_ofActuaciones.setFocus();
				} catch (Exception e) {
					_actuaciones.removeElement(actuacion);
					_ofActuaciones.setChoices(transformActuaciones());
				}
			}

			else if (f.equals(_txtEstado)) {
				_txtEstado.setEditable();
				_txtEstado.setFocus();
			}

			else if (f.equals(_txtCategoria)) {
				VerCategoria v = new VerCategoria(_categoria);
				UiApplication.getUiApplication().pushModalScreen(v.getScreen());
				_categoria = v.getCategoria();
				_txtCategoria.setText(_categoria.getDescripcion());
				_txtCategoria.setFocus();
			}

			else if (f.equals(_txtTipo)) {
				_txtTipo.setEditable();
				_txtTipo.setFocus();
			}

			else if (f.equals(_txtNotas)) {
				_txtNotas.setEditable();
				_txtNotas.setFocus();
			}

			else if (f.equals(_nfPrioridad)) {
				_nfPrioridad.setEditable(true);
				_nfPrioridad.setFocus();
			}
			try {
				if (CampoPersonalizado.class.isInstance(f.getCookie())) {
					EditableTextField editable = (EditableTextField)f;
					editable.setEditable();
					_actCampo = true;
					
/*					VerCampo verCampo = new VerCampo(
							(CampoPersonalizado) f.getCookie());
					UiApplication.getUiApplication().pushModalScreen(
							verCampo.getScreen());
					verCampo.actualizarCampoPersonalizado();
					((EditableTextField) f).setText(verCampo.getCampo()
							.getValor());
					((EditableTextField) f).setLabel(verCampo.getCampo()
							.getNombre() + ": ");
					((EditableTextField) f).setCookie(verCampo.getCampo());*/
				}
			} catch (Exception e) {
				Dialog.alert("Edit Campo -> " + e.toString());
			}
		}
	};

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 0, 0) {

		public void run() {
			_dfFecha.setEditable(true);
			_txtRadicado.setEditable();
			_txtRadicadoUnico.setEditable();
			_ofActuaciones.setEditable(true);
			_txtEstado.setEditable();
			_txtTipo.setEditable();
			_txtNotas.setEditable();
			_nfPrioridad.setEditable(true);
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtDemandante)) {
				ListadoPersonas l = new ListadoPersonas(1);
				UiApplication.getUiApplication().pushModalScreen(l.getScreen());
				try {
					_demandante = l.getSelected();
					_txtDemandante.setText(_demandante.getNombre());
					_txtDemandante.setFocus();
				} catch (NullPointerException e) {
				}
			}

			if (f.equals(_txtDemandado)) {
				ListadoPersonas l = new ListadoPersonas(2);
				UiApplication.getUiApplication().pushModalScreen(l.getScreen());
				try {
					_demandado = l.getSelected();
					_txtDemandado.setText(_demandado.getNombre());
					_txtDemandado.setFocus();
				} catch (NullPointerException e) {
				}
			}

			if (f.equals(_txtJuzgado)) {
				ListadoJuzgados l = new ListadoJuzgados();
				UiApplication.getUiApplication().pushModalScreen(l.getScreen());
				try {
					_juzgado = l.getSelected();
					_txtJuzgado.setText(_juzgado.getNombre());
					_txtJuzgado.setFocus();
				} catch (NullPointerException e) {
				}
			}
		}
	};

	private final MenuItem menuAddActuacion = new MenuItem("Agregar actuación",
			0, 0) {

		public void run() {
			NuevaActuacion n = new NuevaActuacion(_proceso);
			UiApplication.getUiApplication().pushModalScreen(n.getScreen());
			try {
				_actuaciones.addElement(n.getActuacion());
				_ofActuaciones.setChoices(transformActuaciones());
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		}
	};
}
