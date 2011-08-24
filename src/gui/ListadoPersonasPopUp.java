package gui;

public class ListadoPersonasPopUp extends ListaPopUp {
	
	private ListadoPersonasLista _lista;
	
	public ListadoPersonasPopUp() {
		super();
		_lista = new ListadoPersonasLista();
		add(_lista);
	}
}
