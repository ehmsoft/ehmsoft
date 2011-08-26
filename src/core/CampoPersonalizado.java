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
	public String toString() {
		return getNombre();
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_atributo == null) ? 0 : id_atributo.hashCode());
		result = prime * result
				+ ((id_campo == null) ? 0 : id_campo.hashCode());
		result = prime * result + longitudMax;
		result = prime * result + longitudMin;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result
				+ ((obligatorio == null) ? 0 : obligatorio.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CampoPersonalizado))
			return false;
		CampoPersonalizado other = (CampoPersonalizado) obj;
		if (id_atributo == null) {
			if (other.id_atributo != null)
				return false;
		} else if (!id_atributo.equals(other.id_atributo))
			return false;
		if (id_campo == null) {
			if (other.id_campo != null)
				return false;
		} else if (!id_campo.equals(other.id_campo))
			return false;
		if (longitudMax != other.longitudMax)
			return false;
		if (longitudMin != other.longitudMin)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (obligatorio == null) {
			if (other.obligatorio != null)
				return false;
		} else if (!obligatorio.equals(other.obligatorio))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	
	
}
