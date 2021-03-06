package test.service;

import net.caiban.pc.erp.domain.SessionUser;
import net.caiban.pc.erp.domain.sys.SysUser;
import net.caiban.pc.erp.domain.sys.SysUserModel;
import net.caiban.pc.erp.exception.ServiceException;
import net.caiban.pc.erp.persist.sys.SysUserMapper;
import net.caiban.pc.erp.service.sys.SysUserService;
import net.caiban.utils.lang.RandomUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import test.BaseServiceTestCase;
import weixin.popular.bean.EventMessage;

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
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_doWxRegist_mobile(){


        Mockito.when(sysUserMapper.countByAccount(Mockito.anyString())).thenReturn(0);
        Mockito.when(sysUserMapper.updateUid(Mockito.anyLong(), Mockito.anyLong())).thenReturn(1);

        Mockito.when(sysUserMapper.insert(Mockito.any(SysUser.class))).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                SysUser user = (SysUser) args[0];
                user.setId(27l);
                return 1;
            }
        });

        SysUserModel mockUser = randomModel();
        mockUser.setAccount("13666624372");
        mockUser.setAccept(SysUser.ACCEPT_TRUE);
        mockUser.setPassword("12345");
        mockUser.setPasswordRepeat("12345");

        try {
            SessionUser user = sysUserService.doRegistByEveryday(mockUser);
            Assert.assertEquals(mockUser.getAccount(), user.getAccount());
            Assert.assertEquals(27l, user.getUid().longValue());
        } catch (ServiceException e) {
            Assert.fail();
        }

    }

    @Test
    public void test_doAuthByFollow(){
        EventMessage eventMessage = new EventMessage();
        eventMessage.setFromUserName(RandomStringUtils.randomAlphanumeric(32));
        eventMessage.setToUserName("aorgusername");
        sysUserService.doAuthByFollow(eventMessage);
    }

    private SysUserModel randomModel(){
        SysUserModel user = new SysUserModel();
        user.setAccount(RandomUtils.buildRandom(8));
        user.setPassword(RandomUtils.buildRandom(8));
        user.setPasswordRepeat(user.getPassword());
        user.setAccept(SysUser.ACCEPT_TRUE);
        return user;
    }

}
