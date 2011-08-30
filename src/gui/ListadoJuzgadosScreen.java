package gui;

public class ListadoJuzgadosScreen extends ListaScreen {

	public ListadoJuzgadosScreen() {
		super();
		_lista = new ListadoJuzgadosLista();
		add(_lista);
	}
}
