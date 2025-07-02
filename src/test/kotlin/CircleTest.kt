import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.math.PI

class CircleTest {

    @Test
    fun testConstructorWithValidCircle() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 5.0, 5.0)

        assertEquals(0.0, circle.getCenter().getX())
        assertEquals(0.0, circle.getCenter().getY())
        assertEquals(5.0, circle.getRadiusX())
        assertEquals(5.0, circle.getRadiusY())
    }

    @Test
    fun testConstructorWithUnequalRadii() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Circle(center, 3.0, 4.0)
        }
        assertEquals("Circle must have equal X and Y radii", exception.message)
    }

    @Test
    fun testConstructorWithNegativeRadii() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Circle(center, -2.0, -2.0)
        }
        assertEquals("Circle radii must be positive", exception.message)
    }

    @Test
    fun testConstructorWithZeroRadii() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Circle(center, 0.0, 0.0)
        }
        assertEquals("Circle radii must be positive", exception.message)
    }

    @Test
    fun testConstructorWithOneNegativeRadius() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Circle(center, 5.0, -3.0)
        }
        assertEquals("Circle must have equal X and Y radii", exception.message)
    }

    @Test
    fun testConstructorWithOneZeroRadius() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Circle(center, 4.0, 0.0)
        }
        assertEquals("Circle must have equal X and Y radii", exception.message)
    }

    @Test
    fun testConstructorWithSlightlyUnequalRadii() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Circle(center, 2.0, 2.0001)
        }
        assertEquals("Circle must have equal X and Y radii", exception.message)
    }

    @Test
    fun testGetAreaUnitCircle() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 1.0, 1.0)

        assertEquals(PI, circle.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaSmallCircle() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 2.0, 2.0)

        assertEquals(PI * 4.0, circle.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaLargeCircle() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 10.0, 10.0)

        assertEquals(PI * 100.0, circle.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaDecimalRadius() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 2.5, 2.5)

        assertEquals(PI * 6.25, circle.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaVerySmallRadius() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 0.1, 0.1)

        assertEquals(PI * 0.01, circle.getArea(), 0.000001)
    }

    @Test
    fun testGetAreaVeryLargeRadius() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 100.0, 100.0)

        assertEquals(PI * 10000.0, circle.getArea(), 0.01)
    }

    @Test
    fun testGetCenterReturnsCorrectCenter() {
        val center = Point(3.0, 4.0)
        val circle = Circle(center, 5.0, 5.0)

        val returnedCenter = circle.getCenter()
        assertEquals(3.0, returnedCenter.getX())
        assertEquals(4.0, returnedCenter.getY())
    }

    @Test
    fun testGetCenterWithNegativeCoordinates() {
        val center = Point(-2.0, -3.0)
        val circle = Circle(center, 1.0, 1.0)

        val returnedCenter = circle.getCenter()
        assertEquals(-2.0, returnedCenter.getX())
        assertEquals(-3.0, returnedCenter.getY())
    }

    @Test
    fun testGetRadii() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 7.5, 7.5)

        assertEquals(7.5, circle.getRadiusX())
        assertEquals(7.5, circle.getRadiusY())
    }

    @Test
    fun testInheritedMoveFunction() {
        val center = Point(1.0, 2.0)
        val circle = Circle(center, 3.0, 3.0)

        val originalArea = circle.getArea()
        circle.move(4.0, -1.0)

        val movedCenter = circle.getCenter()
        assertEquals(5.0, movedCenter.getX())
        assertEquals(1.0, movedCenter.getY())

        // Area should remain the same after translation
        assertEquals(originalArea, circle.getArea(), 0.0001)

        // Radii should remain the same
        assertEquals(3.0, circle.getRadiusX())
        assertEquals(3.0, circle.getRadiusY())
    }

    @Test
    fun testMultipleMoves() {
        val center = Point(0.0, 0.0)
        val circle = Circle(center, 2.0, 2.0)

        circle.move(1.0, 1.0)
        circle.move(-0.5, 2.0)
        circle.move(2.0, -1.5)

        val finalCenter = circle.getCenter()
        assertEquals(2.5, finalCenter.getX())
        assertEquals(1.5, finalCenter.getY())
    }

    @Test
    fun testMoveWithZeroDeltas() {
        val center = Point(5.0, 7.0)
        val circle = Circle(center, 1.0, 1.0)

        circle.move(0.0, 0.0)

        val centerAfterMove = circle.getCenter()
        assertEquals(5.0, centerAfterMove.getX())
        assertEquals(7.0, centerAfterMove.getY())
    }

    @Test
    fun testMoveWithDecimalDeltas() {
        val center = Point(1.1, 2.2)
        val circle = Circle(center, 1.5, 1.5)

        circle.move(0.7, -1.3)

        val centerAfterMove = circle.getCenter()
        assertEquals(1.8, centerAfterMove.getX(), 0.0001)
        assertEquals(0.9, centerAfterMove.getY(), 0.0001)
    }

    @Test
    fun testAreaCalculationFormula() {
        // Test that area = π * r²
        val center = Point(0.0, 0.0)
        val radius = 3.0
        val circle = Circle(center, radius, radius)

        val expectedArea = PI * radius * radius
        assertEquals(expectedArea, circle.getArea(), 0.0001)
    }

    @Test
    fun testAreaPreservationAfterMovement() {
        val center = Point(10.0, 20.0)
        val circle = Circle(center, 4.0, 4.0)

        val originalArea = circle.getArea()

        // Multiple moves
        circle.move(-5.0, 3.0)
        assertEquals(originalArea, circle.getArea(), 0.0001)

        circle.move(2.0, -8.0)
        assertEquals(originalArea, circle.getArea(), 0.0001)

        circle.move(-1.0, 1.0)
        assertEquals(originalArea, circle.getArea(), 0.0001)
    }

    @Test
    fun testCircleWithDecimalCoordinates() {
        val center = Point(1.234, 5.678)
        val circle = Circle(center, 2.5, 2.5)

        assertEquals(1.234, circle.getCenter().getX(), 0.0001)
        assertEquals(5.678, circle.getCenter().getY(), 0.0001)
        assertEquals(PI * 6.25, circle.getArea(), 0.0001)
    }

    @Test
    fun testInheritedFunctionalityIntegration() {
        val circle = Circle(Point(0.0, 0.0), 3.0, 3.0)

        // Test inherited move
        circle.move(1.0, 1.0)

        // Test inherited getCenter
        val center = circle.getCenter()
        assertEquals(1.0, center.getX())
        assertEquals(1.0, center.getY())

        // Test inherited getRadiusX and getRadiusY
        assertEquals(3.0, circle.getRadiusX())
        assertEquals(3.0, circle.getRadiusY())

        // Test Circle-specific functionality
        assertEquals(PI * 9.0, circle.getArea(), 0.0001)
    }

    @Test
    fun testComplexOperations() {
        val center = Point(-2.0, 3.0)
        val circle = Circle(center, 2.5, 2.5)

        // Initial state
        assertEquals(-2.0, circle.getCenter().getX())
        assertEquals(3.0, circle.getCenter().getY())
        assertEquals(PI * 6.25, circle.getArea(), 0.0001)

        // Move and verify
        circle.move(4.5, -1.5)
        assertEquals(2.5, circle.getCenter().getX())
        assertEquals(1.5, circle.getCenter().getY())
        assertEquals(PI * 6.25, circle.getArea(), 0.0001)
    }

    @Test
    fun testEdgeCaseVeryPreciseRadii() {
        val center = Point(0.0, 0.0)
        val radius = 1.0000000001
        val circle = Circle(center, radius, radius)

        assertEquals(PI * radius * radius, circle.getArea(), 0.0000001)
    }
}