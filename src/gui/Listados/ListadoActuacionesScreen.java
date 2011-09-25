package gui.Listados;

import gui.ListaScreen;

public class ListadoActuacionesScreen extends ListaScreen {

	public ListadoActuacionesScreen() {
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
