package gui.Nuevos;

import gui.Util;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import persistence.Persistence;
import core.CampoPersonalizado;
import core.Categoria;
import core.Juzgado;
import core.Persona;
import core.Plantilla;

public class NuevaPlantilla {

	private Plantilla _plantilla;
	private NuevaPlantillaScreen _screen;

	private Persona _demandante;
	private Persona _demandado;
	private Juzgado _juzgado;
	private Vector _campos;
	private Vector _categorias;

	private Persona _demandanteVacio;
	private Persona _demandadoVacio;
	private Juzgado _juzgadoVacio;

	/**
	 * Se crea un NuevoProceso y le asocia una pantalla, con este objeto se
	 * crearan nuevos Procesos
	 */
	public NuevaPlantilla() {
		_campos = new Vector();
		_demandanteVacio = Util.consultarPersonaVacia(1);
		_demandadoVacio = Util.consultarPersonaVacia(2);
		_juzgadoVacio = Util.consultarJuzgadoVacio();
		_screen = new NuevaPlantillaScreen();

		try {
			Persistence p = new Persistence();

			_categorias = p.consultarCategorias();
			Categoria ninguna = p.consultarCategoria("1");
			_categorias.removeElement(ninguna);
			_categorias.insertElementAt(ninguna, 0);
		} catch (NullPointerException e) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert(e.toString());
		}

		Object[] categorias = new Object[_categorias.size()];
		_categorias.copyInto(categorias);
		_screen.addCategorias(categorias);
		_screen.setDemandante(_demandanteVacio.getNombre());
		_screen.setDemandado(_demandadoVacio.getNombre());
		_screen.setJuzgado(_juzgadoVacio.getNombre());
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
			} else if (context == Util.ELIMINAR_DEMANDANTE) {
				eliminarDemandante();
			} else if (context == Util.ELIMINAR_DEMANDADO) {
				eliminarDemandado();
			} else if (context == Util.ELIMINAR_JUZGADO) {
				eliminarJuzgado();
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
			} else if (context == Util.VER_CAMPO) {
				verCampo();
			}
		}
	};

	public Plantilla getPlantilla() {
		return _plantilla;
	}

	/**
	 * @return La pantalla asociada al objeto
	 */
	public NuevaPlantillaScreen getScreen() {
		return _screen;
	}

	/**
	 * Se crea el nuevo Proceso, basado en los datos capturados por la pantalla
	 * y se guarda en la base de datos
	 */
	private void guardarProceso() {
		getValoresCampos();
		if (_screen.getNombre().equals("")) {
			Util.alert("El campo Nombre es obligatorio");
		} else if (isCampoObligatorio()) {
		} else if (checkLonMin()) {
		} else {
			if (_demandante == null) {
				_demandante = _demandanteVacio;
			}
			if (_demandado == null) {
				_demandado = _demandadoVacio;
			}
			if (_juzgado == null) {
				_juzgado = _juzgadoVacio;
			}
			_plantilla = new Plantilla(_screen.getNombre(), _demandante,
					_demandado, _juzgado, _screen.getRadicado(),
					_screen.getRadicadoUnico(), _screen.getEstado(),
					(Categoria) _screen.getCategoria(), _screen.getTipo(),
					_screen.getNotas(), _campos, _screen.getPrioridad());
			try {
				new Persistence().guardarPlantilla(_plantilla);
			} catch (NullPointerException e) {
				Util.noSd();
			} catch (Exception e) {
				Util.alert(e.toString());
			}
			Util.popScreen(_screen);
		}
	}

	private boolean checkLonMin() {
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
				break;
			}
		}
		return ret;
	}

	private void getValoresCampos() {

		Object[][] campos = _screen.getCampos();

		for (int i = 0; i < campos[0].length; i++) {
			CampoPersonalizado campo = (CampoPersonalizado) campos[0][i];
			String valor = (String) campos[1][i];
			((CampoPersonalizado) _campos.elementAt(_campos.indexOf(campo)))
					.setValor(valor);
		}
	}

	private void addDemandante() {
		Persona demandante = Util.listadoPersonas(1, true, 0);
		if (demandante != null) {
			_demandante = demandante;
			_screen.setDemandante(_demandante.getNombre());
		}
	}

	private void addDemandado() {
		Persona demandado = Util.listadoPersonas(2, true, 0);
		if (demandado != null) {
			_demandado = demandado;
			_screen.setDemandado(_demandado.getNombre());
		}
	}

	private void addJuzgado() {
		Juzgado juzgado = Util.listadoJuzgados(true, 0);
		if (juzgado != null) {
			_juzgado = juzgado;
			_screen.setJuzgado(_juzgado.getNombre());
		}
	}

	private void eliminarDemandante() {
		_demandante = null;
		_screen.setDemandante(_demandanteVacio.getNombre());
	}

	private void eliminarDemandado() {
		_demandado = null;
		_screen.setDemandado(_demandadoVacio.getNombre());
	}

	private void eliminarJuzgado() {
		_juzgado = null;
		_screen.setJuzgado(_juzgadoVacio.getNombre());
	}

	private void addCategoria() {
		Categoria categoria = Util.listadoCategorias(true, 0);
		if (categoria != null) {
			_categorias.addElement(categoria);
			Object[] categorias = new Object[_categorias.size()];
			_categorias.copyInto(categorias);
			_screen.addCategorias(categorias);
			_screen.selectCategoria(_categorias.indexOf(categoria));
		}
	}

	private void addCampo() {
		CampoPersonalizado campo = Util.listadoCampos(true, 0);
		if (campo != null) {
			_campos.addElement(campo);
			_screen.addCampo(campo, campo.getNombre());
		}
	}

	private void eliminarCampo() {
		_campos.removeElement(_screen.getFocused());
		_screen.eliminarCampo();
	}

	private void newCategoria() {
		Categoria categoria = Util.nuevaCategoria(true);
		if (categoria != null) {
			_categorias.addElement(categoria);
			Object[] categorias = new Object[_categorias.size()];
			_categorias.copyInto(categorias);
			_screen.addCategorias(categorias);
			_screen.selectCategoria(_categorias.indexOf(categoria));
		}
	}

	private void verCampo() {
		CampoPersonalizado nw;
		CampoPersonalizado old = (CampoPersonalizado) _screen.getFocused();
		nw = Util.verCampo(old);
		if (nw == null) {
			eliminarCampo();
		} else if (!old.equals(nw)) {
			if (_campos.contains(old)) {
				int index = _campos.indexOf(old);
				_campos.removeElementAt(index);
				_campos.insertElementAt(nw, index);
				_screen.modificarCampo(nw, nw.getNombre());
			}
		}
	}

	private void cerrarPantalla() {
		if (_campos.size() != 0 || _demandante != null || _demandado != null
				|| _juzgado != null) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 1) {
				guardarProceso();
			} else if (sel == 2) {
				Util.popScreen(_screen);
			}
		} else {
			Util.popScreen(_screen);
		}
	}
}
