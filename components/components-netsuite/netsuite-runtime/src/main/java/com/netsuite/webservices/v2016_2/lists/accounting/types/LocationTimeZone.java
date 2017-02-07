
package com.netsuite.webservices.v2016_2.lists.accounting.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationTimeZone.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LocationTimeZone"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="_africaCairo"/&gt;
 *     &lt;enumeration value="_africaCasablanca"/&gt;
 *     &lt;enumeration value="_africaJohannesburg"/&gt;
 *     &lt;enumeration value="_africaNairobi"/&gt;
 *     &lt;enumeration value="_africaTunis"/&gt;
 *     &lt;enumeration value="_africaWindhoek"/&gt;
 *     &lt;enumeration value="_americaAnchorage"/&gt;
 *     &lt;enumeration value="_americaArgentinaBuenosAires"/&gt;
 *     &lt;enumeration value="_americaBogota"/&gt;
 *     &lt;enumeration value="_americaCaracas"/&gt;
 *     &lt;enumeration value="_americaCayenne"/&gt;
 *     &lt;enumeration value="_americaChicago"/&gt;
 *     &lt;enumeration value="_americaChihuahua"/&gt;
 *     &lt;enumeration value="_americaDenver"/&gt;
 *     &lt;enumeration value="_americaGodthab"/&gt;
 *     &lt;enumeration value="_americaGuatemala"/&gt;
 *     &lt;enumeration value="_americaHalifax"/&gt;
 *     &lt;enumeration value="_americaIndianaIndianapolis"/&gt;
 *     &lt;enumeration value="_americaLaPaz"/&gt;
 *     &lt;enumeration value="_americaLosAngeles"/&gt;
 *     &lt;enumeration value="_americaManaus"/&gt;
 *     &lt;enumeration value="_americaMexicoCity"/&gt;
 *     &lt;enumeration value="_americaMontevideo"/&gt;
 *     &lt;enumeration value="_americaNewYork"/&gt;
 *     &lt;enumeration value="_americaNoronha"/&gt;
 *     &lt;enumeration value="_americaPhoenix"/&gt;
 *     &lt;enumeration value="_americaRegina"/&gt;
 *     &lt;enumeration value="_americaSantiago"/&gt;
 *     &lt;enumeration value="_americaSaoPaulo"/&gt;
 *     &lt;enumeration value="_americaStJohns"/&gt;
 *     &lt;enumeration value="_americaTijuana"/&gt;
 *     &lt;enumeration value="_asiaAlmaty"/&gt;
 *     &lt;enumeration value="_asiaAmman"/&gt;
 *     &lt;enumeration value="_asiaBaghdad"/&gt;
 *     &lt;enumeration value="_asiaBaku"/&gt;
 *     &lt;enumeration value="_asiaBangkok"/&gt;
 *     &lt;enumeration value="_asiaBeirut"/&gt;
 *     &lt;enumeration value="_asiaDhaka"/&gt;
 *     &lt;enumeration value="_asiaHongKong"/&gt;
 *     &lt;enumeration value="_asiaIrkutsk"/&gt;
 *     &lt;enumeration value="_asiaJerusalem"/&gt;
 *     &lt;enumeration value="_asiaKabul"/&gt;
 *     &lt;enumeration value="_asiaKarachi"/&gt;
 *     &lt;enumeration value="_asiaKathmandu"/&gt;
 *     &lt;enumeration value="_asiaKolkata"/&gt;
 *     &lt;enumeration value="_asiaKrasnoyarsk"/&gt;
 *     &lt;enumeration value="_asiaKualaLumpur"/&gt;
 *     &lt;enumeration value="_asiaMagadan"/&gt;
 *     &lt;enumeration value="_asiaManila"/&gt;
 *     &lt;enumeration value="_asiaMuscat"/&gt;
 *     &lt;enumeration value="_asiaRangoon"/&gt;
 *     &lt;enumeration value="_asiaRiyadh"/&gt;
 *     &lt;enumeration value="_asiaSeoul"/&gt;
 *     &lt;enumeration value="_asiaTaipei"/&gt;
 *     &lt;enumeration value="_asiaTashkent"/&gt;
 *     &lt;enumeration value="_asiaTbilisi"/&gt;
 *     &lt;enumeration value="_asiaTehran"/&gt;
 *     &lt;enumeration value="_asiaTokyo"/&gt;
 *     &lt;enumeration value="_asiaVladivostok"/&gt;
 *     &lt;enumeration value="_asiaYakutsk"/&gt;
 *     &lt;enumeration value="_asiaYekaterinburg"/&gt;
 *     &lt;enumeration value="_asiaYerevan"/&gt;
 *     &lt;enumeration value="_atlanticAzores"/&gt;
 *     &lt;enumeration value="_atlanticCapeVerde"/&gt;
 *     &lt;enumeration value="_atlanticReykjavik"/&gt;
 *     &lt;enumeration value="_australiaAdelaide"/&gt;
 *     &lt;enumeration value="_australiaBrisbane"/&gt;
 *     &lt;enumeration value="_australiaDarwin"/&gt;
 *     &lt;enumeration value="_australiaHobart"/&gt;
 *     &lt;enumeration value="_australiaPerth"/&gt;
 *     &lt;enumeration value="_australiaSydney"/&gt;
 *     &lt;enumeration value="_etcGmtPlus12"/&gt;
 *     &lt;enumeration value="_europeAmsterdam"/&gt;
 *     &lt;enumeration value="_europeBudapest"/&gt;
 *     &lt;enumeration value="_europeIstanbul"/&gt;
 *     &lt;enumeration value="_europeKiev"/&gt;
 *     &lt;enumeration value="_europeLondon"/&gt;
 *     &lt;enumeration value="_europeMinsk"/&gt;
 *     &lt;enumeration value="_europeMoscow"/&gt;
 *     &lt;enumeration value="_europeParis"/&gt;
 *     &lt;enumeration value="_europeWarsaw"/&gt;
 *     &lt;enumeration value="_pacificAuckland"/&gt;
 *     &lt;enumeration value="_pacificGuam"/&gt;
 *     &lt;enumeration value="_pacificHonolulu"/&gt;
 *     &lt;enumeration value="_pacificKwajalein"/&gt;
 *     &lt;enumeration value="_pacificPagoPago"/&gt;
 *     &lt;enumeration value="_pacificTongatapu"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "LocationTimeZone", namespace = "urn:types.accounting_2016_2.lists.webservices.netsuite.com")
