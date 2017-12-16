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
    
    @Override
    public List<String> getTypyParciel() {
        List<String> typyParciel = new ArrayList<>();
        String sql = "SELECT DISTINCT typ_parcely AS 'TypParcely' FROM farma.pole;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    typyParciel.add(rs.getString("TypParcely"));
                }
                return typyParciel;
            }
        });
    }
    @Override
    public List<String> getCislaParcielPodlaTypu(String typParcely) {
        List<String> cislaParciel = new ArrayList<>();
        String sql = "SELECT DISTINCT cislo_parcely AS 'CisloParcely' FROM farma.pole WHERE typ_parcely = '" + typParcely + "';";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    cislaParciel.add(rs.getString("CisloParcely"));
                }
                return cislaParciel;
            }
        });
    }
    
    @Override
    public List<String> getTypyPozemkov(){
        List<String> typyPozemkov = new ArrayList<>();
        String sql = "SELECT DISTINCT typ_pozemku AS 'TypPozemku' FROM farma.pole;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    typyPozemkov.add(rs.getString("TypPozemku"));
                }
                return typyPozemkov;
            }
        });
    }
    
    @Override
    public List<String> getVlastnictvo() {
        List<String> vlastnictvo = new ArrayList<>();
        String sql = "SELECT DISTINCT vlastnictvo AS 'Vlastnictvo' FROM farma.pole;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    vlastnictvo.add(rs.getString("Vlastnictvo"));
                }
                return vlastnictvo;
            }
        });
    }
    
    @Override
    public List<Pole> rozsireneVyhladavanie(String typParcely, String cisloParcely, String typPozemku, String Vlastnictvo){
        String sql = "SELECT * FROM farma.pole WHERE typ_parcely LIKE " + "'" + typParcely + "'"
                + " AND cislo_parcely LIKE " + "'" + cisloParcely + "'" + " AND typ_pozemku LIKE " + "'" + typPozemku + "'"
                + " AND vlastnictvo LIKE " + "'" + Vlastnictvo + "'" + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Pole>>() {
            @Override
            public List<Pole> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Pole> polia = new ArrayList<>();
                Pole pole = null;
                while (rs.next()) {
                    int poleId = rs.getInt("id");
                    if (pole == null || poleId != pole.getId()) {
                        pole = new Pole();
                        pole.setId(poleId);
                        pole.setTypParcely(rs.getString("typ_parcely"));
                        pole.setCisloParcely(rs.getString("cislo_parcely"));
                        pole.setTypPozemku(rs.getString("typ_pozemku"));
                        pole.setVlastnictvo(rs.getString("vlastnictvo"));
                        polia.add(pole);
                    }
                }
                return polia;
            }
        });
    }
}
