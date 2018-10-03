
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class EmpBean {

    private Employee empobj;
    List EmployeeList;
    private List<Employee> allEmps = new ArrayList<>();

    public List<Employee> getAllEmps() {
        return allEmps;
    }

    public void setAllEmps(List<Employee> allEmps) {
        this.allEmps = allEmps;
    }

    public List getEmployeeList() {
        return EmployeeList;
    }

    public void setEmployeeList(List EmployeeList) {
        this.EmployeeList = EmployeeList;
    }

    @PostConstruct
    public void init() {

        empobj = new Employee();
        EmployeeList = new ArrayList();
        DatabaseOperations d = new DatabaseOperations();
        EmployeeList = d.retriveAllEmployee();
     //   allEmps = d.retriveAllEmployees();

    }

    public Employee getEmpobj() {
        return empobj;
    }

    public void setEmpobj(Employee empobj) {
        this.empobj = empobj;
    }

    public void saveEmployeeRecord() {
        DatabaseOperations dbObj = new DatabaseOperations();
        dbObj.addStudentInDb(empobj);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Saved"));

        //to clear
        empobj = new Employee();
    }

    public void clear() {
        empobj.setId(0);
        empobj.setName(null);
        empobj.setPhone(null);
        empobj.setDepartment(null);

    }

    public void deleteEmplRecord() {

        DatabaseOperations dbObj = new DatabaseOperations();
        dbObj.deleteStudentInDb(empobj.getId());

    }

    private List<Employee> empList;

    public List<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    public List<Employee> retriveStudentDetailsById() {
        //System.out.println("Calling getStudentDetailsById() Method Details For Student Id?= " + id);
        DatabaseOperations dbObj = new DatabaseOperations();
        empList = dbObj.getStudentById(empobj.getId());

        //System.out.println("Fetched Id? " + id + " Details Are: Name=" + name + ", Department=" + department);
        return empList;
    }

    private List<Employee> empList2;

    public List<Employee> getEmpList2() {
        return empList2;
    }

    public void setEmpList2(List<Employee> empList2) {
        this.empList2 = empList2;
    }

    public List<Employee> retriveStudentDetailsByName() {
        //System.out.println("Calling getStudentDetailsById() Method Details For Student Id?= " + id);
        DatabaseOperations dbObj = new DatabaseOperations();
        empList = dbObj.getStudentByName(empobj.getName());

        //System.out.println("Fetched Id? " + id + " Details Are: Name=" + name + ", Department=" + department);
        return empList2;
    }

    private String namesearch;

    public String getNamesearch() {
        return namesearch;
    }

    public void setNamesearch(String namesearch) {
        this.namesearch = namesearch;
    }

    public void updateEmpDetails() {
        DatabaseOperations dt = new DatabaseOperations();
        dt.updateStudentRecord(empobj);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data Saved"));

    }
    DatabaseOperations d = new DatabaseOperations();

//    public List<Employee> all() {
//
//        DatabaseOperations d = new DatabaseOperations();
//        return d.retriveAll();
//
//    }
    List<String> names = d.retriveAll();

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
    StudentConverter s = new StudentConverter(empList);

    public StudentConverter getS() {
        return s;
    }

    public void setS(StudentConverter s) {
        this.s = s;
    }

    /////converter list method
    public void loadAllEmps() {
        d = new DatabaseOperations();
        allEmps = new ArrayList<>();
        allEmps = d.retriveAllEmployees();
       //  
    }
    public  void reset(){
        allEmps = new ArrayList<>();
        
        //commet
        //commet2
    }
}