@XmlEnum
public enum LocationTimeZone {

    @XmlEnumValue("_africaCairo")
    AFRICA_CAIRO("_africaCairo"),
    @XmlEnumValue("_africaCasablanca")
    AFRICA_CASABLANCA("_africaCasablanca"),
    @XmlEnumValue("_africaJohannesburg")
    AFRICA_JOHANNESBURG("_africaJohannesburg"),
    @XmlEnumValue("_africaNairobi")
    AFRICA_NAIROBI("_africaNairobi"),
    @XmlEnumValue("_africaTunis")
    AFRICA_TUNIS("_africaTunis"),
    @XmlEnumValue("_africaWindhoek")
    AFRICA_WINDHOEK("_africaWindhoek"),
    @XmlEnumValue("_americaAnchorage")
    AMERICA_ANCHORAGE("_americaAnchorage"),
    @XmlEnumValue("_americaArgentinaBuenosAires")
    AMERICA_ARGENTINA_BUENOS_AIRES("_americaArgentinaBuenosAires"),
    @XmlEnumValue("_americaBogota")
    AMERICA_BOGOTA("_americaBogota"),
    @XmlEnumValue("_americaCaracas")
    AMERICA_CARACAS("_americaCaracas"),
    @XmlEnumValue("_americaCayenne")
    AMERICA_CAYENNE("_americaCayenne"),
    @XmlEnumValue("_americaChicago")
    AMERICA_CHICAGO("_americaChicago"),
    @XmlEnumValue("_americaChihuahua")
    AMERICA_CHIHUAHUA("_americaChihuahua"),
    @XmlEnumValue("_americaDenver")
    AMERICA_DENVER("_americaDenver"),
    @XmlEnumValue("_americaGodthab")
    AMERICA_GODTHAB("_americaGodthab"),
    @XmlEnumValue("_americaGuatemala")
    AMERICA_GUATEMALA("_americaGuatemala"),
    @XmlEnumValue("_americaHalifax")
    AMERICA_HALIFAX("_americaHalifax"),
    @XmlEnumValue("_americaIndianaIndianapolis")
    AMERICA_INDIANA_INDIANAPOLIS("_americaIndianaIndianapolis"),
    @XmlEnumValue("_americaLaPaz")
    AMERICA_LA_PAZ("_americaLaPaz"),
    @XmlEnumValue("_americaLosAngeles")
    AMERICA_LOS_ANGELES("_americaLosAngeles"),
    @XmlEnumValue("_americaManaus")
    AMERICA_MANAUS("_americaManaus"),
    @XmlEnumValue("_americaMexicoCity")
    AMERICA_MEXICO_CITY("_americaMexicoCity"),
    @XmlEnumValue("_americaMontevideo")
    AMERICA_MONTEVIDEO("_americaMontevideo"),
    @XmlEnumValue("_americaNewYork")
    AMERICA_NEW_YORK("_americaNewYork"),
    @XmlEnumValue("_americaNoronha")
    AMERICA_NORONHA("_americaNoronha"),
    @XmlEnumValue("_americaPhoenix")
    AMERICA_PHOENIX("_americaPhoenix"),
    @XmlEnumValue("_americaRegina")
    AMERICA_REGINA("_americaRegina"),
    @XmlEnumValue("_americaSantiago")
    AMERICA_SANTIAGO("_americaSantiago"),
    @XmlEnumValue("_americaSaoPaulo")
    AMERICA_SAO_PAULO("_americaSaoPaulo"),
    @XmlEnumValue("_americaStJohns")
    AMERICA_ST_JOHNS("_americaStJohns"),
    @XmlEnumValue("_americaTijuana")
    AMERICA_TIJUANA("_americaTijuana"),
    @XmlEnumValue("_asiaAlmaty")
    ASIA_ALMATY("_asiaAlmaty"),
    @XmlEnumValue("_asiaAmman")
    ASIA_AMMAN("_asiaAmman"),
    @XmlEnumValue("_asiaBaghdad")
    ASIA_BAGHDAD("_asiaBaghdad"),
    @XmlEnumValue("_asiaBaku")
    ASIA_BAKU("_asiaBaku"),
    @XmlEnumValue("_asiaBangkok")
    ASIA_BANGKOK("_asiaBangkok"),
    @XmlEnumValue("_asiaBeirut")
    ASIA_BEIRUT("_asiaBeirut"),
    @XmlEnumValue("_asiaDhaka")
    ASIA_DHAKA("_asiaDhaka"),
    @XmlEnumValue("_asiaHongKong")
    ASIA_HONG_KONG("_asiaHongKong"),
    @XmlEnumValue("_asiaIrkutsk")
    ASIA_IRKUTSK("_asiaIrkutsk"),
    @XmlEnumValue("_asiaJerusalem")
    ASIA_JERUSALEM("_asiaJerusalem"),
    @XmlEnumValue("_asiaKabul")
    ASIA_KABUL("_asiaKabul"),
    @XmlEnumValue("_asiaKarachi")
    ASIA_KARACHI("_asiaKarachi"),
    @XmlEnumValue("_asiaKathmandu")
    ASIA_KATHMANDU("_asiaKathmandu"),
    @XmlEnumValue("_asiaKolkata")
    ASIA_KOLKATA("_asiaKolkata"),
    @XmlEnumValue("_asiaKrasnoyarsk")
    ASIA_KRASNOYARSK("_asiaKrasnoyarsk"),
    @XmlEnumValue("_asiaKualaLumpur")
    ASIA_KUALA_LUMPUR("_asiaKualaLumpur"),
    @XmlEnumValue("_asiaMagadan")
    ASIA_MAGADAN("_asiaMagadan"),
    @XmlEnumValue("_asiaManila")
    ASIA_MANILA("_asiaManila"),
    @XmlEnumValue("_asiaMuscat")
    ASIA_MUSCAT("_asiaMuscat"),
    @XmlEnumValue("_asiaRangoon")
    ASIA_RANGOON("_asiaRangoon"),
    @XmlEnumValue("_asiaRiyadh")
    ASIA_RIYADH("_asiaRiyadh"),
    @XmlEnumValue("_asiaSeoul")
    ASIA_SEOUL("_asiaSeoul"),
    @XmlEnumValue("_asiaTaipei")
    ASIA_TAIPEI("_asiaTaipei"),
    @XmlEnumValue("_asiaTashkent")
    ASIA_TASHKENT("_asiaTashkent"),
    @XmlEnumValue("_asiaTbilisi")
    ASIA_TBILISI("_asiaTbilisi"),
    @XmlEnumValue("_asiaTehran")
    ASIA_TEHRAN("_asiaTehran"),
    @XmlEnumValue("_asiaTokyo")
    ASIA_TOKYO("_asiaTokyo"),
    @XmlEnumValue("_asiaVladivostok")
    ASIA_VLADIVOSTOK("_asiaVladivostok"),
    @XmlEnumValue("_asiaYakutsk")
    ASIA_YAKUTSK("_asiaYakutsk"),
    @XmlEnumValue("_asiaYekaterinburg")
    ASIA_YEKATERINBURG("_asiaYekaterinburg"),
    @XmlEnumValue("_asiaYerevan")
    ASIA_YEREVAN("_asiaYerevan"),
    @XmlEnumValue("_atlanticAzores")
    ATLANTIC_AZORES("_atlanticAzores"),
    @XmlEnumValue("_atlanticCapeVerde")
    ATLANTIC_CAPE_VERDE("_atlanticCapeVerde"),
    @XmlEnumValue("_atlanticReykjavik")
    ATLANTIC_REYKJAVIK("_atlanticReykjavik"),
    @XmlEnumValue("_australiaAdelaide")
    AUSTRALIA_ADELAIDE("_australiaAdelaide"),
    @XmlEnumValue("_australiaBrisbane")
    AUSTRALIA_BRISBANE("_australiaBrisbane"),
    @XmlEnumValue("_australiaDarwin")
    AUSTRALIA_DARWIN("_australiaDarwin"),
    @XmlEnumValue("_australiaHobart")
    AUSTRALIA_HOBART("_australiaHobart"),
    @XmlEnumValue("_australiaPerth")
    AUSTRALIA_PERTH("_australiaPerth"),
    @XmlEnumValue("_australiaSydney")
    AUSTRALIA_SYDNEY("_australiaSydney"),
    @XmlEnumValue("_etcGmtPlus12")
    ETC_GMT_PLUS_12("_etcGmtPlus12"),
    @XmlEnumValue("_europeAmsterdam")
    EUROPE_AMSTERDAM("_europeAmsterdam"),
    @XmlEnumValue("_europeBudapest")
    EUROPE_BUDAPEST("_europeBudapest"),
    @XmlEnumValue("_europeIstanbul")
    EUROPE_ISTANBUL("_europeIstanbul"),
    @XmlEnumValue("_europeKiev")
    EUROPE_KIEV("_europeKiev"),
    @XmlEnumValue("_europeLondon")
    EUROPE_LONDON("_europeLondon"),
    @XmlEnumValue("_europeMinsk")
    EUROPE_MINSK("_europeMinsk"),
    @XmlEnumValue("_europeMoscow")
    EUROPE_MOSCOW("_europeMoscow"),
    @XmlEnumValue("_europeParis")
    EUROPE_PARIS("_europeParis"),
    @XmlEnumValue("_europeWarsaw")
    EUROPE_WARSAW("_europeWarsaw"),
    @XmlEnumValue("_pacificAuckland")
    PACIFIC_AUCKLAND("_pacificAuckland"),
    @XmlEnumValue("_pacificGuam")
    PACIFIC_GUAM("_pacificGuam"),
    @XmlEnumValue("_pacificHonolulu")
    PACIFIC_HONOLULU("_pacificHonolulu"),
    @XmlEnumValue("_pacificKwajalein")
    PACIFIC_KWAJALEIN("_pacificKwajalein"),
    @XmlEnumValue("_pacificPagoPago")
    PACIFIC_PAGO_PAGO("_pacificPagoPago"),
    @XmlEnumValue("_pacificTongatapu")
    PACIFIC_TONGATAPU("_pacificTongatapu");
    private final String value;

    LocationTimeZone(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LocationTimeZone fromValue(String v) {
        for (LocationTimeZone c: LocationTimeZone.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
