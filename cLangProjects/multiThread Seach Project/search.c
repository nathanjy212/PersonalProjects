#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <math.h>

#include "search.h"

#define MAX_SIZE 999
#define BUFFER 50
#define INITIALIZE 0
#define ADJUST 1

// This function reads the file that we want as the input
// then converts it into an array so that the threads
// can analyze
//
// INPUT:
// NA - takes in a global variable
// RETURNS:
// NA - updates the global variable
void makeArray() {
    char* token;
    char buffer[MAX_SIZE];
    FILE* file;
    char line[BUFFER];
    file = fopen(fileName, "r");

    if (NULL == file) {
        printf("file can't be opened \n");
    }

    int k;
    k = INITIALIZE;
    while (fscanf(file, "%[^\n] ", line) != EOF) {
        t[k] = atoi(line);
        k++;
    }
}

// This function prints the combined array that
// is shared among the threads
//
// INPUT:
// NA
// RETURNS:
// NA
void printArray() {
    int i;
    printf("\nYou can find integer *%d*, in these locations in the file:\n",
    numberSearch);
    for (i = INITIALIZE; i < count; i++) {
        printf("line %d\n", found[i]);
    }
}

// This function is basically the function that represents what
// you'd want each thread to perform. In this function, we want
// the threads to search their assigned portion of the file
// and then update the shared array on their findings
//
// INPUT:
// args - which is the parameters that we would enter for each
// thread, as a void*
// RETURNS:
// a void*
void* workerThreadFunc(void* args) {
  sem_wait(&sem);
  printf("\nHi, I am thread");
  printf(" *%d* I will search from start: %d, end: %d\n",
  ((ThreadArgs*)args)->id,
  ((ThreadArgs*)args)->startingInclusive,
  ((ThreadArgs*)args)->endExclusive);

  ThreadArgs* arg = (ThreadArgs*)args;

  sleep(1);
  int k;
  int startingInclusive = arg->startingInclusive;
  int endExclusive = arg->endExclusive;
  for (k = startingInclusive; k < endExclusive; k++) {
    if (t[k] == numberSearch) {
        printf("found it! line %d\n", k + ADJUST);
        found[count] = k + 1;
        count++;
    }
  }
  sem_post(&sem);
};

// This function will populate the parameters into an args void*
// which we will then send over to the workerThreadFunc
// when we create the threads
//
// INPUT:
// NA
// RETURNS:
// args, as a ThreadArgs**
ThreadArgs** populateThreadArgs() {
    ThreadArgs** args = (ThreadArgs**)malloc(sizeof(ThreadArgs*) *
    numberThreads);
    int intervals;
    intervals = floor(numberLines / numberThreads);
    int i;
    for (i = INITIALIZE; i < numberThreads; i++) {
        ThreadArgs* sArgs = (ThreadArgs*)malloc(sizeof(ThreadArgs));
        sArgs->id = i + ADJUST;
        sArgs->startingInclusive = (intervals * (i)) + ADJUST;
        sArgs->endExclusive = (i) + intervals * (i + ADJUST) - (i);
        args[i] = sArgs;
    }
    return args;
}

// This function will create and populate the parameters in
// the individual threads
//
// INPUT:
// threadIds - this is basically inputing the memory allocated
// for each threads, as a pthread_t*
// args - this are the commands for each parameter we will add
// to each arg, as a ThreadArgs**
// RETURNS:
// results, as an int*
int* createThreads(pthread_t* threadsIds, ThreadArgs** args) {
    int* results = (int*)malloc(sizeof(int) * numberThreads);
    int i;
    for (i = INITIALIZE; i < numberThreads; i++) {
        results[i] = pthread_create(&(threadIds[i]),
        NULL, workerThreadFunc, (void*)(args[i]));
    }
    return results;
}

// This function will rejoin the threads together
//
// INPUT:
// threadIds - this is basically inputing the memory allocated
// for each threads, as a pthread_t*
// RETURNS:
// results as an int*
int* joinThreads(pthread_t* threadIds) {
    int* results = (int*)malloc(sizeof(int) * numberThreads);
    int i;
    for (i = INITIALIZE; i < numberThreads; i++) {
        results[i] = pthread_join(threadIds[i], NULL);
    }

    return results;
}

// This function will release all the memory we used
// throughout the program
//
// INPUT:
// args - this is the memory we allocated to contain the args
// for each thread, as a ThreadArgs**
// create - this is the memory allocated when we created each
// thread, as an int*
// join - this is the memory allocated when we join the pthreads,
// as an int*
// RETURNS:
// NA
void teardown(ThreadArgs** args, int* create, int* join) {
    free(threadIds);
    int f;
    for (f = INITIALIZE; f < numberThreads; f++) {
        free(args[f]);
    }
    free(args);
    free(create);
    free(join);
    sem_destroy(&sem);
}
