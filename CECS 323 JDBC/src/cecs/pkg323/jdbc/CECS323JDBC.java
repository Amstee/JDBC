package cecs.pkg323.jdbc;
import java.sql.*;
import java.util.Scanner;

/**
 * @author jeremy & Kristen
 */
public class CECS323JDBC {
    static String USER;
    static String PASS;
    static String DBNAME;
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";

    public static void DisplayCommands() {
        System.out.println("Which command do you want to execute :\n"
        		+ "1) List all writing groups\n"
        		+ "2) List data for a group\n"
        		+ "3) List all publishers\n"
        		+ "4) List data for a publisher\n"
        		+ "5) List book titles\n"
        		+ "6) List data for a book\n"
        		+ "7) Insert a book\n"
        		+ "8) Insert a new publisher\n"
        		+ "9) Remove a book\n"
        		+ "10) QUIT");
    }
    
    /**
     * COMMAND 1
     */
    public static void listWriting() {
    	
    }
    
    /**
     * COMMAND 2
     */
    public static void listWritingData() {
    	
    }
    
    /**
     * COMMAND 3
     */
    public static void listPublishers() {
    	
    }
    
    /**
     * COMMAND 4
     */
    public static void listPublisherData() {
    	
    }
    
    /**
     * COMMAND 5
     */
    public static void listBooks() {
    	
    }
    
    /**
     * COMMAND 6
     */
    public static void listBookData() {
    	
    }
    
    /**
     * COMMAND 7
     */
    public static void insertBook() {
    	
    }

    /**
     * COMMAND 8
     */
    public static void insertPublisher() {
    	
    }
    
    /**
     * COMMAND 9
     */
    public static void removeBook() {
    	
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;
        Connection conn = null;
        Statement stmt = null;
        int cmd = 0;
        boolean loop = true; 

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to database successfull");
            CECS323JDBC.DisplayCommands();
            while (loop) {
            	if (in.hasNextInt()) {
                	cmd = in.nextInt();
                	
                	switch (cmd) {
                	case 1: CECS323JDBC.listWriting();
                			break;
                	case 2: CECS323JDBC.listWritingData();
                			break;
                	case 3: CECS323JDBC.listPublishers();
                			break;
                	case 4: CECS323JDBC.listPublisherData();
                			break;
                	case 5: CECS323JDBC.listBooks();
                			break;
                	case 6: CECS323JDBC.listBookData();
                			break;
                	case 7: CECS323JDBC.insertBook();
                			break;
                	case 8: CECS323JDBC.insertPublisher();
                			break;
                	case 9: CECS323JDBC.removeBook();
                			break;
                	case 10: loop = false;
                			break;
                	}            		
            	} else {
            		System.out.println("Please enter an integer");
            	}
                CECS323JDBC.DisplayCommands();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }    
}


/*
stmt = conn.createStatement();
String sql;
sql = "SELECT au_id, au_fname, au_lname, phone FROM Authors";
ResultSet rs = stmt.executeQuery(sql);

while (rs.next()) {
    String id = rs.getString("au_id");
    String phone = rs.getString("phone");
    String first = rs.getString("au_fname");
    String last = rs.getString("au_lname");

    System.out.printf(displayFormat,
            dispNull(id), dispNull(first), dispNull(last), dispNull(phone));
}
rs.close();
stmt.close();
conn.close(); */
