import Models.Student;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StudentTest {
    @Test
    public void testGet() {
        Student st = new Student("d","dsa",123,"dsa","dd");
        assertEquals("d",st.getId());
    }
}
