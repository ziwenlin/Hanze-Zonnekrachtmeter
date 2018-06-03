#include "DS3231.h"

void getTime(byte *hour, byte *minute, byte *second) {
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
    _I2CStart();
    _I2CAddress(DS3231_ADDRESS, WRITE);
    _I2CSend(DS3231_DAYADDRESS);
    _I2CRepeatedStart();
    _I2CAddress(DS3231_ADDRESS, READ);
    _I2CReceive(day, NACK);
    _I2CStop();
}

void toSeconds(byte *sec) {
    bcdtodec(sec);
}

void toMinutes(byte *minute) {
    bcdtodec(minute);
}

void toHour(byte *hour) {
    
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

void toDate(byte *date) {
    bcdtodec(date);
}

void toMonth(byte *month) {
    
}

void toYear(byte *year) {
    bcdtodec(year);
}

void bcdtodec(byte *dec) {
    BCD_t bcd;
    bcd.bcd = *dec;
    *dec = (bcd.bcd0) + (bcd.bcd1) * 10;
}