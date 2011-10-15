package core;

import java.util.Enumeration;
import java.util.Vector;

public class Plantilla {
	private String nombre;
	private String id_plantilla;
	private Persona demandante;
	private Persona demandado;
	private Juzgado juzgado;
	private String radicado;
	private String radicadoUnico;
	private String estado;
	private Categoria categoria;
	private String tipo;
	private String notas;
	private Vector campos;
	private int prioridad;

	// Constructores
	public Plantilla(String nombre, Persona demandante, Persona demandado,
			Juzgado juzgado, String radicado, String radicadoUnico,
			String estado, Categoria categoria, String tipo, String notas,
			Vector campos, int prioridad) {
		this.nombre = nombre;
		this.demandante = demandante;
		this.demandado = demandado;
		this.juzgado = juzgado;
		this.radicado = radicado;
		this.radicadoUnico = radicadoUnico;
		this.estado = estado;
		this.categoria = categoria;
		this.tipo = tipo;
		this.notas = notas;
		this.campos = campos;
		this.prioridad = prioridad;
	}

	public Plantilla(String nombre, String id_plantilla, Persona demandante,
			Persona demandado, Juzgado juzgado, String radicado,
			String radicadoUnico, String estado, Categoria categoria,
			String tipo, String notas, Vector campos, int prioridad) {
		this.nombre = nombre;
		this.id_plantilla = id_plantilla;
		this.demandante = demandante;
		this.demandado = demandado;
		this.juzgado = juzgado;
		this.radicado = radicado;
		this.radicadoUnico = radicadoUnico;
		this.estado = estado;
		this.categoria = categoria;
		this.tipo = tipo;
		this.notas = notas;
		this.campos = campos;
		this.prioridad = prioridad;
	}

	// Fin Constructores
	/*
	 * public void addCampo(CampoPersonalizado campo){
	 * this.campos.addElement(campo); } public void delCampo(CampoPersonalizado
	 * campo){ this.campos.removeElement(campo); }
	 */
	// Getters
	public String getNombre() {
		return nombre;
	}

	public String getId_plantilla() {
		return id_plantilla;
	}

	public Persona getDemandante() {
		return demandante;
	}

	public Persona getDemandado() {
		return demandado;
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

	public String getEstado() {
		return estado;
	}

	public Categoria getCategoria() {
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

	// Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId_plantilla(String id_plantilla) {
		this.id_plantilla = id_plantilla;
	}

	public void setDemandante(Persona demandante) {
		this.demandante = demandante;
	}

	public void setDemandado(Persona demandado) {
		this.demandado = demandado;
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

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setCategoria(Categoria categoria) {
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

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plantilla other = (Plantilla) obj;
		if (campos == null) {
			if (other.campos != null)
				return false;
		} else {
			Enumeration e1 = campos.elements();
			Enumeration e2 = other.campos.elements();
			while (e1.hasMoreElements()) {
				while (e2.hasMoreElements()) {
					CampoPersonalizado a1 = (CampoPersonalizado) e1
							.nextElement();
					CampoPersonalizado a2 = (CampoPersonalizado) e2
							.nextElement();
					if (!a1.equals(a2)) {
						return false;
					}
				}
			}
		}
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (demandado == null) {
			if (other.demandado != null)
				return false;
		} else if (!demandado.equals(other.demandado))
			return false;
		if (demandante == null) {
			if (other.demandante != null)
				return false;
		} else if (!demandante.equals(other.demandante))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (id_plantilla == null) {
			if (other.id_plantilla != null)
				return false;
		} else if (!id_plantilla.equals(other.id_plantilla))
			return false;
		if (juzgado == null) {
			if (other.juzgado != null)
				return false;
		} else if (!juzgado.equals(other.juzgado))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (notas == null) {
			if (other.notas != null)
				return false;
		} else if (!notas.equals(other.notas))
			return false;
		if (prioridad != other.prioridad)
			return false;
		if (radicado == null) {
			if (other.radicado != null)
				return false;
		} else if (!radicado.equals(other.radicado))
			return false;
		if (radicadoUnico == null) {
			if (other.radicadoUnico != null)
				return false;
		} else if (!radicadoUnico.equals(other.radicadoUnico))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	public String toString() {
		return nombre;
	}

}
