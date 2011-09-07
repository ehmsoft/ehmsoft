package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import core.Preferencias;

public class PreferenciasGenerales {
	
	private PreferenciasGeneralesScreen _screen;

	public PreferenciasGenerales() {
		
		_screen = new PreferenciasGeneralesScreen();
		_screen.setNombreUsuario(Preferencias.getNombreUsuario());
		_screen.setMostrarBusqueda(Preferencias.isMostrarCampoBusqueda());
		_screen.setMostrarTitulos(Preferencias.isMostrarTitulosPantallas());
		_screen.setRecordarUltimaCategoria(Preferencias.isRecodarUltimaCategoria());
		_screen.setFuente(Preferencias.getTipoFuente());
		_screen.setChangeListener(listener);
	}
	
	private FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.GUARDAR) {
				guardarPreferencias();
			} else if(context == Util.COPIA_SEGURIDAD) {
				copiaDeSeguridad();
			} else if(context == Util.RESTAURAR_PREFERENCIAS) {
				restaurarPreferencias();
			}
		}
	};
	
	public PreferenciasGeneralesScreen getScreen() {
		return _screen;
	}
	
	private void guardarPreferencias() {
		Preferencias.setNombreUsuario(_screen.getNombreUsuario());
		Preferencias.setMostrarCampoBusqueda(_screen.isMostrarBusqueda());
		Preferencias.setMostrarTitulosPantallas(_screen.isMostrarTitulos());
		Preferencias.setRecodarUltimaCategoria(_screen.isRecordarUltimaCategoria());
		Preferencias.setTipoFuente(_screen.getFuente());
		Preferencias.setPantallaInicial(_screen.getPantallaInicial());
		Util.popScreen(_screen);
	}
	
	private void copiaDeSeguridad() {
		//TODO Acciones para realizar copia de seguridad
		Util.popScreen(_screen);
	}
	
	private void restaurarPreferencias() {
		//TODO Acciones para restaurar preferencias por defecto
		Util.popScreen(_screen);
	}
}
