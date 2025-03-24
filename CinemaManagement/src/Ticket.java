public class Ticket {
    int Row,Seat;
    double Price;
    Person person;

    public Ticket(int row, int seat, double price, Person person) {
        Row = row;
        Seat = seat;
        Price = price;
        this.person = person;
    }

    public int getRow() {
        return Row;
    }

    public void setRow(int row) {
        Row = row;
    }

    public int getSeat() {
        return Seat;
    }

    public void setSeat(int seat) {
        Seat = seat;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
