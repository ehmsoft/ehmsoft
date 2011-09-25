package gui.Listados;

import gui.ListaPopUp;

public class ListadoActuacionesPopUp extends ListaPopUp {

	public ListadoActuacionesPopUp() {
		super();
		_lista = new ListadoActuacionesLista(){
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista);
	}
}
