
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class TriangleTest {

    @Test
    fun testConstructorWithValidVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(0.0, 4.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        val vertices = triangle.getVertices()
        assertEquals(3, vertices.size)
        assertEquals(0.0, vertices[0].getX())
        assertEquals(0.0, vertices[0].getY())
        assertEquals(3.0, vertices[1].getX())
        assertEquals(0.0, vertices[1].getY())
        assertEquals(0.0, vertices[2].getX())
        assertEquals(4.0, vertices[2].getY())
    }

    @Test
    fun testConstructorWithTooFewVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 1.0)

        val exception = assertThrows<IllegalArgumentException> {
            Triangle(listOf(p1, p2))
        }
        assertEquals("Triangle must have exactly 3 vertices", exception.message)
    }

    @Test
    fun testConstructorWithTooManyVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(0.0, 1.0)
        val p4 = Point(1.0, 1.0)

        val exception = assertThrows<IllegalArgumentException> {
            Triangle(listOf(p1, p2, p3, p4))
        }
        assertEquals("Triangle must have exactly 3 vertices", exception.message)
    }

    @Test
    fun testConstructorWithCollinearVerticesHorizontal() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(2.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Triangle(listOf(p1, p2, p3))
        }
        assertEquals("Triangle vertices cannot be collinear", exception.message)
    }

    @Test
    fun testConstructorWithCollinearVerticesVertical() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.0, 1.0)
        val p3 = Point(0.0, 2.0)

        val exception = assertThrows<IllegalArgumentException> {
            Triangle(listOf(p1, p2, p3))
        }
        assertEquals("Triangle vertices cannot be collinear", exception.message)
    }

    @Test
    fun testConstructorWithCollinearVerticesDiagonal() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 1.0)
        val p3 = Point(2.0, 2.0)

        val exception = assertThrows<IllegalArgumentException> {
            Triangle(listOf(p1, p2, p3))
        }
        assertEquals("Triangle vertices cannot be collinear", exception.message)
    }

    @Test
    fun testConstructorWithNearlyCollinearVertices() {
        // Points that are very close to being collinear but not quite
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(2.0, 1e-11) // Very small deviation from collinear

        val exception = assertThrows<IllegalArgumentException> {
            Triangle(listOf(p1, p2, p3))
        }
        assertEquals("Triangle vertices cannot be collinear", exception.message)
    }

    @Test
    fun testConstructorWithBarelyValidVertices() {
        // Points that are just above the collinearity threshold
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(2.0, 1e-9) // Just above the 1e-10 threshold

        // This should create a valid triangle
        assertDoesNotThrow {
            Triangle(listOf(p1, p2, p3))
        }
    }

    @Test
    fun testGetAreaRightTriangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(0.0, 4.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(6.0, triangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaEquilateralTriangle() {
        // Equilateral triangle with side length 2
        val height = kotlin.math.sqrt(3.0)
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 0.0)
        val p3 = Point(1.0, height)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(kotlin.math.sqrt(3.0), triangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaIsoscelesTriangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(4.0, 0.0)
        val p3 = Point(2.0, 3.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(6.0, triangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaWithNegativeCoordinates() {
        val p1 = Point(-1.0, -1.0)
        val p2 = Point(2.0, -1.0)
        val p3 = Point(-1.0, 2.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(4.5, triangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaVertexOrder() {
        // Same triangle with different vertex ordering should give same area
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(0.0, 4.0)

        val triangle1 = Triangle(listOf(p1, p2, p3))
        val triangle2 = Triangle(listOf(p2, p3, p1))
        val triangle3 = Triangle(listOf(p3, p1, p2))

        assertEquals(triangle1.getArea(), triangle2.getArea(), 0.001)
        assertEquals(triangle1.getArea(), triangle3.getArea(), 0.001)
    }

    @Test
    fun testGetAreaCounterClockwiseVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(0.0, 4.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(6.0, triangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaClockwiseVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.0, 4.0)
        val p3 = Point(3.0, 0.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(6.0, triangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaVerySmallTriangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1e-5, 0.0)
        val p3 = Point(0.0, 1e-5)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(5e-11, triangle.getArea(), 1e-13)
    }

    @Test
    fun testMoveTriangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(0.0, 4.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        val originalArea = triangle.getArea()
        triangle.move(5.0, -2.0)

        val vertices = triangle.getVertices()
        assertEquals(5.0, vertices[0].getX())
        assertEquals(-2.0, vertices[0].getY())
        assertEquals(8.0, vertices[1].getX())
        assertEquals(-2.0, vertices[1].getY())
        assertEquals(5.0, vertices[2].getX())
        assertEquals(2.0, vertices[2].getY())

        // Area should remain the same after translation
        assertEquals(originalArea, triangle.getArea(), 0.001)
    }

    @Test
    fun testMultipleMoves() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(1.0, 2.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        triangle.move(1.0, 1.0)
        triangle.move(-0.5, 2.0)
        triangle.move(2.5, -1.5)

        val vertices = triangle.getVertices()
        assertEquals(4.0, vertices[0].getX())
        assertEquals(2.5, vertices[0].getY())
        assertEquals(5.0, vertices[1].getX())
        assertEquals(2.5, vertices[1].getY())
        assertEquals(4.0, vertices[2].getX())
        assertEquals(3.5, vertices[2].getY())
    }

    @Test
    fun testGetVertices() {
        val p1 = Point(1.5, -2.3)
        val p2 = Point(4.7, 1.8)
        val p3 = Point(-0.5, 3.2)
        val triangle = Triangle(listOf(p1, p2, p3))

        val vertices = triangle.getVertices()
        assertEquals(3, vertices.size)
        assertEquals(1.5, vertices[0].getX())
        assertEquals(-2.3, vertices[0].getY())
        assertEquals(4.7, vertices[1].getX())
        assertEquals(1.8, vertices[1].getY())
        assertEquals(-0.5, vertices[2].getX())
        assertEquals(3.2, vertices[2].getY())
    }

    @Test
    fun testComplexOperations() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(6.0, 0.0)
        val p3 = Point(3.0, 4.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        // Initial area
        assertEquals(12.0, triangle.getArea(), 0.001)

        // Move and verify area is preserved
        triangle.move(-3.0, 2.0)
        assertEquals(12.0, triangle.getArea(), 0.001)

        // Verify vertices moved correctly
        val vertices = triangle.getVertices()
        assertEquals(-3.0, vertices[0].getX())
        assertEquals(2.0, vertices[0].getY())
        assertEquals(3.0, vertices[1].getX())
        assertEquals(2.0, vertices[1].getY())
        assertEquals(0.0, vertices[2].getX())
        assertEquals(6.0, vertices[2].getY())
    }

    @Test
    fun testAreaCalculationWithLargeCoordinates() {
        val p1 = Point(1000.0, 1000.0)
        val p2 = Point(1003.0, 1000.0)
        val p3 = Point(1000.0, 1004.0)
        val triangle = Triangle(listOf(p1, p2, p3))

        assertEquals(6.0, triangle.getArea(), 0.001)
    }
}