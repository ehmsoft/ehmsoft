package gui;

import java.util.Calendar;
import java.util.Vector;

import core.CampoPersonalizado;
import core.Juzgado;
import core.Persona;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevoProceso extends FondoNuevos {

	/**
	 * Pantalla para crear un nuevo proceso
	 */
	private ObjectChoiceField _chEstado;
	private ObjectChoiceField _chCategoria;
	private NumericChoiceField _chPrioridad;
	private DateField _dtFecha;
	private BasicEditField _txtNotas;
	private BasicEditField _txtRadicado;
	private BasicEditField _txtRadicadoUnico;
	private ButtonField _btnDemandante;
	private ButtonField _btnDemandado;
	private ButtonField _btnJuzgado;
	private ButtonField _btnCampoPersonalizado;

	private HorizontalFieldManager fldCampoPersonalizado;
	private VerticalFieldManager fldRightCampoPersonalizado;
	private VerticalFieldManager fldLeftCampoPersonalizado;

	private Persona _demandante;
	private Persona _demandado;
	private Juzgado _juzgado;
	private Vector _camposPersonalizados;
	private Vector _valoresCamposPersonalizados;

	public NuevoProceso() {
		setTitle("Nuevo Proceso");
		_camposPersonalizados = new Vector();
		_valoresCamposPersonalizados = new Vector();

		HorizontalFieldManager fldDemandante = new HorizontalFieldManager();
		VerticalFieldManager fldRightDemandante = new VerticalFieldManager(
				USE_ALL_WIDTH);
		VerticalFieldManager fldLeftDemandante = new VerticalFieldManager();
		_btnDemandante = new ButtonField("Seleccionar", FIELD_RIGHT);
		_btnDemandante.setChangeListener(listenerBtnDemandante);
		fldLeftDemandante.add(new LabelField("Demandante: "));
		fldRightDemandante.add(_btnDemandante);
		fldDemandante.add(fldLeftDemandante);
		fldDemandante.add(fldRightDemandante);
		_vertical.add(fldDemandante);

		HorizontalFieldManager fldDemandado = new HorizontalFieldManager();
		VerticalFieldManager fldRightDemandado = new VerticalFieldManager(
				USE_ALL_WIDTH);
		VerticalFieldManager fldLeftDemandado = new VerticalFieldManager();
		_btnDemandado = new ButtonField("Seleccionar", FIELD_RIGHT);
		_btnDemandado.setChangeListener(listenerBtnDemandado);
		fldLeftDemandado.add(new LabelField("Demandado: "));
		fldRightDemandado.add(_btnDemandado);
		fldDemandado.add(fldLeftDemandado);
		fldDemandado.add(fldRightDemandado);
		_vertical.add(fldDemandado);

		HorizontalFieldManager fldJuzgado = new HorizontalFieldManager();
		VerticalFieldManager fldRightJuzgado = new VerticalFieldManager(
				USE_ALL_WIDTH);
		VerticalFieldManager fldLeftJuzgado = new VerticalFieldManager();
		_btnJuzgado = new ButtonField("Seleccionar", FIELD_RIGHT);
		_btnJuzgado.setChangeListener(listenerBtnJuzgado);
		fldLeftJuzgado.add(new LabelField("Juzgado: "));
		fldRightJuzgado.add(_btnJuzgado);
		fldJuzgado.add(fldLeftJuzgado);
		fldJuzgado.add(fldRightJuzgado);
		_vertical.add(fldJuzgado);

		_txtRadicado = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtRadicado.setLabel("Radicado: ");
		_vertical.add(_txtRadicado);

		_txtRadicadoUnico = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtRadicadoUnico.setLabel("Radicado unico: ");
		_vertical.add(_txtRadicadoUnico);

		_chEstado = new ObjectChoiceField();
		_chEstado.setLabel("Estado:");
		Object[] initialChoiceEstado = { "Nuevo" };
		_chEstado.setChoices(initialChoiceEstado);
		_vertical.add(_chEstado);

		_chCategoria = new ObjectChoiceField();
		_chCategoria.setLabel("Categoria:");
		Object[] initialChoiceCategoria = { "Nueva" };
		_chCategoria.setChoices(initialChoiceCategoria);
		_vertical.add(_chCategoria);

		_chPrioridad = new NumericChoiceField("Prioridad", 1, 10, 1);
		_chPrioridad.setSelectedIndex(4);
		_vertical.add(_chPrioridad);

		_dtFecha = new DateField("Fecha de creaci�n: ",
				System.currentTimeMillis(), DateField.DATE);
		_dtFecha.setEditable(true);
		_vertical.add(_dtFecha);

		_txtNotas = new BasicEditField();
		_txtNotas.setLabel("Notas: ");
		_vertical.add(_txtNotas);

		fldCampoPersonalizado = new HorizontalFieldManager();
		fldRightCampoPersonalizado = new VerticalFieldManager(USE_ALL_WIDTH);
		fldLeftCampoPersonalizado = new VerticalFieldManager();
		_btnCampoPersonalizado = new ButtonField("Nuevo", FIELD_RIGHT);
		_btnCampoPersonalizado.setChangeListener(listenerBtnCampoPersonalizado);
		fldLeftCampoPersonalizado.add(new LabelField("Campo personalizado: "));
		fldRightCampoPersonalizado.add(_btnCampoPersonalizado);
		fldCampoPersonalizado.add(fldLeftCampoPersonalizado);
		fldCampoPersonalizado.add(fldRightCampoPersonalizado);
		_vertical.add(fldCampoPersonalizado);

		add(_vertical);

		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	private FieldChangeListener listenerBtnDemandante = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ListadoPersonasController demandantes = new ListadoPersonasController(
					1);
			UiApplication.getUiApplication().pushModalScreen(
					demandantes.getScreen());
			try {
				_demandante = demandantes.getSelected();
				_btnDemandante.setLabel(_demandante.getNombre());
			} catch (NullPointerException e) {
				if (_demandante == null)
					Dialog.alert(_demandante.toString()
							+ "Debe seleccionar un demandante");
			}
		}
	};

	private FieldChangeListener listenerBtnDemandado = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ListadoPersonasController demandados = new ListadoPersonasController(
					2);
			UiApplication.getUiApplication().pushModalScreen(
					demandados.getScreen());
			try {
				_demandado = demandados.getSelected();
				_btnDemandado.setLabel(_demandado.getNombre());
			} catch (NullPointerException e) {
				if (_demandado == null)
					Dialog.alert("Debe seleccionar un demandado");
			} finally {
				demandados = null;
			}
		}
	};

	private FieldChangeListener listenerBtnJuzgado = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ListadoJuzgadosController juzgados = new ListadoJuzgadosController();
			UiApplication.getUiApplication().pushModalScreen(
					juzgados.getScreen());
			try {
				_juzgado = juzgados.getSelected();
				_btnJuzgado.setLabel(_juzgado.getNombre());
			} catch (NullPointerException e) {
				if (_juzgado == null)
					Dialog.alert("Debe seleccionar un juzgado");
			} finally {
				juzgados = null;
			}
		}
	};

	private FieldChangeListener listenerBtnCampoPersonalizado = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			NuevoCampoPersonalizadoController campo = new NuevoCampoPersonalizadoController();
			UiApplication.getUiApplication().pushModalScreen(campo.getScreen());
			try {
				campo.guardarCampo();
				addCampoPersonalizado(campo.getCampo());
			} catch (NullPointerException e) {
				Dialog.alert(e.toString());
			}
		}
	};

	public void addCampoPersonalizado(CampoPersonalizado campo) {
		BasicEditField campoP = new BasicEditField();
		campoP.setLabel(campo.getNombre() + ": ");
		if (campo.getLongitudMax() != 0)
			campoP.setMaxSize(campo.getLongitudMax());
		_vertical.add(campoP);
		_camposPersonalizados.addElement(campo);
		_valoresCamposPersonalizados.addElement(campoP);
	}

	public Vector getCampos() {
		return _camposPersonalizados;
	}

	public Vector getValores() {
		return _valoresCamposPersonalizados;
	}

	public Persona getDemandante() {
		return _demandante;
	}

	public Persona getDemandado() {
		return _demandado;
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

	public String getEstado() {
		return (String) _chEstado.getChoice(_chEstado.getSelectedIndex());
	}

	public String getCategoria() {
		return (String) _chEstado.getChoice(_chCategoria.getSelectedIndex());
	}

	public short getPrioridad() {
		return Short.parseShort((String) _chPrioridad.getChoice(_chEstado
				.getSelectedIndex()));
	}

	public String getNotas() {
		return _txtNotas.getText();
	}

	public Calendar getFecha() {
		Calendar fecha = Calendar.getInstance();

		fecha.setTime(fecha.getTime());
		return fecha;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}