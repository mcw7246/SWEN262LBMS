package Visitors;

import java.util.Calendar;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class Visit
{
  private Calendar startTime;
  private Calendar endTime;
  private String visitorID;
  private Calendar date;

  /**
   * main constructor for a visit.
   * @param startTime start time for visit
   * @param visitorID visitor id for the person visiting
   * @param endTime end of the visit
   * @param date date of the visit
   */
  public Visit(Calendar startTime, String visitorID, Calendar endTime, Calendar date){
    this.startTime = startTime;
    this.visitorID = visitorID;
    this.endTime = endTime;
    this.date = date;
  }

}
