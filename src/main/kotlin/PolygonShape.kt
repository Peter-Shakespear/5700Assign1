import kotlin.math.pow

abstract class PolygonShape(
    private var vertices: List<Point>
) : Movable, CalcuableArea {

    fun getVertices(): List<Point> = vertices

    abstract override fun getArea(): Double

    override fun move(deltaX: Double, deltaY: Double) {
        for(point in vertices) {
            point.move(deltaX, deltaY)
        }
    }

    fun calculateDistance(p1: Point, p2: Point): Double {
        return kotlin.math.sqrt(
            (p2.getX() - p1.getX()).pow(2.0) +
                    (p2.getY() - p1.getY()).pow(2.0)
        )
    }
}