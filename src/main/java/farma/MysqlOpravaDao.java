package farma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

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
                        if (popis == null) {
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
        String sql = "select "
                + "oprava.id as 'oId', "
                + "oprava.stroj_id as 'oStrojId', "
                + "oprava.datum as 'oDatum', "
                + "oprava.cena as 'oCena', "
                + "oprava.porucha as 'oPorucha', "
                + "oprava.popis as 'oPopis' "
                + "from oprava;";
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
                        if (popis == null) {
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

    //
    @Override
    public Oprava findById(int id) {
        String sql = "select "
                + "oprava.id as 'oId', "
                + "oprava.stroj_id as 'oStrojId', "
                + "oprava.datum as 'oDatum', "
                + "oprava.cena as 'oCena', "
                + "oprava.porucha as 'oPorucha', "
                + "oprava.popis as 'oPopis' "
                + "from oprava where oprava.id=" + id + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Oprava>() {
            @Override
            public Oprava extractData(ResultSet rs) throws SQLException, DataAccessException {
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
                        if (popis == null) {
                            oprava.setPopis("");
                        } else {
                            oprava.setPopis(popis);
                        }
                        return oprava;
                    }
                }
                return null;
            }
        });
    }

    //
    @Override
    public void add(Oprava oprava) {
        if (oprava == null) {
            return;
        }
        if (oprava.getId() == 0) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("oprava");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("stroj_id", "datum", "cena", "porucha", "popis");
            Map<String, Object> data = new HashMap<>();
            data.put("stroj_id", oprava.getIdStroj());
            data.put("datum", oprava.getDatum());
            data.put("cena", oprava.getCena());
            data.put("porucha", oprava.getPorucha());
            data.put("popis", oprava.getPopis());
            oprava.setId(simpleJdbcInsert.executeAndReturnKey(data).intValue());
        } else {
            // UPDATE
            String sql = "UPDATE oprava SET stroj_id = ?, datum = ?, cena = ?, porucha = ?,, popis = ? WHERE id = " + oprava.getId();
            jdbcTemplate.update(sql,
                    oprava.getIdStroj(),
                    oprava.getDatum(),
                    oprava.getCena(),
                    oprava.getPorucha(),
                    oprava.getPopis());
        }
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
    
    
    @Override
    public void pridajPopis(Oprava oprava) {
        String sql = "UPDATE farma.oprava SET popis = ? WHERE id = "
                + oprava.getId();
        jdbcTemplate.update(sql, oprava.getPopis());
    }

}
