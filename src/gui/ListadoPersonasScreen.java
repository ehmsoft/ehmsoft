package gui;

public class ListadoPersonasScreen extends ListaScreen {
	
	private ListadoPersonasLista _lista;
	
	public ListadoPersonasScreen() {
		super();
		_lista = new ListadoPersonasLista();
		add(_lista);
	}
}
