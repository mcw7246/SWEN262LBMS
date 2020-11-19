package Visitors;

import java.util.Calendar;

/**
 * Class to hold the data for a Visit
 *
 * @author Mikayla Wishart - mcw7246
 * @author Yug Patel - ydp4388
 */
public class Visit
{
  private Integer startTime;
  private Integer endTime;
  private Calendar endDateTime;
  private Integer visitorID;
  private Integer visitLength;

  /**
   * main constructor for a visit.
   * @param startTime start time for visit
   * @param visitorID visitor id for the person visiting
   * @param endTime end time for the visit
   * @param endDateTime Date and time of end of the visit
   */
  public Visit(Integer startTime, Integer visitorID, Integer endTime,Calendar endDateTime){
    this.startTime = startTime;
    this.endTime = endTime;
    this.visitorID = visitorID;
    this.endDateTime = endDateTime;
    visitLength = endTime - startTime;
  }

  /**
   * Method to get the visit duration in hours(24 hr).
   * @return the length of the visit
   */
  public Integer getVisitLength() {
    return visitLength;
  }

  /**
   *
   * @return the day that the visit occurred on
   */
  public Integer getVisitDay(){
    return endDateTime.get(Calendar.DAY_OF_YEAR);
  }
}
