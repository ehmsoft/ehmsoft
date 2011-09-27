package gui.Listados;

import gui.ListaPopUp;

public class ListadoCategoriasPopUp extends ListaPopUp {

	public ListadoCategoriasPopUp() {
		super();
		_lista = new ListadoCategoriasLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista);
	}
}
