package gui;

public class ListadoCategoriasScreen extends ListaScreen {
	
	private ListadoCategoriasLista _lista;
	
	public ListadoCategoriasScreen() {
		super();
		_lista = new ListadoCategoriasLista();
		add(_lista);
	}
}
