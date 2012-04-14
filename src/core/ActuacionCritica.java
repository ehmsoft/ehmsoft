package core;

import java.util.Calendar;

public class ActuacionCritica extends Actuacion {
	private String id_proceso;

	public ActuacionCritica(Juzgado juzgado, Calendar fecha,
			Calendar fechaProxima, String descripcion, String id_proceso) {
		super(juzgado, fecha, fechaProxima, descripcion);
		this.id_proceso = id_proceso;
	}

	public ActuacionCritica(Juzgado juzgado, Calendar fecha,
			Calendar fechaProxima, String descripcion, String id_actuacion,
			String id_proceso) {
		super(juzgado, fecha, fechaProxima, descripcion, id_actuacion);
		this.id_proceso = id_proceso;
	}

	public ActuacionCritica(Juzgado juzgado, Calendar fecha,
			Calendar fechaProxima, String descripcion, String id_actuacion,
			Cita cita, String id_proceso) {
		super(juzgado, fecha, fechaProxima, descripcion, id_actuacion,cita);
		this.id_proceso = id_proceso;
	}

	public ActuacionCritica(Actuacion actuacion, String id_proceso) {
		super(actuacion.getJuzgado(), actuacion.getFecha(), actuacion
				.getFechaProxima(), actuacion.getDescripcion(), actuacion
				.getId_actuacion(), actuacion.getCita());
		this.id_proceso = id_proceso;
	}

	public String getId_proceso() {
		return id_proceso;
	}

	// public void setId_proceso(String id_proceso) {
	// this.id_proceso = id_proceso;
	// }

}
