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
            simpleJdbcInsert.usingColumns("registracne_cislo", "druh", "plemeno", "pohlavie", "datum_narodenia", "datum_nadobudnutia", "kupna_cena");
            Map<String, Object> data = new HashMap<>();
            data.put("registracne_cislo", zviera.getRegistracneCislo());
            data.put("druh", zviera.getDruh());
            data.put("plemeno", zviera.getPlemeno());
            data.put("pohlavie", zviera.getPohlavie());
            data.put("datum_narodenia", zviera.getDatumNarodenia());
            data.put("datum_nadobudnutia", zviera.getDatumNadobudnutia());
            data.put("kupna_cena", zviera.getKupnaCena());
            zviera.setId(simpleJdbcInsert.executeAndReturnKey(data).intValue());
        } else { // UPDATE
            String sql = "UPDATE zviera SET registracne_cislo = ?, druh = ?,"
                    + "plemeno = ?, pohlavie = ?, datum_narodenia = ?, datum_nadobudnutia = ?,"
                    + "kupna_cena = ? WHERE id = " + zviera.getId();
            jdbcTemplate.update(sql, zviera.getRegistracneCislo(), zviera.getDruh(),
                    zviera.getPlemeno(), zviera.getPohlavie(), zviera.getDatumNarodenia(),
                    zviera.getDatumNadobudnutia(), zviera.getKupnaCena());
        }
        return null;
    }

    @Override
    public Zviera findByRegistracneCislo(String rc) {
        String sql = "select zviera.id as 'zId', zviera.registracne_cislo as 'zRegistracneCislo', zviera.druh as 'zDruh', zviera.plemeno as 'zPlemeno', zviera.pohlavie as 'zPohlavie', datum_narodenia as 'zDatumNarodenia', zviera.datum_nadobudnutia as 'zDatumNadobudnutia', zviera.kupna_cena as 'zKupnaCena', zviera.popis as 'zPopis'  from zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Zviera>() {
            @Override
            public Zviera extractData(ResultSet rs) throws SQLException, DataAccessException {
                Zviera zviera = null;
                while (rs.next()) {
                    String regCis = rs.getString("zRegistracneCislo");
                    if (regCis.equals(rc)) {
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
                        zviera.setPopis(rs.getString("zPopis"));
                        return zviera;
                    }
                }
                return null;
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

    @Override
    public void pridajPopis(Zviera zviera) {
        String sql = "UPDATE zviera SET popis = ? WHERE registracne_cislo = "
                + zviera.getRegistracneCislo();
        jdbcTemplate.update(sql, zviera.getPopis());
    }

    @Override
    public List<String> getDruhy() {
        List<String> druhy = new ArrayList<>();
        String sql = "SELECT DISTINCT druh as 'druh' FROM farma.zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    druhy.add(rs.getString("druh"));
                }
                return druhy;
            }
        });
    }

    @Override
    public List<String> getPohlavia() {
        List<String> pohlavia = new ArrayList<>();
        String sql = "SELECT DISTINCT pohlavie as 'pohlavie' FROM farma.zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    pohlavia.add(rs.getString("pohlavie"));
                }
                return pohlavia;
            }
        });
    }

    @Override
    public List<String> getPlemena() {
        List<String> plemena = new ArrayList<>();
        String sql = "SELECT DISTINCT plemeno as 'plemeno' FROM farma.zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    plemena.add(rs.getString("plemeno"));
                }
                return plemena;
            }
        });
    }

    @Override
    public List<String> getPlemenaPodlaDruhu(String d) {
        List<String> plemena = new ArrayList<>();
        String sql = "SELECT DISTINCT plemeno as 'plemeno' FROM farma.zviera WHERE zviera.druh='" + d + "';";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    plemena.add(rs.getString("plemeno"));
                }
                return plemena;
            }
        });
    }

    @Override
    public List<String> getRokyNarodenia() {
        List<String> rokyNarodenia = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR(datum_narodenia) as 'RokNarodenia' FROM farma.zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    rokyNarodenia.add(rs.getString("RokNarodenia"));
                }
                return rokyNarodenia;
            }
        });
    }

    @Override
    public List<String> getRokyNadobudnutia() {
        List<String> rokyNadobudnutia = new ArrayList<>();
        String sql = "SELECT DISTINCT YEAR(datum_nadobudnutia) as 'RokNadobudnutia' FROM farma.zviera;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    rokyNadobudnutia.add(rs.getString("RokNadobudnutia"));
                }
                return rokyNadobudnutia;
            }
        });
    }

    @Override
    public List<Zviera> rozsireneVyhladavanie(String druh, String plemeno, String rokNarodenia, String rokNadobudnutia, String pohlavie) {
        String sql = "SELECT * FROM farma.zviera WHERE plemeno LIKE " + "'" + plemeno + "'"
                + " AND druh LIKE " + "'" + druh + "'" + " AND YEAR(datum_narodenia) LIKE " + "'" + rokNarodenia + "'"
                + " AND YEAR(datum_nadobudnutia) LIKE " + "'" + rokNadobudnutia + "'" + " AND pohlavie LIKE " + "'" + pohlavie + "'" + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Zviera>>() {
            @Override
            public List<Zviera> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Zviera> zvierata = new ArrayList<>();
                Zviera zviera = null;
                while (rs.next()) {
                    int zvieraId = rs.getInt("id");
                    if (zviera == null || zvieraId != zviera.getId()) {
                        zviera = new Zviera();
                        zviera.setRegistracneCislo(rs.getString("registracne_cislo"));
                        zviera.setDruh(rs.getString("druh"));
                        zviera.setPlemeno(rs.getString("plemeno"));
                        zviera.setPohlavie(rs.getString("pohlavie"));
                        Timestamp ts = rs.getTimestamp("datum_narodenia");
                        zviera.setDatumNarodenia(ts.toLocalDateTime().toLocalDate());
                        ts = rs.getTimestamp("datum_nadobudnutia");
                        zviera.setDatumNadobudnutia(ts.toLocalDateTime().toLocalDate());
                        zviera.setKupnaCena(rs.getDouble("kupna_cena"));
                        zvierata.add(zviera);
                    }
                }
                return zvierata;
            }
        });
    }
}
