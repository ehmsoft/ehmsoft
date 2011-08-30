package gui;

public class ListadoJuzgadosPopUp extends ListaPopUp{

	public ListadoJuzgadosPopUp() {
		super();
		_lista = new ListadoJuzgadosLista();
		add(_lista);
	}
}
