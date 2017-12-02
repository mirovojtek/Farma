/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma;

import java.time.LocalDateTime;
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
 * @author Ondrej Spišák
 */
public class MysqlZvieraDaoTest {
    private ZvieraDao zvieraDao;
    
    
    public MysqlZvieraDaoTest() {
        zvieraDao = DaoFactory.INSTANCE.getZvieraDao(); 
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
     public void addTest() {
     int velkost = zvieraDao.getAll().size();
     Zviera zviera = new Zviera();
     zviera.setRegistracneCislo("100");
     zviera.setDruh("koza");
     zviera.setPlemeno("cierna");
     zviera.setDatumNarodenia(LocalDateTime.now());
     zviera.setDatumNadobudnutia(LocalDateTime.now());
     zviera.setKupnaCena(50);
     zviera.setPohlavie("f");
     zvieraDao.add(zviera);
     assertEquals(velkost +1, zvieraDao.getAll().size()); 
       zvieraDao.deleteByRegistracneCislo("100");
     }
     
     @Test
     public void findByRegistracneCisloTest(){
         String rc = "100";
         List<Zviera> najdene = new ArrayList<>();
     Zviera zviera = new Zviera();
     zviera.setRegistracneCislo("100");
     zviera.setDruh("koza");
     zviera.setPlemeno("cierna");
     zviera.setDatumNarodenia(LocalDateTime.now());
     zviera.setDatumNadobudnutia(LocalDateTime.now());
     zviera.setKupnaCena(50);
     zviera.setPohlavie("f");
     zvieraDao.add(zviera);
     najdene.add(zvieraDao.findByRegistracneCislo(rc));
     assertTrue(najdene.size() == 1);
     zvieraDao.deleteByRegistracneCislo("100");
     }
     
      @Test
      public void getAllTest(){
          List<Zviera> vsetky = zvieraDao.getAll();
          assertTrue(vsetky.size() > 0);
          System.out.println(vsetky);
      }
      @Test
      public void deleteByRegistracneCisloTest(){
     Zviera zviera = new Zviera();
     zviera.setRegistracneCislo("100");
     zviera.setDruh("koza");
     zviera.setPlemeno("cierna");
     zviera.setDatumNarodenia(LocalDateTime.now());
     zviera.setDatumNadobudnutia(LocalDateTime.now());
     zviera.setKupnaCena(50);
     zviera.setPohlavie("f");
     zvieraDao.add(zviera);
     int velkost = zvieraDao.getAll().size();
     zvieraDao.deleteByRegistracneCislo("100");
     assertEquals(velkost-1, zvieraDao.getAll().size());
      }
      
      @Test
      public void pridajPopisTest(){
          Zviera zviera = new Zviera();
     zviera.setRegistracneCislo("100");
     zviera.setDruh("koza");
     zviera.setPlemeno("cierna");
     zviera.setDatumNarodenia(LocalDateTime.now());
     zviera.setDatumNadobudnutia(LocalDateTime.now());
     zviera.setKupnaCena(50);
     zviera.setPohlavie("f");
     zvieraDao.add(zviera);
     zviera.setPopis("Ahoj");
     zvieraDao.pridajPopis(zviera);
     assertEquals(zviera.getPopis(), zvieraDao.findByRegistracneCislo("100").getPopis());
     zvieraDao.deleteByRegistracneCislo("100");
      }
      
}
