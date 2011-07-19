package core;

import java.util.Date;
import net.rim.blackberry.api.pdap.BlackBerryEvent;
import net.rim.blackberry.api.pdap.BlackBerryEventList;
import javax.microedition.pim.EventList;
import javax.microedition.pim.PIM;
public class CalendarManager {
	//Parametro para duracion por defecto de una cita. 1 hora
	private static long DURACION_CITA = 3600000;
	/**
	 * @param fecha Fecha de inicio de la cita
	 * @param descripcion Resumen de la cita
	 */
	public static String agregarCita(Date fecha, String descripcion) throws Exception{
		EventList el = null;
		String uid = null;
		try{
			el = (BlackBerryEventList)PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.WRITE_ONLY);
			BlackBerryEvent event =  (BlackBerryEvent) el.createEvent();
			if (el.isSupportedField(BlackBerryEvent.SUMMARY)){
				event.addString(BlackBerryEvent.SUMMARY, BlackBerryEvent.ATTR_NONE, descripcion);
			}
			if (el.isSupportedField(BlackBerryEvent.START)){
				event.addDate(BlackBerryEvent.START, BlackBerryEvent.ATTR_NONE, fecha.getTime());
			}
			if (el.isSupportedField(BlackBerryEvent.END)){
				event.addDate(BlackBerryEvent.END, BlackBerryEvent.ATTR_NONE, fecha.getTime() + DURACION_CITA);
			}
			event.commit();
			uid = event.getString(BlackBerryEvent.UID, 0);
			
		}
		catch (Exception e) {
			throw e;
		}
		finally{
			if (el != null){
				el.close();
			}
		}
		return uid;
	}
	/**
	 * @param fecha Fecha de inicio de la cita
	 * @param descripcion Resumen de la cita
	 * @param alarma Segundos antes de fecha en que sonara la alarma
	 */
	public static String agregarCita(Date fecha, String descripcion, int alarma) throws Exception{
		EventList el = null;
		String uid = null;
		try{
			el = (BlackBerryEventList)PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.WRITE_ONLY);
			BlackBerryEvent event =  (BlackBerryEvent) el.createEvent();
			if (el.isSupportedField(BlackBerryEvent.SUMMARY)){
				event.addString(BlackBerryEvent.SUMMARY, BlackBerryEvent.ATTR_NONE, descripcion);
			}
			if (el.isSupportedField(BlackBerryEvent.START)){
				event.addDate(BlackBerryEvent.START, BlackBerryEvent.ATTR_NONE, fecha.getTime());
			}
			if (el.isSupportedField(BlackBerryEvent.END)){
				event.addDate(BlackBerryEvent.END, BlackBerryEvent.ATTR_NONE, fecha.getTime() + DURACION_CITA);
			}
			if (el.isSupportedField(BlackBerryEvent.ALARM)){
				event.addInt(BlackBerryEvent.ALARM, BlackBerryEvent.ATTR_NONE, alarma);
			}
			event.commit();
			uid = event.getString(BlackBerryEvent.UID, 0);
			
		}
		catch (Exception e) {
			throw e;
		}
		finally{
			if (el != null){
				el.close();
			}
		}
		return uid;
	}
	public static BlackBerryEvent consultarCita(String uid) throws Exception{
		BlackBerryEvent ret = null;
		BlackBerryEventList el = null;
		try {
			el = (BlackBerryEventList)PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.READ_ONLY);
			ret = el.getByUID(uid);
		}catch (NullPointerException npe){
			throw new NullPointerException("Cita no encontrada!");
		}
		catch (Exception e){
			throw e;
		}finally{
			if (el != null){
				el.close();
			}
		}
		return ret;
	}
	public static void borrarCita(String uid) throws Exception{
		BlackBerryEvent ret = null;
		BlackBerryEventList el = null;
		try {
			el = (BlackBerryEventList)PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.READ_WRITE);
			ret = el.getByUID(uid);
			el.removeEvent(ret);
		}catch (NullPointerException npe){
			throw new NullPointerException("Cita no encontrada!");
		}
		catch (Exception e){
			throw e;
		}finally{
			if (el != null){
				el.close();
			}
		}
	}
	/**
	 * @param fecha Fecha de inicio de la cita
	 * @param descripcion Resumen de la cita
	 */
	public static boolean actualizarCita(String uid, Date fecha, String descripcion) throws Exception{
		boolean flag = false;
		BlackBerryEventList el = null;
		try {
			el = (BlackBerryEventList)PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.READ_WRITE);
			BlackBerryEvent ret = el.getByUID(uid);
			if(ret.countValues(BlackBerryEvent.START)>0){
				ret.setDate(BlackBerryEvent.START, 0,BlackBerryEvent.ATTR_NONE,fecha.getTime());
				flag = true;
			}
			if(ret.countValues(BlackBerryEvent.END)>0){
				ret.setDate(BlackBerryEvent.END, 0,BlackBerryEvent.ATTR_NONE,fecha.getTime() + DURACION_CITA);
				flag = true;
			}
			if(ret.countValues(BlackBerryEvent.SUMMARY)>0){
				ret.setString(BlackBerryEvent.SUMMARY, 0, BlackBerryEvent.ATTR_NONE, descripcion);
				flag = true;
			}
			if(ret.countValues(BlackBerryEvent.ALARM)>0){
				ret.removeValue(BlackBerryEvent.ALARM, 0);
				flag = true;
			}
			if(ret.isModified()){
				ret.commit();
			}
		}catch (NullPointerException npe){
			throw new NullPointerException("Cita no encontrada!");
		}
		catch (Exception e){
			throw e;
		}finally{
			if (el != null){
				el.close();
			}
		}
		return flag;
	}
	/**
	 * @param fecha Fecha de inicio de la cita
	 * @param descripcion Resumen de la cita
	 * @param alarma Segundos antes de fecha en que sonara la alarma
	 */
	public static boolean actualizarCita(String uid, Date fecha, String descripcion, int alarma) throws Exception{
		boolean flag = false;
		BlackBerryEventList el = null;
		try {
			el = (BlackBerryEventList)PIM.getInstance().openPIMList(PIM.EVENT_LIST, PIM.READ_WRITE);
			BlackBerryEvent ret = el.getByUID(uid);
			if(ret.countValues(BlackBerryEvent.START)>0){
				ret.setDate(BlackBerryEvent.START, 0,BlackBerryEvent.ATTR_NONE,fecha.getTime());
				flag = true;
			}
			if(ret.countValues(BlackBerryEvent.END)>0){
				ret.setDate(BlackBerryEvent.END, 0,BlackBerryEvent.ATTR_NONE,fecha.getTime() + DURACION_CITA);
				flag = true;
			}
			if(ret.countValues(BlackBerryEvent.SUMMARY)>0){
				ret.setString(BlackBerryEvent.SUMMARY, 0, BlackBerryEvent.ATTR_NONE, descripcion);
				flag = true;
			}
			if(ret.countValues(BlackBerryEvent.ALARM)>0){
				ret.setInt(BlackBerryEvent.ALARM, 0, BlackBerryEvent.ATTR_NONE, alarma);
				flag = true;
			}else{
				ret.addInt(BlackBerryEvent.ALARM, BlackBerryEvent.ATTR_NONE, alarma);
			}
			if(ret.isModified()){
				ret.commit();
			}
		}catch (NullPointerException npe){
			throw new NullPointerException("Cita no encontrada!");
		}
		catch (Exception e){
			throw e;
		}finally{
			if (el != null){
				el.close();
			}
		}
		return flag;
	}
	public static long getDURACION_CITA() {
		return DURACION_CITA;
	}
	public static void setDURACION_CITA(long dURACION_CITA) {
		DURACION_CITA = dURACION_CITA;
	}
	
}
