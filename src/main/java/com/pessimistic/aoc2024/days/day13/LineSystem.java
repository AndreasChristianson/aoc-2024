package com.pessimistic.aoc2024.days.day13;

import com.pessimistic.aoc2024.numbers.Range;
import com.pessimistic.aoc2024.twoDimensional.Line;
import com.pessimistic.aoc2024.twoDimensional.Point;
import com.pessimistic.aoc2024.twoDimensional.Range2D;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Optional;
import java.util.stream.Stream;

// A =3, B =1

public record LineSystem(
        Point aOffset,
        Point bOffset,
        Point target
) {
    private static final Point origin = new Point(0, 0);

    public Line lineBFromOrigin() {
        return new Line(origin, bOffset);
    }

    public Line lineAFromOrigin() {
        return new Line(origin, aOffset);
    }

    private Line lineBFromTarget() {
        return new Line(target, target.subtract(bOffset));
    }

    public Stream<Pair<Long, Long>> findPresses() {
        var isOnLineA = lineAFromOrigin().hopsToIntersection(target);
        var isOnLineB = lineBFromOrigin().hopsToIntersection(target);
        if (isOnLineA.isPresent() || isOnLineB.isPresent()) { // direct intersection from one or the other button
            return Stream.of(
                    isOnLineA.map(hops -> Pair.of(hops, 0L)),
                    isOnLineB.map(hops -> Pair.of(0L, hops))
            ).flatMap(Optional::stream);
        }
        return lineAFromOrigin().intersect(lineBFromTarget())
                .filter(range()::contains)
                .map(point ->
                        Pair.of(
                                lineAFromOrigin().hopsToIntersection(point),
                                lineBFromTarget().hopsToIntersection(point)
                        ))
                .flatMap(pair ->
                        pair.getRight().isPresent() && pair.getLeft().isPresent()
                                ? Optional.of(Pair.of(pair.getLeft().get(), pair.getRight().get()))
                                : Optional.empty())
                .stream();
    }

    private Range2D range() {
        return new Range2D(
                new Range(origin.row(), target().row()),
                new Range(origin.col(), target().col())
        );
    }


}
