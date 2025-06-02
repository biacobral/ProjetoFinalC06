package br.inatel.Model;
import java.lang.Thread;

public class Util {

    public static void esperaAi(int tempo){
        try{
            Thread.sleep(tempo);
        }
        catch(Exception e){
            System.out.println("Deu não, patrão.");
        }
    }

}
