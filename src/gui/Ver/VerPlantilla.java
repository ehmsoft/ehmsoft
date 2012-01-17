package gui.Ver;

import gui.Util;
import gui.Nuevos.NuevoProceso;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.util.CloneableVector;
import persistence.Persistence;
import core.CampoPersonalizado;
import core.Categoria;
import core.Juzgado;
import core.Persona;
import core.Plantilla;
import core.Preferencias;

public class VerPlantilla {

	private VerPlantillaScreen _screen;
	private Plantilla _plantilla;

	private Persona _demandante;
	private Persona _demandado;
	private Juzgado _juzgado;
	private Categoria _categoria;

	private Persona _demandanteVacio;
	private Persona _demandadoVacio;
	private Juzgado _juzgadoVacio;

	private Vector _campos;
	private Vector _camposNuevos;
	private Vector _camposEliminados;
	private Vector _camposOriginales;

	public VerPlantilla(Plantilla plantilla) {
		_camposNuevos = new Vector();
		_camposEliminados = new Vector();
		_plantilla = plantilla;
		_demandante = _plantilla.getDemandante();
		_demandado = _plantilla.getDemandado();
		_juzgado = _plantilla.getJuzgado();
		_categoria = _plantilla.getCategoria();

		try {
			_campos = new Persistence().consultarCamposPlantilla(_plantilla);
		} catch (NullPointerException e) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert(e.toString());
		}
		if (_campos != null) {
			_camposOriginales = CloneableVector.clone(_campos);
		}
		_plantilla.setCampos(_campos);

