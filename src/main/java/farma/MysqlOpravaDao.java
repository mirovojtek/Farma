package farma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class MysqlOpravaDao implements OpravaDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlOpravaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
   

    @Override
    public List<Oprava> getAllPodlaIdStroja(int idStroja) {
        String sql = "select "
                + "oprava.id as 'oId', "
                + "oprava.stroj_id as 'oStrojId', "
                + "oprava.datum as 'oDatum', "
                + "oprava.cena as 'oCena', "
                + "oprava.porucha as 'oPorucha', "
                + "oprava.popis as 'oPopis' "
                + "from oprava where oprava.stroj_id=" + idStroja + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Oprava>>() {
            @Override
            public List<Oprava> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Oprava> opravy = new ArrayList<>();
                Oprava oprava = null;
                while (rs.next()) {
                    int opravaId = rs.getInt("oId");
                    if (oprava == null || opravaId != oprava.getId()) {
                        oprava = new Oprava();
                        oprava.setId(opravaId);
                        oprava.setIdStroj(rs.getInt("oStrojId"));
                        Timestamp ts = rs.getTimestamp("oDatum");
                        if (ts != null) {
                            oprava.setDatum(ts.toLocalDateTime().toLocalDate());
                        }
                        oprava.setCena(rs.getDouble("oCena"));
                        oprava.setPorucha(rs.getString("oPorucha"));
                        String popis = rs.getString("oPopis");
                        if (popis.equals("null")) {
                            oprava.setPopis("");
                        } else {
                            oprava.setPopis(popis);
                        }
                        opravy.add(oprava);
                    }
                }
                return opravy;
            }
        });
    }

    @Override
    public List<Oprava> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Oprava oprava) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePodlaId(int id) {
        String sql = "DELETE FROM oprava WHERE oprava.id =" + id + ";";
        try {
            int zmazany = jdbcTemplate.update(sql);
        } catch (Exception e) {
        }
    }

    @Override
    public void deleteAllPodlaIdStroja(int idStroja) {
        String sql = "DELETE FROM oprava WHERE oprava.stroj_id =" + idStroja + ";";
        try {
            int zmazany = jdbcTemplate.update(sql);
        } catch (Exception e) {
        }
    }

}
