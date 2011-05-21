package core;

import java.util.Calendar;

public class Actuacion {
	private Juzgado juzgado;
	private Calendar fecha;
	private Calendar fechaProxima;
	private String descripcion;
	private String id_actuacion;
	//Constructores
	public Actuacion(Juzgado juzgado, Calendar fecha, Calendar fechaProxima,String descripcion) {
		this.juzgado = juzgado;
		this.fecha = fecha;
		this.fechaProxima = fechaProxima;
		this.descripcion = descripcion;
	}
	//Fin Constructores

	//Getters
	public Juzgado getJuzgado() {
		return juzgado;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public Calendar getFechaProxima() {
		return fechaProxima;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getId_actuacion() {
		return id_actuacion;
	}

	//Setters
	public void setId_actuacion(String id_actuacion) {
		this.id_actuacion = id_actuacion;
	}
	public void setJuzgado(Juzgado juzgado) {
		this.juzgado = juzgado;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public void setFechaProxima(Calendar fechaProxima) {
		this.fechaProxima = fechaProxima;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
