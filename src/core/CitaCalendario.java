package core;

import java.util.Calendar;

public class CitaCalendario {
	private String id_cita;
	private Calendar fecha;
	private int anticipacion;
	private String id_actuacion;
	private String uid;
	
	// Constructores
	public CitaCalendario(String id_cita, Calendar fecha, int anticipacion,
			String id_actuacion, String uid) {
		this.id_cita = id_cita;
		this.fecha = fecha;
		this.anticipacion = anticipacion;
		this.id_actuacion = id_actuacion;
		this.uid = uid;
	}

	public CitaCalendario(Calendar fecha, int anticipacion,
			String id_actuacion, String uid) {
		this.fecha = fecha;
		this.anticipacion = anticipacion;
		this.id_actuacion = id_actuacion;
		this.uid = uid;
	}

	public CitaCalendario(Calendar fecha, int anticipacion, String uid) {
		this.fecha = fecha;
		this.anticipacion = anticipacion;
		this.uid = uid;
	}

	public CitaCalendario(Calendar fecha, int anticipacion) {
		this.fecha = fecha;
		this.anticipacion = anticipacion;
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


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CitaCalendario))
			return false;
		CitaCalendario other = (CitaCalendario) obj;
		if (anticipacion != other.anticipacion)
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
