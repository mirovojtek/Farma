package farma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MysqlFinancieDao implements FinancieDao {

    private JdbcTemplate jdbcTemplate;

    public MysqlFinancieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Financie> getAll() {
        String sql = "SELECT financie.id AS 'fId', financie.datum AS 'fDatum',financie.suma AS 'fSuma', financie.typ AS 'fTyp', financie.popis AS 'fPopis' from farma.financie;";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Financie>>() {
            @Override
            public List<Financie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Financie> financie = new ArrayList<>();
                Financie polozka = null;
                while (rs.next()) {
                    int polozkaId = rs.getInt("fId");
                    if (polozka == null || polozkaId != polozka.getId()) {
                        polozka = new Financie();
                        polozka.setId(polozkaId);
                        Timestamp ts = rs.getTimestamp("fDatum");
                        polozka.setDatum(ts.toLocalDateTime().toLocalDate());
                        polozka.setSuma(rs.getDouble("fSuma"));
                        polozka.setTyp(rs.getString("fTyp"));
                        polozka.setPopis(rs.getString("fPopis"));
                        financie.add(polozka);
                    }
                }
                return financie;
            }
        }
        );
    }

    @Override
    public List<Financie> getAllByDate(LocalDate localDate) {
        String sql = "SELECT "
                + "financie.id AS 'fId', "
                + "financie.datum AS 'fDatum',"
                + "financie.suma AS 'fSuma', "
                + "financie.typ AS 'fTyp', "
                + "financie.popis AS 'fPopis' "
                + "from farma.financie "
                + "WHERE financie.datum='" + localDate + "';";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Financie>>() {
            @Override
            public List<Financie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Financie> financie = new ArrayList<>();
                Financie polozka = null;
                while (rs.next()) {
                    int polozkaId = rs.getInt("fId");
                    if (polozka == null || polozkaId != polozka.getId()) {
                        polozka = new Financie();
                        polozka.setId(polozkaId);
                        Timestamp ts = rs.getTimestamp("fDatum");
                        polozka.setDatum(ts.toLocalDateTime().toLocalDate());
                        polozka.setSuma(rs.getDouble("fSuma"));
                        polozka.setTyp(rs.getString("fTyp"));
                        polozka.setPopis(rs.getString("fPopis"));
                        financie.add(polozka);
                    }
                }
                return financie;
            }
        });
    }

    @Override
    public Financie add(Financie polozka) {
        if (polozka == null) {
            return null;
        }
        if (polozka.getId() == 0) {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            simpleJdbcInsert.withTableName("financie");
            simpleJdbcInsert.usingGeneratedKeyColumns("id");
            simpleJdbcInsert.usingColumns("datum", "suma", "typ", "popis");
            Map<String, Object> data = new HashMap<>();
            data.put("datum", polozka.getDatum());
            data.put("suma", polozka.getSuma());
            data.put("typ", polozka.getTyp());
            data.put("popis", polozka.getPopis());
            polozka.setId(simpleJdbcInsert.executeAndReturnKey(data).intValue());
        } else {
            // UPDATE
            String sql = "UPDATE financie SET datum = ?, suma = ?,typ = ?, popis = ? WHERE id = " + polozka.getId();
            jdbcTemplate.update(sql,
                    polozka.getDatum(),
                    polozka.getSuma(),
                    polozka.getTyp(),
                    polozka.getPopis());
        }
        return polozka;
    }

    @Override
    public boolean deletePodlaId(int id) {
        String sql = "DELETE FROM financie WHERE financie.id =" + id;
        try {
            int zmazany = jdbcTemplate.update(sql);
            return zmazany == 1;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public Financie findById(int id) {
        String sql = "SELECT "
                + "financie.id AS 'fId', "
                + "financie.datum AS 'fDatum',"
                + "financie.suma AS 'fSuma', "
                + "financie.typ AS 'fTyp', "
                + "financie.popis AS 'fPopis' "
                + "from farma.financie WHERE financie.id=" + id + ";";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Financie>() {
            @Override
            public Financie extractData(ResultSet rs) throws SQLException, DataAccessException {
                Financie polozka = null;
                while (rs.next()) {
                    int polozkaId = rs.getInt("fId");
                    if (polozka == null || polozkaId != polozka.getId()) {
                        polozka = new Financie();
                        polozka.setId(polozkaId);
                        Timestamp ts = rs.getTimestamp("fDatum");
                        polozka.setDatum(ts.toLocalDateTime().toLocalDate());
                        polozka.setSuma(rs.getDouble("fSuma"));
                        polozka.setTyp(rs.getString("fTyp"));
                        polozka.setPopis(rs.getString("fPopis"));
                        return polozka;
                    }
                }
                return null;
            }
        });
    }

    @Override
    public List<Financie> getAllByTyp(String typ) {
        String sql = "SELECT "
                + "financie.id AS 'fId', "
                + "financie.datum AS 'fDatum',"
                + "financie.suma AS 'fSuma', "
                + "financie.typ AS 'fTyp', "
                + "financie.popis AS 'fPopis' "
                + "from farma.financie "
                + "WHERE financie.typ='" + typ + "';";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Financie>>() {
            @Override
            public List<Financie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Financie> financie = new ArrayList<>();
                Financie polozka = null;
                while (rs.next()) {
                    int polozkaId = rs.getInt("fId");
                    if (polozka == null || polozkaId != polozka.getId()) {
                        polozka = new Financie();
                        polozka.setId(polozkaId);
                        Timestamp ts = rs.getTimestamp("fDatum");
                        polozka.setDatum(ts.toLocalDateTime().toLocalDate());
                        polozka.setSuma(rs.getDouble("fSuma"));
                        polozka.setTyp(rs.getString("fTyp"));
                        polozka.setPopis(rs.getString("fPopis"));
                        financie.add(polozka);
                    }
                }
                return financie;
            }
        });
    }

    @Override
    public List<Financie> getAllZaObdobie(LocalDate datumOd, LocalDate datumDo) {
        String sql = "SELECT "
                + "financie.id AS 'fId', "
                + "financie.datum AS 'fDatum',"
                + "financie.suma AS 'fSuma', "
                + "financie.typ AS 'fTyp', "
                + "financie.popis AS 'fPopis' "
                + "from farma.financie "
                + "where datum between '" + datumOd + "' AND '" + datumDo + "';";
        return jdbcTemplate.query(sql, new ResultSetExtractor<List<Financie>>() {
            @Override
            public List<Financie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Financie> financie = new ArrayList<>();
                Financie polozka = null;
                while (rs.next()) {
                    int polozkaId = rs.getInt("fId");
                    if (polozka == null || polozkaId != polozka.getId()) {
                        polozka = new Financie();
                        polozka.setId(polozkaId);
                        Timestamp ts = rs.getTimestamp("fDatum");
                        polozka.setDatum(ts.toLocalDateTime().toLocalDate());
                        polozka.setSuma(rs.getDouble("fSuma"));
                        polozka.setTyp(rs.getString("fTyp"));
                        polozka.setPopis(rs.getString("fPopis"));
                        financie.add(polozka);
                    }
                }
                return financie;
            }
        });
    }
}
