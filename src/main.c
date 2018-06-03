#include "main.h"
#include "com.h"
#include "DS3231.h"

byte hour = 0, minute = 0, second = 0;
unsigned int flag = 0;

void interrupt isr(void) {
    flag++;
    if (PIR1bits.SSPIF == 1) {
        PIR1bits.SSPIF = 0; /* MSSP Buffer full */
        if (SSPCON1bits.WCOL || SSPCON1bits.SSPOV) {
            SSPCON1bits.SSPOV = 0; // Clear the overflow flag
            SSPCON1bits.WCOL = 0; // Clear the collision bit
        }
    }
    if (PIR2bits.BCLIF == 1) {
        PIR2bits.BCLIF = 0;
    }

}

void main(void) {
    _EUSART1Init(9600);
    _I2CInit(100000);
    ei();
    printf("--------START--------\n\r");
    getTime(&hour, &minute, &second);
    printf("%#2d:%2d:%2d\n\r", hour, minute, second);

    while (1) {
        loop();
        delay(1);
    }
}

void loop() {
    getTime(&hour, &minute, &second);
    printf("%#2d:%2d:%2d\n\r", hour, minute, second);
}

void delay(unsigned int delay) {
    for (unsigned int i = 0; i < delay; i++)
        _delay(0xFFFF);
}