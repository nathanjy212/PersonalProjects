#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <math.h>

#include "search.h"

#define START 0
#define END 1


int main(int argc, char** argv) {
    // we begin by parsing the inputs from the command line
    // into appropriate variables
    fileName = argv[1];
    numberSearch = atoi(argv[2]);
    numberThreads = atoi(argv[3]);
    numberLines = atoi(argv[4]);

    // Let's print it so we can see we captured this correctly
    printf("\n");
    printf("filename is %s\n", fileName);
    printf("number to search for is %d\n", numberSearch);
    printf("number of threads is %d\n", numberThreads);
    printf("number of lines in the file is %d\n", numberLines);

    // initialize the semaphore
    sem_init(&sem, START, END);

    // initialize the args we want to populate later
    ThreadArgs** args = populateThreadArgs();

    // allocate memory for the pthreads
    threadIds = (pthread_t*)malloc(sizeof(pthread_t) * numberThreads);

    // let's create the threads and get them ready to run
    int* createResults = createThreads(threadIds, args);

    // this is where the file is read
    // i made sure all threads are spawned before this line
    // the threads will then work on the array
    makeArray();

    // after completing their tasks, let's join the threads
    // back together
    int* joinResults = joinThreads(threadIds);

    // let's print the array that the threads made
    printArray();

    // let's terminate the threads
    pthread_exit(NULL);

    // let's free all memory
    teardown(args, createResults, joinResults);

    // the program is done running
    return 0;
}
