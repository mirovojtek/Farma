/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author student
 */
public class MysqlFinancieDaoTest {

    private FinancieDao financieDao;

    public MysqlFinancieDaoTest() {
        financieDao = DaoFactory.INSTANCE.getFinancieDao();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getAllTest() {
        Financie f = new Financie();
        f.setDatum(LocalDate.of(1111, Month.JANUARY, 1));
        f.setTyp("výdaj");
        f.setSuma(1111);
        f.setPopis("test");
        financieDao.add(f);
        f.setId(f.getId());
        System.out.println(f.getId());
        assertTrue(0 < financieDao.getAll().size());
        financieDao.deletePodlaId(f.getId());
    }

    @Test
    public void addTest() {
        int velkost = financieDao.getAll().size();
        Financie f = new Financie();
        f.setDatum(LocalDate.of(1111, Month.JANUARY, 1));
        f.setTyp("výdaj");
        f.setSuma(1111);
        f.setPopis("test");
        financieDao.add(f);
        f.setId(f.getId());
        System.out.println(f.getId());
        assertTrue(velkost + 1 == financieDao.getAll().size());
        financieDao.deletePodlaId(f.getId());
    }

    @Test
    public void deletePodlaIdTest() {
        Financie f = new Financie();
        f.setDatum(LocalDate.of(1111, Month.JANUARY, 1));
        f.setTyp("výdaj");
        f.setSuma(1111);
        f.setPopis("test");
        financieDao.add(f);
        int velkost = financieDao.getAll().size();
        boolean b = financieDao.deletePodlaId(f.getId());
        assertTrue(b);
        assertEquals(velkost - 1, financieDao.getAll().size());
    }

    @Test
    public void findById() {
        Financie f = new Financie();
        List<Financie> najdeny = new ArrayList<>();
        f.setDatum(LocalDate.of(1111, Month.JANUARY, 1));
        f.setTyp("výdaj");
        f.setSuma(1111);
        f.setPopis("test");
        financieDao.add(f);
        najdeny.add(financieDao.findById(f.getId()));
        assertTrue(najdeny.size() == 1);
        financieDao.deletePodlaId(f.getId());
    }

    @Test
    public void getAllByDate() {
        List<Financie> najdene = new ArrayList<>();
        Financie f1 = new Financie();
        f1.setDatum(LocalDate.of(1111, Month.JANUARY, 1));
        f1.setTyp("výdaj");
        f1.setSuma(2222);
        f1.setPopis("test");
        financieDao.add(f1);

        Financie f2 = new Financie();
        f2.setDatum(LocalDate.of(1111, Month.JANUARY, 1));
        f2.setTyp("príjem");
        f2.setSuma(1111);
        f2.setPopis("test");
        financieDao.add(f2);

        najdene.addAll(financieDao.getAllByDate(LocalDate.of(1111, Month.JANUARY, 1)));
        assertTrue(najdene.size() == 2);

        financieDao.deletePodlaId(f1.getId());
        financieDao.deletePodlaId(f2.getId());

        najdene.clear();
        najdene.addAll(financieDao.getAllByDate(LocalDate.of(1111, Month.JANUARY, 1)));
        assertTrue(najdene.size() == 0);
    }

    @Test
    public void getAllByTyp() {
        int pocetPrijmov = financieDao.getAllByTyp("príjem").size();
        Financie f = new Financie();
        List<Financie> najdeny = new ArrayList<>();
        f.setDatum(LocalDate.of(1111, Month.JANUARY, 1));
        f.setTyp("príjem");
        f.setSuma(1111);
        f.setPopis("test");
        financieDao.add(f);
        assertTrue(pocetPrijmov + 1 == financieDao.getAllByTyp("príjem").size());
        financieDao.deletePodlaId(f.getId());
    }

}
