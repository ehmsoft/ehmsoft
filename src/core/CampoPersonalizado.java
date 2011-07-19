package core;

public class CampoPersonalizado {
	private String id_campo;
	private String id_atributo;
	private String nombre;
	private String valor;
	private Boolean obligatorio;
	private int longitudMax;
	private int longitudMin;
	//Constructores
	public CampoPersonalizado(String nombre, String valor, Boolean obligatorio,
			int longitudMax, int longitudMin) {
		this.id_campo = null;
		this.id_atributo = null;
		this.nombre = nombre;
		this.valor = valor;
		this.obligatorio = obligatorio;
		this.longitudMax = longitudMax;
		this.longitudMin = longitudMin;
	}
	public CampoPersonalizado(String id_campo, String nombre, String valor, Boolean obligatorio,
			int longitudMax, int longitudMin) {
		this.id_campo = id_campo;
		this.id_atributo = null;
		this.nombre = nombre;
		this.valor = valor;
		this.obligatorio = obligatorio;
		this.longitudMax = longitudMax;
		this.longitudMin = longitudMin;
	}
	public CampoPersonalizado(String id_campo,String id_atributo, String nombre, String valor, Boolean obligatorio,
			int longitudMax, int longitudMin) {
		this.id_campo = id_campo;
		this.id_atributo = id_atributo;
		this.nombre = nombre;
		this.valor = valor;
		this.obligatorio = obligatorio;
		this.longitudMax = longitudMax;
		this.longitudMin = longitudMin;
	}
	//Fin Constructores
	//Getters
	public String getNombre() {
		return nombre;
	}
	public String getValor() {
		return valor;
	}
	public Boolean isObligatorio() {
		return obligatorio;
	}
	public int getLongitudMax() {
		return longitudMax;
	}
	public int getLongitudMin() {
		return longitudMin;
	}
	public String getId_campo() {
		return id_campo;
	}
	public void setId_campo(String id_campo) {
		this.id_campo = id_campo;
	}
	public String getId_atributo() {
		return id_atributo;
	}
	public void setId_atributo(String id_atributo) {
		this.id_atributo = id_atributo;
	}
	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public void setObligatorio(Boolean obligatorio) {
		this.obligatorio = obligatorio;
	}
	public void setLongitudMax(int longitudMax) {
		this.longitudMax = longitudMax;
	}
	public void setLongitudMin(int longitudMin) {
		this.longitudMin = longitudMin;
	}
	

	
	
}
