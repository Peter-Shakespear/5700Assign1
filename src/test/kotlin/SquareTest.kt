
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.math.sqrt

class SquareTest {

    @Test
    fun testConstructorWithValidSquare() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 0.0)
        val p3 = Point(2.0, 2.0)
        val p4 = Point(0.0, 2.0)
        val square = Square(listOf(p1, p2, p3, p4))

        val vertices = square.getVertices()
        assertEquals(4, vertices.size)
        assertEquals(0.0, vertices[0].getX())
        assertEquals(0.0, vertices[0].getY())
        assertEquals(2.0, vertices[1].getX())
        assertEquals(0.0, vertices[1].getY())
        assertEquals(2.0, vertices[2].getX())
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
            Square(listOf(p1, p2, p3))
        }
        assertEquals("Square must have exactly 4 vertices", exception.message)
    }

    @Test
    fun testConstructorWithTooManyVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)
        val p4 = Point(0.0, 1.0)
        val p5 = Point(0.5, 0.5)

        val exception = assertThrows<IllegalArgumentException> {
            Square(listOf(p1, p2, p3, p4, p5))
        }
        assertEquals("Square must have exactly 4 vertices", exception.message)
    }

    @Test
    fun testConstructorWithEmptyList() {
        val exception = assertThrows<IllegalArgumentException> {
            Square(emptyList())
        }
        assertEquals("Square must have exactly 4 vertices", exception.message)
    }

    @Test
    fun testConstructorWithZeroLengthSide() {
        // Two identical points create a zero-length side
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.0, 0.0) // Same as p1
        val p3 = Point(1.0, 0.0)
        val p4 = Point(1.0, 1.0)

        val exception = assertThrows<IllegalArgumentException> {
            Square(listOf(p1, p2, p3, p4))
        }
        assertEquals("Square sides cannot have zero length", exception.message)
    }

    @Test
    fun testConstructorWithVerySmallButValidSides() {
        // Sides just above the 1e-10 threshold
        val sideLength = 1e-9
        val p1 = Point(0.0, 0.0)
        val p2 = Point(sideLength, 0.0)
        val p3 = Point(sideLength, sideLength)
        val p4 = Point(0.0, sideLength)

        val square = Square(listOf(p1, p2, p3, p4))
        assertEquals(sideLength * sideLength, square.getArea(), 1e-20)
    }

    @Test
    fun testConstructorWithAlmostZeroSides() {
        // Sides just below the 1e-10 threshold
        val sideLength = 1e-11
        val p1 = Point(0.0, 0.0)
        val p2 = Point(sideLength, 0.0)
        val p3 = Point(sideLength, sideLength)
        val p4 = Point(0.0, sideLength)

        val exception = assertThrows<IllegalArgumentException> {
            Square(listOf(p1, p2, p3, p4))
        }
        assertEquals("Square sides cannot have zero length", exception.message)
    }

    @Test
    fun testConstructorWithUnequalSides() {
        // Rectangle, not a square
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(3.0, 2.0)
        val p4 = Point(0.0, 2.0)

        val exception = assertThrows<IllegalArgumentException> {
            Square(listOf(p1, p2, p3, p4))
        }
        assertEquals("All sides of a square must have equal length", exception.message)
    }

    @Test
    fun testConstructorWithSlightlyUnequalSides() {
        // Sides that differ by more than the tolerance
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0001) // Slightly longer side
        val p4 = Point(0.0, 1.0001)

        val exception = assertThrows<IllegalArgumentException> {
            Square(listOf(p1, p2, p3, p4))
        }
        assertEquals("All sides of a square must have equal length", exception.message)
    }

    @Test
    fun testConstructorWithSidesWithinTolerance() {
        // Sides that differ by less than 1e-10 (should be accepted)
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0 + 1e-11) // Difference within tolerance
        val p4 = Point(0.0, 1.0 + 1e-11)

        val square = Square(listOf(p1, p2, p3, p4))
        assertEquals(1.0, square.getArea(), 0.001)
    }

    @Test
    fun testConstructorWithNonSquareQuadrilateral() {
        // Rhombus with equal sides but not a square
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 0.0)
        val p3 = Point(3.0, 1.732) // Creates a rhombus
        val p4 = Point(1.0, 1.732)

        val exception = assertThrows<IllegalArgumentException> {
            Square(listOf(p1, p2, p3, p4))
        }
        assertEquals("All sides of a square must have equal length", exception.message)
    }

    @Test
    fun testGetAreaUnitSquare() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)
        val p4 = Point(0.0, 1.0)
        val square = Square(listOf(p1, p2, p3, p4))

        assertEquals(1.0, square.getArea(), 0.001)
    }

    @Test
    fun testGetAreaLargerSquare() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 0.0)
        val p3 = Point(3.0, 3.0)
        val p4 = Point(0.0, 3.0)
        val square = Square(listOf(p1, p2, p3, p4))

        assertEquals(9.0, square.getArea(), 0.001)
    }

    @Test
    fun testGetAreaSquareWithNegativeCoordinates() {
        val p1 = Point(-2.0, -2.0)
        val p2 = Point(0.0, -2.0)
        val p3 = Point(0.0, 0.0)
        val p4 = Point(-2.0, 0.0)
        val square = Square(listOf(p1, p2, p3, p4))

        assertEquals(4.0, square.getArea(), 0.001)
    }

    @Test
    fun testGetAreaRotatedSquare() {
        // Diamond orientation - square rotated 45 degrees
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 1.0)
        val p3 = Point(0.0, 2.0)
        val p4 = Point(-1.0, 1.0)
        val square = Square(listOf(p1, p2, p3, p4))

        // Side length is sqrt(2), so area is 2
        assertEquals(2.0, square.getArea(), 0.001)
    }

    @Test
    fun testGetAreaDecimalSideLength() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.5, 0.0)
        val p3 = Point(2.5, 2.5)
        val p4 = Point(0.0, 2.5)
        val square = Square(listOf(p1, p2, p3, p4))

        assertEquals(6.25, square.getArea(), 0.001)
    }

    @Test
    fun testGetAreaVerySmallSquare() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.01, 0.0)
        val p3 = Point(0.01, 0.01)
        val p4 = Point(0.0, 0.01)
        val square = Square(listOf(p1, p2, p3, p4))

        assertEquals(0.0001, square.getArea(), 0.000001)
    }

    @Test
    fun testGetAreaCalculationBasedOnFirstTwoVertices() {
        // The area calculation uses distance between first two vertices
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.0, 3.0) // vertical side first
        val p3 = Point(3.0, 3.0)
        val p4 = Point(3.0, 0.0)
        val square = Square(listOf(p1, p2, p3, p4))

        assertEquals(9.0, square.getArea(), 0.001)
    }

    @Test
    fun testGetAreaDifferentVertexOrder() {
        // Same square with different vertex ordering
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 0.0)
        val p3 = Point(2.0, 2.0)
        val p4 = Point(0.0, 2.0)

        val square1 = Square(listOf(p1, p2, p3, p4))
        val square2 = Square(listOf(p2, p3, p4, p1))
        val square3 = Square(listOf(p3, p4, p1, p2))
        val square4 = Square(listOf(p4, p1, p2, p3))

        assertEquals(4.0, square1.getArea(), 0.001)
        assertEquals(4.0, square2.getArea(), 0.001)
        assertEquals(4.0, square3.getArea(), 0.001)
        assertEquals(4.0, square4.getArea(), 0.001)
    }

    @Test
    fun testValidSquareWithFloatingPointPrecisionIssues() {
        // Test a square where floating-point arithmetic might cause tiny differences
        val side = 1.0 / 3.0 // 0.333...
        val p1 = Point(0.0, 0.0)
        val p2 = Point(side, 0.0)
        val p3 = Point(side, side)
        val p4 = Point(0.0, side)

        val square = Square(listOf(p1, p2, p3, p4))
        assertEquals(side * side, square.getArea(), 0.001)
    }

    @Test
    fun testInheritedMoveFunction() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 0.0)
        val p3 = Point(1.0, 1.0)
        val p4 = Point(0.0, 1.0)
        val square = Square(listOf(p1, p2, p3, p4))

        val originalArea = square.getArea()
        square.move(3.0, -2.0)

        val vertices = square.getVertices()
        assertEquals(3.0, vertices[0].getX())
        assertEquals(-2.0, vertices[0].getY())
        assertEquals(4.0, vertices[1].getX())
        assertEquals(-2.0, vertices[1].getY())
        assertEquals(4.0, vertices[2].getX())
        assertEquals(-1.0, vertices[2].getY())
        assertEquals(3.0, vertices[3].getX())
        assertEquals(-1.0, vertices[3].getY())

        // Area should remain the same after translation
        assertEquals(originalArea, square.getArea(), 0.001)
    }

    @Test
    fun testMultipleMoves() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(2.0, 1.0)
        val p3 = Point(2.0, 2.0)
        val p4 = Point(1.0, 2.0)
        val square = Square(listOf(p1, p2, p3, p4))

        square.move(0.5, -0.5)
        square.move(-1.0, 2.0)
        square.move(2.0, -1.0)

        val vertices = square.getVertices()
        assertEquals(2.5, vertices[0].getX())
        assertEquals(1.5, vertices[0].getY())
        assertEquals(3.5, vertices[1].getX())
        assertEquals(1.5, vertices[1].getY())
        assertEquals(3.5, vertices[2].getX())
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
        val square = Square(listOf(p1, p2, p3, p4))

        val vertices = square.getVertices()
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
        val p2 = Point(5.0, 0.0)
        val p3 = Point(5.0, 5.0)
        val p4 = Point(0.0, 5.0)
        val square = Square(listOf(p1, p2, p3, p4))

        // Initial area
        assertEquals(25.0, square.getArea(), 0.001)

        // Move and verify area is preserved
        square.move(-2.5, 1.0)
        assertEquals(25.0, square.getArea(), 0.001)

        // Verify vertices moved correctly
        val vertices = square.getVertices()
        assertEquals(-2.5, vertices[0].getX())
        assertEquals(1.0, vertices[0].getY())
        assertEquals(2.5, vertices[1].getX())
        assertEquals(1.0, vertices[1].getY())
        assertEquals(2.5, vertices[2].getX())
        assertEquals(6.0, vertices[2].getY())
        assertEquals(-2.5, vertices[3].getX())
        assertEquals(6.0, vertices[3].getY())
    }

    @Test
    fun testAreaWithLargerCoordinates() {
        val p1 = Point(100.0, 200.0)
        val p2 = Point(107.0, 200.0)
        val p3 = Point(107.0, 207.0)
        val p4 = Point(100.0, 207.0)
        val square = Square(listOf(p1, p2, p3, p4))

        assertEquals(49.0, square.getArea(), 0.001)
    }

    @Test
    fun testSquareValidationWithDifferentOrientations() {
        // Test clockwise orientation
        val p1 = Point(0.0, 0.0)
        val p2 = Point(0.0, 2.0)
        val p3 = Point(2.0, 2.0)
        val p4 = Point(2.0, 0.0)
        val square1 = Square(listOf(p1, p2, p3, p4))
        assertEquals(4.0, square1.getArea(), 0.001)

        // Test counter-clockwise orientation
        val square2 = Square(listOf(p1, p4, p3, p2))
        assertEquals(4.0, square2.getArea(), 0.001)
    }

    @Test
    fun testInheritedFunctionalityIntegration() {
        val square = Square(listOf(
            Point(0.0, 0.0),
            Point(2.0, 0.0),
            Point(2.0, 2.0),
            Point(0.0, 2.0)
        ))

        // Test inherited move
        square.move(1.0, 1.0)

        // Test inherited getVertices
        val vertices = square.getVertices()
        assertEquals(1.0, vertices[0].getX())
        assertEquals(1.0, vertices[0].getY())

        // Test Square-specific functionality still works
        assertEquals(4.0, square.getArea(), 0.001)
    }
}