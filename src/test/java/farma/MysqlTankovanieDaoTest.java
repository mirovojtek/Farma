
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


public class MysqlTankovanieDaoTest {
    
    private TankovanieDao tankovanieDao;
    private StrojDao strojDao;
    
    public MysqlTankovanieDaoTest() {
        tankovanieDao = DaoFactory.INSTANCE.getTankovanieDao();
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
   public void getAllPodlaIdStrojaTest(){
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       List<Tankovanie> tank = new ArrayList<>();
       Tankovanie t = new Tankovanie();
       t.setStrojId(stroj.getId());
       t.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       t.setMnozstvo(50.0);
       tank.add(t);
       stroj.setTankovania(tank);
       strojDao.add(stroj);
       assertEquals(1, tankovanieDao.getAllPodlaIdStroja(stroj.getId()).size());
       strojDao.deleteById(stroj.getId());
   }
    
    @Test
    public void getAllTest(){
        int pocet = tankovanieDao.getAll().size();
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       List<Tankovanie> tank = new ArrayList<>();
       Tankovanie t = new Tankovanie();
       t.setStrojId(stroj.getId());
       t.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       t.setMnozstvo(50.0);
       tank.add(t);
       stroj.setTankovania(tank);
       strojDao.add(stroj);
       assertEquals(pocet +1, tankovanieDao.getAll().size());
       strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void addTest(){
       int velkost = tankovanieDao.getAll().size();
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       strojDao.add(stroj);
       Tankovanie t = new Tankovanie();
       t.setStrojId(stroj.getId());
       t.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       t.setMnozstvo(50.0);
       tankovanieDao.add(t);
       assertEquals(velkost +1, tankovanieDao.getAll().size());
       strojDao.deleteById(stroj.getId());
    }
    
    @Test
    public void deletePodlaIdTest(){
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       strojDao.add(stroj);
       Tankovanie t = new Tankovanie();
       t.setStrojId(stroj.getId());
       t.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       t.setMnozstvo(50.0);
       tankovanieDao.add(t);
       int velkost = tankovanieDao.getAll().size();
       tankovanieDao.deletePodlaId(t.getId());
       assertEquals(velkost -1, tankovanieDao.getAll().size());
       strojDao.deleteById(stroj.getId());    
    }
       
    @Test
    public void deleteAllPodlaIdStroja(){
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       strojDao.add(stroj);
       Tankovanie t = new Tankovanie();
       t.setStrojId(stroj.getId());
       t.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       t.setMnozstvo(50.0);
       Tankovanie t2 = new Tankovanie();
       t2.setStrojId(stroj.getId());
       t2.setDatum(LocalDate.of(1787, Month.MARCH, 6));
       t2.setMnozstvo(100.0);
       tankovanieDao.add(t);
       tankovanieDao.add(t2);
       int velkost = tankovanieDao.getAll().size();
       tankovanieDao.deleteAllPodlaIdStroja(stroj.getId());
       assertEquals(velkost -2, tankovanieDao.getAll().size());
       strojDao.deleteById(stroj.getId());    
    }
}
