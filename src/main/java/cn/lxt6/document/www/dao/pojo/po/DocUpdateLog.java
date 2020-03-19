package cn.lxt6.document.www.dao.pojo.po;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

/**
 * @author chenzy
 * @since 2020-03-19
 * @description 接口文档修改记录
 */
public class DocUpdateLog {
	private Integer id;

	/*'记录版本，从1.0开始'*/
	private String version;

	/*'修订日期*/
	@JsonProperty("log_date")
	private Date logDate;

	/*'修订者code'*/
	@JsonProperty("log_code")
	private String logCode;

	/*'修订描述'*/
	@JsonProperty("log_desc")
	private String logDes;

	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id=id;
	}
	public String getVersion(){
		return version;
	}
	public void setVersion(String version){
		this.version=version;
	}
	public Date getLogDate(){
		return logDate;
	}
	public void setLogDate(Date logDate){
		this.logDate=logDate;
	}
	public String getLogCode(){
		return logCode;
	}
	public void setLogCode(String logCode){
		this.logCode=logCode;
	}
	public String getLogDes(){
		return logDes;
	}
	public void setLogDes(String logDes){
		this.logDes=logDes;
	}
}