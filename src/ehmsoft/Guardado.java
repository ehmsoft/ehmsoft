package ehmsoft;

import core.*;

public interface Guardado {
	//Personas
	public void actualizarPersona(Persona persona);
	public void guardarPersona(Persona persona);
	public void borrarPersona(Persona persona);
	//Juzgados
	public void actualizarJuzgado(Juzgado juzgado);
	public void guardarJuzgado(Juzgado juzgado);
	public void borrarJuzgado(Juzgado juzgado);
	//Actuaciones
	public void actualizarActuacion(Actuacion actuacion);
	public void guardarActuacion(Actuacion actuacion);
	public void borrarActuacion(Actuacion actuacion);
	//Campos Personalizados
	public void actualizarCampoPersonalizado(CampoPersonalizado campo);
	public void guardarCampoPersonalizado(CampoPersonalizado campo);
	public void borrarCampoPersonalizado(CampoPersonalizado campo);
	//Procesos
	public void actualizarProceso(Proceso proceso);
	public void guardarProceso(Proceso proceso);
	public void borrarProceso(Proceso proceso);
}
