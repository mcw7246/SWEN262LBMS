package Visitors;

import java.util.Calendar;

/**
 * Provides a structure for persisting fines accumulated by library visitors.
 *
 * @author Ryan Tytka
 */
public class UnpaidFine
{
    private int amount;
    private Calendar dateAccumulated;

    /**
     * Default constructor. Date is initialized to current date.
     *
     * @param amount - The amount charged due to the fine.
     */
    public UnpaidFine(int amount, Calendar dateAccumulated)
    {
        this.amount = amount;
        this.dateAccumulated = dateAccumulated;
    }

    /**
     * Getter for the fine's paid date.
     *
     * @return The fine's paid date.
     */
    public Calendar getDateAccumulated()
    {
        return this.dateAccumulated;
    }

    /**
     * Getter for the fine's amount.
     *
     * @return The fine's amount.
     */
    public int getAmount()
    {
        return this.amount;
    }
}