package gui.Listados;

import gui.ListaScreen;

public class ListadoProcesosScreen extends ListaScreen {
		
	public ListadoProcesosScreen() {
		super();
		_lista = new ListadoProcesosLista();
		add(_lista);
	}
}