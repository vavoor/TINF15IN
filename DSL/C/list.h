#ifndef LIST_H
#define LIST_H

typedef struct ListR * List;

List ListNew(void);
void ListAppend(List l, void* e);
int ListCount(List l);
void* ListAt(List l, int i);

#endif        //  #ifndef LIST_H
