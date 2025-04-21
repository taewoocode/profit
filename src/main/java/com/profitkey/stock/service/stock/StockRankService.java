package com.profitkey.stock.service.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.profitkey.stock.dto.KisApiProperties;
import com.profitkey.stock.dto.request.stock.FluctuationRequest;
import com.profitkey.stock.dto.request.stock.HtsTopViewRequest;
import com.profitkey.stock.dto.request.stock.MarketCapRequest;
import com.profitkey.stock.dto.request.stock.VolumeRankRequest;
import com.profitkey.stock.util.HeaderUtil;
import com.profitkey.stock.util.HttpClientUtil;
import java.io.IOException;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockRankService {
	private final KisApiProperties kisApiProperties;

	public ResponseEntity<Object> getVolumeRank(VolumeRankRequest request) {
		Object result = null;
		String urlData = kisApiProperties.getInquirePriceUrl();

		String trId = request.getTr_id();
		String custtype = request.getCusttype();
		String mrktDivCode = request.getFidCondMrktDivCode();
		String scrDivCode = request.getFidCondScrDivCode();
		String inputJscd = request.getFidInputJscd();
		String divClsCode = request.getFidDivClsCode();
		String blngClsCode = request.getFidBlngClsCode();
		String trgtClsCode = request.getFidTrgtClsCode();
		String trgtExlsClsCode = request.getFidTrgtExlsClsCode();
		String inputPrice1 = request.getFidInputPrice1();
		String inputPrice2 = request.getFidInputPrice2();
		String volCnt = request.getFidVolCnt();
		String inputDate1 = request.getFidInputDate1();

		StringBuilder paramDataBuilder = new StringBuilder("?");

		paramDataBuilder.append("fid_cond_mrkt_div_code=").append(mrktDivCode).append("&");
		paramDataBuilder.append("fid_cond_scr_div_code=").append(scrDivCode).append("&");
		paramDataBuilder.append("fid_input_iscd=").append(inputJscd).append("&");
		paramDataBuilder.append("fid_div_cls_code=").append(divClsCode).append("&");
		paramDataBuilder.append("fid_blng_cls_code=").append(blngClsCode).append("&");
		paramDataBuilder.append("fid_trgt_cls_code=").append(trgtClsCode).append("&");
		paramDataBuilder.append("fid_trgt_exls_cls_code=").append(trgtExlsClsCode).append("&");
		paramDataBuilder.append("fid_input_price_1=").append(inputPrice1).append("&");
		paramDataBuilder.append("fid_input_price_2=").append(inputPrice2).append("&");
		paramDataBuilder.append("fid_vol_cnt=").append(volCnt).append("&");
		paramDataBuilder.append("fid_input_date_1=").append(inputDate1).append("&");
		paramDataBuilder.setLength(paramDataBuilder.length() - 1);

		String paramData = paramDataBuilder.toString();
		String fullUrl = urlData + paramData;

		VolumeRankRequest requestParam = new VolumeRankRequest(
			trId, custtype, mrktDivCode, scrDivCode, inputJscd, divClsCode, blngClsCode,
			trgtClsCode, trgtExlsClsCode, inputPrice1, inputPrice2, volCnt, inputDate1
		);

		log.info("full url : {}", fullUrl);
		try {
			URL url = new URL(fullUrl);
			String jsonString = HttpClientUtil.sendGetRequest(url, HeaderUtil.getCommonHeaders(), requestParam);
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.readValue(jsonString, Object.class);
		} catch (IOException e) {
			e.getMessage();
		}
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<Object> getFluctuation(FluctuationRequest request) {
		Object result = null;
		String urlData = kisApiProperties.getInquirePriceUrl();

		String trId = request.getTr_id();
		String custtype = request.getCusttype();
		String fidRsflRate2 = request.getFidRsflRate2();
		String fidCondMrktDivCode = request.getFidCondMrktDivCode();
		String fidCondScrDivCode = request.getFidCondScrDivCode();
		String fidInputIscd = request.getFidInputIscd();
		String fidRankSortClsCode = request.getFidRankSortClsCode();
		String fidInputCnt1 = request.getFidInputCnt1();
		String fidPrcClsCode = request.getFidPrcClsCode();
		String fidInputPrice1 = request.getFidInputPrice1();
		String fidInputPrice2 = request.getFidInputPrice2();
		String fidVolCnt = request.getFidVolCnt();
		String fidTrgtClsCode = request.getFidTrgtClsCode();
		String fidTrgtExlsClsCode = request.getFidTrgtExlsClsCode();
		String fidDivClsCode = request.getFidDivClsCode();
		String fidRsflRate1 = request.getFidRsflRate1();

		StringBuilder paramDataBuilder = new StringBuilder("?");

		paramDataBuilder.append("fid_rsfl_rate2=").append(request.getFidRsflRate2()).append("&");
		paramDataBuilder.append("fid_cond_mrkt_div_code=").append(request.getFidCondMrktDivCode()).append("&");
		paramDataBuilder.append("fid_cond_scr_div_code=").append(request.getFidCondScrDivCode()).append("&");
		paramDataBuilder.append("fid_input_iscd=").append(request.getFidInputIscd()).append("&");
		paramDataBuilder.append("fid_rank_sort_cls_code=").append(request.getFidRankSortClsCode()).append("&");
		paramDataBuilder.append("fid_input_cnt_1=").append(request.getFidInputCnt1()).append("&");
		paramDataBuilder.append("fid_prc_cls_code=").append(request.getFidPrcClsCode()).append("&");
		paramDataBuilder.append("fid_input_price_1=").append(request.getFidInputPrice1()).append("&");
		paramDataBuilder.append("fid_input_price_2=").append(request.getFidInputPrice2()).append("&");
		paramDataBuilder.append("fid_vol_cnt=").append(request.getFidVolCnt()).append("&");
		paramDataBuilder.append("fid_trgt_cls_code=").append(request.getFidTrgtClsCode()).append("&");
		paramDataBuilder.append("fid_trgt_exls_cls_code=").append(request.getFidTrgtExlsClsCode()).append("&");
		paramDataBuilder.append("fid_div_cls_code=").append(request.getFidDivClsCode()).append("&");
		paramDataBuilder.append("fid_rsfl_rate1=").append(request.getFidRsflRate1()).append("&");
		paramDataBuilder.setLength(paramDataBuilder.length() - 1);

		String paramData = paramDataBuilder.toString();

		FluctuationRequest requestParam = new FluctuationRequest(
			trId, custtype, fidRsflRate2, fidCondMrktDivCode, fidCondScrDivCode, fidInputIscd,
			fidRankSortClsCode, fidInputCnt1, fidPrcClsCode, fidInputPrice1,
			fidInputPrice2, fidVolCnt, fidTrgtClsCode, fidTrgtExlsClsCode,
			fidDivClsCode, fidRsflRate1
		);

		String fullUrl = urlData + paramData;
		log.info("full url : {}", fullUrl);
		try {
			URL url = new URL(fullUrl);
			String jsonString = HttpClientUtil.sendGetRequest(url, HeaderUtil.getCommonHeaders(), requestParam);
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.readValue(jsonString, Object.class);
		} catch (IOException e) {
			e.getMessage();
		}
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<Object> getMarketCap(MarketCapRequest request) {
		Object result = null;
		String urlData = kisApiProperties.getMarketCapUrl();

		String trId = request.getTr_id();
		String custtype = request.getCusttype();
		String fidInputPrice2 = request.getFidInputPrice2();
		String fidCondMrktDivCode = request.getFidCondMrktDivCode();
		String fidCondScrDivCode = request.getFidCondScrDivCode();
		String fidDivClsCode = request.getFidDivClsCode();
		String fidInputIscd = request.getFidInputIscd();
		String fidTrgtClsCode = request.getFidTrgtClsCode();
		String fidTrgtExlsClsCode = request.getFidTrgtExlsClsCode();
		String fidInputPrice1 = request.getFidInputPrice1();
		String fidVolCnt = request.getFidVolCnt();

		StringBuilder paramDataBuilder = new StringBuilder("?");

		paramDataBuilder.append("fid_input_price_2=").append(request.getFidInputPrice2()).append("&");
		paramDataBuilder.append("fid_cond_mrkt_div_code=").append(fidCondMrktDivCode).append("&");
		paramDataBuilder.append("fid_cond_scr_div_code=").append(fidCondScrDivCode).append("&");
		paramDataBuilder.append("fid_div_cls_code=").append(fidDivClsCode).append("&");
		paramDataBuilder.append("fid_input_iscd=").append(fidInputIscd).append("&");
		paramDataBuilder.append("fid_trgt_cls_code=").append(fidTrgtClsCode).append("&");
		paramDataBuilder.append("fid_trgt_exls_cls_code=").append(fidTrgtExlsClsCode).append("&");
		paramDataBuilder.append("fid_input_price_1=").append(request.getFidInputPrice1()).append("&");
		paramDataBuilder.append("fid_vol_cnt=").append(request.getFidVolCnt()).append("&");
		paramDataBuilder.setLength(paramDataBuilder.length() - 1);

		String paramData = paramDataBuilder.toString();
		MarketCapRequest requestParam = new MarketCapRequest(
			trId, custtype, fidInputPrice2, fidCondMrktDivCode, fidCondScrDivCode, fidDivClsCode,
			fidInputIscd, fidTrgtClsCode, fidTrgtExlsClsCode, fidInputPrice1, fidVolCnt
		);

		String fullUrl = urlData + paramData;
		log.info("full url : {}", fullUrl);
		try {
			URL url = new URL(fullUrl);
			String jsonString = HttpClientUtil.sendGetRequest(url, HeaderUtil.getCommonHeaders(), requestParam);
			log.info("jsonString : {}", jsonString);
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.readValue(jsonString, Object.class);
		} catch (IOException e) {
			e.getMessage();
		}
		return ResponseEntity.ok(result);
	}

	public ResponseEntity<Object> getHtsTopView(HtsTopViewRequest request) {
		Object result = null;
		String urlData = kisApiProperties.getHtsTopViewUrl();

		String trId = request.getTr_id();
		String custtype = request.getCusttype();
		HtsTopViewRequest requestParam = new HtsTopViewRequest(
			trId, custtype
		);

		log.info("full url : {}", urlData);
		try {
			URL url = new URL(urlData);
			String jsonString = HttpClientUtil.sendGetRequest(url, HeaderUtil.getCommonHeaders(), requestParam);
			log.info("jsonString : {}", jsonString);
			ObjectMapper objectMapper = new ObjectMapper();
			result = objectMapper.readValue(jsonString, Object.class);
		} catch (IOException e) {
			e.getMessage();
		}
		return ResponseEntity.ok(result);
	}
}
