public class ProposerMenu extends Menu  {

    public void  ProposerMenu(){

    }



    public void Login() {
        boolean flag = true;
        User user;
        while(flag) {
            System.out.println("Enter your user name or B to go back:");
            String userName = scanner.nextLine();
            if (!userName.equals("B")) {
                user= data.LoginProposer(userName);
                if(user!=null)
                    runAfterLoginMenu(user);
                else
                    System.out.println("wrong user name");
            }
            else{
                flag= false;
            }
        }


    }

    private void runAfterLoginMenu(User user) {
    }

    @Override
    public boolean Register(){
        boolean flag = true;
        while(flag) {
            System.out.println("Enter your user name or B to go back:");
            String userName = scanner.nextLine();
            if (!userName.equals("B")) {

            }
            else{
                flag=false;
            }
        }
            System.out.println("Enter first name");
            String fName = scanner.nextLine();
            System.out.println("Enter last name");
            String lName = scanner.nextLine();
            System.out.println("Enter email");
            String email = scanner.nextLine();
            System.out.println("Enter Phone");
            String phone = scanner.nextLine();


    }

    private void printAfterLoginMenu (){
        System.out.println("1. Add project");
        System.out.println("2. Check Project's State");
        System.out.println("3. Back");
    }
}
