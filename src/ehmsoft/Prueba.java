package ehmsoft;

import gui.ListadoActuaciones;
import gui.ListadoCampos;
import gui.ListadoCategorias;
import gui.ListadoJuzgados;
import gui.ListadoPersonas;
import gui.ListadoProcesos;
import gui.NuevaActuacion;
import gui.NuevaCategoria;
import gui.NuevaPersona;
import gui.NuevoJuzgado;
import gui.NuevoProceso;
import gui.VerActuacion;
import gui.VerJuzgado;
import gui.VerPersona;
import gui.VerProceso;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;
import persistence.Persistence;
import core.Actuacion;
import core.Juzgado;
import core.Persona;
import core.Proceso;

public class Prueba extends MainScreen {

	ButtonField listadoActuaciones;
	ButtonField listadoCategorias;
	ButtonField listadoCampos;
	ButtonField listadoJuzgados;
	ButtonField listadoDemandantes;
	ButtonField listadoDemandados;
	ButtonField listadoProcesos;
	ButtonField nuevaActuacion;
	ButtonField nuevaCategoria;
	ButtonField nuevoDemandante;
	ButtonField nuevoDemandado;
	ButtonField nuevoJuzgado;
	ButtonField nuevoProceso;
	ButtonField verActuacion;
	ButtonField verJuzgado;
	ButtonField verDemandante;
	ButtonField verDemandado;
	ButtonField verProceso;

	public Prueba() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		listadoActuaciones = new ButtonField("Listado de actuaciones");
		listadoActuaciones.setChangeListener(listenerListadoActuaciones);
		add(listadoActuaciones);
		
		listadoCategorias = new ButtonField("Listado de categor�as");
		listadoCategorias.setChangeListener(listenerListadoCategorias);
		add(listadoCategorias);
		
		listadoCampos = new ButtonField("Listado de campos");
		listadoCampos.setChangeListener(listenerListadoCampos);
		add(listadoCampos);

		listadoJuzgados = new ButtonField("Listado de juzgados");
		listadoJuzgados.setChangeListener(listenerListadoJuzgados);
		add(listadoJuzgados);

		listadoDemandantes = new ButtonField("Listado de demandantes");
		listadoDemandantes.setChangeListener(listenerListadoDemandantes);
		add(listadoDemandantes);

		listadoDemandados = new ButtonField("Listado de demandados");
		listadoDemandados.setChangeListener(listenerListadoDemandados);
		add(listadoDemandados);

		listadoProcesos = new ButtonField("Listado de procesos");
		listadoProcesos.setChangeListener(listenerListadoProcesos);
		add(listadoProcesos);

		nuevaActuacion = new ButtonField("Nueva actuaci�n");
		nuevaActuacion.setChangeListener(listenerNuevaActuacion);
		add(nuevaActuacion);
		
		nuevaCategoria = new ButtonField("Nueva categor�a");
		nuevaCategoria.setChangeListener(listenerNuevaCategoria);
		add(nuevaCategoria);

		nuevoDemandante = new ButtonField("Nuevo demandante");
		nuevoDemandante.setChangeListener(listenerNuevoDemandante);
		add(nuevoDemandante);

		nuevoDemandado = new ButtonField("Nuevo demandado");
		nuevoDemandado.setChangeListener(listenerNuevoDemandado);
		add(nuevoDemandado);

		nuevoJuzgado = new ButtonField("Nuevo juzgado");
		nuevoJuzgado.setChangeListener(listenerNuevoJuzgado);
		add(nuevoJuzgado);

		nuevoProceso = new ButtonField("Nuevo proceso");
		nuevoProceso.setChangeListener(listenerNuevoProceso);
		add(nuevoProceso);

		verActuacion = new ButtonField("Ver actuaci�n");
		verActuacion.setChangeListener(listenerVerActuacion);
		add(verActuacion);

		verJuzgado = new ButtonField("Ver juzgado");
		verJuzgado.setChangeListener(listenerVerJuzgado);
		add(verJuzgado);

		verDemandante = new ButtonField("Ver demandante");
		verDemandante.setChangeListener(listenerVerDemandante);
		add(verDemandante);

		verDemandado = new ButtonField("Ver demandado");
		verDemandado.setChangeListener(listenerVerDemandado);
		add(verDemandado);

