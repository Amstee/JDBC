package cecs.pkg323.jdbc;
import java.sql.*;
import java.util.Scanner;

/**
 * @author Jeremy & Kristen
 */
public class CECS323Project {
    static String USER;
    static String PASS;
    static String DBNAME;
    static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
    static String DB_URL = "jdbc:derby://localhost:1527/";
    static Connection conn = null;
    static Scanner in = null;

        /**
    * Takes the input string and outputs "N/A" if the string is empty or null.
    * @param input The string to be mapped.
    * @return  Either the input string or "N/A" as appropriate.
    */
    public static String dispNull (String input) {
        //because of short circuiting, if it's null, it never checks the length.
        if (input == null || input.length() == 0)
            return "N/A";
        else
            return input;
    }
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
     * Lists the the names of all the writing groups
     */
    public static void listWriting() {
    	String query = "SELECT * FROM writinggroups";
        
    	try {
        	PreparedStatement stmt = conn.prepareStatement(query);
           	ResultSet rs = stmt.executeQuery();

        	while (rs.next()) {
        	    String name = rs.getString("groupName");
        	    System.out.println(name);
        	}    		
                stmt.close();
                rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * COMMAND 2
     * Lists all the data for a user-specified writing group
     */
    public static void listWritingData() {
        System.out.print("Please enter writing group name: ");
        String str = in.nextLine();
        String query = "SELECT * FROM writinggroups NATURAL JOIN books NATURAL JOIN publishers WHERE groupName = '" + str + "'";
        int count = 0;

        try {
                PreparedStatement stmt;
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                	if (count == 0) {
                        String groupName = rs.getString("groupName");
                        String headWriter = rs.getString("headWriter");
                        int yearFormed = rs.getInt("yearFormed");
                        String subject = rs.getString("subject");
                        System.out.printf("%-30s%-30s%-15s%-20s\n", "Group Name", "Head Writer", "Year Formed", "Subject");
                        System.out.printf("%-30s%-30s%-15d%-20s\n", groupName, headWriter, yearFormed, dispNull(subject));
                        System.out.println("- Books by " + groupName);
                        System.out.printf("%-50s%-20s%-15s%-20s\n", "Title", "Publication Year", "Pages", "Publisher");
                        count++;
                	}
                    String name = rs.getString("pubName");
                    String title = rs.getString("bookTitle");
                    int year = rs.getInt("yearPublished");
                    int pages = rs.getInt("numberPages");
                    System.out.printf("%-50s%-20d%-15d%-20s\n", title, year, pages, name);
                }
                stmt.close();
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * COMMAND 3
     * Lists the names of all the publishers
     */
    public static void listPublishers() {
    	String query = "SELECT pubName FROM publishers";
    	try {
        	PreparedStatement stmt;
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

        	while (rs.next()) {
        	    String name = rs.getString("pubName");
        	    System.out.println(name);
        	}    		
                stmt.close();
                rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}    	
    }
    
    /**
     * COMMAND 4
     * Lists all of the data for a user-specified publisher
     */
    public static void listPublisherData() {
        System.out.print("Please enter the publisher name: ");
        String str = in.nextLine();
        String query = "SELECT * FROM publishers NATURAL JOIN books NATURAL JOIN writinggroups WHERE pubName = '" + str + "'";
        int count = 0;

        try {
                PreparedStatement stmt;
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                	if (count == 0) {
                        String name = rs.getString("pubName");
                        String address = rs.getString("pubAddress");
                        String phone = rs.getString("pubPhone");
                        String email = rs.getString("pubEmail");
                        System.out.printf("%-20s%-50s%-20s%-50s\n", "Publisher Name", "Address", "Phone", "Email");
                        System.out.printf("%-20s%-50s%-20s%-50s\n", name, address, phone, email);
                        System.out.println("- Books Published by " + name);
                        System.out.printf("%-50s%-20s%-15s%-30s\n", "Title", "Publication Year", "Pages", "Writing Group");
                        count++;
                	}
                    String title = rs.getString("bookTitle");
                    int year = rs.getInt("yearPublished");
                    int pages = rs.getInt("numberPages");
                    String groupName = rs.getString("groupName");

                    System.out.printf("%-50s%-20s%-15s%-30s\n", title, year, pages, groupName);
                }
                stmt.close();
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * COMMAND 5
     * Lists the name of all the books
     */
    public static void listBooks() {
    	String query = "SELECT bookTitle FROM books";
    	try {
        	PreparedStatement stmt;
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

        	while (rs.next()) {
        	    String name = rs.getString("bookTitle");
        	    System.out.println(name);
        	}    		
                stmt.close();
                rs.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * COMMAND 6
     * Lists all the data for a user-specified book
     */
    public static void listBookData() {
        System.out.print("Please enter the book title: ");
        String str = in.nextLine();
        String query = "SELECT * FROM books NATURAL JOIN writinggroups NATURAL JOIN publishers WHERE bookTitle = '" + str + "'";

        try {
                PreparedStatement stmt;
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                        String title = rs.getString("bookTitle");
                        int year = rs.getInt("yearPublished");
                        int pages = rs.getInt("numberPages");
                        String pubName = rs.getString("pubName");
                        String groupName = rs.getString("groupName");

                        System.out.printf("%-50s%-20s%-10s%-20s%-20s\n", "Title", "Publication Year", "Pages", "Writing Group", "Publisher");
                        System.out.printf("%-50s%-20d%-10d%-20s%-20s\n", title, year, pages, groupName, pubName);
                }
                stmt.close();
                rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * COMMAND 7
     * Inserts a book into the database
     */
    public static void insertBook() {
        try {
            System.out.print("Title: ");
            String title = in.nextLine();
            System.out.print("Year of Publication: ");
            int year = in.nextInt();
            in.nextLine();
            System.out.print("Number of Pages: ");
            int pages = in.nextInt();
            in.nextLine();
            System.out.print("Publisher Name: ");
            String publisher = in.nextLine();
            System.out.print("Writing Group: ");
            String group = in.nextLine();

            String insertion = "INSERT INTO books(bookTitle, yearPublished, numberPages, pubName, groupName) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(insertion);
            stmt.setString(1, title);
            stmt.setInt(2, year);
            stmt.setInt(3, pages);
            stmt.setString(4, publisher);
            stmt.setString(5, group);
            
            stmt.executeUpdate();
           
            System.out.println("Book has been inserted successfully !");
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * COMMAND 8
     * Inserts a new publishers into the database and takes
     * over the book publications of one already in the database
     */
    public static void insertPublisher() {
    	try {
            System.out.print("Publisher Name: ");
            String name = in.nextLine();
            System.out.print("Publisher Address: ");
            String add = in.nextLine();
            System.out.print("Publisher Phone: ");
            String phone = in.nextLine();
            System.out.print("Publisher Email: ");
            String email = in.nextLine();

            String insertion = "INSERT INTO publishers(pubName, pubAddress, pubPhone, pubEmail) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(insertion);
            stmt.setString(1, name);
            stmt.setString(2, add);
            stmt.setString(3, phone);
            stmt.setString(4, email);
            
            stmt.executeUpdate();
            
            stmt.close();
            
            System.out.print("Which publisher did they replace? ");
            String old = in.nextLine();
            String replace = "UPDATE books SET books.pubName = ? WHERE books.pubName = ?";
            
            PreparedStatement stmt2;
            stmt2 = conn.prepareStatement(replace);
            stmt2.setString(1, name);
            stmt2.setString(2, old);
            stmt2.executeUpdate();
            
            stmt2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * COMMAND 9
     * Removes a book from the database
     */
    public static void removeBook() {
    	try {
                System.out.print("Enter the book title to remove: ");
        	String title = in.nextLine();
        	
                PreparedStatement stmt;
        	String sql = "DELETE FROM books WHERE bookTitle = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, title);
                
                stmt.executeUpdate();
                
    		System.out.println("Book successfully deleted !");
    		stmt.close();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}    	
    }
    
    public static void main(String[] args) {
        in = new Scanner(System.in);
        int cmd = 0;
        boolean loop = true; 

        System.out.print("Name of the database (not the user account): ");
        DBNAME = in.nextLine();
        System.out.print("Database user name: ");
        USER = in.nextLine();
        System.out.print("Database password: ");
        PASS = in.nextLine();
        DB_URL = DB_URL + DBNAME + ";user="+ USER + ";password=" + PASS;

        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            System.out.println("Connecting to database...");
            CECS323Project.conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connection to database successfull");
            CECS323Project.DisplayCommands();
            while (loop) {
            	if (in.hasNextInt()) {
                	cmd = in.nextInt();
                	in.nextLine();
                        System.out.println();
                	
                	switch (cmd) {
                	case 1: CECS323Project.listWriting();
                			break;
                	case 2: CECS323Project.listWritingData();
                			break;
                	case 3: CECS323Project.listPublishers();
                			break;
                	case 4: CECS323Project.listPublisherData();
                			break;
                	case 5: CECS323Project.listBooks();
                			break;
                	case 6: CECS323Project.listBookData();
                			break;
                	case 7: CECS323Project.insertBook();
                			break;
                	case 8: CECS323Project.insertPublisher();
                			break;
                	case 9: CECS323Project.removeBook();
                			break;
                	case 10: loop = false;
                			break;
                	}
                    System.out.println();
                    CECS323Project.DisplayCommands();
            	} else {
            		System.out.println("Please enter an integer");
            		in.next();
            	}
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                in.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }    
}
