package org.apache.maven.archetypes.web03;

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
	public String queryTourist(){
		System.out.println("查询景点");
		List<TouristAttraction> touristList = dbcp.queryTouristAttraction();
		return JSON.toJSONString(touristList);
	}
	@RequestMapping("/queryTouristById")
	@ResponseBody
	public String queryTouristById(@RequestParam(value="id") int id){
		TouristAttraction tourist = dbcp.queryTouristDescription(id);
		return JSON.toJSONString(tourist);
	}
}
