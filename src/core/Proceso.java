package core;

import java.util.Calendar;
import java.util.Vector;

public class Proceso {
	private String id_proceso;
	private Persona demandante;
	private Persona demandado;
	private Calendar fecha;
	private Juzgado juzgado;
	private String radicado;
	private String radicadoUnico;
	private Vector actuaciones;
	private String estado;
	private String categoria;
	private String tipo;
	private String notas;
	private Vector campos;
	private int prioridad;
	//Constructores
	public Proceso(Persona demandante, Persona demandado, Calendar fecha,
			Juzgado juzgado, String radicado, String radicadoUnico,
			Vector actuaciones, String estado, String categoria, String tipo,
			String notas, Vector campos, int prioridad) {
		this.demandante = demandante;
		this.demandado = demandado;
		this.fecha = fecha;
		this.juzgado = juzgado;
		this.radicado = radicado;
		this.radicadoUnico = radicadoUnico;
		this.actuaciones = actuaciones;
		this.estado = estado;
		this.categoria = categoria;
		this.tipo = tipo;
		this.notas = notas;
		this.campos = campos;
		this.prioridad = prioridad;
	}
	public Proceso(String id_proceso, Persona demandante, Persona demandado,
			Calendar fecha, Juzgado juzgado, String radicado,
			String radicadoUnico, Vector actuaciones, String estado,
			String categoria, String tipo, String notas, Vector campos,
			int prioridad) {
		this.id_proceso = id_proceso;
		this.demandante = demandante;
		this.demandado = demandado;
		this.fecha = fecha;
		this.juzgado = juzgado;
		this.radicado = radicado;
		this.radicadoUnico = radicadoUnico;
		this.actuaciones = actuaciones;
		this.estado = estado;
		this.categoria = categoria;
		this.tipo = tipo;
		this.notas = notas;
		this.campos = campos;
		this.prioridad = prioridad;
	}


	//Fin Constructores

	public void addActuacion(Actuacion act){
		this.actuaciones.addElement(act);
	}
	public void delActuacion(Actuacion act){
		this.actuaciones.removeElement(act);
	}
	public void addCampo(CampoPersonalizado campo){
		this.campos.addElement(campo);
	}
	public void delCampo(CampoPersonalizado campo){
		this.campos.removeElement(campo);
	}
	//Getters
	public Persona getDemandante() {
		return demandante;
	}
	public Persona getDemandado() {
		return demandado;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public Juzgado getJuzgado() {
		return juzgado;
	}
	public String getRadicado() {
		return radicado;
	}
	public String getRadicadoUnico() {
		return radicadoUnico;
	}
	public Vector getActuaciones() {
		return actuaciones;
	}
	public String getEstado() {
		return estado;
	}
	public String getCategoria() {
		return categoria;
	}
	public String getTipo() {
		return tipo;
	}
	public String getNotas() {
		return notas;
	}
	public Vector getCampos() {
		return campos;
	}
	public int getPrioridad() {
		return prioridad;
	}
	
	public String getId_proceso() {
		return id_proceso;
	}

	//Setters
	public void setId_proceso(String id_proceso) {
		this.id_proceso = id_proceso;
	}
	public void setDemandante(Persona demandante) {
		this.demandante = demandante;
	}
	public void setDemandado(Persona demandado) {
		this.demandado = demandado;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public void setJuzgado(Juzgado juzgado) {
		this.juzgado = juzgado;
	}
	public void setRadicado(String radicado) {
		this.radicado = radicado;
	}
	public void setRadicadoUnico(String radicadoUnico) {
		this.radicadoUnico = radicadoUnico;
	}
	public void setActuaciones(Vector actuaciones) {
		this.actuaciones = actuaciones;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public void setCampos(Vector campos) {
		this.campos = campos;
	}
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
}
