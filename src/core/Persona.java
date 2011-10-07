package core;

public class Persona {
	private int tipo;
	private String id;
	private String nombre;
	private String telefono;
	private String direccion;
	private String correo;
	private String notas;
	private String id_persona;
	
	//Constructores
	/**
	 * @param tipo	1 para demandante, 2 para demandado
	 * @param id	Cédula o Nit
	 * @param nombre
	 * @param telefono
	 * @param direccion
	 * @param correo
	 * @param notas
	 */
	public Persona(int tipo, String id, String nombre, String telefono,String direccion, String correo, String notas) {// Constructor básico
		this.tipo = tipo;
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.notas = notas;
	}
	public Persona(int tipo, String id, String nombre, String telefono,
			String direccion, String correo, String notas, String id_persona) {
		this.tipo = tipo;
		this.id = id;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.notas = notas;
		this.id_persona = id_persona;
	}
	public Persona(int tipo) {
		this.tipo = tipo;
		this.id = null;
		this.nombre = null;
		this.telefono = null;
		this.direccion = null;
		this.correo = null;
		this.notas = null;
		this.id_persona = null;
	}
	//Fin Constructores
	
	//Getters
	public int getTipo() {
		return tipo;
	}
	public String getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public String getNotas() {
		return notas;
	}
	public String getId_persona() {
		return id_persona;
	}
	
	//Setters
	public void setId_persona(String id_persona) {
		this.id_persona = id_persona;
	}
//	public void setTipo(int tipo) {
//		this.tipo = tipo;
//	}
	public void setId(String id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public void setNotas(String notas) {
		this.notas = notas;
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
		Persona other = (Persona) obj;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
}