class Circle(
    center: Point,
    radiusX: Double,
    radiusY: Double
) : CurvedShape(center, radiusX, radiusY) {

    init {
        require(radiusX == radiusY) { "Circle must have equal X and Y radii" }
    }

    override fun getArea(): Double {
        return Math.PI * getRadiusX() * getRadiusY()
    }
}