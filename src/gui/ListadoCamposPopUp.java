package gui;

public class ListadoCamposPopUp extends ListaPopUp {
	
	private ListadoCamposLista _lista;
	
	public ListadoCamposPopUp() {
		super();
		_lista = new ListadoCamposLista();
		add(_lista);
	}
}
