package bank;
import java.sql.*;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;
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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public class pdf  extends  HttpServlet {
	public static  void pdfcreate(String name,String password,String email,String amount,String address,String city,String contact)
	{
	final String url="jdbc:mysql://localhost:3306/bank";
    final String uname="root";
    final String pass="";
		String query="select  *from accountinfo where name='"+name+"' and password='"+password+"' and email='"+email+"' and amount='"+amount+"' and address='"+address+"' and city='"+city+"' and contact='"+contact+"'";
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
	                        	try
	                        	{
	                        	Document doc=new Document();
	                            PdfWriter writer=PdfWriter.getInstance(doc,new FileOutputStream("C:\\bank\\bankdetail.pdf"));
	                        	doc.open();
	                        	PdfPTable pdt=new PdfPTable(8);
	                        	PdfPCell c0=new PdfPCell(new Phrase("Account number::"));
	                        	PdfPCell c1=new PdfPCell(new Paragraph("Name::"));
	                        	PdfPCell c2=new PdfPCell(new Paragraph("Password::"));
	                            PdfPCell c3=new PdfPCell(new Paragraph("Email::"));
	                        	PdfPCell c4=new PdfPCell(new Paragraph("Amount::"));
	                        	PdfPCell c5=new PdfPCell(new Paragraph("Address"));
	                        	PdfPCell c6=new PdfPCell(new Paragraph("City::"));
	                        	PdfPCell c7=new PdfPCell(new Paragraph("Contact::"));
	                        	pdt.setHeaderRows(1);
	                        	pdt.addCell(c0);
	                        	pdt.addCell(c1);
	                        	pdt.addCell(c2);
	                        	pdt.addCell(c3);
	                        	pdt.addCell(c4);
	                        	pdt.addCell(c5);
	                        	pdt.addCell(c6);
	                        	pdt.addCell(c7);
	                        	pdt.addCell(rs.getString(1));
	                        	pdt.addCell(rs.getString(2));
	                        	pdt.addCell(rs.getString(3));
	                        	pdt.addCell(rs.getString(4));
	                        	pdt.addCell(rs.getString(5));
	                        	pdt.addCell(rs.getString(6));
	                        	pdt.addCell(rs.getString(7));
	                        	pdt.addCell(rs.getString(8));
	                            doc.add(pdt);
	                        	doc.close();
	                        	System.out.println("pdf created");
	                        
	                        	} 
	                        	catch (DocumentException e1)
	                        	{
	                        		// TODO Auto-generated catch block
	                        		e1.printStackTrace();
	                        	} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	                        conn.close();
		                   }
	                       
	        }
			catch(ClassNotFoundException e)
			{
				System.out.println("Exception::"+e.getMessage());
			}
	        catch(SQLException e)
	        {
	           System.out.println("sql exception::"+e.getMessage());
	        }
	  mailer.send();
	}
}
	