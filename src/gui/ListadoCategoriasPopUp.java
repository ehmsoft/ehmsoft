package gui;

public class ListadoCategoriasPopUp extends ListaPopUp {
	private ListadoCategoriasLista _lista;
	
	public ListadoCategoriasPopUp() {
		super();
		_lista = new ListadoCategoriasLista();
		add(_lista);
	}
}
