package core;

import net.rim.device.api.ui.Font;

public class Preferencias {
	
	
	public final static short LISTADO_ACTUACIONES_MAIN = 20111;
	public final static short LISTADO_ACTUACIONES_POP = 20112;
	public final static short LISTADO_CAMPOS_MAIN = 20121;
	public final static short LISTADO_CAMPOS_POP = 20122;
	public final static short LISTADO_JUZGADOS_MAIN = 20131;	
	public final static short LISTADO_JUZGADOS_POP = 20132;	
	public final static short LISTADO_PERSONAS_MAIN = 20141;	
	public final static short LISTADO_PERSONAS_POP = 20142;
	public final static short LISTADO_PROCESOS_MAIN = 20151;
	public final static short LISTADO_PROCESOS_POP = 20152;
	public final static short LISTADO_CATEGORIAS_MAIN = 20171;
	public final static short LISTADO_CATEGORIAS_POP = 20172;
	public final static short LISTADO_DEMANDANTES_MAIN = 20181;	
	public final static short LISTADO_DEMANDANTES_POP = 20182;
	public final static short LISTADO_DEMANDADOS_MAIN = 20191;	
	public final static short LISTADO_DEMANDADOS_POP = 20192;
	public final static short NUEVA_ACTUACION_MAIN = 20211;
	public final static short NUEVO_CAMPO_MAIN = 20221;
	public final static short NUEVO_JUZGADO_MAIN = 20231;
	public final static short NUEVA_PERSONA_MAIN	= 20241;
	public final static short NUEVO_PROCESO_MAIN	= 20251;
	public final static short NUEVA_CITA_POPUP = 20262;
	public final static short NUEVA_CATEGORIA_MAIN = 20271;
	public final static short NUEVA_CATEGORIA_POP = 20272;
	public final static short VER_ACTUACION_MAIN = 20211;	
	public final static short VER_CAMPO_MAIN	 = 20221;
	public final static short VER_JUZGADO_MAIN = 20231;
	public final static short VER_PERSONA_MAIN = 20241;
	public final static short VER_PROCESO_MAIN = 20251;
	public final static short VER_CITA_POP = 20262;
	public final static short VER_CATEGORIA_MAIN = 20271;
	public final static short PANTALLA_INICIAL_MAIN = 20001;
	
	private static boolean mostrarCampoBusqueda = true;
	private static Font tipoFuente = Font.getDefault();
	private static String nombreUsuario = "Usuario";
	private static boolean mostrarTitulosPantallas = true;
	private static boolean recodarUltimaCategoria = false;
	private static short pantallaInicial = PANTALLA_INICIAL_MAIN;
	private static short cantidadActuacionesCriticas = 10;
	
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
	public static int getPANTALLA_INICIAL_MAIN() {
		return PANTALLA_INICIAL_MAIN;
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
		Preferencias.pantallaInicial = (short)pantallaInicial;
	}
	public static void setCantidadActuacionesCriticas(
			int cantidadActuacionesCriticas) {
		Preferencias.cantidadActuacionesCriticas = (short)cantidadActuacionesCriticas;
	}	
}
