class Point(
    private var x: Double,
    private var y: Double
) : Movable {
    init {
        require(x.isFinite() && y.isFinite()) { "Coordinates must not be infinite" }
    }

    fun getX(): Double = x
    fun getY(): Double = y

    override fun move(deltaX: Double, deltaY: Double) {
        require((x + deltaX).isFinite() && (y + deltaY).isFinite()) { "Coordinates must not be infinite" }
        x += deltaX
        y += deltaY
    }

    fun clone(): Point {
        return Point(x, y)
    }
}