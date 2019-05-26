
import java.sql.*;



public class DataBaseManager {
	static int Devices=1;
	private String connectionString= "jdbc:mysql://localhost/ComNetIOT";
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;

	
	public DataBaseManager() {

	}

	synchronized void createConnection(){
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection(connectionString, "root", "Abcd1234!@#$");
				st = conn.createStatement();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean addNewDevice(String ip,String port,String name,String pass ){
		createConnection();
		System.out.println("Database");
		try {
			//System.out.println("Before Query");
			st.executeUpdate("insert into Devices(IP,PORT,NAME,PASSWORD) values('"+ip+"','"+port+"','"+name+"','"+pass+"');");
			//System.out.println("After the Query");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}	

	public boolean sensorValues(String s1,String s2,String s1Value,String s2Value,String uname,String pass){
		createConnection();
		System.out.println("Database");
		try {
			//System.out.println("Before Query");
			if(Authentication(uname,pass)) {
				java.util.Date date = new java.util.Date();
				java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
				st.executeUpdate("insert into sensorValues(Device,Temperature,Humidity,dateTime) values('"+uname+"','"+s1Value+"','"+s2Value+"','"+timestamp+"');");	
			}
			//System.out.println("After the Query");
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Authentication...
	public boolean Authentication(String uname,String pass){
		createConnection();
		try {
			rs=st.executeQuery("select NAME from Devices where NAME='"+uname+"' AND PASSWORD='"+pass+"'");
			while(rs.next()){
				System.out.println(rs.getString(1));
				if(rs.getString(1).equals(uname))
					return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}