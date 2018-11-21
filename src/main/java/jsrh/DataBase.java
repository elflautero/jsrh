package jsrh;

import java.sql.Connection;

public class DataBase {
	
	/* adicione a dll auth no jre 8 bin */

	private java.sql.Connection con = null;
	
	
	private String url = "jdbc:sqlserver://";
	//private String url = "jdbc:sqlserver://";
	//private String url ="jdbc:sqlserver://PC01\\inst01;databaseName=DB01;integratedSecurity=true";
	
	private String serverName = "SRVHOST4\\PROD";
	private String databaseName = "GisDB";
	private String userName = "ADASA\fabricio.barrozo";
	private String password = "fj17000071jf";

// Constructor
public DataBase() { }


String getConnectionUrl() {
    return url + serverName + ";databaseName=" + databaseName + ";integratedSecurity=true;";
}

Connection getConnection() {
	
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = java.sql.DriverManager.getConnection(getConnectionUrl(), userName, password);
        if (con != null) {
            System.out.println("Connection Successful!");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println("Error Trace in getConnection() : " + e.getMessage());
    }
    return con;
}

/*
Display the driver properties, database details
 */
public void displayDbProperties() {
    java.sql.DatabaseMetaData dm = null;
    java.sql.ResultSet rs = null;
    try {
        con = this.getConnection();
        if (con != null) {
            dm = con.getMetaData();
            System.out.println("Driver Information");
            System.out.println("\tDriver Name: " + dm.getDriverName());
            System.out.println("\tDriver Version: " + dm.getDriverVersion());
            System.out.println("\nDatabase Information ");
            System.out.println("\tDatabase Name: " + dm.getDatabaseProductName());
            System.out.println("\tDatabase Version: " + dm.getDatabaseProductVersion());
            System.out.println("Avalilable Catalogs ");
            rs = dm.getCatalogs();
            while (rs.next()) {
                System.out.println("\tcatalog: " + rs.getString(1));
            }
            rs.close();
            rs = null;
            closeConnection();
        } else {
            System.out.println("Error: No active Connection");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    dm = null;
}

private void closeConnection() {
    try {
        if (con != null) {
            con.close();
        }
        con = null;
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) throws Exception {
    DataBase dataBase = new DataBase();
    dataBase.displayDbProperties();
}
}
