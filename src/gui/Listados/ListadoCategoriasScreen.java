package gui.Listados;

import gui.ListaScreen;

public class ListadoCategoriasScreen extends ListaScreen {
		
	public ListadoCategoriasScreen() {
		super();
		_lista = new ListadoCategoriasLista();
		add(_lista);
	}
}
