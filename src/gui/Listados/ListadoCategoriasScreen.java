package gui.Listados;

import gui.ListaScreen;

public class ListadoCategoriasScreen extends ListaScreen {

	public ListadoCategoriasScreen() {
		super();
		_lista = new ListadoCategoriasLista(){
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista, false);
	}
}
