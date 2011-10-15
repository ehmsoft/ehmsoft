package gui.Nuevos;

import net.rim.device.api.ui.Field;

public interface NuevaCategoriaInterface {

	public int ask(Object[] options, String string, int index);

	public void setStatus(Field field);

	public String getDescripcion();
}
