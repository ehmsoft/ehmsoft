package gui;

import java.util.Date;

import javax.microedition.pim.PIMItem;

import net.rim.blackberry.api.pdap.BlackBerryEvent;
import net.rim.device.api.ui.component.Dialog;
import core.CalendarManager;

public class VerCita {
	private String _uid;
	private VerCitaPopUp _screenPp;

	public VerCita(String uid) {
		_uid = uid;
		try {
			BlackBerryEvent event = CalendarManager.consultarCita(_uid);
			Date date = new Date(event.getDate(BlackBerryEvent.START,
					PIMItem.ATTR_NONE));
			int alarma = event.getInt(BlackBerryEvent.ALARM, PIMItem.ATTR_NONE);
			String desc = event.getString(BlackBerryEvent.SUMMARY,
					PIMItem.ATTR_NONE);
			_screenPp = new VerCitaPopUp(desc, date, alarma);
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public VerCitaPopUp getScreen() {
		return _screenPp;
	}

	public void guardarCita() {
		if (_screenPp.isGuardado() && _screenPp.isCambiado()) {
			if (_screenPp.hasAlarma()) {
				try {
					CalendarManager
							.actualizarCita(_uid, _screenPp.getFecha(),
									_screenPp.getDescripcion(),
									_screenPp.getDuracion());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			} else {
				try {
					CalendarManager.actualizarCita(_uid, _screenPp.getFecha(),
							_screenPp.getDescripcion());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		}
	}
}
