package gui.Listados;

import gui.ListaScreen;

public class ListadoActuacionesScreen extends ListaScreen {

	public ListadoActuacionesScreen() {
		super();
		_lista = new ListadoActuacionesLista();
		add(_lista);
	}
}
