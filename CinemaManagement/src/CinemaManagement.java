import java.util.Scanner;
public class CinemaManagement {
    static Scanner scanner = new Scanner(System.in);
    static int[][] Seats = new int[3][16]; // create Seats for representing seats
    static Ticket[][] Ticket_sold = new Ticket[50][]; // create Ticket_sold array for save sold ticket information

    public static void Main() {
        System.out.println("""
                ------------------------------------------------
                Please select an option
                  1) Buy a ticket
                  2) Cancel ticket
                  3) See seating plan
                  4) Find first seat available
                  5) Print tickets information and total price
                  6) Search ticket
                  7) Sort ticket by price
                  8) Exit
                ------------------------------------------------""");

        System.out.print("Select option : ");
        validator("option"); // call validator method for check validation
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                buy_ticket(); // call buy_ticket method
                Main(); // call Main method to re display menu
                break;
            case 2:
                cancel_ticket(); // call cancel_seat method
                Main();
                break;
            case 3:
                print_seating_area(); // call print_seating_area method
                Main();
                break;
            case 4:
                find_first_available(); // call find_first_available method
                Main();
                break;
            case 5:
                print_ticket_info(); // call print_ticket_info method
                Main();
                break;
            case 6:
                search_ticket(); // call search_ticket() method
                Main();
                break;
            case 7:
                sort_tickets(); //  call ort_tickets() method
                Main();
                break;
            case 8:
                break;
            default:
                System.out.println("Please select a correct option \n ");
                Main();
        }
    }

    private static void buy_ticket() {
        int row, seat;
        String Name, Surname, Email;
        double Price;
        System.out.print("\nEnter your row number (1,2,3) : ");
        validator("row number (1,2,3)"); //  call validator method for check validation
        row = scanner.nextInt();
        if (row > 0 && row <= 3) {
            System.out.print("Enter your seat number (1-16) : ");
            validator("seat number (1-16)"); // call validator method for check validation
            seat = scanner.nextInt();
            if (seat > 0 && (seat - 1) <= Seats[row - 1].length - 1) {
                if (Seats[row - 1][seat - 1] == 1) {
                    System.out.println("Sorry the seat is already booked try another seat \n");
                } else {
                    Seats[row - 1][seat - 1] = 1;

                    System.out.print("Enter Your name : ");
                    Name = scanner.next();
                    System.out.print("Enter your surname : ");
                    Surname = scanner.next();
                    System.out.print("Enter your Email address : ");
                    Email = scanner.next();
                    System.out.println("Booked successfully \n");

                    // create new person object with person details
                    Person person = new Person(Name, Surname, Email);

                    if (row == 1) {
                        Price = 12;
                    } else if (row == 2) {
                        Price = 10;
                    } else {
                        Price = 8;
                    }

                    // create new ticket object with ticket information
                    Ticket ticket = new Ticket(row, seat, Price, person);

                    for (int i = 0; i < Ticket_sold.length; i++) {
                        if (Ticket_sold[i] == null) {
                            Ticket_sold[i] = new Ticket[]{ticket};  // add ticket information ti Ticket_sold array
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Invalid seat please enter correct seat number \n");
            }

        } else {
            System.out.println("Invalid row please enter correct row number \n");
        }
    }

    private static void cancel_ticket() {
        int row, seat;
        System.out.print("\nEnter your row number (1,2,3) : ");
        validator("row number (1,2,3)");  //  call validator method for check validation
        row = scanner.nextInt();
        if (row > 0 && row <= 3) {
            System.out.print("Enter your seat number (1-16) : ");
            validator("seat number (1-16)");  //  call validator method for check validation
            seat = scanner.nextInt();
            if (seat > 0 && (seat - 1) <= Seats[row - 1].length - 1) {
                if (Seats[row - 1][seat - 1] == 0) {
                    System.out.println("Sorry the seat is not booked please try correct seat number \n");
                } else {
                    Seats[row - 1][seat - 1] = 0;
                    System.out.println("Cancelled successfully \n");

                    for (int i = 0; i < Ticket_sold.length; i++) {
                        if (Ticket_sold[i] != null) {
                            for (int j = 0; j < Ticket_sold[i].length; j++) {
                                if (Ticket_sold[i][j] != null && Ticket_sold[i][j].getRow() == row && Ticket_sold[i][j].getSeat() == seat) {
                                    Ticket_sold[i] = null; // remove ticket information from Ticket_sold array
                                    break;
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("\nInvalid seat please enter correct seat number \n ");
            }

        } else {
            System.out.println("\nInvalid row please enter correct row number \n ");
        }
    }

    private static void print_seating_area() {
        System.out.println();
        System.out.println("""
                ******************
                *     SCREEN     *
                ******************""");
        for (int[] row : Seats) {
            int count = 0;
            for (int seat : row) {
                count++;
                if (seat == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
                if (count == 8) {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
    private static void find_first_available() {
        for (int i = 0; i < Seats.length; i++) {
            for (int j = 0; j < Seats[i].length; j++) {
                if (Seats[i][j] == 0) {
                    System.out.println("\nFirst available seat is \nRow number : " + (i + 1) + " Seat number : " + (j + 1)+"\n");
                    return; // exit after find first available seat
                }
            }
        }
        System.out.println("No seat is available \n");
    }
    private static void search_ticket() {
        int row, seat;
        System.out.print("\nEnter your row number (1,2,3) : ");
        validator("row number");  // call validator method for check validation
        row = scanner.nextInt();
        if (row > 0 && row <= 3) {
            System.out.print("Enter your seat number (1-16) : ");
            validator("seat number");  // call validator method for check validation
            seat = scanner.nextInt();
            if (seat > 0 && (seat - 1) <= Seats[row - 1].length - 1) {
                if (Seats[row - 1][seat - 1] == 0) {
                    System.out.println("This seat is available \n ");
                } else {
                    for (int i = 0; i < Ticket_sold.length; i++) {
                        if (Ticket_sold[i] != null) {
                            for (int j = 0; j < Ticket_sold[i].length; j++) {
                                if (Ticket_sold[i][j] != null && Ticket_sold[i][j].getRow() == row && Ticket_sold[i][j].getSeat() == seat) {
                                    System.out.println("Ticket information");
                                    System.out.println("Row number : " + Ticket_sold[i][j].getRow() + " Seat number : " + Ticket_sold[i][j].getSeat());
                                    System.out.println("Name : " + Ticket_sold[i][j].getPerson().getName());
                                    System.out.println("Surname : " + Ticket_sold[i][j].getPerson().getSurname());
                                    System.out.println("Email : " + Ticket_sold[i][j].getPerson().getEmail());
                                    System.out.println("Price : £" + Ticket_sold[i][j].getPrice() + "\n");
                                    break;
                                }
                            }
                        }
                    }
                }

            } else {
                System.out.println("Invalid seat please enter correct seat number \n ");
            }

        } else {
            System.out.println("Invalid row please enter correct row number \n ");
        }
    }
    private static void print_ticket_info() {
        double Total_price = 0;
        System.out.println("\nInformation about ticket sold during the session \n ");
        for (int i = 0; i < Ticket_sold.length; i++) {
            if (Ticket_sold[i] != null) {
                for (int j = 0; j < Ticket_sold[i].length; j++) {
                    System.out.println("Row : " + Ticket_sold[i][j].getRow() + " Seat : " + Ticket_sold[i][j].getSeat());
                    System.out.println("Name : " + Ticket_sold[i][j].getPerson().getName());
                    System.out.println("Surname : " + Ticket_sold[i][j].getPerson().getSurname());
                    System.out.println("Email : " + Ticket_sold[i][j].getPerson().getEmail());
                    System.out.println("Price : £" + Ticket_sold[i][j].getPrice() + "\n");
                    Total_price += Ticket_sold[i][j].getPrice();
                }
            }
        }
        System.out.println("Total sales : £" + Total_price + "\n");
    }
    private static void sort_tickets() {
        boolean swapped = true;
        do {
            swapped = false;
            for (int i = 0; i < Ticket_sold.length - 1; i++) {
                if (Ticket_sold[i] != null && Ticket_sold[i + 1] != null && Ticket_sold[i][0].getPrice() > Ticket_sold[i + 1][0].getPrice()) {
                    // get swapped if they are in wrong order
                    Ticket[] temporary = Ticket_sold[i];
                    Ticket_sold[i] = Ticket_sold[i + 1];
                    Ticket_sold[i + 1] = temporary;
                    swapped = true;
                }
            }
        } while (swapped); // swapped continue until no swapped made

        for (int i = 0; i < Ticket_sold.length; i++) {
            if (Ticket_sold[i] != null) {
                for (int j = 0; j < Ticket_sold[i].length; j++) {
                    System.out.println("\nRow : " + Ticket_sold[i][j].getRow() + " Seat : " + Ticket_sold[i][j].getSeat());
                    System.out.println("Price : £" + Ticket_sold[i][j].getPrice());
                    System.out.println("Name : " + Ticket_sold[i][j].getPerson().getName());
                    System.out.println("Name : " + Ticket_sold[i][j].getPerson().getSurname());
                    System.out.println("Surname : " + Ticket_sold[i][j].getPerson().getEmail() + "\n");
                }
            }
        }
    }

    // the validator method created for checks validate user input
    private static void validator(String message){
        while (!scanner.hasNextInt()){ // i got this idea from  this website https://www.javatpoint.com/post/java-scanner-hasnextint-method#:~:text=The%20hasNextInt()%20method%20of,differentiated%20depending%20on%20its%20parameter.
            System.out.print("Invalid input please chose correct "+message+" : ");
            scanner.next();
        }
    }

    public static void main(String[] args) {
        System.out.println(" Welcome to The London Lumiere ");
        Main(); // call Main method to display menu
    }
}