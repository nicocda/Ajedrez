package conexion;

import java.sql.*;

public class Conexion 
{

	//Singleton: Instanciar una �nica vez la conexion
	private static Conexion instancia;
	public static Conexion getInstancia()
	{
		if(instancia==null)
		{
			instancia=new Conexion();
		}
		return instancia;
	}
	
	private static String dbUrl="jdbc:mysql://localhost:3306/ajedrez";
	private static String dbUser="root";
	private static String dbPassword="root";
	
	//Construtor Default
	private Conexion()
	{
	}
	
	//Abrir Conexi�n y manejo de errores
	private Connection conn;
		public Connection getConn()
		{
		try 
		{
			if(conn==null || !conn.isValid(3))
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);	
			}
			
		} catch (InstantiationException e) 
		{
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return conn;
	}
	
	//Cerrar conexi� y manejo de errores
	public void CloseConn()
	{
		try {
			if(conn!=null && !conn.isClosed())
			{
				conn.close();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
