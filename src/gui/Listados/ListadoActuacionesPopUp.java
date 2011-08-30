package gui.Listados;

import gui.ListaPopUp;

public class ListadoActuacionesPopUp extends ListaPopUp {

	public ListadoActuacionesPopUp() {
		super();
		_lista = new ListadoActuacionesLista();
		add(_lista);
	}
}
