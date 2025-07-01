class Rectangle(
    vertices: List<Point>
) : PolygonShape(vertices) {

    init {
        require(vertices.size == 4) { "Rectangle must have exactly 4 vertices" }
    }

    override fun getArea(): Double {
        val rectangleVertices = getVertices()

        // Using the shoelace formula for any quadrilateral
        var area = 0.0
        val n = rectangleVertices.size

        for (i in 0 until n) {
            val j = (i + 1) % n
            area += rectangleVertices[i].getX() * rectangleVertices[j].getY()
            area -= rectangleVertices[j].getX() * rectangleVertices[i].getY()
        }

        return kotlin.math.abs(area) / 2.0
    }
}
