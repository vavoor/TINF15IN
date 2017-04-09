grammar Pipifax;

program
    : ID
    ;

ID
    : [A-Za-z_][A-Za-z0-9_]*
    ;

WS
    : [ \t\r\n] -> skip
    ;
