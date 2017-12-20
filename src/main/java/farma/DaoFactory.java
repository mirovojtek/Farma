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

    public StrojDao getStrojDao() {
        return new MysqlStrojDao(getJDBCTemplate());
    }

    public TankovanieDao getTankovanieDao() {
        return new MysqlTankovanieDao(getJDBCTemplate());
    }

    public OpravaDao getOpravaDao() {
        return new MysqlOpravaDao(getJDBCTemplate());
    }

    public PoleDao getPoleDao() {
        return new MysqlPoleDao(getJDBCTemplate());
    }

    public FinancieDao getFinancieDao() {
        return new MysqlFinancieDao(getJDBCTemplate());
    }

}
