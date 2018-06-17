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
#include "I2C.h"
#define DS3231_ADDRESS 0x68
#define DS3231_TIMEADDRESS 0x00
#define DS3231_DAYADDRESS 0x03
#define DS3231_DATEADDRESS 0x04

typedef union {
    struct {
        uint8_t hour0 : 4;
        uint8_t hour1 : 1;
        uint8_t hour2 : 1;
        uint8_t mode : 1;
    };
    uint8_t hour;
} HOUR_t;

typedef union {
    struct {
        uint8_t month0:4;
        uint8_t month1:3;
        uint8_t century:1;
    };
    uint8_t month;
} MONTH_t;
    
typedef union {
    struct {
        uint8_t bcd0 : 4;
        uint8_t bcd1 : 4;
    };
    uint8_t bcd;
} BCD_t;

void getTime(uint8_t *hour, uint8_t *minute, uint8_t *second);
void getDate(uint8_t *day, uint8_t *month, uint8_t *year);
void getDay(uint8_t *day);

//void setRTC(byte second, byte minute, byte hour, byte dow, byte day, byte month, byte year);
void setRTC(uint8_t second, uint8_t minute, uint8_t hour, uint8_t day, uint8_t month, uint8_t year);
void setDay(uint8_t day);

#define toSeconds(second) bcdtodec(second)
#define toMinutes(minute) bcdtodec(minute)
#define toDay(day) bcdtodec(day)
#define toYear(year) bcdtodec(year)

#define rtcSeconds(second) dectobcd(second)
#define rtcMinutes(minute) dectobcd(minute)
#define rtcDay(day) dectobcd(day)
#define rtcYear(year) dectobcd(year)
#define rtcHour(hour) dectobcd(hour)

void toHour(uint8_t *hour);
void toMonth(uint8_t *month, uint8_t *year);

void rtcMonth(uint8_t *month, uint8_t *year);

void bcdtodec(uint8_t *dec);
void dectobcd(uint8_t *bcd);


#ifdef	__cplusplus
}
#endif

#endif	/* DS3231_H */

