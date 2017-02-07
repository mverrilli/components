
package com.netsuite.webservices.v2016_2.platform.core.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SearchDate.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SearchDate"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="fiscalHalfBeforeLast"/&gt;
 *     &lt;enumeration value="fiscalHalfBeforeLastToDate"/&gt;
 *     &lt;enumeration value="fiscalQuarterBeforeLast"/&gt;
 *     &lt;enumeration value="fiscalQuarterBeforeLastToDate"/&gt;
 *     &lt;enumeration value="fiscalYearBeforeLast"/&gt;
 *     &lt;enumeration value="fiscalYearBeforeLastToDate"/&gt;
 *     &lt;enumeration value="fiveDaysAgo"/&gt;
 *     &lt;enumeration value="fiveDaysFromNow"/&gt;
 *     &lt;enumeration value="fourDaysAgo"/&gt;
 *     &lt;enumeration value="fourDaysFromNow"/&gt;
 *     &lt;enumeration value="fourWeeksStartingThisWeek"/&gt;
 *     &lt;enumeration value="lastBusinessWeek"/&gt;
 *     &lt;enumeration value="lastFiscalHalf"/&gt;
 *     &lt;enumeration value="lastFiscalHalfOneFiscalYearAgo"/&gt;
 *     &lt;enumeration value="lastFiscalHalfToDate"/&gt;
 *     &lt;enumeration value="lastFiscalQuarter"/&gt;
 *     &lt;enumeration value="lastFiscalQuarterOneFiscalYearAgo"/&gt;
 *     &lt;enumeration value="lastFiscalQuarterToDate"/&gt;
 *     &lt;enumeration value="lastFiscalQuarterTwoFiscalYearsAgo"/&gt;
 *     &lt;enumeration value="lastFiscalYear"/&gt;
 *     &lt;enumeration value="lastFiscalYearToDate"/&gt;
 *     &lt;enumeration value="lastMonth"/&gt;
 *     &lt;enumeration value="lastMonthOneFiscalQuarterAgo"/&gt;
 *     &lt;enumeration value="lastMonthOneFiscalYearAgo"/&gt;
 *     &lt;enumeration value="lastMonthToDate"/&gt;
 *     &lt;enumeration value="lastMonthTwoFiscalQuartersAgo"/&gt;
 *     &lt;enumeration value="lastMonthTwoFiscalYearsAgo"/&gt;
 *     &lt;enumeration value="lastRollingHalf"/&gt;
 *     &lt;enumeration value="lastRollingQuarter"/&gt;
 *     &lt;enumeration value="lastRollingYear"/&gt;
 *     &lt;enumeration value="lastWeek"/&gt;
 *     &lt;enumeration value="lastWeekToDate"/&gt;
 *     &lt;enumeration value="monthAfterNext"/&gt;
 *     &lt;enumeration value="monthAfterNextToDate"/&gt;
 *     &lt;enumeration value="monthBeforeLast"/&gt;
 *     &lt;enumeration value="monthBeforeLastToDate"/&gt;
 *     &lt;enumeration value="nextBusinessWeek"/&gt;
 *     &lt;enumeration value="nextFiscalHalf"/&gt;
 *     &lt;enumeration value="nextFiscalQuarter"/&gt;
 *     &lt;enumeration value="nextFiscalYear"/&gt;
 *     &lt;enumeration value="nextFourWeeks"/&gt;
 *     &lt;enumeration value="nextMonth"/&gt;
 *     &lt;enumeration value="nextOneHalf"/&gt;
 *     &lt;enumeration value="nextOneMonth"/&gt;
 *     &lt;enumeration value="nextOneQuarter"/&gt;
 *     &lt;enumeration value="nextOneWeek"/&gt;
 *     &lt;enumeration value="nextOneYear"/&gt;
 *     &lt;enumeration value="nextWeek"/&gt;
 *     &lt;enumeration value="ninetyDaysAgo"/&gt;
 *     &lt;enumeration value="ninetyDaysFromNow"/&gt;
 *     &lt;enumeration value="oneYearBeforeLast"/&gt;
 *     &lt;enumeration value="previousFiscalQuartersLastFiscalYear"/&gt;
 *     &lt;enumeration value="previousFiscalQuartersThisFiscalYear"/&gt;
 *     &lt;enumeration value="previousMonthsLastFiscalHalf"/&gt;
 *     &lt;enumeration value="previousMonthsLastFiscalQuarter"/&gt;
 *     &lt;enumeration value="previousMonthsLastFiscalYear"/&gt;
 *     &lt;enumeration value="previousMonthsSameFiscalHalfLastFiscalYear"/&gt;
 *     &lt;enumeration value="previousMonthsSameFiscalQuarterLastFiscalYear"/&gt;
 *     &lt;enumeration value="previousMonthsThisFiscalHalf"/&gt;
 *     &lt;enumeration value="previousMonthsThisFiscalQuarter"/&gt;
 *     &lt;enumeration value="previousMonthsThisFiscalYear"/&gt;
 *     &lt;enumeration value="previousOneDay"/&gt;
 *     &lt;enumeration value="previousOneHalf"/&gt;
 *     &lt;enumeration value="previousOneMonth"/&gt;
 *     &lt;enumeration value="previousOneQuarter"/&gt;
 *     &lt;enumeration value="previousOneWeek"/&gt;
 *     &lt;enumeration value="previousOneYear"/&gt;
 *     &lt;enumeration value="previousRollingHalf"/&gt;
 *     &lt;enumeration value="previousRollingQuarter"/&gt;
 *     &lt;enumeration value="previousRollingYear"/&gt;
 *     &lt;enumeration value="sameDayFiscalQuarterBeforeLast"/&gt;
 *     &lt;enumeration value="sameDayFiscalYearBeforeLast"/&gt;
 *     &lt;enumeration value="sameDayLastFiscalQuarter"/&gt;
 *     &lt;enumeration value="sameDayLastFiscalYear"/&gt;
 *     &lt;enumeration value="sameDayLastMonth"/&gt;
 *     &lt;enumeration value="sameDayLastWeek"/&gt;
 *     &lt;enumeration value="sameDayMonthBeforeLast"/&gt;
 *     &lt;enumeration value="sameDayWeekBeforeLast"/&gt;
 *     &lt;enumeration value="sameFiscalHalfLastFiscalYear"/&gt;
 *     &lt;enumeration value="sameFiscalHalfLastFiscalYearToDate"/&gt;
 *     &lt;enumeration value="sameFiscalQuarterFiscalYearBeforeLast"/&gt;
 *     &lt;enumeration value="sameFiscalQuarterLastFiscalYear"/&gt;
 *     &lt;enumeration value="sameFiscalQuarterLastFiscalYearToDate"/&gt;
 *     &lt;enumeration value="sameMonthFiscalQuarterBeforeLast"/&gt;
 *     &lt;enumeration value="sameMonthFiscalYearBeforeLast"/&gt;
 *     &lt;enumeration value="sameMonthLastFiscalQuarter"/&gt;
 *     &lt;enumeration value="sameMonthLastFiscalQuarterToDate"/&gt;
 *     &lt;enumeration value="sameMonthLastFiscalYear"/&gt;
 *     &lt;enumeration value="sameMonthLastFiscalYearToDate"/&gt;
 *     &lt;enumeration value="sameWeekFiscalYearBeforeLast"/&gt;
 *     &lt;enumeration value="sameWeekLastFiscalYear"/&gt;
 *     &lt;enumeration value="sixtyDaysAgo"/&gt;
 *     &lt;enumeration value="sixtyDaysFromNow"/&gt;
 *     &lt;enumeration value="startOfFiscalHalfBeforeLast"/&gt;
 *     &lt;enumeration value="startOfFiscalQuarterBeforeLast"/&gt;
 *     &lt;enumeration value="startOfFiscalYearBeforeLast"/&gt;
 *     &lt;enumeration value="startOfLastBusinessWeek"/&gt;
 *     &lt;enumeration value="startOfLastFiscalHalf"/&gt;
 *     &lt;enumeration value="startOfLastFiscalHalfOneFiscalYearAgo"/&gt;
 *     &lt;enumeration value="startOfLastFiscalQuarter"/&gt;
 *     &lt;enumeration value="startOfLastFiscalQuarterOneFiscalYearAgo"/&gt;
 *     &lt;enumeration value="startOfLastFiscalYear"/&gt;
 *     &lt;enumeration value="startOfLastMonth"/&gt;
 *     &lt;enumeration value="startOfLastMonthOneFiscalQuarterAgo"/&gt;
 *     &lt;enumeration value="startOfLastMonthOneFiscalYearAgo"/&gt;
 *     &lt;enumeration value="startOfLastRollingHalf"/&gt;
 *     &lt;enumeration value="startOfLastRollingQuarter"/&gt;
 *     &lt;enumeration value="startOfLastRollingYear"/&gt;
 *     &lt;enumeration value="startOfLastWeek"/&gt;
 *     &lt;enumeration value="startOfMonthBeforeLast"/&gt;
 *     &lt;enumeration value="startOfNextBusinessWeek"/&gt;
 *     &lt;enumeration value="startOfNextFiscalHalf"/&gt;
 *     &lt;enumeration value="startOfNextFiscalQuarter"/&gt;
 *     &lt;enumeration value="startOfNextFiscalYear"/&gt;
 *     &lt;enumeration value="startOfNextMonth"/&gt;
 *     &lt;enumeration value="startOfNextWeek"/&gt;
 *     &lt;enumeration value="startOfPreviousRollingHalf"/&gt;
 *     &lt;enumeration value="startOfPreviousRollingQuarter"/&gt;
 *     &lt;enumeration value="startOfPreviousRollingYear"/&gt;
 *     &lt;enumeration value="startOfSameFiscalHalfLastFiscalYear"/&gt;
 *     &lt;enumeration value="startOfSameFiscalQuarterLastFiscalYear"/&gt;
 *     &lt;enumeration value="startOfSameMonthLastFiscalQuarter"/&gt;
 *     &lt;enumeration value="startOfSameMonthLastFiscalYear"/&gt;
 *     &lt;enumeration value="startOfThisBusinessWeek"/&gt;
 *     &lt;enumeration value="startOfThisFiscalHalf"/&gt;
 *     &lt;enumeration value="startOfThisFiscalQuarter"/&gt;
 *     &lt;enumeration value="startOfThisFiscalYear"/&gt;
 *     &lt;enumeration value="startOfThisMonth"/&gt;
 *     &lt;enumeration value="startOfThisWeek"/&gt;
 *     &lt;enumeration value="startOfThisYear"/&gt;
 *     &lt;enumeration value="startOfWeekBeforeLast"/&gt;
 *     &lt;enumeration value="tenDaysAgo"/&gt;
 *     &lt;enumeration value="tenDaysFromNow"/&gt;
 *     &lt;enumeration value="thirtyDaysAgo"/&gt;
 *     &lt;enumeration value="thirtyDaysFromNow"/&gt;
 *     &lt;enumeration value="thisBusinessWeek"/&gt;
 *     &lt;enumeration value="thisFiscalHalf"/&gt;
 *     &lt;enumeration value="thisFiscalHalfToDate"/&gt;
 *     &lt;enumeration value="thisFiscalQuarter"/&gt;
 *     &lt;enumeration value="thisFiscalQuarterToDate"/&gt;
 *     &lt;enumeration value="thisFiscalYear"/&gt;
 *     &lt;enumeration value="thisFiscalYearToDate"/&gt;
 *     &lt;enumeration value="thisMonth"/&gt;
 *     &lt;enumeration value="thisMonthToDate"/&gt;
 *     &lt;enumeration value="thisRollingHalf"/&gt;
 *     &lt;enumeration value="thisRollingQuarter"/&gt;
 *     &lt;enumeration value="thisRollingYear"/&gt;
 *     &lt;enumeration value="thisWeek"/&gt;
 *     &lt;enumeration value="thisWeekToDate"/&gt;
 *     &lt;enumeration value="thisYear"/&gt;
 *     &lt;enumeration value="threeDaysAgo"/&gt;
 *     &lt;enumeration value="threeDaysFromNow"/&gt;
 *     &lt;enumeration value="threeFiscalQuartersAgo"/&gt;
 *     &lt;enumeration value="threeFiscalQuartersAgoToDate"/&gt;
 *     &lt;enumeration value="threeFiscalYearsAgo"/&gt;
 *     &lt;enumeration value="threeFiscalYearsAgoToDate"/&gt;
 *     &lt;enumeration value="threeMonthsAgo"/&gt;
 *     &lt;enumeration value="threeMonthsAgoToDate"/&gt;
 *     &lt;enumeration value="today"/&gt;
 *     &lt;enumeration value="tomorrow"/&gt;
 *     &lt;enumeration value="twoDaysAgo"/&gt;
 *     &lt;enumeration value="twoDaysFromNow"/&gt;
 *     &lt;enumeration value="weekAfterNext"/&gt;
 *     &lt;enumeration value="weekAfterNextToDate"/&gt;
 *     &lt;enumeration value="weekBeforeLast"/&gt;
 *     &lt;enumeration value="weekBeforeLastToDate"/&gt;
 *     &lt;enumeration value="yesterday"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "SearchDate", namespace = "urn:types.core_2016_2.platform.webservices.netsuite.com")
