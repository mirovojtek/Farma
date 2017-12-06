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

public class MysqlTankovanieDao implements TankovanieDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlTankovanieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tankovanie> getAllPodlaIdStroja(int idStroja) {
        String sql = "select tankovanie.id as 'tId', tankovanie.stroj_id as 'tStrojId', tankovanie.mnozstvo as 'tMnozstvo', tankovanie.datum as 'tDatum' from tankovanie where stroj_id=" + idStroja;
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Tankovanie>>() {
            @Override
            public List<Tankovanie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Tankovanie> tankovania = new ArrayList<>();
                Tankovanie tankovanie = null;
                while (rs.next()) {
                    int tankovanieId = rs.getInt("tId");
                    if (tankovanie == null || tankovanieId != tankovanie.getId()) {
                        tankovanie = new Tankovanie();
                        tankovanie.setId(tankovanieId);
                        tankovanie.setStrojId(rs.getInt("tStrojId"));
                        tankovanie.setMnozstvo(rs.getDouble("tMnozstvo"));
                        Timestamp ts = rs.getTimestamp("tDatum");
                        if (ts != null) {
                            tankovanie.setDatum(ts.toLocalDateTime().toLocalDate());
                        }
                        tankovania.add(tankovanie);
                    }
                }
                return tankovania;
            }
        });
    }

    @Override
    public List<Tankovanie> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Tankovanie tankovanie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePodlaId(int id) {
        String sql = "DELETE FROM tankovanie WHERE tankovanie.id =" + id + ";";
        try {
            int zmazany = jdbcTemplate.update(sql);
        } catch (Exception e) {
        }
    }

    @Override
    public void deleteAllPodlaIdStroja(int idStroja) {
        String sql = "DELETE FROM tankovanie WHERE tankovanie.stroj_id =" + idStroja + ";";
        try {
            int zmazany = jdbcTemplate.update(sql);
        } catch (Exception e) {
        }
    }

}
