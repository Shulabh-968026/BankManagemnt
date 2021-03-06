package bank;
import java.sql.*;
import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.print.Doc;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

@WebServlet("/openaccount")
public class openaccount extends  HttpServlet{
	static String url="jdbc:mysql://localhost:3306/bank";
    static String uname="root";
    static String pass="";
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		//((ServletResponse) request).setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String amount=request.getParameter("amount");
		String address=request.getParameter("address");
		String city=request.getParameter("city");
		String contact=request.getParameter("contact");
		// long   accno=7999888776L;
		String query="insert into accountinfo values('0','"+name+"','"+password+"','"+email+"','"+amount+"','"+address+"','"+city+"','"+contact+"')";
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
	                        boolean i;
	                        i=st.execute(query);
	                        if(true)
	                        {
	                        	out.print("<html><body>");
	                        	out.print("<h1>Data successfully inserted</h1>");
	                        	out.print("</body></html>");
	                        	pdf.pdfcreate(name,password,email,amount,address,city,contact);
	                        }
	                       else
	                        {
	                        	out.print("Data not inserted");
	  	                        RequestDispatcher rd=request.getRequestDispatcher("/openaccount.html");
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
