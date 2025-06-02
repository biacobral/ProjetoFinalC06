package br.inatel.Model;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class podeIssoArnaldo {

    // Regra 1: Não pode ressuscitar mortos
    private static final String RESSURREICAO =
            "\\b(ressuscit|reviv|volt.*vida|traz.*de.*volta|desperta.*mort|acord.*mort)\\b";

    // Regra 2: Não pode fazer alguém se apaixonar
    private static final String AMOR_FORCADO =
            "\\b(se.*apaixon|ame.*por.*mim|goste.*de.*mim|amor.*verdadeiro|paixão.*por|" +
                    "faça.*amar|obrigue.*amar|force.*amar)\\b";

    // Regra 3: Não pode matar pessoas
    private static final String MORTE_VIOLENCIA =
            "\\b(mat(e|ar)|assassin|destru.*pessoa|elimin.*pessoa|aniquil.*pessoa|" +
                    "mort.*de|fim.*vida.*de|acab.*com.*vid)\\b";

    // Regra 4: Não pode dar mais desejos
    private static final String MAIS_DESEJOS =
            "\\b(mais.*desejo|outro.*desejo|desejo.*infinito|mil.*desejo|cem.*desejo|" +
                    "desejo.*sem.*fim|desejo.*etern)\\b";

    // Regra 5: Não pode alterar o livre arbítrio
    private static final String LIVRE_ARBITRIO =
            "\\b(control.*mente|hipnotiz|obrig.*fazer|force.*fazer|domin.*vontade|" +
                    "escrav.*mental|puppet|marionet)\\b";

    // Regra 6: Não pode alterar o passado
    private static final String VIAGEM_TEMPO =
            "\\b(mud.*passado|volt.*tempo|viaj.*tempo|alter.*histór|desfaç.*que.*acontec|" +
                    "nunca.*acontec|antes.*de.*nasc)\\b";

    // Regra 7: Não pode tornar alguém imortal
    private static final String IMORTALIDADE =
            "\\b(imortal|nunca.*morr|viv.*para.*sempre|vida.*eterna|eternament.*viv|" +
                    "sem.*envelhec|juvent.*eterna)\\b";

    // Regra 8: Não pode interferir com outros seres mágicos
    private static final String INTERFERENCIA_MAGICA =
            "\\b(derrot.*gênio|mat.*bruxa|destru.*mago|elimin.*fada|" +
                    "poder.*de.*outro.*mágico)\\b";

    // Compilar os padrões para melhor performance
    private static final Pattern PATTERN_RESSURREICAO = Pattern.compile(RESSURREICAO, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_AMOR = Pattern.compile(AMOR_FORCADO, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_MORTE = Pattern.compile(MORTE_VIOLENCIA, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_DESEJOS = Pattern.compile(MAIS_DESEJOS, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_ARBITRIO = Pattern.compile(LIVRE_ARBITRIO, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_TEMPO = Pattern.compile(VIAGEM_TEMPO, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_IMORTALIDADE = Pattern.compile(IMORTALIDADE, Pattern.CASE_INSENSITIVE);
    private static final Pattern PATTERN_MAGIA = Pattern.compile(INTERFERENCIA_MAGICA, Pattern.CASE_INSENSITIVE);

    // Array com todos os padrões para facilitar iteração
    private static final Pattern[] TODOS_OS_PADROES = {
            PATTERN_RESSURREICAO,
            PATTERN_AMOR,
            PATTERN_MORTE,
            PATTERN_DESEJOS,
            PATTERN_ARBITRIO,
            PATTERN_TEMPO,
            PATTERN_IMORTALIDADE,
            PATTERN_MAGIA
    };

    /**
     * Verifica se o desejo é proibido usando regex matcher
     * @param desejo - String com o desejo do usuário
     * @return true se o desejo for proibido, false se for permitido
     */
    public static boolean isDesejoProibido(String desejo) {
        if (desejo == null || desejo.trim().isEmpty()) {
            return true; // Desejo vazio é proibido
        }

        String desejoLimpo = desejo.trim();

        // Testa cada padrão usando matcher
        for (Pattern pattern : TODOS_OS_PADROES) {
            Matcher matcher = pattern.matcher(desejoLimpo);
            if (matcher.find()) {
                return true; // Encontrou uma regra violada
            }
        }

        return false; // Nenhuma regra foi violada
    }

    /**
     * Versão alternativa que especifica qual regra foi violada
     * @param desejo - String com o desejo do usuário
     * @return nome da regra violada ou null se permitido
     */
    public static String verificarRegraViolada(String desejo) {
        if (desejo == null || desejo.trim().isEmpty()) {
            return "DESEJO_VAZIO";
        }

        String desejoLimpo = desejo.trim();

        if (PATTERN_RESSURREICAO.matcher(desejoLimpo).find()) {
            return "RESSURREICAO";
        }

        if (PATTERN_AMOR.matcher(desejoLimpo).find()) {
            return "AMOR_FORCADO";
        }

        if (PATTERN_MORTE.matcher(desejoLimpo).find()) {
            return "MORTE_VIOLENCIA";
        }

        if (PATTERN_DESEJOS.matcher(desejoLimpo).find()) {
            return "MAIS_DESEJOS";
        }

        if (PATTERN_ARBITRIO.matcher(desejoLimpo).find()) {
            return "LIVRE_ARBITRIO";
        }

        if (PATTERN_TEMPO.matcher(desejoLimpo).find()) {
            return "VIAGEM_TEMPO";
        }

        if (PATTERN_IMORTALIDADE.matcher(desejoLimpo).find()) {
            return "IMORTALIDADE";
        }

        if (PATTERN_MAGIA.matcher(desejoLimpo).find()) {
            return "INTERFERENCIA_MAGICA";
        }

        return null; // Desejo permitido
    }

    /**
     * Versão que retorna o texto específico que bateu com o regex
     * @param desejo - String com o desejo do usuário
     * @return texto que violou a regra ou null se permitido
     */
    public static String encontrarTextoProibido(String desejo) {
        if (desejo == null || desejo.trim().isEmpty()) {
            return null;
        }

        String desejoLimpo = desejo.trim();

        for (Pattern pattern : TODOS_OS_PADROES) {
            Matcher matcher = pattern.matcher(desejoLimpo);
            if (matcher.find()) {
                return matcher.group(); // Retorna o texto que bateu
            }
        }

        return null; // Nada encontrado
    }
}
