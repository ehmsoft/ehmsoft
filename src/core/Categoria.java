package core;

public class Categoria {
	private String id_categoria;
	private String descripcion;
	public Categoria(String id_categoria, String descripcion) {
		this.id_categoria = id_categoria;
		this.descripcion = descripcion;
	}
	public Categoria(String descripcion) {
		this.descripcion = descripcion;
	}
	public Categoria() {
		this.id_categoria = null;
		this.descripcion = null;
	}
	public String getId_categoria() {
		return id_categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setId_categoria(String id_categoria) {
		this.id_categoria = id_categoria;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
