
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

public class MysqlStrojDaoTest {
    
    private StrojDao strojDao;
    
    public MysqlStrojDaoTest() {
        strojDao = DaoFactory.INSTANCE.getStrojDao();
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
    public void addTest(){
        int velkost = strojDao.getAll().size();
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        assertEquals(velkost + 1, strojDao.getAll().size());
        strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void findByIdTest(){
        Stroj stroj = new Stroj();
        List<Stroj> najdeny = new ArrayList<>();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        najdeny.add(strojDao.findById(stroj.getId()));
        assertTrue(najdeny.size() == 1);
        strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void getAllTest(){
        List<Stroj> vsetky = strojDao.getAll();
        assertTrue(vsetky.size() > 0);
        System.out.println(vsetky);
    }
    @Test
    public void deleteByIdTest(){
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        int velkost = strojDao.getAll().size();
        strojDao.deleteById(stroj.getId());
        assertEquals(velkost - 1, strojDao.getAll().size());
    }
    @Test
    public void getVyrobcoviaTest(){
        int velkost = strojDao.getVyrobcovia().size();
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        assertEquals(velkost+1, strojDao.getVyrobcovia().size());
        strojDao.deleteById(stroj.getId());
    }
    @Test
    public void getTypyTest(){
        int velkost = strojDao.getTypy().size();
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        assertEquals(velkost+1, strojDao.getTypy().size());
        strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void getTypyPodlaVyrobcuTest(){
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        assertEquals(1, strojDao.getTypyPodlaVyrobcu("test").size());
        strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void getKategorieTest(){
        int velkost = strojDao.getKategorie().size();
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        assertEquals(velkost + 1, strojDao.getKategorie().size());
        strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void getRokyNadobudnutiaTest(){
        int velkost = strojDao.getRokyNadobudnutia().size();
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        assertEquals(velkost + 1, strojDao.getRokyNadobudnutia().size());
        strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void rozsireneVyhladavanieTest(){
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        List<Stroj> stroje = new ArrayList<>();
        stroje.add(stroj);
        int pocet = stroje.size();
        List<Stroj> strojeZVyhladavania = strojDao.rozsireneVyhladavanie("test", "test", "test", "1787");
        int pocet2 = strojeZVyhladavania.size();
        assertEquals(pocet ,pocet2);
        strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void pridajPopisTest(){
        Stroj stroj = new Stroj();
        stroj.setVyrobca("test");
        stroj.setTyp("test");
        stroj.setKategoria("test");
        stroj.setCena(30);
        stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
        strojDao.add(stroj);
        stroj.setPopis("testovy popis");
        strojDao.pridajPopis(stroj);
        assertEquals(stroj.getPopis(), strojDao.findById(stroj.getId()).getPopis());
        strojDao.deleteById(stroj.getId());
    }
}
