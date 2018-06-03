#include "com.h"

void _EnableAllInterrupts() {

    RCONbits.IPEN = 0;
    INTCONbits.GIE = 1;
    INTCONbits.PEIE = 1;

}

void _I2CAddress(byte address, byte readnwrite) {
    _I2CSend(address * 2 + readnwrite);
}

void _I2CWait() {
    while ((SSPSTAT & 0x04) || (SSPCON2 & 0x1F)); //Transmit is in progress
}

void _I2CReceive(byte *data, byte ack) {
    _I2CWait();
    SSPCON2bits.RCEN = 1;
    _I2CWait();
    *data = SSPBUF;
    _I2CWait();
    SSPCON2bits.ACKDT = ack;
    SSPCON2bits.ACKEN = 1;
    while (SSPCON2bits.ACKEN == 1);
    while (PIR2bits.BCLIF == 1); /* Error occurred <---> Check Pullup Resistors */
}

void _I2CSend(byte data) {
    _I2CWait();
    SSPBUF = data;
    while (SSPSTATbits.BF == 1);
    while (PIR2bits.BCLIF == 1); /* Error occurred <---> Check Pullup Resistors */
}

void _I2CStop() {
    do {
        PIR2bits.BCLIF = 0;
        _I2CWait();
        SSPCON2bits.PEN = 1;
        while (SSPCON2bits.PEN == 1); /* Wait till Start is finished */
    } while (PIR2bits.BCLIF == 1); /* Error occurred <---> Check Pullup Resistors */
}

void _I2CStart() {
    do {
        PIR2bits.BCLIF = 0;
        _I2CWait();
        SSPCON2bits.SEN = 1;
        while (SSPCON2bits.SEN == 1); /* Wait till Start is finished */
    } while (PIR2bits.BCLIF == 1); /* Error occurred <---> Check Pullup Resistors */
}

void _I2CRepeatedStart() {
    do {
        PIR2bits.BCLIF = 0;
        _I2CWait();
        SSPCON2bits.RSEN = 1;
        while (SSPCON2bits.RSEN == 1); /* Wait till Repeated Start is finished */
    } while (PIR2bits.BCLIF == 1); /* Error occurred <---> Check Pullup Resistors */
}

void _I2CInit(const unsigned long BRate) {
    SSPCON1bits.SSPEN = 0; /* Disable I2C I/O */
    TRISCbits.TRISC4 = 1; /* Pin SDA is input */
    TRISCbits.TRISC3 = 1; /* Pin SCL is input */
    SSPSTATbits.CKE = 0; /* Slew rate 400kHz */
    SSPSTATbits.SMP = 0; /* Disable SMBus */
    SSPCON1bits.WCOL = 0; /* No write collision */
    SSPCON1bits.SSPOV = 0; /* No recieve overflow */
    SSPCON1bits.SSPM = 0x8; /* Master mode BRG = Fosc /(4*(SSPADD + 1)) */
    SSPADD = (_XTAL_FREQ / 4 / BRate - 1); /* SSPADD = Fosc / (BRG * 4) - 1 */
    PIR1bits.SSPIF = 0;
    PIE1bits.SSPIE = 1;
    //    IPR1bits.SSPIP = 1;
    PIR2bits.BCLIF = 0;
    PIE2bits.BCLIE = 1;
    //    IPR2bits.BCLIP = 1;
    SSPCON1bits.SSPEN = 1; /* Enable I2C I/O */
}

void _SPIInit() {
    TRISCbits.TRISC5 = 0; // Pin SDO is output:
    TRISCbits.TRISC4 = 1; // Pin SDI is input:
    TRISCbits.TRISC3 = 0; // Pin SCK is output:
    TRISAbits.TRISA5 = 0; // Pin SS is output:
    SSPSTATbits.SMP = 1; // Sample Time bit: Data sampeling in het einde van de bit output tijd
    SSPSTATbits.CKE = 0; // Clock Edge Select bit: Zending gebeurt op de rising edge
    SSPCON1bits.WCOL = 0; // Write Collision Detect bit:
    SSPCON1bits.SSPOV = 0; // Receive Overflow Indicator bit:
    SSPCON1bits.CKP = 0; // Clock Polarity bit: Idle state van de clock op low 
    SSPCON1bits.SSPM = 0b0010; // Master Synchronous Serial Port Mode Select bit: Clock snelheid /64, Mastermode

    // Master Synchronous Serial Port
    PIR1bits.SSPIF = 0; // Interrupt Flag bit
    PIE1bits.SSPIE = 0; // Interrupt Enable bit:
    IPR1bits.SSPIP = 0; // Interrupt Priority bit: Zet op 1 bij foutmeldingen
    SSPCON1bits.SSPEN = 1; // Enable bit: Alle (TRISx) pinnen moeten goed zijn ingesteld, voordat deze geset wordt

}

void _EUSART1Init(const unsigned long BRate) {
    TRISCbits.TRISC6 = 0; //Tx1 Output
    TRISCbits.TRISC7 = 0; //Rx1 Input
    PIE1bits.RC1IE = 0; //Enable/Disable Rx interupt USART1
    PIE1bits.TX1IE = 0; //Enable/Disable Tx interupt USART1
    TXSTA1 = 0xA0; //Zend register
    RCSTA1 = 0x90; //Ontvang register
    BAUDCON1 = 0x40; //Baud register
    SPBRG1 = (byte) ((_XTAL_FREQ / BRate / 64) - 1); //Baud rate generator register
}

void putch(char c) {
    while (TXSTA1bits.TRMT == 0);
    TXREG1 = c;
}