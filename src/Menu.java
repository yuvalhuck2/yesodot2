import java.util.Scanner;

public abstract class Menu {

    //field
    Scanner scanner = new Scanner(System.in);
    Data data;



    public void runLoginMenu (){
        boolean flag=true;
        while (flag) {
            showLoginMenu();
            String chosen = scanner.nextLine();
            switch (chosen) {
                case "1":
                    Login();
                    break;
                case "2":
                    boolean success = Register();
                    break;
                case "3":
                    flag= false;
                default:
                    System.out.println("Wrong input");
            }
        }
    }

    public abstract boolean Register();

    public abstract void Login();





    public void showLoginMenu(){
        System.out.println("Please enter the option you want:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");

    }
}
