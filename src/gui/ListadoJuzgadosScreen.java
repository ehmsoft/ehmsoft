package gui;

public class ListadoJuzgadosScreen extends ListaScreen {

	private ListadoJuzgadosLista _lista;

	public ListadoJuzgadosScreen() {
		super();
		_lista = new ListadoJuzgadosLista();
		add(_lista);
	}
}
