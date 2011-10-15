package gui.Listados;

import gui.ListadosInterface;

public interface ListadoProcesosInterface extends ListadosInterface {
	public void setCategorias(Object[] choices);

	public void setSelectedCategoria(Object object);

	public Object getSelectedCategoria();
}
