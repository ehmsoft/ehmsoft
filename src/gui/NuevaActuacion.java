package gui;

import java.util.Calendar;
import java.util.Date;

import core.Juzgado;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevaActuacion extends FondoNuevos {

	/**
	 * 
	 */
	private ButtonField _btnJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private BasicEditField _txtDescripcion;
	
	private Juzgado _juzgado;
	
	public NuevaActuacion() {
		setTitle("Nueva actuaci�n");
		
		HorizontalFieldManager fldJuzgado = new HorizontalFieldManager();
		VerticalFieldManager fldLeftJuzgado = new VerticalFieldManager();
		VerticalFieldManager fldRightJuzgado = new VerticalFieldManager(USE_ALL_WIDTH);
		
		_btnJuzgado = new ButtonField("Seleccionar",FIELD_RIGHT);
		fldRightJuzgado.add(_btnJuzgado);
		fldLeftJuzgado.add(new LabelField("Juzgado: "));
		fldLeftJuzgado.add(fldRightJuzgado);
		fldJuzgado.add(fldLeftJuzgado);
		addElem(fldJuzgado);
		
		_dfFecha = new DateField("Fecha: ", System.currentTimeMillis(), DateField.DATE);
		addElem(_dfFecha);
		
		_dfFechaProxima = new DateField("Fecha pr�xima: ", System.currentTimeMillis(), DateField.DATE);
		addElem(_dfFechaProxima);
		
		_txtDescripcion = new BasicEditField();
		_txtDescripcion.setLabel("Descripci�n: ");
		addElem(_txtDescripcion);
		
		add(_vertical);
		addMenuItem(menuGuardar);
	}
	
	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			// TODO Auto-generated method stub
			if (_txtDescripcion.getTextLength() == 0) {
				Object[] ask = { "Si", "No" };
				int sel = Dialog.ask(
						"�Est� seguro que no desea agregar descripci�n?", ask,
						1);
				if (sel == 0)
					UiApplication.getUiApplication().popScreen(getScreen());
			} else
				UiApplication.getUiApplication().popScreen(getScreen());
		}
	};
	
	public Juzgado getJuzgado(){
		return _juzgado;
	}
	
	public Calendar getFecha(){
		Calendar fecha = Calendar.getInstance();
		Date date = new Date(_dfFecha.getDate());
		fecha.setTime(date);
		return fecha;
	}
	
	public Calendar getFechaProxima(){
		Calendar fecha = Calendar.getInstance();
		Date date = new Date(_dfFechaProxima.getDate());
		fecha.setTime(date);
		return fecha;
	}
	
	public String getDescripcion(){
		String descripcion = null;
		try{
		descripcion =  _txtDescripcion.getText();
		}catch(NullPointerException e){
			return descripcion;
		}
		return descripcion;
	}
}