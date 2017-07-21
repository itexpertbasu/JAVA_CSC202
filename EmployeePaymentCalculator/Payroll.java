/*
 * SHRABANTI BASU
 * MIDTERM CSC 202
 * MARCH 31, 2017
 * THIS CLASS IS FOR PAYROLL. IT HAS FIEDS TO STORES BASE BAY AND HOURS.
 * IT CALCULATES THE REGULAR PAY, OVERTIME PAY FOR EMPLOYEES
 */
 

/**
 *
 * @author sb
 */
public class Payroll 
{
    private double hours;
    private double payRate;
    
    private static final double MAX_REGULAR_HOURS = 40.0;  //overtime hours limit
    
    /**
     * Constructor 
     */
    public Payroll(double p, double h)
    {
        payRate = p;
        hours = h;        
    }
    
    /**
     * sets hours
     */
    public void setHours(double hrs)
    {
        hours = hrs;
    }
    
    /**
     * sets hpay rate
     */
    public void setpayRate(double pay)
    {
        payRate = pay;
    }
    
    /**
     * returns hours
     */
    public double getHours()
    {
        return hours;
    }
    
    /**
     * returns payrate
     */
    public double getpayRate(double pay)
    {
        return payRate;
    }
    
    /**
     * returns total pay for regular hours which is less than or equal to 40.0
     */
    public double regularPay(double hrs, double pay)
    {
        return hrs * pay;
    }
    
    /**
     * returns total pay for overtime hours which is greater than 40.0
     */
    public double overtimePay(double hrs, double pay)            
    {
        double regular, overtime;
        regular = MAX_REGULAR_HOURS * pay;
        overtime = (hrs - MAX_REGULAR_HOURS) * (1.5 * pay);
        
        return regular + overtime;
    }
    
    
    
    
    
    
}
