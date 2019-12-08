public class ProposerMenu extends Menu  {

    public void  ProposerMenu(){

    }



    public void Login() {
        boolean flag = true;
        Proposer user;
        while(flag) {
            System.out.println("Enter your user name or B to go back:");
            String userName = scanner.nextLine();
            if (!userName.equals("B")) {
                user= data.loginProposer(userName);
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

    private void runAfterLoginMenu(Proposer user) {
        String chosen;
        boolean flag = true;
        while(flag){
            printAfterLoginMenu();
            chosen=scanner.nextLine();
            switch (chosen){
                case "1":
                    addProject();
                    break;
                case "2":
                    checkState();
                    break;
                case "3":
                    flag=false;
                    break;
                default:
                    System.out.println("Wrong input");

            }
        }
    }

    private void checkState() {

    }

    private void addProject() {
        boolean flag= true;
        while(flag) {
            System.out.println("Enter name of project");
            String nameOfProject = scanner.nextLine();
            System.out.println("Enter description");
            String description = scanner.nextLine();
            System.out.println("Enter number of hours");
            int hours = scanner.nextInt();
            if(isValidHours(hours)&&isValidName(nameOfProject)){
               String code= data.addProject();//check with yuval
                System.out.println("The project's code : "+ code);
                flag=false;
            }

        }
    }

    private boolean isValidHours(int hours){
        boolean isValid =true;
        if(hours<200||hours>300){
            System.out.println("number of hours can't be greater than 300 or less than 200");
            isValid=false;
        }
        return isValid;

    }
    private boolean isValidName(String nameOfProject){

    }
    @Override
    public boolean Register(){
        boolean flag = true;
        while(flag) {
            System.out.println("Enter your user name or B to go back:");
            String userName = scanner.nextLine();
            System.out.println("Enter first name");
            String fName = scanner.nextLine();
            System.out.println("Enter last name");
            String lName = scanner.nextLine();
            System.out.println("Enter email");
            String email = scanner.nextLine();
            System.out.println("Enter Phone");
            String phone = scanner.nextLine();
            if (!userName.equals("B")) {
                if( data.registerProposer (userName,fName,lName,email,phone)){
                    System.out.println("user name already taken");
                }
                else{
                    System.out.println("success!");
                    flag= false;
                }
            }
            else{
                flag=false;
            }
        }



    }

    private void printAfterLoginMenu (){
        System.out.println("Please enter the option you want:");
        System.out.println("1. Add project");
        System.out.println("2. Check Project's State");
        System.out.println("3. Back");
    }
}
