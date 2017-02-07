
package com.netsuite.webservices.v2016_2.platform.common.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Language.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Language"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_afrikaans"/&gt;
 *     &lt;enumeration value="_albanian"/&gt;
 *     &lt;enumeration value="_arabic"/&gt;
 *     &lt;enumeration value="_armenian"/&gt;
 *     &lt;enumeration value="_bengali"/&gt;
 *     &lt;enumeration value="_bosnian"/&gt;
 *     &lt;enumeration value="_bulgarian"/&gt;
 *     &lt;enumeration value="_catalan"/&gt;
 *     &lt;enumeration value="_chineseSimplified"/&gt;
 *     &lt;enumeration value="_chineseTraditional"/&gt;
 *     &lt;enumeration value="_croatian"/&gt;
 *     &lt;enumeration value="_czech"/&gt;
 *     &lt;enumeration value="_danish"/&gt;
 *     &lt;enumeration value="_dutch"/&gt;
 *     &lt;enumeration value="_englishAu"/&gt;
 *     &lt;enumeration value="_englishCa"/&gt;
 *     &lt;enumeration value="_englishInternational"/&gt;
 *     &lt;enumeration value="_englishUK"/&gt;
 *     &lt;enumeration value="_englishUS"/&gt;
 *     &lt;enumeration value="_estonian"/&gt;
 *     &lt;enumeration value="_filipino"/&gt;
 *     &lt;enumeration value="_finnish"/&gt;
 *     &lt;enumeration value="_frenchCanada"/&gt;
 *     &lt;enumeration value="_frenchFrance"/&gt;
 *     &lt;enumeration value="_german"/&gt;
 *     &lt;enumeration value="_greek"/&gt;
 *     &lt;enumeration value="_gujarati"/&gt;
 *     &lt;enumeration value="_haitian"/&gt;
 *     &lt;enumeration value="_hebrew"/&gt;
 *     &lt;enumeration value="_hindi"/&gt;
 *     &lt;enumeration value="_hungarian"/&gt;
 *     &lt;enumeration value="_icelandic"/&gt;
 *     &lt;enumeration value="_indonesian"/&gt;
 *     &lt;enumeration value="_italian"/&gt;
 *     &lt;enumeration value="_japanese"/&gt;
 *     &lt;enumeration value="_kannada"/&gt;
 *     &lt;enumeration value="_korean"/&gt;
 *     &lt;enumeration value="_latinAmericanSpanish"/&gt;
 *     &lt;enumeration value="_latvian"/&gt;
 *     &lt;enumeration value="_lithuanian"/&gt;
 *     &lt;enumeration value="_luxembourgish"/&gt;
 *     &lt;enumeration value="_malay"/&gt;
 *     &lt;enumeration value="_marathi"/&gt;
 *     &lt;enumeration value="_norwegian"/&gt;
 *     &lt;enumeration value="_persianIran"/&gt;
 *     &lt;enumeration value="_polish"/&gt;
 *     &lt;enumeration value="_portugueseBrazil"/&gt;
 *     &lt;enumeration value="_portuguesePortugal"/&gt;
 *     &lt;enumeration value="_punjabi"/&gt;
 *     &lt;enumeration value="_romanian"/&gt;
 *     &lt;enumeration value="_russian"/&gt;
 *     &lt;enumeration value="_serbianCyrillic"/&gt;
 *     &lt;enumeration value="_serbianLatin"/&gt;
 *     &lt;enumeration value="_slovak"/&gt;
 *     &lt;enumeration value="_slovenian"/&gt;
 *     &lt;enumeration value="_spanish"/&gt;
 *     &lt;enumeration value="_swedish"/&gt;
 *     &lt;enumeration value="_tamil"/&gt;
 *     &lt;enumeration value="_telugu"/&gt;
 *     &lt;enumeration value="_thai"/&gt;
 *     &lt;enumeration value="_turkish"/&gt;
 *     &lt;enumeration value="_ukrainian"/&gt;
 *     &lt;enumeration value="_vietnamese"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "Language", namespace = "urn:types.common_2016_2.platform.webservices.netsuite.com")
@XmlEnum
public enum Language {

