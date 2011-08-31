package gui.Listados;

import gui.ListaPopUp;

public class ListadoPlantillasPopUp extends ListaPopUp {

	public ListadoPlantillasPopUp() {
		super();
		_lista = new ListadoPlantillasLista();
		add(_lista);
	}

}
