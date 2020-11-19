package State;

import java.util.List;

/**
 * Interface for the Library State
 *
 * @author Yug Patel - ydp4388
 */
public interface LibraryState {

    /**
     * Method to start the Visit.
     * @param visitorId - visitor id.
     */
    void startVisit(Integer visitorId);

    /**
     * Method to end the Visit.
     * @param visitorId - visitor id.
     */
    void endVisit(Integer visitorId);
    void borrowBook(List<Integer> books, int visitorID);
    void returnBooks (int visitorID, List<Integer> bookId);
}
