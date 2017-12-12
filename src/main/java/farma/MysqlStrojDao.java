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

public class MysqlStrojDao implements StrojDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlStrojDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private TankovanieDao tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
    private OpravaDao opravaDao = DaoFactory.INSTANCE.getOpravaDao();

    @Override
    public void add(Stroj stroj) {
        if (stroj == null) {
            return;
        }
        if (stroj.getId() == 0) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("stroj");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("vyrobca", "typ", "kategoria", "datum_nadobudnutia", "cena");

            Map<String, Object> data = new HashMap<>();

            data.put("vyrobca", stroj.getVyrobca());
            data.put("typ", stroj.getTyp());
            data.put("kategoria", stroj.getKategoria());
            data.put("datum_nadobudnutia", stroj.getDatum());
            data.put("cena", stroj.getCena());
            stroj.setId(simpleJdbcInsert.executeAndReturnKey(data).intValue());
        } else {    // UPDATE
            String sql = "UPDATE stroj SET vyrobca = ?, typ = ?,kategoria = ?, datum_nadobudnutia = ?, cena = ? WHERE id = " + stroj.getId();
            jdbcTemplate.update(sql,
                    stroj.getVyrobca(),
                    stroj.getTyp(),
                    stroj.getKategoria(),
                    stroj.getDatum(),
                    stroj.getCena());
        }
        if (stroj.getTankovania().size() > 0) {
            String sql = "INSERT INTO tankovanie (stroj_id, mnozstvo, datum) VALUES (?,?,?)";
            for (Tankovanie tankovanie : stroj.getTankovania()) {
                jdbcTemplate.update(sql, stroj.getId(), tankovanie.getMnozstvo(), tankovanie.getDatum());
            }
        }
        if (stroj.getOpravy().size() > 0) {
            String sql = "INSERT INTO oprava (stroj_id, datum, cena, porucha, popis) VALUES (?,?,?,?,?)";
            for (Oprava oprava : stroj.getOpravy()) {
                jdbcTemplate.update(sql,
                        stroj.getId(),
                        oprava.getDatum(),
                        oprava.getCena(),
                        oprava.getPorucha(),
                        oprava.getPopis());
            }
        }
    }

    @Override
    public Stroj findById(int id) {
        String sql = "select "
                + "stroj.id as 'sId', "
                + "stroj.vyrobca as 'sVyrobca', "
                + "stroj.typ as 'sTyp', "
                + "stroj.kategoria as 'sKategoria', "
                + "stroj.datum_nadobudnutia as 'sDatumNadobudnutia', "
                + "stroj.cena as 'sCena',"
                + "stroj.popis as 'sPopis' from stroj where stroj.id=" + id + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Stroj>() {
            @Override
            public Stroj extractData(ResultSet rs) throws SQLException, DataAccessException {
                Stroj stroj = null;
                while (rs.next()) {
                    int strojId = rs.getInt("sId");
                    if (stroj == null || strojId != stroj.getId()) {
                        stroj = new Stroj();
                        stroj.setId(strojId);
                        stroj.setVyrobca(rs.getString("sVyrobca"));
                        stroj.setTyp(rs.getString("sTyp"));
                        stroj.setKategoria(rs.getString("sKategoria"));
                        Timestamp ts = rs.getTimestamp("sDatumNadobudnutia");
                        if (ts != null) {
                            stroj.setDatum(ts.toLocalDateTime().toLocalDate());
                        }
                        stroj.setCena(rs.getDouble("sCena"));
                        stroj.setPopis(rs.getString("sPopis"));
                        stroj.setTankovania(tankovanieDao.getAllPodlaIdStroja(strojId));
                        stroj.setOpravy(opravaDao.getAllPodlaIdStroja(strojId));
                        return stroj;
                    }
                }
                return null;
            }
        });
    }

    @Override
    public List<Stroj> getAll() {
        String sql = "select "
                + "stroj.id as 'sId', "
                + "stroj.vyrobca as 'sVyrobca', "
                + "stroj.typ as 'sTyp', "
                + "stroj.kategoria as 'sKategoria', "
                + "stroj.datum_nadobudnutia as 'sDatumNadobudnutia', "
                + "stroj.cena as 'sCena' from stroj;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Stroj>>() {
            @Override
            public List<Stroj> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Stroj> stroje = new ArrayList<>();
                Stroj stroj = null;
                while (rs.next()) {
                    int strojId = rs.getInt("sId");
                    if (stroj == null || strojId != stroj.getId()) {
                        stroj = new Stroj();
                        stroj.setId(strojId);
                        stroj.setVyrobca(rs.getString("sVyrobca"));
                        stroj.setTyp(rs.getString("sTyp"));
                        stroj.setKategoria(rs.getString("sKategoria"));
                        Timestamp ts = rs.getTimestamp("sDatumNadobudnutia");
                        if (ts != null) {
                            stroj.setDatum(ts.toLocalDateTime().toLocalDate());
                        }
                        stroj.setCena(rs.getDouble("sCena"));
                        // tankovania
                        stroj.setTankovania(tankovanieDao.getAllPodlaIdStroja(strojId));
                        // opravy
                        stroj.setOpravy(opravaDao.getAllPodlaIdStroja(strojId));
                        stroje.add(stroj);
                    }
                }
                return stroje;
            }
        });
    }

    @Override
    public boolean deleteById(int id) {
        opravaDao.deleteAllPodlaIdStroja(id);
        tankovanieDao.deleteAllPodlaIdStroja(id);

        String sql = "DELETE FROM stroj WHERE stroj.id =" + id;
        try {
            int zmazany = jdbcTemplate.update(sql);
            return zmazany == 1;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List<String> getVyrobcovia() {
        List<String> vyrobcovia = new ArrayList<>();
        String sql = "SELECT DISTINCT vyrobca AS 'vyrobca' FROM farma.stroj;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    vyrobcovia.add(rs.getString("vyrobca"));
                }
                return vyrobcovia;
            }
        });
    }

    @Override
    public List<String> getTypy() {
        List<String> typy = new ArrayList<>();
        String sql = "SELECT DISTINCT typ AS 'typ' FROM farma.stroj;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    typy.add(rs.getString("typ"));
                }
                return typy;
            }
        });
    }

    @Override
    public List<String> getTypyPodlaVyrobcu(String vyrobca) {
        List<String> typyPodlaVyrobcu = new ArrayList<>();
        String sql = "SELECT DISTINCT typ AS 'typ' FROM farma.stroj WHERE vyrobca='" + vyrobca + "';";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    typyPodlaVyrobcu.add(rs.getString("typ"));
                }
                return typyPodlaVyrobcu;
            }
        });
    }

    @Override
    public List<String> getKategorie() {
        List<String> kategorie = new ArrayList<>();
        String sql = "SELECT DISTINCT kategoria AS 'kategoria' FROM farma.stroj;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    kategorie.add(rs.getString("kategoria"));
                }
                return kategorie;
            }
        });
    }

    @Override
    public List<String> getRokyNadobudnutia() {
        List<String> rokyNadobudnutia = new ArrayList<>();
        String sql = "SELECT DISTINCT year(datum_nadobudnutia) AS 'rokNadobudnutia' FROM farma.stroj;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<String>>() {
            @Override
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    rokyNadobudnutia.add(rs.getString("rokNadobudnutia"));
                }
                return rokyNadobudnutia;
            }
        });
    }

    @Override
    public List<Stroj> rozsireneVyhladavanie(String vyrobca, String typ, String kategoria, String rokNadobudnutia) {
        String sql = "select "
                + "stroj.id as 'sId', "
                + "stroj.vyrobca as 'sVyrobca', "
                + "stroj.typ as 'sTyp', "
                + "stroj.kategoria as 'sKategoria', "
                + "stroj.datum_nadobudnutia as 'sDatumNadobudnutia', "
                + "stroj.cena as 'sCena' from farma.stroj "
                + " WHERE vyrobca LIKE " + "'" + vyrobca + "'"
                + " AND typ LIKE " + "'" + typ + "'" + " AND YEAR(datum_nadobudnutia) LIKE " + "'" + rokNadobudnutia + "'"
                + " AND kategoria LIKE " + "'" + kategoria + "'" + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Stroj>>() {
            @Override
            public List<Stroj> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Stroj> stroje = new ArrayList<>();
                Stroj stroj = null;
                while (rs.next()) {
                    int strojId = rs.getInt("sId");
                    if (stroj == null || strojId != stroj.getId()) {
                        stroj = new Stroj();
                        stroj.setId(strojId);
                        stroj.setVyrobca(rs.getString("sVyrobca"));
                        stroj.setTyp(rs.getString("sTyp"));
                        stroj.setKategoria(rs.getString("sKategoria"));
                        Timestamp ts = rs.getTimestamp("sDatumNadobudnutia");
                        if (ts != null) {
                            stroj.setDatum(ts.toLocalDateTime().toLocalDate());
                        }
                        stroj.setCena(rs.getDouble("sCena"));
                        // tankovania
                        stroj.setTankovania(tankovanieDao.getAllPodlaIdStroja(strojId));
                        // opravy
                        stroj.setOpravy(opravaDao.getAllPodlaIdStroja(strojId));
                        stroje.add(stroj);
                    }
                }
                return stroje;

            }
        });
    }

    @Override
    public void pridajPopis(Stroj stroj) {
        String sql = "UPDATE farma.stroj SET popis = ? WHERE id = "
                + stroj.getId();
        jdbcTemplate.update(sql, stroj.getPopis());
    }

}
