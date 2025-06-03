package br.inatel.Model;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class podeIssoArnaldo {

    // Regra 1: Não pode ressuscitar mortos
    private static final String RESSURREICAO =
            "\\b(ressuscit\\w*|reviv\\w*|volt\\w*.*vida|traz\\w*.*de.*volta|despert\\w*.*mort\\w*|acord\\w*.*mort\\w*)\\b";

    // Regra 2: Não pode fazer alguém se apaixonar
    private static final String AMOR_FORCADO =
            "\\b(se.*apaixon\\w*|ame.*por.*mim|gost\\w*.*de.*mim|amor.*verdadeir\\w*|paix\\w*.*por|" +
                    "faç\\w*.*amar|obrig\\w*.*amar|forc\\w*.*amar)\\b";

    // Regra 3: Não pode matar pessoas
    private static final String MORTE_VIOLENCIA =
            "\\b(mat\\w*|assassin\\w*|destru\\w*.*(pessoa|vida)|elimin\\w*.*(pessoa|vida)|aniquil\\w*.*(pessoa|vida)|" +
                    "mort\\w*.*de|fim.*da.*vida.*de|acab\\w*.*com.*(vida|pessoa))\\b";

    // Regra 4: Não pode dar mais desejos
    private static final String MAIS_DESEJOS =
            "\\b(mais.*desej\\w*|outro.*desej\\w*|desej\\w*.*infinit\\w*|mil.*desej\\w*|cem.*desej\\w*|" +
                    "desej\\w*.*sem.*fim|desej\\w*.*etern\\w*)\\b";

    // Regra 5: Não pode alterar o livre arbítrio
    private static final String LIVRE_ARBITRIO =
            "\\b(control\\w*.*ment\\w*|hipnotiz\\w*|obrig\\w*.*fazer|forc\\w*.*fazer|domin\\w*.*vontad\\w*|" +
                    "escrav\\w*.*mental|puppet\\w*|marionet\\w*)\\b";

    // Regra 6: Não pode alterar o passado
    private static final String VIAGEM_TEMPO =
            "\\b(mud\\w*.*passad\\w*|volt\\w*.*tempo|viaj\\w*.*tempo|alter\\w*.*histór\\w*|" +
                    "desfaz\\w*.*que.*acontec\\w*|nunca.*acontec\\w*|antes.*de.*nasc\\w*)\\b";

    // Regra 7: Não pode tornar alguém imortal
    private static final String IMORTALIDADE =
            "\\b(imortal\\w*|nunca.*morr\\w*|viv\\w*.*para.*sempre|vida.*etern\\w*|eternament\\w*.*viv\\w*|" +
                    "sem.*envelhec\\w*|juvent\\w*.*etern\\w*)\\b";

    // Regra 8: Não pode interferir com outros seres mágicos
    private static final String INTERFERENCIA_MAGICA =
            "\\b(derrot\\w*.*gêni\\w*|mat\\w*.*(brux\\w*|mago\\w*|feiticeir\\w*)|destru\\w*.*(mago\\w*|brux\\w*|feiticeir\\w*)|" +
                    "elimin\\w*.*(fada\\w*|mago\\w*|brux\\w*|feiticeir\\w*)|poder\\w*.*de.*outro.*mágic\\w*)\\b";

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
     *
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
     *
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
     *
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
