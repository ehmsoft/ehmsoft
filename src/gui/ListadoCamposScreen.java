package gui;

public class ListadoCamposScreen extends ListaScreen {
	
	private ListadoCamposLista _lista;
	
	public ListadoCamposScreen() {
		super();
		_lista = new ListadoCamposLista();
		add(_lista);
	}
}
