package demo.src.test.java.com.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.example.CreditCard;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CreditCardTest {
    CreditCard creditCard;

    @Before
    public void setup() {
        creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", 123, 1000.0);
    }

    @Test
    public void testMakeTransaction()  {
        CreditCard card = new CreditCard("1234567890123456", "John Doe", "12/25", 123, 1000.0);
        card.makeTransaction(500.0);
        Assert.assertEquals(500.0, card.getBalance(), 0.001);
        Assert.assertEquals(-500.0, card.getTransactionTotal(), 0.001);
        Assert.assertEquals(1, card.getTransactions().size());
        Assert.assertEquals("Transaction: -500.00", card.getMovements().get(0));
    }

    @Test
    public void testMakePayment() {
        CreditCard card = new CreditCard("1234567890123456", "John Doe", "12/25", 123, 1000.0);
        card.makePayment(500.0);
        Assert.assertEquals(1500.0, card.getBalance(), 0.001);
        Assert.assertEquals(500.0, card.getTransactionTotal(), 0.001);
        Assert.assertEquals(1, card.getTransactions().size());
        Assert.assertEquals("Payment: +500.00", card.getMovements().get(0));
    }

    @Test
    public void testInvalidTransaction() {
        CreditCard card = new CreditCard("1234567890123456", "John Doe", "12/25", 123, 1000.0);
        card.makeTransaction(0.0);
    }

    @Test
    public void testInsufficientBalance() {
        CreditCard card = new CreditCard("1234567890123456", "John Doe", "12/25", 123, 1000.0);
        card.makeTransaction(1500.0);
    }

    @Test
    public void testGetCardNumber() {
        assertEquals("1234567890123456", creditCard.getCardNumber());
    }

    @Test
    public void testGetCardHolderName() {
        assertEquals("John Doe", creditCard.getCardHolderName());
    }

    @Test
    public void testGetExpirationDate() {
        assertEquals(LocalDate.of(2025, 12, 1), creditCard.getExpirationDate());
    }

    @Test
    public void testGetCvv() {
        assertEquals(123, creditCard.getCvv());
    }

    @Test
    public void testGetBalance() {
        assertEquals(1000.0, creditCard.getBalance(), 0.001);
    }

    @Test
    public void testGetTransactionTotal() {
        creditCard.makeTransaction(500.0);
        creditCard.makePayment(200.0);
        creditCard.makeTransaction(100.0);
        assertEquals(-400.0, creditCard.getTransactionTotal(), 0.001);
    }

    @Test
    public void testAddFunds() {
        creditCard.addFunds(500.0);
        assertEquals(1500.0, creditCard.getBalance(), 0.001);
        assertEquals(1, creditCard.getTransactions().size());
        assertEquals("Funds added: +500.00", creditCard.getMovements().get(0));
    }

    @Test
    public void testInvalidAddFunds()  {
        creditCard.addFunds(0.0);
    }

    @Test
    public void testGetTransactions() {
        creditCard.makeTransaction(500.0);
        creditCard.makePayment(200.0);
        creditCard.makeTransaction(100.0);
        assertEquals(3, creditCard.getTransactions().size());
        assertEquals("Transaction: -500.00", creditCard.getTransactions().get(0));
        assertEquals("Payment: +200.00", creditCard.getTransactions().get(1));
        assertEquals("Transaction: -100.00", creditCard.getTransactions().get(2));
    }

    @Test
    public void testGetMovements()  {
        creditCard.makeTransaction(500.0);
        creditCard.makePayment(200.0);
        creditCard.makeTransaction(100.0);
        assertEquals(3, creditCard.getMovements().size());
        assertEquals("Transaction: -500.00", creditCard.getMovements().get(0));
        assertEquals("Payment: +200.00", creditCard.getMovements().get(1));
        assertEquals("Transaction: -100.00", creditCard.getMovements().get(2));
    }

    @Test
    public void testMakePaymentValid() {
        CreditCard card = new CreditCard("1111222233334444", "John Doe", "01/25", 123, 1000.0);
        card.makePayment(500.0);
        assertEquals(1500.0, card.getBalance(), 0.001);
    }

    @Test
    public void testAddFundsInvalidAmount() {
        CreditCard card = new CreditCard("1111222233334444", "John Doe", "01/25", 123, 1000.0);
        assertThrows(IllegalStateException.class, () -> card.addFunds(0.0));
    }

    @Test
    public void testAddFundsValid() {
        CreditCard card = new CreditCard("1111222233334444", "John Doe", "01/25", 123, 1000.0);
        card.addFunds(500.0);
        assertEquals(1500.0, card.getBalance(), 0.001);
    }

    @Test
    public void testIsExpiredTrue() {
        CreditCard card = new CreditCard("1111222233334444", "John Doe", "01/22", 123, 1000.0);
        assertTrue(card.isExpired());
    }

    @Test
    public void testIsExpiredFalse() {
        CreditCard card = new CreditCard("1111222233334444", "John Doe", "01/25", 123, 1000.0);
        assertFalse(card.isExpired());
    }
}
