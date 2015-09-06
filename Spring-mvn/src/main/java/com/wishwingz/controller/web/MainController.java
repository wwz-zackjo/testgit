/**
 * 
 */
package com.wishwingz.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wishwingz.model.member.Member;
import com.wishwingz.service.MemberService;

/**
 * @author zackjo
 *
 */

@Controller
public class MainController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/test.wwz")
	public String helloWorld () {
		return "/hello";
	}
	
	@RequestMapping(value="/member.wwz")
	public String queryMember (ModelMap modelMap) {
		String TEST_ID = "1";
		
		List<Member> members = memberService.findAll(TEST_ID);
		
		modelMap.addAttribute("members", members);
		
		return "/member";
	}
}
