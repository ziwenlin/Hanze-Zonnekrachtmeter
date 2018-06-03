/* 
 * File:   DS3231.h
 * Author: Zi-Wen
 *
 * Created on 2 juni 2018, 15:52
 */

#ifndef DS3231_H
#define	DS3231_H

#ifdef	__cplusplus
extern "C" {
#endif

#include "main.h"
#include "com.h"
#define DS3231_ADDRESS 0x68
#define DS3231_TIMEADDRESS 0x00
#define DS3231_DAYADDRESS 0x03
#define DS3231_DATEADDRESS 0x04

typedef union {
    struct {
        byte hour0 : 4;
        byte hour1 : 1;
        byte hour2 : 1;
        byte mode : 1;
    };
    byte hour;
} HOUR_t;

typedef union {
    struct {
        byte month0:4;
        byte month1:3;
        byte century:1;
    };
    byte month;
} MONTH_t;
    
typedef union {
    struct {
        byte bcd0 : 4;
        byte bcd1 : 4;
    };
    byte bcd;
} BCD_t;

void getTime(byte *hour, byte *minute, byte *second);
void getDate(byte *day, byte *month, byte *year);
void getDay(byte *day);

//void setRTC(byte second, byte minute, byte hour, byte dow, byte day, byte month, byte year);
void setRTC(byte second, byte minute, byte hour, byte day, byte month, byte year);
void setDay(byte day);

#define toSeconds(second) bcdtodec(second)
#define toMinutes(minute) bcdtodec(minute)
#define toDay(day) bcdtodec(day)
#define toYear(year) bcdtodec(year)

#define trSeconds(second) dectobcd(second)
#define trMinutes(minute) dectobcd(minute)
#define trDay(day) dectobcd(day)
#define trYear(year) dectobcd(year)
#define trHour(hour) dectobcd(hour)

void toHour(byte *hour);
void toMonth(byte *month, byte *year);

void trMonth(byte *month, byte *year);

void bcdtodec(byte *dec);
void dectobcd(byte *bcd);


#ifdef	__cplusplus
}
#endif

#endif	/* DS3231_H */

