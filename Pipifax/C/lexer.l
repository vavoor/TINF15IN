%{
#include <assert.h>
#include <stdio.h>
#include "lexer.h"
#include "parser.tab.h"
%}

L	[A-Za-z_]
D0	[0-9]
D1	[1-9]
SP	[ \t\n]

%option yylineno noyywrap

%%

"func"	{ return T_FUNC; }

{SP}	/* ignore */
.       { return yytext[0]; }

%%

void lexer_init(const char* fname)
{
  yyin = fopen(fname,"r");
  assert(yyin!=NULL);
}
