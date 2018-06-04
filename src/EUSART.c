#include "EUSART.h"

void _UART1Receive(uint8_t* message) {
    *message = RCREG1;
}

void _UART1Send(uint8_t* message) {
    while (TXSTA1bits.TRMT == 0);
    TXREG1 = *message;
}

void _UART1Init(unsigned long BRate) {
    TRISCbits.TRISC6 = 1; // Tx1 Output
    TRISCbits.TRISC7 = 1; // Rx1 Input
    PIR1bits.TX1IF = 0; // Flag off
    PIR1bits.RC1IF = 0; // FLag off
    PIE1bits.RC1IE = 1; // Enable Rx interupt USART1
    PIE1bits.TX1IE = 1; // Enable Tx interupt USART1
    TXSTA1 = 0xA0; //Zend register
    RCSTA1 = 0x90; //Ontvang register
    BAUDCON1 = 0x40; //Baud register
    SPBRG1 = (uint8_t) ((_XTAL_FREQ / BRate / 64) - 1); //Baud rate generator register
}

void putch(char c) {
    while (TXSTA1bits.TRMT == 0);
    TXREG1 = c;
}