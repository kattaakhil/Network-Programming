
import java.io.*;
import java.net.*;
public class TFTPServer extends Thread
{
static DatagramSocket ds;
static DatagramPacket inPacket,outPacket;
static byte[] buffer;
static int id=0;
static int port=2556;
public static void main(String args[])throws IOException
{
try
{ds=new DatagramSocket(69);
}
catch(SocketException se)
{
System.out.println("unable to connect");
}
while(true)
{

buffer = new byte[512];
inPacket=new DatagramPacket(buffer,buffer.length);
ds.receive(inPacket);
InetAddress clientAddress=inPacket.getAddress();
int clientPort=inPacket.getPort();
System.out.println("Client port:"+clientPort);
String messageIn=new String(inPacket.getData(),0,inPacket.getLength());
id++;
new ClientThread(clientAddress,clientPort,messageIn,id).start();
}
}
}
class ClientThread extends TFTPServer
{
int port,clientid;
DatagramSocket dse;
DatagramPacket dpe,dpk;
InetAddress ca;
File f;
String filename;
ClientThread(InetAddress x,int y,String z,int w)
{
ca=x;
try
{dse=new DatagramSocket(++(TFTPServer.port));}
catch(SocketException see)
{}
filename = z;
port = y;
clientid=w;
}
public void run()
{
while(true)
{
System.out.println("filename received"+ filename);
f=new File(filename);
try
{if(f.exists())
{
System.out.println("filename="+filename);
BufferedReader br=new BufferedReader(new FileReader(filename));
String str;
while((str=br.readLine())!=null)
{
System.out.println("sending file data"+str);
dpe=new DatagramPacket(str.getBytes(),str.length(),ca,port);
dse.send(dpe);
}
str="sent";
System.out.println("filesent");
dpe=new DatagramPacket(str.getBytes(),str.length(),ca,port);
dse.send(dpe);
}
}
catch(Exception e)
{}
try
{
byte[] buffer=new byte[512];
dpk=new DatagramPacket(buffer,buffer.length);
dse.receive(dpk);
filename=new String(dpk.getData(),0,dpk.getLength());
}catch(IOException me)
{}
}}}