abstract class CurvedShape(
    private var center: Point, 
    private var radiusX: Double, 
    private var radiusY: Double
) : Movable, CalcuableArea {
    abstract override fun getArea(): Double

    fun getRadiusX(): Double = radiusX
    fun getRadiusY(): Double = radiusY
    fun getCenter(): Point = center

    override fun move(deltaX: Double, deltaY: Double) {
        center.move(deltaX, deltaY)
    }
}