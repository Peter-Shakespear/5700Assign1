abstract class CurvedShape(
    private var center: Point, 
    private var radiusX: Double, 
    private var radiusY: Double
) : Movable, CalcuableArea {
    abstract override fun getArea()
    
    override fun move(deltaX: Double, deltaY: Double) {
        center.move(deltaX, deltaY)
    }
}