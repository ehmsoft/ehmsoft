package core;

public class Juzgado {
	private String nombre;
	private String ciudad;
	private String direccion;
	private String telefono;
	private String tipo;
	private String id_juzgado;

	// Constructores
	public Juzgado(String nombre, String ciudad, String direccion,
			String telefono, String tipo) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipo = tipo;
	}

	public Juzgado(String nombre, String ciudad, String direccion,
			String telefono, String tipo, String id_juzgado) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.tipo = tipo;
		this.id_juzgado = id_juzgado;
	}

	public Juzgado() {
		this.nombre = null;
		this.ciudad = null;
		this.direccion = null;
		this.telefono = null;
		this.tipo = null;
		this.id_juzgado = null;
	}

	// Fin Constructores
	// Getters
	public String getNombre() {
		return nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getTipo() {
		return tipo;
	}

	public String getId_juzgado() {
		return id_juzgado;
	}

	// Setters
	public void setId_juzgado(String id_juzgado) {
		this.id_juzgado = id_juzgado;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return getNombre();
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Juzgado other = (Juzgado) obj;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

}
