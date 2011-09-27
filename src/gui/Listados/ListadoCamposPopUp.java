package gui.Listados;

import gui.ListaPopUp;

public class ListadoCamposPopUp extends ListaPopUp {

	public ListadoCamposPopUp() {
		super();
		_lista = new ListadoCamposLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista);
	}
}
