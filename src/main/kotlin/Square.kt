import kotlin.math.pow
import kotlin.math.sqrt

class Square(
    vertices: List<Point>
) : PolygonShape(vertices) {

    init {
        require(vertices.size == 4) { "Square must have exactly 4 vertices" }
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
