import java.net.*;
class UDPportscanner
{
public static void main(String args[])
{
for(int i=0;i<=65535;i++)
{
try
{
DatagramSocket s=new DatagramSocket(i);
s.close();
}
catch(BindException e)
{
System.out.println("port active"+i);
}
catch(Exception ie)
{}
}
}
}