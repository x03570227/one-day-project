package test.service;

import net.caiban.pc.erp.domain.sys.SysUserModel;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.sys.SysUserMapper;
import net.caiban.pc.erp.service.sys.SysUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import test.BaseServiceTestCase;

import javax.annotation.Resource;

/**
 * Created by mar on 16/4/4.
 */
public class SysUserServiceTest extends BaseServiceTestCase{

    @InjectMocks
    @Resource
    private SysUserService sysUserService;

    @Mock
    private SysUserMapper sysUserMapper;

    @Before
    private void before(){
        MockitoAnnotations.initMocks(SysUserServiceTest.class);
    }

    @Test
    public void test_doWxRegist(){


        SysUserModel mockUser = new SysUserModel();

        try {
            sysUserService.doWxRegist(mockUser);
        } catch (ServiceException e) {

        }

    }

}
