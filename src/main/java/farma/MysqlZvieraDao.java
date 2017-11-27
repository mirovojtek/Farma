package farma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlZvieraDao implements ZvieraDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlZvieraDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Zviera add(Zviera zviera) {
        if (zviera == null) {
            return null;
        }
        if (zviera.getId() == 0) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("zviera");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
        }
        return null;
    }

    @Override
    public Zviera findByRegistracneCislo(String rc) {

        String sql = "select zviera.id as 'zId' zviera.registracne_cislo as 'zRegistracneCislo', zviera.druh as 'zDruh', zviera.plemeno as 'zPlemeno', zviera.pohlavie as 'zPohlavie', datum_narodenia as 'zDatumNarodenia', zviera.datum_nadobudnutia as 'zDatumNadobudnutia', zviera.kupna_cena as 'zKupnaCena'  from zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Zviera>() {
            @Override
            public Zviera extractData(ResultSet rs) throws SQLException, DataAccessException {
                Zviera zviera = null;
                while (rs.next()) {
                    int zvieraId = rs.getInt("zId");
                    if (zviera == null || zvieraId != zviera.getId()) {
                        zviera = new Zviera();
                        zviera.setRegistracneCislo(rs.getString("zRegistracneCislo"));
                        zviera.setDruh(rs.getString("zDruh"));
                        zviera.setPlemeno(rs.getString("zPlemeno"));
                        zviera.setPohlavie(rs.getString("zPohlavie"));
                        Timestamp ts = rs.getTimestamp("zDatumNarodenia");
                        zviera.setDatumNarodenia(ts.toLocalDateTime().toLocalDate()); // dopísať nastavenie datumu - vhodný formát
                        ts = rs.getTimestamp("zDatumNadobudnutia");
                        zviera.setDatumNadobudnutia(ts.toLocalDateTime().toLocalDate()); // dopísať dátum nadobudnutia - vhodný formát
                        zviera.setKupnaCena(rs.getDouble("zKupnaCena"));
                    }
                }
                return zviera;
            }
        });
    }

    @Override
    public List<Zviera> getAll() {
        String sql = "select zviera.id as 'zId', zviera.registracne_cislo as 'zRegistracneCislo', zviera.druh as 'zDruh', zviera.plemeno as 'zPlemeno', zviera.pohlavie as 'zPohlavie', datum_narodenia as 'zDatumNarodenia', zviera.datum_nadobudnutia as 'zDatumNadobudnutia', zviera.kupna_cena as 'zKupnaCena'  from zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Zviera>>() {
            @Override
            public List<Zviera> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Zviera> zvierata = new ArrayList<>();
                Zviera zviera = null;
                while (rs.next()) {
                    int zvieraId = rs.getInt("zId");
                    if (zviera == null || zvieraId != zviera.getId()) {
                        zviera = new Zviera();
                        zviera.setRegistracneCislo(rs.getString("zRegistracneCIslo"));
                        zviera.setDruh(rs.getString("zDruh"));
                        zviera.setPlemeno(rs.getString("zPlemeno"));
                        zviera.setPohlavie(rs.getString("zPohlavie"));
                        Timestamp ts = rs.getTimestamp("zDatumNarodenia");
                        zviera.setDatumNarodenia(ts.toLocalDateTime().toLocalDate());
                        ts = rs.getTimestamp("zDatumNadobudnutia");
                        zviera.setDatumNadobudnutia(ts.toLocalDateTime().toLocalDate());
                        zviera.setKupnaCena(rs.getDouble("zKupnaCena"));
                        zvierata.add(zviera);
                    }
                }
                System.out.println(zvierata);
                return zvierata;
            }
        });
    }

    @Override
    public boolean deleteByRegistracneCislo(String rc) {
        String sql = "DELETE FROM zviera WHERE registracne_cislo =" + rc;
        try {
            int zmazany = jdbcTemplate.update(sql);
            return zmazany == 1;
        } catch (Exception e) {
            return false;
        }
    }

}
