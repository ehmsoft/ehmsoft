package ehmsoft;

import core.*;

public interface Guardado {
	//Personas
	public void actualizarPersona(Persona persona) throws Exception;
	public void guardarPersona(Persona persona) throws Exception;
	public void borrarPersona(Persona persona) throws Exception;
	//Juzgados
	public void actualizarJuzgado(Juzgado juzgado) throws Exception;
	public void guardarJuzgado(Juzgado juzgado) throws Exception;
	public void borrarJuzgado(Juzgado juzgado) throws Exception;
	//Actuaciones
	public void actualizarActuacion(Actuacion actuacion) throws Exception;
	public void guardarActuacion(Actuacion actuacion, String id_proceso) throws Exception;
	public void borrarActuacion(Actuacion actuacion) throws Exception;
	//Campos Personalizados
	public void actualizarCampoPersonalizado(CampoPersonalizado campo) throws Exception;
	public void guardarCampoPersonalizado(CampoPersonalizado campo, String id_proceso) throws Exception;
	public void borrarCampoPersonalizado(CampoPersonalizado campo) throws Exception;
	//Procesos
	public void actualizarProceso(Proceso proceso) throws Exception;
	public void guardarProceso(Proceso proceso) throws Exception;
	public void borrarProceso(Proceso proceso) throws Exception;
}
