#include "DS3231.h"

void setRTC(uint8_t second, uint8_t minute, uint8_t hour, uint8_t day, uint8_t month, uint8_t year) {
    // RTC Datum en Tijd corrigeren
    // Waarden omzetten van binair naar binair gecodeerd decimalen
    rtcSeconds(&second);
    rtcMinutes(&minute);
    rtcDay(&day);
    rtcMonth(&month, &year);
    rtcYear(&year);
    rtcHour(&hour);

    _I2CStart(); // I2C starten
    _I2CAddress(DS3231_ADDRESS, WRITE); // RTC aanroepen en commando om te luisteren
    _I2CSend(DS3231_DATEADDRESS); // Address pointer op Date zetten
    _I2CSend(day); // Schrijven data dag
    _I2CSend(month); // Schrijven data maand
    _I2CSend(year); // Schrijven data jaar
    _I2CStop(); // I2C stoppen

    _I2CStart(); // I2C starten
    _I2CAddress(DS3231_ADDRESS, WRITE); // RTC aanroepen
    _I2CSend(DS3231_TIMEADDRESS); // Address pointer op Tijd zetten
    _I2CSend(second); // Schrijven data seconden
    _I2CSend(minute); // Schrijven data maand
    _I2CSend(hour); // Schrijven data jaar
    _I2CStop(); // I2C stoppen
}

void setDayOfWeek(uint8_t day) {
    // Maandag, dinsdag, woensdag, donderdag, vrijdag, zaterdag, zondag
    _I2CStart(); // I2C starten
    _I2CAddress(DS3231_ADDRESS, WRITE); // RTC aanroepen
    _I2CSend(DS3231_DAYADDRESS); // Address pointer op DOW zetten
    _I2CSend(day); // Schrijven data dag van de week
    _I2CStop(); // I2C stoppen
}

void getTime(uint8_t *hour, uint8_t *minute, uint8_t *second) {
    // Tijd van de RTC aanvragen en ontvangen
    _I2CStart(); // I2C starten
    _I2CAddress(DS3231_ADDRESS, WRITE); // RTC aanroepen en commando om te luisteren
    _I2CSend(DS3231_TIMEADDRESS); // Address pointer op TIJD zetten
    _I2CRepeatedStart(); // Van schrijven naar lezen schakelen
    _I2CAddress(DS3231_ADDRESS, READ); // RTC opnieuw aanroepen en commando om te zenden
    _I2CReceive(second, ACK); // Data seconden sturen 
    _I2CReceive(minute, ACK); // Data minuten sturen
    _I2CReceive(hour, NACK); // Data uren sturen en stop commando sturen naar RTC
    _I2CStop(); // I2C stoppen
    toSeconds(second); // Data omzetten van BCD naar binair
    toMinutes(minute); // Data omzetten van BCD naar binair
    toHour(hour);  // Data omzetten van speciaal gecodeerde BCD naar binair
}

void getDate(uint8_t *day, uint8_t *month, uint8_t *year) {
    // Datum van de RTC aanvragen en ontvangen
    _I2CStart(); // I2C starten
    _I2CAddress(DS3231_ADDRESS, WRITE); // RTC aanroepen en commando om te luisteren
    _I2CSend(DS3231_DATEADDRESS); // Address pointer op DATE zetten
    _I2CRepeatedStart(); // Van schrijven naar lezen schakelen
    _I2CAddress(DS3231_ADDRESS, READ); // RTC opnieuw aanroepen en commando om te zenden
    _I2CReceive(day, ACK); // Data dag sturen 
    _I2CReceive(month, ACK); // Data maand sturen
    _I2CReceive(year, NACK); // Data jaar ontvangen en stoppen commando sturen naar RTC 
    _I2CStop(); // I2C stoppen
    toYear(year); // Data omzetten van BCD naar binair
    toMonth(month, year);  // Data omzetten van speciaal gecodeerde BCD naar binair
    toDay(day); // Data omzetten van BCD naar binair
}

void getDay(uint8_t *day) {
    _I2CStart(); // I2C starten
    _I2CAddress(DS3231_ADDRESS, WRITE); // RTC aanroepen en commando om te luisteren
    _I2CSend(DS3231_DAYADDRESS); // RTC Addres pointer op DOW zetten
    _I2CRepeatedStart(); // Van schrijven naar lezen schakelen
    _I2CAddress(DS3231_ADDRESS, READ); // RTC opnieuw aanroepen en commando om te zenden
    _I2CReceive(day, NACK); // Data DOW ontvangen en antwoorden om geen data te ontvangen
    _I2CStop(); // I2C stoppen
}

void rtcMonth(uint8_t *month, uint8_t *year) {
    dectobcd(month); // omzetting naar BCD
    MONTH_t m; // Initialisatie struct
    m.month = *month; // Struct invullen
    m.century = (*year >= 100); // Eeuw bit 
    *month = m.month; // Maand waarde terugsturen met eeuw bit
}

void toHour(uint8_t *hour) {
    HOUR_t h;
    h.hour = *hour;
    *hour = h.hour0 + h.hour1 * 10;
    if (h.mode) { /* AM/PM if true, omzetting van PM/AM naar 24 uur mode */
        if (h.hour2) {/* PM (Prettige Middag Ezelbruggetje) */
            *hour += 12;
        } /* AM (Akelige Morgen Ezelbruggetje) */
    } else {
        *hour += h.hour2 * 20;
    }
}

void toMonth(uint8_t *month, uint8_t *year) {
    MONTH_t m;
    m.month = *month;
    *month = (m.month0) + (m.month1) * 10; // BCD naar decimalen
    *year += m.century * 100; // Eeuw bit
}

void bcdtodec(uint8_t *dec) { // Omzetting van BCD naar Decimalen
    BCD_t bcd; // Struct initialiseren
    bcd.bcd = *dec; // Struct invullen met een BCD
    *dec = (bcd.bcd0) + (bcd.bcd1) * 10; // Eerste 4 bits + laatste 4 bits * 10
}

void dectobcd(uint8_t *bcd) {
    if (*bcd > 100) return;  // Als het decimaal groter is dan 100, annuleren!
    // Het getal is waarschijnlijk al BCD
    BCD_t dec; // Struct initialiseren
    dec.bcd1 = (*bcd / 10); // Laatste 4 bits invullen
    dec.bcd0 = (*bcd - dec.bcd1 * 10); // Eerste 4 bits invulllen
    *bcd = (dec.bcd); // BCD doorgeven
}