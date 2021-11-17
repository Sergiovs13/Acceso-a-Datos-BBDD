package ejercicios3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

/**
 * Clase que gestiona la ventana de filtrar
 * @author windows10
 *
 */
public class DialogoFiltrar extends JDialog{

	private JTextField tfDNI,tfNombre,tfApellido,tfSueldo;
	private JTextField tfDia, tfMes, tfAnyo,tfMatricula;
	private JComboBox cbSueldo, cbFecha,cbOrdenar;
	private GestionBBDD gest;
	private DefaultTableModel dtm;
	private JRadioButton rdbtnAsc, rdbtnDesc;
	private JTextField sueldo, trabajadores;
	
	/**
	 * Constructor de la clase
	 * @param gest. Objeto de la clase GestionBBDD
	 * @param dtm. Modelo de la tabla que muestra los trabajadores
	 * @param sueldo. Componente JTextField donde pondremos el sueldo
	 * @param trabajadores. Compoente JTextField donde pondremos el numero de trabajadores
	 */
	public DialogoFiltrar(GestionBBDD gest, DefaultTableModel dtm,JTextField sueldo, JTextField trabajadores) {
		iniciarComponentes();
		this.gest=gest;
		this.dtm=dtm;
		this.sueldo=sueldo;
		this.trabajadores=trabajadores;
	}
	
