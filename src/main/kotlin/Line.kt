import kotlin.math.pow

class Line(
    var vertices: List<Point>
) : Movable {
    init {
        require(vertices.size == 2) { "Line must have exactly 2 vertices" }
    }

    override fun move(deltaX: Double, deltaY: Double) {
        for(point in vertices) {
            point.move(deltaX, deltaY)
        }
    }

    fun getPoints(): List<Point> = vertices

    fun getSlope(): Double {
        val p1 = vertices[0]
        val p2 = vertices[1]
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX())
    }

    fun getLength(): Double {
        val p1 = vertices[0]
        val p2 = vertices[1]
        return kotlin.math.sqrt(
            (p2.getX() - p1.getX()).pow(2) +
                    (p2.getY() - p1.getY()).pow(2)
        )
    }
}