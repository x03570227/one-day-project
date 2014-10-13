/**
 * 
 */
package net.caiban.pc.event.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.caiban.pc.event.service.TestService;

/**
 * @author mays
 *
 */
@Transactional
@Component("testService")
public class TestServiceImpl implements TestService {
	
}
