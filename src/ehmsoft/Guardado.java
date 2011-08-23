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
	public void actualizarAtributo(CampoPersonalizado campo) throws Exception;
	public void guardarAtributo(CampoPersonalizado campo) throws Exception;
	public void borrarAtributo(CampoPersonalizado campo) throws Exception;
	//Procesos
	public void actualizarProceso(Proceso proceso) throws Exception;
	public void guardarProceso(Proceso proceso) throws Exception;
	public void borrarProceso(Proceso proceso) throws Exception;
	//Categorias
	public void actualizarCategoria(Categoria categoria) throws Exception;
	public void guardarCategoria(Categoria categoria) throws Exception;
	public void borrarCategoria(Categoria categoria) throws Exception;
	//Plantillas
	public void actualizarPlantilla(Plantilla plantilla) throws Exception;
	public void guardarPlantilla(Plantilla plantilla) throws Exception;
	public void borrarPlantilla(Plantilla plantilla) throws Exception;
	//Campos Personalizados de las plantillas
	public void actualizarCampoPlantilla(CampoPersonalizado campo) throws Exception;
	public void guardarCampoPlantilla(CampoPersonalizado campo, String id_proceso) throws Exception;
	public void borrarCampoPlantilla(CampoPersonalizado campo) throws Exception;
	//preferencias
	public void actualizarPreferencia(int id_preferencia,String valor) throws Exception;
	public void borrarPreferencia(int id_preferencia) throws Exception;
	//Log
	public void log(String descripcion) throws Exception;
}
