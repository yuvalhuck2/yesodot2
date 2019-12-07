public class Proposer extends User {
    private String lName;
    private String fName;
    private String mail;
    private String phoneNumber;

    public Proposer(String userName, String lName, String fName, String mail, String phoneNumber) {
        super(userName);
        this.lName = lName;
        this.fName = fName;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
    }
}
