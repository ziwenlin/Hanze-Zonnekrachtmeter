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
            byte hour3 : 1;
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

void toSeconds(byte *second);
void toMinutes(byte *minute);
void toHour(byte *hour);
void toDate(byte *day);
void toMonth(byte *month);
void toYear(byte *year);

void bcdtodec(byte *dec);
    
#ifdef	__cplusplus
}
#endif

#endif	/* DS3231_H */

