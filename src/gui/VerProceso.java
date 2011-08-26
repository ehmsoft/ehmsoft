package gui;

import java.util.Enumeration;
import java.util.Vector;

import persistence.Persistence;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import core.Actuacion;
import core.CampoPersonalizado;
import core.Categoria;
import core.Juzgado;
import core.Persona;
import core.Proceso;

public class VerProceso {
	
	private VerProcesoScreen _screen;
	private Proceso _proceso;
	
	private Persona _demandante;
	private Persona _demandado;
	private Juzgado _juzgado;
	private Categoria _categoria;
	
	private Persona _demandanteVacio;
	private Persona _demandadoVacio;
	private Juzgado _juzgadoVacio;
	private Categoria _categoriaVacia;
	
	private Vector _actuaciones;
	private Vector _campos;
	private Vector _camposNuevos;
	private Vector _camposEliminados;
	
	public VerProceso(Proceso proceso) {
		_camposNuevos = new Vector();
		_camposEliminados = new Vector();
		_proceso = proceso;
		_demandante = _proceso.getDemandante();
		_demandado = _proceso.getDemandado();
		_juzgado = _proceso.getJuzgado();
		_actuaciones = _proceso.getActuaciones();
		_campos = _proceso.getCampos();
		_categoria = _proceso.getCategoria();
		
		_screen = new VerProcesoScreen();
		_screen.setFecha(_proceso.getFecha().getTime());
		_screen.setRadicado(_proceso.getRadicado());
		_screen.setRadicadoUnico(_proceso.getRadicadoUnico());
		_screen.setEstado(_proceso.getEstado());
		_screen.setTipo(_proceso.getTipo());
		_screen.setNotas(_proceso.getNotas());
		_screen.setPrioridad(_proceso.getPrioridad());
		_screen.setDemandante(_demandante.getNombre());
		_screen.setDemandado(_demandado.getNombre());
		_screen.setJuzgado(_juzgado.getNombre());
		Object[] actuaciones = new Object[_actuaciones.size()];
		_actuaciones.copyInto(actuaciones);
		_screen.setActuaciones(actuaciones);
		_screen.setCategoria(_categoria.getDescripcion());
		if(_campos != null) {
			Enumeration e = _campos.elements();
			while(e.hasMoreElements()) {
				CampoPersonalizado campo = (CampoPersonalizado)e.nextElement();
				_screen.addCampo(campo, campo.getNombre(), campo.getValor());
			}
		}
		_screen.setChangeListener(listener);
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.GUARDAR) {
				actualizarProceso();
			} else if(context == Util.ADD_CAMPO) {
				addCampo();
			} else if(context == Util.ELIMINAR_DEMANDANTE) {
				eliminarDemandante();
			} else if(context == Util.ELIMINAR_DEMANDADO) {
				eliminarDemandado();
			} else if(context == Util.ELIMINAR_JUZGADO) {
				eliminarJuzgado();
			} else if(context == Util.VER_DEMANDANTE) {
				verDemandante();
			} else if(context == Util.VER_DEMANDADO) {
				verDemandado();
			} else if(context == Util.VER_JUZGADO) {
				verJuzgado();
			} else if(context == Util.VER_CATEGORIA) {
				verCategoria();
			} else if(context == Util.VER_ACTUACION) {
				verActuacion();
			} else if(context == Util.ADD_DEMANDANTE) {
				addDemandante();
			} else if(context == Util.ADD_DEMANDADO) {
				addDemandado();
			} else if(context == Util.ADD_JUZGADO) {
				addJuzgado();
			} else if(context == Util.ADD_CATEGORIA) {
				addCategoria();
			} else if(context == Util.NEW_ACTUACION) {
				newActuacion();
			} else if(context == Util.CERRAR) {
				cerrarPantalla();
			} else if(context == Util.VER_CAMPO) {
				verCampo();
			} else if(context == Util.ELIMINAR_CAMPO) {
				eliminarCampo();
			}
		}
	};

	public Proceso getProceso() {
		return _proceso;
	}

	public VerProcesoScreen getScreen() {
		return _screen;
	}
	
	private void actualizarProceso() {
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
			Proceso proceso = new Proceso(_demandante, _demandado, _screen.getFecha(),
					_juzgado, _screen.getRadicado(),
					_screen.getRadicadoUnico(), _actuaciones,
					_screen.getEstado(), (Categoria) _categoria,
					_screen.getTipo(), _screen.getNotas(), _campos,
					_screen.getPrioridad());
			if(_camposNuevos.size() != 0) {
				guardarCamposNuevos();
			}
			if(_camposEliminados.size() != 0) {
				eliminarCampos();
			}
			if(!proceso.equals(_proceso)) {
				try {
					new Persistence().actualizarProceso(proceso);
					_proceso = proceso;
				} catch(NullPointerException e1) {
					Util.noSd();
				} catch(Exception e1) {
					Util.alert(e1.toString());
				}
			}
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
	
	private void eliminarCampos() {
		Enumeration e = _camposEliminados.elements();
		while(e.hasMoreElements()) {
			try {
				new Persistence().borrarCampoPersonalizado((CampoPersonalizado) e.nextElement());
			} catch(NullPointerException e1) {
				Util.noSd();
			} catch(Exception e1) {
				Util.alert(e1.toString());
			}
		}
	}
	
	private void guardarCamposNuevos() {
		Enumeration e = _camposNuevos.elements();
		while(e.hasMoreElements()) {
			try {
				new Persistence().guardarCampoPersonalizado((CampoPersonalizado)e.nextElement(), _proceso.getId_proceso());
			} catch(NullPointerException e1) {
				Util.noSd();
			} catch(Exception e1) {
				Util.alert(e1.toString());
			}
		}
	}
	
	private void addCampo() {
		CampoPersonalizado campo;
		campo = Util.listadoCampos(true);
		if (campo != null) {
			_camposNuevos.addElement(campo);
			_screen.addCampo(campo, campo.getNombre(), "");
		}
	}
	
	private void verCampo() {
		CampoPersonalizado nw;
		CampoPersonalizado old = (CampoPersonalizado) _screen
				.getCookieOfFocused();
		nw = Util.verCampo(old);
		if (!old.equals(nw)) {
			int index = _campos.indexOf(old);
			_campos.removeElementAt(index);
			_campos.insertElementAt(nw, index);
			_screen.modificarCampo(nw, nw.getNombre());
		} else {
			_campos.removeElement(old);
			_screen.eliminarCampo();
		}
	}
	
	private void eliminarCampo() {
		Object[] ask = {"Aceptar", "Cancelar"};
		int sel = _screen.ask(ask, "¿Esta seguro que desea eliminar el campo del proceso?", 1);
		if(sel == 0) {
			CampoPersonalizado old = (CampoPersonalizado) _screen.getCookieOfFocused();
			_screen.eliminarCampo();
			if(_campos.contains(old)) {
				_campos.removeElement(old);
				if(!_camposEliminados.contains(old)) {
					_camposEliminados.addElement(old);
				}
			} else {
				_camposNuevos.removeElement(old);
			}
		}
	}
	
	private boolean isCampoObligatorio() {
		boolean ret = false;
		Enumeration e = _campos.elements();
		while(e.hasMoreElements()) {
			CampoPersonalizado campo = (CampoPersonalizado)e.nextElement();
			if(campo.isObligatorio().booleanValue() && campo.getValor().length() == 0) {
				_screen.alert("El campo " + campo.getNombre() + " es obligatorio");
				ret = true;
				break;
			}
		}
		
		e = _camposNuevos.elements();
		while(e.hasMoreElements()) {
			CampoPersonalizado campo = (CampoPersonalizado)e.nextElement();
			if(campo.isObligatorio().booleanValue() && campo.getValor().length() == 0) {
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
		for(int i = 0; i < _camposNuevos.size(); i++) {
			Object[] arrayCampo = _screen.getCampo(i + _campos.size());
			CampoPersonalizado campo = (CampoPersonalizado) arrayCampo[0];
			String valor = (String) arrayCampo[1];
			
			((CampoPersonalizado) _camposNuevos.elementAt(_camposNuevos.indexOf(campo)))
			.setValor(valor);
		}
	}
	
	private void eliminarDemandante() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen
				.ask(ask,
						"¿Está seguro que desea eliminar el demandante del proceso?",
						1);
		if (sel == 0) {
			if (_demandanteVacio == null) {
				_demandanteVacio = Util.consultarPersonaVacia(1);
			}
			_demandante = null;
			_screen.setDemandante(_demandanteVacio.getNombre());
		}
	}
	
	private void eliminarDemandado() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen
				.ask(ask,
						"¿Está seguro que desea eliminar el demandado del proceso?",
						1);
		if (sel == 0) {
			if (_demandadoVacio == null) {
				_demandadoVacio = Util.consultarPersonaVacia(2);
			}
			_demandado = null;
			_screen.setDemandado(_demandadoVacio.getNombre());
		}
	}
	
	private void eliminarJuzgado() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen
				.ask(ask,
						"¿Está seguro que desea eliminar el juzgado del proceso?",
						1);
		if (sel == 0) {
			if (_juzgadoVacio == null) {
				_juzgadoVacio = Util.consultarJuzgadoVacio();
			}
			_juzgado = null;
			_screen.setJuzgado(_juzgadoVacio.getNombre());
		}
	}
	
	private void verDemandante() {
		if(_demandante != null) {
			_demandante = Util.verPersona(_demandante);
			if(_demandante != null) {
				_screen.setDemandante(_demandante.getNombre());
			} else {
				if(_demandanteVacio == null) {
					_demandanteVacio = Util.consultarPersonaVacia(1);
				}
				_screen.setDemandante(_demandanteVacio.getNombre());
			}
		}
	}
	
	private void verDemandado() {
		if(_demandado != null) {
			_demandado = Util.verPersona(_demandado);
			if(_demandado != null) {
				_screen.setDemandado(_demandado.getNombre());
			} else {
				if(_demandadoVacio == null) {
					_demandadoVacio = Util.consultarPersonaVacia(2);
				}
				_screen.setDemandado(_demandadoVacio.getNombre());
			}
		}
	}
	
	private void verJuzgado() {
		if(_juzgado != null) {
			_juzgado = Util.verJuzgado(_juzgado);
			if(_juzgado != null) {
				_screen.setJuzgado(_juzgado.getNombre());
			} else {
				if(_juzgadoVacio == null) {
					_juzgadoVacio = Util.consultarJuzgadoVacio();
				}
				_screen.setJuzgado(_juzgadoVacio.getNombre());
			}
		}
	}
	
	private void verCategoria() {
		if(_categoria != null) {
			_categoria = Util.verCategoria(_categoria);
			if(_categoria != null) {
				_screen.setCategoria(_categoria.getDescripcion());
			} else {
				if(_categoriaVacia == null) {
					_categoriaVacia = Util.consultarCategoriaVacio();
				}
				_screen.setCategoria(_categoriaVacia.getDescripcion());
			}
		}
	}
	
	private void verActuacion() {
		Actuacion old = (Actuacion) _screen.getActuacion();
		Actuacion nw;
		if (old != null) {
			nw = Util.verActuacion(old);
			if (nw != null) {
				_actuaciones.removeElement(old);
				_actuaciones.addElement(nw);
			} else {
				_actuaciones.removeElement(old);
			}
			Object[] actuaciones = new Object[_actuaciones.size()];
			_actuaciones.copyInto(actuaciones);
			_screen.setActuaciones(actuaciones);
		}
	}
	
	private void addDemandante() {
		_demandante = Util.listadoPersonas(1, true);
		if(_demandante != null) {
			_screen.setDemandante(_demandante.getNombre());
		} else {
			if(_demandanteVacio == null) {
				_demandanteVacio = Util.consultarPersonaVacia(1);
			}
			_screen.setDemandante(_demandanteVacio.getNombre());
		}
	}
	
	private void addDemandado() {
		_demandado = Util.listadoPersonas(2, true);
		if(_demandado != null) {
			_screen.setDemandado(_demandado.getNombre());
		} else {
			if(_demandadoVacio == null) {
				_demandadoVacio = Util.consultarPersonaVacia(2);
			}
			_screen.setDemandado(_demandadoVacio.getNombre());
		}
	}
	
	private void addJuzgado() {
		_juzgado = Util.listadoJuzgados(true);
		if(_juzgado != null) {
			_screen.setJuzgado(_juzgado.getNombre());
		} else {
			if(_juzgadoVacio == null) {
				_juzgadoVacio = Util.consultarJuzgadoVacio();
			}
			_screen.setJuzgado(_juzgadoVacio.getNombre());
		}
	}
	
	private void addCategoria() {
		_categoria = Util.listadoCategorias(true);
		if(_categoria != null) {
			_screen.setCategoria(_categoria.getDescripcion());
		} else {
			if(_categoriaVacia == null) {
				_categoriaVacia = Util.consultarCategoriaVacio();
			}
			_screen.setCategoria(_categoriaVacia.getDescripcion());
		}
	}
	
	private void newActuacion() {
		Actuacion actuacion = Util.nuevaActuacion(_proceso);
		if(actuacion != null) {
			_actuaciones.addElement(actuacion);
			Object[] actuaciones = new Object[_actuaciones.size()];
			_actuaciones.copyInto(actuaciones);
			_screen.setActuaciones(actuaciones);
		}
	}
	
	private void cerrarPantalla() {
		UiApplication.getUiApplication().popScreen(_screen);
	}
}