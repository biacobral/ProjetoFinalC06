package br.inatel.Model.Personagens;

import static br.inatel.Model.Uteis.Util.esperaAi;

public class AntiFada extends Fada {

    public AntiFada(int idFada, String nomeFada, String tipoFada, int varinha_idSerial) {
        super(idFada, nomeFada, tipoFada, varinha_idSerial);
    }

    public AntiFada(String nomeFada, String tipoFada, int varinha_idSerial) {
        super(nomeFada, tipoFada, varinha_idSerial);
    }

    public void inicioCombate(){
        System.out.println("\n💀 " + this.getNomeFada() + " ergue-se nas sombras com uma risada sinistra...");
        esperaAi(2000);
        System.out.println("😈 \"FINALMENTE... Depois de séculos nas sombras, observando vocês");
        esperaAi(1500);
        System.out.println("   espalharem esperança e alegria pelo mundo... CHEGOU A MINHA HORA!\"");
        esperaAi(2000);

        System.out.println("\n🌪️ \"Vocês pensam que a magia existe apenas para conceder desejos tolos?");
        esperaAi(1500);
        System.out.println("   Para espalhar felicidade barata? ESTÃO ENGANADOS!\"");
        esperaAi(2000);

        System.out.println("\n⚡ \"Eu sou o equilíbrio que este mundo precisa! Onde vocês veem luz,");
        esperaAi(1500);
        System.out.println("   eu trago as trevas necessárias! Onde vocês plantam sonhos, eu colho desilusões!\"");
        esperaAi(2000);

        System.out.println("\n💀 \"Sua varinha... essa fonte patética de poder... será MINHA!");
        esperaAi(1500);
        System.out.println("   E com ela, transformarei cada desejo em pesadelo, cada esperança em desespero!\"");
        esperaAi(2000);

        System.out.println("\n😈 \"Prepare-se, pequena fada, pois hoje você descobrirá que nem toda");
        esperaAi(1500);
        System.out.println("   magia tem final feliz! O reino dos sonhos se tornará o império das TREVAS ETERNAS!\"");
        esperaAi(2000);
        System.out.println("\n🌩️ \"MWAHAHAHAHAHA!\" *risada ecoando pelas dimensões*");
        esperaAi(2500);
    }

    public void ganhaBatalha(){
        System.out.println("\n👑 \"SIIIIM! Sinto o poder correndo pelas minhas veias!\"");
        esperaAi(1500);
        System.out.println("💀 \"Agora EU sou a dona dos desejos! Que a era da felicidade termine...\"");
        esperaAi(1500);
        System.out.println("😈 \"...e que comece o REINADO DAS SOMBRAS! MWAHAHAHA!\"");
        esperaAi(2000);
    }

    public void perdeBatalha(){
        System.out.println("\n💔 \"IMPOSSÍVEL! Como sua patética felicidade pode ser mais forte");
        esperaAi(1500);
        System.out.println("   que meu ódio milenar?!\"");
        esperaAi(2000);
        System.out.println("🌑 \"Isso não acabou... EU VOLTAREI quando as trevas em seu coração crescerem!\"");
        esperaAi(2000);
        System.out.println("👻 *desaparece entre sombras com um grito de fúria*");
        esperaAi(1500);
    }
}
