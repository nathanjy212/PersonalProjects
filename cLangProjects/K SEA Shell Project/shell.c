#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <errno.h>
#include <signal.h>
#define MAXARGS 5
#define MAXLINE 80
#define SINGLE_DIGIT 10
#define NON_POSITIVE 0
#define ALL_COMMANDS 4
#define ERROR 0
#define FAIL -1
#define RUNNING 1

// This is the command_cd function when called will
// help the user change directory.
// INPUT:
// The arguements that follow after "cd", as a char*
// RETURN:
// returns an integer 0 for success and 1 for failure.
int command_cd(char *cmdline) {
  int res = chdir(cmdline);
  if (res == FAIL) {
    printf("You did not enter a correct cd command\n");
    printf("Please enter a correct file path/directory.\n");
  }
  return 0;
}

// This is the command_help function when called will
// explain to the user the build-in shell functions
// and other important information on how to use the shell
// INPUT:
// None
// RETURN:
// returns an integer 0 for success and 1 for failure.
int command_help() {
  printf("Hello there! Thanks for taking the risk to use my ");
  printf("self-build shell\n");
  printf("Treat this like how you would to a ");
  printf("normal command line.\n");
  printf("The following are the commands I have built:\n");
  printf("1) cd\n");
  printf("2) help\n");
  printf("3) exit\n");
  printf("4) game\n");
  printf("You can always man command for more info\n");
  return 0;
};

// This is the command_exit function when called will
// help the user exit the shell
// INPUT:
// None
// RETURN:
// returns an integer 0 for success and 1 for failure.
int command_exit() {
  exit(0);
  return 0;
};

// This is the command_game function when called will
// let the user play a guessing game!
// INPUT:
// None
// RETURN:
// returns an integer 0 for success and 1 for failure.
int command_game() {
  printf("Congrats! You found the secret guessing game!\n");
  printf("Feel free to play as much as you like!\n");
  printf("\n");
  int number;
  int guess;

  // generates a random seed number
  srand(time(NULL));

  // generates a random number
  number = rand() % SINGLE_DIGIT;
  while (number == NON_POSITIVE) {
    number = rand() % SINGLE_DIGIT;
  }

  printf("Guess a number between 1 and 9 inclusive.\n");

  while (guess != number) {
    // Input by user
    scanf("%d", &guess);
    if (guess > number) {
        printf("The number is lower!\n");
    } else if (number > guess) {
        printf("The number is higher!\n");
    }
  }
  printf("You got it right! Thanks for playing!\n");
  return 0;
};

// Put your command names here
char* commands[] = {"cd", "help", "exit", "game"};

// Put your command function pointers here
void* command_function[ALL_COMMANDS] = {(void*)command_cd, (void*)command_help,
  (void*)command_exit, (void*)command_game};

/***
 **  Wrapper of fork(). Checks for fork errors, quits on error. 
 **/
// INPUT:
// None
// RETURNS:
// the process ID
pid_t Fork(void) {
  pid_t pid;
  if ((pid = fork()) < ERROR) {
    fprintf(stderr, "%s: %s\n", "fork error", strerror(errno));
    exit(0);
  }
  return pid;
}

/***
 **  Wrapper of fgets. Checks and quits on Unix error. 
 **/
// INPUT:
// 1) A pointer to contain the string input
// 2) The buffer size
// 3) The input from the user
// RETURN:
// the pointer containing the string
char* Fgets(char *ptr, int n, FILE *stream) {
  char *rptr;
  if (((rptr = fgets(ptr, n, stream)) == NULL) && ferror(stream)) {
    fprintf(stderr, "%s\n", "fgets error");
    exit(0);
  }
  return ptr;
}

