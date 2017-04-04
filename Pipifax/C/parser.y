%{
#include <stdio.h>

#undef yywrap
#define yywrap() 1
%}

%union {
  int i;
}

%token T_FUNC

%start program

%%

program
    : T_FUNC
    | /* empty */
    ;

%%

int yyerror(const char* msg)
{
  fprintf(stderr,"Error: %s\n",msg);
}
