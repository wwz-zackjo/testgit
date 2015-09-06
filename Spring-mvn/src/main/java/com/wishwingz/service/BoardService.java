/**
 * 
 */
package com.wishwingz.service;

import java.util.List;

import com.wishwingz.model.board.Board;
import com.wishwingz.model.board.BoardComment;

/**
 * @author zackjo
 *
 */
public interface BoardService {

	public List<Board> find(int pageNum, int pageAmount);

	public Board findContent(int idx);

	public void modifyContentHit(int contentId);
	
	public void modifyBoardContent(Board board);
	
	public List<BoardComment> findComments(int contentId, int pageNum, int pageAmount);
	
	public void modifyBoardContentComment(BoardComment boardComment);

	public Board findContentWithComment(int contentId);
}
