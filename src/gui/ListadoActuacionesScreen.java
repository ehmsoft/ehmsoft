package gui;

public class ListadoActuacionesScreen extends ListaScreen{

	private ListadoActuacionesLista _lista;

	public ListadoActuacionesScreen() {
		super();
		_lista = new ListadoActuacionesLista();
		add(_lista);
	}
}
