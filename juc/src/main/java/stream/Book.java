package stream;

/**
 * @Author maqiuyue
 * @Date 2020/7/16 17:01
 * @description
 */

//chain = true表示链
//@Accessors(chain=true) //lombok插件，可以用于链式编程。 下述新建对象，可写为 book.setId(11).setBookName("java")
public class Book {

    //链接编程+流式计算
    private int id;
    private String bookName;
    private double price;

    public Book(){}

    public Book(int id, String bookName, double price) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public static void main(String[] args) {
        Book book = new Book();
        book.setId(11);
        book.setBookName("java");
        book.setPrice(33.5d);

        Book book2 = new Book();

    }
}
