class Ellipse(
    center: Point,
    radiusX: Double,
    radiusY: Double
) : CurvedShape(center, radiusX, radiusY) {

    init {
        require(radiusX > 0 && radiusY > 0) { "Ellipse radii must be positive" }
    }

    override fun getArea(): Double {
        return Math.PI * getRadiusX() * getRadiusY()
    }
}
