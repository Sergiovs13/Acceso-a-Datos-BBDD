package ejercicios2_1;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.border.LineBorder;
import java.awt.Color;

public class Ventana extends JFrame{

	private JTextPane tp;
	private JTextField tfDNI,tfNombre,tfApellido,tfSueldo;
	private JTextField tfDia,tfMes,tfAnyo,textField;
	private GestionBBDD gest=new GestionBBDD();
	
	public Ventana() {
		iniciarComponentes();
		gest.abrirConexion();
	}
	
	private void iniciarComponentes() {
		setTitle("Insertar Trabajadores");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 650);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblAltaTrabajador = new JLabel("Alta Trabajador");
		lblAltaTrabajador.setForeground(Color.BLACK);
		lblAltaTrabajador.setBounds(39, 252, 89, 14);
		getContentPane().add(lblAltaTrabajador);
		lblAltaTrabajador.setBackground(Color.WHITE);
		lblAltaTrabajador.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		tp = new JTextPane();
		tp.setEditable(false);
		getContentPane().add(tp);
		
		JScrollPane scrollPane = new JScrollPane(tp);
		scrollPane.setBounds(25, 11, 429, 187);
		getContentPane().add(scrollPane);
		
		JButton btnVerTrabajadores = new JButton("Ver Trabajadores");
		btnVerTrabajadores.setBounds(157, 209, 136, 23);
		getContentPane().add(btnVerTrabajadores);
		
		btnVerTrabajadores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					tp.setText(gest.verTrabajadores());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNI.setBounds(25, 301, 46, 14);
		getContentPane().add(lblDNI);
		
		tfDNI = new JTextField();
		tfDNI.setBounds(123, 298, 136, 20);
		getContentPane().add(tfDNI);
		tfDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:\r\n");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(25, 345, 57, 14);
		getContentPane().add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(123, 342, 136, 20);
		getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellidos:");
		lblApellido.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellido.setBounds(25, 385, 57, 14);
		getContentPane().add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(123, 382, 136, 20);
		getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		JLabel lblSueldo = new JLabel("Sueldo:");
		lblSueldo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSueldo.setBounds(25, 424, 46, 14);
		getContentPane().add(lblSueldo);
		
		tfSueldo = new JTextField();
		tfSueldo.setBounds(123, 421, 136, 20);
		getContentPane().add(tfSueldo);
		tfSueldo.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFecha.setBounds(25, 463, 46, 14);
		getContentPane().add(lblFecha);
		
		tfDia = new JTextField();
		tfDia.setBounds(81, 460, 24, 20);
		getContentPane().add(tfDia);
		tfDia.setColumns(10);
		
		tfMes = new JTextField();
		tfMes.setBounds(123, 460, 24, 20);
		getContentPane().add(tfMes);
		tfMes.setColumns(10);
		
		tfAnyo = new JTextField();
		tfAnyo.setBounds(167, 460, 86, 20);
		getContentPane().add(tfAnyo);
		tfAnyo.setColumns(10);
		
		JLabel lblMatricula = new JLabel("Matricula:");
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMatricula.setBounds(25, 500, 57, 14);
		getContentPane().add(lblMatricula);
		
		textField = new JTextField();
		textField.setBounds(123, 497, 136, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnDarAlta = new JButton("Dar Alta");
		btnDarAlta.setBounds(304, 562, 89, 23);
		getContentPane().add(btnDarAlta);
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
		setVisible(true);
	}
}
