public class Student extends User {
    private boolean isSigendToProject;
    private int id;
    private String projectCode;

    public Student(String userName,int id) {
        super(userName);
        this.id=id;
        isSigendToProject=false;
    }

    public boolean isSigendToProject() {
        return isSigendToProject;
    }

    public void SignToProject(String projectCode) {
        isSigendToProject = true;
        this.projectCode=projectCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
