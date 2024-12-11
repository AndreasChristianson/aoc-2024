package com.pessimistic.aoc2024.days.day10;

import com.pessimistic.aoc2024.twoDimensional.Direction;
import com.pessimistic.aoc2024.twoDimensional.Grid;
import com.pessimistic.aoc2024.twoDimensional.Point;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Trailhead {
    private static final LavaHeight MAX_HEIGHT = new LavaHeight(9);
    private final Grid<LavaHeight, String> grid;
    private final Point origin;

    public Trailhead(Grid<LavaHeight, String> grid, Point origin) {
        this.grid = grid;
        this.origin = origin;
    }

    public Collection<Trail> findTrails() {
        return new Trail(List.of(origin)).findTrails();
    }

    public class Trail {
        private final List<Point> stops;

        public Trail(List<Point> stops) {
            this.stops = stops;
        }

        public Trail addStop(Point newPosition) {
            var ret = new ArrayList<>(stops);
            ret.add(newPosition);
            return new Trail(ret);
        }

        public boolean complete() {
            return maxHeight().equals(MAX_HEIGHT);
        }

        public LavaHeight maxHeight() {
            return grid.get(getLastStop()).orElseThrow();
        }

        public Point getLastStop() {
            return stops.getLast();
        }

        private Collection<Trail> findTrails() {
            var lastPoint = stops.getLast();
            var lastHeight = maxHeight();
            var ret = new ArrayList<Trail>();
            ret.add(this);
            for (var dir : Direction.cardinalDirections()) {
                var newPosition = lastPoint.add(dir.getDelta());
                var potentialStop = grid.get(newPosition)
                        .orElse(LavaHeight.none());
                if (potentialStop.equals(lastHeight.next())) {
                    ret.addAll(addStop(newPosition).findTrails());
                }
            }
            return ret;
        }
    }
}
