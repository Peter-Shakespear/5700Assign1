abstract class PolygonShape(val vertices: List<Point>) : Movable, CalcuableArea {
    abstract override fun getArea()

    override fun move(deltaX: Double, deltaY: Double) {
        for(point in vertices) {
            point.move(deltaX, deltaY)
        }
    }
}