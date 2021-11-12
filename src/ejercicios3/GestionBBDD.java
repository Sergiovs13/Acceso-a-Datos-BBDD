package ejercicios3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class GestionBBDD {

	Connection cnnctn;
	
	public void abrirConexion() {
		try {
			cnnctn=DriverManager.getConnection("jdbc:mysql://localhost/manempsa","root","");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion() {
		try {
			cnnctn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarTrabajadores(DefaultTableModel dtm) throws SQLException {
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
			dtm.addRow(datos);
		}
		st.close();
		rs.close();
	}
	
	public void eliminarTrabajador(String dni) {
		try {
			Statement st=cnnctn.createStatement();
			st.execute("Delete From trabajadores Where dni='"+dni+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void limpiarTabla(DefaultTableModel dtm) {
		for(int i=dtm.getRowCount()-1;i>=0;i--) {
			dtm.removeRow(i);
		}
	}
}
