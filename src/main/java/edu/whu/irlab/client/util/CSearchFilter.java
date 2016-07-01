package edu.whu.irlab.client.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Map.Entry;

public class CSearchFilter {

	public enum COperator {
		EQ, LIKE, GT, LT, GTE, LTE, NEQ, NOTNULL, NOTEMPTY
	}

	public String fieldName;
	public Object value;
	public COperator operator;

	public CSearchFilter(String fieldName, COperator operator, Object value) {
		this.fieldName = fieldName;
		this.value = value;
		this.operator = operator;
	}

	/**
	 * searchParams涓璳ey鐨勬牸寮忎负OPERATOR_FIELDNAME
	 */
	public static Map<String, CSearchFilter> parse(Map<String, Object> searchParams) {
		Map<String, CSearchFilter> filters = Maps.newHashMap();

		for (Entry<String, Object> entry : searchParams.entrySet()) {
			// 杩囨护鎺夌┖鍊�
			String key = entry.getKey();
			Object value = entry.getValue();
			if (StringUtils.isBlank((String) value)) {
				continue;
			}

			// 鎷嗗垎operator涓巉iledAttribute
			String[] names = StringUtils.split(key, "_");
			if (names.length != 2) {
				throw new IllegalArgumentException(key + " is not a valid search filter name");
			}
			String filedName = names[1];
			COperator operator = COperator.valueOf(names[0]);

			// 鍒涘缓searchFilter
			CSearchFilter filter = new CSearchFilter(filedName, operator, value);
			filters.put(key, filter);
		}

		return filters;
	}
}
