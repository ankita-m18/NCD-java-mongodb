package com.ankita;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PatientServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter() )
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Patient Information</title> ");
            out.println("</head>");
            out.println("<body>");
            
            

           
            // read form field
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String gender = request.getParameter("gender");
            String birthday = request.getParameter("birthday");
            int pincode = Integer.parseInt(request.getParameter("pincode"));
        
            

            out.println("<table align=center border=1 width=100%>");
            out.println("<tr>");
            out.println("<th colspan=2 style=background-color:lightgreen>Patient Information</th>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>First Name :</td>");
            out.println("<td>"+firstname+"</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Last Name :</td>");
            out.println("<td>"+lastname+"</td> ");
            out.println("</tr>");
            
            out.println("<tr>");
            out.println("<td>Gender :</td>");
            out.println("<td>"+gender+"</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Date of Birth :</td>");
            out.println("<td>"+birthday+"</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>Pincode :");
            out.println("<td>"+pincode+"</td>");
            out.println("</tr>");
            
            out.println("</table>");
            
            out.println("<br><br><button align=center onclick=location.href='registration.html';>Previous</button></a>");
            out.println("<right><button align=center onclick=location.href='NCD.html';>Next</button></right");
            
            
            out.println("</body>");
            out.println("</html>");
            
        }
	}

}
