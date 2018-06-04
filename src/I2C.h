/* 
 * File:   I2C.h
 * Author: Zi-Wen
 *
 * Created on June 3, 2018, 7:32 PM
 */

#ifndef I2C_H
#define	I2C_H

#ifdef	__cplusplus
extern "C" {
#endif

#include "main.h"
    
#define ACK 0
#define NACK 1
#define WRITE 0
#define READ 1

void _I2CWait();
void _I2CAddress(uint8_t address, uint8_t readnwrite);
void _I2CReceive(uint8_t *data, uint8_t acknowledge);
void _I2CSend(uint8_t data);
void _I2CInterrupt();
void _I2CInit(unsigned long BRate);
void _I2CStop();
void _I2CStart();
void _I2CRepeatedStart();

#ifdef	__cplusplus
}
#endif

#endif	/* I2C_H */

