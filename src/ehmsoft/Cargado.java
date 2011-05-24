package ehmsoft;

import java.util.Vector;

import core.*;


<<<<<<< OURS
public interface Cargado {
	public Vector consultarDemandantes();
	public Vector consultarDemandados();
	public Vector consultarPersonas();
	public Persona consultarPersona(String id_persona);
	public Vector consultarProcesos();
	public Proceso consultarProceso(String id_proceso);
	public Vector consultarActuaciones(Proceso proceso);
	public Actuacion consultarActuacion(String id_actuacion);
	public Vector consultarJuzgados();
	public Juzgado consultarJuzgado(String id_juzgado);
=======
public interface Cargado {
	public Vector consultarPersonas() throws Exception;
	public Persona consultarPersona(String id_persona) throws Exception;
	public Vector consultarProcesos() throws Exception;
	public Proceso consultarProceso(String id_proceso) throws Exception;
	public Vector consultarActuaciones(Proceso proceso) throws Exception;
	public Actuacion consultarActuacion(String id_actuacion) throws Exception;
	public Vector consultarJuzgados() throws Exception;
	public Juzgado consultarJuzgado(String id_juzgado) throws Exception;
>>>>>>> THEIRS
	
}
