import java.io.*;
import java.net.*;
import java.util.*;
public class Smtp{
public static void main(String args[])throws Exception{
Socket s=null;
try{
s=new Socket("192.168.0.113",25);
System.out.println("Connected to server");
}
catch(Exception e){}
PrintWriter out=new PrintWriter(s.getOutputStream(),true);
BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
in.readLine();
out.println("helo");
in.readLine();
System.out.println("Enter ur mail id");
String sid=sin.readLine().trim();
out.println("mail from:"+sid);
System.out.println(in.readLine());
System.out.println("Enter reciever mail id");
String rid=sin.readLine().trim();
out.println("rcpt to:"+rid);
System.out.println(in.readLine());
System.out.println("Enter ur Message to send");
String msg=sin.readLine().trim();
out.println("data");
in.readLine();
out.println(msg);
out.println(".");
System.out.println(in.readLine());
System.out.println("mail sent");
}
}