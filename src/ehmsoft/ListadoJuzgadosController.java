package ehmsoft;

import net.rim.device.api.ui.UiApplication;
import core.Juzgado;
import gui.ListadoJuzgados;
import persistence.Persistence;

import java.util.Enumeration;
import java.util.Vector;

public class ListadoJuzgadosController {
	
	private Persistence _persistencia;
	private Object[] _juzgados;
	private Vector _vectorJuzgados;
	private ListadoJuzgados _screen;
	
	public ListadoJuzgadosController()
	{
		this._persistencia = new Persistence();
		
		this._vectorJuzgados = _persistencia.consultarJuzgados();

		if(this._vectorJuzgados == null)
			this._juzgados = new Object[0];
		else
		{
			this._juzgados = new Object[_vectorJuzgados.size()];
			transformarListado();
		}
		this._screen = new ListadoJuzgados(this._juzgados);
	}
	
	private void transformarListado()
	{
		int i = 0;
		
		Enumeration index = _vectorJuzgados.elements();
		
		while(index.hasMoreElements()) 
		{
			_juzgados[i] = index.nextElement();			
			i++;
		}
	}
	
	public Juzgado getSelected()
	{
		NuevoJuzgadoController nuevoJuzgado = new NuevoJuzgadoController();
		if(String.class.isInstance(_screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(nuevoJuzgado.getScreen());
			nuevoJuzgado.guardarJuzgado();
			_screen.addJuzgado(nuevoJuzgado.getJuzgado());
			return nuevoJuzgado.getJuzgado();
		}
		else
			return (Juzgado)_screen.getSelected();
	}
	
	public ListadoJuzgados getScreen()
	{
		return _screen;
	}
}
