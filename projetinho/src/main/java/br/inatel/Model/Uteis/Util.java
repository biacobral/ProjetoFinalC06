package br.inatel.Model.Uteis;
import java.lang.Thread;

import static br.inatel.Model.Personagens.Desejos.felicidade;

public class Util {

    public static void esperaAi(int tempo){
        try{
            Thread.sleep(tempo);
        }
        catch(Exception e){
            System.out.println("Deu não, patrão.");
        }
    }

    public static void diminuirFelicidade(int valor) throws NaoPodeSerTriste {
        if((felicidade-valor)<0){
            throw new NaoPodeSerTriste();
        }
        else{
            felicidade-=valor;
        }
    }
}
