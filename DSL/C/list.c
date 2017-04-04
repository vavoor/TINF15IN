#include <assert.h>
#include <stdlib.h>

#include "list.h"

struct ListR {
  int size;
  int count;
  void** elements;
};

List ListNew(void)
{
  List l;

  l = malloc(sizeof(struct ListR));
  assert(l!=NULL);

  l->size = 0;
  l->count = 0;
  l->elements = NULL;

  return l;
}

void ListAppend(List l, void* e)
{
  assert(l!=NULL);
  assert(e!=NULL);

  if (l->count>=l->size) {
    l->size += 100;
    l->elements = realloc(l->elements,l->size*sizeof(void*));
    assert(l->elements!=NULL);
  }
  l->elements[l->count] = e;
  l->count++;
}

int ListCount(List l)
{
  assert(l!=NULL);
  return l->count;
}

void* ListAt(List l, int i)
{
  assert(l!=NULL);

  if (0<=i && i<l->count) {
    return l->elements[i];
  }
  else {
    return NULL;
  }
}