// Is the command one built into the shell?
// That is, that the *shell* has implemented?
// If so, execute it here
int builtin_command(char** argv) {
  if (argv[0] == NULL) {
    // this means that no commands were given
    // so it's a fail
    return 1;
  }

  int i;
  // Loop through as hinted from starter code
  for (i = 0; i < 4; i++) {
    int res = strcmp(commands[i], argv[0]);
    // if it is a success
    if (res == 0) {
      command_function[i];
      printf("recognized\n");
      return 1;
    }
  }

  // if the command is not built-in
  // or if program is trying to exit
  // from the inner loop
  printf("unrecognized\n");
  return 0;
}

// the function evaluates whether the given args are built in
// or they are not. It will execute accordingly
// INPUT:
// the commands from user, as a char*
// RETURN
// None
void eval(char *cmdline) {
  char *argv[MAXARGS]; /* Argument list execve() */
  char buffer[MAXLINE]; /* Holds modified command line */
  pid_t pid; /* Process id */

  // Please note even though clint.py recommends sprintf
  // starter code used strcpy, so i left it as is
  strcpy(buffer, cmdline);

  // this process below is splitting the lines
  const char* delim = " ";
  char* rest = buffer;
  char* token = strtok_r(buffer, delim, &rest);
  int k;
  k = 0;
  while (token != NULL) {
    argv[k] = token;
    k++;
    token = strtok_r(NULL, delim, &rest);
  }

  // if empty, ignore the lines
  if (argv[0] == NULL)
    return;

  if (!builtin_command(argv)) {
    // Check that the command/program exists in Unix (ie /bin/) OR (see below)
    // Create a child process
    // Run the program/command (execve...)-- what is the result of exec
    // if the command is bogus?
    pid = Fork();
    int status;
    // this is successful
    if (pid == 0) {
      int res;
      // this is the child process
      res = execvp(argv[0], argv);
      // res = execve(argv[0], argv, NULL);
      if (res == FAIL) {
        printf("Hello There! You did not enter a valid command\n");
        printf("Please try again. Try the \"help\" command to learn more!\n");
        printf("OR\n");
        printf("You might have just ended from playing the guessing game\n");
        printf("if such is so, please don't worry, this error is showing\n");
        printf("because the program is trying to exit from the inner\n");
        printf("loop. Please continue using as per normal.\n");
        // this exit needs to be here so that even when the child
        // fails, the program can continue running
        exit(0);
      } else {
          // this is where the parent will land if everything is successful
          // in order to use waitpid(), we need to use the macros within
          // <sys/wait.h>
          while (!WIFEXITED(status) && !WIFSIGNALED(status)) {
            waitpid(pid, &status, WUNTRACED);
          }
      }
    }
  } else {
    // What do we do if it *IS* a command built into the shell?
    if (strcmp("help", argv[0]) == 0) {
      command_help();
      return;
    } else if (strcmp("game", argv[0]) == 0) {
      command_game();
      return;
    } else if (strcmp("exit", argv[0]) == 0) {
      command_exit();
      exit(0);
      return;
    } else if (strcmp("cd", argv[0]) == 0) {
      if (argv[1] == NULL) {
        printf("To use \"cd\", you need to specify a location.\n");
        printf("such as \"..\" or a file path.\n");
        return;
      } else {
        command_cd(argv[1]);
      }
    }
      return;
    }
    // Be sure to wait for the child process to terminate
    wait(NULL);
    return;
  }

// Main function that brings all other functions and executes
// together to make the shell program
// INPUT: None
// RETURNS: 0 for success and 1 for failure
int main() {
  // add the buffer here
  char cmdline[MAXLINE];
  char* realCommand;
  while (RUNNING) {
    // Print command prompt
    printf("k-sea-shell> ");
    // Read input from user
    realCommand = Fgets(cmdline, MAXLINE, stdin);
    realCommand[strcspn(realCommand, "\n")] = 0;
    // If we get the eof, quit the program/shell
    if (feof(stdin))
      exit(0);

    // Otherwise, evaluate the input and execute.
    eval(realCommand);
  }
}
