package gui;

public class ListadoProcesosScreen extends ListaScreen {
	
	private ListadoProcesosLista _lista;
	
	public ListadoProcesosScreen() {
		super();
		_lista = new ListadoProcesosLista();
		add(_lista);
	}
}