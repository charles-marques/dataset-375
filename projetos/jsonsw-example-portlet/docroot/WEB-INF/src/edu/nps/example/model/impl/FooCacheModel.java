/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package edu.nps.example.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import edu.nps.example.model.Foo;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Foo in entity cache.
 *
 * @author kirk
 * @see Foo
 * @generated
 */
public class FooCacheModel implements CacheModel<Foo>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{fooId=");
		sb.append(fooId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", field1=");
		sb.append(field1);
		sb.append(", field2=");
		sb.append(field2);
		sb.append(", field3=");
		sb.append(field3);
		sb.append(", field4=");
		sb.append(field4);
		sb.append(", field5=");
		sb.append(field5);
		sb.append("}");

		return sb.toString();
	}

	public Foo toEntityModel() {
		FooImpl fooImpl = new FooImpl();

		fooImpl.setFooId(fooId);
		fooImpl.setCompanyId(companyId);
		fooImpl.setUserId(userId);

		if (userName == null) {
			fooImpl.setUserName(StringPool.BLANK);
		}
		else {
			fooImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			fooImpl.setCreateDate(null);
		}
		else {
			fooImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fooImpl.setModifiedDate(null);
		}
		else {
			fooImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (field1 == null) {
			fooImpl.setField1(StringPool.BLANK);
		}
		else {
			fooImpl.setField1(field1);
		}

		fooImpl.setField2(field2);
		fooImpl.setField3(field3);

		if (field4 == Long.MIN_VALUE) {
			fooImpl.setField4(null);
		}
		else {
			fooImpl.setField4(new Date(field4));
		}

		if (field5 == null) {
			fooImpl.setField5(StringPool.BLANK);
		}
		else {
			fooImpl.setField5(field5);
		}

		fooImpl.resetOriginalValues();

		return fooImpl;
	}

	public long fooId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String field1;
	public boolean field2;
	public int field3;
	public long field4;
	public String field5;
}