#include "main.h"
#include "SPI.h"
#include "EUSART.h"
#include "I2C.h"
#include "DS3231.h"
#include "DS18B20.h"
#include "onewire.h"

uint8_t hour = 0, minute = 0, second = 0,
        dow = 1, day = 1, month = 1, year = 20;
uint16_t year1 = 2000;

DBYTE timer0;

float temp = 0;

uint8_t start = 1;
uint8_t flag = 0;

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
    if (INTCONbits.TMR0IF == 1) {
        INTCONbits.TMR0IF = 0;
        TMR0H = timer0.b;
        TMR0L = timer0.a;
        //        TMR0H = 0x48;
        //        TMR0L = 0xE5;
        start = 1;
    }
}

void main(void) {
    setup();
    ei();
    printf("\n\n\n\n\r");
    printf("--------START--------\n\r");
    while (1) {
        loop();
        start = 0;
        while (!start);
    }
}

void setup() {
    _UART1Init(9600);
    _I2CInit(100000);
    timer0init(&timer0, 1);
//    ANCON0bits.ANSEL1 = 0;
}

void loop() {
//    broadcastConvert();
//    temp = getTemperature(address);
    getTime(&hour, &minute, &second);
    getDate(&day, &month, &year);
    printf("%4d/%2d/%2d-%2d:%2d:%2d\n\r", year1 + year, month, day, hour, minute, second);
    printf("%4d,%2d,%2d,%2d,%2d,%2d,%f\n\r", year1 + year, month, day, hour, minute, second, temp);
}
