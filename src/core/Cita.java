package core;

import java.util.Calendar;

public class Cita {
	private String id_cita;
	private Calendar fecha;
	private int anticipacion;
	private String id_actuacion;
	private String uid;
	private String descripcion;
	private Boolean alarma;
	
	// Constructores
	public Cita(String id_cita, Calendar fecha, int anticipacion,
			String id_actuacion, String uid, String descripcion, Boolean alarma) {
		this.id_cita = id_cita;
		this.fecha = fecha;
		this.anticipacion = anticipacion;
		this.id_actuacion = id_actuacion;
		this.uid = uid;
		this.descripcion = descripcion;
		this.alarma = alarma;
	}
	
	public Cita(String id_cita, Calendar fecha, int anticipacion,
			String id_actuacion, String descripcion, Boolean alarma) {
		this.id_cita = id_cita;
		this.fecha = fecha;
		this.anticipacion = anticipacion;
		this.id_actuacion = id_actuacion;
		this.descripcion = descripcion;
		this.alarma = alarma;
	}

	public Cita(String id_cita, Calendar fecha, String id_actuacion,
			String descripcion, Boolean alarma) {
		this.id_cita = id_cita;
		this.fecha = fecha;
		this.id_actuacion = id_actuacion;
		this.descripcion = descripcion;
		this.alarma = alarma;
	}

	public Cita(Calendar fecha, int anticipacion,
			String id_actuacion, String uid, String descripcion, Boolean alarma) {
		this.fecha = fecha;
		this.anticipacion = anticipacion;
		this.id_actuacion = id_actuacion;
		this.uid = uid;
		this.descripcion = descripcion;
		this.alarma = alarma;
	}

	// Fin Constructores
	// Getters
	public String getId_cita() {
		return id_cita;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public int getAnticipacion() {
		return anticipacion;
	}

	public String getId_actuacion() {
		return id_actuacion;
	}

	public String getUid() {
		return uid;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Boolean isAlarma() {
		return alarma;
	}

	
	//Setters
	public void setId_cita(String id_cita) {
		this.id_cita = id_cita;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public void setAnticipacion(int anticipacion) {
		this.anticipacion = anticipacion;
	}

	public void setId_actuacion(String id_actuacion) {
		this.id_actuacion = id_actuacion;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setAlarma(Boolean alarma) {
		this.alarma = alarma;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Cita))
			return false;
		Cita other = (Cita) obj;
		if (alarma == null) {
			if (other.alarma != null)
				return false;
		} else if (!alarma.equals(other.alarma))
			return false;
		if (anticipacion != other.anticipacion)
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
		if (id_actuacion == null) {
			if (other.id_actuacion != null)
				return false;
		} else if (!id_actuacion.equals(other.id_actuacion))
			return false;
		if (id_cita == null) {
			if (other.id_cita != null)
				return false;
		} else if (!id_cita.equals(other.id_cita))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}


	
}
