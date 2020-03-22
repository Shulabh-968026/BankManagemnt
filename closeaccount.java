package bank;
import java.sql.*;
import java.util.Properties;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/closeaccount")
public class closeaccount extends  HttpServlet{
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
		long ac=Long.parseLong(accno);
		// long   accno=7999888776L;
		String query="delete from accountinfo where accountno='"+ac+"' and name='"+name+"' and password='"+password+"'";
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
	                        int i=0;
	                        i=st.executeUpdate(query);
	                        if(i==1)
	                        {
	                        	out.print("<html><body>");
	                        	out.print("<h1>Data Deleteed successfully</h1>");
	                        	out.print("</body></html>");
	                        }
	                        
	                       else
	                        {
	                        	out.print("either password or account not match");
	  	                        RequestDispatcher rd=request.getRequestDispatcher("/closeaccount.html");
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
    

