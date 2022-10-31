package com.jcg.mongodb.servlet;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient; 
//import com.mongodb.MongoCredential;
import com.mongodb.MongoException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registrationServlet")
public class Registration extends HttpServlet {
	public static String aadhaar;

	private static final long serialVersionUID = 1L;
	String id="";

	// This method is called by the servlet container to process a 'post' request
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
	{

		// Reading post parameters from the request
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String gender = request.getParameter("gender");
		aadhaar = request.getParameter("aadhaar");
		String phone = request.getParameter("phone");
		String birthday = request.getParameter("birthday");
		int pincode = Integer.parseInt(request.getParameter("pincode"));

		String pid="";
		//Long pid2=0L;
		// Creating a Mongo client
	    
	    
	    try (MongoClient mongo = new MongoClient( "localhost" , 27017 ))
	    {
	    	// Accessing the database 
		    MongoDatabase database = mongo.getDatabase("ncdjava"); 
		    
		    
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
		    
		    
		    //insertion of the values in the field
		    try
		    {
		    	
		    	List<Document> results = new ArrayList<>();
		    	FindIterable<Document> iterable = collection.find();
		    	iterable.into(results);
		    	
		    	if(!results.isEmpty())
		    	{
		    		for(Document document: results) 
		    		{
		    		    
		    			 long  min = 99999999999999L;
		    		     long  max = 10000000000000L;
		    		     long random_int = (long)(Math.random() * (max - min + 1) + min);
		    		     id = Long.toString(random_int);
		    			
		    			//id = String.valueOf(Math.random() * 1000000000000000L);
		    			if(id!=document.get("Patient id"))
		    			{
		    				 Document doc = new Document("Patient id", id)
		    			    			.append("First Name", firstname)
		    			    			.append("Last Name", lastname)
		    			    			.append("Gender", gender)
		    			    			.append("Aadhaar UID", aadhaar)
		    			    			.append("Phone No", phone)
		    			    			.append("DOB", birthday)
		    			    			.append("Pincode", pincode);
		    			    			
		    			    			//Inserting document into the collection
		    			    			collection.insertOne(doc);
		    			    			break;
		    			}
		    			else
		    			{
		    				continue;
		    			}
		    		}
		    	}
		    	else
		    	{
		    		
		    		long  min = 99999999999999L;
	    		    long  max = 10000000000000L;
	    		    long random_int = (long)(Math.random() * (max - min + 1) + min);
	    		    id = Long.toString(random_int);
		    		//id = String.valueOf(Math.random() * 1000000000000000L);
		    		Document doc = new Document("Patient id", id)
			    			.append("First Name", firstname)
			    			.append("Last Name", lastname)
			    			.append("Gender", gender)
			    			.append("Aadhaar UID", aadhaar)
			    			.append("Phone No", phone)
			    			.append("DOB", birthday)
			    			.append("Pincode", pincode);
			    			
			    			//Inserting document into the collection
			    			collection.insertOne(doc);
		    	}
		            
		       
		    			
		    			
		    }
		    catch (MongoException me) {
                System.err.println("Unable to insert due to an error: " + me);
            }
		    
		    
		    
		    //selecting patient_id to be displayed in the next field
		    try {
		    	List<Document> results = new ArrayList<>();
		    	FindIterable<Document> fi = collection.find(Filters.eq("Aadhaar UID", aadhaar));
		    	fi.into(results);
		    	
		    	
		    	pid =results.get(0).get("Patient id").toString();
		    	
		    	//pid2=Long.parseLong(pid);
		    	
		    }
		    catch(MongoException me)
		    {
		    	System.err.println("Unable to select id due to an error: " + me);
		    }
		    
		    //RequestDispatcher dispatcher = request.getRequestDispatcher("/checklist.jsp");
	        request.setAttribute("pid", pid);
			//dispatcher.forward(request, response);
		    request.getRequestDispatcher("/checklist.jsp").forward(request, response);
		    
	    }
	   	    
	}
	
}
