class Point(
    private var x: Double,
    private var y: Double
) : Movable {

    fun getX(): Double = x
    fun getY(): Double = y

    override fun move(deltaX: Double, deltaY: Double) {
        x += deltaX
        y += deltaY
    }
}