package persistence;

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

	public Vector consultarDemandantes() throws Exception {//Devuelve una lista de todos los demandantes
		Database d = null;
		Vector demandantes = new Vector();
		connMgr = new ConnectionManager();
		connMgr.prepararBD();
		d = DatabaseFactory.open(connMgr.getDbLocation());
    	Statement st = d.createStatement("SELECT * FROM demandantes");
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
            Persona per = new Persona(id_demandante, cedula, nombre, telefono, direccion, correo, notas);
            demandantes.addElement(per);
                                            
        }
        st.close();
        cursor.close();
        return demandantes;
	}

	public Vector consultarDemandados() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarPersonas() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Persona consultarPersona(String id_persona) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	public Actuacion consultarActuacion(String id_actuacion) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Vector consultarJuzgados() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Juzgado consultarJuzgado(String id_juzgado) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
