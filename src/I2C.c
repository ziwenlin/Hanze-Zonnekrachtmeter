#include "I2C.h"

void _I2CAddress(uint8_t address, uint8_t readnwrite) {
    _I2CSend(address * 2 + readnwrite); // Slave aanroepen
}

void _I2CWait() {
    while ((SSPSTAT & 0x04) || (SSPCON2 & 0x1F)); //Transmit is in progress
}

void _I2CReceive(uint8_t *data, uint8_t ack) { // Data ontvangen
    _I2CWait(); // Wacht tot de poorten klaar zijn
    SSPCON2bits.RCEN = 1; // Receive poort enable
    _I2CWait(); // Wacht tot de poorten klaar zijn
    *data = SSPBUF; // Data opslaan
    _I2CWait(); // Wacht tot de poorten klaar zijn
    SSPCON2bits.ACKDT = ack; // Acknowledge
    SSPCON2bits.ACKEN = 1; // Acknowledge sequence starten
    while (SSPCON2bits.ACKEN == 1);
//    while (PIR2bits.BCLIF == 1); /* Error occurred <---> Check Pullup Resistors */
}

void _I2CSend(uint8_t data) {
    _I2CWait(); // Wacht tot de poorten klaar zijn
    SSPBUF = data; // Data sturen
    while (SSPSTATbits.BF == 1); // Wacht tot de buffer leeg is
//    while (PIR2bits.BCLIF == 1); /* Error occurred <---> Check Pullup Resistors */
}

void _I2CStop() {
        PIR2bits.BCLIF = 0;
        _I2CWait(); // Wacht tot de poorten klaar zijn
        SSPCON2bits.PEN = 1; // Stop sequence starten
        while (SSPCON2bits.PEN == 1); /* Wait till Start is finished */
}

void _I2CStart() {
        PIR2bits.BCLIF = 0;
        _I2CWait();  // Wacht tot de poorten klaar zijn
        SSPCON2bits.SEN = 1; // Start sequence starten
        while (SSPCON2bits.SEN == 1); /* Wait till Start is finished */
}

void _I2CRepeatedStart() {
        PIR2bits.BCLIF = 0;
        _I2CWait(); // Wacht tot de poorten klaar zijn
        SSPCON2bits.RSEN = 1; // HErhaalde start sequence starten
        while (SSPCON2bits.RSEN == 1); /* Wait till Repeated Start is finished */
}

void _I2CInit(unsigned long BRate) {
    SSPCON1bits.SSPEN = 0; /* Disable I2C I/O */
    TRISCbits.TRISC4 = 1; /* Pin SDA is input */
    TRISCbits.TRISC3 = 1; /* Pin SCL is input */
    SSPSTATbits.CKE = 0; /* Slew rate 400kHz */
    SSPSTATbits.SMP = 0; /* Disable SMBus */
    SSPCON1bits.WCOL = 0; /* No write collision */
    SSPCON1bits.SSPOV = 0; /* No recieve overflow */
    SSPCON1bits.SSPM = 0x8; /* Master mode BRG = Fosc /(4*(SSPADD + 1)) */
    SSPADD = (_XTAL_FREQ / 4 / BRate - 1); /* SSPADD = Fosc / (BRG * 4) - 1 */
    PIR1bits.SSPIF = 0; /* Interrupt flag uit */
    PIE1bits.SSPIE = 1; /* Interrupt aan */
    //    IPR1bits.SSPIP = 1;
    PIR2bits.BCLIF = 0; /* Interrupt flag uit */
    PIE2bits.BCLIE = 1; /* Interrupt aan */
    //    IPR2bits.BCLIP = 1;
    SSPCON1bits.SSPEN = 1; /* Enable I2C I/O */
}
