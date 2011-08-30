package gui;

public class ListadoCategoriasPopUp extends ListaPopUp {
	
	public ListadoCategoriasPopUp() {
		super();
		_lista = new ListadoCategoriasLista();
		add(_lista);
	}
}
