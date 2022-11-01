#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include <math.h>

#ifndef SEARCH_H
#define SEARCH_H

#define DEFAULT_SIZE 999

// global variables for file reading
sem_t sem;
int t[DEFAULT_SIZE];
char* fileName;
int numberSearch;
int numberThreads;
int numberLines;

// global variables for the shared array
int found[DEFAULT_SIZE];
int count;

// This is to declare the thread's we'll use
pthread_t* threadIds;

// This is the struct Threadargs that represents the inputs for a thread
// It holds 3 fields:
// 1) startingInclusive - this is the line a thread will start searching
// in, as an integer
// 2) endExclusive - this is the line a thread will stop searching in
// as an integer
// 3) id - this is to identify each thread as it is created
typedef struct ThreadArgs {
  int startingInclusive;
  int endExclusive;
  int id;
} ThreadArgs;

// This function reads the file that we want as the input
// then converts it into an array so that the threads
// can analyze
//
// INPUT:
// NA - takes in a global variable
// RETURNS:
// NA - updates the global variable
void makeArray();

// This function prints the combined array that
// is shared among the threads
//
// INPUT:
// NA
// RETURNS:
// NA
void printArray();

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
void* workerThreadFunc(void* args);

// This function will populate the parameters into an args void*
// which we will then send over to the workerThreadFunc
// when we create the threads
//
// INPUT:
// NA
// RETURNS:
// args, as a ThreadArgs**
ThreadArgs** populateThreadArgs();

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
int* createThreads(pthread_t* threadsIds, ThreadArgs** args);

// This function will rejoin the threads together
//
// INPUT:
// threadIds - this is basically inputing the memory allocated
// for each threads, as a pthread_t*
// RETURNS:
// results as an int*
int* joinThreads(pthread_t* threadIds);

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
void teardown(ThreadArgs** args, int* create, int* join);

#endif  // SEARCH_H
