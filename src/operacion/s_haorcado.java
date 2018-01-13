package operacion;
import java.net.*;
import java.io.*;
import java.util.Random;
public class s_haorcado{
    
    public static int existe(char aux[],char l){
        int pos=-1;
        for(int i=0;i<aux.length;i++){
            if(aux[i]==l){
                pos=i;
                break;
            }
        }
        return pos;
    }
    
    public static void main(String[]args){
        String facil[]={"hola","maquina","papel","manos","disco","botella","casco"};
        String media[]={"videojuego","mastodonte","computadora","campismo"};
        String dificil[]={"matematicas","encantadamente","ferrocarril"};
        int aleatorio=0;
        Random ran=new Random();
        String palabra="";
        try{
            ServerSocket s=new ServerSocket(1234);
            System.out.println("Servicio iniciado... esperando clientes");
            for(;;){
                Socket cl=s.accept();
                System.out.println("Cliente conectado desde "+cl.getInetAddress()+":"+cl.getPort());
                PrintWriter pw=new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                BufferedReader br=new BufferedReader(new InputStreamReader(cl.getInputStream()));
                int dif=Integer.parseInt(br.readLine());
                switch(dif){
                    case 1:
                        aleatorio=ran.nextInt(7);
                        palabra=facil[aleatorio];
                    break;
                    case 2:
                        aleatorio=ran.nextInt(4);
                        palabra=media[aleatorio];
                    break;
                    case 3:
                        aleatorio = ran.nextInt(3);
                        palabra=dificil[aleatorio];
                    break;
                }
                pw.println(palabra.length()+"");
                pw.flush();
                char auxpal[]=palabra.toCharArray();
                for(;;){
                    String msj=br.readLine();
                    if(msj.toLowerCase().indexOf("ex")>=0){
                        pw.println(palabra);
                        pw.flush();
                        br.close();
                        pw.close();
                        cl.close();
                        break;
                    }else{
                        //int ind=palabra.indexOf(msj);
                        int ind=existe(auxpal,msj.charAt(0));
                        if(ind>=0){
                            pw.println(ind+"");
                            pw.println(""+auxpal[ind]);
                            pw.flush();
                            auxpal[ind]=' ';
                        }else{
                            pw.println("-");
                            pw.println("");
                            pw.flush();
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}