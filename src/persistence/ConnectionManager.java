package persistence;

import java.util.Enumeration;
import javax.microedition.io.file.FileSystemRegistry;
import java.lang.Exception;
import net.rim.device.api.database.*;
import net.rim.device.api.io.URI;

public class ConnectionManager {
	
	private static String location = "file:///SDCard/databases/ehmSoft/"; //Ruta de la DB
	private static String dbName = "database.ehm";//Nombre DB
	private URI dbLocation;

	public ConnectionManager()throws Exception{
		this.dbLocation = URI.create(location + dbName);//Crea la URI para guardar la BD
	}
	public void prepararBD() throws Exception{//Método que verifica si la SD está presente, luego verifica si la base de datos existe, sino la crea
		verificarSD();
		if(!DatabaseFactory.exists(dbLocation)){
			crearBD();
			
			//  PRAGMA foreing_keys = ON;
		
		}
	}
	
	
	private void verificarSD() throws NullPointerException  {//Verifica si la SD está presente y popula la información de raíces
    	boolean sdCardPresent = false;
        String root = null;
        Enumeration e = FileSystemRegistry.listRoots();
        while (e.hasMoreElements())
        {
            root = (String)e.nextElement();
            if(root.equalsIgnoreCase("sdcard/"))
            {
                sdCardPresent = true;
            }     
        }            
        if(!sdCardPresent)
        {
            throw new NullPointerException("Tarjeta SD no Presente");      
        }
    }
    public void crearBD() throws Exception{//Crea la base de datos y la estructura
    	Database d = null;
    	d = DatabaseFactory.create(dbLocation);
    	//TODO Script de creación de base de datos  
            Statement st = d.createStatement("CREATE TABLE 'demandantes' " + "(" + 
            		"'id_demandante' INTEGER PRIMARY KEY," + 
            		"'cedula' TEXT," + 
            		"'nombre' TEXT,"+ 
            		" 'telefono' TEXT," + 
            		" 'direccion' TEXT,"+ 
            		" 'correo' TEXT," + 
            		" 'notas' TEXT)");
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'demandados' (" + 
            		"'id_demandado' INTEGER PRIMARY KEY," + 
            		"'cedula' TEXT," + 
            		"'nombre' TEXT,"+ 
            		" 'telefono' TEXT," + 
            		" 'direccion' TEXT,"+ 
            		" 'correo' TEXT," + 
            		" 'notas' TEXT)");
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'procesos' (" + 
            		"'id_proceso' INTEGER PRIMARY KEY," + 
            		"'id_demandante' INTEGER,"+ 
            		"'id_demandado' INTEGER," + 
            		"'fecha_creacion' DATE," + 
            		"'radicado' TEXT,"+ 
            		" 'radicado_unico' TEXT," + 
            		" 'estado' TEXT,"+ 
            		" 'tipo' TEXT," + 
            		" 'notas' TEXT,"+ 
            		"'prioridad' TEXT," + 
            		"'id_juzgado' INTEGER,"+ 
            		"'id_categoria' INTEGER,"+
            		"FOREIGN KEY('id_demandante') REFERENCES 'demandantes'('id_demandante'),"+
    				"FOREIGN KEY(id_demandado) REFERENCES demandados(id_demandado),"+
					"FOREIGN KEY(id_juzgado) REFERENCES juzgados(id_juzgado),"+
            		"FOREIGN KEY(id_categoria) REFERENCES categorias(id_categoria))");            
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'juzgados' (" + 
            		"'id_juzgado' INTEGER PRIMARY KEY," + 
            		"'nombre' TEXT,"+ 
            		" 'ciudad' TEXT,"+ 
            		" 'telefono' TEXT," + 
            		" 'direccion' TEXT," + 
            		" 'tipo' TEXT)");
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'actuaciones' (" + 
            		"'id_actuacion' INTEGER PRIMARY KEY," + 
            		"'id_proceso' INTEGER,)"+ 
            		"'id_juzgado' INTEGER," + 
            		"'fecha_creacion' DATE," + 
            		"'fecha_proxima' DATE,"+ 
            		" 'descripcion' TEXT,"+ 
            		"FOREIGN KEY(id_proceso) REFERENCES procesos(id_proceso),"+
    				"FOREIGN KEY(id_juzgado) REFERENCES juzgados(id_juzgado))");

            
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'categorias' (" + 
            		"'id_categoria' INTEGER PRIMARY KEY," + 
            		" 'descripcion' TEXT)");
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'atributos' (" + 
            		"'id_atributo' INTEGER PRIMARY KEY," + 
            		"'id_proceso' INTEGER," + 
            		"'nombre' TEXT,"+ 
            		" 'naturaleza' TEXT," + 
            		" 'longitud_max' INTEGER,"+ 
            		" 'longitud_min' INTEGER,"+
    				"FOREIGN KEY(id_proceso) REFERENCES procesos(id_proceso))");

            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'atributos_proceso' (" + 
            		"'id_atributo' INTEGER PRIMARY KEY," + 
            		"'id_proceso' INTEGER," + 
            		"'valor' TEXT,"+
    				"FOREIGN KEY(id_atributo) REFERENCES atributos(id_atributo),"+
        			"FOREIGN KEY(id_proceso) REFERENCES procesos(id_proceso))");

            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'filtros' (" + 
            		"'id_filtro' INTEGER PRIMARY KEY," + 
            		"'nombre' TEXT,"+ 
            		"'sentencia' TEXT)");
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'plantillas' (" + 
            		"'id_plantilla' INTEGER PRIMARY KEY," + 
            		"'nombre' TEXT)");
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'valores_plantilla' (" + 
            		"'id_plantilla' INTEGER PRIMARY KEY," + 
            		"'id_atributo' INTEGER," + 
            		"'valor' TEXT,"+
    				"FOREING KEY(id_plantilla) REFERENCES plantillas(id_plantilla),"+
        			"FOREING KEY(id_atributo) REFERENCES atributos(id_atributo))");
            st.prepare();
            st.execute();
            st.close();
            st = d.createStatement("CREATE TABLE 'preferencias' (" + 
            		"'id_preferencia' INTEGER PRIMARY KEY," + 
            		"'valor' TEXT)");
            st.prepare();
            st.execute();
            st.close();
  
    	d.close();

    }
	public URI getDbLocation() {
		return dbLocation;
	}
	


}
