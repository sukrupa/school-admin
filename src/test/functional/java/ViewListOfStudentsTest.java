import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.sukrupa.student.Student;
import org.sukrupa.student.StudentBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ViewListOfStudentsTest {

    private WebDriver driver = new HtmlUnitDriver();

    @Ignore
    @Test
    public void shouldDisplayListOfAllStudents() {
        // given we have three students in the DB
        Student rebecca = new StudentBuilder().name("rebecca").female().build();
        Student pat = new StudentBuilder().name("pat").male().build();
        Student renaud = new StudentBuilder().name("renaud").male().build();

        save(rebecca, pat, renaud);

        // when I navigate to the students page
        ListOfStudentsPage page = new ListOfStudentsPage(driver);


        // then I should see the three students
        assertThat(page.getStudents().get(0).getName(), is("rebecca"));
        assertThat(page.getStudents().get(1).getName(), is("pat"));
        assertThat(page.getStudents().get(2).getName(), is("renaud"));
    }

    private void save(Student rebecca, Student pat, Student renaud) {
    }

    private class ListOfStudentsPage {

        private final WebDriver driver;

        public ListOfStudentsPage(WebDriver driver) {
            this.driver = driver;
            driver.get("http://localhost:8080/sukrupa/app/students");
        }

        public List<StudentRow> getStudents() {
            List<StudentRow> students = new ArrayList<StudentRow>();



            // Find the text input element by its name
            List<WebElement> rows = driver.findElements(By.xpath("//tr[@class='student']"));
            for (WebElement row : rows) {
                students.add(new StudentRow(row));
            }

            return students;
        }

        private class StudentRow {
            private final WebElement row;

            public StudentRow(WebElement row) {
                this.row = row;
            }

            public String getName() {
                return row.findElement(By.xpath("//td[@class='name']")).getText();
            }

            public String getStudentId() {
                return row.findElement(By.xpath("//td[@class='studentId']")).getText();
            }

            public String getSex() {
                return row.findElement(By.xpath("//td[@class='sex']")).getText();
            }

            public String getAge() {
                return row.findElement(By.xpath("//td[@class='age']")).getText();
            }

            public Student getStudent(){
                return new StudentBuilder().name(getName()).studentId(getStudentId()).sex(getSex()).build();
            }
        }
    }
}
