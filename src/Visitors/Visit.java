package Visitors;

import java.util.Calendar;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class Visit
{
  private Calendar startDateTime;
  private Calendar endDateTime;
  private String visitorID;

  /**
   * main constructor for a visit.
   * @param startTime start time for visit
   * @param visitorID visitor id for the person visiting
   */
  public Visit(Calendar startTime, String visitorID){
    this.startDateTime = startTime;
    this.visitorID = visitorID;
  }

  /**
   * sets the end time for the visit
   * @param endTime the end time for when the visitor leaves
   */
  public void endVisit(Calendar endTime){
    this.endDateTime = endTime;
  }

}
