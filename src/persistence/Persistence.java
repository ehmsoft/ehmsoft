package persistence;

import java.util.Calendar;
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
		// TODO Auto-generated method stub

	}

	public void guardarPersona(Persona persona) throws Exception {
		/**
		 * @param tipo	1 para demandante, 2 para demandado
		 **/
		Database d = null;
		Statement stPersona1;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
		if(persona.getTipo()==1){
			stPersona1 = d.createStatement("INSERT INTO demandantes VALUES(NULL,?,?,?,?,?,?)");
		}
		else if(persona.getTipo()==2){
			stPersona1 = d.createStatement("INSERT INTO demandados VALUES(NULL,?,?,?,?,?,?)");
		}
		else{
			throw new Exception("Tipo persona invalido");
		}
			stPersona1.prepare();
			stPersona1.bind(1, persona.getId());
			stPersona1.bind(2, persona.getNombre());
			stPersona1.bind(3, persona.getTelefono());
			stPersona1.bind(4, persona.getDireccion());
			stPersona1.bind(5, persona.getCorreo());
			stPersona1.bind(6, persona.getNotas());
			stPersona1.execute();
			stPersona1.close();
			d.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		
	}

	public void borrarPersona(Persona persona) throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarJuzgado(Juzgado juzgado) throws Exception {
		

	}

	public void guardarJuzgado(Juzgado juzgado) throws Exception {
		Database d = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement stJuzgado = d.createStatement("INSERT INTO juzgados VALUES( NULL,?,?,?,?,?)");
			stJuzgado.prepare();
			stJuzgado.bind(1, juzgado.getNombre());
			stJuzgado.bind(2, juzgado.getCiudad());
			stJuzgado.bind(3, juzgado.getTelefono());
			stJuzgado.bind(4, juzgado.getDireccion());
			stJuzgado.bind(5, juzgado.getTipo());
			stJuzgado.execute();
			stJuzgado.close();
			d.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		
	}

	public void borrarJuzgado(Juzgado juzgado) throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarActuacion(Actuacion actuacion) throws Exception {
		// TODO Auto-generated method stub

	}

	public void guardarActuacion(Actuacion actuacion, String id_proceso) throws Exception {
		Database d = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			String fecha = actuacion.getFecha().get(Calendar.YEAR)+"-"+actuacion.getFecha().get(Calendar.MONTH)+"-"+actuacion.getFecha().get(Calendar.DAY_OF_MONTH);
			String fechaProxima = actuacion.getFechaProxima().get(Calendar.YEAR)+"-"+actuacion.getFechaProxima().get(Calendar.MONTH)+"-"+actuacion.getFechaProxima().get(Calendar.DAY_OF_MONTH);
			Statement stActuacion = d.createStatement("INSERT INTO actuaciones VALUES( NULL,?,?,?,?,?)");
			stActuacion.prepare();
			stActuacion.bind(1,Integer.parseInt(id_proceso));
			stActuacion.bind(2,actuacion.getJuzgado().getId_juzgado());
			stActuacion.bind(3,fecha);
			stActuacion.bind(4,fechaProxima);
			stActuacion.bind(5,actuacion.getDescripcion());
			stActuacion.execute(); 
			stActuacion.close();
			d.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}
		

	}

	public void borrarActuacion(Actuacion actuacion) throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarCampoPersonalizado(CampoPersonalizado campo)
	throws Exception {
		// TODO Auto-generated method stub

	}

	public void guardarCampoPersonalizado(CampoPersonalizado campo,String id_proceso) throws Exception {
		Database d = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement stAtributos = d.createStatement("INSERT INTO atributos VALUES( NULL,"+
					" campo.getNombre(),"+
					"campo.isObligatorio(),"+ 
					"campo.getLongitudMax(),"+ 
			"campo.getLongitudMin())");
			stAtributos.prepare();
			stAtributos.execute();
			stAtributos.close();
			long id_atributo = d.lastInsertedRowID();
			Statement stAtributosProceso = d.createStatement("INSERT INTO atributos_proceso VALUES( id_atributo, "+
					"id_proceso,"+
					"campo.getValor())");
			stAtributosProceso.prepare();
			stAtributosProceso.execute();
			stAtributosProceso.close();	
			
			d.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}


	}

	public void borrarCampoPersonalizado(CampoPersonalizado campo)
	throws Exception {
		// TODO Auto-generated method stub

	}

	public void actualizarProceso(Proceso proceso) throws Exception {
		// TODO Auto-generated method stub

	}

	public void guardarProceso(Proceso proceso) throws Exception {
		Database d = null;
		try{
			connMgr.prepararBD();
			d = DatabaseFactory.open(connMgr.getDbLocation());
			Statement stProceso = d.createStatement("INSERT INTO procesos VALUES(NULL, " +
					"proceso.getDemandante().getId_persona()," + //ingresa el id del demandante
					"proceso.getDemandado().getId_persona()," +
					"proceso.getFecha()," +
					"proceso.getRadicado()," +
					"proceso.getRadicadoUnico()," +
					"proceso.getEstado()," +
					"proceso.getTipo()," +
					"proceso.getNotas()," +
					"proceso.getPrioridad()," +
					"proceso.getJuzgado().getId_juzgado()," +
					"'Categoria por defecto')"); //como obtener id_categoria	
			d.close();
		} catch (Exception e){
			throw e;
		} finally {
			if (d != null){
				d.close();
			}
		}		
		

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
		// TODO Auto-generated method stub
		return null;
	}

	public Proceso consultarProceso(String id_proceso) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
