package zdf.learn.com.commonUtils.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSONObject;

import zdf.learn.com.commonUtils.MultiTask.inf.TaskBeanInsertDbFromCSV;
import zdf.learn.com.commonUtils.data.TransferDataFromFilesToProdQuery;
import zdf.learn.com.commonUtils.data.TransferDataFromProdQuery;


@RestController
@RequestMapping("/query")
public class ToolsController {
	@Autowired
	private TransferDataFromProdQuery trans;
	@Autowired
	private TransferDataFromFilesToProdQuery insertTrans;
	@GetMapping("/fetch")
	public JSONObject fetchDataBase(){
		JSONObject returnJson = new JSONObject();
		trans.fetchToLocal();
		return returnJson;
	}
	@GetMapping("/insert")
	public JSONObject insertDataBase(){
		JSONObject returnJson = new JSONObject();
		insertTrans.insertToProd();
		return returnJson;
	}
}
