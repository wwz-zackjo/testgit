/**
 * 
 */
package com.wishwingz.repository;

import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wishwingz.model.member.Member;

/**
 * @author zackjo
 *
 */

@Repository
public class MemberRepository {
	private Log log = LogFactory.getLog(MemberRepository.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	protected void printQueryId(String queryId) {
        if(log.isDebugEnabled()){
            log.debug("\t QueryId  \t:  " + queryId);
        }
    }
	
	public List<Member> findAll(String queryId){
        printQueryId(queryId);
        return sqlSession.selectList("com.wishwingz.repository.MemberRepository.findAll");
    }
}
