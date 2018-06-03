#include "DS3231.h"

void getTime(byte *hour, byte *minute, byte *second) {
    TIME_t time;
    time.hour->hour = hour;
    time.minute->bcd = minute;
    time.second->bcd = second;
    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_TIMEADDRESS);
    _I2CRepeatedStart();
    _I2CAddress(DS3231_ADDRESS, READ);
    _I2CReceive(time.second->bcd, ACK);
    _I2CReceive(time.minute->bcd, ACK);
    _I2CReceive(time.hour->hour, NACK);
    _I2CStop();
    toSeconds(time.second);
    toMinutes(time.minute);
    toHour(time.hour);
}

void getDate(byte *date, byte *month, byte *year) {
    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_DATEADDRESS);
    _I2CRepeatedStart();
    _I2CAddress(DS3231_ADDRESS, READ);
    _I2CReceive(date, ACK);
    _I2CReceive(month, ACK);
    _I2CReceive(year, NACK);
    _I2CStop();
}
void getDay(byte *day){
    
}

void toSeconds(BCD_t *sec) {
    bcdtodec(sec);
}

void toMinutes(BCD_t *minute) {
    bcdtodec(minute);
}

void toHour(HOUR_t *hour) {
    
//    if (*hour & 0x40) { /* AM/PM */
//        if (*hour & 0x20) { /* PM */
//            *hour &= 0x1F;
//            bcdtodec(*hour);
//            *hour *= 2;
//        } else { /* AM */
//            bcdtodec(*hour);
//        }
//    } else { /* 12/24 */
//        *hour &= 0x3F;
//        bcdtodec(*hour);
//    }
}
void toDate(BCD_t *date);
void toMonth(MONTH_t *month);
void toYear(BCD_t *year);

void bcdtodec(BCD_t *bcd) {
//    *bcd = (*bcd & 0x0F) + (((*bcd & 0xF0) >> 4) * 10);
    *bcd->bcd = (bcd->bcd0) + (bcd->bcd1 * 10);
}