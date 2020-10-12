package State;

import java.util.List;

public interface LibraryState {

    /**
     * Method to start the Visit.
     * @param visitorId - visitor id.
     */
    public void startVisit(Integer visitorId);

    /**
     * Method to end the Visit.
     * @param visitorId - visitor id.
     */
    public void endVisit(Integer visitorId);
    public void borrowBook(Integer visitorId, List<Integer> bookId);
}
