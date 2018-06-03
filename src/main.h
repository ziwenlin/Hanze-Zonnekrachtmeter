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

#define CLEAR 0
#define SET 1




#ifdef	__cplusplus
}
#endif

#endif	/* MAIN_H */

typedef unsigned char byte;
void interrupt isr(void);
void loop();
void delay(unsigned int delay);