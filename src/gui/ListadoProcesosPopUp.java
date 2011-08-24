package gui;

public class ListadoProcesosPopUp extends ListaPopUp{
	
	private ListadoProcesosLista _lista;
	
	public ListadoProcesosPopUp() {
		super();
		_lista = new ListadoProcesosLista();
		add(_lista);
	}
}
