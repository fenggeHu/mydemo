package hu.jinfeng.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("股票日线")
@Data
public class StockDaily {
    /**
     * 自增ID
     */
    private Long id;
    /**
     * ts_code /TS代码
     */
    @ApiModelProperty("股票TS代码")
    private String tsCode;

    @ApiModelProperty("股票代码")
    private String symbol;
    /**
     * 交易日期
     */
    @ApiModelProperty("交易日期")
    private String tradeDate;
    /**
     * 开盘价
     */
    private double open	;
    /**
     * 最高价
     */
    private double high;
    /**
     * 最低价
     */
    private double low;
    /**
     * 收盘价
     */
    private double close;
    /**
     * 昨收价
     */
    private double preClose;
    /**
     * 涨跌额
     */
    private double change;
    /**
     * 涨跌幅 （未复权）
     */
    private double pctChg;
    /**
     * 成交量 （手）
     */
    private double vol; 	
    /**
     * 成交额 （千元）
     */
    private double amount;

    /** MA */
    private double ma5;

    private double ma20;
}
