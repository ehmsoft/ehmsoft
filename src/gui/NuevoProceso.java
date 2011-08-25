package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;

import persistence.Persistence;
import core.Actuacion;
import core.CampoPersonalizado;
import core.Categoria;
import core.Juzgado;
import core.Persona;
import core.Proceso;

public class NuevoProceso {

	private Proceso _proceso;
	private NuevoProcesoScreen _screen;

	private Persona _demandante;
	private Persona _demandado;
	private Juzgado _juzgado;
	private Vector _actuaciones;
	private Vector _campos;
	private Vector _categorias;

	/**
	 * Se crea un NuevoProceso y le asocia una pantalla, con este objeto se
	 * crearan nuevos Procesos
	 */
	public NuevoProceso() {
		_campos = new Vector();
		_actuaciones = new Vector();
		_screen = new NuevoProcesoScreen();
		try {
			Persistence p = new Persistence();

			_screen.setDemandante(p.consultarPersona("1", 1).getNombre());
			_screen.setDemandado(p.consultarPersona("1", 2).getNombre());
			_screen.setJuzgado(p.consultarJuzgado("1").getNombre());

			_categorias = p.consultarCategorias();
			Categoria ninguna = p.consultarCategoria("1");
			_categorias.removeElement(ninguna);
			_categorias.insertElementAt(ninguna, 0);

		} catch (NullPointerException e) {
			_screen.alert(Util.noSD());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}

		Object[] categorias = new Object[_categorias.size()];
		_categorias.copyInto(categorias);
		_screen.addCategorias(categorias);
		_screen.setChangeListener(listener);
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.ADD_DEMANDANTE) {
				addDemandante();
			} else if (context == Util.ADD_DEMANDADO) {
				addDemandado();
			} else if (context == Util.ADD_JUZGADO) {
				addJuzgado();
			} else if (context == Util.NEW_ACTUACION) {
				newActuacion();
			} else if (context == Util.NEW_CATEGORIA) {
				newCategoria();
			} else if (context == Util.ADD_CATEGORIA) {
				addCategoria();
			} else if (context == Util.GUARDAR) {
				guardarProceso();
			} else if (context == Util.ADD_CAMPO) {
				addCampo();
			} else if (context == Util.ELIMINAR_CAMPO) {
				eliminarCampo();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	public Proceso getProceso() {
		return _proceso;
	}

	/**
	 * @return La pantalla asociada al objeto
	 */
	public NuevoProcesoScreen getScreen() {
		return _screen;
	}

	/**
	 * Se crea el nuevo Proceso, basado en los datos capturados por la pantalla
	 * y se guarda en la base de datos
	 */
	private void guardarProceso() {
		getValoresCampos();
		if (_demandante == null) {
			_screen.alert("El Demandante es obligatorio");
		} else if (_demandado == null) {
			_screen.alert("El Demandado es obligatorio");
		} else if (_juzgado == null) {
			_screen.alert("El juzgado es obligatorio");
		} else if(isCampoObligatorio()) {
		}
		else {
			_proceso = new Proceso(_demandante, _demandado, _screen.getFecha(),
					_juzgado, _screen.getRadicado(),
					_screen.getRadicadoUnico(), _actuaciones,
					_screen.getEstado(), (Categoria) _screen.getCategoria(),
					_screen.getTipo(), _screen.getNotas(), _campos,
					_screen.getPrioridad());
			try {
				new Persistence().guardarProceso(_proceso);
			} catch (NullPointerException e) {
				_screen.alert(Util.noSD());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
	
	private boolean isCampoObligatorio() {
		boolean ret = false;
		Enumeration e = _campos.elements();
		while(e.hasMoreElements()) {
			CampoPersonalizado campo = (CampoPersonalizado)e.nextElement();
			if(campo.isObligatorio().booleanValue()) {
				_screen.alert("El campo " + campo.getNombre() + " es obligatorio");
				ret = true;
				break;
			}
		}
		return ret;
	}

	private void getValoresCampos() {
		for (int i = 0; i < _campos.size(); i++) {
			Object[] arrayCampo = _screen.getCampo(i);
			CampoPersonalizado campo = (CampoPersonalizado) arrayCampo[0];
			String valor = (String) arrayCampo[1];

			((CampoPersonalizado) _campos.elementAt(_campos.indexOf(campo)))
					.setValor(valor);
		}
	}

	private void addDemandante() {
		ListadoPersonas l = new ListadoPersonas(1, true);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		Persona demandante = l.getSelected();
		if (demandante != null) {
			_demandante = demandante;
			_screen.setDemandante(_demandante.getNombre());
		}
		l = null;
	}

	private void addDemandado() {
		ListadoPersonas l = new ListadoPersonas(2, true);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		Persona demandado = l.getSelected();
		if (demandado != null) {
			_demandado = demandado;
			_screen.setDemandado(_demandado.getNombre());
		}
		l = null;
	}

	private void addJuzgado() {
		ListadoJuzgados l = new ListadoJuzgados(true);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		Juzgado juzgado = l.getSelected();
		if (juzgado != null) {
			_juzgado = juzgado;
			_screen.setJuzgado(_juzgado.getNombre());
		}
		l = null;
	}

	private void newActuacion() {
		NuevaActuacion n = new NuevaActuacion();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Actuacion actuacion = n.getActuacion();
		if (actuacion != null) {
			_actuaciones.addElement(actuacion);
			Object[] actuaciones = new Object[_actuaciones.size()];
			_actuaciones.copyInto(actuaciones);
			_screen.addActuaciones(actuaciones);
			_screen.selectActuacion(_actuaciones.indexOf(actuacion));
		}
		n = null;
	}

	private void addCategoria() {
		ListadoCategorias l = new ListadoCategorias(true);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		Categoria categoria = l.getSelected();
		if (categoria != null) {
			_categorias.addElement(categoria);
			Object[] categorias = new Object[_categorias.size()];
			_categorias.copyInto(categorias);
			_screen.addCategorias(categorias);
			_screen.selectCategoria(_categorias.indexOf(categoria));
		}
		l = null;
	}

	private void addCampo() {
		ListadoCampos l = new ListadoCampos(true);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		CampoPersonalizado campo = l.getSelected();
		if (campo != null) {
			_campos.addElement(campo);
			_screen.addCampo(campo, campo.getNombre());
		}
	}

	private void eliminarCampo() {

	}

	private void newCategoria() {
		NuevaCategoria n = new NuevaCategoria(true);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Categoria categoria = n.getCategoria();
		if (categoria != null) {
			_categorias.addElement(categoria);
			Object[] categorias = new Object[_categorias.size()];
			_categorias.copyInto(categorias);
			_screen.addCategorias(categorias);
			_screen.selectCategoria(_categorias.indexOf(categoria));
		}
	}

	private void cerrarPantalla() {
		UiApplication.getUiApplication().popScreen(_screen);
	}

}
