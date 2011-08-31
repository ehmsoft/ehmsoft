package gui.Listados;

import gui.ListaScreen;

public class ListadoPlantillasScreen extends ListaScreen {

	public ListadoPlantillasScreen() {
		super();
		_lista = new ListadoPlantillasLista();
		add(_lista);
	}
}
