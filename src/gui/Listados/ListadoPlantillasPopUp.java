package gui.Listados;

import gui.ListaPopUp;

public class ListadoPlantillasPopUp extends ListaPopUp {

	public ListadoPlantillasPopUp() {
		super();
		_lista = new ListadoPlantillasLista(){
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista);
	}

}
