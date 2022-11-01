from game_controller import GameController

'''
Nathan Yap
CS 5001, Spring 2022
hw11 - Gomoku part 1
About: This program allows you to try the mechanics of Gomoku game! You won't
be able to declare a winner or loser but this shows you how the Gomoku game
would result in a draw. For now, the game only has a 3*3 board and you can
test how you can put a black and then white stone. It alternates and once
all 9 positions are filled, the game ends because it would be a draw in a
normal case. I have made this game scalable as we continue working on
part 2, please see the first constant GAMEBOARD_H_W. Right now, it's
3 becaue is a 3*3 board but you can change it to 15 to see the full
game board.
'''

# Please change this value for size of gameboard
# height and gameboard width.
# For e.g. for part 1 we want a 3X3 gameboard,
# so input 3.
# actual size is 15 for full gameboard.
GAMEBOARD_H_W = 3

# Other Constants
GAME_SQUARE_SIZE = 50
PIXEL_WIDTH = GAME_SQUARE_SIZE * GAMEBOARD_H_W
PIXEL_HEIGHT = GAME_SQUARE_SIZE * GAMEBOARD_H_W
BG_COLOR = (186, 140, 99)

field = (PIXEL_WIDTH, PIXEL_HEIGHT)

gc = GameController(field)


def setup():
    """
    Set up the environment
    """
    # This creates the screen size of the game
    size(*field)


def draw():
    """
    Update the environment
    """
    # Creates the wood background color
    background(*BG_COLOR)
    # This draws the black lines
    gc.draw_background_gameboard()
    # This cuts the board into a grid like system
    # so that it helps with detection of psotions
    # and placing the stones
    gc.draw_interactive_list_gameboard()  # draw based on values of grid

    # once a condition is met where the game is over
    # it prints "GAME OVER" in white
    if not gc.playing:
        gc.end_text()


def mousePressed():
    gc.update()  # update values in the grid
