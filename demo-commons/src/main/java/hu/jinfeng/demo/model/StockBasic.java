package hu.jinfeng.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("股票基本信息")
@Data
public class StockBasic {

    /**
     * ts_code /TS代码
     */
    @ApiModelProperty("股票TS代码")
    private String tsCode;
    /**
     * 股票代码
     */
    @ApiModelProperty("股票代码")
    private String symbol;//	str
    /**
     * 股票名称
     */
    private String name;
    /**
     * 所在地域
     */
    private String area;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 股票全称
     */
    private String fullname;
    /**
     * 英文全称
     */
    private String enname;
    /**
     * 市场类型 （主板/中小板/创业板）
     */
    private String market;
    /**
     * 交易所代码
     */
    private String exchange;
    /**
     * 交易货币
     */
    private String currType;
    /**
     * 上市状态： L上市 D退市 P暂停上市
     */
    private String listStatus;
    /**
     * 上市日期
     */
    private String listDate;
    /**
     * 退市日期
     */
    private String delistDate;
    /**
     * 是否沪深港通标的，N否 H沪股通 S深股通
     */
    private String isHs;


}
