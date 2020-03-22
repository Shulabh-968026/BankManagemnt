package bank;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/transferamount")
public class transfer  extends HttpServlet {
	static String url="jdbc:mysql://localhost:3306/bank";
    static String uname="root";
    static String pass="";
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		//((ServletResponse) request).setContentType("text/html");
		PrintWriter out=response.getWriter();
		String accno=request.getParameter("accountno");
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		String taccno=request.getParameter("targetaccountno");
		String amount=request.getParameter("amount");
		long amount3=Long.parseLong(amount);
		long tac=Long.parseLong(taccno);
		long ac=Long.parseLong(accno);
		// long   accno=7999888776L;
		String query="select amount from accountinfo where accountno='"+ac+"' and name='"+name+"' and  password='"+password+"'";
		String query1="select amount,accountno from accountinfo where accountno='"+tac+"'";
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		  try
	        {
				            Class.forName("com.mysql.jdbc.Driver");
				            System.out.println("class loded");
	                        conn= DriverManager.getConnection(url,uname,pass);
	                        System.out.println("connection establish");
	                        st=conn.createStatement();
	                        rs=st.executeQuery(query);
	                        if(rs.next())
	                        {
	                                  String am=rs.getString(1);
	                                  long amount1=Long.parseLong(am);
	                                  ResultSet rs1=st.executeQuery(query1);
	                                  if(rs1.next())
	                                  {
	                                	    String amount2=rs1.getString(1);
	                                        long amu=Long.parseLong(amount2);
	                                        if(amount1>amu)
	                                        {
	                                        	amount1=amount1-amount3;
	                                        	String amum1=Long.toString(amount1);
	                                        	String q1="update accountinfo set amount ='"+amum1+"' where accountno='"+ac+"' and name='"+name+"' and password='"+password+"'";
	                                        	amu=amu+amount3;
	                                        	String q2="update accountinfo set amount='"+Long.toString(amu)+"' where accountno='"+tac+"'";
	                                        	int i=0,j=0;
	                                        	i=st.executeUpdate(q1);
	                                        	j=st.executeUpdate(q2);
	                                            if(i==1 && j==1)
	                                             {
	                        	                   out.print("<html><body>");
	                        	                   out.print("<h1>money  successfully transfer</h1>");
	                        	                   out.print("<p>your current balance is "+amum1+"Rup. and transfer money is "+amount+"</p>");
	                        	                   out.print("</body></html>"); 
	                                              }
	                                            else
	                                             {
	                        	                  out.print("either password or account not match");
	  	                                          RequestDispatcher rd=request.getRequestDispatcher("/transfer.html");
	  	                		                  rd.include(request, response);
	                                             }
	                                          }
	                                        else
	                                        {
	                                        	   out.print("<html><body>");
	                        	                   out.print("<h1>money  successfully not transfer</h1>");
	                        	                   out.print("<p>your current balance is "+amount1+"Rup. and transfer money is "+amount3+" greater than curren balance</p>");
	                        	                   out.print("</body></html>"); 
	                                         }
	                                  } 
	                                  else
	                                  {
	                                	  out.print("target  account not in data base");
	                                      RequestDispatcher rd=request.getRequestDispatcher("/transfer.html");
	                		              rd.include(request, response);
	                                  }
	                        }
	                        else
	                        {
	                        	 out.print("either password or account not match");
	                                 RequestDispatcher rd=request.getRequestDispatcher("/transfer.html");
	                		         rd.include(request, response);
	                        }
	                        conn.close();
	                     
		   }
			catch(ClassNotFoundException e)
			{
				System.out.println("Exception::"+e.getMessage());
			}
	        catch(SQLException e)
	        {
	           System.out.println("sql exception::"+e.getMessage());
	        }

}
}


