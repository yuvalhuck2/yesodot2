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

    public String addProject(String pName,String description,int hours,String proposerUserName,String organization){
        Proposer proposer=proposerByUserName.get(proposerUserName);
        String code=getNextCode();
        Project newProject=new Project(code,pName,hours,description,proposer,organization);
        projectBycode.put(code,newProject);
        return code;

    }

    public boolean hasProjectSameNameSameYear(String pName) {
        for(HashMap.Entry<String, Project> projectsCodes : projectBycode.entrySet()){
            Project currProject=projectsCodes.getValue();
            if(currProject.getYear()==getCurrYear()&&pName.equals(currProject.getProjectName()))
                return false;
        }
        return true;
    }

    public boolean loginStudent(String userName){
        return  studentByUserName.containsKey(userName);
    }

    public boolean loginProposer(String userName){
        return  proposerByUserName.containsKey(userName);
    }

    public boolean loginModerator(String userName){
        return moderatorByUserName.containsKey(userName);
    }

    public boolean registerModerator(String userName){
        if(moderatorByUserName.containsKey(userName))
            return false;
        moderatorByUserName.put(userName,new Moderator(userName));
        return true;
    }

    public boolean registerStudent(String userName,int id){
        if(studentByUserName.containsKey(userName))
            return false;
        studentByUserName.put(userName,new Student(userName,id));
        return true;
    }

    public boolean registerProposer(String userName,String fName, String lName, String mail, String phoneNumber){
        if(proposerByUserName.containsKey(userName))
            return false;
        proposerByUserName.put(userName,new Proposer(userName,fName,lName,mail,phoneNumber));
        return true;
    }

    public State checkProjectState(String projectCode){
        if(projectBycode.containsKey(projectCode))
            return projectBycode.get(projectCode).getState();
        else
            return State.Error;
    }

    public String regiterToProject(String userName, Vector<Integer> ids,String projectCode){
        Project curr_project=projectBycode.get(projectCode);
        curr_project.signToProject();
        for (int id:ids)
            getStudentById(id).SignToProject(projectCode);
        return projectCode;
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
        return "efpj"+projectsCode;
    }
    private int  getCurrYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }



}
