#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "err.h"
#include "scanner.h"
#include "parser.h"

static int DEBUG=1;

static Stmt parse_Stmt(void);
static Term parse_Term(void);

static void expect(int token)
{
  if (LA!=token) {
    err("Token %d expected",token);
  }
}

static int isInFirstStmt(int token)
{
  return token==T_NAME || token==T_DEF;
}

static int isInFirstParameters(int token)
{
  return token==T_NAME;
}

static int isInFirstTerm(int token)
{
  return token==T_NAME || token==T_NUMBER || token=='-' || token=='(';
}

static Term parse_Primary(void)
{
  Term t = malloc(sizeof(struct TermR));
  assert(t!=NULL);

  if (DEBUG) fprintf(stderr,"In parse_Primary\n");

  switch (LA) {
  case T_NAME:
    t->type = ID;
    t->u.id = strdup(Value);
    Next();
    break;

  case T_NUMBER:
    t->type =LITERAL;
    t->u.literal = atoi(Value),
    Next();
    break;

  case '-':
    t->type = NEG;
    Next();
    t->u.unary = parse_Primary();
    break;

  case '(':
    Next();
    free(t);
    t = parse_Term();
    expect(')');
    Next();
    break;

  default:
    err("Unexpected token %d",LA);
  }

  return t;
}

static Term parse_Term(void)
{
  Term t;
  if (DEBUG) fprintf(stderr,"In parse_Term\n");

  t = parse_Primary();

  while (LA=='+'||LA=='-'|LA=='*'||LA=='/') {
    Term t2 = malloc(sizeof(struct TermR));
    assert(t2!=NULL);
    switch (LA) {
    case '+':
      t2->type = PLUS;
      break;
    case '-':
      t2->type = MINUS;
      break;
    case '*':
      t2->type = TIMES;
      break;
    case '/':
      t2->type = DIVIDE;
      break;
    }
    Next();
    t2->u.binary.left = t;
    t = t2;
    t->u.binary.right = parse_Primary();
  }

  return t;
}

static List parse_Parameters(void)
{
  List l = ListNew();

  if (DEBUG) fprintf(stderr,"In parse_Parameters\n");

  expect(T_NAME);
  ListAppend(l,strdup(Value));
  Next();

  while (LA==',') {
    Next();

    expect(T_NAME);
    ListAppend(l,strdup(Value));
    Next();
  }

  return l;
}

static List parse_ParameterList(void)
{
  List l;

  if (DEBUG) fprintf(stderr,"In parse_ParameterList\n");

  expect('(');
  Next();

  if (isInFirstParameters(LA)) {
    l = parse_Parameters();
  }
  else {
    l = ListNew();
  }

  expect(')');
  Next();

  return l;
}

static Stmt parse_Definition(void)
{
  Stmt def = malloc(sizeof(struct StmtR));
  assert(def!=NULL);
  def->type = DEFINITION;

  if (DEBUG) fprintf(stderr,"In parse_Definition\n");

  expect(T_DEF);
  Next();

  expect(T_NAME);
  def->name = strdup(Value);
  Next();

  if (LA=='(') {
    def->u.def.parameters = parse_ParameterList();
  }
  else {
    def->u.def.parameters = ListNew();
  }

  def->u.def.stmts = ListNew();
  while (isInFirstStmt(LA)) {
    Stmt s = parse_Stmt();
    ListAppend(def->u.def.stmts,s);
  }

  expect(T_END);
  Next();

  return def;
}

static Stmt parse_Command(void)
{
  Stmt s = malloc(sizeof(struct StmtR));
  assert(s!=NULL);

  s->type = COMMAND;
  s->u.cmd.terms = ListNew();

  if (DEBUG) fprintf(stderr,"In parse_Command\n");

  expect(T_NAME);
  s->name = strdup(Value);
  Next();

  while (isInFirstTerm(LA)) {
    Term t = parse_Term();
    ListAppend(s->u.cmd.terms,t);
  }

  expect(';');
  Next();

  return s;
}

static Stmt parse_Stmt(void)
{
  Stmt s;
  if (DEBUG) fprintf(stderr,"In parse_Stmt\n");

  if (LA==T_DEF) {
    s = parse_Definition();
  }
  else {
    s = parse_Command();
  }

  return s;
}

static Shape parse_Shape(void)
{
  Shape s = malloc(sizeof(struct ShapeR));
  assert(s!=NULL);

  if (DEBUG) fprintf(stderr,"In parse_Shape\n");

  expect(T_SHAPE);
  Next();

  expect(T_NAME);
  s->name = strdup(Value);
  Next();

  expect(T_SIZE);
  Next();

  expect(T_NUMBER);
  s->width = atoi(Value);
  Next();

  expect(T_NUMBER);
  s->height = atoi(Value);
  Next();

  expect(';');
  Next();

  s->stmts = ListNew();
  while (isInFirstStmt(LA)) {
    Stmt st = parse_Stmt();
    ListAppend(s->stmts,st);
  }

  expect(T_END);
  Next();

  return s;
}

Shape Parse(const char *fname)
{
  Shape s;

  InitScanner(fname);
  s = parse_Shape();
  expect(EOF);

  return s;
}
