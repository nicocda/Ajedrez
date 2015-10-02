package datos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.ConexionPropuesta;
import entidades.Alfil;
import entidades.Caballo;
import entidades.Peon;
import entidades.Reina;
import entidades.Rey;
import entidades.Torre;
import entidades.Trebejo;

public class CatalogoTrebejosPropuesto
{
	private ArrayList<Trebejo> listaTrebejos;
	static private ArrayList<Trebejo> trebejos;
	private static CatalogoTrebejosPropuesto instance = null;
	public CatalogoTrebejosPropuesto() {
	   }
	   public static CatalogoTrebejosPropuesto getInstance() {
	      if(instance == null) {
	         instance = new CatalogoTrebejosPropuesto();
	      }
	      return instance;
	   }
	
	public ArrayList<Trebejo> getListaTrebejos()
	{
		return listaTrebejos;
	}
	
	//Busco los trebejos correspondiente a 2 jugadores (una partida)
	public ArrayList<Trebejo> buscarTrebejos(int blanco, int negro)
	{
		listaTrebejos = new ArrayList<Trebejo>();
		String sql="select tipo, posx, posy, color from partida p"
				+ "inner join trebejos t on t.dni1="+Integer.toString(blanco)+" and t.dni2="+Integer.toString(negro)
				+ " where blanco="+Integer.toString(blanco)+" and negro="+Integer.toString(negro);
		Statement sentencia=null;
		ResultSet rs=null;
		
		try 
		{			
			sentencia= ConexionPropuesta.getInstancia().getConn().createStatement();
			rs= sentencia.executeQuery(sql);
			while (rs.next())
			{			
				Trebejo t;
				char tipo = rs.getString("tipo").charAt(0);
				int posX = rs.getInt("posx");
				int posY = rs.getInt("posy");
				boolean color = rs.getBoolean("color");
				
				//Según el tipo se crean (con sus respectivos constructores) cada pieza
				
				switch(tipo)
				{
				case 'P' : t= new Peon(tipo, posX, posY, color, blanco, negro);
							break;
				case 'T' : t= new Torre(tipo, posX, posY, color, blanco, negro);
							break;
				case 'C' : t= new Caballo(tipo, posX, posY, color, blanco, negro);
							break;
				case 'A' : t= new Alfil(tipo, posX, posY, color, blanco, negro);
							break;
				case 'R' : t= new Rey(tipo, posX, posY, color, blanco, negro);
							break;
				case 'D' : t=new Reina(tipo, posX, posY, color, blanco, negro);
							break;
				default: t = null;
							break;
				}
				if (t!= null)
					listaTrebejos.add(t);
			}
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				ConexionPropuesta.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
		return(listaTrebejos);
	}
	
	
	public void addTrebejos(int j1, int j2){
		
		for(int i = 0; i<8; i++){
			Peon pe = new Peon('P', i, 2, true, j1, j2);
			trebejos.add(pe);
		}
		Caballo c1 = new Caballo('C', 3, 1, true, j1, j2);
		trebejos.add(c1);
		Caballo c2 = new Caballo('C', 3, 1, true, j1, j2);
		for(int i = 0; i<8; i++){
			Peon pe = new Peon('P', i, 7, false, j1, j2);
			trebejos.add(pe);
		}
			//LEO... Falta terminar
		
	}
	
	/*public void addTrebejos(int j1, int j2) {
		String sql1="INSERT INTO `ajedrez`.`trebejos` (`tipo`,`posX`,`posY`,`color`,`dni1`,`dni2`) VALUES('P',?,2,true,?,?),('P',?,7,false,?,?);"; //1 es el i, el 2 j1 y el 3 j2
		String sql2="INSERT INTO `ajedrez`.`trebejos` (`tipo`,`posX`,`posY`,`color`,`dni1`,`dni2`) VALUES"
				+ "('A',3,1,true,?,?),('A',6,1,true,?,?),('A',3,8,false,?,?),('A',6,8,false,?,?),"
				+ "('C',2,1,true,?,?),('C',7,1,true,?,?),('C',2,8,false,?,?),('C',7,8,false,?,?),"
				+ "('T',8,1,true,?,?),('T',1,1,true,?,?),('T',1,8,false,?,?),('T',8,8,false,?,?),"
				+ "('D',4,1,true,?,?),('D',4,8,false,?,?),"
				+ "('R',5,1,true,?,?),('R',5,8,false,?,?)";
		ResultSet rs=null;
		PreparedStatement sentencia=null;
		Connection con = ConexionPropuesta.getInstancia().getConn();
		try{
				for(int i;i<=8;i++){
					sentencia=con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
					sentencia.setInt(1, i);
					sentencia.setInt(2, j1);
					sentencia.setInt(3, j2);
					sentencia.setInt(4, i);
					sentencia.setInt(5, j1);
					sentencia.setInt(6, j2);
					sentencia.executeUpdate();	
				}
				
				sentencia=con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
				for(int i=1;i<=16;i=i+2){
					sentencia.setInt(i, j1);
					sentencia.setInt(i+1, j2);
				}
				sentencia.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(sentencia!=null && !sentencia.isClosed())
				{
					sentencia.close();
				}
				ConexionPropuesta.getInstancia().CloseConn();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}	
	*/
}
	
