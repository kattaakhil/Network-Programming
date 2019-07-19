import java.net.*;
class RemotePortScanner1
{
public static void main(String args[])
{
for(int i=1;i<=1024;i++)
{
try
{
System.out.print("hju");
Socket s=new Socket("192.168.0.107",i);
System.out.print("hju");
System.out.println("port active  "+i);
s.close();
}
catch(Exception e)
{
	System.out.println(e);
}
}
}
}