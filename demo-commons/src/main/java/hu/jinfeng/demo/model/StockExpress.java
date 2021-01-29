package hu.jinfeng.demo.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述: 业绩快报
 *
 * @author ningfeng.hjf   @date 2019-06-20 16:23
 */
@ApiModel("股票业绩快报")
@Data
public class StockExpress {
    /** TS股票代码*/
    @ApiModelProperty("股票TS代码")
    private String tsCode;
    /** 公告日期*/
    private String annDate;
    /** 报告期*/
    private String endDate;
    /** 营业收入(元)*/
    private Double revenue;
    /** 营业利润(元)*/
    private Double operateProfit;
    /** 利润总额(元)*/
    private Double totalProfit;
    /** 净利润(元)*/
    private Double nIncome;
    /** 总资产(元)*/
    private Double totalAssets;
    /** 股东权益合计(不含少数股东权益)(元)*/
    private Double totalHldrEqyExcMinInt;
    /** 每股收益(摊薄)(元)*/
    private Double dilutedEps;
    /** 净资产收益率(摊薄)(%)*/
    private Double dilutedRoe;
    /** 去年同期修正后净利润*/
    private Double yoyNetProfit;
    /** 每股净资产*/
    private Double bps;
    /** 同比增长率:营业收入*/
    private Double yoySales;
    /** 同比增长率:营业利润*/
    private Double yoyOp;
    /** 同比增长率:利润总额*/
    private Double yoyTp;
    /** 同比增长率:归属母公司股东的净利润*/
    private Double yoyDeduNp;
    /** 同比增长率:基本每股收益*/
    private Double yoyEps;
    /** 同比增减:加权平均净资产收益率*/
    private Double yoyRoe;
    /** 比年初增长率:总资产*/
    private Double growthAssets;
    /** 比年初增长率:归属母公司的股东权益*/
    private Double yoyEquity;
    /** 比年初增长率:归属于母公司股东的每股净资产*/
    private Double growthBps;
    /** 去年同期营业收入*/
    private Double orLastYear;
    /** 去年同期营业利润*/
    private Double opLastYear;
    /** 去年同期利润总额*/
    private Double tpLastYear;
    /** 去年同期净利润*/
    private Double npLastYear;
    /** 去年同期每股收益*/
    private Double epsLastYear;
    /** 期初净资产*/
    private Double openNetAssets;
    /** 期初每股净资产*/
    private Double openBps;
    /** 业绩简要说明*/
    private String perfSummary;
    /** 是否审计： 1是 0否*/
    private Integer isAudit;
    /** 备注*/
    private String remark;
}
