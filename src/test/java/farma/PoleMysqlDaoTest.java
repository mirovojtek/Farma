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

public class PoleMysqlDaoTest {

    private PoleDao poleDao;

    public PoleMysqlDaoTest() {
        poleDao = DaoFactory.INSTANCE.getPoleDao();
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
        Pole pole = new Pole();
        pole.setCisloParcely("test");
        pole.setTypParcely("C");
        pole.setTypPozemku("test");
        pole.setVlastnictvo("ine");
        pole.setVymera(1);
        poleDao.add(pole);
        assertTrue(0 < poleDao.getAll().size());
        poleDao.deletePodlaId(pole.getId());
    }

    @Test
    public void addTest() {
        int velkost = poleDao.getAll().size();
        Pole pole = new Pole();
        pole.setCisloParcely("test");
        pole.setTypParcely("C");
        pole.setTypPozemku("test");
        pole.setVlastnictvo("ine");
        pole.setVymera(1);
        poleDao.add(pole);
        assertTrue(velkost + 1 == poleDao.getAll().size());
        poleDao.deletePodlaId(pole.getId());
    }

    @Test
    public void deletePodlaIdTest() {
        Pole pole = new Pole();
        pole.setCisloParcely("test");
        pole.setTypParcely("C");
        pole.setTypPozemku("test");
        pole.setVlastnictvo("ine");
        pole.setVymera(1);
        poleDao.add(pole);
        int velkost = poleDao.getAll().size();
        boolean b = poleDao.deletePodlaId(pole.getId());
        assertTrue(b);
        assertEquals(velkost - 1, poleDao.getAll().size());
    }

    @Test
    public void findByIdTest() {
        List<Pole> najdenePole = new ArrayList<>();
        Pole pole = new Pole();
        pole.setCisloParcely("test");
        pole.setTypParcely("C");
        pole.setTypPozemku("test");
        pole.setVlastnictvo("ine");
        pole.setVymera(1);
        poleDao.add(pole);
        najdenePole.add(poleDao.findById(pole.getId()));
        assertTrue(najdenePole.size() == 1);
        poleDao.deletePodlaId(pole.getId());
    }

    @Test
    public void getTypyParcielTest() {

        Pole pole1 = new Pole();
        pole1.setCisloParcely("test");
        pole1.setTypParcely("C");
        pole1.setTypPozemku("test");
        pole1.setVlastnictvo("ine");
        pole1.setVymera(1);

        Pole pole2 = new Pole();
        pole2.setCisloParcely("test");
        pole2.setTypParcely("E");
        pole2.setTypPozemku("test");
        pole2.setVlastnictvo("ine");
        pole2.setVymera(1);

        poleDao.add(pole1);
        poleDao.add(pole2);

        List<String> najdeneTypy = new ArrayList<>();
        najdeneTypy.addAll(poleDao.getTypyParciel());
        assertTrue(najdeneTypy.size() == 2);
        poleDao.deletePodlaId(pole1.getId());
        poleDao.deletePodlaId(pole2.getId());
    }

    @Test
    public void getCislaParcielPodlaTypuTest() {
        int pocetParcielC = poleDao.getCislaParcielPodlaTypu("C").size();
        Pole pole = new Pole();
        pole.setCisloParcely("test");
        pole.setTypParcely("C");
        pole.setTypPozemku("test");
        pole.setVlastnictvo("ine");
        pole.setVymera(1);
        poleDao.add(pole);

        assertTrue((pocetParcielC + 1) == poleDao.getCislaParcielPodlaTypu("C").size());
        poleDao.deletePodlaId(pole.getId());
    }

    @Test
    public void getTypyPozemkov() {
        int pocetTypovPozemkov = poleDao.getTypyPozemkov().size();

        Pole pole = new Pole();
        pole.setCisloParcely("test");
        pole.setTypParcely("C");
        pole.setTypPozemku("test");
        pole.setVlastnictvo("ine");
        pole.setVymera(1);
        poleDao.add(pole);
        assertTrue(pocetTypovPozemkov + 1 == poleDao.getTypyPozemkov().size());
        poleDao.deletePodlaId(pole.getId());
    }

    @Test
    public void getVlastnictvoTest() {

        Pole pole1 = new Pole();
        pole1.setCisloParcely("test");
        pole1.setTypParcely("C");
        pole1.setTypPozemku("test");
        pole1.setVlastnictvo("najomca");
        pole1.setVymera(1);

        Pole pole2 = new Pole();
        pole2.setCisloParcely("test");
        pole2.setTypParcely("E");
        pole2.setTypPozemku("test");
        pole2.setVlastnictvo("ine");
        pole2.setVymera(1);

        Pole pole3 = new Pole();
        pole3.setCisloParcely("test");
        pole3.setTypParcely("E");
        pole3.setTypPozemku("test");
        pole3.setVlastnictvo("vlastnik");
        pole3.setVymera(1);

        poleDao.add(pole1);
        poleDao.add(pole2);
        poleDao.add(pole3);

        assertTrue(poleDao.getVlastnictvo().size() == 3);
        poleDao.deletePodlaId(pole1.getId());
        poleDao.deletePodlaId(pole2.getId());
        poleDao.deletePodlaId(pole3.getId());
    }

    @Test
    public void rozsireneVyhladavanieTest() {
        Pole pole = new Pole();
        pole.setCisloParcely("12321/1");
        pole.setTypParcely("E");
        pole.setTypPozemku("ladova kryha");
        pole.setVymera(1);
        pole.setVlastnictvo("ine");
        poleDao.add(pole);
        List<Pole> polia = new ArrayList<>();
        polia.addAll(poleDao.rozsireneVyhladavanie(pole.getTypParcely(), pole.getCisloParcely(), pole.getTypPozemku(), pole.getVlastnictvo()));
        assertTrue(polia.size() == 1);
        Pole pole2 = new Pole();
        pole2.setCisloParcely("12321/1");
        pole2.setTypParcely("E");
        pole2.setTypPozemku("ladova kryha");
        pole2.setVymera(2);
        pole2.setVlastnictvo("ine");
        poleDao.add(pole2);
        polia.clear();
        polia.addAll(poleDao.rozsireneVyhladavanie(pole.getTypParcely(), pole.getCisloParcely(), pole.getTypPozemku(), pole.getVlastnictvo()));
        assertTrue(polia.size() == 2);
        poleDao.deletePodlaId(pole.getId());
        poleDao.deletePodlaId(pole2.getId());
        polia.clear();
        polia.addAll(poleDao.rozsireneVyhladavanie(pole.getTypParcely(), pole.getCisloParcely(), pole.getTypPozemku(), pole.getVlastnictvo()));
        assertTrue(polia.size() == 0);
    }

}
