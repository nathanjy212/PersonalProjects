# Multithreading Search

1) What each file means
- mt_search.c -> This is the file that contains the "Main" function. This is where the logic flow of how the program runs can be found.
- search.h -> This is the header file where I declare all variables and functions
- search.c -> This is where all the logic for each function is defined.
- Makefile -> I made a Makefile so that it will be easy to compile all the files since we need to use multiple flags like -pthread and -lm.
- numbers.txt -> this is an example file of integers that I used to run the program.

2) How to run the program
- In order to run the program please begin by typing "make build". This will create the file "mt_search" which you can run.
- Next, enter "make run" this will run the file with the following inputs "./mt_search <numbers.txt> <81> <3> <960>". Please feel free to adjust this or you can always just type "./mt-search <your choice of file> <your choice of number to search> <your choice of number of threads> <your choice of number of lines of file>"
- Using my default setting, the expected output will be 3 threads searching through the file and returning where it found the number "80" in. Please see the screenshot "expectedOutput.png" as an example






