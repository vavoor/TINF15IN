#include <assert.h>
#include <stdio.h>
#include <ctype.h>
#include <string.h>

#include "scanner.h"
#include "err.h"

static int DEBUG=0;

int LA;
char Value[1024];

static FILE* input;
static int last;

static int mapName(const char* name)
{
  if (strcmp(name,"shape")==0) {
    return T_SHAPE;
  }
  else if (strcmp(name,"size")==0) {
    return T_SIZE;
  }
  else if (strcmp(name,"def")==0) {
    return T_DEF;
  }
  else if (strcmp(name,"end")==0) {
    return T_END;
  }
  else {
    return T_NAME;
  }
}

void InitScanner(const char* fname)
{
  input = fopen(fname,"r");
  if (input==NULL) {
    err("Cannot open input file %s",fname);
  }
  
  last = fgetc(input);
  Next();
}

void Next(void)
{
  char* v = Value;
  
  while (isspace(last)) {
    last = fgetc(input);
  }
  
  if (isalpha(last)) {
    while (isalpha(last)) {
      *v++ = last;
      last = fgetc(input);
    }
    *v = '\0';
    LA = mapName(Value);
  }
  else if (isdigit(last)) {
    while (isdigit(last)) {
      *v++ = last;
      last = fgetc(input);
    }
    *v = '\0';
    LA = T_NUMBER;
  }
  else {
    LA = last;
    last = fgetc(input);
  }
  
  if (DEBUG) fprintf(stderr,"Reading %d ...\n",LA);
}
