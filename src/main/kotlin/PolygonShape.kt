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
}