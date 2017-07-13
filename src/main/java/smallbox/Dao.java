package smallbox;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Dao {
    public static Connection connect() {
    	 	try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
        Connection conn = null;
        
        try {
            // db parameters
            String url = "jdbc:sqlite:notejam.db";
            System.out.println(url);
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
       
        return conn;
    }
    public String selectAllNotes(String search) {
        String sql = "SELECT * FROM notes WHERE text LIKE ?";
        ArrayList<HashMap<String, Object>> offlineList = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Connection conn = this.connect();
        try (
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
        		pstmt.setString(1, "%" + search + "%");
        		ResultSet rs = pstmt.executeQuery();
        	
            while (rs.next()) {
            		HashMap<String, Object> map = new HashMap<>();
	            	map.put("id", rs.getInt("id"));
	            	map.put("body", rs.getString("text"));
	            	offlineList.add(map);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        		if(conn != null) {
        			try {
        				conn.close();
        				conn = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
        		}
        }
        return gson.toJson(offlineList);
    }
    public String selectAllNotes(){
        String sql = "SELECT * FROM notes";
        ArrayList<HashMap<String, Object>> offlineList = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Connection conn = this.connect();
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
        	
            while (rs.next()) {
            		HashMap<String, Object> map = new HashMap<>();
	            	map.put("id", rs.getInt("id"));
	            	map.put("body", rs.getString("text"));
	            	offlineList.add(map);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
        		if(conn != null) {
        			try {
        				conn.close();
        				conn = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
        		}
        }
        return gson.toJson(offlineList);
    }
    public String selectNote(int id){
        String sql = "SELECT * FROM notes WHERE id=(?)";
        ArrayList<HashMap<String, Object>> offlineList = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Connection conn = this.connect();
        try (
        		PreparedStatement pstmt = conn.prepareStatement(sql)) {
        		pstmt.setInt(1, id);
        		ResultSet rs = pstmt.executeQuery();
        		
            while (rs.next()) {
            		HashMap<String, Object> map = new HashMap<>();
	            	map.put("id", rs.getInt("id"));
	            	map.put("body", rs.getString("text"));
	            	offlineList.add(map);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
    		if(conn != null) {
    			try {
    				conn.close();
    				conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
    }
        return gson.toJson(offlineList);
    }
    public void createNote(String note) {
    	   String sql = "INSERT INTO notes(text) VALUES(?)";
    	   
           try (Connection conn = this.connect();
               PreparedStatement pstmt = conn.prepareStatement(sql)) {
               pstmt.setString(1, note);
               pstmt.executeUpdate();
           } catch (SQLException e) {
               System.out.println(e.getMessage());
           }
    	
    };
}
