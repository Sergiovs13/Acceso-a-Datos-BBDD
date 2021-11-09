package ejercicios1_2;

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
	 * Metodo para crear la conexion
	 */
	public void crearConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para ver los servicios
	 */
	public void verServicios() {
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs=st.executeQuery("Select * From servicios Order By cantidad");
			
			String info="";
			String fecha;
			String dia;
			String mes;
			String anyo;
			String cantidad;
			while(rs.next()) {
				fecha=rs.getString("fecha");
				dia=fecha.substring(8, 10);
				mes=fecha.substring(5, 7);
				anyo=fecha.substring(0, 4);
				fecha=dia+"/"+mes+"/"+anyo;
				
				cantidad=rs.getString("cantidad");
				cantidad=cantidad.replace(".", ",");
				
				info+=fecha+" "+rs.getString("tipo")+" "+cantidad+"\n";
			}
			JOptionPane.showMessageDialog(null, info);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo para ver los clientes
	 */
	public void verClientes() {
		try {
			Statement st=cnnctn.createStatement();
			ResultSet rs=st.executeQuery("Select * From clientes Order By nombre");
			
			String info="";
			String movil;
			
			while(rs.next()) {
				movil=rs.getString("tfno2");
				if(movil.equalsIgnoreCase("null"))
					movil="no tiene";
				
				info+=rs.getString("nombre")+" ----- Fijo: "+rs.getString("tfno1")+" ----- Movil: "+movil+"\n";
			}
			JOptionPane.showMessageDialog(null, info);
			st.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para cerrar la conexion
	 */
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
