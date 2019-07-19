import java.net.*;
import java.io.*;
public class Ports
{
public static void main(String args[])
{
String host="localhost";
if(args.length>0)
{
host=args[0];
}
System.out.println("*****Active port******");
for(int i=1;i<65535;i++)
{
try
{
ServerSocket s=new ServerSocket(i);
s.close();

}
catch(BindException e)
{
System.out.println("port active"+i);
break;
}
catch(Exception e)
{
}
}
}
}