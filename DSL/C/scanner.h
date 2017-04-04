#ifndef SCANNER_H
#define SCANNER_H

enum {
	T_SHAPE=257, T_SIZE, T_DEF, T_END, T_NAME, T_NUMBER
};

extern int LA;
extern char Value[];

void InitScanner(const char* fname);
void Next(void);

#endif        //  #ifndef SCANNER_H
