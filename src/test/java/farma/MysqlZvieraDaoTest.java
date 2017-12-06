/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farma;

import java.time.LocalDateTime;
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
        zviera.setDruh("viľk");
        zviera.setPlemeno("šivy");
        zviera.setDatumNarodenia(LocalDateTime.now());
        zviera.setDatumNadobudnutia(LocalDateTime.now());
        zviera.setKupnaCena(50);
        zviera.setPohlavie("f");
        zvieraDao.add(zviera);
        assertEquals(velkost + 1, zvieraDao.getAll().size());
        zvieraDao.deleteByRegistracneCislo("100");
    }

    @Test
    public void findByRegistracneCisloTest() {
        String rc = "100";
        List<Zviera> najdene = new ArrayList<>();
        Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("viľkopies");
        zviera.setPlemeno("mixovanyj");
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
    public void getAllTest() {
        List<Zviera> vsetky = zvieraDao.getAll();
        assertTrue(vsetky.size() > 0);
        System.out.println(vsetky);
    }

    @Test
    public void deleteByRegistracneCisloTest() {
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
        assertEquals(velkost - 1, zvieraDao.getAll().size());
    }

    @Test
    public void pridajPopisTest() {
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

    @Test
    public void getDruhyTest(){
        int pocet = zvieraDao.getDruhy().size();
       Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("koza");
        zviera.setPlemeno("cierna");
        zviera.setDatumNarodenia(LocalDateTime.now());
        zviera.setDatumNadobudnutia(LocalDateTime.now());
        zviera.setKupnaCena(50);
        zviera.setPohlavie("f");
        zvieraDao.add(zviera);
        assertEquals(pocet+1, zvieraDao.getDruhy().size());
        zvieraDao.deleteByRegistracneCislo("100");
    }
    
   @Test
    public void getPohlaviaTest(){
        int pocet = zvieraDao.getPohlavia().size();
       Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("koza");
        zviera.setPlemeno("cierna");
        zviera.setDatumNarodenia(LocalDateTime.now());
        zviera.setDatumNadobudnutia(LocalDateTime.now());
        zviera.setKupnaCena(50);
        zviera.setPohlavie("d");
        zvieraDao.add(zviera);
        assertEquals(pocet+1, zvieraDao.getPohlavia().size());
        zvieraDao.deleteByRegistracneCislo("100");
    }
    
    @Test
    public void getPlemenaTest(){
        int pocet = zvieraDao.getPlemena().size();
       Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("koza");
        zviera.setPlemeno("skusobne");
        zviera.setDatumNarodenia(LocalDateTime.now());
        zviera.setDatumNadobudnutia(LocalDateTime.now());
        zviera.setKupnaCena(50);
        zviera.setPohlavie("f");
        zvieraDao.add(zviera);
        assertEquals(pocet+1, zvieraDao.getPlemena().size());
        zvieraDao.deleteByRegistracneCislo("100");
    }
    
    @Test
    public void getRokyNarodeniaTest(){
        int pocet = zvieraDao.getRokyNarodenia().size();
       Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("koza");
        zviera.setPlemeno("cierna");
        zviera.setDatumNarodenia(LocalDateTime.of(1787, Month.MARCH, 5, 5, 5));
        zviera.setDatumNadobudnutia(LocalDateTime.now());
        zviera.setKupnaCena(50);
        zviera.setPohlavie("f");
        zvieraDao.add(zviera);
        assertEquals(pocet+1, zvieraDao.getRokyNarodenia().size());
        zvieraDao.deleteByRegistracneCislo("100");
    }
    
    @Test
    public void getRokyNadobudnutiaTest(){
        int pocet = zvieraDao.getRokyNadobudnutia().size();
       Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("koza");
        zviera.setPlemeno("cierna");
        zviera.setDatumNarodenia(LocalDateTime.now());
        zviera.setDatumNadobudnutia(LocalDateTime.of(1787, Month.MARCH, 5, 5, 5));
        zviera.setKupnaCena(50);
        zviera.setPohlavie("f");
        zvieraDao.add(zviera);
        assertEquals(pocet+1, zvieraDao.getRokyNadobudnutia().size());
        zvieraDao.deleteByRegistracneCislo("100");
    }
    
    @Test
    public void rozsireneVyhladavanieTest(){
        Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("koza");
        zviera.setPlemeno("cierna");
        zviera.setDatumNarodenia(LocalDateTime.of(1787, Month.MARCH, 1, 0, 0));
        zviera.setDatumNadobudnutia(LocalDateTime.of(1787, Month.MARCH, 1, 0, 0));
        zviera.setKupnaCena(50);
        zviera.setPohlavie("f");
        List<Zviera> zvierata = new ArrayList<>();
        zvierata.add(zviera);
        int pocet = zvierata.size();
        zvieraDao.add(zviera);
        List<Zviera> zvierataZVyhladavania = zvieraDao.rozsireneVyhladavanie("koza", "cierna", "1787", "1787", "f");
        int pocet2 = zvierataZVyhladavania.size();
        assertEquals(pocet ,pocet2);
        zvieraDao.deleteByRegistracneCislo("100");
    }
    
    @Test
    public void getPlemenaPodlaDruhuTest(){
        Zviera zviera = new Zviera();
        zviera.setRegistracneCislo("100");
        zviera.setDruh("koza");
        zviera.setPlemeno("cierna");
        zviera.setDatumNarodenia(LocalDateTime.of(1787, Month.MARCH, 1, 0, 0));
        zviera.setDatumNadobudnutia(LocalDateTime.of(1787, Month.MARCH, 1, 0, 0));
        zviera.setKupnaCena(50);
        zviera.setPohlavie("f");
        zvieraDao.add(zviera);
        assertEquals(1, zvieraDao.getPlemenaPodlaDruhu("koza").size());
        zvieraDao.deleteByRegistracneCislo("100");
    }
}
