package gui;

public class ListadoCamposScreen extends ListaScreen {
		
	public ListadoCamposScreen() {
		super();
		_lista = new ListadoCamposLista();
		add(_lista);
	}
}
