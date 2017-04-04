#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>

void err(const char* msg, ...)
{
	va_list ap;
	va_start(ap,msg);
	assert(msg!=NULL);
	fprintf(stderr,"Error: ");
	vfprintf(stderr,msg,ap);
	fprintf(stderr,".\n");
	va_end(ap);
	exit(1);
}