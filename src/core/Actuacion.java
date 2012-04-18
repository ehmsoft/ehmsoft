package core;

import java.util.Calendar;

public class Actuacion {
	private Juzgado juzgado;
	private Calendar fecha;
	private Calendar fechaProxima;
	private String descripcion;
	private String id_actuacion;
	private Cita cita;
	
	//Constructores
	public Actuacion(Juzgado juzgado, Calendar fecha, Calendar fechaProxima,
			String descripcion) {
		this.juzgado = juzgado;
		this.fecha = fecha;
		this.fechaProxima = fechaProxima;
		this.descripcion = descripcion;
	}
	public Actuacion(Juzgado juzgado, Calendar fecha, Calendar fechaProxima,
			String descripcion, String id_actuacion) {
		this.juzgado = juzgado;
		this.fecha = fecha;
		this.fechaProxima = fechaProxima;
		this.descripcion = descripcion;
		this.id_actuacion = id_actuacion;
	}
	public Actuacion(Juzgado juzgado, Calendar fecha, Calendar fechaProxima,
			String descripcion, String id_actuacion, Cita cita) {
		this.juzgado = juzgado;
		this.fecha = fecha;
		this.fechaProxima = fechaProxima;
		this.descripcion = descripcion;
		this.id_actuacion = id_actuacion;
		this.cita = cita;
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
	public Cita getCita() {
		return cita;
	}
	
	//Setters
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
	public void setId_actuacion(String id_actuacion) {
		this.id_actuacion = id_actuacion;
	}
	public void setCita(Cita cita) {
		this.cita = cita;
	}
	
	public String toString() {
		return descripcion;
	}	
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Actuacion))
			return false;
		Actuacion other = (Actuacion) obj;
		if (cita == null) {
			if (other.cita != null)
				return false;
		} else if (!cita.equals(other.cita))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (fechaProxima == null) {
			if (other.fechaProxima != null)
				return false;
		} else if (!fechaProxima.equals(other.fechaProxima))
			return false;
		if (id_actuacion == null) {
			if (other.id_actuacion != null)
				return false;
		} else if (!id_actuacion.equals(other.id_actuacion))
			return false;
		if (juzgado == null) {
			if (other.juzgado != null)
				return false;
		} else if (!juzgado.equals(other.juzgado))
			return false;
		return true;
	}
}
