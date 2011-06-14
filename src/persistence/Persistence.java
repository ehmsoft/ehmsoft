package persistence;

import java.util.Calendar;
import java.util.Enumeration;
import java.util.Vector;


import net.rim.device.api.database.Cursor;
import net.rim.device.api.database.Database;
import net.rim.device.api.database.DatabaseFactory;
import net.rim.device.api.database.Row;
import net.rim.device.api.database.Statement;

import core.*;
import ehmsoft.Cargado;
import ehmsoft.Guardado;

public class Persistence implements Cargado, Guardado {
	private ConnectionManager connMgr;
	public Persistence() throws Exception{
		connMgr = new ConnectionManager();
	}

	public void actualizarPersona(Persona persona) throws Exception {
		/**
		 * @param tipo	1 para demandante, 2 para demandado
		 **/
		Database d = null;
		d= DatabaseFactory.open(connMgr.getDbLocation());
		if(persona.getTipo()==1){
			Statement stPersona1 = d.createStatement("INSERT INTO demandantes VALUES(persona.getId_persona()," +
					"persona.getId()," +
					"persona.getNombre()," +
					"persona.getTelefono()," +
					"persona.getDireccion()," +
					"persona.getCorreo()," +																		
			"persona.getNombre())");
			stPersona1.prepare();
			stPersona1.execute();
			stPersona1.close();
		}
		if(persona.getTipo()==2){
			Statement stPersona2 = d.createStatement("INSERT INTO demandados VALUES(persona.getId_persona()," +
					"persona.getId()," +
					"persona.getNombre()," +
					"persona.getTelefono()," +
					"persona.getDireccion()," +
					"persona.getCorreo()," +																		
			"persona.getNombre())");
			stPersona2.prepare();
			stPersona2.execute();
			stPersona2.close();
		}
		d.close();

	}

	public void guardarPersona(Persona persona) throws Exception {
		/**
		 * @param tipo	1 para demandante, 2 para demandado
		 **/
		Database d = null;
		d= DatabaseFactory.open(connMgr.getDbLocation());
		if(persona.getTipo()==1){
			Statement stPersona1 = d.createStatement("INSERT INTO demandantes VALUES(NULL, " +
					"persona.getId()," +
					"persona.getNombre()," +
					"persona.getTelefono()," +
					"persona.getDireccion()," +
					"persona.getCorreo()," +																		
			"persona.getNotas())");
			stPersona1.prepare();
			stPersona1.execute();
			stPersona1.close();
		}
		else if(persona.getTipo()==2){
			Statement stPersona2 = d.createStatement("INSERT INTO demandados VALUES(NULL, " +
					"persona.getId()," +
					"persona.getNombre()," +
					"persona.getTelefono()," +
					"persona.getDireccion()," +
					"persona.getCorreo()," +																		
			"persona.getNotas())");
			stPersona2.prepare();
			stPersona2.execute();
			stPersona2.close();
		}
		else{
			throw new Exception("Tipo persona invalido");
		}
		d.close();
	}

	public void borrarPersona(Persona persona) throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarJuzgado(Juzgado juzgado) throws Exception {
		Database d = null;
		d= DatabaseFactory.open(connMgr.getDbLocation());
		Statement stJuzgado = d.createStatement("INSERT INTO juzgados VALUES( juzgado.getId_juzgado(),"+
				"juzgado.getNombre(),"+
				"juzgado.getCiudad(),"+
				"juzgado.getTelefono(),"+
				"juzgado.getDireccion(),"+ 
		"juzgado.getTipo())");
		stJuzgado.prepare();
		stJuzgado.execute();
		stJuzgado.close();
		d.close();

	}

	public void guardarJuzgado(Juzgado juzgado) throws Exception {
		Database d = null;
		d= DatabaseFactory.open(connMgr.getDbLocation());
		Statement stJuzgado = d.createStatement("INSERT INTO juzgados VALUES( NULL,"+
				"juzgado.getNombre(),"+
				"juzgado.getCiudad(),"+
				"juzgado.getTelefono(),"+
				"juzgado.getDireccion(),"+
		"juzgado.getTipo())");
		stJuzgado.prepare();
		stJuzgado.execute();
		stJuzgado.close();
		d.close();
	}

	public void borrarJuzgado(Juzgado juzgado) throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarActuacion(Actuacion actuacion) throws Exception {
		// TODO Auto-generated method stub

	}

