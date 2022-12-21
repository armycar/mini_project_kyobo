package com.armycar.kyobo.api;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.armycar.kyobo.entity.ViewEntity;
import com.armycar.kyobo.repoistory.BookRepository;
import com.armycar.kyobo.repoistory.DetailRepository;
import com.armycar.kyobo.repoistory.ViewRepository;
import com.armycar.kyobo.vo.ViewDetailInfoVO;

//home, booklist, member, detail
@RestController
@RequestMapping("/api")
public class APIController {
  @Autowired BookRepository bookRepo; 
  @Autowired DetailRepository detailRepo;
  @Autowired ViewRepository viewRepo;

  @GetMapping("/book/list/")
  public Map<String, Object> getBookList(
    @PageableDefault(size=5) Pageable pageable
  ) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    
    Page<ViewEntity> page = viewRepo.getBookList(pageable);
    // List<ViewEntity> list = page.getContent();
    List<ViewDetailInfoVO> list = new ArrayList<ViewDetailInfoVO>() ;
    for(ViewEntity data : page.getContent()) {
      ViewDetailInfoVO obj = new ViewDetailInfoVO();
      obj.copyValues(data);
      list.add(obj);
      // data.setBiPrice(formatter.format(data.getBiPrice()));
      // data.setDisCountPrice(formatter.format(data.getDisCountPrice()));
    }
    resultMap.put("total", page.getTotalPages());
    resultMap.put("curentpage",page.getNumber());
    resultMap.put("list",list);
    
    // NumberFormat formatter = new DecimalFormat("#,##0");// 소수점 이하 2자리까지만 노출
    // List<ViewEntity> list = page.getContent();
    // for(ViewEntity data : list) {
    //   data.setStrPrice(formatter.format(data.getBiPrice()));
    //   data.setStrDiscountPrice(formatter.format(data.getDisCountPrice()));
    // }
    return resultMap;
  }
  
  @GetMapping("book/search")
  public Map<String, Object> searchBookTitleList(
    @RequestParam @Nullable String keyword
  ) {
    Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    if(keyword == null) keyword ="";
 
    // resultMap.put("keyword", keyword);
    List<ViewEntity> list = viewRepo.searchBookTitle(keyword);
    resultMap.put("totalCount", list.size()); // 추가
    resultMap.put("list",list);
    return resultMap;
  }
}
// ?page=0&size=10