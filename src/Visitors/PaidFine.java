package Visitors;

import java.util.Calendar;

/**
 * Provides a structure for persisting fines paid by library visitors.
 * 
 * @author - Ryan Tytka
 */
public class PaidFine
{
    private int amount;
    private Calendar datePaid;

    /**
     * Default constructor. Date is initialized to current date.
     *
     * @param amount - The amount charged due to the fine.
     */
    public PaidFine(int amount, Calendar datePaid)
    {
        this.amount = amount;
        this.datePaid = datePaid;
    }

    /**
     * Getter for fine's paid date.
     *
     * @return The fine's paid date.
     */
    public Calendar getDatePaid()
    {
        return this.datePaid;
    }

    /**
     * Getter for fine's amount.
     *
     * @return The fine's amount.
     */
    public int getAmount()
    {
        return this.amount;
    }
}