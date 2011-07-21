package ehmsoft;

import java.util.Vector;

import core.*;


public interface Cargado {
	public Vector consultarDemandantes() throws Exception;
	public Vector consultarDemandados() throws Exception;
	public Vector consultarPersonas() throws Exception;
	public Persona consultarPersona(String id_persona, int tipo) throws Exception;
	public Vector consultarProcesos() throws Exception;
	public Proceso consultarProceso(String id_proceso) throws Exception;
	public Vector consultarActuaciones(Proceso proceso) throws Exception;
	public Actuacion consultarActuacion(String id_actuacion) throws Exception;
	public Vector consultarJuzgados() throws Exception;
	public Juzgado consultarJuzgado(String id_juzgado) throws Exception;
	public Categoria consultarCategoria(String id_categoria) throws Exception;
	public Vector consultarCategorias() throws Exception;
	public Vector consultarCampos() throws Exception;
	public CampoPersonalizado consultarCampo(String id_campo)throws Exception;
	public Vector consultarAtributos() throws Exception;
}
