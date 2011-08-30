package core;

import persistence.Persistence;
import net.rim.device.api.ui.Font;

public class Preferencias {
	private static boolean mostrarCampoBusqueda = true;
	private static Font tipoFuente = Font.getDefault();
	private static String nombreUsuario = "Usuario";
	private static boolean mostrarTitulosPantallas = true;
	private static boolean recodarUltimaCategoria = false;
	private static int pantallaInicial = Persistence.LISTADO_PROCESOS;
	private static int cantidadActuacionesCriticas = 10;
	
	//TODO Agregar las constantes respectivas
	
	public static boolean isMostrarCampoBusqueda() {
		return mostrarCampoBusqueda;
	}
	public static Font getTipoFuente() {
		return tipoFuente;
	}
	public static String getNombreUsuario() {
		return nombreUsuario;
	}
	public static boolean isMostrarTitulosPantallas() {
		return mostrarTitulosPantallas;
	}
	public static boolean isRecodarUltimaCategoria() {
		return recodarUltimaCategoria;
	}
	public static int getPantallaInicial() {
		return pantallaInicial;
	}
	public static int getCantidadActuacionesCriticas() {
		return cantidadActuacionesCriticas;
	}
	public static void setMostrarCampoBusqueda(boolean mostrarCampoBusqueda) {
		Preferencias.mostrarCampoBusqueda = mostrarCampoBusqueda;
	}
	public static void setTipoFuente(Font tipoFuente) {
		Preferencias.tipoFuente = tipoFuente;
	}
	public static void setNombreUsuario(String nombreUsuario) {
		Preferencias.nombreUsuario = nombreUsuario;
	}
	public static void setMostrarTitulosPantallas(boolean mostrarTitulosPantallas) {
		Preferencias.mostrarTitulosPantallas = mostrarTitulosPantallas;
	}
	public static void setRecodarUltimaCategoria(boolean recodarUltimaCategoria) {
		Preferencias.recodarUltimaCategoria = recodarUltimaCategoria;
	}
	public static void setPantallaInicial(int pantallaInicial) {
		Preferencias.pantallaInicial = pantallaInicial;
	}
	public static void setCantidadActuacionesCriticas(
			int cantidadActuacionesCriticas) {
		Preferencias.cantidadActuacionesCriticas = cantidadActuacionesCriticas;
	}	
}
