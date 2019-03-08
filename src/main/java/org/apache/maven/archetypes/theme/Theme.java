package org.apache.maven.archetypes.theme;

import java.util.List;

import org.apache.maven.archetypes.dataSource.ThemeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class Theme {
	@Autowired
	private ThemeDao themeDao;
	@RequestMapping(value="/queryTheme")
	@ResponseBody
	public String queryTheme(){
		List<ThemeObject> list = themeDao.queryTheme();
		return JSON.toJSONString(list);
	}
}
