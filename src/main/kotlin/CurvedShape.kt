abstract class CurvedShape(
    private var center: Point, 
    private var radiusX: Double, 
    private var radiusY: Double
) : Movable, CalcuableArea {

    override fun getArea(): Double {
        val area = Math.PI * getRadiusX() * getRadiusY()
        return area
    }

    fun getRadiusX(): Double = radiusX
    fun getRadiusY(): Double = radiusY
    fun getCenter(): Point = center

    override fun move(deltaX: Double, deltaY: Double) {
        center.move(deltaX, deltaY)
    }
}