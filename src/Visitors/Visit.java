package Visitors;

import java.util.Calendar;

/**
 * @author Mikayla Wishart - mcw7246
 */
public class Visit
{
  private Calendar startTime;
  private Calendar endDateTime;
  private Integer visitorID;
  private Integer visitLength;

  /**
   * main constructor for a visit.
   * @param startTime start time for visit
   * @param visitorID visitor id for the person visiting
   * @param endDateTime Date and time of end of the visit
   */
  public Visit(Calendar startTime, Integer visitorID, Calendar endDateTime){
    this.startTime = startTime;
    this.visitorID = visitorID;
    this.endDateTime = endDateTime;
    visitLength = endDateTime.get(Calendar.HOUR) - endDateTime.get(Calendar.HOUR);
  }

  public Integer getVisitLength() {
    return visitLength;
  }
}
