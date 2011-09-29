package core;

import net.rim.device.api.ui.Font;

public class Preferencias {
	
	
	private static short LISTADO_ACTUACIONES_MAIN = 20111;
	private static short LISTADO_ACTUACIONES_POP = 20112;
	private static short LISTADO_CAMPOS_MAIN = 20121;
	private static short LISTADO_CAMPOS_POP = 20122;
	private static short LISTADO_JUZGADOS_MAIN = 20131;	
	private static short LISTADO_JUZGADOS_POP = 20132;	
	private static short LISTADO_PERSONAS_MAIN = 20141;	
	private static short LISTADO_PERSONAS_POP = 20142;
	private static short LISTADO_PROCESOS_MAIN = 20151;
	private static short LISTADO_PROCESOS_POP = 20152;
	private static short LISTADO_CATEGORIAS_MAIN = 20171;
	private static short LISTADO_CATEGORIAS_POP = 20172;
	private static short LISTADO_DEMANDANTES_MAIN = 20181;	
	private static short LISTADO_DEMANDANTES_POP = 20182;
	private static short LISTADO_DEMANDADOS_MAIN = 20191;	
	private static short LISTADO_DEMANDADOS_POP = 20192;
	private static short NUEVA_ACTUACION_MAIN = 20211;
	private static short NUEVO_CAMPO_MAIN = 20221;
	private static short NUEVO_JUZGADO_MAIN = 20231;
	private static short NUEVA_PERSONA_MAIN	= 20241;
	private static short NUEVO_PROCESO_MAIN	= 20251;
	private static short NUEVA_CITA_POPUP = 20262;
	private static short NUEVA_CATEGORIA_MAIN = 20271;
	private static short NUEVA_CATEGORIA_POP = 20272;
	private static short VER_ACTUACION_MAIN = 20211;	
	private static short VER_CAMPO_MAIN	 = 20221;
	private static short VER_JUZGADO_MAIN = 20231;
	private static short VER_PERSONA_MAIN = 20241;
	private static short VER_PROCESO_MAIN = 20251;
	private static short VER_CITA_POP = 20262;
	private static short VER_CATEGORIA_MAIN = 20271;
	private static short PANTALLA_INICIAL_MAIN = 20001;
	
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
	public static int getLISTADO_ACTUACIONES_MAIN() {
		return LISTADO_ACTUACIONES_MAIN;
	}
	public static int getLISTADO_ACTUACIONES_POP() {
		return LISTADO_ACTUACIONES_POP;
	}
	public static int getLISTADO_CAMPOS_MAIN() {
		return LISTADO_CAMPOS_MAIN;
	}
	public static int getLISTADO_CAMPOS_POP() {
		return LISTADO_CAMPOS_POP;
	}
	public static int getLISTADO_JUZGADOS_MAIN() {
		return LISTADO_JUZGADOS_MAIN;
	}
	public static int getLISTADO_JUZGADOS_POP() {
		return LISTADO_JUZGADOS_POP;
	}
	public static int getLISTADO_PERSONAS_MAIN() {
		return LISTADO_PERSONAS_MAIN;
	}
	public static int getLISTADO_PERSONAS_POP() {
		return LISTADO_PERSONAS_POP;
	}
	public static int getLISTADO_PROCESOS_MAIN() {
		return LISTADO_PROCESOS_MAIN;
	}
	public static int getLISTADO_PROCESOS_POP() {
		return LISTADO_PROCESOS_POP;
	}
	public static int getLISTADO_CATEGORIAS_MAIN() {
		return LISTADO_CATEGORIAS_MAIN;
	}
	public static int getLISTADO_CATEGORIAS_POP() {
		return LISTADO_CATEGORIAS_POP;
	}
	public static int getNUEVA_ACTUACION_MAIN() {
		return NUEVA_ACTUACION_MAIN;
	}
	public static int getNUEVO_CAMPO_MAIN() {
		return NUEVO_CAMPO_MAIN;
	}
	public static int getNUEVO_JUZGADO_MAIN() {
		return NUEVO_JUZGADO_MAIN;
	}
	public static int getNUEVA_PERSONA_MAIN() {
		return NUEVA_PERSONA_MAIN;
	}
	public static int getNUEVO_PROCESO_MAIN() {
		return NUEVO_PROCESO_MAIN;
	}
	public static int getNUEVA_CITA_POPUP() {
		return NUEVA_CITA_POPUP;
	}
	public static int getNUEVA_CATEGORIA_MAIN() {
		return NUEVA_CATEGORIA_MAIN;
	}
	public static int getNUEVA_CATEGORIA_POP() {
		return NUEVA_CATEGORIA_POP;
	}
	public static int getVER_ACTUACION_MAIN() {
		return VER_ACTUACION_MAIN;
	}
	public static int getVER_CAMPO_MAIN() {
		return VER_CAMPO_MAIN;
	}
	public static int getVER_JUZGADO_MAIN() {
		return VER_JUZGADO_MAIN;
	}
	public static int getVER_PERSONA_MAIN() {
		return VER_PERSONA_MAIN;
	}
	public static int getVER_PROCESO_MAIN() {
		return VER_PROCESO_MAIN;
	}
	public static int getVER_CITA_POP() {
		return VER_CITA_POP;
	}
	public static int getVER_CATEGORIA_MAIN() {
		return VER_CATEGORIA_MAIN;
	}
	public static int getPANTALLA_INICIAL_MAIN() {
		return PANTALLA_INICIAL_MAIN;
	}
	public static int getLISTADO_DEMANDANTES_MAIN() {
		return LISTADO_DEMANDANTES_MAIN;
	}
	public static int getLISTADO_DEMANDANTES_POP() {
		return LISTADO_DEMANDANTES_POP;
	}
	public static int getLISTADO_DEMANDADOS_MAIN() {
		return LISTADO_DEMANDADOS_MAIN;
	}
	public static int getLISTADO_DEMANDADOS_POP() {
		return LISTADO_DEMANDADOS_POP;
	}
	public static void setLISTADO_DEMANDANTES_MAIN(int lISTADO_DEMANDANTES_MAIN) {
		LISTADO_DEMANDANTES_MAIN = (short)lISTADO_DEMANDANTES_MAIN;
	}
	public static void setLISTADO_DEMANDANTES_POP(int lISTADO_DEMANDANTES_POP) {
		LISTADO_DEMANDANTES_POP = (short)lISTADO_DEMANDANTES_POP;
	}
	public static void setLISTADO_DEMANDADOS_MAIN(int lISTADO_DEMANDADOS_MAIN) {
		LISTADO_DEMANDADOS_MAIN = (short)lISTADO_DEMANDADOS_MAIN;
	}
	public static void setLISTADO_DEMANDADOS_POP(int lISTADO_DEMANDADOS_POP) {
		LISTADO_DEMANDADOS_POP = (short)lISTADO_DEMANDADOS_POP;
	}
	public static void setLISTADO_ACTUACIONES_MAIN(int lISTADO_ACTUACIONES_MAIN) {
		LISTADO_ACTUACIONES_MAIN = (short)lISTADO_ACTUACIONES_MAIN;
	}
	public static void setLISTADO_ACTUACIONES_POP(int lISTADO_ACTUACIONES_POP) {
		LISTADO_ACTUACIONES_POP = (short)lISTADO_ACTUACIONES_POP;
	}
	public static void setLISTADO_CAMPOS_MAIN(int lISTADO_CAMPOS_MAIN) {
		LISTADO_CAMPOS_MAIN = (short)lISTADO_CAMPOS_MAIN;
	}
	public static void setLISTADO_CAMPOS_POP(int lISTADO_CAMPOS_POP) {
		LISTADO_CAMPOS_POP = (short)lISTADO_CAMPOS_POP;
	}
	public static void setLISTADO_JUZGADOS_MAIN(int lISTADO_JUZGADOS_MAIN) {
		LISTADO_JUZGADOS_MAIN = (short)lISTADO_JUZGADOS_MAIN;
	}
	public static void setLISTADO_JUZGADOS_POP(int lISTADO_JUZGADOS_POP) {
		LISTADO_JUZGADOS_POP = (short)lISTADO_JUZGADOS_POP;
	}
	public static void setLISTADO_PERSONAS_MAIN(int lISTADO_PERSONAS_MAIN) {
		LISTADO_PERSONAS_MAIN = (short)lISTADO_PERSONAS_MAIN;
	}
	public static void setLISTADO_PERSONAS_POP(int lISTADO_PERSONAS_POP) {
		LISTADO_PERSONAS_POP = (short)lISTADO_PERSONAS_POP;
	}
	public static void setLISTADO_PROCESOS_MAIN(int lISTADO_PROCESOS_MAIN) {
		LISTADO_PROCESOS_MAIN = (short)lISTADO_PROCESOS_MAIN;
	}
	public static void setLISTADO_PROCESOS_POP(int lISTADO_PROCESOS_POP) {
		LISTADO_PROCESOS_POP = (short)lISTADO_PROCESOS_POP;
	}
	public static void setLISTADO_CATEGORIAS_MAIN(int lISTADO_CATEGORIAS_MAIN) {
		LISTADO_CATEGORIAS_MAIN = (short)lISTADO_CATEGORIAS_MAIN;
	}
	public static void setLISTADO_CATEGORIAS_POP(int lISTADO_CATEGORIAS_POP) {
		LISTADO_CATEGORIAS_POP = (short)lISTADO_CATEGORIAS_POP;
	}
	public static void setNUEVA_ACTUACION_MAIN(int nUEVA_ACTUACION_MAIN) {
		NUEVA_ACTUACION_MAIN = (short)nUEVA_ACTUACION_MAIN;
	}
	public static void setNUEVO_CAMPO_MAIN(int nUEVO_CAMPO_MAIN) {
		NUEVO_CAMPO_MAIN = (short)nUEVO_CAMPO_MAIN;
	}
	public static void setNUEVO_JUZGADO_MAIN(int nUEVO_JUZGADO_MAIN) {
		NUEVO_JUZGADO_MAIN = (short)nUEVO_JUZGADO_MAIN;
	}
	public static void setNUEVA_PERSONA_MAIN(int nUEVA_PERSONA_MAIN) {
		NUEVA_PERSONA_MAIN = (short)nUEVA_PERSONA_MAIN;
	}
	public static void setNUEVO_PROCESO_MAIN(int nUEVO_PROCESO_MAIN) {
		NUEVO_PROCESO_MAIN = (short)nUEVO_PROCESO_MAIN;
	}
	public static void setNUEVA_CITA_POPUP(int nUEVA_CITA_POPUP) {
		NUEVA_CITA_POPUP = (short)nUEVA_CITA_POPUP;
	}
	public static void setNUEVA_CATEGORIA_MAIN(int nUEVA_CATEGORIA_MAIN) {
		NUEVA_CATEGORIA_MAIN = (short)nUEVA_CATEGORIA_MAIN;
	}
	public static void setNUEVA_CATEGORIA_POP(int nUEVA_CATEGORIA_POP) {
		NUEVA_CATEGORIA_POP = (short)nUEVA_CATEGORIA_POP;
	}
	public static void setVER_ACTUACION_MAIN(int vER_ACTUACION_MAIN) {
		VER_ACTUACION_MAIN = (short)vER_ACTUACION_MAIN;
	}
	public static void setVER_CAMPO_MAIN(int vER_CAMPO_MAIN) {
		VER_CAMPO_MAIN = (short)vER_CAMPO_MAIN;
	}
	public static void setVER_JUZGADO_MAIN(int vER_JUZGADO_MAIN) {
		VER_JUZGADO_MAIN = (short)vER_JUZGADO_MAIN;
	}
	public static void setVER_PERSONA_MAIN(int vER_PERSONA_MAIN) {
		VER_PERSONA_MAIN = (short)vER_PERSONA_MAIN;
	}
	public static void setVER_PROCESO_MAIN(int vER_PROCESO_MAIN) {
		VER_PROCESO_MAIN = (short)vER_PROCESO_MAIN;
	}
	public static void setVER_CITA_POP(int vER_CITA_POP) {
		VER_CITA_POP = (short)vER_CITA_POP;
	}
	public static void setVER_CATEGORIA_MAIN(int vER_CATEGORIA_MAIN) {
		VER_CATEGORIA_MAIN = (short)vER_CATEGORIA_MAIN;
	}
	public static void setPANTALLA_INICIAL_MAIN(int pANTALLA_INICIAL_MAIN) {
		PANTALLA_INICIAL_MAIN = (short)pANTALLA_INICIAL_MAIN;
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
