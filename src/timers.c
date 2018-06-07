#include "main.h"

void timer0init(DBYTE *timer0, float seconds) {
    timer0->ab = (uint16_t)((0x0BDC) + (0xF424/8.0*(8.0-seconds))); /* TMR0L en TMR0H */
    T0CONbits.TMR0ON = 1;
    T0CONbits.T08BIT = 0;
    T0CONbits.T0CS = 0;
    T0CONbits.T0SE = 0;
    T0CONbits.PSA = 0;
    T0CONbits.T0PS = 0x7;
    INTCON2bits.TMR0IP = 1; /* Timer0 Interrupt Priority */
    INTCONbits.TMR0IF = 0; /* Timer0 Interrupt Flag */
    INTCONbits.TMR0IE = 1; /* Timer0 Interrupt Enable */
}