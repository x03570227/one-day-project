package test.service;

import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.service.EverydayService;
import org.junit.Test;
import org.springframework.util.Assert;
import test.BaseServiceTestCase;

import javax.annotation.Resource;

/**
 * Created by mar on 16/5/28.
 */
public class EverydayServiceTest extends BaseServiceTestCase {

    @Resource
    private EverydayService everydayService;

    @Test
    public void test_search(){

        Pager pager =new Pager<EverydayModel>();
        pager = everydayService.search("", pager);
        Assert.notNull(pager);

    }
}
