import kotlin.math.pow

class Rectangle(
    vertices: List<Point>
) : PolygonShape(vertices) {

    private fun calculateDistance(p1: Point, p2: Point): Double {
        return kotlin.math.sqrt(
            (p2.getX() - p1.getX()).pow(2.0) +
                    (p2.getY() - p1.getY()).pow(2.0)
        )
    }

    init {
        require(vertices.size == 4) { "Rectangle must have exactly 4 vertices" }

        val side1 = calculateDistance(vertices[0], vertices[1])
        val side2 = calculateDistance(vertices[1], vertices[2])
        val side3 = calculateDistance(vertices[2], vertices[3])
        val side4 = calculateDistance(vertices[3], vertices[0])

        require(
            side1 > 1e-10 && side2 > 1e-10 &&
                    side3 > 1e-10 && side4 > 1e-10
        ) { "Rectangle sides cannot have zero length" }
    }

    override fun getArea(): Double {
        val rectangleVertices = getVertices()

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
