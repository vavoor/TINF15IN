#! /usr/bin/sh
SRCS="main.c parser.c scanner.c err.c list.c"
/c/lcc/bin/lc -g2 $SRCS -o cutter.exe