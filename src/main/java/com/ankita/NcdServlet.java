package com.ankita;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class NcdServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter() )
        {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>NCD Risk Assessment Checklist</title> ");
            out.println("</head>");
            out.println("<body>");


            int total=-1;

            // read form field
            int age = Integer.parseInt(request.getParameter("age"));
            int smoke = Integer.parseInt(request.getParameter("smoke"));
            int alcohol = Integer.parseInt(request.getParameter("alcohol"));
            int waist = Integer.parseInt(request.getParameter("waist"));
            int phy_act = Integer.parseInt(request.getParameter("phy_act"));
            int fam_his = Integer.parseInt(request.getParameter("fam_his"));
            

            total = age + smoke + alcohol + waist + phy_act + fam_his;

            out.println("<table align=center border=1>");
            out.println("<tr>");
            out.println("<th colspan=2 style=background-color:rgb(52, 173, 52)>NCD Risk Assessment  Checklist Score</th>");
            out.println("</tr>");

            out.println("<tr style=background-color:skyblue>");
            out.println("<th>Question</th>");
            out.println("<th>Score</th>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>1. What is your age? (in complete years)</td>");
            out.println("<td>"+age+"</td> ");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>2. Do you smoke or Consume smokeless product like Gutka or Khaini?</td>");
            out.println("<td>"+smoke+"</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>3. Do you consume alcohol daily?</td>");
            out.println("<td>"+alcohol+"</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>4. Measurement of waist in (cm)</td>");
            out.println("<td>"+waist+"</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>5. Do you undertake any physical activities for a minimum of 150 minutes a week?</td>");
            out.println("<td>"+phy_act+"</td>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>6. Do you have a family history (any one of your parents or siblings) of high blood pressure, diabetes and heart disease?</td>");
            out.println("<td>"+fam_his+"</td>");
            out.println("</tr>");

            out.println("<tr style=text-align:right>");
            out.println("<th rowspan=2 style=background-color:lightpink align=left>Total Score: </th>");
	        out.println("<td>"+total+"</td>");
            out.println("</tr>");
            
            out.println("</table>");
            
            
            if(total == -1)
				out.println("<h2>Please answer the questions first.");
            if (total > 4)
            	out.println("The person may be at higher risk of NCDs and needs to be prioritized for"+ 
                "attending screening.");
            if (total < 4)
            	out.println("The person is not at risk of NCDs and doesn't need screening.");
            
            

            out.println("</h2><br><br><button onclick=history.back() align=center>Go Back</button>");
            out.println("</body>");
            out.println("</html>");
            
        }
	}
 
	

}
