
import java.io.Serializable;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class StudentConverter implements Converter, Serializable {

    private final List<Employee> employee;

    public StudentConverter(List<Employee> employee) {
        this.employee = employee;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        for (Employee employee1 : this.employee) {

            try {
                Long.parseLong(value);
            } catch (Exception e) {
                return null;
            }
            if (!value.equals("") && employee1.getId() == Long.parseLong(value)) {
                return employee1;
            }

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            if (value != null && !value.equals("")) {
                if (((Employee) value).getId() != 0) {
                    Employee emp = (Employee) value;
                    return emp.getId() + "";
                } else {
                    return "";
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
