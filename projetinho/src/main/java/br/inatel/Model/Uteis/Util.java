package br.inatel.Model.Uteis;
import java.lang.Thread;

import static br.inatel.Model.Personagens.Crianca.felicidade;

public class Util {

    public static void esperaAi(int tempo){
        try{
            Thread.sleep(tempo);
        }
        catch(Exception e){
            System.out.println("Deu não, patrão.");
        }
    }

    public static void diminuirFelicidade(int valor) throws NaoPodeSerTristeException {
        if((felicidade-valor)<0){
            throw new NaoPodeSerTristeException();
        }
        else{
            felicidade-=valor;
        }
    }
}
