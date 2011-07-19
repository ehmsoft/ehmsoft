package ehmsoft;

import java.util.Calendar;

import gui.ListadoActuaciones;
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

	ButtonField verJuzgado;
	ButtonField verDemandante;
	ButtonField verDemandado;
	ButtonField verProceso;
	ButtonField actualizarActuacion;

	public Prueba() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		listadoActuaciones = new ButtonField("Listado de actuaciones");
		listadoActuaciones.setChangeListener(listenerListadoActuaciones);
		add(listadoActuaciones);
		
		listadoCategorias = new ButtonField("Listado de categorías");
		listadoCategorias.setChangeListener(listenerListadoCategorias);
		add(listadoCategorias);

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

		nuevaActuacion = new ButtonField("Nueva actuación");
		nuevaActuacion.setChangeListener(listenerNuevaActuacion);
		add(nuevaActuacion);
		
		nuevaCategoria = new ButtonField("Nueva categoría");
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
		
		actualizarActuacion = new ButtonField("Actualizar Actuacion");
		actualizarActuacion.setChangeListener(listenerActualizarActuacion);
		add(actualizarActuacion);
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
				listadoA.getSelected();
			}
		}
	};
	
	private FieldChangeListener listenerListadoCategorias = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoCategorias l = new ListadoCategorias();
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
			l.getSelected();
			}
	};

	private FieldChangeListener listenerListadoJuzgados = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoJuzgados listado = new ListadoJuzgados();
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			listado.getSelected();
		}
	};

	private FieldChangeListener listenerListadoDemandantes = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoPersonas listado = new ListadoPersonas(1);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			listado.getSelected();
		}
	};

	private FieldChangeListener listenerListadoDemandados = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoPersonas listado = new ListadoPersonas(2);
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			listado.getSelected();
		}
	};

	private FieldChangeListener listenerListadoProcesos = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoProcesos listado = new ListadoProcesos();
			UiApplication.getUiApplication().pushModalScreen(
					listado.getScreen());
			listado.getSelected();
		}
	};

	private FieldChangeListener listenerNuevaActuacion = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Persistence per = null;
			//NuevaActuacion nueva = new NuevaActuacion();
			//ListadoProcesos listado = new ListadoProcesos();
			//UiApplication.getUiApplication().pushModalScreen(
			//		listado.getScreen());
			//nueva.setProceso(listado.getSelected());
			//UiApplication.getUiApplication().pushModalScreen(nueva.getScreen());
			
			Juzgado juzgado = new Juzgado("23213","23213213","213213","2132132","3","3");

			Actuacion actuacion = new Actuacion(juzgado,stringToCalendar("1918-03-02"),stringToCalendar("1919-04-04"),"primera actuacion con uid","1","2");
			
			try {
				Persistence persistence = new Persistence();
				persistence.guardarActuacion(actuacion, "1");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			
		} 
		}
	};
	
	private FieldChangeListener listenerNuevaCategoria = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevaCategoria n = new NuevaCategoria();
			UiApplication.getUiApplication().pushModalScreen(n.getScreen());
			try {
				n.guardarCategoria();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		}
	};

	private FieldChangeListener listenerNuevoDemandante = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevaPersona nuevo = new NuevaPersona(1);
			UiApplication.getUiApplication().pushModalScreen(nuevo.getScreen());
			try {
				nuevo.guardarPersona();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		}
	};

	private FieldChangeListener listenerNuevoDemandado = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevaPersona nuevo = new NuevaPersona(2);
			UiApplication.getUiApplication().pushModalScreen(nuevo.getScreen());
			try {
				nuevo.guardarPersona();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		}
	};

	private FieldChangeListener listenerNuevoJuzgado = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevoJuzgado nuevo = new NuevoJuzgado();
			UiApplication.getUiApplication().pushModalScreen(nuevo.getScreen());
			try {
				nuevo.guardarJuzgado();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		}
	};

	private FieldChangeListener listenerNuevoProceso = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			NuevoProceso nuevo = new NuevoProceso();
			UiApplication.getUiApplication().pushModalScreen(nuevo.getScreen());
			try {
				nuevo.guardarProceso();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		}
	};

	private FieldChangeListener listenerActualizarActuacion = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			Juzgado juzgado = new Juzgado("23213","23213213","213213","2132132","3","1");

			Actuacion actuacion = new Actuacion(juzgado,stringToCalendar("1218-03-02"),stringToCalendar("1212-04-04"),"primer cambio en actuacion con uid","91","1");
			
			try {
				Persistence persistence = new Persistence();
				persistence.actualizarActuacion(actuacion);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			
		} 
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
			ver.actualizarJuzgado();
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
			ver.actualizarPersona();
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
			ver.actualizarPersona();
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
			ver.actualizarProceso();
		}
	};
	
	private Calendar stringToCalendar(String fecha) {
		Calendar calendar_return = Calendar.getInstance();
		calendar_return.set(Calendar.YEAR,
				Integer.parseInt(fecha.substring(0, 4)));
		calendar_return.set(Calendar.MONTH,
				Integer.parseInt(fecha.substring(5, 7)) - 1);
		calendar_return.set(Calendar.DAY_OF_MONTH,
				Integer.parseInt(fecha.substring(8, 10)));
		if (fecha.length() > 10) {
			calendar_return.set(Calendar.HOUR_OF_DAY,
					Integer.parseInt(fecha.substring(11, 13)));
			calendar_return.set(Calendar.MINUTE,
					Integer.parseInt(fecha.substring(14, 16)));
		}
		return calendar_return;
	};
}