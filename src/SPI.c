#include "SPI.h"

void _EnableAllInterrupts() {

    RCONbits.IPEN = 0;
    INTCONbits.GIE = 1;
    INTCONbits.PEIE = 1;

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
