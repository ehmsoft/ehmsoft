package gui;

public class ListadoActuacionesScreen extends ListaScreen{

	public ListadoActuacionesScreen() {
		super();
		_lista = new ListadoActuacionesLista();
		add(_lista);
	}
}