@XmlEnum
public enum SearchDate {

    @XmlEnumValue("fiscalHalfBeforeLast")
    FISCAL_HALF_BEFORE_LAST("fiscalHalfBeforeLast"),
    @XmlEnumValue("fiscalHalfBeforeLastToDate")
    FISCAL_HALF_BEFORE_LAST_TO_DATE("fiscalHalfBeforeLastToDate"),
    @XmlEnumValue("fiscalQuarterBeforeLast")
    FISCAL_QUARTER_BEFORE_LAST("fiscalQuarterBeforeLast"),
    @XmlEnumValue("fiscalQuarterBeforeLastToDate")
    FISCAL_QUARTER_BEFORE_LAST_TO_DATE("fiscalQuarterBeforeLastToDate"),
    @XmlEnumValue("fiscalYearBeforeLast")
    FISCAL_YEAR_BEFORE_LAST("fiscalYearBeforeLast"),
    @XmlEnumValue("fiscalYearBeforeLastToDate")
    FISCAL_YEAR_BEFORE_LAST_TO_DATE("fiscalYearBeforeLastToDate"),
    @XmlEnumValue("fiveDaysAgo")
    FIVE_DAYS_AGO("fiveDaysAgo"),
    @XmlEnumValue("fiveDaysFromNow")
    FIVE_DAYS_FROM_NOW("fiveDaysFromNow"),
    @XmlEnumValue("fourDaysAgo")
    FOUR_DAYS_AGO("fourDaysAgo"),
    @XmlEnumValue("fourDaysFromNow")
    FOUR_DAYS_FROM_NOW("fourDaysFromNow"),
    @XmlEnumValue("fourWeeksStartingThisWeek")
    FOUR_WEEKS_STARTING_THIS_WEEK("fourWeeksStartingThisWeek"),
    @XmlEnumValue("lastBusinessWeek")
    LAST_BUSINESS_WEEK("lastBusinessWeek"),
    @XmlEnumValue("lastFiscalHalf")
    LAST_FISCAL_HALF("lastFiscalHalf"),
    @XmlEnumValue("lastFiscalHalfOneFiscalYearAgo")
    LAST_FISCAL_HALF_ONE_FISCAL_YEAR_AGO("lastFiscalHalfOneFiscalYearAgo"),
    @XmlEnumValue("lastFiscalHalfToDate")
    LAST_FISCAL_HALF_TO_DATE("lastFiscalHalfToDate"),
    @XmlEnumValue("lastFiscalQuarter")
    LAST_FISCAL_QUARTER("lastFiscalQuarter"),
    @XmlEnumValue("lastFiscalQuarterOneFiscalYearAgo")
    LAST_FISCAL_QUARTER_ONE_FISCAL_YEAR_AGO("lastFiscalQuarterOneFiscalYearAgo"),
    @XmlEnumValue("lastFiscalQuarterToDate")
    LAST_FISCAL_QUARTER_TO_DATE("lastFiscalQuarterToDate"),
    @XmlEnumValue("lastFiscalQuarterTwoFiscalYearsAgo")
    LAST_FISCAL_QUARTER_TWO_FISCAL_YEARS_AGO("lastFiscalQuarterTwoFiscalYearsAgo"),
    @XmlEnumValue("lastFiscalYear")
    LAST_FISCAL_YEAR("lastFiscalYear"),
    @XmlEnumValue("lastFiscalYearToDate")
    LAST_FISCAL_YEAR_TO_DATE("lastFiscalYearToDate"),
    @XmlEnumValue("lastMonth")
    LAST_MONTH("lastMonth"),
    @XmlEnumValue("lastMonthOneFiscalQuarterAgo")
    LAST_MONTH_ONE_FISCAL_QUARTER_AGO("lastMonthOneFiscalQuarterAgo"),
    @XmlEnumValue("lastMonthOneFiscalYearAgo")
    LAST_MONTH_ONE_FISCAL_YEAR_AGO("lastMonthOneFiscalYearAgo"),
    @XmlEnumValue("lastMonthToDate")
    LAST_MONTH_TO_DATE("lastMonthToDate"),
    @XmlEnumValue("lastMonthTwoFiscalQuartersAgo")
    LAST_MONTH_TWO_FISCAL_QUARTERS_AGO("lastMonthTwoFiscalQuartersAgo"),
    @XmlEnumValue("lastMonthTwoFiscalYearsAgo")
    LAST_MONTH_TWO_FISCAL_YEARS_AGO("lastMonthTwoFiscalYearsAgo"),
    @XmlEnumValue("lastRollingHalf")
    LAST_ROLLING_HALF("lastRollingHalf"),
    @XmlEnumValue("lastRollingQuarter")
    LAST_ROLLING_QUARTER("lastRollingQuarter"),
    @XmlEnumValue("lastRollingYear")
    LAST_ROLLING_YEAR("lastRollingYear"),
    @XmlEnumValue("lastWeek")
    LAST_WEEK("lastWeek"),
    @XmlEnumValue("lastWeekToDate")
    LAST_WEEK_TO_DATE("lastWeekToDate"),
    @XmlEnumValue("monthAfterNext")
    MONTH_AFTER_NEXT("monthAfterNext"),
    @XmlEnumValue("monthAfterNextToDate")
    MONTH_AFTER_NEXT_TO_DATE("monthAfterNextToDate"),
    @XmlEnumValue("monthBeforeLast")
    MONTH_BEFORE_LAST("monthBeforeLast"),
    @XmlEnumValue("monthBeforeLastToDate")
    MONTH_BEFORE_LAST_TO_DATE("monthBeforeLastToDate"),
    @XmlEnumValue("nextBusinessWeek")
    NEXT_BUSINESS_WEEK("nextBusinessWeek"),
    @XmlEnumValue("nextFiscalHalf")
    NEXT_FISCAL_HALF("nextFiscalHalf"),
    @XmlEnumValue("nextFiscalQuarter")
    NEXT_FISCAL_QUARTER("nextFiscalQuarter"),
    @XmlEnumValue("nextFiscalYear")
    NEXT_FISCAL_YEAR("nextFiscalYear"),
    @XmlEnumValue("nextFourWeeks")
    NEXT_FOUR_WEEKS("nextFourWeeks"),
    @XmlEnumValue("nextMonth")
    NEXT_MONTH("nextMonth"),
    @XmlEnumValue("nextOneHalf")
    NEXT_ONE_HALF("nextOneHalf"),
    @XmlEnumValue("nextOneMonth")
    NEXT_ONE_MONTH("nextOneMonth"),
    @XmlEnumValue("nextOneQuarter")
    NEXT_ONE_QUARTER("nextOneQuarter"),
    @XmlEnumValue("nextOneWeek")
    NEXT_ONE_WEEK("nextOneWeek"),
    @XmlEnumValue("nextOneYear")
    NEXT_ONE_YEAR("nextOneYear"),
    @XmlEnumValue("nextWeek")
    NEXT_WEEK("nextWeek"),
    @XmlEnumValue("ninetyDaysAgo")
    NINETY_DAYS_AGO("ninetyDaysAgo"),
    @XmlEnumValue("ninetyDaysFromNow")
    NINETY_DAYS_FROM_NOW("ninetyDaysFromNow"),
    @XmlEnumValue("oneYearBeforeLast")
    ONE_YEAR_BEFORE_LAST("oneYearBeforeLast"),
    @XmlEnumValue("previousFiscalQuartersLastFiscalYear")
    PREVIOUS_FISCAL_QUARTERS_LAST_FISCAL_YEAR("previousFiscalQuartersLastFiscalYear"),
    @XmlEnumValue("previousFiscalQuartersThisFiscalYear")
    PREVIOUS_FISCAL_QUARTERS_THIS_FISCAL_YEAR("previousFiscalQuartersThisFiscalYear"),
    @XmlEnumValue("previousMonthsLastFiscalHalf")
    PREVIOUS_MONTHS_LAST_FISCAL_HALF("previousMonthsLastFiscalHalf"),
    @XmlEnumValue("previousMonthsLastFiscalQuarter")
    PREVIOUS_MONTHS_LAST_FISCAL_QUARTER("previousMonthsLastFiscalQuarter"),
    @XmlEnumValue("previousMonthsLastFiscalYear")
    PREVIOUS_MONTHS_LAST_FISCAL_YEAR("previousMonthsLastFiscalYear"),
    @XmlEnumValue("previousMonthsSameFiscalHalfLastFiscalYear")
    PREVIOUS_MONTHS_SAME_FISCAL_HALF_LAST_FISCAL_YEAR("previousMonthsSameFiscalHalfLastFiscalYear"),
    @XmlEnumValue("previousMonthsSameFiscalQuarterLastFiscalYear")
    PREVIOUS_MONTHS_SAME_FISCAL_QUARTER_LAST_FISCAL_YEAR("previousMonthsSameFiscalQuarterLastFiscalYear"),
    @XmlEnumValue("previousMonthsThisFiscalHalf")
    PREVIOUS_MONTHS_THIS_FISCAL_HALF("previousMonthsThisFiscalHalf"),
    @XmlEnumValue("previousMonthsThisFiscalQuarter")
    PREVIOUS_MONTHS_THIS_FISCAL_QUARTER("previousMonthsThisFiscalQuarter"),
    @XmlEnumValue("previousMonthsThisFiscalYear")
    PREVIOUS_MONTHS_THIS_FISCAL_YEAR("previousMonthsThisFiscalYear"),
    @XmlEnumValue("previousOneDay")
    PREVIOUS_ONE_DAY("previousOneDay"),
    @XmlEnumValue("previousOneHalf")
    PREVIOUS_ONE_HALF("previousOneHalf"),
    @XmlEnumValue("previousOneMonth")
    PREVIOUS_ONE_MONTH("previousOneMonth"),
    @XmlEnumValue("previousOneQuarter")
    PREVIOUS_ONE_QUARTER("previousOneQuarter"),
    @XmlEnumValue("previousOneWeek")
    PREVIOUS_ONE_WEEK("previousOneWeek"),
    @XmlEnumValue("previousOneYear")
    PREVIOUS_ONE_YEAR("previousOneYear"),
    @XmlEnumValue("previousRollingHalf")
    PREVIOUS_ROLLING_HALF("previousRollingHalf"),
    @XmlEnumValue("previousRollingQuarter")
    PREVIOUS_ROLLING_QUARTER("previousRollingQuarter"),
    @XmlEnumValue("previousRollingYear")
    PREVIOUS_ROLLING_YEAR("previousRollingYear"),
    @XmlEnumValue("sameDayFiscalQuarterBeforeLast")
    SAME_DAY_FISCAL_QUARTER_BEFORE_LAST("sameDayFiscalQuarterBeforeLast"),
    @XmlEnumValue("sameDayFiscalYearBeforeLast")
    SAME_DAY_FISCAL_YEAR_BEFORE_LAST("sameDayFiscalYearBeforeLast"),
    @XmlEnumValue("sameDayLastFiscalQuarter")
    SAME_DAY_LAST_FISCAL_QUARTER("sameDayLastFiscalQuarter"),
    @XmlEnumValue("sameDayLastFiscalYear")
    SAME_DAY_LAST_FISCAL_YEAR("sameDayLastFiscalYear"),
    @XmlEnumValue("sameDayLastMonth")
    SAME_DAY_LAST_MONTH("sameDayLastMonth"),
    @XmlEnumValue("sameDayLastWeek")
    SAME_DAY_LAST_WEEK("sameDayLastWeek"),
    @XmlEnumValue("sameDayMonthBeforeLast")
    SAME_DAY_MONTH_BEFORE_LAST("sameDayMonthBeforeLast"),
    @XmlEnumValue("sameDayWeekBeforeLast")
    SAME_DAY_WEEK_BEFORE_LAST("sameDayWeekBeforeLast"),
    @XmlEnumValue("sameFiscalHalfLastFiscalYear")
    SAME_FISCAL_HALF_LAST_FISCAL_YEAR("sameFiscalHalfLastFiscalYear"),
    @XmlEnumValue("sameFiscalHalfLastFiscalYearToDate")
    SAME_FISCAL_HALF_LAST_FISCAL_YEAR_TO_DATE("sameFiscalHalfLastFiscalYearToDate"),
    @XmlEnumValue("sameFiscalQuarterFiscalYearBeforeLast")
    SAME_FISCAL_QUARTER_FISCAL_YEAR_BEFORE_LAST("sameFiscalQuarterFiscalYearBeforeLast"),
    @XmlEnumValue("sameFiscalQuarterLastFiscalYear")
    SAME_FISCAL_QUARTER_LAST_FISCAL_YEAR("sameFiscalQuarterLastFiscalYear"),
    @XmlEnumValue("sameFiscalQuarterLastFiscalYearToDate")
    SAME_FISCAL_QUARTER_LAST_FISCAL_YEAR_TO_DATE("sameFiscalQuarterLastFiscalYearToDate"),
    @XmlEnumValue("sameMonthFiscalQuarterBeforeLast")
    SAME_MONTH_FISCAL_QUARTER_BEFORE_LAST("sameMonthFiscalQuarterBeforeLast"),
    @XmlEnumValue("sameMonthFiscalYearBeforeLast")
    SAME_MONTH_FISCAL_YEAR_BEFORE_LAST("sameMonthFiscalYearBeforeLast"),
    @XmlEnumValue("sameMonthLastFiscalQuarter")
    SAME_MONTH_LAST_FISCAL_QUARTER("sameMonthLastFiscalQuarter"),
    @XmlEnumValue("sameMonthLastFiscalQuarterToDate")
    SAME_MONTH_LAST_FISCAL_QUARTER_TO_DATE("sameMonthLastFiscalQuarterToDate"),
    @XmlEnumValue("sameMonthLastFiscalYear")
    SAME_MONTH_LAST_FISCAL_YEAR("sameMonthLastFiscalYear"),
    @XmlEnumValue("sameMonthLastFiscalYearToDate")
    SAME_MONTH_LAST_FISCAL_YEAR_TO_DATE("sameMonthLastFiscalYearToDate"),
    @XmlEnumValue("sameWeekFiscalYearBeforeLast")
    SAME_WEEK_FISCAL_YEAR_BEFORE_LAST("sameWeekFiscalYearBeforeLast"),
    @XmlEnumValue("sameWeekLastFiscalYear")
    SAME_WEEK_LAST_FISCAL_YEAR("sameWeekLastFiscalYear"),
    @XmlEnumValue("sixtyDaysAgo")
    SIXTY_DAYS_AGO("sixtyDaysAgo"),
    @XmlEnumValue("sixtyDaysFromNow")
    SIXTY_DAYS_FROM_NOW("sixtyDaysFromNow"),
    @XmlEnumValue("startOfFiscalHalfBeforeLast")
    START_OF_FISCAL_HALF_BEFORE_LAST("startOfFiscalHalfBeforeLast"),
    @XmlEnumValue("startOfFiscalQuarterBeforeLast")
    START_OF_FISCAL_QUARTER_BEFORE_LAST("startOfFiscalQuarterBeforeLast"),
    @XmlEnumValue("startOfFiscalYearBeforeLast")
    START_OF_FISCAL_YEAR_BEFORE_LAST("startOfFiscalYearBeforeLast"),
    @XmlEnumValue("startOfLastBusinessWeek")
    START_OF_LAST_BUSINESS_WEEK("startOfLastBusinessWeek"),
    @XmlEnumValue("startOfLastFiscalHalf")
    START_OF_LAST_FISCAL_HALF("startOfLastFiscalHalf"),
    @XmlEnumValue("startOfLastFiscalHalfOneFiscalYearAgo")
    START_OF_LAST_FISCAL_HALF_ONE_FISCAL_YEAR_AGO("startOfLastFiscalHalfOneFiscalYearAgo"),
    @XmlEnumValue("startOfLastFiscalQuarter")
    START_OF_LAST_FISCAL_QUARTER("startOfLastFiscalQuarter"),
    @XmlEnumValue("startOfLastFiscalQuarterOneFiscalYearAgo")
    START_OF_LAST_FISCAL_QUARTER_ONE_FISCAL_YEAR_AGO("startOfLastFiscalQuarterOneFiscalYearAgo"),
    @XmlEnumValue("startOfLastFiscalYear")
    START_OF_LAST_FISCAL_YEAR("startOfLastFiscalYear"),
    @XmlEnumValue("startOfLastMonth")
    START_OF_LAST_MONTH("startOfLastMonth"),
    @XmlEnumValue("startOfLastMonthOneFiscalQuarterAgo")
    START_OF_LAST_MONTH_ONE_FISCAL_QUARTER_AGO("startOfLastMonthOneFiscalQuarterAgo"),
    @XmlEnumValue("startOfLastMonthOneFiscalYearAgo")
    START_OF_LAST_MONTH_ONE_FISCAL_YEAR_AGO("startOfLastMonthOneFiscalYearAgo"),
    @XmlEnumValue("startOfLastRollingHalf")
    START_OF_LAST_ROLLING_HALF("startOfLastRollingHalf"),
    @XmlEnumValue("startOfLastRollingQuarter")
    START_OF_LAST_ROLLING_QUARTER("startOfLastRollingQuarter"),
    @XmlEnumValue("startOfLastRollingYear")
    START_OF_LAST_ROLLING_YEAR("startOfLastRollingYear"),
    @XmlEnumValue("startOfLastWeek")
    START_OF_LAST_WEEK("startOfLastWeek"),
    @XmlEnumValue("startOfMonthBeforeLast")
    START_OF_MONTH_BEFORE_LAST("startOfMonthBeforeLast"),
    @XmlEnumValue("startOfNextBusinessWeek")
    START_OF_NEXT_BUSINESS_WEEK("startOfNextBusinessWeek"),
    @XmlEnumValue("startOfNextFiscalHalf")
    START_OF_NEXT_FISCAL_HALF("startOfNextFiscalHalf"),
    @XmlEnumValue("startOfNextFiscalQuarter")
    START_OF_NEXT_FISCAL_QUARTER("startOfNextFiscalQuarter"),
    @XmlEnumValue("startOfNextFiscalYear")
    START_OF_NEXT_FISCAL_YEAR("startOfNextFiscalYear"),
    @XmlEnumValue("startOfNextMonth")
    START_OF_NEXT_MONTH("startOfNextMonth"),
    @XmlEnumValue("startOfNextWeek")
    START_OF_NEXT_WEEK("startOfNextWeek"),
    @XmlEnumValue("startOfPreviousRollingHalf")
    START_OF_PREVIOUS_ROLLING_HALF("startOfPreviousRollingHalf"),
    @XmlEnumValue("startOfPreviousRollingQuarter")
    START_OF_PREVIOUS_ROLLING_QUARTER("startOfPreviousRollingQuarter"),
    @XmlEnumValue("startOfPreviousRollingYear")
    START_OF_PREVIOUS_ROLLING_YEAR("startOfPreviousRollingYear"),
    @XmlEnumValue("startOfSameFiscalHalfLastFiscalYear")
    START_OF_SAME_FISCAL_HALF_LAST_FISCAL_YEAR("startOfSameFiscalHalfLastFiscalYear"),
    @XmlEnumValue("startOfSameFiscalQuarterLastFiscalYear")
    START_OF_SAME_FISCAL_QUARTER_LAST_FISCAL_YEAR("startOfSameFiscalQuarterLastFiscalYear"),
    @XmlEnumValue("startOfSameMonthLastFiscalQuarter")
    START_OF_SAME_MONTH_LAST_FISCAL_QUARTER("startOfSameMonthLastFiscalQuarter"),
    @XmlEnumValue("startOfSameMonthLastFiscalYear")
    START_OF_SAME_MONTH_LAST_FISCAL_YEAR("startOfSameMonthLastFiscalYear"),
    @XmlEnumValue("startOfThisBusinessWeek")
    START_OF_THIS_BUSINESS_WEEK("startOfThisBusinessWeek"),
    @XmlEnumValue("startOfThisFiscalHalf")
    START_OF_THIS_FISCAL_HALF("startOfThisFiscalHalf"),
    @XmlEnumValue("startOfThisFiscalQuarter")
    START_OF_THIS_FISCAL_QUARTER("startOfThisFiscalQuarter"),
    @XmlEnumValue("startOfThisFiscalYear")
    START_OF_THIS_FISCAL_YEAR("startOfThisFiscalYear"),
    @XmlEnumValue("startOfThisMonth")
    START_OF_THIS_MONTH("startOfThisMonth"),
    @XmlEnumValue("startOfThisWeek")
    START_OF_THIS_WEEK("startOfThisWeek"),
    @XmlEnumValue("startOfThisYear")
    START_OF_THIS_YEAR("startOfThisYear"),
    @XmlEnumValue("startOfWeekBeforeLast")
    START_OF_WEEK_BEFORE_LAST("startOfWeekBeforeLast"),
    @XmlEnumValue("tenDaysAgo")
    TEN_DAYS_AGO("tenDaysAgo"),
    @XmlEnumValue("tenDaysFromNow")
    TEN_DAYS_FROM_NOW("tenDaysFromNow"),
    @XmlEnumValue("thirtyDaysAgo")
    THIRTY_DAYS_AGO("thirtyDaysAgo"),
    @XmlEnumValue("thirtyDaysFromNow")
    THIRTY_DAYS_FROM_NOW("thirtyDaysFromNow"),
    @XmlEnumValue("thisBusinessWeek")
    THIS_BUSINESS_WEEK("thisBusinessWeek"),
    @XmlEnumValue("thisFiscalHalf")
    THIS_FISCAL_HALF("thisFiscalHalf"),
    @XmlEnumValue("thisFiscalHalfToDate")
    THIS_FISCAL_HALF_TO_DATE("thisFiscalHalfToDate"),
    @XmlEnumValue("thisFiscalQuarter")
    THIS_FISCAL_QUARTER("thisFiscalQuarter"),
    @XmlEnumValue("thisFiscalQuarterToDate")
    THIS_FISCAL_QUARTER_TO_DATE("thisFiscalQuarterToDate"),
    @XmlEnumValue("thisFiscalYear")
    THIS_FISCAL_YEAR("thisFiscalYear"),
    @XmlEnumValue("thisFiscalYearToDate")
    THIS_FISCAL_YEAR_TO_DATE("thisFiscalYearToDate"),
    @XmlEnumValue("thisMonth")
    THIS_MONTH("thisMonth"),
    @XmlEnumValue("thisMonthToDate")
    THIS_MONTH_TO_DATE("thisMonthToDate"),
    @XmlEnumValue("thisRollingHalf")
    THIS_ROLLING_HALF("thisRollingHalf"),
    @XmlEnumValue("thisRollingQuarter")
    THIS_ROLLING_QUARTER("thisRollingQuarter"),
    @XmlEnumValue("thisRollingYear")
    THIS_ROLLING_YEAR("thisRollingYear"),
    @XmlEnumValue("thisWeek")
    THIS_WEEK("thisWeek"),
    @XmlEnumValue("thisWeekToDate")
    THIS_WEEK_TO_DATE("thisWeekToDate"),
    @XmlEnumValue("thisYear")
    THIS_YEAR("thisYear"),
    @XmlEnumValue("threeDaysAgo")
    THREE_DAYS_AGO("threeDaysAgo"),
    @XmlEnumValue("threeDaysFromNow")
    THREE_DAYS_FROM_NOW("threeDaysFromNow"),
    @XmlEnumValue("threeFiscalQuartersAgo")
    THREE_FISCAL_QUARTERS_AGO("threeFiscalQuartersAgo"),
    @XmlEnumValue("threeFiscalQuartersAgoToDate")
    THREE_FISCAL_QUARTERS_AGO_TO_DATE("threeFiscalQuartersAgoToDate"),
    @XmlEnumValue("threeFiscalYearsAgo")
    THREE_FISCAL_YEARS_AGO("threeFiscalYearsAgo"),
    @XmlEnumValue("threeFiscalYearsAgoToDate")
    THREE_FISCAL_YEARS_AGO_TO_DATE("threeFiscalYearsAgoToDate"),
    @XmlEnumValue("threeMonthsAgo")
    THREE_MONTHS_AGO("threeMonthsAgo"),
    @XmlEnumValue("threeMonthsAgoToDate")
    THREE_MONTHS_AGO_TO_DATE("threeMonthsAgoToDate"),
    @XmlEnumValue("today")
    TODAY("today"),
    @XmlEnumValue("tomorrow")
    TOMORROW("tomorrow"),
    @XmlEnumValue("twoDaysAgo")
    TWO_DAYS_AGO("twoDaysAgo"),
    @XmlEnumValue("twoDaysFromNow")
    TWO_DAYS_FROM_NOW("twoDaysFromNow"),
    @XmlEnumValue("weekAfterNext")
    WEEK_AFTER_NEXT("weekAfterNext"),
    @XmlEnumValue("weekAfterNextToDate")
    WEEK_AFTER_NEXT_TO_DATE("weekAfterNextToDate"),
    @XmlEnumValue("weekBeforeLast")
    WEEK_BEFORE_LAST("weekBeforeLast"),
    @XmlEnumValue("weekBeforeLastToDate")
    WEEK_BEFORE_LAST_TO_DATE("weekBeforeLastToDate"),
    @XmlEnumValue("yesterday")
    YESTERDAY("yesterday");
    private final String value;

    SearchDate(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SearchDate fromValue(String v) {
        for (SearchDate c: SearchDate.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
