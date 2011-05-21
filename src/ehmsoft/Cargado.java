package ehmsoft;

import java.util.Vector;

import core.*;


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
	
}
