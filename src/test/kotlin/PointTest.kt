import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows


class PointTest {

    @Test
    fun testConstructorAndGetters() {
        val point = Point(3.5, -2.7)
        assertEquals(3.5, point.getX(), 0.001)
        assertEquals(-2.7, point.getY(), 0.001)
    }

    @Test
    fun testConstructorWithZeroValues() {
        val point = Point(0.0, 0.0)
        assertEquals(0.0, point.getX())
        assertEquals(0.0, point.getY())
    }

    @Test
    fun testConstructorWithLargeValues() {
        val point = Point(Double.MAX_VALUE, Double.MIN_VALUE)
        assertEquals(Double.MAX_VALUE, point.getX())
        assertEquals(Double.MIN_VALUE, point.getY())
    }

    @Test
    fun testMove() {
        val point = Point(1.0, 2.0)
        point.move(3.0, -1.5)

        assertEquals(4.0, point.getX(), 0.001)
        assertEquals(0.5, point.getY(), 0.001)
    }

    @Test
    fun testMoveWithZeroDeltas() {
        val point = Point(5.0, -3.0)
        point.move(0.0, 0.0)

        assertEquals(5.0, point.getX())
        assertEquals(-3.0, point.getY())
    }

    @Test
    fun testMoveWithNegativeDeltas() {
        val point = Point(10.0, 20.0)
        point.move(-5.0, -15.0)

        assertEquals(5.0, point.getX())
        assertEquals(5.0, point.getY())
    }

    @Test
    fun testMultipleMoves() {
        val point = Point(0.0, 0.0)
        point.move(1.0, 2.0)
        point.move(3.0, -1.0)
        point.move(-2.0, 0.5)

        assertEquals(2.0, point.getX(), 0.001)
        assertEquals(1.5, point.getY(), 0.001)
    }

    @Test
    fun testClone() {
        val original = Point(7.5, -4.2)
        val cloned = original.clone()

        assertEquals(original.getX(), cloned.getX())
        assertEquals(original.getY(), cloned.getY())
    }

    @Test
    fun testCloneIndependence() {
        val original = Point(1.0, 2.0)
        val cloned = original.clone()

        // Modify original
        original.move(5.0, 5.0)

        // Cloned should be unchanged
        assertEquals(1.0, cloned.getX())
        assertEquals(2.0, cloned.getY())
        assertEquals(6.0, original.getX())
        assertEquals(7.0, original.getY())
    }

    @Test
    fun testCloneAfterMove() {
        val point = Point(0.0, 0.0)
        point.move(3.0, 4.0)
        val cloned = point.clone()

        assertEquals(3.0, cloned.getX())
        assertEquals(4.0, cloned.getY())
    }

    @Test
    fun testMoveWithDoubleEdgeCases() {
        val point = Point(0.0, 0.0)

        // Test with very small values
        point.move(Double.MIN_VALUE, -Double.MIN_VALUE)
        assertEquals(Double.MIN_VALUE, point.getX())
        assertEquals(-Double.MIN_VALUE, point.getY())
    }

    @Test
    fun testGettersAfterMultipleOperations() {
        val point = Point(1.1, 2.2)

        // Multiple operations
        point.move(0.9, -1.2)
        val cloned = point.clone()
        point.move(1.0, 1.0)

        // Original point should be modified
        assertEquals(3.0, point.getX(), 0.001)
        assertEquals(2.0, point.getY(), 0.001)

        // Cloned point should have intermediate values
        assertEquals(2.0, cloned.getX(), 0.001)
        assertEquals(1.0, cloned.getY(), 0.001)
    }

    @Test
    fun testConstructorWithInfiniteX() {
        assertThrows<IllegalArgumentException> {
            Point(Double.POSITIVE_INFINITY, 1.0)
        }
        assertThrows<IllegalArgumentException> {
            Point(Double.NEGATIVE_INFINITY, 1.0)
        }
    }

    @Test
    fun testConstructorWithInfiniteY() {
        assertThrows<IllegalArgumentException> {
            Point(1.0, Double.POSITIVE_INFINITY)
        }
        assertThrows<IllegalArgumentException> {
            Point(1.0, Double.NEGATIVE_INFINITY)
        }
    }

    @Test
    fun testMoveWithInfiniteX() {
        val point = Point(1.0, 1.0)
        assertThrows<IllegalArgumentException> {
            point.move(Double.POSITIVE_INFINITY, 1.0)
        }
        assertThrows<IllegalArgumentException> {
            point.move(Double.NEGATIVE_INFINITY, 1.0)
        }
    }

    @Test
    fun testMoveWithInfiniteY() {
        val point = Point(1.0, 1.0)
        assertThrows<IllegalArgumentException> {
            point.move(1.0, Double.POSITIVE_INFINITY)
        }
        assertThrows<IllegalArgumentException> {
            point.move(1.0, Double.NEGATIVE_INFINITY)
        }
    }
}