		_screen = new VerPlantillaScreen();
		_screen.setNombre(_plantilla.getNombre());
		_screen.setRadicado(_plantilla.getRadicado());
		_screen.setRadicadoUnico(_plantilla.getRadicadoUnico());
		_screen.setEstado(_plantilla.getEstado());
		_screen.setTipo(_plantilla.getTipo());
		_screen.setNotas(_plantilla.getNotas());
		_screen.setPrioridad(_plantilla.getPrioridad());
		_screen.setDemandante(_demandante.getNombre());
		_screen.setDemandado(_demandado.getNombre());
		_screen.setJuzgado(_juzgado.getNombre());
		_screen.setCategoria(_categoria.getDescripcion());
		if (_campos != null) {
			Enumeration e = _campos.elements();
			while (e.hasMoreElements()) {
				CampoPersonalizado campo = (CampoPersonalizado) e.nextElement();
				_screen.addCampo(campo, campo.getNombre(), campo.getValor(), campo.getLongitudMax());
			}
		}
		if(Preferencias.isMostrarTitulosPantallas()) {
			_screen.setTitle("Plantilla");
		}
		_screen.setChangeListener(listener);
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				actualizarPlantilla();
			} else if (context == Util.ADD_CAMPO) {
				addCampo();
			} else if (context == Util.ELIMINAR_DEMANDANTE) {
				eliminarDemandante();
			} else if (context == Util.ELIMINAR_DEMANDADO) {
				eliminarDemandado();
			} else if (context == Util.ELIMINAR_JUZGADO) {
				eliminarJuzgado();
			} else if (context == Util.VER_DEMANDANTE) {
				verDemandante();
			} else if (context == Util.VER_DEMANDADO) {
				verDemandado();
			} else if (context == Util.VER_JUZGADO) {
				verJuzgado();
			} else if (context == Util.VER_CATEGORIA) {
				verCategoria();
			} else if (context == Util.ADD_DEMANDANTE) {
				addDemandante();
			} else if (context == Util.ADD_DEMANDADO) {
				addDemandado();
			} else if (context == Util.ADD_JUZGADO) {
				addJuzgado();
			} else if (context == Util.ADD_CATEGORIA) {
				addCategoria();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.VER_CAMPO) {
				verCampo();
			} else if (context == Util.ELIMINAR_CAMPO) {
				eliminarCampo();
			} else if (context == Util.ELIMINAR) {
				eliminarPlantilla();
			} else if (context == Util.NEW_PROCESO) {
				nuevoProceso();
			}
		}
	};

	public Plantilla getPlantilla() {
		return _plantilla;
	}

	public VerPlantillaScreen getScreen() {
		return _screen;
	}

	private void actualizarPlantilla() {
		getValoresCampos();
		if (isCampoObligatorio()) {
		} else if (checkLong()) {
		} else if (_screen.isDirty()) {
			if (_camposNuevos.size() != 0) {
				guardarCamposNuevos();
			}
			if (_camposEliminados.size() != 0) {
				eliminarCampos();
			}
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					concatCampos();
					_plantilla.setNombre(_screen.getNombre());
					_plantilla.setDemandante(_demandante);
					_plantilla.setDemandado(_demandado);
					_plantilla.setJuzgado(_juzgado);
					_plantilla.setRadicado(_screen.getRadicado());
					_plantilla.setRadicadoUnico(_screen.getRadicadoUnico());
					_plantilla.setEstado(_screen.getEstado());
					_plantilla.setCategoria(_categoria);
					_plantilla.setTipo(_screen.getTipo());
					_plantilla.setNotas(_screen.getNotas());
					_plantilla.setCampos(_campos);
					_plantilla.setPrioridad(_screen.getPrioridad());
					try {
						new Persistence().actualizarPlantilla(_plantilla);
						if (cambioCampos()) {
							Persistence p = new Persistence();
							Enumeration e = _campos.elements();
							while (e.hasMoreElements()) {
								p.actualizarCampoPlantilla((CampoPersonalizado) e
										.nextElement());
							}
						}
					} catch (NullPointerException e1) {
						Util.noSd();
					} catch (DatabaseException e) {
						if (e.getMessage().equalsIgnoreCase(
								": constraint failed")) {
							Util.alert("Esta plantilla ya existe");
							_plantilla = Util.consultarPlantilla(_plantilla
									.getId_plantilla());
						}
					} catch (Exception e1) {
						Util.alert(e1.toString());
					} finally {
						Util.popScreen(_screen);
					}
				}
			});
		}
	}

	private void concatCampos() {
		Enumeration e = _camposNuevos.elements();
		while (e.hasMoreElements()) {
			_campos.addElement(e.nextElement());
		}
	}

	private boolean cambioCampos() {
		if (_campos.equals(_camposOriginales)) {
			return false;
		} else {
			return true;
		}
	}

	private void eliminarCampos() {
		Enumeration e = _camposEliminados.elements();
		while (e.hasMoreElements()) {
			try {
				new Persistence().borrarCampoPlantilla((CampoPersonalizado) e
						.nextElement());
			} catch (NullPointerException e1) {
				Util.noSd();
			} catch (Exception e1) {
				Util.alert(e1.toString());
			}
		}
	}

	private void guardarCamposNuevos() {
		Enumeration e = _camposNuevos.elements();
		while (e.hasMoreElements()) {
			try {
				new Persistence().guardarCampoPlantilla(
						(CampoPersonalizado) e.nextElement(),
						_plantilla.getId_plantilla());
			} catch (NullPointerException e1) {
				Util.noSd();
			} catch (Exception e1) {
				Util.alert(e1.toString());
			}
		}
	}

	private void addCampo() {
		CampoPersonalizado campo = Util.listadoCampos(true, 0);
		if (!_campos.contains(campo) && !_camposNuevos.contains(campo)) {
			if (campo != null) {
				if (_camposEliminados.contains(campo)) {
					_camposEliminados.removeElement(campo);
					if (_camposOriginales.contains(campo)) {
						_campos.addElement(campo);
					} else {
						_camposNuevos.addElement(campo);
					}
				} else {
					_camposNuevos.addElement(campo);
				}
				_screen.addCampo(campo, campo.getNombre(), "",
						campo.getLongitudMax());
				_screen.setDirty(true);
			}
		} else {
			Util.alert("Este campo personalizado ya está incluido en el proceso");
		}
	}

	private void verCampo() {
		CampoPersonalizado campo = (CampoPersonalizado) _screen
				.getCookieOfFocused();
		campo = Util.verCampo(campo);
		if (campo != null) {
			_screen.modificarCampo(campo, campo.getNombre(), campo.getLongitudMax());
			_screen.setDirty(true);
		} else {
			if (_campos.contains(campo)) {
				_campos.removeElement(campo);
			} else if (_camposNuevos.contains(campo)) {
				_camposNuevos.removeElement(campo);
			}
			_screen.eliminarCampo();
			_screen.setDirty(true);
		}
	}

	private void eliminarCampo() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delCampo(), 1);
		if (sel == 0) {
			CampoPersonalizado old = (CampoPersonalizado) _screen
					.getCookieOfFocused();
			_screen.eliminarCampo();
			if (_campos.contains(old)) {
				_campos.removeElement(old);
				if (!_camposEliminados.contains(old)) {
					_camposEliminados.addElement(old);
				}
			} else {
				_camposNuevos.removeElement(old);
			}
			_screen.setDirty(true);
		}
	}

	private boolean checkLong() {
		boolean ret = false;
		Enumeration e = _campos.elements();
		while (e.hasMoreElements()) {
			CampoPersonalizado campo = (CampoPersonalizado) e.nextElement();
			if (campo.getLongitudMin() > campo.getValor().length()
					&& campo.getLongitudMin() != 0) {
				Util.alert("El campo " + campo.getNombre()
						+ " posee una longitud minima de "
						+ campo.getLongitudMin()
						+ " caracteres, y usted ingresó "
						+ campo.getValor().length());
				ret = true;
			}
			if (campo.getLongitudMax() < campo.getValor().length()
					&& campo.getLongitudMax() != 0) {
				Util.alert("El campo " + campo.getNombre()
						+ " posee una longitud máxima de "
						+ campo.getLongitudMax()
						+ " caracteres, y usted ingresó "
						+ campo.getValor().length());
				ret = true;
			}
		}

		e = _camposNuevos.elements();
		while (e.hasMoreElements()) {
			CampoPersonalizado campo = (CampoPersonalizado) e.nextElement();
			if (campo.getLongitudMin() > campo.getValor().length()
					&& campo.getLongitudMin() != 0) {
				Util.alert("El campo " + campo.getNombre()
						+ "posee una longitud minima de "
						+ campo.getLongitudMin()
						+ " caracteres, y usted ingresó "
						+ campo.getValor().length());
				ret = true;
			}
			if (campo.getLongitudMax() < campo.getValor().length()
					&& campo.getLongitudMax() != 0) {
				Util.alert("El campo " + campo.getNombre()
						+ " posee una longitud máxima de "
						+ campo.getLongitudMax()
						+ " caracteres, y usted ingresó "
						+ campo.getValor().length());
				ret = true;
			}
		}
		return ret;
	}

	private boolean isCampoObligatorio() {
		boolean ret = false;
		Enumeration e = _campos.elements();
		while (e.hasMoreElements()) {
			CampoPersonalizado campo = (CampoPersonalizado) e.nextElement();
			if (campo.isObligatorio().booleanValue()
					&& campo.getValor().length() == 0) {
				Util.alert("El campo " + campo.getNombre() + " es obligatorio");
				ret = true;
			}
		}

		e = _camposNuevos.elements();
		while (e.hasMoreElements()) {
			CampoPersonalizado campo = (CampoPersonalizado) e.nextElement();
			if (campo.isObligatorio().booleanValue()
					&& campo.getValor().length() == 0) {
				Util.alert("El campo " + campo.getNombre() + " es obligatorio");
				ret = true;
			}
		}
		return ret;
	}

	private void getValoresCampos() {
		Object[][] campos = _screen.getCampos();

		for (int i = 0; i < campos[0].length; i++) {
			CampoPersonalizado campo = (CampoPersonalizado) campos[0][i];
			String valor = (String) campos[1][i];
			if (_campos.contains(campo)) {
				((CampoPersonalizado) _campos.elementAt(_campos.indexOf(campo)))
						.setValor(valor);
			} else if (_camposNuevos.contains(campo)) {
				((CampoPersonalizado) _camposNuevos.elementAt(_camposNuevos
						.indexOf(campo))).setValor(valor);
			}
		}
	}

	private void eliminarDemandante() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delPersona(), 1);
		if (sel == 0) {
			if (_demandanteVacio == null) {
				_demandanteVacio = Util.consultarPersonaVacia(1);
			}
			_demandante = null;
			_screen.setDemandante(_demandanteVacio.getNombre());
			_screen.setDirty(true);
		}
	}

	private void eliminarDemandado() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delPersona(), 1);
		if (sel == 0) {
			if (_demandadoVacio == null) {
				_demandadoVacio = Util.consultarPersonaVacia(2);
			}
			_demandado = null;
			_screen.setDemandado(_demandadoVacio.getNombre());
			_screen.setDirty(true);
		}
	}

	private void eliminarJuzgado() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delJuzgado(), 1);
		if (sel == 0) {
			if (_juzgadoVacio == null) {
				_juzgadoVacio = Util.consultarJuzgadoVacio();
			}
			_juzgado = null;
			_screen.setJuzgado(_juzgadoVacio.getNombre());
			_screen.setDirty(true);
		}
	}

	private void verDemandante() {
		if (!_demandante.getId_persona().equals("1") && _demandante != null) {
			Persona demandante = Util.verPersona(_demandante);
			if (demandante != null) {
				if (!demandante.equals(_demandante)) {
					_demandante = demandante;
					_screen.setDemandante(_demandante.getNombre());
					_screen.setDirty(true);
				}
			} else {
				if (_demandanteVacio == null) {
					_demandanteVacio = Util.consultarPersonaVacia(1);
				}
				_screen.setDemandante(_demandanteVacio.getNombre());
				_screen.setDirty(true);
			}
		} else {
			addDemandante();
		}
	}

	private void verDemandado() {
		if (!_demandado.getId_persona().equals("1") && _demandado != null) {
			Persona demandado = Util.verPersona(_demandado);
			if (demandado != null) {
				if (!demandado.equals(_demandado)) {
					_demandado = demandado;
					_screen.setDemandado(_demandado.getNombre());
					_screen.setDirty(true);
				}
			} else {
				if (_demandadoVacio == null) {
					_demandadoVacio = Util.consultarPersonaVacia(2);
				}
				_screen.setDemandado(_demandadoVacio.getNombre());
				_screen.setDirty(true);
			}
		} else {
			addDemandado();
		}
	}

	private void verJuzgado() {
		if (!_juzgado.getId_juzgado().equals("1") && _juzgado != null) {
			Juzgado juzgado = Util.verJuzgado(_juzgado);
			if (juzgado != null) {
				if (!juzgado.equals(_juzgado)) {
					_juzgado = juzgado;
					_screen.setJuzgado(_juzgado.getNombre());
					_screen.setDirty(true);
				}
			} else {
				if (_juzgadoVacio == null) {
					_juzgadoVacio = Util.consultarJuzgadoVacio();
				}
				_screen.setJuzgado(_juzgadoVacio.getNombre());
				_screen.setDirty(true);
			}
		} else {
			addJuzgado();
		}
	}

	private void verCategoria() {
		if (_categoria.getId_categoria() != "1") {
			Categoria categoria = Util.verCategoria(_categoria);
			if (_categoria != null) {
				if (!categoria.equals(_categoria)) {
					_categoria = categoria;
					_screen.setCategoria(_categoria.getDescripcion());
					_screen.setDirty(true);
				}
			}
		}
	}

	private void addDemandante() {
		Persona demandante = Util.listadoPersonas(1, true, 0);
		if (demandante != null) {
			if (!_demandante.equals(demandante)) {
				_demandante = demandante;
				_screen.setDemandante(_demandante.getNombre());
				_screen.setDirty(true);
			}
		}
	}

	private void addDemandado() {
		Persona demandado = Util.listadoPersonas(2, true, 0);
		if (demandado != null) {
			if (!_demandado.equals(demandado)) {
				_demandado = demandado;
				_screen.setDemandado(_demandado.getNombre());
				_screen.setDirty(true);
			}
		}
	}

	private void addJuzgado() {
		Juzgado juzgado = Util.listadoJuzgados(true, 0);
		if (juzgado != null) {
			if (!_juzgado.equals(juzgado)) {
				_juzgado = juzgado;
				_screen.setJuzgado(_juzgado.getNombre());
				_screen.setDirty(true);
			}
		}
	}

	private void addCategoria() {
		Categoria categoria = Util.listadoCategorias(true, 0);
		if (categoria != null) {
			if (!_categoria.equals(categoria)) {
				_categoria = categoria;
				_screen.setCategoria(_categoria.getDescripcion());
				_screen.setDirty(true);
			}
		}
	}

	private void eliminarPlantilla() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDPlantilla(), 1);
		if (sel == 0) {
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {

				public void run() {
					try {
						new Persistence().borrarPlantilla(_plantilla);
					} catch (NullPointerException e) {
						Util.noSd();
					} catch (Exception e) {
						Util.alert(e.toString());
					} finally {
						_plantilla = null;
						Util.popScreen(_screen);
					}
				}
			});
		}
	}

	private void nuevoProceso() {
		Plantilla plantilla = new Plantilla(_screen.getNombre(),
				_plantilla.getId_plantilla(), _demandante, _demandado,
				_juzgado, _screen.getRadicado(), _screen.getRadicadoUnico(),
				_screen.getEstado(), _categoria, _screen.getTipo(),
				_screen.getNotas(), _campos, _screen.getPrioridad());
		NuevoProceso n = new NuevoProceso(plantilla);
		Util.pushModalScreen(n.getScreen());
	}

	private void cerrarPantalla() {
		if (_screen.isDirty()) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarPlantilla();
			} else if (sel == 1) {
				Util.popScreen(_screen);
			}
		} else {
			Util.popScreen(_screen);
		}
	}
}