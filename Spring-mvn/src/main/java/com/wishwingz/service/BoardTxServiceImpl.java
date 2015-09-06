/**
 * 
 */
package com.wishwingz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wishwingz.model.board.Board;
import com.wishwingz.model.board.BoardComment;
import com.wishwingz.repository.BoardRepository;


/**
 * @author zackjo
 *
 */
@Service
public class BoardTxServiceImpl implements BoardTxService {
	@Autowired
	private BoardRepository boardRepository;
	
	@Transactional
	public void saveBoardContent(Board board){
		int parentId = board.getParentId();
		
		// 답글인 경우
		if(parentId > 0){
			Board parentBoard = boardRepository.findContent(parentId);
			Board sibling = boardRepository.findNextSibling(parentBoard.getGrp(),parentBoard.getOrder(),parentBoard.getLvl());
			
			if(sibling == null){
				// order 최대값 + 1로 삽입한다
				int maxOrder = boardRepository.findMaxOrder(parentBoard.getGrp());
				board.setOrder(maxOrder + 1);
			} else {
				boardRepository.modifyContentOrderFrom(sibling.getOrder(), parentBoard.getGrp());
				board.setOrder(sibling.getOrder());
			}
			
			board.setLvl(parentBoard.getLvl() + 1); // 레벨을 하나 높인다
			board.setGrp(parentBoard.getGrp()); // 부모와 같은 그룹으로 처리한다
		}
		
		boardRepository.saveBoardContent(board);
		
		// 신규 입력인 경우 그룹값을 한번 업데이트 해준다
		if(parentId <= 0){
			int grp = Integer.parseInt(board.getId()) * -1;
			boardRepository.modifyGrp(board.getId(), grp);
		}
	}
	
	@Transactional
	public void deleteBoardContent(int contentId) {
		
		// 보드 컨텐츠 하위에 댓글을 삭제한다
		boardRepository.deleteBoardCommentAll(contentId);
		
		// 보드 컨텐츠를 삭제한다
		boardRepository.deleteBoardContent(contentId);
	}

	@Transactional
	public void saveBoardComment(BoardComment boardComment) {
		
		boardRepository.saveBoardComment(boardComment);
		
		int grp = Integer.parseInt(boardComment.getId()) * -1;
		boardRepository.modifyCommentGrp(boardComment.getId(), grp);
		
		boardRepository.modifyCommentCount(boardComment.getContentId());
	}
	
	@Transactional
	public void deleteBoardComment(int id) {
		
		boardRepository.deleteBoardComment(id);
		
	}
	
}