/**
 * 
 */
package com.wishwingz.service;

import com.wishwingz.model.board.Board;
import com.wishwingz.model.board.BoardComment;

/**
 * @author zackjo
 *
 */
public interface BoardTxService {
	public void saveBoardContent(Board board);

	public void deleteBoardContent(int contentId);
	
	public void saveBoardComment(BoardComment boardComment);

	public void deleteBoardComment(int id);
}
