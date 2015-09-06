package com.wishwingz.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wishwingz.model.board.Board;
import com.wishwingz.service.BoardService;
import com.wishwingz.service.BoardTxService;

@Controller
@RequestMapping(value = "/board")
public class BoardApiController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardTxService boardTxService;
	
	@RequestMapping(value="/detail/{contentId}", method=RequestMethod.GET)
	public String detailBoard(ModelMap modelMap, @PathVariable("contentId") int contentId, @RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum){
		
		//Board board = boardService.findContent(contentId);
		Board board = boardService.findContentWithComment(contentId);
		
		boolean isExist = true;
		if(board == null) {
			isExist = false;
		} else {
			// 쿠키 값을 확인하여 unique 카운터를 구현한다
			boardService.modifyContentHit(contentId);
			board.setHit(board.getHit() + 1);
		}
		
//		modelMap.addAttribute("isExist", isExist);
//		modelMap.addAttribute("board", board);
//		modelMap.addAttribute("comments", board.getComments());
//		modelMap.addAttribute("pageNum", pageNum);
		
		
		
		return "/boardDetail";
	}
}
