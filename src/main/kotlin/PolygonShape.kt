abstract class PolygonShape(
    private val vertices: List<Point>
) : Movable, CalcuableArea {

    protected fun getVertices(): List<Point> = vertices

    abstract override fun getArea(): Double

    override fun move(deltaX: Double, deltaY: Double) {
        for(point in vertices) {
            point.move(deltaX, deltaY)
        }
    }
}