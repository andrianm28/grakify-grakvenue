package com.andrianm28.grakify;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.GeoPoint;

public class ParcelableGeoPoint implements Parcelable {

    private GeoPoint geoPoint;

    public ParcelableGeoPoint(GeoPoint point) {
        geoPoint = point;
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(geoPoint.getLatitude());
        out.writeDouble(geoPoint.getLongitude());
    }

    public static final Parcelable.Creator<ParcelableGeoPoint> CREATOR
            = new Parcelable.Creator<ParcelableGeoPoint>() {
        public ParcelableGeoPoint createFromParcel(Parcel in) {
            return new ParcelableGeoPoint(in);
        }

        public ParcelableGeoPoint[] newArray(int size) {
            return new ParcelableGeoPoint[size];
        }
    };

    private ParcelableGeoPoint(Parcel in) {
        Double lat = in.readDouble();
        Double lon = in.readDouble();
        geoPoint = new GeoPoint(lat, lon);
    }
}
