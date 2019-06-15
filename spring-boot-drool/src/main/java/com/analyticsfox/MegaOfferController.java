package com.analyticsfox;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analyticsfox.Order;



@RestController
@RequestMapping("/megaoffer")
public class MegaOfferController {
	@Autowired
	private KieSession session;

	@PostMapping("/order")
	public Order orderNow(@RequestBody Order order) {
		session.insert(order);
		session.fireAllRules();
		return order;
	}
}
