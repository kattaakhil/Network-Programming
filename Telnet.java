import java.net.*;
import java.io.*;
import java.util.*;
public class Telnet extends Thread
{
static Socket cs=null;
static PrintWriter pw=null;
static BufferedReader br=null,in=null;
public static void main(String[] args) throws Exception
{
System.out.println("TELNET:>");
in=new BufferedReader(new InputStreamReader(System.in));
String command=in.readLine();
StringTokenizer st=new StringTokenizer(command);
String service=st.nextToken();
if(service.equals("open"))
{
String address=st.nextToken();
String port=st.nextToken();
if(!port.equals("null"))
{
open(address,port);
}
}
else
if(service.equals("quit")) System.exit(0);
else System.out.println("Invalid, Try help");
}
static void open(String address,String port) throws Exception

{
try
{
if(address.equals("null"))
{
System.out.println("Specify Correct address");
return;
}
System.out.println("waiting for a connection");
cs=new Socket(address,Integer.parseInt(port));
br=new BufferedReader(new InputStreamReader(cs.getInputStream()));
pw=new PrintWriter(cs.getOutputStream(),true);
System.out.println("Connection established");
new SendData(pw,in).start();
new ReceiveData(br).start();
}
catch(Exception e)
{
System.out.println("unable to connect");
}
}
static class SendData extends Telnet
{
PrintWriter pw=null;
BufferedReader in=null;
SendData(PrintWriter p,BufferedReader b)
{
pw=p;
in=b;
}
public void run()
{
while(true)
{
try
{
pw.println(in.readLine());
}
catch(IOException e)
{}
}
}
}
static class ReceiveData extends Telnet
{
BufferedReader br=null;
ReceiveData(BufferedReader b)
{
br=b;
}
public void run()
{
while(true)
{
try
{
System.out.println(br.readLine());
}
catch(IOException e)
{}
}
}
}
}