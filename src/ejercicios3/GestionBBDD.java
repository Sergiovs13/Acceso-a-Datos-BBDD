package ejercicios3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
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
	 * Metodo que muestra los trabajadores en la tabla
	 * @param dtm. Modelo de la tabla que muestra los trabajadores
	 * @return Sueldo total de los trabajadores
	 * @throws SQLException
	 */
	public double mostrarTrabajadores(DefaultTableModel dtm) throws SQLException {
		double sueldo=0;
		Statement st=cnnctn.createStatement();
		ResultSet rs=st.executeQuery("Select * From trabajadores");
		String[] datos=new String[dtm.getColumnCount()];
		while(rs.next()) {
			for(int i=0;i<datos.length;i++) {
				datos[i]=rs.getString(i+1);
			}
			String fecha=(String) datos[4].subSequence(8, 10);
			fecha+="-"+(String) datos[4].subSequence(5, 7);
			fecha+="-"+(String) datos[4].subSequence(0, 4);
			datos[4]=fecha;
			sueldo+=Double.parseDouble(datos[3]);
			dtm.addRow(datos);
		}
		st.close();
		rs.close();
		
		return sueldo;
	}
	
	/**
	 * Metodo que elimina los trabajadores
	 * @param dni. DNI del trabajador a eliminar
	 */
	public void eliminarTrabajador(String dni) {
		try {
			Statement st=cnnctn.createStatement();
			st.execute("Delete From trabajadores Where dni='"+dni+"'");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que limpia la tabla
	 * @param dtm. Modelo de la tabla que muestra los trabajadores
	 */
	public void limpiarTabla(DefaultTableModel dtm) {
		for(int i=dtm.getRowCount()-1;i>=0;i--) {
			dtm.removeRow(i);
		}
	}
	
	/**
	 * Metodo que inserta un trabajador en la tabla
	 * @param dni. DNI del trabajador
	 * @param nombre. Nombre del trabajador
	 * @param apellido. Apellido del trabajador
	 * @param sueldo. Sueldo del trabajador
	 * @param fecha. Fecha del trabajador
	 * @param matricula. Matricula del trabajador
	 */
	public void insertarTrabajador(String dni, String nombre, String apellido, String sueldo, String fecha, String matricula) {
		try {
			Statement st=cnnctn.createStatement();
			st.execute("Insert Into trabajadores (dni,nombre,apellido,sueldo,fecha,matricula) Values('"+dni+"','"+nombre+"','"+apellido+"','"+sueldo+"','"+fecha+"','"+matricula+"')");
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que modifica los datos del usuario
	 * @param dni. DNI del trabajador al que queremos modificar los datos
	 * @param nombre. Nombre del trabajador
	 * @param apellido. Apellido del trabajador
	 * @param sueldo. Sueldo del trabajador
	 * @param fecha. Fecha del trabajador
	 * @param matricula. Matricula del trabajador
	 */
	public void modificarTrabajador(String dni, String nombre, String apellido, String sueldo, String fecha, String matricula) {
		try {
			Statement st= cnnctn.createStatement();
			st.execute("Update trabajadores SET nombre='"+nombre+"', apellido='"+apellido+"', sueldo='"+sueldo+"', fecha='"+fecha+"', matricula='"+matricula+"' Where dni='"+dni+"'");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que devuelve los datos del trabajador segun el dni
	 * @param dni. DNI del trabajador del cual queremos obtener los datos
	 * @param dtm. Modelo de la tabla que muestra los trabajadores
	 * @return Los datos del trabajador en un array
	 */
	public String[] datosTrabajador(String dni, DefaultTableModel dtm) {
		String[] datos=new String[dtm.getColumnCount()-1];
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs=st.executeQuery("Select * From trabajadores Where dni='"+dni+"'");
			
			while(rs.next()) {
				for(int i=0;i<datos.length;i++) {
					datos[i]=rs.getString(i+2);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}
	
	/**
	 * Metodo que filtra los trabajadores
	 * @param dtm. Modelo de la tabla que muestra los trabajadores
	 * @param sentencia. Condiciones de la sentencia
	 * @return Sueldo de los trabajadores
	 */
	public double filtrarTrabajadores(DefaultTableModel dtm, String sentencia) {
		double sueldo=0;
		try {
			PreparedStatement ps = cnnctn.prepareStatement("Select * From trabajadores "+sentencia);
			ResultSet rs = ps.executeQuery();
			String[] datos=new String[dtm.getColumnCount()];
			while(rs.next()) {
				for(int i=0;i<datos.length;i++) {
					datos[i]=rs.getString(i+1);
				}
				String fecha=(String) datos[4].subSequence(8, 10);
				fecha+="-"+(String) datos[4].subSequence(5, 7);
				fecha+="-"+(String) datos[4].subSequence(0, 4);
				datos[4]=fecha;
				sueldo+=Double.parseDouble(datos[3]);
				dtm.addRow(datos);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sueldo;
	}
	
	/**
	 * Metodo que suma los sueldos de los trabajadores
	 * @return Sueldo de los trabajadores
	 */
	public double sumaSueldo() {
		double sueldo=0;
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs = st.executeQuery("Select Sum(sueldo) From trabajadores");
			while(rs.next()) {
				sueldo+=rs.getDouble(1);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sueldo;
	}
}
