class Point(
    private var x: Double,
    private var y: Double
) : Movable {
    init {
        require(!x.isInfinite()) { "X coordinate must not be infinite" }
        require(!y.isInfinite()) { "Y coordinate must not be infinite" }
    }

    fun getX(): Double = x
    fun getY(): Double = y

    override fun move(deltaX: Double, deltaY: Double) {
        require(!(x + deltaX).isInfinite()) { "Moving to infinite X coordinate is not allowed" }
        require(!(y + deltaY).isInfinite()) { "Moving to infinite Y coordinate is not allowed" }
        x += deltaX
        y += deltaY
    }

    fun clone(): Point {
        return Point(x, y)
    }
}