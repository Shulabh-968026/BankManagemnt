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
@WebServlet("/withdrawamount")
public class withdrawamount  extends HttpServlet {
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
		String amount=request.getParameter("amount");
		
		long ac=Long.parseLong(accno);
		// long   accno=7999888776L;
		String query="select amount from accountinfo where accountno='"+ac+"' and name='"+name+"' and  password='"+password+"'";
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
	                                  Long amount1=Long.parseLong(am);
	                                  Long amu=Long.parseLong(amount);
	                                  if(amount1>=amu)
	                                  {
	                                            amount1=amount1-amu;
	                                            String amum=Long.toString(amount1);
	                                            String q1="update accountinfo set amount ='"+amum+"' where accountno='"+ac+"' and name='"+name+"' and password='"+password+"'";
	                                            int i=0;
	                                            i=st.executeUpdate(q1);
	                                            if(i==1)
	                                             {
	                        	                   out.print("<html><body>");
	                        	                   out.print("<h1>Data Withdraw successfully</h1>");
	                        	                   out.print("<p>your balance is decrease to "+amum+"Rup.</p>");
	                        	                   out.print("</body></html>"); 
	                                              }
	                                            else
	                                             {
	                        	                  out.print("either password or account not match");
	  	                                          RequestDispatcher rd=request.getRequestDispatcher("/withdraw.html");
	  	                		                  rd.include(request, response);
	                                             }
	                                  }
	                                  else
	                                  {
	                                   out.print("<html><body>");
                   	                   out.print("<h1>Data Withdraw unsuccessfully</h1>");
                   	                   out.print("<p>your  withdraw amount "+amu+" Rup. is high from current balance to "+amount1+"Rup.</p>");
                   	                   out.print("</body></html>");  
	                                  }
	                        }
	                        else
	                        {
	                        	 out.print("either password or account not match");
	                                 RequestDispatcher rd=request.getRequestDispatcher("/withdraw.html");
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

