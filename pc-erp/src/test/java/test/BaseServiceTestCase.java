package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-spring-application.xml"})
public class BaseServiceTestCase extends AbstractJUnit4SpringContextTests {

//	protected Connection connection;

//	@Autowired
//	private SqlMapClientFactoryBean sqlMapClient;

//	protected String[] getConfigLocations() {
//		return new String[] { "spring-desktop.xml" };
//	}
//
//	public void test_demo() {
//
//	}
//
//	public void onSetUp() {
//		try {
//			connection = ((SqlMapClient) sqlMapClient.getObject())
//					.getDataSource().getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void onTearDown() {
//		if (connection != null) {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//			}
//		}
//	}
//
//	public int insertResult() {
//		try {
//			ResultSet rs = connection.createStatement().executeQuery(
//					"select last_insert_id()");
//			if (rs.next()) {
//				return rs.getInt(1);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
}
