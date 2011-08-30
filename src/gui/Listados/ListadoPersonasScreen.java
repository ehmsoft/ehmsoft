package gui.Listados;

import gui.ListaScreen;

public class ListadoPersonasScreen extends ListaScreen {
		
	public ListadoPersonasScreen() {
		super();
		_lista = new ListadoPersonasLista();
		add(_lista);
	}
}
