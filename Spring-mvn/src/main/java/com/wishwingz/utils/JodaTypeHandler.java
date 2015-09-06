/**
 * 
 */
package com.wishwingz.utils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * @author zackjo
 * 
 */

// Copyright 2012 Lucas Libraro
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

@MappedTypes(DateTime.class)
public class JodaTypeHandler implements TypeHandler<Object> {

	public Object getResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp ts = rs.getTimestamp(columnName);

		if (ts != null) {
			return new DateTime(ts.getTime(), DateTimeZone.UTC);
		} else {
			return null;
		}
	}

	public DateTime getResult(ResultSet arg0, int arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
		Timestamp ts = cs.getTimestamp(columnIndex);
		
		if (ts != null) {
			return new DateTime(ts.getTime(), DateTimeZone.UTC);
		} else {
			return null;
		}
	}

	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {

		if (parameter != null) {
			ps.setTimestamp(i,
					new Timestamp(((DateTime) parameter).getMillis()));
		} else {
			ps.setTimestamp(i, null);
		}
	}
}
