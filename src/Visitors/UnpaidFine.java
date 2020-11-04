package Visitors;

import java.util.Calendar;
import java.util.Date;

/**
 * Provides a structure for persisting fines accumulated by library visitors.
 *
 * @author Ryan Tytka
 */
public class UnpaidFine
{
    private double amount;
    private Date dateAccumulated;

    /**
     * Default constructor. Date is initialized to current date.
     *
     * @param amount - The amount charged due to the fine.
     */
    public UnpaidFine(double amount, Date dateAccumulated)
    {
        this.amount = amount;
        this.dateAccumulated = dateAccumulated;
    }

    /**
     * Getter for the fine's paid date.
     *
     * @return The fine's paid date.
     */
    public Date getDateAccumulated()
    {
        return this.dateAccumulated;
    }

    /**
     * Getter for the fine's amount.
     *
     * @return The fine's amount.
     */
    public double getAmount()
    {
        return this.amount;
    }
}