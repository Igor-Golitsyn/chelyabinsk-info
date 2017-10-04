
package polus.ddns.net.chelinfo.beans.yandexBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeoObject {

    @SerializedName("metaDataProperty")
    @Expose
    public MetaDataProperty_ metaDataProperty;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("boundedBy")
    @Expose
    public BoundedBy_ boundedBy;
    @SerializedName("Point")
    @Expose
    public Point_ point;

}
