package negocio;
import java.util.ArrayList;

import datos.*;
import entidades.Jugador;
import entidades.Partida;
import entidades.Trebejo;

public class ControladorPartida 
	{
		private CatalogoPartidaPropuesto cp = new CatalogoPartidaPropuesto();
		private CatalogoTrebejosPropuesto ct = new CatalogoTrebejosPropuesto();
		private CatalogoJugadoresPropuesto cj = new CatalogoJugadoresPropuesto();
	
		public Partida cargarPartida(int dni1, int dni2) 
		{
			if (!cp.existePartida(dni1, dni2))
			//Si la partida no existe la creo nueva (con los valores predeterminado de las piezas)y la agrego en la BD
			{
				cp.agregarPartida(dni1, dni2);
				ct.addTrebejos(dni1, dni2);
			}
			//ahora busco en la base de datos la partida que quiero y la retorno
			Partida p = cp.buscarUnaPartida(dni1, dni2);
			return p;
	
		}

	public ArrayList<Integer> buscarOponentes(int dni)
	{
		ArrayList<Integer> listaOponentes = cp.buscarOponente(dni);
		return(listaOponentes);
	}
	
	
	public int mover(int finalPosX, int finalPosY, Trebejo treb, Partida part){
		boolean encontroTrebejo = false;
		for  (Trebejo t : part.getFichas())
		{
			if(t.getPosX()== finalPosX && t.getPosY()==finalPosY)
			{
					encontroTrebejo = true;
					if(treb.movimientoPermitido(finalPosX, finalPosY, true))
					{
						if (t.getColor() == treb.getColor())
						{
							//No puedo mover
							return 1 ;
						}
						//Como
						else 
						{
							
								part.getFichas().remove(t);
								ct.removeBD(t);
								int pos= this.buscarPosicion(treb,part);
								part.getFichas().get(pos).setPosX(finalPosX);
								part.getFichas().get(pos).setPosY(finalPosY);
								//part.getFichas().set(pos, treb);
								ct.updateBD(finalPosX, finalPosY, treb);
								boolean turno = part.getTurno();
								if(turno)
									part.setTurno(false);
								else part.setTurno(true);
								return 2;
						}
					}
					//Si el movimiento no es permitido
					else 
						return 4;
			}
		};
			if(!encontroTrebejo)
			{	
				if(treb.movimientoPermitido(finalPosX, finalPosY, false))
				{
					int pos= this.buscarPosicion(treb,part);
					ct.updateBD(finalPosX, finalPosY, treb);
					treb.setPosX(finalPosX);
					treb.setPosY(finalPosY);
					part.getFichas().set(pos, treb);
					boolean turno = part.getTurno();
					if(turno)
						part.setTurno(false);
					else part.setTurno(true);
					return 3 ;
				}
				else
					return 4;
			}
			else return 0;
		}

	private int buscarPosicion(Trebejo treb, Partida part) {
		int i= 0;
		for(Trebejo t : part.getFichas())
		{
			if(t.getPosX()==treb.getPosX() && t.getPosY() == treb.getPosY())
			{
				i=part.getFichas().indexOf(t);
			}
		}
		return i;
	}
		
	}



