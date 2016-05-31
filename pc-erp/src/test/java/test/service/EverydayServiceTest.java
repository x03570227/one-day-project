package test.service;

import net.caiban.pc.erp.domain.EverydayModel;
import net.caiban.pc.erp.domain.Pager;
import net.caiban.pc.erp.service.EverydayService;
import org.junit.Assert;
import org.junit.Test;
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
        pager = everydayService.search("git", pager);
        Assert.assertNotNull(pager);
        Assert.assertEquals(19, ""+pager.getTotals());

    }
}
