package com.pessimistic.aoc2024.days.day14;

import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.twoDimensional.Range2D;
import com.pessimistic.aoc2024.util.TextUtils;

public class Robot {
    private Point position;
    private final Point velocity;

    public Robot(Point position, Point velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    //p=0,4 v=3,-3
    public static Robot fromString(String line) {
        var matches = TextUtils.allMatches(line, "p=(?<px>-?\\d+),(?<py>-?\\d+) v=(?<vx>-?\\d+),(?<vy>-?\\d+)")
                    .findFirst().orElseThrow();
        var px = Integer.parseInt(matches.get("px"));
        var py = Integer.parseInt(matches.get("py"));
        var vx = Integer.parseInt(matches.get("vx"));
        var vy = Integer.parseInt(matches.get("vy"));
        return new Robot(
                Point.of(px,py),
                Point.of(vx,vy)
        );
    }

    public void move(Range2D range) {
        var newPosition = position.add(velocity);
        position = newPosition.wrap(range);
    }

    public Point getPosition() {
        return position;
    }

}
