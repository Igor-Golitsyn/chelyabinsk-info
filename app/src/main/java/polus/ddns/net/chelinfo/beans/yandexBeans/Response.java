
package polus.ddns.net.chelinfo.beans.yandexBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("GeoObjectCollection")
    @Expose
    public GeoObjectCollection geoObjectCollection;

}
