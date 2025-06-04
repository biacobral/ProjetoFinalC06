package br.inatel.Model.Uteis;

import static br.inatel.Model.Uteis.Util.esperaAi;

public class NaoPodeSerTriste extends Exception {
    public NaoPodeSerTriste() {
        System.out.println("✨═══════════════════════════════════════✨");
        System.out.println("🌟     INTERVENÇÃO DIVINA DETECTADA!     🌟");
        System.out.println("✨═══════════════════════════════════════✨");
        esperaAi(1500);
        System.out.println("☁️  Uma voz ecoa dos céus...");
        esperaAi(1000);
        System.out.println("👼 Deus: 'Opa, opa, opa! Calma lá, parceiro!'");
        esperaAi(1500);
        System.out.println("😇 Deus: 'Não pode ser tão triste assim também, né!'");
        esperaAi(1000);
        System.out.println("🌈 Deus: 'Olha só quantas coisas bonitas ainda tem pra viver!'");
        esperaAi(1500);
        System.out.println("💖 Uma sensação calorosa toma conta do seu peito...");

    }
}
