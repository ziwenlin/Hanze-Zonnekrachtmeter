/* 
 * File:   main.h
 * Author: Zi-Wen
 *
 * Created on 31 mei 2018, 9:18
 */

#ifndef MAIN_H
#define	MAIN_H

#ifdef	__cplusplus
extern "C" {
#endif

#define _XTAL_FREQ 8000000
#include "fuses.h"
#include "xc.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
    
typedef unsigned char uint8_t;
typedef unsigned int uint16_t;

typedef union {
    struct {
        uint16_t a : 8;
        uint16_t b : 8;
    };
    uint16_t ab;
} DBYTE;

void timer0init(DBYTE *timer0, float seconds);
void getADC9(float *zonnekracht);
void main(void);
void interrupt isr(void);
void loop();
void setup();

#ifdef	__cplusplus
}
#endif

#endif	/* MAIN_H */
