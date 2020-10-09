package Sort;

import Books.Book;

import java.util.List;
import java.util.Comparator;

public class Sort //implements SortOrder
{

    public Sort() {}

    private int sortType; // 1=title, 2=publish date, 3=total # copies, 4=available # copies

    public Sort(int sortType){
        this.sortType = sortType;
    }

    /**
     * Sorts a list of books by title.
     *
     * @param bookList - A list of books to be sorted.
     */
    public void sort(List<Book> bookList)
    {
        bookList.sort(BookTitleComparator);
    }

    /**
     * Implements a Comparator that compares books by title. Sorts the list in ascending order. Numbers first, followed
     * by alphabetically.
     */
    private static Comparator<Book> BookTitleComparator = new Comparator<Book>()
    {
        /**
         * Compares two books by their titles.
         *
         * @param b1 - A book to compare.
         * @param b2 - Another book to compare.
         * @return A negative, zero, or a positive integer if b1's title is alphanumerically less than, equal to, or
         * greater than b2's title.
         */
        @Override
        public int compare(Book b1, Book b2)
        {
            String BookTitle1 = b1.getTitle().toUpperCase();
            String BookTitle2 = b2.getTitle().toUpperCase();
            return BookTitle1.compareTo(BookTitle2);
        }
    };




    /**
     * Main method for testing.
     public static void main(String [] args)
     {
        Book b1 = new Book("0123456789","cBook", null, "apub",
        "02-02-2501", 441);
        Book b2 = new Book("1234567890", "aBook", null, "apub",
        "02-02-2501", 441);
        Book b3 = new Book("2345678901", "bBook", null, "apub",
        "02-02-2501", 441);
        
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(b1);
        bookList.add(b2);
        bookList.add(b3);
        
        ByTitle byTitle = new ByTitle();
        byTitle.sort(bookList);
        
        // Test 1
        if (bookList.get(0).equals(b2) &&
        bookList.get(1).equals(b3) && bookList.get(2).equals(b1))
        System.out.println("Test 1 PASSED");
        else {
            System.out.println("Test 1 FAILED");
            System.out.println("Got: " + bookList.get(0).getTitle() + " Expected: " + b2.getTitle());
            System.out.println("Got: " + bookList.get(1).getTitle() + " Expected: " + b3.getTitle());
            System.out.println("Got: " + bookList.get(2).getTitle() + " Expected: " + b1.getTitle());
        }
    }
    */
}