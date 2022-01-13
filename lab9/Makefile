CFILES= conway.cpp
HFILES=
CC=g++
CFLAGS= -std=c++11  -fopenmp

%.o: %.cpp
	$(CC) -c -o $@ $< $(CFLAGS) $(DEBUGFLAGS)

CONWAY: conway.o
	$(CC) -o conway.out conway.o $(CFLAGS) $(DEBUGFLAGS)

ALTER: alter.o
	$(CC) -o alter.out alter.o $(CFLAGS) $(DEBUGFLAGS)

SIMPLE: simple.o
	$(CC) -o simple.out simple.o $(CFLAGS) $(DEBUGFLAGS)


DSTENCIL: DEBUGFLAGS = -g -O0
DSTENCIL: CONWAY
DSTENCIL: ALTER
DSTENCIL: SIMPLE

CLEAN:
	rm *.o
