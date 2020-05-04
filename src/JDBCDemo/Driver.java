package JDBCDemo;
import java.sql.*;
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/welcome";
		String user="root";
		String pass="Welcome123";
		try {
			// 1. Get a connection to database
			Connection myConn= DriverManager.getConnection(url,user,pass);
			 
			// 2. Create a statement
			Statement myStmt= myConn.createStatement();
			
			// 3. Execute a SQL query
			ResultSet myRs= myStmt.executeQuery("Select * from student");
			
			// 4. Process the result set
			System.out.println("Databse Before any operation");
			while (myRs.next()) {
				System.out.println(myRs.getString("student_id")+ " "+myRs.getString("name")+ " "+myRs.getString("major"));
			}
			
			
			//5. Inserting into database using sql query
			//Uncomment to insert any data into the database
			
			/*Statement myStmt1= myConn.createStatement();
			String sql= "insert into student values ('10','Samon','Physics')";
			myStmt1.executeUpdate(sql);
			System.out.println("Insert Complete"); */
			
			//6. Updating the data in the database
			
			/*Statement myStmt2= myConn.createStatement();
			String sql2= "UPDATE student set major='Computer' where student_id='9'";
			myStmt2.executeUpdate(sql2);
			System.out.println("Update Complete");*/
			
			//7. Deleting the data from the database
			/*Statement myStmt3= myConn.createStatement();
			String sql3= "DELETE FROM student where student_id='10'";
			myStmt3.executeUpdate(sql3);
			System.out.println("Deletion Complete");*/
			
			//8. Prepared Statements
			PreparedStatement myStmt5= null;
			ResultSet my= null;
			myStmt5= myConn.prepareStatement("SELECT * FROM student where student_id>? and major=?");
			myStmt5.setDouble(1, 5);
			myStmt5.setString(2,"Biology");
			
			my=myStmt5.executeQuery();
			System.out.println("\nUse of prepared statements");
			while(my.next()) {
				System.out.println(my.getString("student_id")+ " "+my.getString("name")+ " "+my.getString("major"));
			}
			
			System.out.println("\nDatabase after performing the operations");
			Statement myStmt4= myConn.createStatement();
			ResultSet fdata= myStmt4.executeQuery("Select * from student");
			while (fdata.next()) {
				System.out.println(fdata.getString("student_id")+ " "+fdata.getString("name")+ " "+fdata.getString("major"));
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}
