#include "DS3231.h"

//void setRTC(byte second, byte minute, byte hour, byte dow, byte day, byte month, byte year) {
//    
//}

void setRTC(uint8_t second, uint8_t minute, uint8_t hour, uint8_t day, uint8_t month, uint8_t year) {

    trSeconds(&second);
    trMinutes(&minute);
    trDay(&day);
    trMonth(&month, &year);
    trYear(&year);
    trHour(&hour);

    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_DATEADDRESS);
    _I2CSend(day);
    _I2CSend(month);
    _I2CSend(year);
    _I2CStop();

    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_TIMEADDRESS);
    _I2CSend(second);
    _I2CSend(minute);
    _I2CSend(hour);
    _I2CStop();
}

void setDay(uint8_t day) {
    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_DAYADDRESS);
    _I2CSend(day);
    _I2CStop();
}

void getTime(uint8_t *hour, uint8_t *minute, uint8_t *second) {
    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_TIMEADDRESS);
    _I2CRepeatedStart();
    _I2CAddress(DS3231_ADDRESS, READ);
    _I2CReceive(second, ACK);
    _I2CReceive(minute, ACK);
    _I2CReceive(hour, NACK);
    _I2CStop();
    toSeconds(second);
    toMinutes(minute);
    toHour(hour);
}

void getDate(uint8_t *day, uint8_t *month, uint8_t *year) {
    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_DATEADDRESS);
    _I2CRepeatedStart();
    _I2CAddress(DS3231_ADDRESS, READ);
    _I2CReceive(day, ACK);
    _I2CReceive(month, ACK);
    _I2CReceive(year, NACK);
    _I2CStop();
    toYear(year);
    toMonth(month, year);
    toDay(day);
}

void getDay(uint8_t *day) {
    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_DAYADDRESS);
    _I2CRepeatedStart();
    _I2CAddress(DS3231_ADDRESS, READ);
    _I2CReceive(day, NACK);
    _I2CStop();
}

void trMonth(uint8_t *month, uint8_t *year) {
    dectobcd(month);
    MONTH_t m;
    m.month = *month;
    m.century = (*year >= 100);
    *month = m.month;
}

void toHour(uint8_t *hour) {
    HOUR_t h;
    h.hour = *hour;
    *hour = h.hour0 + h.hour1 * 10;
    if (h.mode) { /* AM/PM if true */
        if (h.hour2) {/* PM (Prettige Middag) */
            *hour += 12;
        } /* AM (Akelige Morgen) */
    } else {
        *hour += h.hour2 * 20;
    }
}

void toMonth(uint8_t *month, uint8_t *year) {
    MONTH_t m;
    m.month = *month;
    *month = (m.month0) + (m.month1) * 10;
    *year += m.century * 100;
}

void bcdtodec(uint8_t *dec) {
    BCD_t bcd;
    bcd.bcd = *dec;
    *dec = (bcd.bcd0) + (bcd.bcd1) * 10;
}

void dectobcd(uint8_t *bcd) {
    if (*bcd > 100) return;
    BCD_t dec;
    dec.bcd1 = (*bcd / 10);
    dec.bcd0 = (*bcd - dec.bcd1 * 10);
    *bcd = (dec.bcd);
}