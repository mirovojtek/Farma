package farma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

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
                        polozka.setDatum(ts.toLocalDateTime());
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
    public Financie add(Financie financie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deletePodlaId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Financie findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
