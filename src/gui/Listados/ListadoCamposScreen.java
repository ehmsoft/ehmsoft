package gui.Listados;

import gui.ListaScreen;

public class ListadoCamposScreen extends ListaScreen {

	public ListadoCamposScreen() {
		super();
		_lista = new ListadoCamposLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista, false);
	}
}
