from snake_controller import SnakeController

# These constant assignments are redundant and
# already provide by Processing, however
# providing them here makes it possible to run
# tests in Pytest on functions using them.
UP, DOWN, LEFT, RIGHT = 38, 40, 37, 39


class AI(SnakeController):
    """
    AI Snake controller class
    """

    def __init__(self, gc):
        """
        Initialize AI
        GameController --> AI
        """
        self._gc = gc

    # Public methods
    def update(self):
        """
        Calculate next move and control the snake
        None --> None
        """
        dir = self._choose_dir()
        self.control_snake(dir)

    # Private methods
    def _choose_dir(self):
        """
        Method - _choose_dir
            Score directions and return top scored
            direction
            None --> Direction
        Parameters
            self - The self parameter is a reference to the current instance
            of the class. It is used to access variables/methods that belongs
            to the class.
        Return
            best_dir - it returns the safest most optimized direction it should
            travel to reach the apple
        """
        directions = [UP, DOWN, LEFT, RIGHT]

        # some constants
        MOST_SAFE = 0.75
        INITIALIZED = -1

        # we initialize this to help snake keep score
        # of where is safest
        potential_scores = []

        # we use a for loop to loop through all
        # directions, basically helping the snake
        # sense all 4 directions continuously
        for dir in directions:
            # in each loop, checks 1 location a.k.a direction
            location = self._get_neighbor_coord(dir)
            # counts the apple score from that location
            possible_apple_score = self._apple_distance(location)
            # counts the safety score from that location
            possible_safety_score = self._clear_score(location)

            # we know safety score CANNOT go above MOST_SAFE (0.75)
            # the best score is 0.75. See _clear_score method
            # we want the snake to ignore if it's 0.74 or lower
            if possible_safety_score < MOST_SAFE:
                pass
            else:
                potential_scores.append((possible_apple_score, dir))
        safest_score = INITIALIZED
        safest_dir = None
        # we use another for loop for snake to make final decision
        for score, dir in potential_scores:
            # it updates the best scores as it loops all the top potential
            # decisions in scores
            if score > safest_score:
                safest_score = score
                safest_dir = dir
        return safest_dir

    def _get_neighbor_coord(self, neighbor_dir):
        """
        Get neighbor coordinate for direction
        Direction --> (Number, Number)
        """
        x = self._snake.body[0].x
        y = self._snake.body[0].y
        if neighbor_dir == UP:
            return (x, y-1)
        elif neighbor_dir == DOWN:
            return (x, y+1)
        elif neighbor_dir == LEFT:
            return (x-1, y)
        elif neighbor_dir == RIGHT:
            return (x+1, y)

    def _min_dist(self, p1, p2):
        """
        Find the minimum distance between two points
        (Number, Number) (Number, Number) --> Number
        """
        # To find the shortest path between two points
        # We calculate the inner path (within the game frame)
        # and the outer path (going out of the bounds of the
        # frame and wrapping in the other side) and take the
        # minimum of the two.
        p1_x, p1_y = p1
        p2_x, p2_y = p2

        inner_x = p1_x - p2_x
        inner_y = p1_y - p2_y

        outer_x = self._gc.w-(max(p1_x, p2_x)) + min(p1_x, p2_x)
        outer_y = self._gc.h-(max(p1_y, p2_y)) + min(p1_y, p2_y)

        print("Outer x", outer_x)
        print("Outer y", outer_y)

        # Pythagorean theorem
        shortest = (min(inner_x, outer_x)**2+min(inner_y, outer_y)**2)**0.5
        return shortest

    def _clear_score(self, coord):
        """
        Generate a weighting score (normalized)
        from the number of unoccupied neighbor squares
        for a coordinate
        (Number, Number) --> Number
        """
        # normalized number of clear neighbor squares
        if coord in self._gc.deadly_points:
            return -1
        else:
            score = 0
            if (coord[0]-1, coord[1]) not in self._gc.deadly_points:
                score += 1
            if (coord[0], coord[1]-1) not in self._gc.deadly_points:
                score += 1
            if (coord[0], coord[1]+1) not in self._gc.deadly_points:
                score += 1
            if (coord[0]+1, coord[1]) not in self._gc.deadly_points:
                score += 1
        NEIGHBOR_COUNT = 4.0
        score = score/NEIGHBOR_COUNT
        return score

    def _apple_distance(self, coords):
        """
        Get the distance score to the apple
        (Number, Number) --> Number
        """
        # Get the distance
        apple_min_dist = self._min_dist(self._gc.apple_location, coords)
        # Return the distance score
        return self._convert_min_to_score(apple_min_dist)

    def _convert_min_to_score(self, min_dist):
        """
        Convert a distance to a score
        Number --> Number
        """
        return ((self._gc.h * self._gc.w) - min_dist)
