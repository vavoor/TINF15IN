grammar Pipifax;

program
    : ( functionDefinition | variableDeclaration )*
    ;

functionDefinition
    : 'func' ID '(' parameterList? ')' type? block
    ;

parameterList
    : parameter ( ',' parameter )*
    ;

parameter
    : ID parameterType
    ;

parameterType
    : type #theType
    | '*' type #referenceType
    | '*' '[' ']' type #arrayReferenceType
    ;

variableDeclaration
    : 'var' ID type
    ;

type
    : 'int' #intType
    | 'double' #doubleType
    | ' string' #stringType
    | '[' INT_LITERAL ']' type #arrayType
    ;

block
    : '{' statement* '}'
    ;

statement
    : assignment
    | ifStatement
    | whileStatement
    | functionCallStatement
    ;

assignment
    : lvalue '=' expr
    ;

ifStatement
    : 'if' expr block ( 'else' block )?
    ;

whileStatement
    : 'while' expr block
    ;

functionCallStatement
    : functionCall
    ;

lvalue
    : ID #idLValue
    | lvalue '[' expr ']' #indexedLValue
    ;

functionCall
    : ID '(' exprList? ')'
    ;

exprList
    : expr ( ',' expr )*
    ;

expr
    : varValue #varExpr
    | STRING_LITERAL #stringLiteralExpr
    | INT_LITERAL #intLiteralExpr
    | DOUBLE_LITERAL #doubleLiteralExpr
    | '(' expr ')' #bracketExpr
    | ( '-' | '!' ) expr #unaryExpr
    | expr ( '*' | '/' | '%' ) expr #multExpr
    | expr ( '+' | '-' ) expr #addExpr
    | expr ( '<' | '<=' | '>' | '>=' ) expr #compExpr
    | expr ( '==' | '!=' | '<=>' ) expr #eqExpr
    | expr '&&' expr #andExpr
    | expr '||' expr #orExpr
    ;

varValue
    : ID #idValue
    | functionCall #functionCallValue
    | varValue '[' expr ']' #indexedValue
    ;

fragment
L
    : [A-Za-z_]
    ;

fragment
D
    : [0-9]
    ;

fragment
E
    : ('E'|'e') ('+'|'-')? D+
    ;

ID
    : L ( L| D )*
    ;

INT_LITERAL
    : D+
    ;

DOUBLE_LITERAL
    : D+ ( '.' D+ E? | E)
    ;

STRING_LITERAL
    : '"' ~'"'*  '"'
    ;

COMMENT
    : '#' ~[\r\n]* -> skip
    ;

WS
    : [ \t\f\r\n] -> skip
    ;
