package gui;

public class ListadoActuacionesPopUp extends ListaPopUp{
		
	public ListadoActuacionesPopUp() {
		super();
		_lista = new ListadoActuacionesLista();
		add(_lista);
	}
}
