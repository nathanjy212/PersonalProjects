all: run

build: search.c search.h mt_search.c
	gcc -pthread -lm search.c mt_search.c -o mt_search

run: mt_search
	./mt_search numbers.txt 80 3 960

clean_build:
	rm mt_search