	/**
	 * Metodo que inicia los componentes y les da utilidad
	 */
	private void iniciarComponentes() {
		setTitle("Filtrar Trabajador");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(325,450);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNI.setBounds(10, 30, 46, 14);
		getContentPane().add(lblDNI);
		
		tfDNI = new JTextField();
		tfDNI.setBounds(77, 30, 117, 20);
		getContentPane().add(tfDNI);
		tfDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 77, 55, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(77, 74, 117, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(10, 121, 55, 14);
		getContentPane().add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(77, 118, 117, 20);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSueldo.setBounds(10, 161, 46, 14);
		getContentPane().add(lblSueldo);
		
		tfSueldo = new JTextField();
		tfSueldo.setBounds(114, 158, 117, 20);
		getContentPane().add(tfSueldo);
		tfSueldo.setColumns(10);
		
		cbSueldo = new JComboBox();
		cbSueldo.setModel(new DefaultComboBoxModel(new String[] {"<", "=", ">"}));
		cbSueldo.setBounds(54, 157, 50, 22);
		getContentPane().add(cbSueldo);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(10, 214, 46, 14);
		getContentPane().add(lblFecha);
		
		cbFecha = new JComboBox();
		cbFecha.setModel(new DefaultComboBoxModel(new String[] {"<", "=", ">"}));
		cbFecha.setBounds(54, 210, 50, 22);
		getContentPane().add(cbFecha);
		
		tfDia = new JTextField();
		tfDia.setBounds(109, 211, 28, 20);
		getContentPane().add(tfDia);
		tfDia.setColumns(10);
		
		tfMes = new JTextField();
		tfMes.setBounds(147, 211, 28, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(185, 211, 46, 20);
		getContentPane().add(tfAnyo);
		tfAnyo.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(10, 256, 63, 14);
		getContentPane().add(lblMatricula);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(77, 253, 117, 20);
		getContentPane().add(tfMatricula);
		tfMatricula.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 309, 80, 23);
		getContentPane().add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {

			/*Al pulsar en el boton, comprobamos que campos estan vacios o llenos
			 * Si el usuario selecciona un campo para ordenar y el tipo de ordenacion
			 */
			public void actionPerformed(ActionEvent e) {
				String sentencia="";
				int semaforo=0;
				
				if(!tfDNI.getText().equals("")) {
					semaforo++;
					sentencia+="Where dni Like '%"+tfDNI.getText()+"%' ";
				}
				if(!tfNombre.getText().equals("")) {
					semaforo++;
					if(semaforo>1)
						sentencia+= "And nombre Like '%"+tfNombre.getText()+"%' ";
					else
						sentencia+="Where nombre Like '%"+tfNombre.getText()+"%'";
				}
				if(!tfApellido.getText().equals("")) {
					semaforo++;
					if(semaforo>1)
						sentencia+= "And apellido Like '%"+tfApellido.getText()+"%' ";
					else
						sentencia+="Where apellido Like '%"+tfApellido.getText()+"%'";
				}
				if(!tfSueldo.getText().equals("")) {
					semaforo++;
					if(semaforo>1)
						sentencia+= "And sueldo"+cbSueldo.getSelectedItem()+tfSueldo.getText()+" ";
					else
						sentencia+="Where sueldo"+cbSueldo.getSelectedItem()+tfSueldo.getText()+" ";
				}
				if(!tfDia.getText().equals("")&&!tfMes.getText().equals("")&&!tfAnyo.getText().equals("")) {
					semaforo++;
					if(semaforo>1)
						sentencia+= "And fecha "+cbFecha.getSelectedItem()+" '"+tfAnyo.getText()+"-"+tfMes.getText()+"-"+tfDia.getText()+"' ";
					else
						sentencia+="Where fecha "+cbFecha.getSelectedItem()+" '"+tfAnyo.getText()+"-"+tfMes.getText()+"-"+tfDia.getText()+"' ";
				}
				if(!tfMatricula.getText().equals("")) {
					semaforo++;
					if(semaforo>1)
						sentencia+= "And matricula Like '%"+tfMatricula.getText()+"%' ";
					else
						sentencia+="Where matricula Like '%"+tfMatricula.getText()+"%'";
				}
				if(!cbOrdenar.getSelectedItem().equals("Sin Ordenacion")) {
					
					if(rdbtnAsc.isSelected())
						sentencia+=" Order By "+cbOrdenar.getSelectedItem()+" ASC";
					else
						sentencia+=" Order By "+cbOrdenar.getSelectedItem()+" DESC";
				}
				gest.limpiarTabla(dtm);
				sueldo.setText(""+gest.filtrarTrabajadores(dtm, sentencia));
				trabajadores.setText(""+dtm.getRowCount());
				dispose();
			}
			
		});
		JButton btnVerTodos = new JButton("Ver Todos");
		btnVerTodos.setBounds(97, 309, 97, 23);
		getContentPane().add(btnVerTodos);
		
		btnVerTodos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					gest.limpiarTabla(dtm);
					sueldo.setText(""+gest.mostrarTrabajadores(dtm));
					trabajadores.setText(""+dtm.getRowCount());
					dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(202, 309, 89, 23);
		getContentPane().add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Filtrado cancelado");
				vaciarCampos();
				dispose();
			}
			
		});
		
		JLabel lblOrdenacion = new JLabel("Ordenacion:");
		lblOrdenacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblOrdenacion.setBounds(10, 343, 73, 14);
		getContentPane().add(lblOrdenacion);
		
		cbOrdenar = new JComboBox();
		cbOrdenar.setModel(new DefaultComboBoxModel(new String[] {"Sin Ordenacion", "DNI", "Nombre", "Apellido", "Sueldo", "Fecha", "Matricula"}));
		cbOrdenar.setBounds(34, 368, 117, 22);
		getContentPane().add(cbOrdenar);
		
		
		rdbtnAsc = new JRadioButton("ASC");
		rdbtnAsc.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnAsc.setBounds(202, 355, 55, 23);
		getContentPane().add(rdbtnAsc);
		
		rdbtnDesc = new JRadioButton("DESC");
		rdbtnDesc.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbtnDesc.setBounds(202, 381, 63, 23);
		getContentPane().add(rdbtnDesc);
		
		ButtonGroup botonesO=new ButtonGroup();
		botonesO.add(rdbtnAsc);
		botonesO.add(rdbtnDesc);
		
		setVisible(true);
	}
	
	/**
	 * Metodo que vacia los campos del dialogo
	 */
	private void vaciarCampos() {
		tfDNI.setText("");
		tfNombre.setText("");
		tfApellido.setText("");
		tfSueldo.setText("");
		tfDia.setText("");
		tfMes.setText("");
		tfAnyo.setText("");
		tfMatricula.setText("");
	}
	
	
}
