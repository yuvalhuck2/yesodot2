import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

public class Data {
    private HashMap <String,Project> projectBycode;
    private HashMap<String,Proposer> proposerByUserName;
    private HashMap<String,Student> studentByUserName;
    private HashMap<String,Moderator> moderatorByUserName;
    private int projectsCode;

    public Data() {
        projectBycode=new HashMap<>();
        proposerByUserName=new HashMap<>();
        studentByUserName=new HashMap<>();
        moderatorByUserName=new HashMap<>();
        projectsCode=-1;
    }

    public String addProject(String pName,String description,int hours,Proposer proposer,String organization){
        if(hours<200||hours>300)
            return "number of hours needs to be between 200 to 300";
        if(pName==null)
            return "project name is missing";
        if(description==null)
            return "description is missing";
        if(hasProjectSameNameSameYear(pName,proposer,organization))
            return "projects can't have same name, same year and same organization or proposer";
        String code=getNextCode();
        Project newProject=new Project(code,pName,hours,description,proposer,organization);
        projectBycode.put(code,newProject);
        return code;

    }

    private boolean hasProjectSameNameSameYear(String pName, Proposer proposer, String organization) {
        for(HashMap.Entry<String, Project> projectsCodes : projectBycode.entrySet()){
            Project currProject=projectsCodes.getValue();
            if(currProject.getYear()==getCurrYear()&&pName.equals(currProject.getProjectName())&&checkSameProposer(currProject,proposer,organization))
                return false;
        }
        return true;
    }

    private boolean checkSameProposer(Project currProject, Proposer proposer,String organization) {
        if(organization==null)
            return proposer.userName.equals(currProject.getProposer().getUserName());
        return organization.equals(currProject.getOrganization());
    }

    public Student loginStudent(String userName){
        if(studentByUserName.containsKey(userName))
            return studentByUserName.get(userName);
        return null;
    }

    public Proposer loginProposer(String userName){
        if(proposerByUserName.containsKey(userName))
            return  proposerByUserName.get(userName);
        return null;
    }

    public Moderator loginModerator(String userName){
        if(moderatorByUserName.containsKey(userName))
            return moderatorByUserName.get(userName);
        return null;
    }

    public boolean registerModerator(String userName){
        if(moderatorByUserName.containsKey(userName))
            return false;
        Moderator newModerator=new Moderator(userName);
        moderatorByUserName.put(userName,newModerator);
        return true;
    }

    public boolean registerStudent(String userName,int id){
        if(studentByUserName.containsKey(userName))
            return false;
        Student newStudent=new Student(userName,id);
        studentByUserName.put(userName,newStudent);
        return true;
    }

    public boolean registerProposer(String userName,String fName, String lName, String mail, String phoneNumber){
        if(proposerByUserName.containsKey(userName))
            return false;
        Proposer newProposer=new Proposer(userName,fName,lName,mail,phoneNumber);
        proposerByUserName.put(userName,newProposer);
        return true;
    }

    public State checkProjectState(String projectCode){
        if(projectBycode.containsKey(projectCode))
            return projectBycode.get(projectCode).getState();
        else
            return State.Error;
    }

    public String registerToProject(String userName, Vector<Integer> ids,String projectCode,String moderatorUserName){
        if(ids.size()<1)
            return "you need to have at least one partner to sign to a project";
        if(checkIfCanNotChooseProject(projectCode))
            return "this project is already taken";
        Student myStudent=studentByUserName.get(userName);
        if(myStudent.isSigendToProject())
            return "student "+myStudent.getId()+" is already sign to a project";
        for (int id:ids) {
            Student currStudent=getStudentById(id);
            if(currStudent==null)
                return "student "+id+" is not exist in the system";
            if(currStudent.isSigendToProject())
                return "student "+id+" is already sign to a project";
        }

        Project curr_project=projectBycode.get(projectCode);
        String moderatorOfCurrProject=curr_project.getModeratorUserName();
        if(moderatorOfCurrProject!=null&&!moderatorUserName.equals(moderatorOfCurrProject))
            return  "moderator "+moderatorOfCurrProject+" is already signed to this project";

        curr_project.signToProject();
        for (int id:ids)
            getStudentById(id).SignToProject(projectCode);
        myStudent.SignToProject(projectCode);
        return projectCode;
    }

    //@todo
    public boolean registerModeratorToProject(Moderator moderator,String projectCode){
        Project currProject=projectBycode.get(projectCode);
        if(currProject.getModeratorUserName()!=null)
            return false;
        currProject.setModeratorUserName(moderator.getUserName());
        return true;

    }

    public boolean checkIfCanNotChooseProject(String projectCode){
        return projectBycode.get(projectCode).getState()!=State.APPROVED;
    }

    private Student getStudentById(int id) {
        for(HashMap.Entry<String, Student> studentsAndCode : studentByUserName.entrySet()){
            Student currStudent=studentsAndCode.getValue();
            if(currStudent.getId()==id)
                return currStudent;
        }
        return null;
    }


    private String getNextCode(){
        projectsCode++;
        return "xfpj"+projectsCode;
    }
    private int  getCurrYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }




}
