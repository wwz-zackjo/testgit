/**
 * 
 */
package com.wishwingz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishwingz.model.board.Board;
import com.wishwingz.model.board.BoardComment;
import com.wishwingz.repository.BoardRepository;

/**
 * @author zackjo
 *
 */
@Service
public class BoardServiceImple implements BoardService {
	@Autowired
	BoardRepository boardRepository;
	
	public List<Board> find(int pageNum, int pageAmount) {
		
		pageNum = pageNum < 1 ? 0 : pageNum - 1;
		pageNum = pageNum * pageAmount;
		
		return boardRepository.find(pageNum, pageAmount);
	}

	public Board findContent(int idx) {
		
		return boardRepository.findContent(idx);
	}

	public void modifyContentHit(int contentId) {
		
		boardRepository.modifyContentHit(contentId);
	}

	public List<BoardComment> findComments(int contentId, int pageNum, int pageAmount) {
		
		pageNum = pageNum < 1 ? 0 : pageNum - 1;
		pageNum = pageNum * pageAmount;
		
		return boardRepository.findComments(contentId, pageNum, pageAmount);
	}

	public void modifyBoardContent(Board board) {
		
		boardRepository.modifyBoardContent(board);
		
	}
}
