import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.math.PI

class EllipseTest {

    @Test
    fun testConstructorWithValidEllipse() {
        val center = Point(2.0, 3.0)
        val ellipse = Ellipse(center, 4.0, 5.0)

        assertEquals(2.0, ellipse.getCenter().getX())
        assertEquals(3.0, ellipse.getCenter().getY())
        assertEquals(4.0, ellipse.getRadiusX())
        assertEquals(5.0, ellipse.getRadiusY())
    }

    @Test
    fun testConstructorWithEqualRadii() {
        // Should work fine - circle is a special case of ellipse
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 3.0, 3.0)

        assertEquals(3.0, ellipse.getRadiusX())
        assertEquals(3.0, ellipse.getRadiusY())
    }

    @Test
    fun testConstructorWithNegativeRadiusX() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Ellipse(center, -2.0, 3.0)
        }
        assertEquals("Ellipse radii must be positive", exception.message)
    }

    @Test
    fun testConstructorWithNegativeRadiusY() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Ellipse(center, 3.0, -2.0)
        }
        assertEquals("Ellipse radii must be positive", exception.message)
    }

    @Test
    fun testConstructorWithBothNegativeRadii() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Ellipse(center, -3.0, -2.0)
        }
        assertEquals("Ellipse radii must be positive", exception.message)
    }

    @Test
    fun testConstructorWithZeroRadiusX() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Ellipse(center, 0.0, 3.0)
        }
        assertEquals("Ellipse radii must be positive", exception.message)
    }

    @Test
    fun testConstructorWithZeroRadiusY() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Ellipse(center, 3.0, 0.0)
        }
        assertEquals("Ellipse radii must be positive", exception.message)
    }

    @Test
    fun testConstructorWithBothZeroRadii() {
        val center = Point(0.0, 0.0)

        val exception = assertThrows<IllegalArgumentException> {
            Ellipse(center, 0.0, 0.0)
        }
        assertEquals("Ellipse radii must be positive", exception.message)
    }

    @Test
    fun testGetAreaBasicEllipse() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 2.0, 3.0)

        assertEquals(PI * 2.0 * 3.0, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaCircleAsEllipse() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 4.0, 4.0)

        assertEquals(PI * 16.0, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaUnitEllipse() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 1.0, 1.0)

        assertEquals(PI, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaVeryFlatEllipse() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 10.0, 0.1)

        assertEquals(PI * 10.0 * 0.1, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaVeryTallEllipse() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 0.1, 10.0)

        assertEquals(PI * 0.1 * 10.0, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaDecimalRadii() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 2.5, 1.5)

        assertEquals(PI * 2.5 * 1.5, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testGetAreaVerySmallRadii() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 0.01, 0.02)

        assertEquals(PI * 0.01 * 0.02, ellipse.getArea(), 0.0000001)
    }

    @Test
    fun testGetAreaVeryLargeRadii() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 100.0, 200.0)

        assertEquals(PI * 20000.0, ellipse.getArea(), 0.01)
    }

    @Test
    fun testGetCenterReturnsCorrectCenter() {
        val center = Point(5.0, -3.0)
        val ellipse = Ellipse(center, 2.0, 4.0)

        val returnedCenter = ellipse.getCenter()
        assertEquals(5.0, returnedCenter.getX())
        assertEquals(-3.0, returnedCenter.getY())
    }

    @Test
    fun testGetCenterWithDecimalCoordinates() {
        val center = Point(1.234, -5.678)
        val ellipse = Ellipse(center, 1.0, 2.0)

        val returnedCenter = ellipse.getCenter()
        assertEquals(1.234, returnedCenter.getX(), 0.0001)
        assertEquals(-5.678, returnedCenter.getY(), 0.0001)
    }

    @Test
    fun testGetRadii() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 3.5, 7.2)

        assertEquals(3.5, ellipse.getRadiusX(), 0.0001)
        assertEquals(7.2, ellipse.getRadiusY(), 0.0001)
    }

    @Test
    fun testInheritedMoveFunction() {
        val center = Point(1.0, 2.0)
        val ellipse = Ellipse(center, 3.0, 4.0)

        val originalArea = ellipse.getArea()
        ellipse.move(5.0, -3.0)

        val movedCenter = ellipse.getCenter()
        assertEquals(6.0, movedCenter.getX())
        assertEquals(-1.0, movedCenter.getY())

        // Area should remain the same after translation
        assertEquals(originalArea, ellipse.getArea(), 0.0001)

        // Radii should remain the same
        assertEquals(3.0, ellipse.getRadiusX())
        assertEquals(4.0, ellipse.getRadiusY())
    }

    @Test
    fun testMultipleMoves() {
        val center = Point(0.0, 0.0)
        val ellipse = Ellipse(center, 1.0, 2.0)

        ellipse.move(2.0, 3.0)
        ellipse.move(-1.0, 1.0)
        ellipse.move(0.5, -2.5)

        val finalCenter = ellipse.getCenter()
        assertEquals(1.5, finalCenter.getX())
        assertEquals(1.5, finalCenter.getY())
    }

    @Test
    fun testMoveWithZeroDeltas() {
        val center = Point(10.0, -5.0)
        val ellipse = Ellipse(center, 2.0, 3.0)

        ellipse.move(0.0, 0.0)

        val centerAfterMove = ellipse.getCenter()
        assertEquals(10.0, centerAfterMove.getX())
        assertEquals(-5.0, centerAfterMove.getY())
    }

    @Test
    fun testMoveWithDecimalDeltas() {
        val center = Point(2.5, 3.7)
        val ellipse = Ellipse(center, 1.2, 1.8)

        ellipse.move(-0.3, 1.1)

        val centerAfterMove = ellipse.getCenter()
        assertEquals(2.2, centerAfterMove.getX(), 0.0001)
        assertEquals(4.8, centerAfterMove.getY(), 0.0001)
    }

    @Test
    fun testAreaCalculationFormula() {
        // Test that area = π * radiusX * radiusY
        val center = Point(0.0, 0.0)
        val radiusX = 5.0
        val radiusY = 3.0
        val ellipse = Ellipse(center, radiusX, radiusY)

        val expectedArea = PI * radiusX * radiusY
        assertEquals(expectedArea, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testAreaPreservationAfterMovement() {
        val center = Point(-2.0, 8.0)
        val ellipse = Ellipse(center, 3.5, 2.1)

        val originalArea = ellipse.getArea()

        // Multiple moves
        ellipse.move(1.0, -3.0)
        assertEquals(originalArea, ellipse.getArea(), 0.0001)

        ellipse.move(-4.0, 2.0)
        assertEquals(originalArea, ellipse.getArea(), 0.0001)

        ellipse.move(0.5, 0.5)
        assertEquals(originalArea, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testEllipseVsCircleArea() {
        val center = Point(0.0, 0.0)

        // Circle with radius 3
        val circle = Ellipse(center, 3.0, 3.0)
        val circleArea = PI * 9.0
        assertEquals(circleArea, circle.getArea(), 0.0001)

        // Ellipse with same area but different radii
        val ellipse = Ellipse(center, 1.5, 6.0)
        assertEquals(circleArea, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testInheritedFunctionalityIntegration() {
        val ellipse = Ellipse(Point(0.0, 0.0), 2.0, 4.0)

        // Test inherited move
        ellipse.move(1.0, 1.0)

        // Test inherited getCenter
        val center = ellipse.getCenter()
        assertEquals(1.0, center.getX())
        assertEquals(1.0, center.getY())

        // Test inherited getRadiusX and getRadiusY
        assertEquals(2.0, ellipse.getRadiusX())
        assertEquals(4.0, ellipse.getRadiusY())

        // Test Ellipse-specific functionality
        assertEquals(PI * 8.0, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testComplexOperations() {
        val center = Point(3.0, -1.0)
        val ellipse = Ellipse(center, 1.5, 2.5)

        // Initial state
        assertEquals(3.0, ellipse.getCenter().getX())
        assertEquals(-1.0, ellipse.getCenter().getY())
        assertEquals(PI * 3.75, ellipse.getArea(), 0.0001)

        // Move and verify
        ellipse.move(-2.0, 4.0)
        assertEquals(1.0, ellipse.getCenter().getX())
        assertEquals(3.0, ellipse.getCenter().getY())
        assertEquals(PI * 3.75, ellipse.getArea(), 0.0001)
    }

    @Test
    fun testVeryPreciseRadii() {
        val center = Point(0.0, 0.0)
        val radiusX = 1.23456789
        val radiusY = 9.87654321
        val ellipse = Ellipse(center, radiusX, radiusY)

        assertEquals(PI * radiusX * radiusY, ellipse.getArea(), 0.0000001)
    }

    @Test
    fun testAsymmetricEllipses() {
        val center = Point(0.0, 0.0)

        // Horizontal ellipse (wider than tall)
        val horizontalEllipse = Ellipse(center, 10.0, 2.0)
        assertEquals(PI * 20.0, horizontalEllipse.getArea(), 0.0001)

        // Vertical ellipse (taller than wide)
        val verticalEllipse = Ellipse(center, 2.0, 10.0)
        assertEquals(PI * 20.0, verticalEllipse.getArea(), 0.0001)

        // Both should have same area but different shapes
        assertEquals(horizontalEllipse.getArea(), verticalEllipse.getArea(), 0.0001)
    }

    @Test
    fun testExtremeRatioEllipses() {
        val center = Point(0.0, 0.0)

        // Very flat ellipse
        val flatEllipse = Ellipse(center, 1000.0, 0.001)
        assertEquals(PI * 1.0, flatEllipse.getArea(), 0.0001)

        // Very tall ellipse
        val tallEllipse = Ellipse(center, 0.001, 1000.0)
        assertEquals(PI * 1.0, tallEllipse.getArea(), 0.0001)
    }
}