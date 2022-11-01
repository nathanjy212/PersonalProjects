from gomoku_circle import GomokuCircle

EMPTY = 0
BLACK = 0
WHITE = 225
HEIGHT = 1
WIDTH = 0
CHECKER = 2
ODD = 1
A_TURN = 1
CIRCLE_OUTLINE = 4
BOARD_LINE = 8


class GameController(object):
    """
    GameController class
    The GameController class is responsible for starting,
    ending and keeping track of the progress of the game.
    """

    def __init__(self, field):
        """
        Initialize game controller
        (Number, Number) -> GameController

        Please note I have initialized these values for
        the purposes of part 1 of the assignment.
        """
        # size of each square on the grid
        self.SQUARE_SIZE = 50
        self.OFFSET = 25

        # 300px is the width in pixels in this case
        self._pix_w = field[WIDTH]
        # 300px is the height in pixels in this case
        self._pix_h = field[HEIGHT]

        # 3 squares wide in this case
        self._w = self._pix_w // self.SQUARE_SIZE
        # 3 squares tall in this case
        self._h = self._pix_h // self.SQUARE_SIZE

        # 3x3 = 9 in this case
        self._remaining_spots = self._w * self._h

        # we first set the playing to be true
        self._playing = True

        # this creates the nested list for the game
        self._grid = [[EMPTY for i in range(self._w)] for i in range(self._h)]
        self._turn = A_TURN

    # Public methods
    def update(self):
        """
        Update all game elements
        None --> None
        """
        my = mouseY // self.SQUARE_SIZE
        mx = mouseX // self.SQUARE_SIZE

        # only enter this if/else if the click is on a 0 in the grid
        # if not, it becomes unclickable because a player has alraedy
        # made a move in that position.
        if self._grid[my][mx] == EMPTY:
            self._grid[my][mx] = self._turn
            self._turn += A_TURN  # increment turn
            self._remaining_spots += -A_TURN  # decrement remaining spots

        # checks if all spots are already played
        if self._remaining_spots == EMPTY:
            self.end_game(event='Full Board')

    def draw_interactive_list_gameboard(self):
        """
        This method constantly checks the value in each
        grid or the numbers in the nested list. It updates
        the board to draw the correct black or white circle
        depending on the value in that grid position.
        """
        # to loop through each row
        if self._turn == A_TURN:
            return

        for y in range(self._w):
            # to loop through each col
            for x in range(self._h):
                # if zero value in the grid, pass for now
                if self._grid[y][x] == EMPTY:
                    pass
                # if the value in the grid (turn) is odd, then black circle
                elif self._grid[y][x] % CHECKER == ODD:
                    stone = GomokuCircle(x, y, BLACK)
                    stone.display()
                # else white circle
                else:
                    stone = GomokuCircle(x, y, WHITE)
                    stone.display()

    def draw_background_gameboard(self):
        """
        This method draws the balck lines you see
        on the game board.
        """
        strokeWeight(BOARD_LINE)
        # draws self._w times of rows, 3 in this case
        for row in range(self._w):
            line(self.OFFSET,
                 row * self.SQUARE_SIZE + self.OFFSET,
                 self._pix_h - self.OFFSET,
                 row * self.SQUARE_SIZE + self.OFFSET)
        # draws self._h times of cols, 3 in this case
        for col in range(self._h):
            line(col * self.SQUARE_SIZE + self.OFFSET,
                 self.OFFSET,
                 col * self.SQUARE_SIZE + self.OFFSET,
                 self._pix_w - self.OFFSET)

    def end_game(self, winner=None, event=None):
        """
        Handle end of game conditions
        Player? String? --> None
        For part 1 it can only handle a draw
        which will happen when all playable positions
        have been filled
        """
        self._end_event = event
        self._playing = False

    def end_text(self):
        """
        Display game over text
        None --> None
        """
        WHITE = 255
        FONT_SIZE = self.OFFSET
        VERT_MID = self._pix_h/2
        HORIZ_MID = self._pix_w/2

        fill(WHITE)
        textSize(FONT_SIZE)
        textAlign(CENTER)

        if self._end_event == 'Full Board':
            text("GAME OVER!", HORIZ_MID, VERT_MID)

    @property
    def playing(self):
        """
        Getter for playing value
        None --> Boolean
        """
        return self._playing

    @property
    def h(self):
        """
        Getter for h value
        None --> Number
        """
        return self._h

    @property
    def w(self):
        """
        Getter for w value
        None --> Number
        """
        return self._w
