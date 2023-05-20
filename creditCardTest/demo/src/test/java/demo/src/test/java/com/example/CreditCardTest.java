package demo.src.test.java.com.example;

import org.junit.Before;
import org.junit.Test;
import com.example.CreditCard;
import static org.junit.Assert.*;
import java.time.LocalDate;


public class CreditCardTest {
    CreditCard creditCard;

    @Before
    public void setup() {
        creditCard = new CreditCard("1234567890123456", "John Doe", "12/25", 123, 1000.0);
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
        assertEquals("12/25", creditCard.getExpirationDate());
    }

    @Test
    public void testGetCvv() {
        assertEquals(123, creditCard.getCvv());
    }

    @Test
    public void testGetBalance() {
        assertEquals(1000.0, creditCard.getBalance(), 0.0);
    }

    @Test
    public void testGetTransactions() {
        assertEquals(0, creditCard.getTransactions().size());
    }

    @Test
    public void testGetMovements() {
        assertEquals(0, creditCard.getMovements().size());
    }

    @Test
    public void testMakeTransaction() {
        creditCard.makeTransaction(100.0);
        assertEquals(900.0, creditCard.getBalance(), 0.0);
        assertEquals(1, creditCard.getTransactions().size());
        assertEquals(1, creditCard.getMovements().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testMakeTransactionWithNegativeAmount() {
        creditCard.makeTransaction(-100.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testMakeTransactionWithInsufficientFunds() {
        creditCard.makeTransaction(1001.0);
    }

    @Test
    public void testMakePayment() {
        creditCard.makeTransaction(100.0);
        creditCard.makePayment(50.0);
        assertEquals(950.0, creditCard.getBalance(), 0.0);
        assertEquals(2, creditCard.getTransactions().size());
        assertEquals(2, creditCard.getMovements().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testMakePaymentWithNegativeAmount() {
        creditCard.makePayment(-100.0);
    }

    @Test
    public void testAddFunds() {
        creditCard.addFunds(100.0);
        assertEquals(1100.0, creditCard.getBalance(), 0.0);
        assertEquals(0, creditCard.getTransactions().size());
        assertEquals(1, creditCard.getMovements().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddFundsNegativeAmount() {
        creditCard.addFunds(-100.0);
    }
    
}
