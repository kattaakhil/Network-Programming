import java.io.*;
import java.net.*;
import java.util.*;
public class TFTPClient
{
static InetAddress host;
static DatagramSocket dss;
static DatagramPacket outPacket,inPacket;
static byte[] buffer;
static int port=69;
public static void main(String[] args)
{
try
{
host=InetAddress.getByName("172.16.4.118");
}
catch(UnknownHostException uhe)
{
System.out.println("Host not found");
}
try
{
dss=new DatagramSocket();
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("Enter file name to be received from server");
String str1,finput;
BufferedWriter bw;
while(!(str1=br.readLine()).equals("null"))
{
String str2="D:\\mypro\\NP LAB\\"+str1;
System.out.println("Haiiiiii");
outPacket=new DatagramPacket(str1.getBytes(),str1.length(),host,port);
dss.send(outPacket);
bw=new BufferedWriter(new FileWriter(str2));
System.out.println("Created File input stream");
do
{
System.out.println("Receiving file data");
buffer=new byte[512];
inPacket=new DatagramPacket(buffer,buffer.length);
dss.receive(inPacket);
System.out.println("Server Port="+inPacket.getPort());
finput=new String(inPacket.getData(),0,inPacket.getLength());
if(!(finput.equals("sent"))) bw.write(finput,0,finput.length());
bw.newLine();
bw.flush();
System.out.println(finput);
}
while(!finput.equals("sent"));
port=inPacket.getPort();
System.out.println(port);
bw.close();
}
}
catch(IOException ie)
{}
}
}

