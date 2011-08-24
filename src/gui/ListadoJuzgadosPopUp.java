package gui;

public class ListadoJuzgadosPopUp extends ListaPopUp{

	private ListadoJuzgadosLista _lista;

	public ListadoJuzgadosPopUp() {
		super();
		_lista = new ListadoJuzgadosLista();
		add(_lista);
	}
}
