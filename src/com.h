/* 
 * File:   com.h
 * Author: Zi-Wen
 *
 * Created on 30 mei 2018, 9:19
 */

#include "main.h"

#ifndef COM_H
#define	COM_H

#ifdef	__cplusplus
extern "C" {
#endif

#define ACK 0
#define NACK 1
#define WRITE 0
#define READ 1

    void _I2CWait();
    void _I2CAddress(byte address, byte readnwrite);
    void _I2CReceive(byte *data, byte acknowledge);
    void _I2CSend(byte data);
    void _I2CInterrupt();
    void _I2CInit(const unsigned long BRate);
    void _I2CStop();
    void _I2CStart();
    void _I2CRepeatedStart();

    void _SPIInit();
    void _EUSART1Init(const unsigned long BRate);

    void putch(char c);

#ifdef	__cplusplus
}
#endif

#endif	/* COM_H */

//#define _MSSPIF PIR1bits.SSPIF /* MSSP Interrupt Flag */
//#define _MSSPIE PIE1bits.SSPIE /* MSSP Interrupt Enable */
//#define _MSSPIP IPR1bits.SSPIP /* MSSP Interrupt Priority */
//#define _MSSPBCIF PIR2bits.BCLIF /* Bus Collision Interrupt Flag */
//#define _MSSPBCIE PIE2bits.BCLIE /* Bus Collision Interrupt Enable */
//#define _MSSPBCIP IPR2bits.BCLIP /* Bus Collision Interrupt Priority */
//
//#define _MSSPBUF SSPBUFbits.SSPBUF /* MSSP Send/Receive Buffer */
//#define _MSSPBF SSPSTATbits.BF /* MSSP Buffer Full Indicator */
//#define _MSSPWCOL SSPCON1bits.WCOL /**/


//typedef union {
//    struct {
//        byte Mode:1;
//        byte Addresss:7;
//    } fields;
//    byte Data;
//} SomeStruct_t;

//typedef enum {
//    B_TRUE = 1,
//    B_FALSE = 0
//} BOOL;

//typedef enum  {
//    COM_OK = 0,
//    COM_ERROR
//} COM_RESULT;
