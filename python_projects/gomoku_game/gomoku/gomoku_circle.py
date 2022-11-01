# constants
CIRCLE_OUTLINE = 4


class GomokuCircle(object):
    """
    A single stone/disk for the Gomoku game
    """

    def __init__(self, x, y, circle_color):
        """
        Initialize circle
        Number Number Color --> GomokuCircle
        """
        # the grid coords where to put the pieces
        self._x = x
        self._y = y
        # Black or White
        self._circle_color = circle_color

        # initialize the sizes
        self.ELLIPSE_SIZE = 35
        self.OFFSET = 25
        self.SQUARE_SIZE = 50

    def display(self):
        """
        Actually draws the black or white
        playing pieces.
        """
        strokeWeight(CIRCLE_OUTLINE)
        fill(self._circle_color)
        ellipse(self._x * self.SQUARE_SIZE + self.OFFSET,
                self._y * self.SQUARE_SIZE + self.OFFSET,
                self.ELLIPSE_SIZE,
                self.ELLIPSE_SIZE)

    # helps in testing later to make sure it is drawn in
    # the correct place
    @property
    def x(self):
        return self._x

    @property
    def y(self):
        return self._y
