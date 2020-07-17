package unit.com.trailblazers.freewheelers.service;

import com.trailblazers.freewheelers.service.TaxCalculator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class TaxCalculatorTest {
    private TaxCalculator taxCalculator;
    private BigDecimal price = new BigDecimal(10.00);

    @Before
    public void setup()
    {
        taxCalculator = new TaxCalculator();
    }

    @Test
    public void testCalculatedVATForIncludedCountries() throws Exception {
        assertVATAmountEquals(1.8, price, "United Kingdom");
        assertVATAmountEquals(1.8, price, "Ireland");
    }

    @Test
    public void testCalculatedVATForExcludedCountry() throws Exception {
        assertVATAmountEquals(0, price, "France");
    }

    @Test
    public void testCalculatedDutyForIncludedCountries() throws Exception {
        assertDutyAmountEquals(0.7, price, "France");
        assertDutyAmountEquals(0.54, price, "Germany");
    }

    @Test
    public void testCalculatedDutyForExcludedCountries() throws Exception {
        assertDutyAmountEquals(0, price, "United Kingdom");
    }

    @Test
    public void testStringOutputIsToTwoDecimalPlaces() throws Exception {
        assertEquals("1.80",
                taxCalculator.calculateTaxOnItem(price, "United Kingdom").toString());
    }

    @Test
    public void testDutyAndVATTogether() throws Exception {
        assertTaxAmountEquals(0.54, price, "Germany");
        assertTaxAmountEquals(0.7, price, "France");
        assertTaxAmountEquals(1.8, price, "Ireland");
        assertTaxAmountEquals(1.8, price, "United Kingdom");
    }

    @Test
    public void testDutyTaxForCountriesOtherThanUK() throws Exception {
        assertTaxAmountEquals(0.5, price, "Austria");
        assertTaxAmountEquals(0.5, price, "Poland");
        assertTaxAmountEquals(0.5, price, "Switzerland");
    }

    private void assertVATAmountEquals(double amount, BigDecimal price, String country) {
        assertEquals(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP),
                taxCalculator.calculateVATOnItem(price, country));
    }

    private void assertDutyAmountEquals(double amount, BigDecimal price, String country) {
        assertEquals(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP),
                taxCalculator.calculateDutyOnItem(price, country));
    }

    private void assertTaxAmountEquals(double amount, BigDecimal price, String country) {
        assertEquals(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP),
                taxCalculator.calculateTaxOnItem(price, country));
    }
}
