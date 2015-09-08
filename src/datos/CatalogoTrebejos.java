package datos;
import java.sql.*;

import conexion.DataConnection;
public class CatalogoTrebejos {

	public void addTrebejos(int j1, int j2) throws SQLException {
		DataConnection con = new DataConnection();
		
		for(int i=1;i<=8;i++)
		{
			String query="INSERT INTO `ajedrez`.`trebejos` (`tipo`,`posX`,`posY`,`color`,`dni1`,`dni2`) VALUES('P',"+i+",2,true,"+j1+","+j2+"),('P',"+i+",7,false,"+j1+","+j2+");";
			con.setQuery(query);
		}
		
		String query="INSERT INTO `ajedrez`.`trebejos` (`tipo`,`posX`,`posY`,`color`,`dni1`,`dni2`) VALUES('A',3,1,true,"+j1+","+j2+"),('A',6,1,true,"+j1+","+j2+"),('A',3,8,false,"+j1+","+j2+"),('A',6,8,false,"+j1+","+j2+"),"
																										+ "('C',2,1,true,"+j1+","+j2+"),('C',7,1,true,"+j1+","+j2+"),('C',2,8,false,"+j1+","+j2+"),('C',7,8,false,"+j1+","+j2+"),"
																										+ "('T',8,1,true,"+j1+","+j2+"),('T',1,1,true,"+j1+","+j2+"),('T',1,8,false,"+j1+","+j2+"),('T',8,8,false,"+j1+","+j2+"),"
																										+ "('D',4,1,true,"+j1+","+j2+"),('D',4,8,false,"+j1+","+j2+"),"
																										+ "('R',5,1,true,"+j1+","+j2+"),('R',5,8,false,"+j1+","+j2+")";
		con.setQuery(query);
		
	}

}
