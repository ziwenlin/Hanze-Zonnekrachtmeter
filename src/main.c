#include "main.h"
#include "SPI.h"
#include "EUSART.h"
#include "I2C.h"
#include "DS3231.h"
#include "DS18B20.h"
#include "onewire.h"
/*
 * 
 * Initialisatie/Declaratie variabelen
 *
 */
uint8_t hour = 0, minute = 0, second = 0,
        dow = 1, day = 1, month = 1, year = 20;
uint16_t year1 = 2000;
DBYTE timer0;
float temp = 0, adc = 0;
float tempAvg = 0, adcAvg = 0;
uint8_t count = 0;
uint8_t start = 1, read1 = 0, read2 = 0;
uint8_t tempAddress[8] = {0x28, 0x53, 0x44, 0xa3, 0x8, 0x0, 0x0, 0x9b};
uint8_t rx1length = 0, rx2length = 0;
char message[80], msgrx1[80], msgrx2[80];

void main(void) {
    setup(); 
    while (1) {
        if (start && !read1 && !read2) loop();
//        if (read1);
//        if (read2);
        start = 0; // Wacht tot de volgende seconde
        while (!start);
    }
}

void setup() {
    _UART1Init(9600); // RS232 poort 1 initialisatie
    _UART2Init(9600); // RS232 poort 2 initialisatie
    _I2CInit(100000); // I2C initialisatie
    timer0init(&timer0, 1000); // Timer initialisatie
    ANCON0bits.ANSEL1 = 0; // Pin 3 ADC uit
    ei(); // Alle interrupts aan
    sprintf(&message, "Jaar,Maand,Dag,Uur,Minuut,Temperatuur,Zonnekracht"); // CSV tabel formaat
    _UART2Send(&message); // Verstuur bericht
}

void loop() {
    broadcastConvert();
    temp = getTemperature(tempAddress); // Temperatuur waarde lezen.
    getTime(&hour, &minute, &second); // Uur, minuten en seconden waarden aanvragen en ontvangen,
    getDate(&day, &month, &year); // Dag, maand en jaar waarden aanvragen en ontvangen.
    getADC9(&adc); // Analoge waarde van de 9de ADC lezen
    sprintf(&message, "%04d/%02d/%02d-%02d:%02d:%02d   Temperatuur: %f C   Zonnekracht: %f w/m2", 
            (year1 + year), month, day, hour, minute, second, temp, adc); // Maakt bericht klaar
    _UART1Send(&message); // Verstuur bericht
    tempAvg = (tempAvg * count + temp) / (count + 1); // Gemiddelde temperatuur bereken
    adcAvg = (adcAvg * count + adc) / (count + 1); // Gemiddelde waarde van de ADC berekenen
    count++;
    if (count >= 60){
        sprintf(&message, "%04d,%02d,%02d,%02d,%02d,%f,%f",  // Bericht klaarmaken aan het CSV formaat
                (year1 + year), month, day, hour, minute, tempAvg, adcAvg);
        _UART2Send(&message); // Verstuur bericht
        count = 0; 
    }
}

void interrupt isr(void) { // Interrupt Service Routine
    if (PIR1bits.SSPIF == 1) {
        PIR1bits.SSPIF = 0; /* MSSP Buffer full */
        if (SSPCON1bits.WCOL || SSPCON1bits.SSPOV) { // Error bits
            SSPCON1bits.SSPOV = 0; // Clear the overflow flag
            SSPCON1bits.WCOL = 0; // Clear the collision bit
        }
    }
    if (PIR2bits.BCLIF == 1) { // Bus collision Interrupt Flag
        PIR2bits.BCLIF = 0;
    }
    if (INTCONbits.TMR0IF == 1) { // Timer0 Interupt Flag
        INTCONbits.TMR0IF = 0;
        TMR0H = timer0.b;
        TMR0L = timer0.a;
        start = 1;
    }
    if (PIR1bits.RC1IF) { // Rx1 Interrupt Flag
        msgrx1[rx1length] = RCREG1;
        read1 = (msgrx1[rx1length] == '\n') ? 1 : 0;
        rx1length++;
    }
}
