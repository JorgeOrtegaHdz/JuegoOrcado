package operacion;
import java.net.*;
import java.io.*;

public class c_haorca{
    public static void main(String[]args){
        int longitud,longi;
        char palabra[]={'_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_','_'};
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Escribe la direccion del servidor:  ");
            String host=br.readLine();
            System.out.print("\n\nEscribe el puerto: ");
            int pto=Integer.parseInt(br.readLine());
            Socket cl=new Socket(host,pto);
            PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            BufferedReader br2=new BufferedReader(new InputStreamReader(cl.getInputStream()));
            System.out.println("DIFICULTADES:  ");
            System.out.println("1.-Facil");
            System.out.println("2.-Media");
            System.out.println("3.-Dificil");
            pw.println(br.readLine());
            pw.flush();
            longitud=Integer.parseInt(br2.readLine());
            longi=longitud;
            int errores=0;
            while(errores<6){
                for(int j=0;j<longitud;j++){
                    System.out.print(palabra[j]+" ");
                }
                System.out.println();
                if(longi<=0){
                    pw.println("ex");
                    pw.flush();
                    System.out.println("Usted a ganado!!  ");
                    pw.close();
                    br.close();
                    br2.close();
                    cl.close();
                    System.exit(0);
                }
                System.out.println("Dame una letra: ");
                pw.println(br.readLine());
                pw.flush();
                String dato=br2.readLine();
                String dato2=br2.readLine();
                //System.out.println("longi: "+longitud);
                switch(dato.charAt(0)){
                    case '-':
                        errores++;
                        System.out.println("Error numero: "+errores);
                    break;
                    default:
                        int pos=Integer.parseInt(dato);
                        System.out.println("Correcto!!");
                        palabra[pos]=dato2.charAt(0);
                        longi--;
                    break;
                }
            }
            System.out.println("Usted a perdido!!");
            pw.println("ex");
            pw.flush();
            System.out.println("La palabra correcta es: "+br2.readLine());
            pw.close();
            br.close();
            br2.close();
            cl.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}