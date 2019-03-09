package org.apache.maven.archetypes.web03;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.archetypes.dataSource.Dao;
import org.apache.maven.archetypes.tourist.TouristAttraction;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Hello world!
 *
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
@Controller

public class App 
{	@Autowired
	private Dao dbcp;
	@RequestMapping("/demo")
	@ResponseBody
    public String demo()
    {
        System.out.println( "Helssssssssslo World!" );
        return "123";
    }
	@RequestMapping("/queryTourist")
	@ResponseBody
	public String queryTourist(@RequestParam(value="page", defaultValue="1") int page,@RequestParam(value="category_id",defaultValue="0") int category_id){
		System.out.println("查询景点");
		List<TouristAttraction> touristList = dbcp.queryTouristAttraction(page,category_id);
		int total = dbcp.queryCount();
		class ResponseObj{
			private int currentPage;
			private int totalPage;
			private List<TouristAttraction> items;
			List<TouristAttraction> list = new ArrayList<TouristAttraction>();
			ResponseObj(List<TouristAttraction> list, int page, int total){
				this.currentPage = page;
				this.items = list;
				this.totalPage = (int) Math.ceil((double)total / 10);
			}
			public int getCurrentPage(){
				return this.currentPage;
			}
			public List<TouristAttraction> getItems(){
				return this.items;
			}
			public int getTotalPage(){
				return this.totalPage;
			}
		}
		ResponseObj ro = new ResponseObj(touristList,page,total);
//		ro.setItems(tourist);
		System.out.println(ro.currentPage + "执行了");
		return JSON.toJSONString(ro);
	}
	@RequestMapping("/queryTouristById")
	@ResponseBody
	public String queryTouristById(@RequestParam(value="id") int id){
		TouristAttraction tourist = dbcp.queryTouristDescription(id);
		return JSON.toJSONString(tourist);
	}
}
