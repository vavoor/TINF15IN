#ifndef PARSER_H
#define PARSER_H

#include "list.h"

typedef struct ShapeR {
	const char* name;
	int width;
	int height;
	List stmts;
} *Shape;

typedef struct StmtR {
	enum {COMMAND, DEFINITION} type;
	const char* name;
	union {
		struct {
			List terms;
		} cmd;
		struct {
			List parameters;
			List stmts;
		} def;
	} u;
} *Stmt;

typedef struct TermR {
	enum {LITERAL, ID, NEG, PLUS, MINUS, TIMES, DIVIDE} type;
	union {
		const char* id;
		int literal;
		struct TermR* unary;
		struct {
			struct TermR* left;
			struct TermR* right;
		} binary;
	} u;
} *Term;

Shape Parse(const char *fname);

#endif        //  #ifndef PARSER_H