	public void guardarActuacion(Actuacion actuacion, String id_proceso) throws Exception {
		Database d = null;
		d= DatabaseFactory.open(connMgr.getDbLocation());
		Statement stActiacion = d.createStatement("INSERT INTO actuciones VALUES( NULL,"+
				"id_proceso,"+
				"actuacion.getJuzgado(),"+
				"actuacion.getFecha(),"+
				"actuacion.getFechaProxima(),"+
		"actuacion.getDescripcion())");
		stActiacion.prepare();
		stActiacion.execute();
		stActiacion.close();
		d.close();

	}

	public void borrarActuacion(Actuacion actuacion) throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarCampoPersonalizado(CampoPersonalizado campo)
	throws Exception {
		// TODO Auto-generated method stub

	}

	public void guardarCampoPersonalizado(CampoPersonalizado campo,String id_proceso) throws Exception {
		// TODO Auto-generated method stub


	}

	public void borrarCampoPersonalizado(CampoPersonalizado campo)
	throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub

	}

	public void guardarProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub

	}

	public void borrarProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub

	}

	public Vector consultarDemandantes() throws Exception {//Devuelve una vector iterable de todos los demandantes
		Database d = null;
		Vector demandantes = new Vector();
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT * FROM demandantes order by nombre");
			st.prepare();
			Cursor cursor = st.getCursor();
			while(cursor.next())
			{                    
				Row row = cursor.getRow();
				int id_demandante = row.getInteger(0);
				String cedula = row.getString(1);
				String nombre = row.getString(2);
				String telefono = row.getString(3);
				String direccion = row.getString(4);
				String correo = row.getString(5);
				String notas = row.getString(6);
				Persona per = new Persona(1, cedula, nombre, telefono, direccion, correo, notas, Integer.toString(id_demandante));
				demandantes.addElement(per);
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return demandantes;
	}
	public Vector consultarDemandados() throws Exception {//Devuelve un vector iterable con todos los demandados
		Database d = null;
		Vector demandados = new Vector();
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT * FROM demandados order by nombre");
			st.prepare();
			Cursor cursor = st.getCursor();
			while(cursor.next())
			{                    
				Row row = cursor.getRow();
				int id_demandado = row.getInteger(0);
				String cedula = row.getString(1);
				String nombre = row.getString(2);
				String telefono = row.getString(3);
				String direccion = row.getString(4);
				String correo = row.getString(5);
				String notas = row.getString(6);
				Persona per = new Persona(2, cedula, nombre, telefono, direccion, correo, notas, Integer.toString(id_demandado));
				demandados.addElement(per);

			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return demandados;
	}
	public Vector consultarPersonas() throws Exception {//Devuelve un vector iterable con todos los demandantes y demandados
		Database d = null;
		Vector pers = new Vector();
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT * FROM demandados order by nombre");
			st.prepare();
			Cursor cursor = st.getCursor();
			while(cursor.next())
			{                    
				Row row = cursor.getRow();
				int id_demandado = row.getInteger(0);
				String cedula = row.getString(1);
				String nombre = row.getString(2);
				String telefono = row.getString(3);
				String direccion = row.getString(4);
				String correo = row.getString(5);
				String notas = row.getString(6);
				Persona per = new Persona(2, cedula, nombre, telefono, direccion, correo, notas, Integer.toString(id_demandado));
				pers.addElement(per);
			}
			st.close();
			cursor.close();
			st = d.createStatement("SELECT * FROM demandantes order by nombre");
			st.prepare();
			cursor = st.getCursor();
			while(cursor.next())
			{                    
				Row row = cursor.getRow();
				int id_demandante = row.getInteger(0);
				String cedula = row.getString(1);
				String nombre = row.getString(2);
				String telefono = row.getString(3);
				String direccion = row.getString(4);
				String correo = row.getString(5);
				String notas = row.getString(6);
				Persona per = new Persona(1, cedula, nombre, telefono, direccion, correo, notas, Integer.toString(id_demandante));
				pers.addElement(per);
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return pers;
	}
	public Persona consultarPersona(String id_persona, int tipo) throws Exception {//Devuelve una persona especifica
		Database d = null;
		Persona per = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			if(tipo == 2){
				Statement st = d.createStatement("SELECT * FROM demandados where id_demandado = ?");
				st.prepare();
				st.bind(1, id_persona);
				Cursor cursor = st.getCursor();
				if(cursor.next()){                    
					Row row = cursor.getRow();
					int id_demandado = row.getInteger(0);
					String cedula = row.getString(1);
					String nombre = row.getString(2);
					String telefono = row.getString(3);
					String direccion = row.getString(4);
					String correo = row.getString(5);
					String notas = row.getString(6);
					per = new Persona(2, cedula, nombre, telefono, direccion, correo, notas, Integer.toString(id_demandado));
				}
				st.close();
				cursor.close();
			} else if(tipo == 1){
				Statement st = d.createStatement("SELECT * FROM demandantes where id_demandante = ?");
				st.prepare();
				st.bind(1, id_persona);
				Cursor cursor = st.getCursor();
				if(cursor.next()){                    
					Row row = cursor.getRow();
					int id_demandante = row.getInteger(0);
					String cedula = row.getString(1);
					String nombre = row.getString(2);
					String telefono = row.getString(3);
					String direccion = row.getString(4);
					String correo = row.getString(5);
					String notas = row.getString(6);
					per = new Persona(1, cedula, nombre, telefono, direccion, correo, notas, Integer.toString(id_demandante));
				}	
				st.close();
				cursor.close();
			}else{
				throw new Exception("tipo incorrecto para consultar Persona");
			}
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return per;
	}
	public Vector consultarProcesos() throws Exception {
		Database d = null;
		Vector procesos = new Vector();
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT p.id_proceso, p.fecha_creacion, p.radicado, p.radicado_unico, p.estado, p.tipo, p.notas, p.prioridad, demandante.id_demandante, demandante.cedula, demandante.nombre, demandante.telefono, demandante.direccion, demandante.correo, demandante.notas, demandado.id_demandado, demandado.cedula, demandado.nombre, demandado.telefono, demandado.direccion, demandado.correo, demandado.notas, j.id_juzgado, j.nombre, j.ciudad, j.telefono, j.direccion, j.tipo, c.descripcion FROM procesos p, demandantes demandante, demandados demandado, juzgados j, categorias c WHERE p.id_demandante = demandante.id_demandante AND p.id_demandado = demandado.id_demandado AND p.id_juzgado = j.id_juzgado AND p.id_categoria = c.id_categoria ");
			st.prepare();
			Cursor cursor = st.getCursor();
			while(cursor.next())
			{                    
				Row row = cursor.getRow();
				int id_proceso = row.getInteger(0);
				String fecha_creacion = row.getString(1);
				String radicado = row.getString(2);
				String radicado_unico = row.getString(3);
				String estado = row.getString(4);
				String tipo = row.getString(5);
				String notas = row.getString(6);
				String prioridad = row.getString(7);
				int id_demandante = row.getInteger(8);
				String cedula_demandante = row.getString(9);
				String nombre_demandante = row.getString(10);
				String telefono_demandante = row.getString(11);
				String direccion_demandante = row.getString(12);
				String correo_demandante = row.getString(13);
				String notas_demandante = row.getString(14);
				int id_demandado = row.getInteger(15);
				String cedula_demandado = row.getString(16);
				String nombre_demandado = row.getString(17);
				String telefono_demandado = row.getString(18);
				String direccion_demandado = row.getString(19);
				String correo_demandado = row.getString(20);
				String notas_demandado = row.getString(21);
				int id_juzgado = row.getInteger(22);
				String nombre_juzgado = row.getString(23);
				String ciudad_juzagdo = row.getString(24);
				String telefono_juzgado = row.getString(25);
				String direccion_juzgado = row.getString(26);
				String tipo_juzgado = row.getString(27);
				String descripcion_categoria = row.getString(28);
				Persona demandante = new Persona(1, cedula_demandante, nombre_demandante, telefono_demandante, direccion_demandante, correo_demandante, notas_demandante, Integer.toString(id_demandante));
				Persona demandado = new Persona(2, cedula_demandado, nombre_demandado, telefono_demandado, direccion_demandado, correo_demandado, notas_demandado, Integer.toString(id_demandado));
				Juzgado juzgado = new Juzgado(nombre_juzgado, ciudad_juzagdo, direccion_juzgado, telefono_juzgado, tipo_juzgado, Integer.toString(id_juzgado));
				Proceso proceso = new Proceso(Integer.toString(id_proceso), demandante, demandado, stringToCalendar(fecha_creacion), juzgado, radicado, radicado_unico, new Vector(), estado, descripcion_categoria, tipo, notas, new Vector(), Integer.parseInt(prioridad));
				procesos.addElement(proceso);
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		Enumeration e = procesos.elements();
		while(e.hasMoreElements()){
			Proceso proceso_act = (Proceso) e.nextElement();
			proceso_act.setActuaciones(consultarActuaciones(proceso_act));
		}
		return procesos;
	}

	public Proceso consultarProceso(String id_proceso) throws Exception {
		Database d = null;
		Proceso proceso = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT p.id_proceso, p.fecha_creacion, p.radicado, p.radicado_unico, p.estado, p.tipo, p.notas, p.prioridad, demandante.id_demandante, demandante.cedula, demandante.nombre, demandante.telefono, demandante.direccion, demandante.correo, demandante.notas, demandado.id_demandado, demandado.cedula, demandado.nombre, demandado.telefono, demandado.direccion, demandado.correo, demandado.notas, j.id_juzgado, j.nombre, j.ciudad, j.telefono, j.direccion, j.tipo, c.descripcion FROM procesos p, demandantes demandante, demandados demandado, juzgados j, categorias c WHERE p.id_proceso= ? AND p.id_demandante = demandante.id_demandante AND p.id_demandado = demandado.id_demandado AND p.id_juzgado = j.id_juzgado AND p.id_categoria = c.id_categoria ");
			st.prepare();
			st.bind(1, id_proceso);
			Cursor cursor = st.getCursor();
			if(cursor.next())
			{                    
				Row row = cursor.getRow();
				String fecha_creacion = row.getString(1);
				String radicado = row.getString(2);
				String radicado_unico = row.getString(3);
				String estado = row.getString(4);
				String tipo = row.getString(5);
				String notas = row.getString(6);
				String prioridad = row.getString(7);
				int id_demandante = row.getInteger(8);
				String cedula_demandante = row.getString(9);
				String nombre_demandante = row.getString(10);
				String telefono_demandante = row.getString(11);
				String direccion_demandante = row.getString(12);
				String correo_demandante = row.getString(13);
				String notas_demandante = row.getString(14);
				int id_demandado = row.getInteger(15);
				String cedula_demandado = row.getString(16);
				String nombre_demandado = row.getString(17);
				String telefono_demandado = row.getString(18);
				String direccion_demandado = row.getString(19);
				String correo_demandado = row.getString(20);
				String notas_demandado = row.getString(21);
				int id_juzgado = row.getInteger(22);
				String nombre_juzgado = row.getString(23);
				String ciudad_juzagdo = row.getString(24);
				String telefono_juzgado = row.getString(25);
				String direccion_juzgado = row.getString(26);
				String tipo_juzgado = row.getString(27);
				String descripcion_categoria = row.getString(28);
				Persona demandante = new Persona(1, cedula_demandante, nombre_demandante, telefono_demandante, direccion_demandante, correo_demandante, notas_demandante, Integer.toString(id_demandante));
				Persona demandado = new Persona(2, cedula_demandado, nombre_demandado, telefono_demandado, direccion_demandado, correo_demandado, notas_demandado, Integer.toString(id_demandado));
				Juzgado juzgado = new Juzgado(nombre_juzgado, ciudad_juzagdo, direccion_juzgado, telefono_juzgado, tipo_juzgado, Integer.toString(id_juzgado));
				proceso = new Proceso(id_proceso, demandante, demandado, stringToCalendar(fecha_creacion), juzgado, radicado, radicado_unico, new Vector(), estado, descripcion_categoria, tipo, notas, new Vector(), Integer.parseInt(prioridad));
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		proceso.setActuaciones(consultarActuaciones(proceso));
		return proceso;
	}

	public Vector consultarActuaciones(Proceso proceso) throws Exception {
		Database d = null;
		Vector actuaciones = new Vector();
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT a.id_actuacion, a.id_proceso, a.id_juzgado, a.fecha_creacion, a.fecha_proxima, a.descripcion, j.nombre, j.ciudad, j.telefono, j.direccion, j.tipo FROM actuaciones a, juzgados j where a.id_proceso = ? and a.id_juzgado = j.id_juzgado ");
			st.prepare();
			st.bind(1, proceso.getId_proceso());
			Cursor cursor = st.getCursor();
			while(cursor.next()){                    
				Row row = cursor.getRow();
				int id_actuacion = row.getInteger(0);
				int id_juzgado = row.getInteger(2);
				Calendar fecha_creacion = stringToCalendar(row.getString(3));
				Calendar fecha_proxima = stringToCalendar(row.getString(4));
				String descripcion = row.getString(5);
				String nombre_juzgado = row.getString(6);
				String ciudad_juzgado = row.getString(7);
				String telefono_juzgado = row.getString(8);
				String direccion_juzgado = row.getString(9);
				String tipo_juzgado = row.getString(10);
				Juzgado juzgado = new Juzgado(nombre_juzgado, ciudad_juzgado, direccion_juzgado, telefono_juzgado, tipo_juzgado,Integer.toString(id_juzgado));
				Actuacion actuacion = new Actuacion(juzgado, fecha_creacion, fecha_proxima,descripcion, Integer.toString(id_actuacion));
				actuaciones.addElement(actuacion);
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return actuaciones;
	}
	public Actuacion consultarActuacion(String id_actuacion) throws Exception {
		Database d = null;
		Actuacion actuacion = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT a.id_actuacion, a.id_proceso, a.id_juzgado, a.fecha_creacion, a.fecha_proxima, a.descripcion, j.nombre, j.ciudad, j.telefono, j.direccion, j.tipo FROM actuaciones a, juzgados j where a.id_actuacion = ? and a.id_juzgado = j.id_juzgado ");
			st.prepare();
			st.bind(1, id_actuacion);
			Cursor cursor = st.getCursor();
			if(cursor.next()){                    
				Row row = cursor.getRow();
				int id_juzgado = row.getInteger(2);
				Calendar fecha_creacion = stringToCalendar(row.getString(3));
				Calendar fecha_proxima = stringToCalendar(row.getString(4));
				String descripcion = row.getString(5);
				String nombre_juzgado = row.getString(6);
				String ciudad_juzgado = row.getString(7);
				String telefono_juzgado = row.getString(8);
				String direccion_juzgado = row.getString(9);
				String tipo_juzgado = row.getString(10);
				Juzgado juzgado = new Juzgado(nombre_juzgado, ciudad_juzgado, direccion_juzgado, telefono_juzgado, tipo_juzgado,Integer.toString(id_juzgado));
				actuacion = new Actuacion(juzgado, fecha_creacion, fecha_proxima,descripcion, id_actuacion);
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return actuacion;
	}
	//Devuelve la lista de todos los juzgados
	public Vector consultarJuzgados() throws Exception {
		Database d = null;
		Vector juzgados = new Vector();
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT * FROM juzgados order by nombre");
			st.prepare();
			Cursor cursor = st.getCursor();
			while(cursor.next())
			{                    
				Row row = cursor.getRow();
				int id_juzgado = row.getInteger(0);
				String nombre = row.getString(1);
				String ciudad = row.getString(2);
				String tipo = row.getString(3);
				String direccion = row.getString(4);
				String telefono = row.getString(5);
				Juzgado juz = new Juzgado(nombre, ciudad, direccion, telefono, tipo, Integer.toString(id_juzgado));
				juzgados.addElement(juz);
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return juzgados;
	}
	public Juzgado consultarJuzgado(String id_juzgado) throws Exception {
		Database d = null;
		Juzgado juz = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement st = d.createStatement("SELECT * FROM juzgados where id_juzgado = ?");
			st.prepare();
			st.bind(1, id_juzgado);
			Cursor cursor = st.getCursor();
			if(cursor.next())
			{                    
				Row row = cursor.getRow();	
				String nombre = row.getString(1);
				String ciudad = row.getString(2);
				String tipo = row.getString(3);
				String direccion = row.getString(4);
				String telefono = row.getString(5);
				juz = new Juzgado(nombre, ciudad, direccion, telefono, tipo, id_juzgado);
			}
			st.close();
			cursor.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		return juz;
	}
	private Calendar stringToCalendar(String fecha) {
		Calendar calendar_return = Calendar.getInstance();
		calendar_return.set(Calendar.YEAR, Integer.parseInt(fecha.substring(0, 4)));
		calendar_return.set(Calendar.MONTH, Integer.parseInt(fecha.substring(5, 7)));
		calendar_return.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fecha.substring(8, 10)));
		return calendar_return;
	}

}
