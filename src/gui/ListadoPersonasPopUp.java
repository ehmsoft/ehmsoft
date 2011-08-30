package gui;

public class ListadoPersonasPopUp extends ListaPopUp {
		
	public ListadoPersonasPopUp() {
		super();
		_lista = new ListadoPersonasLista();
		add(_lista);
	}
}
