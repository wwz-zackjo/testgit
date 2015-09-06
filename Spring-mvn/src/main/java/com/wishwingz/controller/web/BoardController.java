/**
 * 
 */
package com.wishwingz.controller.web;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardTxService boardTxService;
	
	@RequestMapping(value="/list.wwz")
	public String listBoard(ModelMap modelMap, @RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum){
		
		List<Board> boards = boardService.find(pageNum, 20);
		
		modelMap.addAttribute("boards", boards);
		modelMap.addAttribute("pageNum", pageNum);
		
		return "/boardlist";
	}
	
	@RequestMapping(value="/detail.wwz")
	public String detailBoard(ModelMap modelMap, @RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(required = true, value = "idx") int contentId){
		
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
		
//		List<BoardComment> comments = new ArrayList<BoardComment>();
//		if(isExist){
//			comments = boardService.findComments(contentId, 1, 20);
//		}
		
		modelMap.addAttribute("isExist", isExist);
		modelMap.addAttribute("board", board);
		modelMap.addAttribute("comments", board.getComments());
		modelMap.addAttribute("pageNum", pageNum);
		
		return "/boardDetail";
	}
	
	/**
	 * 신규 쓰기 화면으로 이동, contentId가 있는 경우에는 수정 모드
	 * @param modelMap
	 * @param contentId
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/write.wwz")
	public String prepareWriteBoard(ModelMap modelMap, @RequestParam(value = "parentId", defaultValue = "-1") int parentId){
		Board board = null;
		
		int grp = 0; // 임시 값, Insert 하면서 아이디 값으로 업데이트
		int order = 1;
		int lvl = 0;
		
		modelMap.addAttribute("actionUrl", "/board/writeproc.wwz");
		modelMap.addAttribute("grp", grp);
		modelMap.addAttribute("order", order);
		modelMap.addAttribute("lvl", lvl);
		modelMap.addAttribute("board", board);
		modelMap.addAttribute("parentId", parentId);
		
		return "/boardWrite";
	}
	
	@RequestMapping(value="/writeproc.wwz", method=RequestMethod.POST) 
	public String writeBoard(ModelMap modelMap, @ModelAttribute Board board){
		
		// 서버에서 채워줄 값은 이것 뿐이다
		board.setRegDttm(DateTime.now().toDate());
		board.setUpdDttm(DateTime.now().toDate());
		
		try{
			// 답글 여부 처리는 서비스가 담당
			boardTxService.saveBoardContent(board);
		} catch(Exception e) {
			// do anything
			logger.debug(e.toString());
		}
		
		return UrlBasedViewResolver. REDIRECT_URL_PREFIX + "/board/list.wwz";
	}
	
	/**
	 * 수정 글쓰기 화면으로 이동, contentId 필수 값
	 * @param modelMap
	 * @param contentId
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/modify.wwz")
	public String prepareModifyBoard(ModelMap modelMap, @RequestParam(value = "idx", defaultValue = "0") int contentId, @RequestParam(value = "parentId", defaultValue = "-1") int parentId){
		
		// 수정모드인 경우
		Board board = boardService.findContent(contentId);
		int grp = board.getGrp();
		int order = board.getOrder();
		int lvl = board.getLvl();
		
		modelMap.addAttribute("actionUrl", "/board/modifyproc.wwz");
		modelMap.addAttribute("grp", grp);
		modelMap.addAttribute("order", order);
		modelMap.addAttribute("lvl", lvl);
		modelMap.addAttribute("board", board);
		modelMap.addAttribute("parentId", parentId);
		
		return "/boardWrite";
	}
	
	@RequestMapping(value="/modifyproc.wwz", method=RequestMethod.POST)
	public String modifyBoard(ModelMap modelMap, @ModelAttribute Board board){
		
		// 서버에서 채워줘야 하는 요소는 이것 뿐이다
		board.setUpdDttm(DateTime.now().toDate());
		
		try{
			boardService.modifyBoardContent(board);
		} catch(Exception e) {
			// do anything
			logger.debug(e.toString());
			return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
		}
		
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
	}
	
	@RequestMapping(value="/delete.wwz")
	public String deleteBoard(ModelMap modelMap, @RequestParam(value = "idx", required=true, defaultValue = "0") int contentId){
		
		try{
			// 하위 답변들을 어떻게 할지 선택
			boardTxService.deleteBoardContent(contentId);
		} catch(Exception e) {
			// do anything
			logger.debug(e.toString());
			return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
		}
		
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
	}
	
	@RequestMapping(value="/comment/save.wwz", method=RequestMethod.POST)
	public String saveComment(ModelMap modelMap, @ModelAttribute BoardComment boardComment, @RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(required = true, value = "idx") int contentId){
		
		boardComment.setRegDttm(DateTime.now().toDate());
		boardComment.setUpdDttm(DateTime.now().toDate());
		boardComment.setGrp(0);
		boardComment.setOrder(1);
		boardComment.setLvl(0);
		boardComment.setParentId(-1);
		
		try{
			boardTxService.saveBoardComment(boardComment);
		} catch(Exception e) {
			// do anything
			logger.debug(e.toString());
			return UrlBasedViewResolver.FORWARD_URL_PREFIX + "/board/detail.wwz";
		}
		
		return UrlBasedViewResolver.FORWARD_URL_PREFIX + "/board/detail.wwz";
	}
	
	@RequestMapping(value="/comment/modify.wwz", method=RequestMethod.POST)
	public String modifyBoard(ModelMap modelMap, @ModelAttribute BoardComment boardComment, @RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum){
		
		return UrlBasedViewResolver.REDIRECT_URL_PREFIX + "/board/list.wwz";
	}
	
	@RequestMapping(value="/comment/delete.wwz")
	public String modifyBoard(ModelMap modelMap, @RequestParam(value = "idx", required=true, defaultValue = "0") int commentId,
			@RequestParam(required = true, value = "pageNum", defaultValue = "1") int pageNum, 
			@RequestParam(required = true, value = "contentId") int contentId){
		
		try{
			// 하위 답변들을 어떻게 할지 선택
			boardTxService.deleteBoardComment(commentId);
		} catch(Exception e) {
			// do anything
			logger.debug(e.toString());
			return "/boardlist";
		}
		
		return "/boardlist";
	}
}
