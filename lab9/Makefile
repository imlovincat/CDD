CFILES= game.c
HFILES=
CC=gcc
CFLAGS= -std=c++11  -fopenmp

%.o: %.c
	$(CC) -c -o $@ $< $(CFLAGS) $(DEBUGFLAGS)

GAME: game.o
	$(CC) -o game.out game.o $(CFLAGS) $(DEBUGFLAGS)


DSTENCIL: DEBUGFLAGS = -g -O0
DSTENCIL: GAME

CLEAN:
	rm *.o
