import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class LineTest {

    @Test
    fun testConstructorWithValidVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 4.0)
        val line = Line(listOf(p1, p2))
        
        val points = line.getPoints()
        assertEquals(2, points.size)
        assertEquals(0.0, points[0].getX())
        assertEquals(0.0, points[0].getY())
        assertEquals(3.0, points[1].getX())
        assertEquals(4.0, points[1].getY())
    }

    @Test
    fun testConstructorWithTooFewVertices() {
        val p1 = Point(1.0, 2.0)
        
        val exception = assertThrows<IllegalArgumentException> {
            Line(listOf(p1))
        }
        assertEquals("Line must have exactly 2 vertices", exception.message)
    }

    @Test
    fun testConstructorWithTooManyVertices() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 1.0)
        val p3 = Point(2.0, 2.0)
        
        val exception = assertThrows<IllegalArgumentException> {
            Line(listOf(p1, p2, p3))
        }
        assertEquals("Line must have exactly 2 vertices", exception.message)
    }

    @Test
    fun testConstructorWithEmptyList() {
        val exception = assertThrows<IllegalArgumentException> {
            Line(emptyList())
        }
        assertEquals("Line must have exactly 2 vertices", exception.message)
    }

    @Test
    fun testMove() {
        val p1 = Point(1.0, 2.0)
        val p2 = Point(3.0, 4.0)
        val line = Line(listOf(p1, p2))
        
        line.move(2.0, -1.0)
        
        val points = line.getPoints()
        assertEquals(3.0, points[0].getX())
        assertEquals(1.0, points[0].getY())
        assertEquals(5.0, points[1].getX())
        assertEquals(3.0, points[1].getY())
    }

    @Test
    fun testMoveWithZeroDeltas() {
        val p1 = Point(5.0, -3.0)
        val p2 = Point(2.0, 7.0)
        val line = Line(listOf(p1, p2))
        
        line.move(0.0, 0.0)
        
        val points = line.getPoints()
        assertEquals(5.0, points[0].getX())
        assertEquals(-3.0, points[0].getY())
        assertEquals(2.0, points[1].getX())
        assertEquals(7.0, points[1].getY())
    }

    @Test
    fun testMultipleMoves() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 1.0)
        val line = Line(listOf(p1, p2))
        
        line.move(1.0, 2.0)
        line.move(-0.5, 1.5)
        line.move(2.0, -1.0)
        
        val points = line.getPoints()
        assertEquals(2.5, points[0].getX())
        assertEquals(2.5, points[0].getY())
        assertEquals(3.5, points[1].getX())
        assertEquals(3.5, points[1].getY())
    }

    @Test
    fun testGetPoints() {
        val p1 = Point(7.5, -2.3)
        val p2 = Point(-1.8, 4.6)
        val line = Line(listOf(p1, p2))
        
        val points = line.getPoints()
        assertEquals(2, points.size)
        assertEquals(7.5, points[0].getX())
        assertEquals(-2.3, points[0].getY())
        assertEquals(-1.8, points[1].getX())
        assertEquals(4.6, points[1].getY())
    }

    @Test
    fun testGetSlopePositive() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(2.0, 4.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(2.0, line.getSlope(), 0.001)
    }

    @Test
    fun testGetSlopeNegative() {
        val p1 = Point(0.0, 4.0)
        val p2 = Point(2.0, 0.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(-2.0, line.getSlope(), 0.001)
    }

    @Test
    fun testGetSlopeZero() {
        val p1 = Point(1.0, 3.0)
        val p2 = Point(5.0, 3.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(0.0, line.getSlope(), 0.001)
    }

    @Test
    fun testGetSlopeFractional() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 1.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(1.0/3.0, line.getSlope(), 0.001)
    }

    @Test
    fun testGetSlopeVerticalLine() {
        val p1 = Point(2.0, 1.0)
        val p2 = Point(2.0, 5.0)
        val line = Line(listOf(p1, p2))
        
        // Vertical line should have infinite slope
        assertEquals(Double.POSITIVE_INFINITY, line.getSlope())
    }

    @Test
    fun testGetSlopeAfterMove() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 1.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(1.0, line.getSlope(), 0.001)
        
        line.move(5.0, 10.0)
        
        // Slope should remain the same after translation
        assertEquals(1.0, line.getSlope(), 0.001)
    }

    @Test
    fun testGetLength() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 4.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(5.0, line.getLength(), 0.001)
    }

    @Test
    fun testGetLengthZero() {
        val p1 = Point(2.0, 3.0)
        val p2 = Point(2.0, 3.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(0.0, line.getLength(), 0.001)
    }

    @Test
    fun testGetLengthHorizontalLine() {
        val p1 = Point(1.0, 5.0)
        val p2 = Point(7.0, 5.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(6.0, line.getLength(), 0.001)
    }

    @Test
    fun testGetLengthVerticalLine() {
        val p1 = Point(3.0, 2.0)
        val p2 = Point(3.0, 8.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(6.0, line.getLength(), 0.001)
    }

    @Test
    fun testGetLengthAfterMove() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(3.0, 4.0)
        val line = Line(listOf(p1, p2))
        
        val originalLength = line.getLength()
        line.move(10.0, -5.0)
        
        // Length should remain the same after translation
        assertEquals(originalLength, line.getLength(), 0.001)
    }

    @Test
    fun testGetLengthWithNegativeCoordinates() {
        val p1 = Point(-2.0, -1.0)
        val p2 = Point(1.0, 3.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(5.0, line.getLength(), 0.001)
    }

    @Test
    fun testGetLengthPythagoreanTriple() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(5.0, 12.0)
        val line = Line(listOf(p1, p2))
        
        assertEquals(13.0, line.getLength(), 0.001)
    }

    @Test
    fun testComplexOperations() {
        val p1 = Point(1.0, 1.0)
        val p2 = Point(4.0, 5.0)
        val line = Line(listOf(p1, p2))
        
        // Initial state
        assertEquals(4.0/3.0, line.getSlope(), 0.001)
        assertEquals(5.0, line.getLength(), 0.001)
        
        // After move
        line.move(-1.0, -1.0)
        assertEquals(4.0/3.0, line.getSlope(), 0.001) // Slope unchanged
        assertEquals(5.0, line.getLength(), 0.001)     // Length unchanged
        
        // Points moved correctly
        val points = line.getPoints()
        assertEquals(0.0, points[0].getX())
        assertEquals(0.0, points[0].getY())
        assertEquals(3.0, points[1].getX())
        assertEquals(4.0, points[1].getY())
    }
}