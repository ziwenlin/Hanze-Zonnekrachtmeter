#include "EUSART.h"

void _UART1Receive(uint8_t *message) {
    *message = RCREG1;
}

void _UART1Send(uint8_t *message) {
    for (uint16_t i = 0; *(message + i) != '\0'; i++) {
        while (TXSTA1bits.TRMT == 0);
        TXREG1 = *(message + i);
    }
    //while (TXSTA1bits.TRMT == 0);
    //TXREG1 = 0x0A;
    while (TXSTA1bits.TRMT == 0);
    TXREG1 = 0x0A;
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

void _UART2Receive(uint8_t* message) {
    *message = RCREG2;
}

void _UART2Send(uint8_t *message) {
    for (uint16_t i = 0; *(message + i) != '\0'; i++) {
        while (TXSTA2bits.TRMT == 0);
        TXREG2 = *(message + i);
    }
    //while (TXSTA2bits.TRMT == 0);
    //TXREG2 = 0x0A;
    while (TXSTA2bits.TRMT == 0);
    TXREG2 = 0x0A0;
}

void _UART2Init(unsigned long BRate) {
    TRISBbits.TRISB6 = 1; // Tx1 Output
    TRISBbits.TRISB7 = 1; // Rx1 Input
    PIR3bits.TX2IF = 0; // Flag off
    PIR3bits.RC2IF = 0; // FLag off
    PIE3bits.RC2IE = 1; // Enable Rx interupt USART1
    PIE3bits.TX2IE = 1; // Enable Tx interupt USART1
    TXSTA2 = 0xA0; //Zend register
    RCSTA2 = 0x90; //Ontvang register
    BAUDCON2 = 0x40; //Baud register
    SPBRG2 = (uint8_t) ((_XTAL_FREQ / BRate / 64) - 1); //Baud rate generator register
}
