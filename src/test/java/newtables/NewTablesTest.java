package newtables;

import fi.helsinki.cs.tmc.edutestutils.Points;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Points("03-01")
@ActiveProfiles("test")
public class NewTablesTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void hasTableUser() {
        jdbcTemplate.execute("SELECT id, username FROM User");
    }

    @Test
    public void hasTableEvent() {
        jdbcTemplate.execute("SELECT id, event_title, event_date FROM Event");
    }


    @Test
    public void hasTableCategory() {
        jdbcTemplate.execute("SELECT id, category_name FROM Category");
    }

    @Test
    public void hasJoinTable() {
        jdbcTemplate.execute("SELECT event_id, category_id FROM event_categories");
    }
}
