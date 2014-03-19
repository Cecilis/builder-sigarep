package sigarep.modelos.test.maestros;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import sigarep.modelos.data.maestros.SancionMaestro;
import sigarep.modelos.repositorio.maestros.ISancionMaestroDAO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/application-context.xml")
@Transactional
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=true)//si defaultRollback=false guarda en la BD y si se coloca true No guarda en la BD
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TestSancionMaestro {
	@Autowired
	private ISancionMaestroDAO sancionDao;
	private SancionMaestro sancionMaestro,resultado,resultadoB;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
		sancionMaestro=new SancionMaestro();
	}
	@After
	public void tearDown() throws Exception {
		sancionMaestro=null;
	}
	@Test
	public void testGuardarSancion(){
		sancionMaestro = new SancionMaestro(999, "Descripcion de prueba", true, "Sancion de Prueba");
		sancionDao.save(sancionMaestro);
		resultado=sancionDao.findOne(999);
		assertEquals(resultado.getIdSancion(), sancionMaestro.getIdSancion());
	}
	@Test
	public void testBuscarSancion(){
		resultadoB=sancionDao.findOne(1);
		assertNotNull(resultadoB);
		System.out.println("Nombre de La Sancion Buscanda en BD es :"+resultadoB.getNombreSancion());
	}
	@Test
	public void testEliminarSancion(){
		sancionDao.delete(1);
	}

}
