class Circle(
    center: Point,
    radiusX: Double,
    radiusY: Double
) : CurvedShape(center, radiusX, radiusY) {

    init {
        require(radiusX == radiusY) { "Circle must have equal X and Y radii" }
        require(radiusX > 0 && radiusY > 0) { "Circle radii must be positive" }
    }
}