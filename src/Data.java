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

    public boolean loginStudent(String userName){
        return  studentByUserName.containsKey(userName);
    }

    public Proposer loginProposer(String userName){
        return  proposerByUserName.get(userName);
    }

    public boolean loginModerator(String userName){
        return moderatorByUserName.containsKey(userName);
    }

    public Moderator registerModerator(String userName){
        if(moderatorByUserName.containsKey(userName))
            return null;
        Moderator newModerator=new Moderator(userName);
        moderatorByUserName.put(userName,newModerator);
        return newModerator;
    }

    public Student registerStudent(String userName,int id){
        if(studentByUserName.containsKey(userName))
            return null;
        Student newStudent=new Student(userName,id);
        studentByUserName.put(userName,newStudent);
        return newStudent;
    }

    public Proposer registerProposer(String userName,String fName, String lName, String mail, String phoneNumber){
        if(proposerByUserName.containsKey(userName))
            return null;
        Proposer newProposer=new Proposer(userName,fName,lName,mail,phoneNumber);
        proposerByUserName.put(userName,newProposer);
        return newProposer;
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

        curr_project.signToProject();
        for (int id:ids)
            getStudentById(id).SignToProject(projectCode);
        return projectCode;
    }

    public boolean checkModeratorOfProject(String projectCode,String moderatorName){
        Project projectToCheck=projectBycode.get(projectCode);
        String moderatorOfProjectUserName=projectToCheck.getModeratorUserName();
        return moderatorOfProjectUserName == null || moderatorOfProjectUserName.equals(moderatorName);

    }
    //@todo
    public boolean registerModeratorToProject(Moderator moderator,String projectCode){
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
