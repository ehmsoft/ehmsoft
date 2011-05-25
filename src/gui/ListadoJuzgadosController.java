package gui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import core.Juzgado;
import persistence.Persistence;

import java.util.Enumeration;
import java.util.Vector;

public class ListadoJuzgadosController {
	
	private Persistence _persistencia;
	private Vector _vectorJuzgados;
	private ListadoJuzgados _screen;
	
	public ListadoJuzgadosController()
	{
		_persistencia = new Persistence();		
		_vectorJuzgados = _persistencia.consultarJuzgados();
		_screen = new ListadoJuzgados();
		addJuzgados();
	}
	
	private void addJuzgados()
	{
		Enumeration index;
		try{
		index = _vectorJuzgados.elements();
		
		while(index.hasMoreElements()) 
		 _screen.addJuzgado(index.nextElement());	
		}catch(NullPointerException e){
			
		}
		catch(Exception e){
			Dialog.alert(e.toString());
		}
	}
	
	public void setVectorJuzgados(Vector juzgados){
		_vectorJuzgados = juzgados;
		addJuzgados();
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
