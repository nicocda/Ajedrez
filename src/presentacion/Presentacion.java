package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import negocio.ControladorPartida;
import entidades.Partida;
import entidades.Trebejo;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class Presentacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, pnlFichas, pnlSeleccionPartidas;
	private JList<Trebejo> lista1, lista2; 
	private JTextField txtDni1;
	private JTextField txtMovX;
	private JTextField txtMovY;
	private JLabel lblJ1, lblJ2;
	private ControladorPartida cp = new ControladorPartida();
	private Partida p=null;
	private JTextField txtNuevaPartida;
	private JScrollPane scrPanelSeleccionOponentes;
	private JPanel pnlSeleccionOponentes;
	private DefaultListModel<Trebejo> model_1, model_2;
	

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
		setBounds(100, 100, 494, 471);
		
		//Panel Principal
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Panel movimiento de fichas
		pnlFichas = new JPanel();
		pnlFichas.setLayout(null);
		pnlFichas.setBounds(0, 0, 478, 432);
		contentPane.add(pnlFichas);
		pnlFichas.setVisible(false);

		//Elementos del panel "Movimiento de fichas"
		JLabel lblFichasBlancas = new JLabel("Fichas de: ");
		lblFichasBlancas.setFont(new Font("Verdana", Font.BOLD, 16));
		lblFichasBlancas.setBounds(25, 22, 100, 44);
		pnlFichas.add(lblFichasBlancas);
		
		JLabel lblFichasNegras = new JLabel("Fichas de: ");
		lblFichasNegras.setFont(new Font("Verdana", Font.BOLD, 16));
		lblFichasNegras.setBounds(225, 22, 100, 44);
		pnlFichas.add(lblFichasNegras);
		
		lista1 = new JList<Trebejo>();
		model_1 = new DefaultListModel<Trebejo>();
		lista1.setBounds(38, 61, 135, 326);
		pnlFichas.add(lista1);
		
		lista2 = new JList<Trebejo>();
		model_2 = new DefaultListModel<Trebejo>();
		lista2.setBounds(257, 61, 135, 326);
		pnlFichas.add(lista2);
		
		txtMovX = new JTextField();
		txtMovX.setColumns(10);
		txtMovX.setBounds(139, 398, 53, 20);
		pnlFichas.add(txtMovX);
		
		txtMovY = new JTextField();
		txtMovY.setColumns(10);
		txtMovY.setBounds(202, 398, 53, 20);
		pnlFichas.add(txtMovY);
		
		JLabel lblPosicionFinal = new JLabel("Posicion Final: ");
		lblPosicionFinal.setBounds(38, 398, 104, 20);
		pnlFichas.add(lblPosicionFinal);
	
		lblJ1 = new JLabel("Jugador 1");
		lblJ1.setFont(new Font("Verdana", Font.BOLD, 16));
		lblJ1.setBounds(130, 22, 98, 44);
		pnlFichas.add(lblJ1);
		
		lblJ2 = new JLabel("Jugador 2");
		lblJ2.setFont(new Font("Verdana", Font.BOLD, 16));
		lblJ2.setBounds(337, 22, 89, 44);
		pnlFichas.add(lblJ2);

		JButton btnMover = new JButton("Mover");
		btnMover.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				mover();
			}
				
		});
		
		btnMover.setBounds(267, 397, 89, 23);
		pnlFichas.add(btnMover);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(366, 397, 89, 23);
		pnlFichas.add(btnVolver);
		
		btnVolver.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				pnlSeleccionPartidas.setVisible(true);
				pnlFichas.setVisible(false);
				model_1.clear();
				model_2.clear();
			}
		});
		
		//Si los paneles no son propiedades privadas no puedo entrar a modificarlos en el evento (no se por qué)
		//Panel scrolleable, donde apareceran las partidas del jugador.
		scrPanelSeleccionOponentes = new JScrollPane();
		pnlSeleccionOponentes = new JPanel();
		
		//Panel selección de partidas
		pnlSeleccionPartidas = new JPanel();
		pnlSeleccionPartidas.setBounds(5, 5, 473, 427);
		contentPane.add(pnlSeleccionPartidas);
		pnlSeleccionPartidas.setLayout(null);
		
			//Elementos del panel "Selección de partidas"
			txtDni1 = new JTextField();
			txtDni1.setBounds(280, 32, 183, 20);
			pnlSeleccionPartidas.add(txtDni1);
			txtDni1.setColumns(10);
			
			JLabel lblDni1 = new JLabel("Ingrese DNI: ");
			lblDni1.setBounds(191, 35, 79, 14);
			pnlSeleccionPartidas.add(lblDni1);
			
			JButton btnNuevaPartida = new JButton("Nueva Partida");
			btnNuevaPartida.setBounds(10, 393, 259, 23);
			pnlSeleccionPartidas.add(btnNuevaPartida);
			
			txtNuevaPartida = new JTextField();
			txtNuevaPartida.setBounds(280, 394, 183, 20);
			pnlSeleccionPartidas.add(txtNuevaPartida);
			txtNuevaPartida.setColumns(10);
			
					
					//Ahora va a haber un botón para crear una nueva partida
					btnNuevaPartida.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked(MouseEvent arg0) 
						{
							try
							{
								//Selecciono los 2 dni y cargo la nueva partida.
								if (!(txtDni1.getText().isEmpty()))
								{
									if(!(txtNuevaPartida.getText().isEmpty()))
									{
										int j1 =Integer.parseInt(txtDni1.getText());
										int j2 =Integer.parseInt(txtNuevaPartida.getText());
										cargarPartida(j1, j2);
									}
									else
										JOptionPane.showMessageDialog(pnlSeleccionPartidas, "Escriba un número de DNI con quien comenzar una nueva partida");
								}
								else
									JOptionPane.showMessageDialog(pnlSeleccionPartidas, "Escriba su número de DNI");
							}
							catch(NumberFormatException ne)
							{
								JOptionPane.showMessageDialog(pnlSeleccionPartidas, "En este campo sólo se pueden ingresar números");
							}
							catch(Exception ne)
							{
								JOptionPane.showMessageDialog(pnlSeleccionPartidas, ne.getMessage());
							}
						}
						
					
					});
					
					
					JButton btnBuscarOponentes = new JButton("Buscar Oponentes");
					

					
					btnBuscarOponentes.addMouseListener(new MouseAdapter() 
					{
						@Override
						public void mouseClicked(MouseEvent arg0) 
						{
							if (!(txtDni1.getText().isEmpty()))
							{
								//Una pavada: para que los paneles se muestren después de que el usuario le da al botón...
								scrPanelSeleccionOponentes.setBounds(10, 129, 453, 195);
								pnlSeleccionPartidas.add(scrPanelSeleccionOponentes);
	
								scrPanelSeleccionOponentes.setViewportView(pnlSeleccionOponentes);
								pnlSeleccionOponentes.setLayout(new GridLayout(0, 1, 0, 0));
								
								//Esto es para que cada vez que vuelvo a apretar el botón de buscar un jugador se haga un refresh
								pnlSeleccionOponentes.removeAll();
	
								//Busco los DNIs de los oponentes, los muestro en el panel.
								
								//NO SE si esto esta bien. Valida los tipos de dato, nada más.
								try
								{
									int dni= Integer.parseInt(txtDni1.getText());
									ArrayList<Integer> dniOponentes = cp.buscarOponentes(dni);
									if(dniOponentes.isEmpty())
									{
										JOptionPane.showMessageDialog(pnlSeleccionOponentes, "No se encontraron partidas iniciadas");
									}
									else
									{
									
									//Para mostrarlos recorro toda la colección y creo un nuevo botón con el texto igual al dni
									for (int i=0; i<dniOponentes.size(); i++)
									{
										pnlSeleccionOponentes.add(new JButton(dniOponentes.get(i).toString()));
										//A ese boton lo guardo en una variable casteándo el componenente que tiene el panel
										JButton a =(JButton)pnlSeleccionOponentes.getComponent(i);
										//Y le agrego un listener. Cuando apriete uno de esos botones directamente me lleva a la partida
										pnlSeleccionOponentes.getComponent(i).addMouseListener(new MouseAdapter() 
										{
											@Override
											public void mouseClicked(MouseEvent e) 
											{
												if (!(txtDni1.getText().isEmpty()))
												{
													int j1=Integer.parseInt(txtDni1.getText());
													int j2 = Integer.parseInt(a.getText());
													cargarPartida(j1, j2);
												}
												else
													JOptionPane.showMessageDialog(pnlSeleccionPartidas, "Escriba su número de DNI");
											}
										});
									}
									}
								}
								//Si pongo algo que no sea un numero muestra un mensaje
								catch(NumberFormatException ne)
								{
									JOptionPane.showMessageDialog(pnlSeleccionPartidas, "En este campo sólo se pueden ingresar números");
								}
								catch(Exception ne)
								{
									JOptionPane.showMessageDialog(pnlSeleccionPartidas, ne.getMessage());
								}
							}
							else
								JOptionPane.showMessageDialog(pnlSeleccionPartidas, "Escriba su número de DNI");
						}
					});
					
					btnBuscarOponentes.setBounds(10, 63, 453, 23);
					pnlSeleccionPartidas.add(btnBuscarOponentes);	
	}
	
	protected void mover() {
		//Todo esto sería ideal que esté en un método aparte, privado (Cuando hicimos lo de 
		//windows form de mecca, en clases, me acuerdo que una de las cosas que me corrigio fue eso.
		if (!(txtMovX.getText().isEmpty()) && !(txtMovY.getText().isEmpty()))
		{
			int posX =Integer.parseInt( txtMovX.getText());
			int posY =Integer.parseInt( txtMovY.getText());
			Trebejo trebSelecc = (Trebejo) lista1.getSelectedValue();
			if(trebSelecc!=null)
			{
				int estado = cp.mover(posX, posY, trebSelecc, p);
				switch (estado)
				{
					case 1:
						JOptionPane.showMessageDialog(pnlFichas,"Hay un trebejo aliado en esa posición");
						break;
					case 2:
						JOptionPane.showMessageDialog(pnlFichas,"Has eliminado un trebejo enemigo");
						pnlSeleccionPartidas.setVisible(true);
						model_1.clear();
						model_2.clear();
						pnlFichas.setVisible(false);
						break;
					case 3:
						JOptionPane.showMessageDialog(pnlFichas,"Se movió un trebejo exitosamente");
						pnlSeleccionPartidas.setVisible(true);
						model_1.clear();
						model_2.clear();
						pnlFichas.setVisible(false);
						break;
					case 4:
						JOptionPane.showMessageDialog(pnlFichas,"Este trebejo no se puede mover así");
						break;
					case 5: 
						JOptionPane.showMessageDialog(pnlFichas, "Ganaste la Partida");
						pnlSeleccionPartidas.setVisible(true);
						model_1.clear();
						model_2.clear();
						pnlFichas.setVisible(false);
						break;
					default:
						JOptionPane.showMessageDialog(pnlFichas,"Error desconocido, consulte al operador");
						break;
				}
			} 
			else
			{
				JOptionPane.showMessageDialog(pnlFichas,"Seleccione un trebejo");
			}
		}
		else
			JOptionPane.showMessageDialog(pnlFichas,"Debe completar los campos de la posición final");
		
	}

	protected void cargarPartida(int j1, int j2)
	{
			p = cp.cargarPartida(j1, j2);
			if(p == null)
			{
				JOptionPane.showMessageDialog(pnlFichas,"Jugador inexistente o duplicado, ingrese otro");
			}
			else 
			{											
				ArrayList<Trebejo> trebs = p.getFichas();
				for (Trebejo t  :trebs)
				{
					if(t.getColor()){
						model_1.addElement(t);
						}
						else{
						model_2.addElement(t);
						}
				}

				if (j1 == p.getBlanco().getDni())
				{
					lblJ1.setText(p.getBlanco().getNombre());
					lblJ2.setText(p.getNegro().getNombre());
					lista1.setModel(model_1);
					lista2.setModel(model_2);
				}
				else if(j1 == p.getNegro().getDni())
				{
					lblJ1.setText(p.getNegro().getNombre());
					lblJ2.setText(p.getBlanco().getNombre());
					lista1.setModel(model_2);
					lista2.setModel(model_1);
				}
				pnlSeleccionPartidas.setVisible(false);
				pnlFichas.setVisible(true);
				//Valido que sea su turno, si no lo es le deshabilito el JList
				if (p.getBlanco().getDni() == j1)
				{
					lista2.setEnabled(false);
					if (!p.getTurno())
					{
						lista1.setEnabled(true);
					}
					else
					{
						lista1.setEnabled(false);
					}
				}
				else if (p.getNegro().getDni() == j1)
				{
					lista2.setEnabled(false);
					if (p.getTurno())
					{
						lista1.setEnabled(true);
					}
					else
					{
						lista1.setEnabled(false);
					}
				}
			}
			
	}
}