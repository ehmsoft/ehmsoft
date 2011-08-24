package gui;

public class ListadoActuacionesPopUp extends ListaPopUp{
	
	private ListadoActuacionesLista _lista;
	
	public ListadoActuacionesPopUp() {
		super();
		_lista = new ListadoActuacionesLista();
		add(_lista);
	}
}
