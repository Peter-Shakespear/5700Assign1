
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class RectangleTest {

    @Test
    fun testConstructorWithValidVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(3.0, 2.0)
        val p4 = Point(0.0, 2.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        val vertices = rectangle.getVertices()
        assertEquals(4, vertices.size)
        assertEquals(0.0, vertices[0].getX())
        assertEquals(0.0, vertices[0].getY())
        assertEquals(3.0, vertices[1].getX())
        assertEquals(0.0, vertices[1].getY())
        assertEquals(3.0, vertices[2].getX())
        assertEquals(2.0, vertices[2].getY())
        assertEquals(0.0, vertices[3].getX())
        assertEquals(2.0, vertices[3].getY())
    }

    @Test
    fun testConstructorWithTooFewVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)

        val exception = assertThrows<IllegalArgumentException> {
            Rectangle(listOf(p1, p2, p3))
        }
        assertEquals("Rectangle must have exactly 4 vertices", exception.message)
    }

    @Test
    fun testConstructorWithTooManyVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)
        val p4 = Point(0.0, 1.0)
        val p5 = Point(0.5, 0.5)

        val exception = assertThrows<IllegalArgumentException> {
            Rectangle(listOf(p1, p2, p3, p4, p5))
        }
        assertEquals("Rectangle must have exactly 4 vertices", exception.message)
    }

    @Test
    fun testConstructorWithEmptyList() {
        val exception = assertThrows<IllegalArgumentException> {
            Rectangle(emptyList())
        }
        assertEquals("Rectangle must have exactly 4 vertices", exception.message)
    }

    @Test
    fun testGetAreaSimpleRectangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(4.0, 0.0)
        val p3 = Point(4.0, 3.0)
        val p4 = Point(0.0, 3.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(12.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaUnitRectangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)
        val p4 = Point(0.0, 1.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(1.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaSquareAsRectangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 0.0)
        val p3 = Point(2.0, 2.0)
        val p4 = Point(0.0, 2.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(4.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaWithNegativeCoordinates() {
        val p1 = Point(-2.0, -3.0)
        val p2 = Point(1.0, -3.0)
        val p3 = Point(1.0, -1.0)
        val p4 = Point(-2.0, -1.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(6.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaMixedCoordinates() {
        val p1 = Point(-1.0, -2.0)
        val p2 = Point(3.0, -2.0)
        val p3 = Point(3.0, 1.0)
        val p4 = Point(-1.0, 1.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(12.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaDecimalDimensions() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.5, 0.0)
        val p3 = Point(2.5, 1.5)
        val p4 = Point(0.0, 1.5)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(3.75, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaVerySmallRectangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.001, 0.0)
        val p3 = Point(0.001, 0.002)
        val p4 = Point(0.0, 0.002)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(0.000002, rectangle.getArea(), 0.0000001)
    }

    @Test
    fun testGetAreaCounterClockwiseVertices() {
        // Vertices in counter-clockwise order
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(3.0, 2.0)
        val p4 = Point(0.0, 2.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(6.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaClockwiseVertices() {
        // Vertices in clockwise order
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.0, 2.0)
        val p3 = Point(3.0, 2.0)
        val p4 = Point(3.0, 0.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(6.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaDifferentVertexOrdering() {
        // Test that area calculation works with different starting vertices
        val p1 = Point(0.0, 0.0)
        val p2 = Point(4.0, 0.0)
        val p3 = Point(4.0, 3.0)
        val p4 = Point(0.0, 3.0)

        val rect1 = Rectangle(listOf(p1, p2, p3, p4))
        val rect2 = Rectangle(listOf(p2, p3, p4, p1))
        val rect3 = Rectangle(listOf(p3, p4, p1, p2))
        val rect4 = Rectangle(listOf(p4, p1, p2, p3))

        assertEquals(12.0, rect1.getArea(), 0.001)
        assertEquals(12.0, rect2.getArea(), 0.001)
        assertEquals(12.0, rect3.getArea(), 0.001)
        assertEquals(12.0, rect4.getArea(), 0.001)
    }

    @Test
    fun testGetAreaRotatedRectangle() {
        // Rectangle rotated 45 degrees (diamond shape)
        val p1 = Point(0.0, 1.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(2.0, 1.0)
        val p4 = Point(1.0, 2.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(2.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaIrregularQuadrilateral() {
        // Not actually a rectangle, but should still calculate area correctly
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 1.0)
        val p3 = Point(2.0, 4.0)
        val p4 = Point(-1.0, 3.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        // Using shoelace formula: Area = 0.5 * |sum of (x_i * y_(i+1) - x_(i+1) * y_i)|
        // = 0.5 * |(0*1 - 3*0) + (3*4 - 2*1) + (2*3 - (-1)*4) + ((-1)*0 - 0*3)|
        // = 0.5 * |0 + 10 + 10 + 0| = 10
        assertEquals(10.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testGetAreaLargeCoordinates() {
        val p1 = Point(100.0, 200.0)
        val p2 = Point(105.0, 200.0)
        val p3 = Point(105.0, 207.0)
        val p4 = Point(100.0, 207.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(35.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testInheritedMoveFunction() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 0.0)
        val p3 = Point(2.0, 3.0)
        val p4 = Point(0.0, 3.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        val originalArea = rectangle.getArea()
        rectangle.move(1.5, -0.5)

        val vertices = rectangle.getVertices()
        assertEquals(1.5, vertices[0].getX())
        assertEquals(-0.5, vertices[0].getY())
        assertEquals(3.5, vertices[1].getX())
        assertEquals(-0.5, vertices[1].getY())
        assertEquals(3.5, vertices[2].getX())
        assertEquals(2.5, vertices[2].getY())
        assertEquals(1.5, vertices[3].getX())
        assertEquals(2.5, vertices[3].getY())

        // Area should remain the same after translation
        assertEquals(originalArea, rectangle.getArea(), 0.001)
    }

    @Test
    fun testMultipleMoves() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(3.0, 1.0)
        val p3 = Point(3.0, 2.0)
        val p4 = Point(1.0, 2.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        rectangle.move(0.5, -0.5)
        rectangle.move(-1.0, 2.0)
        rectangle.move(2.0, -1.0)

        val vertices = rectangle.getVertices()
        assertEquals(2.5, vertices[0].getX())
        assertEquals(1.5, vertices[0].getY())
        assertEquals(4.5, vertices[1].getX())
        assertEquals(1.5, vertices[1].getY())
        assertEquals(4.5, vertices[2].getX())
        assertEquals(2.5, vertices[2].getY())
        assertEquals(2.5, vertices[3].getX())
        assertEquals(2.5, vertices[3].getY())
    }

    @Test
    fun testGetVertices() {
        val p1 = Point(1.5, -2.3)
        val p2 = Point(4.7, -2.3)
        val p3 = Point(4.7, 0.9)
        val p4 = Point(1.5, 0.9)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        val vertices = rectangle.getVertices()
        assertEquals(4, vertices.size)
        assertEquals(1.5, vertices[0].getX())
        assertEquals(-2.3, vertices[0].getY())
        assertEquals(4.7, vertices[1].getX())
        assertEquals(-2.3, vertices[1].getY())
        assertEquals(4.7, vertices[2].getX())
        assertEquals(0.9, vertices[2].getY())
        assertEquals(1.5, vertices[3].getX())
        assertEquals(0.9, vertices[3].getY())
    }

    @Test
    fun testComplexOperations() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(6.0, 0.0)
        val p3 = Point(6.0, 4.0)
        val p4 = Point(0.0, 4.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        // Initial area
        assertEquals(24.0, rectangle.getArea(), 0.001)

        // Move and verify area is preserved
        rectangle.move(-3.0, 2.0)
        assertEquals(24.0, rectangle.getArea(), 0.001)

        // Verify vertices moved correctly
        val vertices = rectangle.getVertices()
        assertEquals(-3.0, vertices[0].getX())
        assertEquals(2.0, vertices[0].getY())
        assertEquals(3.0, vertices[1].getX())
        assertEquals(2.0, vertices[1].getY())
        assertEquals(3.0, vertices[2].getX())
        assertEquals(6.0, vertices[2].getY())
        assertEquals(-3.0, vertices[3].getX())
        assertEquals(6.0, vertices[3].getY())
    }

    @Test
    fun testShoelaceFormulaDirectly() {
        // Test that verifies the shoelace formula implementation
        val p1 = Point(1.0, 1.0)
        val p2 = Point(4.0, 1.0)
        val p3 = Point(4.0, 3.0)
        val p4 = Point(1.0, 3.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(6.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testZeroAreaRectangle() {
        // Degenerate case - all points on a line (zero area)
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(2.0, 0.0)
        val p4 = Point(3.0, 0.0)
        val rectangle = Rectangle(listOf(p1, p2, p3, p4))

        assertEquals(0.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testZeroHeightRectangle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 0.0)
        val p3 = Point(2.0, 0.0)
        val p4 = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Rectangle(listOf(p1, p2, p3, p4))
        }
        assertEquals("Rectangle sides cannot have zero length", exception.message)
    }

    @Test
    fun testInheritedFunctionalityIntegration() {
        val rectangle = Rectangle(listOf(
            Point(0.0, 0.0),
            Point(3.0, 0.0),
            Point(3.0, 2.0),
            Point(0.0, 2.0)
        ))

        // Test inherited move
        rectangle.move(1.0, 1.0)

        // Test inherited getVertices
        val vertices = rectangle.getVertices()
        assertEquals(1.0, vertices[0].getX())
        assertEquals(1.0, vertices[0].getY())

        // Test Rectangle-specific functionality still works
        assertEquals(6.0, rectangle.getArea(), 0.001)
    }

    @Test
    fun testAreaPreservationAfterMultipleOperations() {
        val rectangle = Rectangle(listOf(
            Point(2.0, 3.0),
            Point(5.0, 3.0),
            Point(5.0, 7.0),
            Point(2.0, 7.0)
        ))

        val originalArea = rectangle.getArea()
        assertEquals(12.0, originalArea, 0.001)

        // Perform multiple moves
        rectangle.move(10.0, -5.0)
        assertEquals(originalArea, rectangle.getArea(), 0.001)

        rectangle.move(-15.0, 8.0)
        assertEquals(originalArea, rectangle.getArea(), 0.001)

        rectangle.move(3.0, -1.5)
        assertEquals(originalArea, rectangle.getArea(), 0.001)
    }
}