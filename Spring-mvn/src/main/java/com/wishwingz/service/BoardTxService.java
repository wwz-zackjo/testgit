/**
 * 
 */
package com.wishwingz.service;

import com.wishwingz.model.board.Board;

/**
 * @author zackjo
 *
 */
public interface BoardTxService {
	public void saveBoardContent(Board board);

	public void deleteBoardContent(int contentId);
}
