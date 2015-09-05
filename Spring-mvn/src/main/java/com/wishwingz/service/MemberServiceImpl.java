/**
 * 
 */
package com.wishwingz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishwingz.model.member.Member;
import com.wishwingz.repository.MemberRepository;

/**
 * @author zackjo
 *
 */

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	public List<Member> findAll (String id){
		return memberRepository.findAll(id);
	}
}
