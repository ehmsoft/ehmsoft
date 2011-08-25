package gui;

public class ListadoPersonasScreen extends ListaScreen {
		
	public ListadoPersonasScreen() {
		super();
		_lista = new ListadoPersonasLista();
		add(_lista);
	}
}
