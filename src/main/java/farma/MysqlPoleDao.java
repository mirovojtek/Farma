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
        String sql = "SELECT pole.id AS 'pId', pole.parcela AS 'pParcela', pole.vymera AS 'pVymera', pole.typ AS 'typ', pole.datum_nadobudnutia AS 'pDatumNadobudnutia', pole.cena AS 'pCena' FROM pole;";
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
                        pole.setParcela(rs.getString("pParcela"));
                        pole.setVymera(rs.getInt("pVymera"));
                        pole.setTyp("pTyp");
                        Timestamp ts = rs.getTimestamp("pDatumNadobudnutia");
                        ts = rs.getTimestamp("pDatumNadobudnutia");
                        if (ts != null) {
                            pole.setDatumNadobudnutia(ts.toLocalDateTime());
                        }
                        pole.setCena(rs.getDouble("pCena"));
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
            simpleJdbcInsert.usingColumns("parcela", "vymera", "typ", "cena");
            Map<String, Object> data = new HashMap<>();
            data.put("parcela", pole.getParcela());
            data.put("vymera", pole.getVymera());
            data.put("typ", pole.getTyp());
            data.put("datum_nadobudnutia", pole.getDatumNadobudnutia());
            data.put("cena", pole.getCena());
            pole.setId(simpleJdbcInsert.executeAndReturnKey(data).intValue());
        } else { // UPDATE
            String sql = "UPDATE pole SET parcela = ?, vymera = ?,"
                    + "typ = ?, datum_nadobudnutia = ?, cena = ? WHERE id = " + pole.getId();

            jdbcTemplate.update(sql,
                    pole.getParcela(),
                    pole.getVymera(),
                    pole.getTyp(),
                    pole.getDatumNadobudnutia(),
                    pole.getCena());
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
}
