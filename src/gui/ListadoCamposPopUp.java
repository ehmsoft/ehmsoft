package gui;

public class ListadoCamposPopUp extends ListaPopUp {
		
	public ListadoCamposPopUp() {
		super();
		_lista = new ListadoCamposLista();
		add(_lista);
	}
}
