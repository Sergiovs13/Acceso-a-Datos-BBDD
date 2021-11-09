package ejercicios1_3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class Ventana extends JFrame{

	private GestionBBDD gest=new GestionBBDD();
	private JTextPane tp;
	public Ventana() {
		iniciarComponentes();
		gest.abrirConexion();
	}
	
	private void iniciarComponentes() {
		setTitle("Ver informacion");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		tp = new JTextPane();
		getContentPane().add(tp);
		
		JScrollPane scrollPane = new JScrollPane(tp);
		scrollPane.setBounds(41, 21, 403, 255);
		getContentPane().add(scrollPane);
		
		JButton btnVerTrabajadores = new JButton("Ver Trabajadores");
		btnVerTrabajadores.setBounds(31, 309, 146, 23);
		getContentPane().add(btnVerTrabajadores);
		
		btnVerTrabajadores.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				tp.setText(gest.verTrabajadores());
			}
			
		});
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				gest.cerrarConexion();
			}
		});
		setVisible(true);
	}
}
