
package polus.ddns.net.chelinfo.beans.yandexBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocoderResponseMetaData {

    @SerializedName("request")
    @Expose
    public String request;
    @SerializedName("found")
    @Expose
    public String found;
    @SerializedName("results")
    @Expose
    public String results;
    @SerializedName("boundedBy")
    @Expose
    public BoundedBy boundedBy;
    @SerializedName("Point")
    @Expose
    public Point point;
    @SerializedName("kind")
    @Expose
    public String kind;

}
