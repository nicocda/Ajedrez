package datos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Alfil;
import entidades.Caballo;
import entidades.Peon;
import entidades.Reina;
import entidades.Rey;
import entidades.Torre;
import entidades.Trebejo;

public class CatalogoTrebejos
{
	static private ArrayList<Trebejo> listaTrebejos;
	private static CatalogoTrebejos instance = null;
	public CatalogoTrebejos() {
	   }
	   public static CatalogoTrebejos getInstance() {
	      if(instance == null) {
	         instance = new CatalogoTrebejos();
	      }
	      return instance;
	   }
	
	public ArrayList<Trebejo> getListaTrebejos()
	{
		return listaTrebejos;
	}
	
	
	

	//Agrego los 32 trebejos a la lista listaTrebejos.
	public void addTrebejos(int dni1, int dni2){
		listaTrebejos = new ArrayList<Trebejo>();
		
		//Fichas blancas
		for(int i = 0; i<8; i++){
			Peon pe = new Peon('P', i, 1, true, dni1, dni2);
			listaTrebejos.add(pe);
			}
		Caballo c1 = new Caballo('C', 1, 0, true, dni1, dni2);
		listaTrebejos.add(c1);
		Caballo c2 = new Caballo('C', 6, 0, true, dni1, dni2);
		listaTrebejos.add(c2);
		Torre t1 = new Torre('T', 7, 0, true, dni1, dni2);
		listaTrebejos.add(t1);
		Torre t2 = new Torre('T', 0, 0, true, dni1, dni2);
		listaTrebejos.add(t2);
		Alfil a1 = new Alfil('A', 2, 0, true, dni1, dni2);
		listaTrebejos.add(a1);
		Alfil a2 = new Alfil('A', 5, 0, true, dni1, dni2);
		listaTrebejos.add(a2);
		Rey k1 = new Rey('K', 3, 0, true, dni1, dni2);
		listaTrebejos.add(k1);
		Reina q1 = new Reina('Q', 4, 0, true, dni1, dni2);
		listaTrebejos.add(q1);
		//Fichas negras
		for(int i = 0; i<8; i++){
			Peon pe = new Peon('P', i, 6, false, dni1, dni2);
			listaTrebejos.add(pe);
			}
		Caballo c3 = new Caballo('C', 1, 7, false, dni1, dni2);
		listaTrebejos.add(c3);
		Caballo c4 = new Caballo('C', 6, 7, false, dni1, dni2);
		listaTrebejos.add(c4);
		Torre t3 = new Torre('T', 7, 7, false, dni1, dni2);
		listaTrebejos.add(t3);
		Torre t4 = new Torre('T', 0, 7, false, dni1, dni2);
		listaTrebejos.add(t4);
		Alfil a3 = new Alfil('A', 2, 7, false, dni1, dni2);
		listaTrebejos.add(a3);
		Alfil a4 = new Alfil('A', 5, 7, false, dni1, dni2);
		listaTrebejos.add(a4);
		Rey k2 = new Rey('K', 4, 7, false, dni1, dni2);
		listaTrebejos.add(k2);
		Reina q2 = new Reina('Q', 3, 7, false, dni1, dni2);
		listaTrebejos.add(q2);
		
		this.registrarTrebejos();
				
	}

	//Introduzco los 32 trebejos en la DB.
	private void registrarTrebejos(){
		String sql;
		PreparedStatement sentencia=null;
		Connection con = Conexion.getInstancia().getConn();
		
		try
		{
		for(Trebejo t : listaTrebejos)
			{
			
			sql = "INSERT INTO `ajedrez`.`trebejos` (`tipo`,`posX`,`posY`,`color`,`dni1`,`dni2`) VALUES(?,?,?,?,?,?)";
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setString(1,String.valueOf(t.getTipo()));
			sentencia.setInt(2, t.getPosX());
			sentencia.setInt(3, t.getPosY());
			sentencia.setBoolean(4, t.getColor());
			sentencia.setInt(5, t.getDni1());
			sentencia.setInt(6, t.getDni2());
			sentencia.executeUpdate();
			}					
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				Conexion.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
		
		
	}
	
	public void actualizarTrebejos(int posX, int posY, Trebejo treb){
		String sql;
		PreparedStatement sentencia=null;
		Connection con = Conexion.getInstancia().getConn();
		sql = "UPDATE `ajedrez`.`trebejos` SET `posX` = ?, `posY` = ? WHERE `posX` = ? AND `posY` = ? AND `dni1` = ? AND `dni2` = ?;";
		try {
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1,posX);
			sentencia.setInt(2, posY);
			sentencia.setInt(3,treb.getPosX());
			sentencia.setInt(4, treb.getPosY());
			sentencia.setInt(5, treb.getDni1());
			sentencia.setInt(6, treb.getDni2());
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void borrarTrebejos(Trebejo treb) {
		String sql;
		PreparedStatement sentencia=null;
		Connection con = Conexion.getInstancia().getConn();
		sql = "DELETE from trebejos where posX=? and posY=? and dni1=? and dni2=?;";
		try {
			sentencia = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.setInt(1,treb.getPosX());
			sentencia.setInt(2, treb.getPosY());
			sentencia.setInt(3, treb.getDni1());
			sentencia.setInt(4, treb.getDni2());
			sentencia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
	
