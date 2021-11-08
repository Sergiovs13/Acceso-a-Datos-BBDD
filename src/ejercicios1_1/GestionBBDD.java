package ejercicios1_1;

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
	 * Metodo para conectarse a la base de datos
	 */
	public void conectarseBBDD() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para ver la informacion de los trabajadores
	 */
	public void verTrabajadores() {
		String info="";
		try {
			Statement st=cnnctn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=st.executeQuery("Select * From trabajadores Order By nombre");
			while(rs.next()) {
				info+=rs.getString("nombre")+" "+rs.getString("apellido")+" "+rs.getString("sueldo")+"\n";
			}
			JOptionPane.showMessageDialog(null, info);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para cerrar conexion
	 */
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
