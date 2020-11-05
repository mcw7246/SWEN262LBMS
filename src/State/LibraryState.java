package State;

import java.util.List;

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
