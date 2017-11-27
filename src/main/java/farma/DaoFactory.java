package farma;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public enum DaoFactory {

    INSTANCE;

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJDBCTemplate() {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("farma");
            dataSource.setPassword("farma");
            dataSource.setURL("jdbc:mysql://localhost/farma?serverTimezone=Europe/Bratislava");
            
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

    public ZvieraDao getZvieraDao() {
        return new MysqlZvieraDao(getJDBCTemplate());
    }

}
