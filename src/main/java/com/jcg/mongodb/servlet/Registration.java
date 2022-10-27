package com.jcg.mongodb.servlet;

import com.mongodb.client.MongoCollection; 
import com.mongodb.client.MongoDatabase; 
import org.bson.Document; 
import com.mongodb.MongoClient; 
//import com.mongodb.MongoCredential;
import com.mongodb.MongoException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrationServlet")
public class Registration extends HttpServlet {
	public static String aadhaar;

	private static final long serialVersionUID = 1L;

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// Reading post parameters from the request
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		aadhaar = request.getParameter("aadhaar");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		int pincode = Integer.parseInt(request.getParameter("pincode"));

		// Creating a Mongo client
	    
	    
	    try (MongoClient mongo = new MongoClient( "localhost" , 27017 ))
	    {
	    	// Accessing the database 
		    MongoDatabase database = mongo.getDatabase("NcdDb"); 
		    
		    
		    try
		    {
		    	// Creating a collection
				database.createCollection("PatientInfo");
		    } catch (Exception exception) 
		    {
		        System.err.println("Collection:- PatientInfo already Exists");
		    }
		    
		    // Retrieving a collection
		    MongoCollection<Document> collection = database.getCollection("PatientInfo"); 
		    System.out.println("Collection PatientInfo selected successfully"); 
		    
		    try
		    {
		    	Document document = new Document("First Name", firstname)
		    			.append("Last Name", lastname)
		    			.append("Gender", gender)
		    			.append("Aadhaar UID", aadhaar)
		    			.append("Phone No", phone)
		    			.append("DOB", birthday)
		    			.append("Pincode", pincode);
		    			
		    			//Inserting document into the collection
		    			collection.insertOne(document);	
		    			
		    			request.getRequestDispatcher("/checklist.jsp").forward(request, response);
		    }
		    catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
		    
	    }
	   	    
	}
}