    @XmlEnumValue("_afrikaans")
    AFRIKAANS("_afrikaans"),
    @XmlEnumValue("_albanian")
    ALBANIAN("_albanian"),
    @XmlEnumValue("_arabic")
    ARABIC("_arabic"),
    @XmlEnumValue("_armenian")
    ARMENIAN("_armenian"),
    @XmlEnumValue("_bengali")
    BENGALI("_bengali"),
    @XmlEnumValue("_bosnian")
    BOSNIAN("_bosnian"),
    @XmlEnumValue("_bulgarian")
    BULGARIAN("_bulgarian"),
    @XmlEnumValue("_catalan")
    CATALAN("_catalan"),
    @XmlEnumValue("_chineseSimplified")
    CHINESE_SIMPLIFIED("_chineseSimplified"),
    @XmlEnumValue("_chineseTraditional")
    CHINESE_TRADITIONAL("_chineseTraditional"),
    @XmlEnumValue("_croatian")
    CROATIAN("_croatian"),
    @XmlEnumValue("_czech")
    CZECH("_czech"),
    @XmlEnumValue("_danish")
    DANISH("_danish"),
    @XmlEnumValue("_dutch")
    DUTCH("_dutch"),
    @XmlEnumValue("_englishAu")
    ENGLISH_AU("_englishAu"),
    @XmlEnumValue("_englishCa")
    ENGLISH_CA("_englishCa"),
    @XmlEnumValue("_englishInternational")
    ENGLISH_INTERNATIONAL("_englishInternational"),
    @XmlEnumValue("_englishUK")
    ENGLISH_UK("_englishUK"),
    @XmlEnumValue("_englishUS")
    ENGLISH_US("_englishUS"),
    @XmlEnumValue("_estonian")
    ESTONIAN("_estonian"),
    @XmlEnumValue("_filipino")
    FILIPINO("_filipino"),
    @XmlEnumValue("_finnish")
    FINNISH("_finnish"),
    @XmlEnumValue("_frenchCanada")
    FRENCH_CANADA("_frenchCanada"),
    @XmlEnumValue("_frenchFrance")
    FRENCH_FRANCE("_frenchFrance"),
    @XmlEnumValue("_german")
    GERMAN("_german"),
    @XmlEnumValue("_greek")
    GREEK("_greek"),
    @XmlEnumValue("_gujarati")
    GUJARATI("_gujarati"),
    @XmlEnumValue("_haitian")
    HAITIAN("_haitian"),
    @XmlEnumValue("_hebrew")
    HEBREW("_hebrew"),
    @XmlEnumValue("_hindi")
    HINDI("_hindi"),
    @XmlEnumValue("_hungarian")
    HUNGARIAN("_hungarian"),
    @XmlEnumValue("_icelandic")
    ICELANDIC("_icelandic"),
    @XmlEnumValue("_indonesian")
    INDONESIAN("_indonesian"),
    @XmlEnumValue("_italian")
    ITALIAN("_italian"),
    @XmlEnumValue("_japanese")
    JAPANESE("_japanese"),
    @XmlEnumValue("_kannada")
    KANNADA("_kannada"),
    @XmlEnumValue("_korean")
    KOREAN("_korean"),
    @XmlEnumValue("_latinAmericanSpanish")
    LATIN_AMERICAN_SPANISH("_latinAmericanSpanish"),
    @XmlEnumValue("_latvian")
    LATVIAN("_latvian"),
    @XmlEnumValue("_lithuanian")
    LITHUANIAN("_lithuanian"),
    @XmlEnumValue("_luxembourgish")
    LUXEMBOURGISH("_luxembourgish"),
    @XmlEnumValue("_malay")
    MALAY("_malay"),
    @XmlEnumValue("_marathi")
    MARATHI("_marathi"),
    @XmlEnumValue("_norwegian")
    NORWEGIAN("_norwegian"),
    @XmlEnumValue("_persianIran")
    PERSIAN_IRAN("_persianIran"),
    @XmlEnumValue("_polish")
    POLISH("_polish"),
    @XmlEnumValue("_portugueseBrazil")
    PORTUGUESE_BRAZIL("_portugueseBrazil"),
    @XmlEnumValue("_portuguesePortugal")
    PORTUGUESE_PORTUGAL("_portuguesePortugal"),
    @XmlEnumValue("_punjabi")
    PUNJABI("_punjabi"),
    @XmlEnumValue("_romanian")
    ROMANIAN("_romanian"),
    @XmlEnumValue("_russian")
    RUSSIAN("_russian"),
    @XmlEnumValue("_serbianCyrillic")
    SERBIAN_CYRILLIC("_serbianCyrillic"),
    @XmlEnumValue("_serbianLatin")
    SERBIAN_LATIN("_serbianLatin"),
    @XmlEnumValue("_slovak")
    SLOVAK("_slovak"),
    @XmlEnumValue("_slovenian")
    SLOVENIAN("_slovenian"),
    @XmlEnumValue("_spanish")
    SPANISH("_spanish"),
    @XmlEnumValue("_swedish")
    SWEDISH("_swedish"),
    @XmlEnumValue("_tamil")
    TAMIL("_tamil"),
    @XmlEnumValue("_telugu")
    TELUGU("_telugu"),
    @XmlEnumValue("_thai")
    THAI("_thai"),
    @XmlEnumValue("_turkish")
    TURKISH("_turkish"),
    @XmlEnumValue("_ukrainian")
    UKRAINIAN("_ukrainian"),
    @XmlEnumValue("_vietnamese")
    VIETNAMESE("_vietnamese");
    private final String value;

    Language(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Language fromValue(String v) {
        for (Language c: Language.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
