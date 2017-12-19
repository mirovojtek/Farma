
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


public class MysqlOpravaDaoTest {
    
    private OpravaDao opravaDao;
    private StrojDao strojDao;
    
    public MysqlOpravaDaoTest() {
        opravaDao = DaoFactory.INSTANCE.getOpravaDao();
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
       List<Oprava> op = new ArrayList<>();
       Oprava o = new Oprava();
       o.setIdStroj(stroj.getId());
       o.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       o.setCena(50.0);
       o.setPorucha("test");
       op.add(o);
       stroj.setOpravy(op);
       strojDao.add(stroj);
       assertEquals(1, opravaDao.getAllPodlaIdStroja(stroj.getId()).size());
       strojDao.deleteById(stroj.getId());
         
     }
      @Test
    public void getAllTest(){
        int pocet = opravaDao.getAll().size();
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       List<Oprava> op = new ArrayList<>();
       Oprava o = new Oprava();
       o.setIdStroj(stroj.getId());
       o.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       o.setCena(50.0);
       o.setPorucha("test");
       op.add(o);
       stroj.setOpravy(op);
       strojDao.add(stroj);
       assertEquals(pocet +1, opravaDao.getAll().size());
       strojDao.deleteById(stroj.getId());
    }
    
    @Test
   public void addTest(){
       int velkost = opravaDao.getAll().size();
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       strojDao.add(stroj);
       Oprava o = new Oprava();
       o.setIdStroj(stroj.getId());
       o.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       o.setCena(50.0);
       o.setPorucha("test");
       opravaDao.add(o);
       assertEquals(velkost +1, opravaDao.getAll().size());
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
       List<Oprava> op = new ArrayList<>();
       Oprava o = new Oprava();
       o.setIdStroj(stroj.getId());
       o.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       o.setCena(50.0);
       o.setPorucha("test");
       op.add(o);
       opravaDao.add(o);
       int velkost = opravaDao.getAll().size();
       opravaDao.deletePodlaId(o.getId());
       assertEquals(velkost -1, opravaDao.getAll().size());
       strojDao.deleteById(stroj.getId());   
   }

   @Test
   public void deleteAllPodlaIdStrojaTest(){
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       strojDao.add(stroj);
       Oprava o = new Oprava();
       o.setIdStroj(stroj.getId());
       o.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       o.setCena(50.0);
       o.setPorucha("test");
       Oprava o2 = new Oprava();
       o2.setIdStroj(stroj.getId());
       o2.setDatum(LocalDate.of(1787, Month.MARCH, 6));
       o2.setCena(100.0);
       o2.setPorucha("test");
       opravaDao.add(o);
       opravaDao.add(o2);
       int velkost = opravaDao.getAll().size();
       opravaDao.deleteAllPodlaIdStroja(stroj.getId());
       assertEquals(velkost -2, opravaDao.getAll().size());
       strojDao.deleteById(stroj.getId());   
   }

   @Test
    public void findByIdTest(){
       Stroj stroj = new Stroj();
       stroj.setVyrobca("test");
       stroj.setTyp("test");
       stroj.setKategoria("test");
       stroj.setCena(30);
       stroj.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       strojDao.add(stroj);
       Oprava o = new Oprava();
       List<Oprava> op = new ArrayList<>();
       o.setIdStroj(stroj.getId());
       o.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       o.setCena(50.0);
       o.setPorucha("test");
       opravaDao.add(o);
       op.add(opravaDao.findById(o.getId()));
       assertTrue(op.size() == 1);
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
       Oprava o = new Oprava();
       o.setIdStroj(stroj.getId());
       o.setDatum(LocalDate.of(1787, Month.MARCH, 5));
       o.setCena(50.0);
       o.setPorucha("test");
       opravaDao.add(o);
       o.setPopis("caves");
       opravaDao.pridajPopis(o);
       assertEquals(o.getPopis(), opravaDao.findById(o.getId()).getPopis());
       strojDao.deleteById(stroj.getId());
    }
}
