
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class DatabaseOperations {

//add student form
    public void addStudentInDb(Employee employeetObj) {
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            se.save(employeetObj);
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdEmployee", employeetObj.getId());

        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            se.getTransaction().commit();
        }
    }

    //update form
    public void updateStudentRecord(Employee updateEmployeeObj) {
        Session se = HibernateUtil.getSessionFactory().openSession();

        try {
            se.beginTransaction();
            se.update(updateEmployeeObj);
            se.save(updateEmployeeObj);
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            se.getTransaction().commit();
        }
    }
    //delete

    public void deleteStudentInDb(int delEmpId) {
        Session se = HibernateUtil.getSessionFactory().openSession();

        try {
            se.beginTransaction();
            Employee empID = (Employee) se.get(Employee.class, new Integer(delEmpId));
            se.delete(empID);
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            se.getTransaction().commit();
        }
    }

    //search 
    public List<Employee> getStudentById(int empId) {
        Employee emp = new Employee();
        List<Employee> empObj = new ArrayList<Employee>();
        Session se = HibernateUtil.getSessionFactory().openSession();

        try {
            se.beginTransaction();
            Query queryObj = se.createQuery("from Employee where id= :emp_id").setInteger("emp_id", empId);
            emp = (Employee) queryObj.uniqueResult();
            empObj = queryObj.list();
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            se.getTransaction().commit();
        }
        return empObj;
    }

    public List<Employee> getStudentByName(String empName) {
        Employee emp = new Employee();
        List<Employee> empObj = new ArrayList<Employee>();
        Session se = HibernateUtil.getSessionFactory().openSession();

        try {
            se.beginTransaction();
            // Query queryObj = se.createQuery("from Employee where Lower(name) like :'%emp_name%'").setString("emp_name", empName.toLowerCase());

            Query queryObj = se.createQuery(" from Employee where Lower(name)= :emp_name").setString("emp_name", empName.toLowerCase());
           // Query queryObj = se.createSQLQuery("Select * from Employee where name = " + empName);
//            emp = (Employee) queryObj.uniqueResult();
            empObj = queryObj.list();
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            se.getTransaction().commit();
        }
        return empObj;
    }

    public List< String> retriveAll() {

        List<String> emp = new ArrayList<>();
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            Query q = se.createQuery("select name from Employee");
            emp = q.list();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return emp;

    }
    
    
      public List< Employee> retriveAllEmployee() {

        List<Employee> emp = new ArrayList<>();
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            Query q = se.createQuery("select name from Employee");
            emp = q.list();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return emp;

    }
      
      
         public List< Employee> retriveAllEmployees() {

        List<Employee> emp = new ArrayList<>();
        Session se = HibernateUtil.getSessionFactory().openSession();
        try {
            se.beginTransaction();
            Query q = se.createQuery(" from Employee");
            emp = q.list();
            System.out.println("DatabaseOperations.retriveAllEmployees()");
        } catch (HibernateException ex) {
            ex.printStackTrace();
        }
        return emp;

    }

}
