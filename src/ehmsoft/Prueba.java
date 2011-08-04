package ehmsoft;


import gui.ListadoPersonas;
import gui.ListadoPersonasLista;
import gui.ListadoPersonasScreen;


import gui.NuevaPersona;

import gui.VerPersona;

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
	ButtonField verActuacion;
	ButtonField verJuzgado;
	ButtonField verDemandante;
	ButtonField verDemandado;
	ButtonField verProceso;

	public Prueba() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		listadoDemandantes = new ButtonField("Listado de demandantes");
		listadoDemandantes.setChangeListener(listenerListadoDemandantes);
		add(listadoDemandantes);

		listadoDemandados = new ButtonField("Listado de demandados");
		listadoDemandados.setChangeListener(listenerListadoDemandados);
		add(listadoDemandados);
	}

	private FieldChangeListener listenerListadoDemandantes = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			ListadoPersonas listado = new ListadoPersonas(1,ListadoPersonasScreen.SEARCH);
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
	
	public boolean onClose() {
		System.exit(0);
		return true;
	}
}