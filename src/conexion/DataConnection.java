package conexion;
import java.sql.*;

public class DataConnection {

	private String user = "root";
	private String pass = "";
	private static String db = "ajedrez";
	private String url = "jdbc:mysql://localhost/"+db;
	private Connection con =null;
	
	public DataConnection()
	{
		try{
			Class.forName("com.mysql.jdbc.Driver");
				con = (Connection)DriverManager.getConnection(url,user,pass);
			if (con != null ) 
				System.out.println("Conectado a base de datos "+url+" Con Exito");
			}
		catch(SQLException ex){
			System.out.println("Hubo un problema al conectarse a la base de datos "+url+"\n Excepcion: "+ex);
		}
		catch(ClassNotFoundException ex)
		{
		System.out.println(ex);
		}
	}
	
	public ResultSet getQuery(String query)
	{
	Statement state = null;
	ResultSet resultado = null;
	try{
		state =(Statement) con.createStatement();
		resultado = state.executeQuery(query);
	}
	catch(SQLException ex)
	{
		ex.printStackTrace();
	}
	return resultado;
	}
	
	
	public void setQuery(String query)
	{
		Statement state = null;
		try{
			state = (Statement) con.createStatement();
			state.execute(query);
			/* Preguntar a Meca por el cierre de state y con */
		}
		catch(SQLException ex)
		{
		ex.printStackTrace();	
		}
		
	}
}
