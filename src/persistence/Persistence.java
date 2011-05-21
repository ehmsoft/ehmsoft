package persistence;

import java.util.Vector;

import core.*;
import ehmsoft.Cargado;
import ehmsoft.Guardado;

public class Persistence implements Cargado, Guardado {
	private ConnectionManager connMgr;
	public void actualizarPersona(Persona persona) {
		// TODO Aquí se actualiza una persona

	}

	public void guardarPersona(Persona persona) {
		// TODO Aquí se guarda una persona

	}

	public void borrarPersona(Persona persona) {
		// TODO Aquí se borra una persona

	}

	public void actualizarJuzgado(Juzgado juzgado) {
		// TODO Se actualiza un juzado

	}

	public void guardarJuzgado(Juzgado juzgado) {
		// TODO Se guarda un juzgado

	}

	public void borrarJuzgado(Juzgado juzgado) {
		// TODO Se borra un juzgado

	}

	public void actualizarActuacion(Actuacion actuacion) {
		// TODO Se actualiza una actuación

	}

	public void guardarActuacion(Actuacion actuacion) {
		// TODO Se guarda un actuación

	}

	public void borrarActuacion(Actuacion actuacion) {
		// TODO Se borra una actuación

	}

	public void actualizarCampoPersonalizado(CampoPersonalizado campo) {
		// TODO Se actualiza un campo personalizado

	}

	public void guardarCampoPersonalizado(CampoPersonalizado campo) {
		// TODO se guarda un campo personalizado

	}

	public void borrarCampoPersonalizado(CampoPersonalizado campo) {
		// TODO se borra un campo personalizado

	}

	public void actualizarProceso(Proceso proceso) {
		// TODO Se actualiza Proceso

	}

	public void guardarProceso(Proceso proceso) {
		// TODO Se guarda Proceso

	}

	public void borrarProceso(Proceso proceso) {
		// TODO Se borra un proceso

	}

	public Vector consultarPersonas() {
		// TODO Se consulta la lista de personas
		return null;
	}

	public Persona consultarPersona(String id_persona) {
		// TODO Se consulta una persona
		return null;
	}

	public Vector consultarProcesos() {
		// TODO Se consulta la lista de procesos
		return null;
	}

	public Proceso consultarProceso(String id_proceso) {
		// TODO se consulta un proceso
		return null;
	}

	public Vector consultarActuaciones(Proceso proceso) {
		// TODO Se consulta la lista de Actuaciones
		return null;
	}

	public Actuacion consultarActuacion(String id_actuacion) {
		// TODO Se consulta una actuacion
		return null;
	}

	public Vector consultarJuzgados() {
		// TODO Se consulta la lista de  Juzgados
		return null;
	}

	public Juzgado consultarJuzgado(String id_juzgado) {
		// TODO Se consulta un Juzgado
		return null;
	}

	public Vector consultarDemandantes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarDemandados() {
		// TODO Auto-generated method stub
		return null;
	}

}
