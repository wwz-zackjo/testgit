/**
 * 
 */
package com.wishwingz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wishwingz.model.board.Board;
import com.wishwingz.repository.BoardRepository;


/**
 * @author zackjo
 *
 */
@Service
public class BoardTxServiceImpl implements BoardTxService {
	@Autowired
	BoardRepository boardRepository;
	
	@Transactional
	public void saveBoardContent(Board board){
		
		boardRepository.saveBoardContent(board);
		
		int grp = Integer.parseInt(board.getId()) * -1;
		boardRepository.modifyGrp(board.getId(), grp);
		
	}
	
	@Transactional
	public void deleteBoardContent(int contentId) {
		
		// 보드 컨텐츠 하위에 댓글을 삭제한다
		// 보드 컨텐츠를 삭제한다
		boardRepository.deleteBoardContent(contentId);
		
	}
	
}