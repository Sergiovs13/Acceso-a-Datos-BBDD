package ejercicios2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * Clase que gestiona la base de datos
 * @author windows10
 *
 */
public class GestionBBDD {

	Connection cnnctn;
	
	/**
	 * Metodo que abre la conexion con la base de datos
	 */
	public void abrirConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que cierra la conexion con la base de datos
	 */
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo muestra los trabajadores que hay en la tabla
	 * @return string con los datos de la tabla trabajadores
	 * @throws SQLException
	 */
	public String verTrabajadores() throws SQLException {
		Statement st=cnnctn.createStatement();
		ResultSet rs=st.executeQuery("Select * From trabajadores");
		
		String info="";
		String fecha;
		String dia;
		String mes;
		String anyo;
		while(rs.next()) {
			fecha=rs.getString("fecha");
			dia=fecha.substring(8, 10);
			mes=fecha.substring(5, 7);
			anyo=fecha.substring(0, 4);
			fecha=dia+"/"+mes+"/"+anyo;
			
			info+=rs.getString("dni")+" -- "+rs.getString("nombre")+" "+rs.getString("apellido")+" -- "+rs.getString("sueldo")+" -- "+fecha+" -- "+rs.getString("matricula")+"\n";
		}
		return info;
	}
	
	/**
	 * Metodo que inserta trabajadores en la tabla
	 * @param dni del trabajador
	 * @param nombre del trabajador
	 * @param apellido del trabajador
	 * @param sueldo del trabajador
	 * @param fecha del trabajador
	 * @param matricula del trabajador
	 * @throws SQLException
	 */
	public void insertarTrabajadores(String dni, String nombre, String apellido, String sueldo, String fecha,String matricula) throws SQLException {
		Statement st=cnnctn.createStatement();
		st.execute("Insert Into trabajadores (dni, nombre, apellido, sueldo, fecha, matricula) Values ('"+dni+"','"+nombre+"','"+apellido+"','"+sueldo+"','"+fecha+"','"+matricula+"')");
		st.close();
	}
	
	/**
	 * Metodo que elimina los trabajadores
	 * @param dni del trabajador a eliminar
	 * @return true o false si ha podido borrar el trabajador
	 * @throws SQLException
	 */
	public boolean eliminarTrabajadores(String dni) throws SQLException {
		Statement st=cnnctn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs=st.executeQuery("Select * From trabajadores Where dni='"+dni+"'");
		
		if(!rs.first()) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado ningun trabajador con ese DNI");
			return false;
		}else {
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			rs.first();
			fecha=rs.getString("fecha");
			dia=fecha.substring(8, 10);
			mes=fecha.substring(5, 7);
			anyo=fecha.substring(0, 4);
			fecha=dia+"/"+mes+"/"+anyo;		
			info+=rs.getString("dni")+" -- "+rs.getString("nombre")+" "+rs.getString("apellido")+" -- "+rs.getString("sueldo")+" -- "+fecha+" -- "+rs.getString("matricula");
			
			int opcion=JOptionPane.showConfirmDialog(null, "Se ha encontrado el siguiente trabajador: \n"+info, "Borrar trabajador", JOptionPane.YES_NO_OPTION);
			
			if(opcion==JOptionPane.YES_OPTION) {
				st.execute("Delete From trabajadores Where dni ='"+dni+"'");
			}else
				JOptionPane.showMessageDialog(null, "Eliminacion cancelada");
			return true;
		}
	}
	
	/**
	 * Metodo que encuentra al trabajador
	 * @param dni del trabajador a encontrar
	 * @return true o false si encuentra al trabajador
	 * @throws SQLException
	 */
	public boolean dniEncontrado(String dni) throws SQLException {
		Statement st = cnnctn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs=st.executeQuery("Select * From trabajadores Where dni='"+dni+"'");
		if(rs.first()) {
			st.close();
			return true;
		}
			
		return false;
	}
	
	/**
	 * Metodo que devuelve los datos del trabajador
	 * @param dni del trabajador a obtener los datos
	 * @return array de tipo string con los datos del trabajador
	 * @throws SQLException
	 */
	public String[] datosDNI(String dni) throws SQLException {
		String[] datos=new String[5];
		Statement st = cnnctn.createStatement();
		ResultSet rs=st.executeQuery("Select * From trabajadores Where dni='"+dni+"'");
		
		while(rs.next()) {
			String fecha=rs.getString("fecha");
			String dia=fecha.substring(8, 10);
			String mes=fecha.substring(5, 7);
			String anyo=fecha.substring(0, 4);
			fecha=dia+"/"+mes+"/"+anyo;
			
			datos[0]=rs.getString("nombre");
			datos[1]=rs.getString("apellido");
			datos[2]=rs.getString("sueldo");
			datos[3]=fecha;
			datos[4]=rs.getString("matricula");
			
		}
		return datos;
	}
	
	/**
	 * Metodo que modifica al trabajador
	 * @param dni del trabajador a modificar
	 * @param nombre del trabajador modificado
	 * @param apellido del trabajador modificado
	 * @param sueldo del trabajador modificado
	 * @param fecha del trabajador modificado
	 * @param matricula del trabajador modificado
	 * @throws SQLException
	 */
	public void modificarTrabajador(String dni, String nombre, String apellido, String sueldo, String fecha, String matricula) throws SQLException {
		Statement st=cnnctn.createStatement();
		st.execute("Update trabajadores Set nombre='"+nombre+"',apellido='"+apellido+"',sueldo='"+sueldo+"',fecha='"+fecha+"',matricula='"+matricula+"' Where dni='"+dni+"'");
	}
}
