from game_controller import GameController
from gui_controls import GUI_Controls

'''
Nathan Yap
CS 5001, Spring 2022
lab 10 - Snake Race
About: This program allows you to play the Snake Game! Use your keyboard
"up", "down", "left", "right" to play control the Human (Blue Snake) to
defeat the AI(Red Snake). In this game, everytime you eat an apple your
snake grows by 3 squares and the opponent will lose 1 sqaure. To win, you
either the opponent crashes into you or itself or you starve it to death by
eating all the apples and causing it to decrease until it has no snake squaresoo
left.
'''

PIXEL_WIDTH = 600
PIXEL_HEIGHT = 600
BG_COLOR = (0.0, 0.6, 0.4)

field = (PIXEL_WIDTH, PIXEL_HEIGHT)
gui_control = GUI_Controls()
gc = GameController(field, gui_control)


def setup():
    """
    Set up the environment
    """
    size(*field)
    colorMode(RGB, 1)


def draw():
    """
    Update the environment
    """
    if gc.playing:
        background(*BG_COLOR)
        gc.update()
    else:
        gc.end_text()
    gc.allowKeyPress = True


def keyPressed():
    if key == CODED and gc.allowKeyPress:
        # Ensure that only one key press
        # event can happen during a single
        # draw cycle
        gc.allowKeyPress = False
        gui_control.key_pressed(keyCode)
