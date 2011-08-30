package gui.Listados;

import gui.ListaPopUp;

public class ListadoProcesosPopUp extends ListaPopUp{
		
	public ListadoProcesosPopUp() {
		super();
		_lista = new ListadoProcesosLista();
		add(_lista);
	}
}
