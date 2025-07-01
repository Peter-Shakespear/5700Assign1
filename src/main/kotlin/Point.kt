class Point(
    private var x: Double,
    private var y: Double
) : Movable {
    override fun move(deltaX: Double, deltaY: Double) {
        x += deltaX
        y += deltaY
    }
}