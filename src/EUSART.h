/* 
 * File:   EUSART.h
 * Author: Zi-Wen
 *
 * Created on June 3, 2018, 7:35 PM
 */

#ifndef EUSART_H
#define	EUSART_H

#ifdef	__cplusplus
extern "C" {
#endif

#include "main.h"
    
void _UART1Send(uint8_t *message);
void _UART1Receive(uint8_t *message);
void _UART1Init(unsigned long BRate);

void _UART2Receive(uint8_t* message);
void _UART2Send(uint8_t* message);
void _UART2Init(unsigned long BRate);

#ifdef	__cplusplus
}
#endif

#endif	/* EUSART_H */

