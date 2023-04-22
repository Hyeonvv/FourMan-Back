package fourman.backend.domain.freeBoard.controller;

import fourman.backend.domain.freeBoard.controller.requestForm.FreeBoardRequestForm;
import fourman.backend.domain.freeBoard.entity.FreeBoard;
import fourman.backend.domain.freeBoard.service.FreeBoardService;
import fourman.backend.domain.freeBoard.service.responseForm.FreeBoardImageResourceResponseForm;
import fourman.backend.domain.freeBoard.service.responseForm.FreeBoardResponseForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/free-board")
@RequiredArgsConstructor
public class FreeBoardController {

    final private FreeBoardService freeBoardService;

    @PostMapping(value = "/register",
            consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public FreeBoard boardRegister (@RequestPart(value = "fileList", required = false) List<MultipartFile> fileList,
                                    @RequestPart(value = "freeBoardInfo") FreeBoardRequestForm freeBoardRequest) {
        log.info("boardRegister()");

        return freeBoardService.register(fileList, freeBoardRequest);
    }

    @GetMapping("/list")
    public List<FreeBoardResponseForm> FreeBoardList () {

        return freeBoardService.list();
    }

    @GetMapping("/{boardId}")
    public FreeBoardResponseForm boardRead(@PathVariable("boardId") Long boardId) {
        log.info("boardRead()");

        return freeBoardService.read(boardId);
    }

    @DeleteMapping("/{boardId}")
    public void boardRemove(@PathVariable("boardId") Long boardId) {
        log.info("boardRemove()");

        freeBoardService.remove(boardId);
    }

    @PutMapping("/{boardId}")
    public Boolean boardModify(@PathVariable("boardId") Long boardId,
                             @RequestBody FreeBoardRequestForm boardRequest) {

        log.info("boardModify(): " + boardRequest + "id: " + boardId);

        return freeBoardService.modify(boardId, boardRequest);
    }

    @GetMapping("/myPage/{memberId}")
    public List<FreeBoard> freeBoardMyPageList(@PathVariable("memberId") Long memberId) {
        return freeBoardService.myPageList(memberId);
    }

    @GetMapping("/search/{searchText}")
    public List<FreeBoard> freeBoardSearchList(@PathVariable("searchText") String searchText) {
        return freeBoardService.searchFreeBoardList(searchText);
    }

    @PostMapping("/up-recommend/{boardId}")
    public Long increaseRecommendation (@PathVariable("boardId") Long boardId) {
        return freeBoardService.incRecommendation(boardId);
    }

    @PostMapping("/down-recommend/{boardId}")
    public Long decreaseRecommendation(@PathVariable("boardId") Long boardId) {
        return freeBoardService.decRecommendation(boardId);
    }

    @GetMapping("/imageList/{boardId}")
    public List<FreeBoardImageResourceResponseForm> readFreeBoardImageResource(
            @PathVariable("boardId") Long boardId) {


        return freeBoardService.findFreeBoardImage(boardId);
    }
}
