package gui;

import java.util.Calendar;

import persistence.Persistence;

import core.Actuacion;
import core.CampoPersonalizado;
import core.Categoria;
import core.Juzgado;
import core.Persona;
import core.Proceso;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class Util {
	
	public static final short ADD_DEMANDANTE = 1;
	public static final short ADD_DEMANDADO = 2;
	public static final short ADD_JUZGADO = 3;
	public static final short ADD_CATEGORIA = 4;
	public static final short ADD_CITA = 5;
	public static final short ADD_CAMPO = 6;
	
	public static final short VER_DEMANDANTE= 7;
	public static final short VER_DEMANDADO = 8;
	public static final short VER_JUZGADO = 9;
	public static final short VER_ELEMENTO = 10;
	public static final short VER_CITA = 11;
	public static final short VER_CATEGORIA = 12;
	public static final short VER_ACTUACION = 13;
	public static final short VER_CAMPO = 25;
	
	public static final short NEW_ACTUACION = 14;
	public static final short NEW_CATEGORIA = 15;
	
	public static final short ELIMINAR_DEMANDANTE = 16;
	public static final short ELIMINAR_DEMANDADO = 17;
	public static final short ELIMINAR_JUZGADO = 18;
	public static final short ELIMINAR_CITA = 19;
	public static final short ELIMINAR_CAMPO = 20;
	
	public static final short GUARDAR = 21;
	public static final short CERRAR = 22;
	public static final short ELIMINAR = 23;

	public static final short CLICK = 24;
	
	public static PopupScreen WAIT_SCREEN;
	
	public static void popScreen(Screen screen) {
		UiApplication.getUiApplication().popScreen(screen);
	}
	
	public static void pushWaitScreen() {
		WAIT_SCREEN = new PopupScreen(new VerticalFieldManager());
		WAIT_SCREEN.add(new LabelField("Procesando, espere porfavor..."));
		UiApplication.getUiApplication().pushGlobalScreen(WAIT_SCREEN, 0,
				UiApplication.GLOBAL_QUEUE);
	}

	public static void popModalScreen() {
		if (!WAIT_SCREEN.isDisplayed())
			;
		popScreen(WAIT_SCREEN);
	}

	public static String noSDString() {
		return("Tarjeta SD no presente, la aplicación se cerrará, verifique e inicie nuevamente");
	}
	
	public static void noSd() {
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
				Dialog.alert(noSDString());
				System.exit(0);
			}
		});
	}
	
	public static void alert(final String alert) {
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
				Dialog.alert(alert);
			}
		});
	}
	
	public static String delBDJuzgado() {
		return "¿Desea eliminar el juzgado?. Se eliminará definitivamente y de cada proceso que lo contenga";
	}
	
	public static String delBDPersona() {
		return "¿Desea eliminar la persona?. Se eliminará definitivamente y de cada proceso que la contenga";
	}
	
	public static String delBDCategoria() {
		return "¿Desea eliminar la categoría?. Se eliminará de definitivamente y cada proceso ligado a esta";
	}
	
	public static String delBDCampo() {
		return "¿Desea eliminar el campo personalizado?. Se eliminará definitivamente y de cada proceso que lo contenga";
	}
	
	public static String delBDActuacion() {
		return "¿Desea eliminar la actuación?. Se eliminará definitivamente";
	}
	
	public static String delBDProceso() {
		return "¿Desea eliminar el proceso?. Se eliminará definitivamente y las actuaciones ligadas a este";
	}
	
	public static String delJuzgado() {
		return "¿Desea eliminar el juzgado?";
	}
	
	public static String delPersona() {
		return "¿Desea eliminar la persona?";
	}
	
	public static String delCategoria() {
		return "¿Desea eliminar la categoría?";
	}
	
	public static String delCampo() {
		return "¿Desea eliminar el campo personalizado?";
	}
	
	public static String delActuacion() {
		return "¿Desea eliminar la actuación?";
	}
	
	public static Persona consultarPersonaVacia(int tipo) {
		Persona persona = null;
		try {
			persona = new Persistence().consultarPersona("1", tipo);
		} catch (NullPointerException e) {
			noSd();
		} catch (Exception e) {
			alert(e.toString());
		}
		return persona;
	}
	
	public static Juzgado consultarJuzgadoVacio() {
		Juzgado juzgado = null;
		try {
			juzgado = new Persistence().consultarJuzgado("1");
		} catch (NullPointerException e) {
			noSd();
		} catch (Exception e) {
			alert(e.toString());
		}
		return juzgado;
	}
	
	public static Categoria consultarCategoriaVacio() {
		Categoria categoria = null;
		try {
			categoria = new Persistence().consultarCategoria("1");
		} catch (NullPointerException e) {
			noSd();
		} catch (Exception e) {
			alert(e.toString());
		}
		return categoria;
	}
	
	public static Persona verPersona(Persona persona) {
		VerPersona v = new VerPersona(persona);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		return v.getPersona();
	}
	
	public static Juzgado verJuzgado(Juzgado juzgado) {
		VerJuzgado v = new VerJuzgado(juzgado);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		return v.getJuzgado();
	}
	
	public static Categoria verCategoria(Categoria categoria) {
		VerCategoria v = new VerCategoria(categoria);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		return v.getCategoria();
	}
	
	public static CampoPersonalizado verCampo(CampoPersonalizado campo) {
		VerCampo v = new VerCampo(campo);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		return v.getCampo();
	}
	
	public static Actuacion verActuacion(Actuacion actuacion) {
		VerActuacion v = new VerActuacion(actuacion);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		return v.getActuacion();
	}
	
	public static Proceso verProceso(Proceso proceso) {
		VerProceso v = new VerProceso(proceso);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		return v.getProceso();
	}
	
	public static Persona listadoPersonas(int tipo, boolean popup) {
		ListadoPersonas l = new ListadoPersonas(tipo, popup);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		return l.getSelected();
	}
	
	public static Juzgado listadoJuzgados(boolean popup) {
		ListadoJuzgados l = new ListadoJuzgados(popup);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		return l.getSelected();
	}
	
	public static Categoria listadoCategorias(boolean popup) {
		ListadoCategorias l = new ListadoCategorias(popup);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		return l.getSelected();
	}
	
	public static CampoPersonalizado listadoCampos(boolean popup) {
		ListadoCampos l = new ListadoCampos(popup);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		return l.getSelected();
	}
	
	public static Actuacion nuevaActuacion(Proceso proceso) {
		NuevaActuacion n = new NuevaActuacion(proceso);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		return n.getActuacion();
	}
	
	public static Actuacion nuevaActuacion() {
		NuevaActuacion n = new NuevaActuacion();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		return n.getActuacion();
	}
	
	public static CampoPersonalizado nuevoCampoPersonalizado() {
		NuevoCampo n = new NuevoCampo();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		return n.getCampo();
	}
	
	public static Categoria nuevaCategoria(boolean popup) {
		NuevaCategoria n = new NuevaCategoria(popup);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		return n.getCategoria();
	}
	
	public static Juzgado nuevoJuzgado() {
		NuevoJuzgado n = new NuevoJuzgado();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		return n.getJuzgado();
	}
	
	public static Persona nuevaPersona(int tipo) {
		NuevaPersona n = new NuevaPersona(tipo);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		return n.getPersona();
	}

	public static String calendarToString(Calendar calendar, boolean largo) {
		String string = "";
		if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
			string = string + "0";
		}
		string = string + calendar.get(Calendar.DAY_OF_MONTH);
		string = string + "/";
		if ((calendar.get(Calendar.MONTH) + 1) < 10) {
			string = string + "0";
		}
		string = string + (calendar.get(Calendar.MONTH) + 1);
		string = string + "/";
		string = string + calendar.get(Calendar.YEAR);
		if (largo) {
			if (calendar.get(Calendar.HOUR) == 1) {
				string = string + " a la ";
			} else {
				string = string + " a las ";
			}
		} else {
			string = string + " - ";
		}
		if (calendar.get(Calendar.HOUR) < 10) {
			string = string + "0";
		}
		string = string + calendar.get(Calendar.HOUR);
		string = string + ":";
		if(calendar.get(Calendar.MINUTE) < 10) {
			string = string + "0";
		}
		string = string + calendar.get(Calendar.MINUTE);
		string = string + " ";
		if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
			string = string + ("AM");
		} else {
			string = string + ("PM");
		}
		return string;
	}
}
