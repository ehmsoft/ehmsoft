package gui;

public class Proceso {
	private String demandante;
	private String demandado;
	private String radicado;
	private String juzgado;
	
	public Proceso(String demandante, String demandado, String radicado, String juzgado)
	{
		this.demandante = demandante;
		this.demandado = demandado;
		this.radicado = radicado;
		this.juzgado = juzgado;
	}
	
	public String getDemandante()
	{
		return this.demandante;
	}
	
	public String getDemandado()
	{
		return this.demandado;
	}
	
	public String getRadicado()
	{
		return this.radicado;
	}
	
	public String getJuzgado()
	{
		return this.juzgado;
	}
	
}
