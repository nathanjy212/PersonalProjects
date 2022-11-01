# K-SEA Shell (ksesh)

1) Command Name I made
- Name: "game" - it's basically a guessing game. You can start it by typing the command "game" in my self-build terminal
- What the command does: The command brings the user to play a guessing game. The user gets to guess a number between 1-9 inclusive. The player wins and will exit the guessing game loop once they guessed the correct number.
- How I implemented the command. I used a very simple structure. Similar to the shell, I also used a while loop to generate the game. I used the rand() function to generate the random number and srand() to generate the seed number. I modded the number by 10 to make sure i only want single digits can be guessed. I also made a secondary while loop so that if 0 is a random number, the program will redo the while loop until the required guess number is a non-zero. I made conditionals to help guide the player to guess the correct numeber by telling the player whether to guess higher or lower.

2) How to run the program
- I made a makefile to make it simpler. Please use "make build" to compile the program and then please use "make run" to run my shell program.
- There are 4 build-in commands
    - cd -> Changes the directory that the user is in. Please use pwd after that to check where you are.
    - help -> informs the user of the build in commands i made, and how to use the program
    - exit -> exits the whole shell program
    - game -> plays a guessing game!
- There are only 9 functions in this program. I documented the functions within the shell.c file. Please refer therein for more details.
    - 1) int command_cd(char* cmdline);
    - 2) int command_help();
    - 3) int command_exit();
    - 4) int command_game();
    - 5) pid_t Fork(void);
    - 6) char* Fgets(cgar* ptr, int n, FILE* stream);
    - 7) int builtin_command(char** argv);
    - 8) void eval(char* cmdline);
    - 9) int main();
     

