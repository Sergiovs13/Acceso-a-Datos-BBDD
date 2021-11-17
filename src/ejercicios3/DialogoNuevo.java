package ejercicios3;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

/**
 * Clase que gestiona la ventana de agregar un nuevo trabajador
 * @author windows10
 *
 */
public class DialogoNuevo extends JDialog{
	private JTextField tfDNI, tfNombre, tfApellido, tfMatricula;
	private JTextField tfSueldo, tfDia, tfMes, tfAnyo;
	private GestionBBDD gest;
	private DefaultTableModel dtm;
	private JTextField sueldo, trabajadores;
	
	/**
	 * Constructor de la clase
	 * @param gest. Objeto de la clas GestionBBDD
	 * @param dtm. Modelo de la tabla que muestra trabajadores
	 * @param sueldo. Componente JTextField donde pondremos el sueldo
	 * @param trabajadores. Componente JTextField donde pondremos el numero de trabajadores
	 */
	public DialogoNuevo(GestionBBDD gest, DefaultTableModel dtm, JTextField sueldo, JTextField trabajadores) {
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
		setTitle("Insertar Trabajador");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(260,400);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNI.setBounds(10, 26, 46, 14);
		getContentPane().add(lblDNI);
		
		tfDNI = new JTextField();
		tfDNI.setBounds(81, 23, 113, 20);
		getContentPane().add(tfDNI);
		tfDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 72, 60, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(81, 69, 113, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(10, 122, 60, 14);
		getContentPane().add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(81, 119, 113, 20);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSueldo.setBounds(10, 173, 46, 14);
		getContentPane().add(lblSueldo);
		
		tfSueldo = new JTextField();
		tfSueldo.setBounds(81, 170, 113, 20);
		getContentPane().add(tfSueldo);
		tfSueldo.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(10, 224, 46, 14);
		getContentPane().add(lblFecha);
		
		tfDia = new JTextField();
		tfDia.setBounds(70, 221, 22, 20);
		getContentPane().add(tfDia);
		tfDia.setColumns(10);
		
		tfMes = new JTextField();
		tfMes.setBounds(102, 221, 22, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(134, 221, 60, 20);
		getContentPane().add(tfAnyo);
		tfAnyo.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(10, 263, 60, 14);
		getContentPane().add(lblMatricula);
		
		tfMatricula = new JTextField();
		tfMatricula.setBounds(81, 260, 113, 20);
		getContentPane().add(tfMatricula);
		tfMatricula.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 312, 89, 23);
		getContentPane().add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String fecha="";
				fecha+=tfAnyo.getText();
				fecha+="-"+tfMes.getText();
				fecha+="-"+tfDia.getText();
				gest.insertarTrabajador(tfDNI.getText(), tfNombre.getText(), tfApellido.getText(), tfSueldo.getText(), fecha, tfMatricula.getText());
				JOptionPane.showMessageDialog(null, "Trabajador insertado correctamente");
				gest.limpiarTabla(dtm);
				try {
					sueldo.setText(""+gest.mostrarTrabajadores(dtm));
					trabajadores.setText(""+dtm.getRowCount());
					dispose();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				vaciarCampos();
			}
			
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(122, 312, 89, 23);
		getContentPane().add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Insercion cancelada");
				vaciarCampos();
				dispose();
			}
			
		});
		
		setVisible(true);
	}
	
	/**
	 * Metodo que vacia los campos
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
