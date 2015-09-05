/**
 * 
 */
package com.wishwingz.service;

import java.util.List;

import com.wishwingz.model.member.Member;

/**
 * @author zackjo
 *
 */
public interface MemberService {
	
	public List<Member> findAll (String id);
	
}
