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
	
	public Board findContentWithComment(int contentId) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.findContentWithComment");
		return sqlSession.selectOne("com.wishwingz.repository.BoardRepository.findContentWithComment", contentId);
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
	
	public void modifyCommentGrp(String commentId, int grp) {
		HashMap map = new HashMap();
		
		map.put("id", commentId);
		map.put("grp", grp);
		
		printQueryId("com.wishwingz.repository.BoardRepository.modifyCommentGrp");
		sqlSession.update("com.wishwingz.repository.BoardRepository.modifyCommentGrp", map);
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
		sqlSession.update("com.wishwingz.repository.BoardRepository.modifyBoardContent", board);
	}

	public void deleteBoardContent(int contentId) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.deleteBoardContent");
		sqlSession.delete("com.wishwingz.repository.BoardRepository.deleteBoardContent", contentId);
	}

	public void modifyBoardContentComment(BoardComment boardComment) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.modifyBoardContentComment");
		sqlSession.update("com.wishwingz.repository.BoardRepository.modifyBoardContentComment", boardComment);	
	}
	
	public void modifyCommentCount(String contentId) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.modifyCommentCount");
		sqlSession.update("com.wishwingz.repository.BoardRepository.modifyCommentCount", contentId);	
	}
	
	public void saveBoardComment (BoardComment boardComment) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.saveBoardComment");
		sqlSession.insert("com.wishwingz.repository.BoardRepository.saveBoardComment", boardComment);
	}
	
	/**
	 * 댓글 하나만 삭제
	 * @param contentId
	 */
	public void deleteBoardComment(int id) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.deleteBoardComment");
		sqlSession.delete("com.wishwingz.repository.BoardRepository.deleteBoardComment", id);
	}
	
	/**
	 * 컨텐츠 하위의 댓글을 모두 지운다
	 * @param contentId
	 */
	public void deleteBoardCommentAll(int contentId) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.deleteBoardCommentAll");
		sqlSession.delete("com.wishwingz.repository.BoardRepository.deleteBoardCommentAll", contentId);
	}

	public Board findNextSibling(int grp, int order, int lvl) {
		HashMap map = new HashMap();
		
		map.put("grp", grp);
		map.put("order", order);
		map.put("lvl", lvl);
		
		printQueryId("com.wishwingz.repository.BoardRepository.findNextSibling");
		return sqlSession.selectOne("com.wishwingz.repository.BoardRepository.findNextSibling", map);
	}

	public void modifyContentOrderFrom(int order, int grp) {
		HashMap map = new HashMap();
		
		map.put("order", order);
		map.put("grp", grp);
		
		printQueryId("com.wishwingz.repository.BoardRepository.modifyContentOrderFrom");
		sqlSession.update("com.wishwingz.repository.BoardRepository.modifyContentOrderFrom", map);
	}
	
	public int findMaxOrder(int grp) {
		
		printQueryId("com.wishwingz.repository.BoardRepository.findMaxOrder");
		return sqlSession.selectOne("com.wishwingz.repository.BoardRepository.findMaxOrder", grp);
	}
	
}
