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
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
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
	
}