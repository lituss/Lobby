package comunicaServer;
import java.sql.*;



public class sqlDB {
	Connection conexioDB = null;
	public  sqlDB(){
	    try {
	      Class.forName("org.sqlite.JDBC");
	      conexioDB = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	    creaTaules();
	  }
	
private boolean creaTaules(){
	String crea = "create table if not exists jugadors(nom string,clau string)";
	Statement sentencia = null ;
	try {sentencia = conexioDB.createStatement();} catch (SQLException e) {
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	}
	try{
		sentencia.executeUpdate(crea);} 
	catch (SQLException e ){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	
	 finally {
		try {sentencia.close();} catch (Exception ignore) {}
	}
return true;
}

public boolean existeixUsuari(String user)
{
	int total = 0;
	Statement sentencia = null;
try {
	synchronized(conexioDB){
		sentencia = conexioDB.createStatement();
		String aux = "SELECT count(*) AS total FROM jugadors WHERE nom =\""+user+"\";";
		//String aux = "SELECT count(*) AS total FROM jugadors WHERE nom = 'sss'";
		System.out.println("Query : "+aux);
		//ResultSet rs = sentencia.executeQuery("SELECT count(*) AS total from jugadors where nom ='"+user+"';");
	
		ResultSet rs = sentencia.executeQuery(aux);
		total = rs.getInt("total");
		}
	}
	catch (SQLException e){
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	}
	finally {
		try {sentencia.close();} 
		catch (Exception ignore) {}
	}

if (total != 0) return true;
return false;
}

public boolean login(String user,String pass){
	String dbpass = null;
	Statement sentencia = null;
	if (!existeixUsuari(user)) return false;
try {
	synchronized(conexioDB){
		sentencia = conexioDB.createStatement();
		ResultSet rs = sentencia.executeQuery("SELECT clau from jugadors where nom =\""+user+"\";");
		dbpass = rs.getString("clau");
		}
	}
	catch (SQLException e){
		System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	}
	finally {
		try {sentencia.close();} catch (Exception ignore) {}}

if (pass.equals(dbpass)) return true;
return false;
}

public boolean join(String user,String pass){
	
		Statement sentencia = null;
		if (existeixUsuari(user)) return false;
	try {
		synchronized(conexioDB){
			sentencia = conexioDB.createStatement();
			sentencia.executeUpdate("INSERT INTO jugadors (nom,clau) VALUES (\""+user+"\",\""+pass+"\");");
			}
		}
		catch (SQLException e){
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
		finally {
			try {sentencia.close();} catch (Exception ignore) {}}

	return true;
}
public boolean conectat(){
	return (conexioDB == null ? false : true); 
}
}
