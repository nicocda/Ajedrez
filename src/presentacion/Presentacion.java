package presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.util.ArrayList;


import javax.swing.JTextArea;

import negocio.ControladorPartida;
import entidades.Jugador;
import entidades.Partida;
import entidades.Trebejo;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Presentacion extends JFrame {

	private JPanel contentPane;
	private JTextField txtDni;
	private JTextField txtDni2;
	private JTextField movX;
	private JTextField movY;
	private JLabel lblJ1, lblJ2;
	private ControladorPartida cp = new ControladorPartida();
	private Partida p=null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Presentacion frame = new Presentacion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Presentacion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_inicial = new JPanel();
		panel_inicial.setBounds(5, 5, 424, 490);
		contentPane.add(panel_inicial);
		panel_inicial.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(0, 0, 424, 490);
		contentPane.add(panel_2);
		panel_2.setVisible(false);
		
		JList list_1 = new JList();
		DefaultListModel model_1 = new DefaultListModel();
		list_1.setBounds(38, 61, 135, 304);
		panel_2.add(list_1);
		
		JList list_2 = new JList();
		DefaultListModel model_2 = new DefaultListModel();
		list_2.setBounds(257, 61, 135, 304);
		panel_2.add(list_2);
				
				txtDni = new JTextField();
				txtDni.setBounds(124, 32, 98, 20);
				panel_inicial.add(txtDni);
				txtDni.setColumns(10);
				
				JLabel lblIngreseSuDni = new JLabel("DNI Tuyo :");
				lblIngreseSuDni.setBounds(36, 35, 79, 14);
				panel_inicial.add(lblIngreseSuDni);
				
				JTextArea textArea = new JTextArea();
				textArea.setBounds(36, 103, 340, 257);
				panel_inicial.add(textArea);
				
				
				txtDni2 = new JTextField();
				txtDni2.setBounds(124, 72, 252, 20);
				panel_inicial.add(txtDni2);
				txtDni2.setColumns(10);
				
				JLabel lblDniOponente = new JLabel("DNI Oponente :");
				lblDniOponente.setBounds(36, 75, 87, 14);
				panel_inicial.add(lblDniOponente);
				
				JButton btnJugar = new JButton("Jugar");
				btnJugar.addMouseListener(new MouseAdapter() {
					@Override
				
public void mouseClicked(MouseEvent arg0) {
						
						int j1 =Integer.parseInt( txtDni.getText());
						int j2 =Integer.parseInt( txtDni2.getText());
						//SI UNO DE LOS textFields ESTA VACIO TIRA ERROR(CORREGIR)...	
								
								
										p = cp.cargarPartida(j1, j2);
										if((p.getBlanco()== null) || (p.getNegro()== null)){
											JOptionPane.showMessageDialog(panel_2,"Jugador no existe, ingrese otro");
										}
										else {
											lblJ1.setText(p.getBlanco().getNombre());
											lblJ2.setText(p.getNegro().getNombre());
											ArrayList<Trebejo> trebs = p.getFichas();
											for (Trebejo t  :trebs){
												if(t.getColor()){
													model_1.addElement(t);
												}else{
													model_2.addElement(t);
												}
											}
											list_1.setModel(model_1);
											list_2.setModel(model_2);
											panel_inicial.setVisible(false);
											panel_2.setVisible(true);
										}
								/*if (p!=null){
								lblJ1.setText(p.getBlanco().getNombre());
								lblJ2.setText(p.getNegro().getNombre());}
								else{
									lblJ1.setText("");
									lblJ2.setText("");
								}*/
								
							
							
						
					}
				});
				btnJugar.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				btnJugar.setBounds(36, 371, 359, 34);
				panel_inicial.add(btnJugar);
				
				JButton btnBuscarOponentes = new JButton("Buscar Oponentes");
				btnBuscarOponentes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnBuscarOponentes.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						
						int dni= Integer.parseInt(txtDni.getText());
						textArea.setText("");
						ArrayList<Partida> pts= cp.buscarPartidas(dni);
						for( Partida p: pts){
						textArea.append("Dni Blanco  : "+p.getBlanco().getDni()+"       Dni Negro: "+p.getNegro().getDni()+"\n");
					}}
				});
				btnBuscarOponentes.setBounds(243, 31, 133, 23);
				panel_inicial.add(btnBuscarOponentes);

		

		
		JLabel lblFichasDe = new JLabel("Blancas: ");
		lblFichasDe.setFont(new Font("Verdana", Font.BOLD, 16));
		lblFichasDe.setBounds(38, 22, 89, 44);
		panel_2.add(lblFichasDe);
		
		JLabel lblFichasDe_1 = new JLabel("Negras: ");
		lblFichasDe_1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblFichasDe_1.setBounds(238, 22, 89, 44);
		panel_2.add(lblFichasDe_1);
		
		
		
		movX = new JTextField();
		movX.setColumns(10);
		movX.setBounds(139, 384, 53, 20);
		panel_2.add(movX);
		
		movY = new JTextField();
		movY.setColumns(10);
		movY.setBounds(202, 384, 53, 20);
		panel_2.add(movY);
		
		JLabel label_2 = new JLabel("Posicion Final: ");
		label_2.setBounds(38, 384, 104, 20);
		panel_2.add(label_2);
	
		lblJ1 = new JLabel("Jugador 1");
		lblJ1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblJ1.setBounds(130, 22, 98, 44);
		panel_2.add(lblJ1);
		
		lblJ2 = new JLabel("Jugador 2");
		lblJ2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblJ2.setBounds(337, 22, 89, 44);
		panel_2.add(lblJ2);
				
	
		
		JButton button = new JButton("Mover");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_inicial.setVisible(true);
				panel_2.setVisible(false);
				int posX =Integer.parseInt( movX.getText());
				int posY =Integer.parseInt( movY.getText());
				Trebejo trebSelecc = (Trebejo) list_1.getSelectedValue();
				if(trebSelecc!=null)
				{
					int estado = cp.mover(posX, posY, trebSelecc,p);
					
				} else{
					JOptionPane.showMessageDialog(panel_2,"trebejo no existe");
						}
			}
				
		});
		button.setBounds(292, 384, 89, 23);
		panel_2.add(button);
		
		
	}
}
