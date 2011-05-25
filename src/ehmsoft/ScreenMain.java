package ehmsoft;

import gui.ListadoJuzgadosController;
import gui.ListadoPersonasController;
import gui.ListadoProcesosController;
import gui.NuevaPersonaController;
import gui.NuevoCampoPersonalizadoController;
import gui.NuevoJuzgadoController;
import gui.NuevoProcesoController;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;
import core.CampoPersonalizado;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class ScreenMain extends MainScreen {
	/**
	 * Creates a new screenMain object
	 */
	Main theApp;
	ButtonField btnListadoProcesos;
	ButtonField btnNuevoProceso;
	ButtonField btnNuevoDemandante;
	ButtonField btnNuevoDemandado;
	ButtonField btnListadoDemandantes;
	ButtonField btnListadoJuzgados;
	ButtonField btnNuevoCampoPersonalizado;

	public ScreenMain(Main theApp) {
		this.theApp = theApp;
		// Set the displayed title of the screen
		setTitle("Software abogados v0.1.0");
		btnListadoProcesos = new ButtonField("Listado de procesos");
		btnListadoProcesos.setChangeListener(listenerListadoProcesos);
		add(btnListadoProcesos);

		btnNuevoProceso = new ButtonField("Nuevo Proceso");
		btnNuevoProceso.setChangeListener(listenerNuevoProceso);
		add(btnNuevoProceso);

		btnNuevoDemandante = new ButtonField("Nuevo Demandante");
		btnNuevoDemandante.setChangeListener(listenerNuevoDemandante);
		add(btnNuevoDemandante);

		btnNuevoDemandado = new ButtonField("Nuevo Demandado");
		btnNuevoDemandado.setChangeListener(listenerNuevoDemandado);
		add(btnNuevoDemandado);

		btnListadoDemandantes = new ButtonField("Listado Demandantes");
		btnListadoDemandantes.setChangeListener(listenerListadoDemandantes);
		add(btnListadoDemandantes);

		btnListadoJuzgados = new ButtonField("Listado Juzgados");
		btnListadoJuzgados.setChangeListener(listenerListadoJuzgados);
		add(btnListadoJuzgados);

		btnNuevoCampoPersonalizado = new ButtonField(
				"Nuevo campo personalizado");
		btnNuevoCampoPersonalizado
				.setChangeListener(listenerNuevoCampoPersonalizado);
		add(btnNuevoCampoPersonalizado);
	}

	private FieldChangeListener listenerListadoProcesos = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ListadoProcesosController listado = new ListadoProcesosController();
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			Dialog.alert(listado.getSelected().getDemandante().getNombre());
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			Dialog.alert(listado.getSelected().getDemandante().getNombre());
		}
	};

	private FieldChangeListener listenerNuevoProceso = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			NuevoProcesoController proceso = new NuevoProcesoController();
			UiApplication.getUiApplication().pushModalScreen(
					proceso.getScreen());
			proceso.guardarProceso();
			Dialog.alert(((CampoPersonalizado) proceso.getProceso().getCampos()
					.elementAt(1)).getValor());
			proceso = null;
		}
	};

	private FieldChangeListener listenerNuevoDemandante = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			NuevaPersonaController demandante = new NuevaPersonaController(1);
			UiApplication.getUiApplication().pushModalScreen(
					demandante.getScreen());
			demandante.guardarPersona();
			Dialog.alert(demandante.getPersona().getNombre());
			demandante = null;
		}
	};

	private FieldChangeListener listenerNuevoDemandado = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			NuevaPersonaController demandado = new NuevaPersonaController(2);
			UiApplication.getUiApplication().pushModalScreen(
					demandado.getScreen());
			demandado.guardarPersona();
			Dialog.alert(demandado.getPersona().getNombre());
			demandado = null;
		}
	};

	private FieldChangeListener listenerListadoDemandantes = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			NuevaPersonaController persona1 = new NuevaPersonaController(1);
			UiApplication.getUiApplication().pushModalScreen(
					persona1.getScreen());
			persona1.guardarPersona();
			NuevaPersonaController persona2 = new NuevaPersonaController(1);
			UiApplication.getUiApplication().pushModalScreen(
					persona2.getScreen());
			persona2.guardarPersona();
			NuevaPersonaController persona3 = new NuevaPersonaController(1);
			UiApplication.getUiApplication().pushModalScreen(
					persona3.getScreen());
			persona3.guardarPersona();
			Vector vector = new Vector();
			vector.addElement(persona1.getPersona());
			vector.addElement(persona2.getPersona());
			vector.addElement(persona3.getPersona());

			ListadoPersonasController listado = new ListadoPersonasController(1);
			listado.setVectorPersonas(vector);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			Dialog.alert(listado.getSelected().getNombre());
		}
	};

	private FieldChangeListener listenerListadoJuzgados = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			NuevoJuzgadoController juzgado1 = new NuevoJuzgadoController();
			UiApplication.getUiApplication().pushModalScreen(
					juzgado1.getScreen());
			juzgado1.guardarJuzgado();

			NuevoJuzgadoController juzgado2 = new NuevoJuzgadoController();
			UiApplication.getUiApplication().pushModalScreen(
					juzgado2.getScreen());
			juzgado2.guardarJuzgado();

			Vector vector = new Vector();
			vector.addElement(juzgado1.getJuzgado());
			vector.addElement(juzgado2.getJuzgado());

			ListadoJuzgadosController listado = new ListadoJuzgadosController();
			listado.setVectorJuzgados(vector);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			Dialog.alert(listado.getSelected().getNombre());
		}
	};

	private FieldChangeListener listenerNuevoCampoPersonalizado = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			NuevoCampoPersonalizadoController controller = new NuevoCampoPersonalizadoController();
			UiApplication.getUiApplication().pushModalScreen(
					controller.getScreen());
			controller.guardarCampo();
			Dialog.alert(controller.getCampo().getNombre());
		}
	};

	public boolean onClose() {
		System.exit(0);
		return true;
	}
}
