class Triangle(
    vertices: List<Point>
) : PolygonShape(vertices) {

    init {
        require(vertices.size == 3) { "Triangle must have exactly 3 vertices" }
    }

    override fun getArea(): Double {
        val triangleVertices = getVertices()
        val p1 = triangleVertices[0]
        val p2 = triangleVertices[1]
        val p3 = triangleVertices[2]

        // Using the shoelace formula for triangle area
        // Note: You'll still need getters for Point's x,y coordinates
        return 0.5 * kotlin.math.abs(
            p1.getX() * (p2.getY() - p3.getY()) +
                    p2.getX() * (p3.getY() - p1.getY()) +
                    p3.getX() * (p1.getY() - p2.getY())
        )
    }
}
