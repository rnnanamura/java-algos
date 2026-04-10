package com.seir.algos.structure.tree;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {

    public record Point(double x, double y) {}

    public static class Rectangle {
        Point basePoint;
        double width;
        double height;
        Rectangle(Point basePoint, double width, double height) {
            this.basePoint = basePoint;
            this.width = width;
            this.height = height;
        }
        public boolean contains(Point point) {
            return point.x >= this.basePoint.x - this.width &&
                    point.x < this.basePoint.x + this.width &&
                    point.y >= this.basePoint.y - this.height &&
                    point.y < this.basePoint.y + this.height;
        }
        public boolean intersects(Rectangle range) {
            return !(range.basePoint.x - range.width > this.basePoint.x + this.width ||
                    range.basePoint.y - range.height > this.basePoint.y + this.height ||
                    range.basePoint.x + range.width < this.basePoint.x - this.width ||
                    range.basePoint.y + range.height < this.basePoint.y - this.height);
        }
    }

    @Getter
    private Rectangle boundary;
    @Getter
    private int capacity;
    @Getter
    private List<Point> points;
    @Getter
    private boolean divided;
    private QuadTree northEast;
    private QuadTree northWest;
    private QuadTree southEast;
    private QuadTree southWest;

    public QuadTree(Rectangle boundary, int capacity) {
        this.boundary = boundary;
        this.capacity = capacity;
        this.points = new ArrayList<>();
        this.divided = false;
    }

    public void subDivide() {
        double width = this.boundary.width / 2;
        double height = this.boundary.height / 2;

        northEast = new QuadTree(new Rectangle(new Point(this.boundary.basePoint.x + width, this.boundary.basePoint.y + height), width, height), capacity);
        northWest = new QuadTree(new Rectangle(new Point(this.boundary.basePoint.x - width, this.boundary.basePoint.y + height), width, height), capacity);
        southEast = new QuadTree(new Rectangle(new Point(this.boundary.basePoint.x + width, this.boundary.basePoint.y - height), width, height), capacity);
        southWest = new QuadTree(new Rectangle(new Point(this.boundary.basePoint.x - width, this.boundary.basePoint.y - height), width, height), capacity);
        this.divided = true;

    }

    public boolean insert(Point point) {
        if (!this.boundary.contains(point)) {
            return false;
        }
        if (this.points.size() < this.capacity) {
            this.points.add(point);
            return true;
        }
        if (!this.divided) {
            this.subDivide();
        }
        return northEast.insert(point) || northWest.insert(point) || southEast.insert(point) || southWest.insert(point);
    }

    public void query(Rectangle range, List<Point> result) {
        if (!this.boundary.intersects(range)) {
            return;
        }
        for (Point point: this.points) {
            if (range.contains(point)) {
                result.add(point);
            }
        }
        if (this.divided) {
            northEast.query(range, result);
            northWest.query(range, result);
            southEast.query(range, result);
            southWest.query(range, result);
        }
    }


}
