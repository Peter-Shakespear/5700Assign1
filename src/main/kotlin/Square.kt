import kotlin.math.pow
import kotlin.math.sqrt

class Square(
    vertices: List<Point>
) : PolygonShape(vertices) {

    private fun calculateDistance(p1: Point, p2: Point): Double {
        return sqrt(
            (p2.getX() - p1.getX()).pow(2.0) +
                    (p2.getY() - p1.getY()).pow(2.0)
        )
    }

    init {
        require(vertices.size == 4) { "Square must have exactly 4 vertices" }

        val side1 = calculateDistance(vertices[0], vertices[1])
        val side2 = calculateDistance(vertices[1], vertices[2])
        val side3 = calculateDistance(vertices[2], vertices[3])
        val side4 = calculateDistance(vertices[3], vertices[0])

        require(
            side1 > 1e-10 && side2 > 1e-10 &&
                    side3 > 1e-10 && side4 > 1e-10
        ) { "Square sides cannot have zero length" }

        require(
            kotlin.math.abs(side1 - side2) < 1e-10 &&
                    kotlin.math.abs(side2 - side3) < 1e-10 &&
                    kotlin.math.abs(side3 - side4) < 1e-10
        ) { "All sides of a square must have equal length" }
    }

    override fun getArea(): Double {
        val squareVertices = getVertices()

        // For a square, we can calculate the side length and square it
        val p1 = squareVertices[0]
        val p2 = squareVertices[1]

        // Calculate side length (distance between the first two vertices)
        val sideLength = sqrt(
            (p2.getX() - p1.getX()).pow(2.0) +
                    (p2.getY() - p1.getY()).pow(2.0)
        )

        return sideLength * sideLength
    }
}
