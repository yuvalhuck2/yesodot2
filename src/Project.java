import java.util.Date;

public class Project {
    private String code;
    private String pName;
    private int quantityOfhours;
    private Proposer proposer;
    private String description;
    private String organization;
    private Date createdDate;
    private String moderatorUserName;
    private State state;

    public Project(String code, String pName, int quantityOfhours,String description,Proposer proposer,String organization) {
        this.code = code;
        this.pName = pName;
        this.quantityOfhours = quantityOfhours;
        this.moderatorUserName=null;
        this.proposer=proposer;
        this.createdDate=new Date(System.currentTimeMillis());
        this.state=State.APPROVED;
        this.organization=organization;
        this.description=description;
    }

    public String getCode() {
        return code;
    }

    public Proposer getProposer() {
        return proposer;
    }

    public String getOrganization() {
        return organization;
    }

    //@TODO
    public int getYear() {
        return createdDate.getYear();
    }

    public String getModeratorUserName() {
        return moderatorUserName;
    }

    public State getState() {
        return state;
    }

    public String getProjectName() {
        return pName;
    }

    public void signToProject(){
        this.state=State.IN_PROGRESS;
    }
}
