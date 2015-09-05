/**
 * 
 */
package com.wishwingz.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.wishwingz.model.board.Board;
import com.wishwingz.model.board.BoardComment;
import com.wishwingz.service.BoardService;
import com.wishwingz.service.BoardTxService;

/**
 * @author zackjo
 *
 */

@Controller
@RequestMapping(value="/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	@Autowired
	BoardTxService boardTxService;
	
	@RequestMapping(value="/list.wwz")
	public String listBoard(ModelMap modelMap, @RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum){
		
		List<Board> boards = boardService.find(pageNum, 20);
		
		modelMap.addAttribute("boards", boards);
		modelMap.addAttribute("pageNum", pageNum);
		
		return "/boardlist";
	}
	
	@RequestMapping(value="/detail.wwz")
	public String detailBoard(ModelMap modelMap, HttpServletRequest request, @RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(required = true, value = "idx") int contentId){
		
		Board board = boardService.findContent(contentId);
		
		boolean isExist = true;
		if(board == null) {
			isExist = false;
		} else {
			// 쿠키 값을 확인하여 unique 카운터를 구현한다
			boardService.modifyContentHit(contentId);
			board.setHit(board.getHit() + 1);
		}
		
		List<BoardComment> comments = new ArrayList<BoardComment>();
		if(isExist){
			comments = boardService.findComments(contentId, 1, 20);
		}
		
		modelMap.addAttribute("isExist", isExist);
		modelMap.addAttribute("board", board);
		modelMap.addAttribute("comments", comments);
		modelMap.addAttribute("pageNum", pageNum);
		
		return "/boardDetail";
	}
	
	/**
	 * 글쓰기 화면으로 이동, contentId가 있는 경우에는 수정 모드
	 * @param modelMap
	 * @param contentId
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/write.wwz")
	public String prepareWriteBoard(ModelMap modelMap, @RequestParam(value = "idx", defaultValue = "0") int contentId, @RequestParam(value = "parentId", defaultValue = "-1") int parentId){
		Board board;
		int grp = 0;
		int order = 0;
		int lvl = 0;
		
		// 수정모드인 경우
		if(contentId != 0){
			board = boardService.findContent(contentId);
			grp = board.getGrp();
			order = board.getOrder();
			lvl = board.getLvl();
		} else {
			if(parentId > 0){
				// 답글인 경우
				
			}
			board = null;
		}
		
		modelMap.addAttribute("grp", grp);
		modelMap.addAttribute("order", order);
		modelMap.addAttribute("lvl", lvl);
		modelMap.addAttribute("board", board);
		modelMap.addAttribute("parentId", parentId);
		
		return "/boardWrite";
	}
	
	@RequestMapping(value="/writeproc.wwz", method=RequestMethod.POST) 
	public String writeBoard(ModelMap modelMap, @ModelAttribute Board board){
		
		board.setRegDttm(DateTime.now().toDate());
		board.setUpdDttm(DateTime.now().toDate());
		board.setParentId(-1);
		
		try{
			boardTxService.saveBoardContent(board);
		} catch(Exception e) {
			// do anything
		}
		
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
	}
	
	@RequestMapping(value="/modify.wwz", method=RequestMethod.POST)
	public String modifyBoard(ModelMap modelMap, @ModelAttribute Board board){
		
		board.setUpdDttm(DateTime.now().toDate());
		board.setParentId(-1);
		
		boardService.modifyBoardContent(board);
		
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
	}
	
	@RequestMapping(value="/delete.wwz")
	public String deleteBoard(ModelMap modelMap, @RequestParam(value = "idx", required=true, defaultValue = "0") int contentId){
		
		try{
			boardTxService.deleteBoardContent(contentId);
		} catch(Exception e) {
			// do anything
		}
		
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
	}
}
