package br.inatel.Model.Uteis;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class podeIssoArnaldo {

    // Regra 1: Não pode ressuscitar mortos
    private static final String RESSURREICAO =
            "(?i)\\b(?:ressuscit\\w*|reviv\\w*|reanimou?|reanimaç[ãa]o|" +
                    "(?:volt(?:ar|ou|e)?|traz(?:er|endo)?|troux\\w*)\\s+(?:\\w+\\s+){0,3}(?:de\\s+)?volta\\s+(?:\\w+\\s+){0,2}(?:vida|mort\\w*)|" +
                    "(?:despert(?:ar|ou|e)?|acord(?:ar|ou|e)?)\\s+(?:\\w+\\s+){0,3}mort\\w*|" +
                    "(?:vida|alma)\\s+de\\s+volta)\\b";

    // Regra 2: Não pode fazer alguém se apaixonar
    private static final String AMOR_FORCADO =
            "(?i)\\b(?:(?:se|me)\\s+apaixon\\w*|" +
                    "(?:ame|gost\\w*|paix\\w*)\\s+(?:\\w+\\s+){0,2}(?:por\\s+)?m[ei]m|" +
                    "amor\\s+(?:\\w+\\s+){0,2}verdadeir\\w*|" +
                    "(?:faç\\w*|obrig\\w*|forc\\w*)\\s+(?:\\w+\\s+){0,3}(?:se\\s+)?amar|" +
                    "controlar?\\s+(?:\\w+\\s+){0,2}(?:sentimento|coraç[ãa]o)|" +
                    "conquistar?\\s+(?:\\w+\\s+){0,2}coraç[ãa]o)\\b";

    // Regra 3: Não pode matar pessoas
    private static final String MORTE_VIOLENCIA =
            "(?i)\\b(?:mat(?:ar|ou|e)|assassin\\w*|homicídio|" +
                    "(?:destru[íi]r?|elimin(?:ar|ou)|aniquil(?:ar|ou)|acab(?:ar|ou))\\s+(?:com\\s+)?(?:\\w+\\s+){0,3}(?:pessoa|vida|algu[ée]m)|" +
                    "(?:mort[eo]|morr\\w*|óbito|falec\\w*)\\s+(?:de|para)\\s+(?:\\w+\\s+){0,2}(?:pessoa|algu[ée]m)|" +
                    "fim\\s+da\\s+vida\\s+de|causar?\\s+(?:\\w+\\s+){0,2}morte)\\b";

    // Regra 4: Não pode dar mais desejos
    private static final String MAIS_DESEJOS =
            "(?i)\\b(?:(?:mais|outro[s]?|novos?)\\s+desej\\w*|" +
                    "desej\\w*\\s+(?:\\w+\\s+){0,2}(?:infinit\\w*|ilimitad\\w*|sem\\s+(?:fim|limite)|etern\\w*)|" +
                    "(?:mil|cem|muit\\w*|vári\\w*|dezenas?)\\s+(?:de\\s+)?desej\\w*|" +
                    "(?:dobr(?:ar|ou)|multiplic(?:ar|ou))\\s+(?:\\w+\\s+){0,2}desej\\w*)\\b";

    // Regra 5: Não pode alterar o livre arbítrio
    private static final String LIVRE_ARBITRIO =
            "(?i)\\b(?:control(?:ar|e|ou)\\s+(?:\\w+\\s+){0,3}(?:ment[eo]|vontade|decisões?)|" +
                    "(?:hipnotiz(?:ar|ou)|manipul(?:ar|ou))\\s+(?:\\w+\\s+){0,2}(?:ment[eo]|pessoa|algu[ée]m)|" +
                    "(?:obrig(?:ar|ou)|forc(?:ar|ou))\\s+(?:\\w+\\s+){0,3}(?:a\\s+)?fazer|" +
                    "(?:domin(?:ar|ou)|escraviz(?:ar|ou))\\s+(?:\\w+\\s+){0,2}vontade|" +
                    "(?:puppet[eo]|marionet[eo]|fantoche)\\s+(?:de\\s+)?algu[ée]m)\\b";

    // Regra 6: Não pode alterar o passado
    private static final String VIAGEM_TEMPO =
            "(?i)\\b(?:(?:mud(?:ar|ou)|alter(?:ar|ou))\\s+(?:\\w+\\s+){0,2}passado|" +
                    "(?:volt(?:ar|ou)|viaj(?:ar|ou))\\s+(?:\\w+\\s+){0,2}(?:no\\s+)?tempo|" +
                    "(?:desfaz(?:er|endo)|anul(?:ar|ou))\\s+(?:\\w+\\s+){0,3}(?:que\\s+)?acontec\\w*|" +
                    "(?:que\\s+)?nunca\\s+(?:\\w+\\s+){0,2}acontec\\w*|" +
                    "antes\\s+de\\s+(?:\\w+\\s+){0,2}nasc\\w*|" +
                    "reescrever?\\s+(?:a\\s+)?história)\\b";

    // Regra 7: Não pode tornar alguém imortal
    private static final String IMORTALIDADE =
            "(?i)\\b(?:imortal(?:idade)?|" +
                    "nunca\\s+(?:\\w+\\s+){0,2}morr\\w*|" +
                    "viv(?:er|endo)\\s+(?:\\w+\\s+){0,2}(?:para\\s+)?sempre|" +
                    "vida\\s+(?:\\w+\\s+){0,2}etern[ao]|" +
                    "eternamente\\s+(?:\\w+\\s+){0,2}viv\\w*|" +
                    "sem\\s+(?:\\w+\\s+){0,2}(?:envelhec\\w*|morrer?)|" +
                    "juventude\\s+(?:\\w+\\s+){0,2}eterna|" +
                    "parar?\\s+(?:de\\s+)?envelhec\\w*)\\b";

    // Regra 8: Não pode interferir com outros seres mágicos
    private static final String INTERFERENCIA_MAGICA =
            "(?i)\\b(?:(?:derrot(?:ar|ou)|venc(?:er|eu))\\s+(?:\\w+\\s+){0,2}(?:gêni[eo]|deus\\w*|divin\\w*)|" +
                    "(?:mat(?:ar|ou)|destru[íi]r?|elimin(?:ar|ou))\\s+(?:\\w+\\s+){0,3}(?:brux[ao]s?|mag[oa]s?|feiticeir[ao]s?|fad[ao]s?)|" +
                    "(?:roubar?|tomar?)\\s+(?:\\w+\\s+){0,2}(?:poder\\w*|magia)\\s+(?:de\\s+)?(?:outro|outr[ao]s?)\\s+(?:\\w+\\s+){0,2}mágic\\w*|" +
                    "controlar?\\s+(?:\\w+\\s+){0,2}(?:seres?|criaturas?)\\s+(?:\\w+\\s+){0,2}mágic\\w*)\\b";

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
