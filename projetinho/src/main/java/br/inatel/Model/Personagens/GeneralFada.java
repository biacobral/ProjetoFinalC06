package br.inatel.Model.Personagens;

import static br.inatel.Model.Personagens.Crianca.felicidade;
import static br.inatel.Model.Uteis.Util.esperaAi;

public class GeneralFada extends Fada {

    public GeneralFada(int idFada, String nomeFada, String tipoFada, int varinha_idSerial) {
        super(idFada, nomeFada, tipoFada, varinha_idSerial);
    }

    public GeneralFada(String nomeFada, String tipoFada, int varinha_idSerial) {
        super(nomeFada, tipoFada, varinha_idSerial);
    }
    
    public void FarejarOProibido(){
        System.out.println("\n⚡═══════════════════════════════════════⚡");
        System.out.println("🌩️     MAS ALGO SINISTRO DESPERTA...   🌩️");
        System.out.println("⚡═══════════════════════════════════════⚡");
        esperaAi(2000);
        System.out.println("🌩️  TROVÕES ECOAM PELOS REINOS MÍSTICOS!");
        esperaAi(1750);
        System.out.println("💀 O AR SE TORNA DENSO COM ENERGIA SOMBRIA...");
        esperaAi(1750);
        System.out.println("\n" + this.nomeFada + ": '👃 Hmm... que aroma peculiar paira no ar...'");
        esperaAi(1750);
        System.out.println(this.nomeFada + ": '🔥 CHEIRO DE DESEJO PROIBIDO QUEIMA MINHAS NARINAS!'");
        esperaAi(1750);
        System.out.println(this.nomeFada + ": '😈 Está emanando de VOCÊ, pequeno transgressor!'");
        esperaAi(2000);
        System.out.println(this.nomeFada + ": '⚖️  OUSOU FAZER UM PEDIDO PROIBIDO PELOS ANCIÕES?!'");
        esperaAi(1750);
        System.out.println(this.nomeFada + ": '🏛️  E AINDA FOI CONCEDIDO?! QUE BLASFÊMIA CÓSMICA!'");
        esperaAi(2000);
        System.out.println("\n💀═══════════════════════════════════════💀");
        System.out.println("⚔️         O DESTINO ESTÁ SELADO!        ⚔️");
        System.out.println("💀═══════════════════════════════════════💀");
        esperaAi(1500);
        System.out.println("⚔️  VOCÊ E SEU PADRINHO SERÃO JULGADOS!");
        esperaAi(1000);
        System.out.println("🏛️  PELO SUPREMO TRIBUNAL DA MAGIA!");
        esperaAi(2500);
        System.out.println("🌟════════ PREPARANDO O JULGAMENTO FINAL ════════🌟");
        esperaAi(1000);
    }

}
