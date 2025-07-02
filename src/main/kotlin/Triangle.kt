class Triangle(
    vertices: List<Point>
) : PolygonShape(vertices) {

    init {
        require(vertices.size == 3) { "Triangle must have exactly 3 vertices" }
        val p1 = vertices[0]
        val p2 = vertices[1]
        val p3 = vertices[2]
        val determinant = p1.getX() * (p2.getY() - p3.getY()) +
                p2.getX() * (p3.getY() - p1.getY()) +
                p3.getX() * (p1.getY() - p2.getY())
        require(kotlin.math.abs(determinant) > 1e-10) { "Triangle vertices cannot be collinear" }
    }

    override fun getArea(): Double {
        val triangleVertices = getVertices()
        val p1 = triangleVertices[0]
        val p2 = triangleVertices[1]
        val p3 = triangleVertices[2]

        return 0.5 * kotlin.math.abs(
            p1.getX() * (p2.getY() - p3.getY()) +
                    p2.getX() * (p3.getY() - p1.getY()) +
                    p3.getX() * (p1.getY() - p2.getY())
        )
    }
}
