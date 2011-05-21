package gui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

public class ListaListadoProcesos extends ObjectListField {
	
	public ListaListadoProcesos()
	{
		super();
		//setRowHeight(69);
		setRowHeight(getFont().getHeight()*4);
	}
	public void drawListRow(ListField list, Graphics g, int index, int y, int w)
	{
		Proceso objeto = (Proceso) this.get(this,index);
		String demandante = objeto.getDemandante();
		String demandado = objeto.getDemandado();
		String radicado = objeto.getRadicado();
		String juzgado = objeto.getJuzgado();
		g.setFont(getFont().derive(Font.BOLD,25));
		g.drawText("Radicado: "+radicado,0,y);
		g.setFont(getFont().derive(Font.PLAIN,22));
		g.drawText("Demandante: "+demandante,20,y + getFont().getHeight());
		g.drawText("Demandado: "+demandado,20,y + getFont().getHeight()*2);
		g.drawText("Juzgado: "+juzgado, 20, y + getFont().getHeight()*3);
	}	
}