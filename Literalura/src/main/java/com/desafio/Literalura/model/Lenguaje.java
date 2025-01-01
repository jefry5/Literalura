package com.desafio.Literalura.model;

import java.text.Normalizer;
import java.util.regex.Pattern;

//Contiene los 50 lenguajes más frecuentes basado en el codigo ISO 639-1
public enum Lenguaje {
    INGLES("en", "Inglés"),
    ESPANOL("es", "Español"),
    FRANCES("fr", "Francés"),
    ALEMAN("de", "Alemán"),
    ITALIANO("it", "Italiano"),
    PORTUGUES("pt", "Portugués"),
    CHINO("zh", "Chino"),
    JAPONES("ja", "Japonés"),
    RUSO("ru", "Ruso"),
    ARABE("ar", "Árabe"),
    HINDI("hi", "Hindi"),
    BENGALI("bn", "Bengalí"),
    PUNJABI("pa", "Punjabi"),
    INDONESIO("id", "Indonesio"),
    COREANO("ko", "Coreano"),
    VIETNAMITA("vi", "Vietnamita"),
    TURCO("tr", "Turco"),
    TAILANDES("th", "Tailandés"),
    UCRANIANO("uk", "Ucraniano"),
    HOLANDES("nl", "Holandés"),
    POLACO("pl", "Polaco"),
    SUECO("sv", "Sueco"),
    HEBREO("he", "Hebreo"),
    CHECO("cs", "Checo"),
    DANES("da", "Danés"),
    FINLANDES("fi", "Finlandés"),
    NORUEGO("no", "Noruego"),
    GRIEGO("el", "Griego"),
    HUNGARO("hu", "Húngaro"),
    SLOVACO("sk", "Eslovaco"),
    CATALAN("ca", "Catalán"),
    SERBIO("sr", "Serbio"),
    CROATA("hr", "Croata"),
    MALAYO("ms", "Malayo"),
    FILIPINO("fil", "Filipino"),
    PERSA("fa", "Persa"),
    SWAHILI("sw", "Swahili"),
    AFRIKAANS("af", "Afrikáans"),
    LITUANO("lt", "Lituano"),
    LETON("lv", "Letón"),
    ESTONIO("et", "Estonio"),
    GEORGIANO("ka", "Georgiano"),
    SLOVENO("sl", "Esloveno"),
    BIELORRUSO("be", "Bielorruso"),
    ALBANES("sq", "Albanés"),
    BULGARO("bg", "Búlgaro"),
    ROMANO("ro", "Rumano"),
    AMHARICO("am", "Amhárico"),
    UZBECO("uz", "Uzbeko"),
    KAZAJO("kk", "Kazajo");

    private String lenguajeGutendex;
    private String lenguajeEspanol;

    Lenguaje(String lenguajeGutendex, String lenguajeEspanol) {
        this.lenguajeGutendex = lenguajeGutendex;
        this.lenguajeEspanol = lenguajeEspanol;
    }

    public static Lenguaje fromString(String text) {
        for (Lenguaje lenguaje : Lenguaje.values()) {
            if (lenguaje.lenguajeGutendex.equalsIgnoreCase(text)) {
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado '" + text + "'");
    }

    public static Lenguaje fromEspanol(String text) {
        text = removerAcento(text);
        for (Lenguaje lenguaje : Lenguaje.values()) {
            if (removerAcento(lenguaje.lenguajeEspanol).equalsIgnoreCase(text)) {
                return lenguaje;
            }
        }
        throw new IllegalArgumentException("Ningun lenguaje encontrado '" + text + "'");
    }

    //Extrae el acento de una cadena
    private static String removerAcento(String text) {
        if (text == null) {
            return null;
        }
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }
}
