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

public class MysqlPoleDao implements PoleDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlPoleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pole> getAll() {
        String sql = "SELECT pole.id AS 'pId', pole.typ_parcely AS 'pTypParcely',"
                + " pole.cislo_parcely AS 'pCisloParcely', pole.vymera AS 'pVymera',"
                + " pole.typ_pozemku AS 'pTypPozemku', pole.vlastnictvo AS 'pVlastnictvo' FROM pole";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Pole>>() {
            @Override
            public List<Pole> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Pole> polia = new ArrayList<>();
                Pole pole = null;
                while (rs.next()) {
                    int poleId = rs.getInt("pId");
                    if (pole == null || poleId != pole.getId()) {
                        pole = new Pole();
                        pole.setId(poleId);
                        pole.setTypParcely(rs.getString("pTypParcely"));
                        pole.setCisloParcely(rs.getString("pCisloParcely"));
                        pole.setVymera(rs.getDouble("pVymera"));
                        pole.setTypPozemku(rs.getString("pTypPozemku"));
                        pole.setVlastnictvo(rs.getString("pVlastnictvo"));
                        polia.add(pole);
                    }
                }
                return polia;
            }
        });
    }

    @Override
    public Pole add(Pole pole) {
        if (pole == null) {
            return null;
        }
        if (pole.getId() == 0) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("pole");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("typ_parcely","cislo_parcely","vymera","typ_pozemku","vlastnictvo");
            Map<String, Object> data = new HashMap<>();
            data.put("typ_parcely", pole.getTypParcely());
            data.put("cislo_parcely", pole.getCisloParcely());
            data.put("vymera", pole.getVymera());
            data.put("typ_pozemku", pole.getTypPozemku());
            data.put("vlastnictvo", pole.getVlastnictvo());
            pole.setId(simpleJdbcInsert.executeAndReturnKey(data).intValue());
        } else { // UPDATE
            String sql = "UPDATE pole SET typ_parcely = ?, cislo_parcely = ?, vymera = ?,"
                    + "typ_pozemku = ?, vlastnictvo = ? WHERE id = " + pole.getId();
            jdbcTemplate.update(sql,
                    pole.getTypParcely(),
                    pole.getCisloParcely(),
                    pole.getVymera(),
                    pole.getTypPozemku(),
                    pole.getVlastnictvo());
        }
        return pole;
    }

    @Override
    public boolean deletePodlaId(int id) {
        String sql = "DELETE FROM pole WHERE pole.id =" + id;
        try {
            int zmazany = jdbcTemplate.update(sql);
            return zmazany == 1;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Pole findById(int id) {
        String sql = "SELECT pole.id AS 'pId', pole.typ_parcely AS 'pTypParcely',"
                + " pole.cislo_parcely AS 'pCisloParcely', pole.vymera AS 'pVymera',"
                + " pole.typ_pozemku AS 'pTypPozemku', pole.vlastnictvo AS 'pVlastnictvo' FROM pole WHERE pole.id=" + id + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Pole>() {
            @Override
            public Pole extractData(ResultSet rs) throws SQLException, DataAccessException {
                Pole pole = null;
                while (rs.next()) {
                    int poleId = rs.getInt("pId");
                    if (pole == null || poleId != pole.getId()) {
                        pole = new Pole();
                        pole.setId(poleId);
                        pole.setTypParcely(rs.getString("pTypParcely"));
                        pole.setCisloParcely(rs.getString("pCisloParcely"));
                        pole.setVymera(rs.getDouble("pVymera"));
                        pole.setTypPozemku(rs.getString("pTypPozemku"));
                        pole.setVlastnictvo(rs.getString("pVlastnictvo"));
                        return pole;
                    }
                }
                return null;
            }
        });
    }
}
