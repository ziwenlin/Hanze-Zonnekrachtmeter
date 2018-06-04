#ifndef ONEWIRE_H_   /* Include guard */
#define ONEWIRE_H_

#define _XTAL_FREQ 8000000

//onewirePin defines
#define onewirePin PORTAbits.RA1
#define onewirePinDirection TRISAbits.TRISA1 //0 = output, 1 = input

void onewireWriteBit(int b);
unsigned char onewireReadBit();
unsigned char onewireInit();
unsigned char onewireReadByte();
void onewireWriteByte(char data);
unsigned char onewireCRC(unsigned char* addr, unsigned char len);

#endif