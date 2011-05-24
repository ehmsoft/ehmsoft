package persistence;

import java.util.Vector;

import core.*;
import ehmsoft.Cargado;
import ehmsoft.Guardado;

public class Persistence implements Cargado, Guardado {
	private ConnectionManager connMgr;

	public void actualizarPersona(Persona persona) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void guardarPersona(Persona persona) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void borrarPersona(Persona persona) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void actualizarJuzgado(Juzgado juzgado) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void guardarJuzgado(Juzgado juzgado) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void borrarJuzgado(Juzgado juzgado) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void actualizarActuacion(Actuacion actuacion) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void guardarActuacion(Actuacion actuacion, String id_proceso)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void borrarActuacion(Actuacion actuacion) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void actualizarCampoPersonalizado(CampoPersonalizado campo)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void guardarCampoPersonalizado(CampoPersonalizado campo,
			String id_proceso) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void borrarCampoPersonalizado(CampoPersonalizado campo)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void actualizarProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void guardarProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void borrarProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Vector consultarDemandantes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarDemandados() {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarPersonas() {
		// TODO Auto-generated method stub
		return null;
	}

	public Persona consultarPersona(String id_persona) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarProcesos() {
		// TODO Auto-generated method stub
		return null;
	}

	public Proceso consultarProceso(String id_proceso) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarActuaciones(Proceso proceso) {
		// TODO Auto-generated method stub
		return null;
	}

	public Actuacion consultarActuacion(String id_actuacion) {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarJuzgados() {
		// TODO Auto-generated method stub
		return null;
	}

	public Juzgado consultarJuzgado(String id_juzgado) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
