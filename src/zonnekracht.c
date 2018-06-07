
#include "main.h"

void getADC9(float *zonnekracht){ //PV-paneel
    int adc = 0;
    ADCON0 = 0x27; //Channel AN1, Start ADC conversion, ADC ON
    ADCON1 = 0x00; //Trigger ECCP1, AVdd, AVss, Neg Channel00 (AVss)
    ADCON2 = 0xAE; //Right justified, Tad = 0, Conversion Clock Fosc/32
    while(ADCON0bits.GO); //Conversion Ready
    adc = ADRESH<<8|ADRESL;
    *zonnekracht = adc / 4096.0 * 5.0;
}