		verProceso = new ButtonField("Ver proceso");
		verProceso.setChangeListener(listenerVerProceso);
		add(verProceso);
		

	}
	

	private FieldChangeListener listenerListadoActuaciones = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoProcesos listadoP = new ListadoProcesos();
			Proceso proceso = null;
			UiApplication.getUiApplication().pushModalScreen(
					listadoP.getScreen());
			proceso = listadoP.getSelected();
			if (proceso != null) {
				ListadoActuaciones listadoA = new ListadoActuaciones(proceso);
				UiApplication.getUiApplication().pushModalScreen(
						listadoA.getScreen());
			}
		}
	};
	
	private FieldChangeListener listenerListadoCategorias = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoCategorias l = new ListadoCategorias(ListadoCategorias.SEARCH);
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
			l.getSelected();
			}
	};
	
	private FieldChangeListener listenerListadoCampos = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoCampos l = new ListadoCampos(ListadoCampos.SEARCH | ListadoCampos.ON_CLICK_VER);
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
			l.getSelected();
			}
	};

	private FieldChangeListener listenerListadoJuzgados = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoJuzgados listado = new ListadoJuzgados(ListadoJuzgados.SEARCH);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
		}
	};

	private FieldChangeListener listenerListadoDemandantes = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoPersonas listado = new ListadoPersonas(1, ListadoPersonas.SEARCH);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
		}
	};

	private FieldChangeListener listenerListadoDemandados = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoPersonas listado = new ListadoPersonas(2, ListadoPersonas.SEARCH);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
		}
	};

	private FieldChangeListener listenerListadoProcesos = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoProcesos listado = new ListadoProcesos(ListadoProcesos.SEARCH);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			listado.getSelected();
		}
	};

	private FieldChangeListener listenerNuevaActuacion = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevaActuacion nueva = new NuevaActuacion();
			ListadoProcesos listado = new ListadoProcesos();
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			nueva.setProceso(listado.getSelected());
			UiApplication.getUiApplication().pushModalScreen(nueva.getScreen());
		}
	};
	
	private FieldChangeListener listenerNuevaCategoria = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevaCategoria n = new NuevaCategoria();
			UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		}
	};

	private FieldChangeListener listenerNuevoDemandante = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevaPersona nuevo = new NuevaPersona(1);
			UiApplication.getUiApplication().pushScreen(nuevo.getScreen());
		}
	};

	private FieldChangeListener listenerNuevoDemandado = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevaPersona nuevo = new NuevaPersona(2);
			UiApplication.getUiApplication().pushScreen(nuevo.getScreen());
		}
	};

	private FieldChangeListener listenerNuevoJuzgado = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevoJuzgado nuevo = new NuevoJuzgado();
			UiApplication.getUiApplication().pushScreen(nuevo.getScreen());
		}
	};

	private FieldChangeListener listenerNuevoProceso = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevoProceso nuevo = new NuevoProceso();
			UiApplication.getUiApplication().pushModalScreen(nuevo.getScreen());
		}
	};

	private FieldChangeListener listenerVerActuacion = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Persistence persistence = null;
			try {
				persistence = new Persistence();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			Actuacion actuacion = null;
			try {
				actuacion = persistence.consultarActuacion("1");
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			VerActuacion ver = new VerActuacion(actuacion);
			UiApplication.getUiApplication().pushModalScreen(ver.getScreen());
		}
	};

	private FieldChangeListener listenerVerJuzgado = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Persistence persistence = null;
			try {
				persistence = new Persistence();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			Juzgado juzgado = null;
			try {
				juzgado = persistence.consultarJuzgado("1");
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			VerJuzgado ver = new VerJuzgado(juzgado);
			UiApplication.getUiApplication().pushModalScreen(ver.getScreen());
		}
	};

	private FieldChangeListener listenerVerDemandante = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Persistence persistence = null;
			try {
				persistence = new Persistence();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			Persona persona = null;
			try {
				persona = persistence.consultarPersona("1", 1);
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			VerPersona ver = new VerPersona(persona);
			UiApplication.getUiApplication().pushModalScreen(ver.getScreen());
		}
	};

	private FieldChangeListener listenerVerDemandado = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Persistence persistence = null;
			try {
				persistence = new Persistence();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			Persona persona = null;
			try {
				persona = persistence.consultarPersona("1", 2);
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			VerPersona ver = new VerPersona(persona);
			UiApplication.getUiApplication().pushModalScreen(ver.getScreen());
		}
	};

	private FieldChangeListener listenerVerProceso = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Persistence persistence = null;
			try {
				persistence = new Persistence();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			Proceso proceso = null;
			try {
				proceso = persistence.consultarProceso("1");
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			VerProceso ver = new VerProceso(proceso);
			UiApplication.getUiApplication().pushModalScreen(ver.getScreen());
		}
	};
	
	public boolean onClose() {
		System.exit(0);
		return true;
	}
}
