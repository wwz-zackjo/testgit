/**
 * 
 */
package com.wishwingz.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wishwingz.model.board.Board;
import com.wishwingz.model.board.BoardComment;

/**
 * @author zackjo
 *
 */

@Repository
public class BoardRepository {
	private Log log = LogFactory.getLog(MemberRepository.class);
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private void printQueryId(String queryId) {
        if(log.isDebugEnabled()){
            log.debug("\t QueryId  \t:  " + queryId);
        }
    }
	
	public List<Board> find(int pageNum, int pageAmount){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("pageNum", pageNum);
		map.put("pageAmount", pageAmount);
		
        printQueryId("com.wishwingz.repository.BoardRepository.findAll");
        return sqlSession.selectList("com.wishwingz.repository.BoardRepository.findAll", map);
    }

	public Board findContent(int contentId) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.findContent");
		return sqlSession.selectOne("com.wishwingz.repository.BoardRepository.findContent", contentId);
	}
	
	public void modifyContentHit(int contentId) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.modifyHit");
		sqlSession.update("com.wishwingz.repository.BoardRepository.modifyHit", contentId);
	}
	
	public void modifyGrp(String contentId, int grp) {
		HashMap map = new HashMap();
		
		map.put("contentId", contentId);
		map.put("grp", grp);
		
		printQueryId("com.wishwingz.repository.BoardRepository.modifyGrp");
		sqlSession.update("com.wishwingz.repository.BoardRepository.modifyGrp", map);
	}
	
	public List<BoardComment> findComments (int contentId,int pageNum, int pageAmount){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("contentId", contentId);
		map.put("pageNum", pageNum);
		map.put("pageAmount", pageAmount);
		
		printQueryId("com.wishwingz.repository.BoardRepository.findComments");
        return sqlSession.selectList("com.wishwingz.repository.BoardRepository.findComments", map);
	}
	
	public void saveBoardContent (Board board) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.saveBoardContent");
		sqlSession.insert("com.wishwingz.repository.BoardRepository.saveBoardContent", board);
	}

	public void modifyBoardContent(Board board) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.modifyBoardContent");
		sqlSession.insert("com.wishwingz.repository.BoardRepository.modifyBoardContent", board);
	}

	public void deleteBoardContent(int contentId) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.deleteBoardContent");
		sqlSession.insert("com.wishwingz.repository.BoardRepository.deleteBoardContent", contentId);
	}
	
}
