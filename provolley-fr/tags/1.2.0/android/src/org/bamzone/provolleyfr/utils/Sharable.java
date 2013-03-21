/*
	ProVolley-fr
	Copyright (C) 2012 Christophe Bothamy
	
	This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see http://www.gnu.org/licenses.
*/  	
package org.bamzone.provolleyfr.utils;

import java.util.HashMap;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

public final class Sharable implements Parcelable {

private Object mObject;

public static final Parcelable.Creator<Sharable> CREATOR = new  Parcelable.Creator<Sharable>() {
    public Sharable createFromParcel(Parcel in) {
        return new Sharable (in);
    }

    @Override
    public Sharable[] newArray(int size) {
        return new Sharable[size];
    }
};

public Sharable(final Object obj) {
    mObject = obj;
}

public Sharable(Parcel in) {
    readFromParcel(in);
}

public Object obj() {
    return mObject;
}

@Override
public int describeContents() {
    return 0;
}

@Override
public void writeToParcel(final Parcel out, int flags) {
    final long val = SystemClock.elapsedRealtime();
    out.writeLong(val);
    put(val, mObject);
}

private void readFromParcel(final Parcel in) {
    final long val = in.readLong();
    mObject = get(val);
}

/////

private static final HashMap<Long, Object> sSharableMap = new HashMap<Long, Object>(3);

synchronized private static void put(long key, final Object obj) {
    sSharableMap.put(key, obj);
}


synchronized private static Object get(long key) {
    return sSharableMap.remove(key);
}

}