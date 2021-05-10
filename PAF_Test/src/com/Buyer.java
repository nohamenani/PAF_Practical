package com;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Buyer { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget","root","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	
	//inserting buyers
	
	public String insertBuyers(String fname, String lname, String pnumber, String email , String password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into buyers(`bId`,`fname`,`lname`,`pnumber`,`email`,`password`)"+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, pnumber);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, password);			
			
			// execute the statement3
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the  buyer.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	//display buyers
	
	public String readBuyers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1' align='center'><tr><th>First Name</th>" +
					 "<th>Last Name</th>" +
					 "<th>Phone Number</th>" +
					 "<th>Email</th>"+
					 "<th>Password</th>"+
					 "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from buyers";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String bId = Integer.toString(rs.getInt("bId"));
				 String fname = rs.getString("fname");
				 String lname = rs.getString("lname");
				 String pnumber =rs.getString("pnumber");
				 String email = rs.getString("email");
				 String password = rs.getString("password");
			
				// Add into the html table 
				 output += "<tr><td>" + fname + "</td>";
				 output += "<td>" + lname + "</td>";
				 output += "<td>" + pnumber + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + password + "</td>";
				
				// buttons  
				 output += "<td><input id='btnUpdate' name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-buyerid='" + bId + "'> </td>"
						 + "<input id='btnRemove' name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-buyerid='" + bId + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the buyers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//updating buyers

	public String updateBuyers(String bId, String fname, String lname, String pnumber, String email, String password) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE buyers SET fname=?,lname=?,pnumber=?,email=?,password=? WHERE bId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			 preparedStmt.setString(1, fname);
			 preparedStmt.setString(2, lname);
			 preparedStmt.setString(3, pnumber);
			 preparedStmt.setString(4, email);
			 preparedStmt.setString(5, password);
			 preparedStmt.setInt(6, Integer.parseInt(bId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the buyer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	// delete buyers

	public String deleteBuyers(String bId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from buyers where bId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the buyer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}