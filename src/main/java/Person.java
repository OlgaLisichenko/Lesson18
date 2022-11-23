import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlElement
    private String firstName;
    @XmlElement
    private String lastName;
    @XmlElement
    private String title;
    @XmlElement
    private String line;

    public Person() {
    }

    public Person(String line) {
        this.line = line;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTitle() {
        return title;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "Person{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", title='" + title + '\'' +
               ", line='" + line + '\'' +
               '}';
    }
}