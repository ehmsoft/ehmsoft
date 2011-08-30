package gui.Listados;

import gui.ListaScreen;

public class ListadoCamposScreen extends ListaScreen {
		
	public ListadoCamposScreen() {
		super();
		_lista = new ListadoCamposLista();
		add(_lista);
	}
}
